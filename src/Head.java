public class Head {
    private int x = 8;
    private int y = 8;
    private int width = 50;
    private int height = 50;
    private int lastX;
    private int lastY;
    private String direction;

    public void logic() {
        System.out.println(direction);
        if (this.direction.equals("right")) {
            this.moveRight();
            System.out.println("right");
        }
        if (this.direction.equals("left")) {
            this.moveLeft();
            System.out.println("left");
        }
        if (this.direction.equals("up")) {
            this.moveUp();
            System.out.println("up");
        }
        if (this.direction.equals("down")) {
            this.moveDown();
            System.out.println("down");
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    public void moveLeft() {
        this.lastX = this.x;
        this.x -= 1;
    }

    public void moveRight() {
        this.lastX = this.x;
        this.x += 1;
    }

    public void moveUp() {
        this.lastY = this.y;
        this.y -= 1;
    }

    public void moveDown() {
        this.lastY = this.y;
        this.y += 1;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setRight() {
        this.direction = "right";
        System.out.println("setRight");
        System.out.println(direction);
    }

    public void setLeft() {
        this.direction = "left";
    }

    public void setUp() {
        this.direction = "up";
    }

    public void setDown() {
        this.direction = "down";
    }

    public void setNone() {
        this.direction = "none";
    }
}

