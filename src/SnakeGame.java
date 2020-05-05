import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

/**
 * This is a class
 * Created 2020-01-21
 *
 * @author Magnus Silverdal
 */
public class SnakeGame extends Canvas implements Runnable{
    private String title = "Snake";
    private JFrame frame;
    private int fps = 1;
    private int ups = 1;
    private boolean running = false;
    private Thread thread;
    Food food = new Food();
    Head head = new Head();

    public SnakeGame(int w, int h) {
        Dimension size = new Dimension(w * 50, h * 50);
        setPreferredSize(size);
        frame = new JFrame();
        frame.setTitle(title);
    }

    private synchronized void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        double frameUpdateInterval = 1000000000.0 / fps;
        double stateUpdateInterval = 1000000000.0 / ups;
        double deltaFrame = 0;
        double deltaUpdate = 0;
        long lastTime = System.nanoTime();

        while (running) {
            long now = System.nanoTime();
            deltaFrame += (now - lastTime) / frameUpdateInterval;
            deltaUpdate += (now - lastTime) / stateUpdateInterval;
            lastTime = now;

            while (deltaUpdate >= 1) {
                update();
                deltaUpdate--;
            }

            while (deltaFrame >= 1) {
                draw();
                deltaFrame--;
            }
        }
        stop();
    }

    private void draw() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        for (int i = 0; i < 16; i++) {
            for (int w = 0; w < 16 ; w++) {
                g.drawRect(w * 50,i * 50, 50, 50);
            }
        }
        g.setColor(Color.RED);
        g.fillRect(food.getX()*50,food.getY()*50,food.getWidth(),food.getHeight());
        g.setColor(Color.BLACK);
        g.fillRect(head.getX()*50, head.getY()*50, head.getWidth(), head.getHeight());
        g.dispose();
        bs.show();
    }

    private void update() {
        food.update();
        head.logic();
    }

    public static void main(String[] args) {
        SnakeGame game = new SnakeGame(16,16);
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);
        game.start();
    }
}