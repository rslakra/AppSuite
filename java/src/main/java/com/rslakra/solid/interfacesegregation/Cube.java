package com.rslakra.solid.interfacesegregation;

import com.rslakra.solid.interfacesegregation.SolidShape;
import com.rslakra.solid.liskovsubstitution.Shape;

/**
 * Author: Rohtash Singh Lakra
 * Created: 5/9/20 7:45 PM
 * Version: 1.0.0
 */
public class Cube implements Shape, SolidShape {
    /**
     * @return
     */
    @Override
    public double area() {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public double volume() {
        return 0;
    }
}
