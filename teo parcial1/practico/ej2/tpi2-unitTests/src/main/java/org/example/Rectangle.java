package org.example;

public class Rectangle {
    private Point a;
    private Point b;
    private Point c;
    private Point d;

    public Rectangle(Point a, Point b, Point c, Point d) {
        if (!isRectangle(a, b, c, d)) {
            throw new IllegalArgumentException("Los puntos no forman un rectángulo válido.");
        }
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    private boolean isRectangle(Point a, Point b, Point c, Point d) {
        // Verificamos que los cuatro ángulos sean rectos usando el producto escalar
        return isRightAngle(a, b, c) &&
                isRightAngle(b, c, d) &&
                isRightAngle(c, d, a) &&
                isRightAngle(d, a, b);
    }

    private boolean isRightAngle(Point p1, Point p2, Point p3) {
        int[] v1 = {p1.getX() - p2.getX(), p1.getY() - p2.getY()};
        int[] v2 = {p3.getX() - p2.getX(), p3.getY() - p2.getY()};
        int dotProduct = v1[0] * v2[0] + v1[1] * v2[1];
        return dotProduct == 0;
    }

    public double calculateArea() {
        double base = a.distancia(b);
        double altura = a.distancia(d);
        return base * altura;
    }


    public double calculatePerimeter() {
        double base = a.distancia(b);
        double altura = a.distancia(d);
        return 2 * (base + altura);
    }

    public Point getA() { return a; }
    public Point getB() { return b; }
    public Point getC() { return c; }
    public Point getD() { return d; }
    }
