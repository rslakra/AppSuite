package com.rslakra.solid.openclosed.problem;

/**
 * Author: Rohtash Singh Lakra
 * Created: 5/9/20 1:13 PM
 * Version: 1.0.0
 */
public class Circle {

    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double area() {
        return (3.14 * Math.pow(radius, 2));
    }

}
