package com.kmeans.cluster.data;

import java.io.Serializable;

/**
 * Classe astratta che rappresenta un attributo generico di una tabella
 */
public abstract class Attribute implements Serializable {

    /** Nome simbolico dell'attributo */
    private String name;

    /** Identificativo numerico dell'attributo */
    private int index;

    /**
     * <p>
     * Costruttore classe astratta Attribute.
     * In input prende il nome dell'attributo e l'identificativo numerico
     * dell'attributo
     * </p>
     * 
     * @param name  Nome attributo
     * @param index Identificatore numerico dell'attributo
     */
    public Attribute(String name, int index) {
        this.name = name;
        this.index = index;
    }

    /**
     * <p>
     * Restituisce il nome simbolico dell'attributo
     * </p>
     * 
     * @return Nome dell' attributo simbolico
     */
    public String getName() {
        return this.name;
    }

    /**
     * <p>
     * Restituisce l'identificativo numerico dell'attributo
     * </p>
     * 
     * @return Identificativo numerico dell'attributo
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * Permette di rappresentare come una stringa la classe Attribute
     * 
     * @return Stringa rappresentate l'attributo
     */
    @Override
    public String toString() {
        return String.format("{Name: %s}", this.name);
    }
}
