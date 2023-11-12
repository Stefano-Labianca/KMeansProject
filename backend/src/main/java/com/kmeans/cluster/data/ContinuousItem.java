package com.kmeans.cluster.data;

/**
 * Permette il salvataggio di un valore continuo da associare ad un attributo in
 * grado di contenerlo
 * 
 * @see Item
 * @see Attribute
 * @see ContinuousAttribute
 */
public class ContinuousItem extends Item {

    /**
     * Permette la creazione di un'istanza di ContinuousItem
     * 
     * @param attribute Attributo da usare
     * @param value     Valore da assegnare all'attributo
     */
    public ContinuousItem(Attribute attribute, double value) {
        super(attribute, value);
    }

    /**
     * Calcola la distanza tra l'oggetto corrente e l'oggetto {@code a}
     * 
     * @param a Oggetto da cui calcolare la distanza
     * @return Distanza tra l'oggetto corrente e {@code a}
     */
    @Override
    double distance(Object a) {
        ContinuousAttribute attribute = (ContinuousAttribute) this.getAttribute();

        double firstValue = attribute.getScaledValue((double) this.getValue());
        double secondValue = attribute.getScaledValue((double) a);

        return Math.abs(firstValue - secondValue);
    }

}