package com.rslakra.solid.interfacesegregation;

/**
 * Author: Rohtash Singh Lakra
 * Created: 5/9/20 1:13 PM
 * Version: 1.0.0
 */
public class VolumeCalculator extends AreaCalculator {

    public VolumeCalculator(SolidShape... shapes) {
        super(shapes);
    }

    public double sum() {
        //write logic to calculate the volume of the shapes.
        double sum = 0;
        for (Shape shape : shapes) {
            if (shape instanceof SolidShape) {
                sum += ((SolidShape) shape).volume();
            }
        }

        return sum;
    }
}
