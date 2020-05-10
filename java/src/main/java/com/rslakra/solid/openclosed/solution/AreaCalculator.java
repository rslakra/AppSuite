package com.rslakra.solid.openclosed.solution;

/**
 * Author: Rohtash Singh Lakra
 * Created: 5/9/20 1:13 PM
 * Version: 1.0.0
 */
public class AreaCalculator {
    private Shape[] shapes;

    public AreaCalculator(Shape... shapes) {
        this.shapes = shapes;
    }

    public double sum() {
        double sum = 0;
        for (Shape shape : shapes) {
            sum += shape.area();
        }

        return sum;
    }

    public static void main(String[] args) {
        Shape[] shapes = {new Circle(3), new Square(4), new Rectangle(3, 4)};
        AreaCalculator areaCalculator = new AreaCalculator(shapes);
        System.out.println(areaCalculator.sum());
    }
}
