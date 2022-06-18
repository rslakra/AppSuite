# SOLID

The ```SOLID``` is an acronym for the first five object-oriented design(OOD) **principles** by Robert C. Martin, 
popularly known as [Uncle Bob](https://en.wikipedia.org/wiki/Robert_C._Martin).

These principles, all together, make it easy for a programmer to develop software that are easy to maintain and extend.
They also make it easy to avoid code pitfalls, refactor code, and are a part of the agile or adaptive software 
development too.



## SOLID stands for:

    S - Single-Responsiblity Principle
    O - Open-Closed Principle
    L - Liskov Substitution Principle
    I - Interface Segregation Principle
    D - Dependency Inversion Principle

Let's look at each principle individually to understand the SOLID acronym.


###1. Single-Responsibility Principle (SRP)

Single-responsibility Principle states that:

```
A class should have one and only one reason to change, meaning that a class should have only one job.
```

For example, let's say, we have some shapes and we wanted to sum all the areas of the shapes.
Well it is straight forward and pretty simple right?

```java
public class Circle {

    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
}

```
```java
public class Square {

    private double length;

    public Square(double length) {
        this.length = length;
    }

    public double getLength() {
        return length;
    }
}
```

First, we have created the shapes classes and have the constructors setup the required parameters.
Next, we move on by creating the AreaCalculator class and then write up our logic to sum up the areas of all provided 
shapes.

```java
public class AreaCalculator {
    private Object[] objects;

    public AreaCalculator(Object... objects) {
        this.objects = objects;
    }

    public double sum() {
        double sum = 0;
        for (Object shape : objects) {
            if (shape instanceof Circle) {
                double radius = ((Circle) shape).getRadius();
                sum += (3.14 * Math.pow(radius, 2));
            } else if (shape instanceof Square) {
                double length = ((Square) shape).getLength();
                sum += Math.pow(length, 2);
            }
        }

        return sum;
    }

    public void output() {
        System.out.println("Sum of the area of the shapes:" + sum());
    }
}
```

To use the ```AreaCalculator``` class, you simply instantiate the class and pass in an array of shapes, and display the 
output at the bottom of the page.

```java
public class TestAreaCalculator {
    public static void main(String[] args) {
        AreaCalculator areaCalculator = new AreaCalculator(new Circle(3), new Square(4));
        areaCalculator.output();
    }
}
```


The problem with the output method is that the ```AreaCalculator``` handles the logic to output the data.
Therefore, what if the user wanted to output the data as ```json``` or ```html``` or other format.

Currently, the ```AreaCalculator``` class handles all that logic and this is what ```SRP``` disapprove; 
the ```AreaCalculator``` class should only sum the areas of provided shapes, instead of taking care of whether the user 
wants the output in ```json``` or ```html``` or in any other format.

* Solution

    To fix this problem, you should create a separator class ```Renderer``` class and use this to handle whatever logic 
    you need to handle how the sum areas of all provided shapes are displayed.
    
The ```Renderer``` class should be like:

```java
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
```
```java
public class TestAreaCalculator {
    public static void main(String[] args) {
        AreaCalculator areaCalculator = new AreaCalculator(new Circle(3), new Square(4));
        Renderer renderer = new Renderer(areaCalculator.sum());
        renderer.renderConsole();
        renderer.renderJson();
        renderer.renderHtml();
    }
}
```

Now, the logic to output the data is handled by the ```Renderer``` class.


###2. Open-Closed Principle (OCP)

This principle states that:

```
Objects or entities should be open for extension, but closed for modification.
```

It means that a class should be easily extendable without modifying the class itself.

Let's take a look at the ```AreaCalculator``` class, especially it's sum method.

```
public double sum() {
    double sum = 0;
    for (Object shape : objects) {
        if (shape instanceof Circle) {
            double radius = ((Circle) shape).getRadius();
            sum += (3.14 * Math.pow(radius, 2));
        } else if (shape instanceof Square) {
            double length = ((Square) shape).getLength();
            sum += Math.pow(length, 2);
        }
    }

    return sum;
}
```

If we wanted the sum method to be able to sum the areas of more shapes, we would have to add more if/else blocks and 
that goes against the ```Open-Closed Principle```.

- Solution
    
    To make it better, just remove the logic to calculate the sum of the area of each shape out of the sum method and 
    attach it to the respective class like ```Shape``` and ```Circle```.
    

```java
public class Circle {

    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double area() {
        return (3.14 * Math.pow(radius, 2));
    }
}
```
```java
public class Square {

    private double length;

    public Square(double length) {
        this.length = length;
    }

    public double area() {
        return Math.pow(length, 2);
    }
}
```
After designing the classes as per the ```Open-Closed Principle```, to calculate the sum of any shape provided 
should be as simple as:

```
public double sum() {
    double sum = 0;
    for (Object shape : shapes) {
        // commented intentionally
        // sum += shape.area();
    }

    return sum;
}
```

- Problem

    Now another problem arises, how do we know that the object passed into the ```AreaCalculator``` is actually a 
    ```Square``` or ```Circle``` or if the ```square``` has a method named ```area``` or not?
    
- Solution
    
    Now we can create another ```Shape``` class/interface and pass it in when calculating the sum without breaking 
    our code. 
    
    Coding to an ```interface``` is an integral part of ```SOLID``` principles.


Now, we create an interface, that every shape must implement:
    
```java
public interface Shape {
    public double area();
}
```

```java
public class Circle implements Shape {

    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double area() {
        return (3.14 * Math.pow(radius, 2));
    }
}
```
```java
public class Square implements Shape {

    private double length;

    public Square(double length) {
        this.length = length;
    }

    public double area() {
        return Math.pow(length, 2);
    }
}
```
```java
public class Rectangle implements Shape {

    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double area() {
        return width * height;
    }
}
```

In our ```AreaCalculator``` class the ```sum``` method, we can easily check if the shapes provided are actually 
instances of the ```Shape``` interface, otherwise we can throw an exception, if needed:

```java
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
}
```
```java
public class AreaCalculator {
    private Object[] shapes;

    public AreaCalculator(Object[] shapes) {
        this.shapes = shapes;
    }

    public double sum() {
        double sum = 0;
        for (Object shape : shapes) {
            if(shape instanceof Shape){
                sum += shape.area();
            }
            
            throw new IllegalArgumentException("Invalid Object Type");
        }

        return sum;
    }
}
```

```java
public class TestAreaCalculator {
    public static void main(String[] args) {
        Shape[] shapes = {new Circle(3), new Square(4), new Rectangle(3, 4)};
        AreaCalculator areaCalculator = new AreaCalculator(shapes);
        System.out.println(areaCalculator.sum());
    }
}
```
  
###3. Liskov Substitution Principle (LSP)

This principle states that:

    Let q(x) be a property provable about objects of x of type T. Then q(y) should be provable for objects y of type S 
    where S is a subtype of T.
    
OR
    
    Functions that use pointers or references to base classes must be able to use objects of derived classes without 
    knowing it.


All it is stating that every subclass/derived class should be substitutable for their base/parent class.


Still making use of our ```AreaCalculator``` class, say we have a ```VolumeCalculator``` class that extends the 
```AreaCalculator``` class:

```java
public class VolumeCalculator extends AreaCalculator {

    public VolumeCalculator(Shape... shapes) {
        super(shapes);
    }

    public double sum() {
        //write logic to calculate the volume of the shapes.
        double sum = 0;
        for (Shape shape : shapes) {
            if (shape instanceof Circle) {
                double radius = ((Circle) shape).getRadius();
                // 4/3 × π × radius3.
                sum += (4 / 3 * 3.14 * Math.pow(radius, 3));
            }
        }

        return sum;
    }
}
```

Now, the output class will be like:

```java
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
```

```java
public class TestCalculator {
    public static void main(String[] args) {
        //area calculator
        AreaCalculator areaCalculator = new AreaCalculator(new Circle(2), new Square(3));
        Renderer renderer = new Renderer(areaCalculator);
        renderer.renderConsole();
        renderer.renderHtml();
        renderer.renderJson();

        //volume calculator
        VolumeCalculator volumeCalculator = new VolumeCalculator(new Circle(2), new Square(3));
        renderer = new Renderer(volumeCalculator);
        renderer.renderConsole();
        renderer.renderHtml();
        renderer.renderJson();
    }
}
```

###4. Interface Segregation Principle (ISP)

This principle states that:

    A client should never be forced to implement an interface that it doesn't use or clients shouldn't be forced to 
    depend on methods they do not use.

Continuing on our shapes example, we know that we also have solid shapes, so since we would also want to calculate the 
volume of the shape, we can add another contract to the ```Shape``` interface:

```java
public interface Shape {
    public double area();

    public double volume();
}
```

- Problem

Any shape we create must implement the volume method, but we know that squares are flat shapes and that they do not 
have volumes, so this interface would force the ```Square``` class to implement a method that it has no use of.

And the change, adding the new method to existing interface, breaks the ```Interface Segregation Principle``` principle.

- Solution

    Create an another interface named ```SolidShape``` that has the volume contract and solid shapes like cubes etc. 
    can implement it.

```java
public interface Shape {
    public double area();
}
```

```java
public interface SolidShape {
    public double volume();
}
```
OR
```java
public interface SolidShape extends Shape {
    public double volume();
}
```

I want that the circle should have volume too.

```java
public class Circle implements SolidShape {

    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public double area() {
        return (3.14 * Math.pow(radius, 2));
    }

    public double volume() {
        // 4/3 × π × radius3.
        return (4 / 3 * 3.14 * Math.pow(radius, 3));
    }
}
```

```java
public class Cube implements SolidShape {

    private double length;

    public Cube(double length) {
        this.length = length;
    }

    public double getLength() {
        return length;
    }

    /**
     * @return
     */
    @Override
    public double area() {
        return (6 * Math.pow(length, 2));
    }

    /**
     * @return
     */
    @Override
    public double volume() {
        return Math.pow(length, 3);
    }
}
```

```java
public class TestCalculator {
    public static void main(String[] args) {
        AreaCalculator areaCalculator = new AreaCalculator(new Circle(3), new Square(4));
        System.out.println(areaCalculator.sum());

        VolumeCalculator volumeCalculator = new VolumeCalculator(new Circle(3), new Cube(2));
        System.out.println(volumeCalculator.sum());
    }
}
```


###5. Dependency Inversion Principle (DIP)

The last principle of (SOLID) states that:

    Entities must depend on abstractions not on concretions. It states that the high level module must not depend on 
    the low level module, but they should depend on abstractions.


This principle allows for decoupling the objects.

```java
public interface Renderer {

    public void render();
}
```

```java
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
```

```java
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
```

```java
public interface Calculator {

    public double calculate();
}
```

```java
public class AreaCalculator implements Calculator {
    protected Shape[] shapes;

    public AreaCalculator(Shape... shapes) {
        this.shapes = shapes;
    }

    @Override
    public double calculate() {
        double sum = 0;
        for (Shape shape : shapes) {
            sum += shape.area();
        }

        return sum;
    }
}
```

```java
public class VolumeCalculator extends AreaCalculator implements Calculator {

    public VolumeCalculator(SolidShape... shapes) {
        super(shapes);
    }

    public double calculate() {
        //write logic to calculate the volume of the shapes.
        double sum = 0;
        for (Shape shape : shapes) {
            if (shape instanceof SolidShape) {
                sum += ((SolidShape) shape).volume();
            }
        }

        return sum;
    }
}
```

```java
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
```


- One more example and mostly everyone has used in their development:

```java
public class PasswordReminder {

    private MySqlConnection connection;

    public PasswordReminder(MySqlConnection connection) {
        this.connection = connection;
    }
}
```

```java
public class MySqlConnection {

}
```

- Problem

    The ```MySqlConnection``` is the low level module while the ```PasswordReminder``` is high level, but according to 
    the definition of the ```Dependency Inversion Principle```, the objects should link based on abstraction instead 
    of the concretions, that above snippet totally violates and the ```PasswordReminder``` class is being forced to 
    depend on the ```MySqlConnection``` class.

    Later if you were to change the database engine, you would also have to edit the ```PasswordReminder``` class.

- Solution

    The ```PasswordReminder``` class should not care what database your application uses, to fix this again we 
    "code to an interface", since high level and low level modules should depend on abstraction, we can create an 
    interface:

```java
public interface Connection {
    void connect();
}
```

```java
public class MySqlConnection implements Connection {

    public void connect() {

    }
}
```

```java
public class PasswordReminder {

    private Connection connection;

    public PasswordReminder(Connection connection) {
        this.connection = connection;
    }

}
```

## Authors

* [Rohtash Lakra](https://github.com/rslakra/AppSuite/tree/master/java/src/main/java/com/rslakra/solid)
