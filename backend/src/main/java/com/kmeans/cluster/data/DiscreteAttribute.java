package com.kmeans.cluster.data;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Permette il salvataggio di un valore discreto da associare ad un attributo
 * in grado di contenerlo.
 * 
 * @see Attribute
 */
public class DiscreteAttribute extends Attribute implements Iterable<String> {
    /** Insieme dei valori */
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

    /**
     * Restituisce l'iteratore dell'insieme dei valori
     * 
     * @return Iteratore
     */
    public Iterator<String> iterator() {
        return this.values.iterator();
    }

    /**
     * Calcola la frequenza di un valore {@code v} all'intero della tabella
     * 
     * @param data   Tabella
     * @param idList Indici di colonna
     * @param v      Valore a cui calcolare la frequenza
     * @return Frequenza di {@code v}
     */
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
