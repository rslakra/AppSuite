package com.rslakra.solid.interfacesegregation;

/**
 * Author: Rohtash Singh Lakra
 * Created: 5/10/20 11:47 AM
 * Version: 1.0.0
 */
public class TestCalculator {
    public static void main(String[] args) {
        AreaCalculator areaCalculator = new AreaCalculator(new Circle(3), new Square(4));
        System.out.println(areaCalculator.sum());

        VolumeCalculator volumeCalculator = new VolumeCalculator(new Circle(3), new Cube(2));
        System.out.println(volumeCalculator.sum());
    }
}
