package com.rslakra.solid.openclosed.solution;

/**
 * Author: Rohtash Singh Lakra
 * Created: 5/10/20 11:44 AM
 * Version: 1.0.0
 */
public class TestAreaCalculator {

    public static void main(String[] args) {
        Shape[] shapes = {new Circle(3), new Square(4), new Rectangle(3, 4)};
        AreaCalculator areaCalculator = new AreaCalculator(shapes);
        System.out.println(areaCalculator.sum());
    }
}
