package com.rslakra.solid.dependencyinversion;

/**
 * Author: Rohtash Singh Lakra
 * Created: 5/10/20 12:17 PM
 * Version: 1.0.0
 */
public class HtmlRender implements Renderer {

    private Calculator calculator;

    public HtmlRender(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void render() {
        System.out.println(String.format("<html><h1>sum=%.2f</h1></html>", calculator.calculate()));
    }
}
