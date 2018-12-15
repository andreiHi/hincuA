package ru.job4j.multithreading.pingpong;

import javafx.scene.shape.Rectangle;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 30.11.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class RectangleMove implements Runnable {
    private final Rectangle rect;

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        boolean moveStraight = true;
        int delta = 1;
        while (!Thread.currentThread().isInterrupted()) {
            if (this.rect.getX() == 290) {
                moveStraight = false;
            }
            if (this.rect.getX() == 0) {
                moveStraight = true;
            }
            if (moveStraight) {
                this.rect.setX(this.rect.getX() + delta);
            } else {
                this.rect.setX(this.rect.getX() - delta);
            }
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}
