import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

/**
 * This is a class
 * Created 2020-01-21
 *
 * @author Magnus Silverdal
 */
public class SnakeGame extends Canvas implements Runnable{
    private JFrame frame;
    private int fps = 60;
    private int ups = 2;
    private boolean running = false;
    private Thread thread;
    private static Food food = new Food();
    private static Head head = new Head();
    private static boolean titleScreen = false;
    private static boolean gameScreen = false;
    private static boolean gameOverScreen = false;

    public SnakeGame(int w, int h) {
        Dimension size = new Dimension(w * 50, h * 50);
        setPreferredSize(size);
        frame = new JFrame();
        frame.setTitle("Snake");
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

        g.setColor(Color.WHITE);
        g.fillRect(0,0,16*50,16*50);

        if (titleScreen) {
            char[] data = {'P','L','A','Y'};
            g.setColor(Color.red);
            g.fillRect(5*50,6*50,250,100);
            g.setColor(Color.BLACK);
            for (int i = 0; i < data.length; i++) {
                int x = 6 + i;
                g.drawChars(data, i,1,x*50,7*50);
            }
        }

        if (this.gameScreen) {
            g.setColor(Color.BLACK);
            for (int i = 0; i < 16; i++) {
                for (int w = 0; w < 16 ; w++) {
                    g.drawRect(w * 50,i * 50, 50, 50);
                }
            }
            g.setColor(Color.RED);
            g.fillRect(food.getX()*50,food.getY()*50,food.getWidth(),food.getHeight());
            g.setColor(Color.BLACK);
            g.fillRect(head.getX()*50, head.getY()*50, head.getWidth(), head.getHeight());
        }
        g.dispose();
        bs.show();
    }

    private void update() {
        if (this.gameScreen) {
            food.update();
            head.logic();
            collision();
        }
    }

    private void collision() {
        if (head.getX() == food.getX() && head.getY() == food.getY()) {
            food.setTaken();
        }
    }


    public static void main(String[] args) {
        titleScreen = true;
        SnakeGame game = new SnakeGame(16,16);
        game.frame.add(game);
        game.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar()=='a') {
                    head.setLeft();
                }
                if (e.getKeyChar()=='d') {
                    head.setRight();
                }
                if (e.getKeyChar()=='s') {
                    head.setDown();
                }
                if (e.getKeyChar()=='w') {
                    head.setUp();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) { }
        });
        game.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("clicked");
                double pointX = e.getPoint().getX();
                double pointY = e.getPoint().getY();

                if (pointX >= 5*50 && pointX <= 11*50 && pointY >= 6*50 && pointY <= 8*50) {
                    titleScreen = false;
                    gameScreen = true;
                    System.out.println("hello");
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("clicked");
                double pointX = e.getPoint().getX();
                double pointY = e.getPoint().getY();

                if (pointX >= 6*50 && pointX <= 11*50 && pointY >= 7*50 && pointY <= 8*50) {
                    titleScreen = false;
                    gameScreen = true;
                    System.out.println("hello");
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) { }
        });
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setFocusable(true);
        game.frame.requestFocusInWindow();
        game.frame.setVisible(true);
        game.head.setRight();
        game.start();
    }
}