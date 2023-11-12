package com.kmeans.cluster.database;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe che rappresenta un esempio di una tabella del database
 */
public class Example implements Comparable<Example> {
    /** Lista di oggetti che rappresentano i valori di una riga */
    private List<Object> example = new ArrayList<>();

    /**
     * Aggiunge un oggetto alla lista di oggetti che rappresentano i valori di una
     * riga
     * 
     * @param o Oggetto da aggiungere alla lista di oggetti che rappresentano i
     *          valori di una riga
     */
    public void add(Object o) {
        example.add(o);
    }

    /**
     * Restituisce il numero di colonne della riga, ovvero la sua lunghezza
     * 
     * @param i Posizione da cui leggere un dato
     * @return Dato in posizione {@code i}
     */
    public Object get(int i) {
        return example.get(i);
    }

    /**
     * Confronta la tupla corrente con la tupla {@code ex}
     * 
     * @param ex Tupla da confrontare con la tupla corrente
     * @return 0 se le tuple sono uguali, <br>
     *         -1 se la tupla corrente è minore di {@code ex}, <br>
     *         1 se la tupla corrente è maggiore di {@code ex}
     */
    public int compareTo(Example ex) {

        int i = 0;
        for (Object o : ex.example) {
            if (!o.equals(this.example.get(i))) {
                return ((Comparable) o).compareTo(example.get(i));
            }
            i++;
        }
        return 0;
    }

    /**
     * Permette di rappresentare come una stringa la classe Example
     * 
     * @return Rappresentazione come stringa della classe
     */
    @Override
    public String toString() {
        String b = "[";

        for (Object obj : this.example) {
            b += obj.toString() + ", s";
        }

        b += "]";

        return b;
    }
}
