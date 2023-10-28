package com.kmeans.cluster.data;

/**
 * Permette il salvataggio di un valore discreto da associare ad un attributo in
 * grado di contenerlo
 * 
 * @see Item
 * @see Attribute
 * @see ContinuousAttribute
 */
public class DiscreteItem extends Item {

    /**
     * Costruttore
     * 
     * @param attribute Attributo in cui salvare un valore
     * @param value     Valore da salvare
     */
    public DiscreteItem(DiscreteAttribute attribute, String value) {
        super(attribute, value);
    }

    /**
     * Calcola la distanza tra l'oggetto corrente e l'oggetto {@code a}.
     * Se i due oggetti sono uguali, restituisce distanza 0, altimenti 1.
     * 
     * @param a Oggetto da cui calcolare la distanza
     * @return Distanza tra l'oggetto corrente e {@code a}
     */
    public double distance(Object a) {
        if (this.getValue().equals(a)) {
            return 0;
        }

        return 1;
    }
}
