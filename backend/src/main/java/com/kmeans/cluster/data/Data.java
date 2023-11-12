package com.kmeans.cluster.data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import com.kmeans.cluster.database.DatabaseConnectionException;
import com.kmeans.cluster.database.DbAccess;
import com.kmeans.cluster.database.EmptySetException;
import com.kmeans.cluster.database.Example;
import com.kmeans.cluster.database.NoValueException;
import com.kmeans.cluster.database.QUERY_TYPE;
import com.kmeans.cluster.database.TableData;
import com.kmeans.cluster.database.TableSchema;

/**
 * Contiene le informazioni di una tabella di un database
 */
public class Data {
	/** Dati della tabella */
	private List<Example> data;

	/** Contiene le colonne della tabella */
	private List<Attribute> attributeSet;

	/** Numero di colonne */
	private int numberOfExamples;

	/**
	 * Costruttore della classe Data
	 * 
	 * @param server       Indirizzo del server
	 * @param databaseName Nome del database
	 * @param tableName    Nome della tabella
	 * @param userId       Identificativo dell'utente
	 * @param password     Password dell'utente
	 * @param port         Numero di porta del database
	 */
	public Data(String server, String databaseName, String tableName, String userId, String password, Integer port)
			throws DatabaseConnectionException, SQLException, EmptySetException, NoValueException {

		DbAccess database = new DbAccess(server, databaseName, userId, password, port);
		database.initConnection();

		TableData tableData = new TableData(database);
		TableSchema schema = new TableSchema(database, tableName);

		this.data = tableData.getDistinctTransazioni(tableName);
		this.numberOfExamples = this.data.size();
		this.attributeSet = new ArrayList<Attribute>();

		for (int i = 0; i < schema.getNumberOfAttributes(); i++) {
			if (schema.getColumn(i).isNumber()) {
				this.attributeSet.add(new ContinuousAttribute(
						schema.getColumn(i).getColumnName(), i,
						(double) tableData.getAggregateColumnValue(tableName, schema.getColumn(i), QUERY_TYPE.MIN),
						(double) tableData.getAggregateColumnValue(tableName, schema.getColumn(i), QUERY_TYPE.MAX)));
			} else {
				HashSet<Object> distinctValue = (HashSet<Object>) tableData.getDistinctColumnValues(tableName,
						schema.getColumn(i));
				TreeSet<String> values = new TreeSet<String>();

				for (Object obj : distinctValue) {
					values.add((String) obj);
				}

				this.attributeSet.add(new DiscreteAttribute(
						schema.getColumn(i).getColumnName(), i, values));
			}
		}

	}

	/**
	 * Restituisce la cardinalita' dell'insieme di transazioni
	 * 
	 * @return Cardinalita' dell'insieme di transazioni
	 */
	public int getNumberOfExamples() {
		return this.numberOfExamples;
	}

	/**
	 * Restituisce la cardinalita' dell'insieme degli attributi
	 * 
	 * @return Cardinalita' dell'insieme degli attributi
	 */
	public int getNumberOfAttributes() {
		return this.attributeSet.size();
	}

	/**
	 * Restituisce un valore assunto in {@code data} dall'attributo alla riga
	 * {@code exampleIndex} e colonna {@code attributeIndex}.
	 * 
	 * @param exampleIndex   Indice della riga
	 * @param attributeIndex Indice della colonna
	 * @return Valore assunto in {@code data} dall'attributo alla riga
	 *         {@code exampleIndex} e colonna {@code attributeIndex}.
	 */
	public Object getAttributeValue(int exampleIndex, int attributeIndex) {
		return this.data.get(exampleIndex).get(attributeIndex);
	}

	/**
	 * Restituisce l'attributo alla posizione {@code index} dell'insieme
	 * degli attributi.
	 * 
	 * @param index Indice dell'attributo
	 * @return Attributo alla posizione {@code index} dell'insieme degli attributi
	 */
	public Attribute getAttribute(int index) {
		return this.attributeSet.get(index);
	}

	/**
	 * Restituisce l'insieme degli attributi della tabella del database in esame in
	 * un oggetto di tipo {@code Tuple}
	 * 
	 * @param index Indice della tupla da restituire
	 * @return Tupla contenente gli attributi della tabella del database in esame
	 */
	public Tuple getItemSet(int index) {
		Tuple tuple = new Tuple(this.attributeSet.size());

		for (int i = 0; i < this.attributeSet.size(); i++) {
			Object attributeObj = attributeSet.get(i);
			Object attributeValue = data.get(index).get(i);

			if (attributeObj instanceof ContinuousAttribute) {
				tuple.add(new ContinuousItem((ContinuousAttribute) attributeObj, (Double) attributeValue), i);
			} else {
				tuple.add(new DiscreteItem((DiscreteAttribute) attributeObj, (String) attributeValue), i);
			}
		}
		return tuple;
	}

