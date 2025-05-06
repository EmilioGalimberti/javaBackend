package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.awt.*;

public class RectangleTest {
    @Test
    public void testRectangleIsCreatedSuccessfully() {
        Point pointA = new Point(0, 0);
        Point pointB = new Point(4, 0);
        Point pointC = new Point(4, 3);
        Point pointD = new Point(0, 3);
        Rectangle rectangle = new Rectangle(pointA, pointB, pointC, pointD);
        assertNotNull(rectangle, "The rectangle was successfully created and should not be null.");
    }

    @Test
    public void testPointsFormValidRectangle() {
        Point pointA = new Point(0, 0);
        Point pointB = new Point(4, 0);
        Point pointC = new Point(3, 3);
        Point pointD = new Point(0, 3);
        assertThrows(IllegalArgumentException.class,
                () -> new Rectangle(pointA, pointB, pointC, pointD),
                "The provided points do not form a valid rectangle.");
    }

    @Test
    public void testCalculateRectangleArea() {
        Point pointA = new Point(0, 0);
        Point pointB = new Point(4, 0);
        Point pointC = new Point(4, 3);
        Point pointD = new Point(0, 3);
        Rectangle rectangle = new Rectangle(pointA, pointB, pointC, pointD);

        double expectedArea = 12.0;
        assertEquals(expectedArea, rectangle.calculateArea(), 0.0001, "The rectangle's area should be calculated correctly.");
    }

    @Test
    public void testCalculateRectanglePerimeter() {
        Point pointA = new Point(0, 0);
        Point pointB = new Point(4, 0);
        Point pointC = new Point(4, 3);
        Point pointD = new Point(0, 3);
        Rectangle rectangle = new Rectangle(pointA, pointB, pointC, pointD);

        double expectedPerimeter = 14.0;
        assertEquals(expectedPerimeter, rectangle.calculatePerimeter(), 0.0001, "The rectangle's perimeter should be calculated correctly.");
    }


    @Test
    public void testConstructorThrowsErrorForIdenticalPoints() {
        Point pointA = new Point(0, 0);
        Point pointB = new Point(0, 0); // Identical to pointA
        Point pointC = new Point(4, 3);
        Point pointD = new Point(0, 3);

        assertThrows(IllegalArgumentException.class,
                () -> new Rectangle(pointA, pointB, pointC, pointD),
                "Constructor should throw an error when any two points are identical.");
    }
}
