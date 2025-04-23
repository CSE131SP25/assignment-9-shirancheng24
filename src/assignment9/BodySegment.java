package assignment9;

import java.awt.Color;

import edu.princeton.cs.introcs.StdDraw;

public class BodySegment {

    private double x, y, size;
    private Color color;
    public static final double SEGMENT_SIZE = 0.02;

    public BodySegment(double x, double y, double size) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = ColorUtils.solidColor();
    }

    public void draw() {
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(x, y, size);
    }

    public double getX() {  // Getter for x
        return x;
    }

    public double getY() {  // Getter for y
        return y;
    }
}