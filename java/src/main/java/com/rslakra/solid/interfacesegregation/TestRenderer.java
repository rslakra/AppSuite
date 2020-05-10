package com.rslakra.solid.interfacesegregation;

/**
 * Author: Rohtash Singh Lakra
 * Created: 5/10/20 11:49 AM
 * Version: 1.0.0
 */
public class TestRenderer {
    public static void main(String[] args) {
        AreaCalculator areaCalculator = new AreaCalculator(new Circle(2), new Square(3));
        Renderer renderer = new Renderer(areaCalculator);
        renderer.renderConsole();
        renderer.renderHtml();
        renderer.renderJson();
    }
}
