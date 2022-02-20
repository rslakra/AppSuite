package com.rslakra.solid.liskovsubstitution;

/**
 * Author: Rohtash Singh Lakra
 * Created: 5/9/20 1:13 PM
 * Version: 1.0.0
 */
public class AreaCalculator {
    protected Shape[] shapes;

    public AreaCalculator(Shape... shapes) {
        this.shapes = shapes;
    }

    public double sum() {
        double sum = 0;
        for (Shape shape : shapes) {
            sum += shape.area();
        }

        return sum;
    }
}
