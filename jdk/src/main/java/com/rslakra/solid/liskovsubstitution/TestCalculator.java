package com.rslakra.solid.liskovsubstitution;

/**
 * Author: Rohtash Singh Lakra
 * Created: 5/10/20 11:42 AM
 * Version: 1.0.0
 */
public class TestCalculator {
    public static void main(String[] args) {
        //area calculator
        AreaCalculator areaCalculator = new AreaCalculator(new Circle(2), new Square(3));
        Renderer renderer = new Renderer(areaCalculator);
        renderer.renderConsole();
        renderer.renderHtml();
        renderer.renderJson();

        //area calculator
        VolumeCalculator volumeCalculator = new VolumeCalculator(new Circle(2), new Square(3));
        renderer = new Renderer(areaCalculator);
        renderer.renderConsole();
        renderer.renderHtml();
        renderer.renderJson();
    }
}
