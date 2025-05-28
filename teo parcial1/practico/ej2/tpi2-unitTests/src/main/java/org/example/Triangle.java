package org.example;

public class Triangle {
    private Point a;
    private Point b;
    private Point c;

    public Triangle(Point a, Point b, Point c) {
        if (a.equals(b) || a.equals(c) || b.equals(c)) {
            throw new IllegalArgumentException("Points cannot be identical.");
        }
        if (areCollinear(a, b, c)) {
            throw new IllegalArgumentException("Triangle cannot be collinear.");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    private boolean areCollinear(Point p1, Point p2, Point p3) {
        // Usamos el determinante de la matriz para comprobar colinealidad
        return (p2.getX() - p1.getX()) * (p3.getY() - p1.getY()) ==
                (p3.getX() - p1.getX()) * (p2.getY() - p1.getY());
    }

    public double calculateArea() {
        // Fórmula del área usando determinantes (Shoelace formula)
        return 0.5 * Math.abs(
                a.getX() * (b.getY() - c.getY()) +
                        b.getX() * (c.getY() - a.getY()) +
                        c.getX() * (a.getY() - b.getY())
        );
    }

    public double calculatePerimeter() {
        return a.distancia(b) + b.distancia(c) + c.distancia(a);
    }

    public Point getA() { return a; }
    public Point getB() { return b; }
    public Point getC() { return c; }
}
