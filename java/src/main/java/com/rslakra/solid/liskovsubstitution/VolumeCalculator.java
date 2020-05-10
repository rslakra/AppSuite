package com.rslakra.solid.liskovsubstitution;

/**
 * Author: Rohtash Singh Lakra
 * Created: 5/9/20 1:13 PM
 * Version: 1.0.0
 */
public class VolumeCalculator extends AreaCalculator {

    public VolumeCalculator(Shape... shapes) {
        super(shapes);
    }

    public double sum() {
        //write logic to calculate the volume of the shapes.
        double sum = 0;
        for (Shape shape : shapes) {
            if (shape instanceof Circle) {
                double radius = ((Circle) shape).getRadius();
//                4/3 × π × radius3.
                sum += (4 / 3 * 3.14 * Math.pow(radius, 3));
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        VolumeCalculator volumeCalculator = new VolumeCalculator(new Circle(3), new Square(4));
        System.out.println(volumeCalculator.sum());
    }
}
