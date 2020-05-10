package model;

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
        double timer = System.currentTimeMillis();
        long lastTime = System.nanoTime();
        
        long now = 0;
        int frames = 0;

        while (isRunning) {
            now = System.nanoTime();
            delta += (now - lastTime) / INTERVAL;
            lastTime = now;
            if (delta >= 1) {
                this.game.clear();
                this.game.update();
                frames++;
                delta--;
            }

            if (System.currentTimeMillis() - timer >= 1000) {
                System.out.println("FPS: " + frames);
                frames = 0;
                timer += 1000;
            }
        }
    }

}
