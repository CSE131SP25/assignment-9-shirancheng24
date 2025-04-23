package assignment9;

import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class Game {

    private Snake snake;
    private Food food;
    private static final int DELAY = 50;
    private int score; // Add a score variable

    public Game() {
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);

        snake = new Snake();
        food = new Food();
        score = 0; // Initialize the score
    }

    public void play() {
        while (snake.isInBounds()) {
            int dir = getKeypress();
            snake.changeDirection(dir);
            snake.move();

            if (snake.eat(food)) {
                snake.grow();
                food = new Food();
                score++; // Increment the score
            }

            updateDrawing();
            StdDraw.pause(DELAY);
        }
        // Game over sequence (can be expanded)
        StdDraw.clear(); // Clear the screen
        StdDraw.text(0.5, 0.5, "Game Over! Score: " + score); // Display final score
        StdDraw.show();
        StdDraw.pause(2000); // Pause for 2 seconds before closing
    }

    private int getKeypress() {
    	if (StdDraw.isKeyPressed(KeyEvent.VK_W)) {
            return 1; // Up
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
            return 2; // Down
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
            return 3; // Left
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
            return 4; // Right
        } else {
            return -1; // No key pressed
        }
    }
    

    private void updateDrawing() {
        StdDraw.clear();
        snake.draw();
        food.draw();

        // Display the score
        StdDraw.setPenColor(ColorUtils.solidColor()); // Choose a color for the text
        StdDraw.text(0.1, 0.9, "Score: " + score); // Position the text
        StdDraw.show();
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.play();
    }
}