	/**
	 * Calcola i centroidi
	 * 
	 * @param k Numero di cluster da generare
	 * @return Array di indici di centroidi
	 * @throws OutOfRangeSampleSize Causata quando il valore di {@code k} è maggiore
	 *                              del numero di righe della tabella
	 */
	public int[] sampling(int k) throws OutOfRangeSampleSize {
		if (k <= 0 || k > this.data.size()) {
			throw new OutOfRangeSampleSize("Inserire k compreso tra 1 e " + this.data.size());
		}

		int centroidIndexes[] = new int[k];
		// choose k random different centroids in data.
		Random rand = new Random();

		rand.setSeed(System.currentTimeMillis());

		for (int i = 0; i < k; i++) {
			boolean found = false;
			int c;
			do {
				found = false;
				c = rand.nextInt(getNumberOfExamples());
				// verify that centroid[c] is not equal to a centroide already stored in
				// CentroidIndexes
				for (int j = 0; j < i; j++)
					if (compare(centroidIndexes[j], c)) {
						found = true;
						break;
					}
			} while (found);
			centroidIndexes[i] = c;
		}
		return centroidIndexes;
	}

	/**
	 * Confronta due valori di attributi, restituendo false se sono
	 * diversi, altrimenti true
	 * 
	 * @param i Indice del primo attributo
	 * @param j Indice del secondo attributo
	 * @return Risultato del confronto
	 */
	private boolean compare(int i, int j) {
		for (int k = 0; k < this.attributeSet.size(); k++) {
			Object first = this.getAttributeValue(i, k);
			Object second = this.getAttributeValue(j, k);

			if (!first.equals(second)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Permette di associare un attributo generico ad un centroide
	 * 
	 * @param idList    Indici dei prototipi
	 * @param attribute Attributo a cui associare un centroide
	 * @return Prototipo trovato
	 */
	public Object computePrototype(Set<Integer> idList, Attribute attribute) {
		if (attribute instanceof DiscreteAttribute) {
			return this.computePrototype(idList, (DiscreteAttribute) attribute);
		}

		return this.computePrototype(idList, (ContinuousAttribute) attribute);
	}

	/**
	 * Permette di associare un attributo che può contenere valori continui ad un
	 * centroide
	 * 
	 * @param idList    Indici dei prototipi
	 * @param attribute Attributo a cui associare un centroide
	 * @return Prototipo trovato
	 */
	public String computePrototype(Set<Integer> idList, DiscreteAttribute attribute) {
		String prototype = "";
		int tMaxFrequency = 0;
		int max = 0;

		for (String attIter : attribute) {
			tMaxFrequency = attribute.frequency(this, idList, attIter);
			if (tMaxFrequency > max) {
				max = tMaxFrequency;
				prototype = attIter;
			}
		}
		return prototype;
	}

	/**
	 * Permette di associare un attributo che può contenere valori discreti ad un
	 * centroide
	 * 
	 * @param idList    Indici dei prototipi
	 * @param attribute Attributo a cui associare un centroide
	 * @return Prototipo trovato
	 */
	public Double computePrototype(Set<Integer> idList, ContinuousAttribute attribute) {
		double sum = 0.0D;
		int attributeIndex = attribute.getIndex();

		for (Integer index : idList) {
			sum += (double) data.get(index).get(attributeIndex);
		}

		return sum / (double) idList.size();
	}

	/**
	 * Restituisce la rappresentazione del contenuto della classe Data sottoforma di
	 * stringa
	 * 
	 * @return Stringa rappresentate la classe
	 */
	@Override
	public String toString() {
		String out = " ";

		for (int i = 0; i < this.attributeSet.size(); i++) {
			if (i == this.attributeSet.size() - 1) {
				out += this.attributeSet.get(i).getName();
			} else {
				out += this.attributeSet.get(i).getName() + ", ";
			}
		}

		out += "\n";

		for (int i = 0; i < this.numberOfExamples; i++) {
			String row = (i + 1) + ": ";
			String column = "";

			for (int j = 0; j < this.attributeSet.size(); j++) {
				if (j == this.attributeSet.size() - 1) {
					column += this.data.get(i).get(j);
				} else {
					column += this.data.get(i).get(j) + ", ";
				}
			}

			column += "\n";
			out += row + column;
		}

		return out;
	}
}
