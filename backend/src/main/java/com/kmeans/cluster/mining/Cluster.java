package com.kmeans.cluster.mining;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.kmeans.cluster.data.Data;
import com.kmeans.cluster.data.Tuple;

/**
 * CLasse che rappresenta un cluster
 * 
 * @see Tuple
 */
public class Cluster implements Serializable {
	/** Centroide del cluster */
	private Tuple centroid;

	/** Indici di tuple associate al cluster */
	private Set<Integer> clusteredData;

	/**
	 * Costruttore di un cluster
	 * 
	 * @param centroid Tupla che rappresenta il centroide
	 */
	Cluster(Tuple centroid) {
		this.centroid = centroid;
		clusteredData = new HashSet<Integer>();
	}

	/**
	 * Restituisce il centroide
	 * 
	 * @return Centroide del cluster
	 */
	Tuple getCentroid() {
		return centroid;
	}

	/**
	 * Calcola un
	 * 
	 * @param data
	 */
	void computeCentroid(Data data) {
		for (int i = 0; i < centroid.getLength(); i++) {
			centroid.get(i).update(data, clusteredData);
		}
	}

	/**
	 * Restituisce true se la tupla ha cambiato cluster, altrimenti false
	 * 
	 * @param id indice della tupla
	 * @return Esito del controllo
	 */
	boolean addData(int id) {
		return clusteredData.add(id);
	}

	/**
	 * Verifica se una transazione e' clusterizzata nell'array corrente
	 * 
	 * @param id Indice della tupla
	 * @return Esito della verifica
	 */
	boolean contain(int id) {
		return clusteredData.contains(id);
	}

	// remove the tuple that has changed the cluster
	void removeTuple(int id) {
		clusteredData.remove(id);
	}

	/**
	 * Rappresenta con una stringa il centroide
	 * 
	 * @return Stringa rappresentate il centroide
	 */
	public String toString() {
		String str = "Centroid=(";
		for (int i = 0; i < centroid.getLength(); i++)
			str += centroid.get(i) + " ";
		str += ")";
		return str;
	}

	/**
	 * Rappresenta con una stringa il cluster
	 * 
	 * @return Stringa rappresentate il cluster
	 */
	public String toString(Data data) {
		String str = "Centroid=(";
		for (int i = 0; i < centroid.getLength(); i++) {
			str += centroid.get(i) + (i == centroid.getLength() - 1 ? "" : " ");
		}
		str += ")\nExamples:\n";
		for (int i : clusteredData) {
			str += "[";
			for (int j = 0; j < data.getNumberOfAttributes(); j++)
				str += data.getAttributeValue(i, j) + (j == data.getNumberOfAttributes() - 1 ? "" : " ");
			str += "] dist=" + getCentroid().getDistance(data.getItemSet(i)) + "\n";

		}
		str += "AvgDistance=" + getCentroid().avgDistance(data, clusteredData) + "\n";
		return str;
	}

}
