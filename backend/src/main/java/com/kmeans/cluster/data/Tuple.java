package com.kmeans.cluster.data;

import java.util.Set;

/**
 * Classe che rappresenta una riga di una tabella
 */
public class Tuple {
    /** Contenuto della riga */
    private final Item[] tuple;

    /**
     * Costruttore della classe
     * 
     * @param size Numero di colonne
     */
    public Tuple(int size) {
        this.tuple = new Item[size];
    }

    /**
     * Restituisce il numero di colonne della riga, ovvero la sua lunghezza
     * 
     * @return Numero di colonne
     */
    public int getLength() {
        return this.tuple.length;
    }

    /**
     * Restituisce il valore contenuto in posizione {@code i}
     * 
     * @param i Posizione da cui leggere un dato
     * @return Dato in posizione {@code i}
     */
    public Item get(int i) {
        return this.tuple[i];
    }

    /**
     * Aggiunge un item in posizione {@code i}
     * 
     * @param item Item da aggiungere
     * @param i    Posizione in cui aggiungerlo
     */
    public void add(Item item, int i) {
        this.tuple[i] = item;
    }

    /**
     * Calcola la distanza fra la tuple corrente e la tupla {@code t}
     * 
     * @param t Tupla da cui calcolare la distanza
     * @return Distanza fra le tuple
     */
    public double getDistance(Tuple t) {
        double distance = 0;

        for (int i = 0; i < this.tuple.length; i++) {
            distance += this.tuple[i].distance(t.get(i).getValue());
        }

        return distance;
    }

    /**
     * Calcola la distanza media tra la tupla corrente e le altre tuple
     * della tabella
     * 
     * @param data          Tuple della tabella
     * @param clusteredData Indici delle tuple
     * @return Distanza media
     */
    public double avgDistance(Data data, Set<Integer> clusteredData) {//
        double p = 0.0D;
        double sumD = 0.0D;

        for (Integer index : clusteredData) {
            double d = this.getDistance(data.getItemSet(index));
            sumD += d;
        }

        p = sumD / (double) clusteredData.size();
        return p;
    }
}
