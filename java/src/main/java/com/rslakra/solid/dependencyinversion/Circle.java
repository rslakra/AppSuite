package com.rslakra.solid.dependencyinversion;

/**
 * Author: Rohtash Singh Lakra
 * Created: 5/9/20 1:13 PM
 * Version: 1.0.0
 */
public class Circle implements SolidShape {

    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public double area() {
        return (3.14 * Math.pow(radius, 2));
    }

    public double volume() {
        // 4/3 × π × radius3.
        return (4 / 3 * 3.14 * Math.pow(radius, 3));
    }
}
