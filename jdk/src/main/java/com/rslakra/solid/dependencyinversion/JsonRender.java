package com.rslakra.solid.dependencyinversion;

/**
 * Author: Rohtash Singh Lakra
 * Created: 5/10/20 12:17 PM
 * Version: 1.0.0
 */
public class JsonRender implements Renderer {

    private Calculator calculator;

    public JsonRender(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void render() {
        System.out.println(String.format("{\"sum\":%.2f}", calculator.calculate()));
    }
}
