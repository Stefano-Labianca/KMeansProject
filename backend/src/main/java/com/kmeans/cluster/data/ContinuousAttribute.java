package com.kmeans.cluster.data;

/**
 * Rappresenta un attributo in grado di contenere valori continui, come numeri
 * 
 * @see Attribute
 */
public class ContinuousAttribute extends Attribute {
    /** Valore massimo del dominio */
    private double max;

    /** Valore minimo del dominio */
    private double min;

    /**
     * <p>
     * Crea un'istanza della classe ContinuousAttribute.
     * </p>
     * 
     * @param name  Nome dell'attributo
     * @param index Identificatore numerico dell'attributo
     * @param min   Valore minimo del dominio
     * @param max   Valore massimo del dpminio
     */
    public ContinuousAttribute(String name, int index, double min, double max) {
        super(name, index);
        this.max = max;
        this.min = min;
    }

    /**
     * <p>
     * Calcola e restituisce il valore normalizato del parametro passato in input.
     * La normalizzazione ha come codominio l'intervallo [0, 1].
     * </p>
     * 
     * <p>
     * La normalizzazione di v e' quindi calcolaa come segue:
     * </p>
     * 
     * <p>
     * {@code v' = (v - min) / (max - min)}
     * </p>
     * 
     * @param v Valore dell'attributo da normalizzare
     * @return Valore normalizzato tramite la formula
     */
    public double getScaledValue(double v) {
        return (v - this.min) / (this.max - this.min);
    }

}
