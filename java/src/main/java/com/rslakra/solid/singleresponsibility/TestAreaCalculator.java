package com.rslakra.solid.singleresponsibility;

/**
 * Author: Rohtash Singh Lakra
 * Created: 5/10/20 11:45 AM
 * Version: 1.0.0
 */
public class TestAreaCalculator {
    public static void main(String[] args) {
        AreaCalculator areaCalculator = new AreaCalculator(new Circle(3), new Square(4));
        areaCalculator.output();
    }
}
