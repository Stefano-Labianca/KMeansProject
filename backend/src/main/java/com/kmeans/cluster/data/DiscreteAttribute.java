package com.kmeans.cluster.data;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * <h2>DiscreteAttribute</h2>
 * 
 * @see Attribute
 */
public class DiscreteAttribute extends Attribute implements Iterable<String> {

    private TreeSet<String> values;

    /**
     * <p>
     * Costruttore della classe DiscreteAttribute
     * </p>
     * 
     * @param name   Nome dell'attributo
     * @param index  Identificatore numerico dell'attributo
     * @param values Array di oggetti String, contenente i valori del dominio
     *               discreto
     */
    public DiscreteAttribute(String name, int index, TreeSet<String> values) {
        super(name, index);
        this.values = values;
    }

    /**
     * <p>
     * Restituisce le dimensioni di {@code values}, ovvero il numero di valori
     * discreti del dominio.
     * </p>
     * 
     * @return Dimensioni di {@code values}
     */
    public int getNumberOfDistinctValues() {
        return this.values.size();
    }

    public Iterator<String> iterator() {
        return this.values.iterator();
    }

    int frequency(Data data, Set<Integer> idList, String v) {
        int frequency = 0;

        for (Integer i : idList) {
            if (data.getAttributeValue(i, this.getIndex()).equals(v)) {
                frequency++;
            }
        }

        return frequency;
    }

}
