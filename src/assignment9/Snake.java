package assignment9;

import java.util.LinkedList;

public class Snake {

	private static final double SEGMENT_SIZE = 0.02;
	private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
	private LinkedList<BodySegment> segments;
	private double deltaX;
	private double deltaY;
	
	
    public Snake() {
        segments = new LinkedList<>();
        segments.add(new BodySegment(0.5, 0.5, BodySegment.SEGMENT_SIZE)); // Start at center
        deltaX = MOVEMENT_SIZE; // Start moving right
        deltaY = 0;
    }

    public void changeDirection(int direction) {
        if (direction == 1 && deltaY == 0) { // up, and not going down
            deltaY = MOVEMENT_SIZE;
            deltaX = 0;
        } else if (direction == 2 && deltaY == 0) { // down, and not going up
            deltaY = -MOVEMENT_SIZE;
            deltaX = 0;
        } else if (direction == 3 && deltaX == 0) { // left, and not going right
            deltaY = 0;
            deltaX = -MOVEMENT_SIZE;
        } else if (direction == 4 && deltaX == 0) { // right, and not going left
            deltaY = 0;
            deltaX = MOVEMENT_SIZE;
        }
        // Ignore invalid directions (e.g., going back on itself)
    }

    /**
     * Moves the snake by updating the position of each of the segments
     * based on the current direction of travel
     */
    public void move() {
        double headX = segments.get(0).getX();
        double headY = segments.get(0).getY();

        // Calculate new head position
        double newHeadX = headX + deltaX;
        double newHeadY = headY + deltaY;

        // Create a new head segment
        BodySegment newHead = new BodySegment(newHeadX, newHeadY, BodySegment.SEGMENT_SIZE);
        segments.add(0, newHead); // Add to the front

        segments.remove(segments.size() - 1); // Remove the tail
    }

    /**
     * Draws the snake by drawing each segment
     */
    public void draw() {
        for (BodySegment segment : segments) {
            segment.draw();
        }
    }

    /**
     * The snake attempts to eat the given food, growing if it does so successfully
     *
     * @param f the food to be eaten
     * @return true if the snake successfully ate the food
     */
    public boolean eat(Food f) {
        double headX = segments.get(0).getX();
        double headY = segments.get(0).getY();

        if (Math.abs(headX - f.getX()) < BodySegment.SEGMENT_SIZE &&
                Math.abs(headY - f.getY()) < BodySegment.SEGMENT_SIZE) {
            grow(); // Grow the snake
            return true;
        }
        return false;
    }

    public void grow() {
        double tailX = segments.get(segments.size() - 1).getX();
        double tailY = segments.get(segments.size() - 1).getY();
        segments.add(new BodySegment(tailX, tailY, BodySegment.SEGMENT_SIZE)); // Add a new tail segment
    }

    /**
     * Returns true if the head of the snake is in bounds
     *
     * @return whether or not the head is in the bounds of the window
     */
    public boolean isInBounds() {
        double headX = segments.get(0).getX();
        double headY = segments.get(0).getY();
        return headX >= 0 && headX <= 1 && headY >= 0 && headY <= 1;
    }
}