package model;

import javafx.application.Platform;

/**
 *
 * @author Uellington Conceição
 * @since 09/05/2020
 * @version 0.1
 */
public class GameLoop implements Runnable {

    private final double AMOUNT_OF_TICKS = 60.0;
    private final double INTERVAL = 1000000000 / AMOUNT_OF_TICKS;
    private Thread thread;
    private boolean isRunning;

    private Game game;

    public GameLoop(Game game) {
        this.isRunning = false;
        this.thread = new Thread(this);
        this.game = game;
    }

    public synchronized void start() {
        this.thread.start();
    }

    public synchronized void stop() {
        this.isRunning = false;
    }

    @Override
    public void run() {
        this.isRunning = true;

        double delta = 0;
        long lastTime = System.nanoTime();

        long now;

        while (isRunning) {
            now = System.nanoTime();
            delta += (now - lastTime) / INTERVAL;
            lastTime = now;
            if (delta >= 1) {
                Platform.runLater(() -> {
                    this.game.clear();
                    this.game.update();
                });
                delta--;
            }
        }
    }

}
