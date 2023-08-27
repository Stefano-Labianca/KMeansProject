package com.kmeans.cluster.data;

/**
 * ContinuousItem
 */
public class ContinuousItem extends Item {

    public ContinuousItem(Attribute attribute, double value) {
        super(attribute, value);
    }

    @Override
    double distance(Object a) {
        ContinuousAttribute attribute = (ContinuousAttribute) this.getAttribute();

        double firstValue = attribute.getScaledValue((double) this.getValue());
        double secondValue = attribute.getScaledValue((double) a);

        return Math.abs(firstValue - secondValue);
    }

}