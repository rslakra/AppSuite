package com.rslakra.solid.dependencyinversion;

/**
 * Author: Rohtash Singh Lakra
 * Created: 5/10/20 11:49 AM
 * Version: 1.0.0
 */
public class TestShapes {
    public static void main(String[] args) {
        Calculator calculator = new AreaCalculator(new Circle(2), new Square(3));
        Renderer renderer = new HtmlRender(calculator);
        renderer.render();
        System.out.println();


        calculator = new VolumeCalculator(new Circle(2), new Cube(3));
        renderer = new JsonRender(calculator);
        renderer.render();
    }
}
