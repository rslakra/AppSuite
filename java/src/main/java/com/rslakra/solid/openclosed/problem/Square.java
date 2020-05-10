package com.rslakra.solid.openclosed.problem;

/**
 * Author: Rohtash Singh Lakra
 * Created: 5/9/20 1:13 PM
 * Version: 1.0.0
 */
public class Square {

    private double length;

    public Square(double length) {
        this.length = length;
    }

    public double area() {
        return Math.pow(length, 2);
    }
}
