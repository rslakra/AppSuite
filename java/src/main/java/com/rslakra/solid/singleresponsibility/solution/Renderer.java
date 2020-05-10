package com.rslakra.solid.singleresponsibility.solution;

/**
 * Author: Rohtash Singh Lakra
 * Created: 5/9/20 5:15 PM
 * Version: 1.0.0
 */
public class Renderer {
    private double sum;

    public Renderer(double sum) {
        this.sum = sum;
    }

    public void renderConsole() {
        System.out.println(String.format("Sum of the area of the shapes:%.2f", sum));
    }

    public void renderJson() {
        System.out.println(String.format("{\"sum\":%.2f}", sum));
    }

    public void renderHtml() {
        System.out.println(String.format("<html><h1>sum=%.2f</h1></html>", sum));
    }

}
