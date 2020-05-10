package com.rslakra.solid.dependencyinversion;

/**
 * Author: Rohtash Singh Lakra
 * Created: 5/9/20 7:45 PM
 * Version: 1.0.0
 */
public class Cube implements SolidShape {

    private double length;

    public Cube(double length) {
        this.length = length;
    }

    public double getLength() {
        return length;
    }

    /**
     * @return
     */
    @Override
    public double area() {
        return (6 * Math.pow(length, 2));
    }

    /**
     * @return
     */
    @Override
    public double volume() {
        return Math.pow(length, 3);
    }
}
