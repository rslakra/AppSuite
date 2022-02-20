package com.rslakra.solid.interfacesegregation;

/**
 * Author: Rohtash Singh Lakra
 * Created: 5/9/20 5:15 PM
 * Version: 1.0.0
 */
public class Renderer {
    private AreaCalculator calculator;

    public Renderer(AreaCalculator calculator) {
        this.calculator = calculator;
    }

    public void renderConsole() {
        System.out.println(String.format("Sum of the area of the shapes:%.2f", calculator.sum()));
    }

    public void renderJson() {
        System.out.println(String.format("{\"sum\":%.2f}", calculator.sum()));
    }

    public void renderHtml() {
        System.out.println(String.format("<html><h1>sum=%.2f</h1></html>", calculator.sum()));
    }
}
