package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TriangleTest {

    @Test
    public void testTriangleIsCreatedSuccessfully() {
        Point pointA = new Point(0, 0);
        Point pointB = new Point(1, 1);
        Point pointC = new Point(1, 0);
        Triangle triangle = new Triangle(pointA, pointB, pointC);
        assertNotNull(triangle, "The triangle was successfully created and should not be null.");
    }

    @Test
    public void testCalculateTriangleArea() {
        Point pointA = new Point(0, 0);
        Point pointB = new Point(4, 0);
        Point pointC = new Point(0, 3);
        Triangle triangle = new Triangle(pointA, pointB, pointC);

        double expectedArea = 6.0;
        assertEquals(expectedArea, triangle.calculateArea(), 0.0001, "The triangle's area should be calculated correctly.");
    }

    @Test
    public void testCalculateTrianglePerimeter() {
        Point pointA = new Point(0, 0);
        Point pointB = new Point(3, 0);
        Point pointC = new Point(0, 4);
        Triangle triangle = new Triangle(pointA, pointB, pointC);

        double expectedPerimeter = 12.0;
        assertEquals(expectedPerimeter, triangle.calculatePerimeter(), 0.0001, "The triangle's perimeter should be calculated correctly.");
    }

    @Test
    public void testPointsCannotBeCollinear() {
        Point pointA = new Point(0, 0);
        Point pointB = new Point(1, 1);
        Point pointC = new Point(2, 2);
        assertThrows(IllegalArgumentException.class, () -> new Triangle(pointA, pointB, pointC), "Triangle cannot be collinear.");
    }

    @Test
    public void testPointsCannotBeIdentical() {
        Point pointA = new Point(0, 0);
        Point pointB = new Point(0, 0);
        Point pointC = new Point(1, 1);
        assertThrows(IllegalArgumentException.class, () -> new Triangle(pointA, pointB, pointC), "Points cannot be identical.");
    }
}
