package com.kmeans.cluster.data;

import java.util.Set;

/**
 * Classe astratta in grado di rappresentare una cella di una tabella
 * 
 * @see Attribute
 */
public abstract class Item {
    /** Tipologia di dato che può contenere */
    private final Attribute attribute;

    /** Valore contenuto */
    private Object value;

    /**
     * Costruttore della classe
     * 
     * @param attribute Attributo da usare
     * @param value     Valore da associare all'attributo
     */
    Item(Attribute attribute, Object value) {
        this.attribute = attribute;
        this.value = value;
    }

    /**
     * Restituisce l'attributo
     * 
     * @return Attributo
     */
    public Attribute getAttribute() {
        return this.attribute;
    }

    /**
     * Restituisce il valore associato all'attributo
     * 
     * @return Valore
     */
    public Object getValue() {
        return this.value;
    }

    /**
     * Permette di rappresentare come una stringa la classe Item
     * 
     * @return Rappresentazione come stringa della classe
     */
    @Override
    public String toString() {
        return this.value.toString();
    }

    /**
     * Metodo astratto che calcola la distanza tra l'oggetto corrente e l'oggetto
     * {@code a}
     * 
     * @param a Oggetto da cui calcolare la distanza
     * @return Distanza tra l'oggetto corrente e {@code a}
     */
    abstract double distance(Object a);

    /**
     * Permette di aggiornare il contenuto di un item
     * 
     * @param data          Contenuto della tabella
     * @param clusteredData Insieme di indici di cluster
     */
    public void update(Data data, Set<Integer> clusteredData) {
        this.value = data.computePrototype(clusteredData, this.attribute);
    }
}
