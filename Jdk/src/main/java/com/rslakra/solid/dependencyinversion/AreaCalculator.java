package com.rslakra.solid.dependencyinversion;

/**
 * Author: Rohtash Singh Lakra
 * Created: 5/9/20 1:13 PM
 * Version: 1.0.0
 */
public class AreaCalculator implements Calculator {
    protected Shape[] shapes;

    public AreaCalculator(Shape... shapes) {
        this.shapes = shapes;
    }

    @Override
    public double calculate() {
        double sum = 0;
        for (Shape shape : shapes) {
            sum += shape.area();
        }

        return sum;
    }
}
