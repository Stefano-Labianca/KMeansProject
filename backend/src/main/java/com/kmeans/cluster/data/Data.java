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

public class Data {
	private List<Example> data;
	private List<Attribute> attributeSet;
	private int numberOfExamples;

	/**
	 * Costruttore della classe Data
	 */
	public Data(String tableName)
			throws DatabaseConnectionException, SQLException, EmptySetException, NoValueException {
		DbAccess database = new DbAccess();
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
	 * 
	 * @param k
	 * @return
	 * @throws OutOfRangeSampleSize
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

	public Object computePrototype(Set<Integer> idList, Attribute attribute) {
		if (attribute instanceof DiscreteAttribute) {
			return this.computePrototype(idList, (DiscreteAttribute) attribute);
		}

		return this.computePrototype(idList, (ContinuousAttribute) attribute);
	}

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

	public Double computePrototype(Set<Integer> idList, ContinuousAttribute attribute) {
		double sum = 0.0D;
		int attributeIndex = attribute.getIndex();

		for (Integer index : idList) {
			sum += (double) data.get(index).get(attributeIndex);
		}

		return sum / (double) idList.size();
	}

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
