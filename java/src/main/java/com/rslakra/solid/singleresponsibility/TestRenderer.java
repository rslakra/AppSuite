package com.rslakra.solid.singleresponsibility;

import com.rslakra.solid.singleresponsibility.Circle;
import com.rslakra.solid.singleresponsibility.Renderer;
import com.rslakra.solid.singleresponsibility.Square;
import com.rslakra.solid.singleresponsibility.solution.AreaCalculator;

/**
 * Author: Rohtash Singh Lakra
 * Created: 5/10/20 11:45 AM
 * Version: 1.0.0
 */
public class TestRenderer {
    public static void main(String[] args) {
        com.rslakra.solid.singleresponsibility.solution.AreaCalculator areaCalculator = new AreaCalculator(new Circle(3), new Square(4));
        Renderer renderer = new Renderer(areaCalculator.sum());
        renderer.renderConsole();
        renderer.renderJson();
        renderer.renderHtml();
    }
}
