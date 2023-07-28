package com.kmeans.cluster.data;

public abstract class Attribute {

    private String name; // Nome simbolico dell'attributo
    private int index; // Identificativo numerico dell'attributo

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

    @Override
    public String toString() {
        return String.format("{Name: %s}", this.name);
    }
}
