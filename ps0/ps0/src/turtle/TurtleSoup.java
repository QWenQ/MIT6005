/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package turtle;

import java.util.List;
import java.util.ArrayList;
import java.lang.Math;

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
        final int TURN_AROUNDS = 4;
        final double STEERING_ANGLE = 90.0;
        for (int i = 0; i < TURN_AROUNDS; ++i) {
            turtle.forward(sideLength);
            turtle.turn(STEERING_ANGLE);
        }
        // throw new RuntimeException("implement me!");
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
        // throw new RuntimeException("implement me!");
        final double STRAIGHT_ANGLE = 180.0;
        final double ROUND_ANGLE = 360.0;
        double angle = STRAIGHT_ANGLE - ROUND_ANGLE / sides;
        return angle;
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
        // throw new RuntimeException("implement me!");
        final double STRAIGHT_ANGLE = 180.0;
        final double ROUND_ANGLE = 360.0;
        int sides = (int) Math.round(ROUND_ANGLE / (STRAIGHT_ANGLE - angle));
        return sides;
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
        // throw new RuntimeException("implement me!");
        final double STRAIGHT_ANGLE = 180.0;
        double angle = TurtleSoup.calculateRegularPolygonAngle(sides);
        for (int i = 0; i < sides; ++i) {
            turtle.forward(sideLength);
            turtle.turn(STRAIGHT_ANGLE - angle);
        }
        
    }

    /**
     * Given the current direction, current location, and a target location, calculate the heading
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentHeading. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentHeading current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to heading (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateHeadingToPoint(double currentHeading, int currentX, int currentY,
                                                 int targetX, int targetY) {
//        throw new RuntimeException("implement me!");
        
        final double RIGHT_ANGLE = 90.0;
        final double STRAIGHT_ANGLE = 180.0;
        final double ROUND_ANGLE = 360.0;
        
        double theta = Math.atan2(targetY - currentY, targetX - currentX);
        
        
        double angle = Math.toDegrees(theta);
        
        if (angle >= 0.0 && angle <= RIGHT_ANGLE) {
            angle = RIGHT_ANGLE - angle;
        }
        else if (angle > RIGHT_ANGLE && angle <= STRAIGHT_ANGLE) {
            angle = STRAIGHT_ANGLE + angle;
        }
        else {
            angle = Math.abs(angle) + RIGHT_ANGLE;
        }
        
        double ans = angle - currentHeading;
        if (ans < 0) {
            ans += ROUND_ANGLE;
        }
        
        return ans;
    }

    /**
     * Given a sequence of points, calculate the heading adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateHeadingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of heading adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateHeadings(List<Integer> xCoords, List<Integer> yCoords) {
//        throw new RuntimeException("implement me!");
        List<Double> adjustments = new ArrayList<Double>(xCoords.size() - 1);
        
        double heading = 0.0;
        
        for (int i = 0; i < xCoords.size() - 1; ++i) {
            double newHeading = TurtleSoup.calculateHeadingToPoint(heading, xCoords.get(i), yCoords.get(i), xCoords.get(i + 1), yCoords.get(i + 1));
            adjustments.add(i, newHeading);
            heading = newHeading;
        }
        return adjustments;
    }

    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
//        throw new RuntimeException("implement me!");
        final int LINES = 450;
        final double STEERING_ANGLE = 90.5;
        final int COLOR_TYPES = 4;
        int sideLength = 1;
        int stride = 1;
        
        for (int i = 0; i < LINES; ++i) {
            int remain = i % COLOR_TYPES;
            if (remain == 0) {
                turtle.color(PenColor.BLUE);
            }
            else if (remain == 1) {
                turtle.color(PenColor.GRAY);
            }
            else if (remain == 2) {
                turtle.color(PenColor.RED);
            }
            else {
                turtle.color(PenColor.MAGENTA);
            }
            turtle.forward(sideLength);
            turtle.turn(STEERING_ANGLE);
            sideLength += stride;
        }
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();
        
        TurtleSoup.drawPersonalArt(turtle);
        
        // draw the window
        turtle.draw();
    }

}
