public class Head {
    private int x = 8;
    private int y = 8;
    private int width = 50;
    private int height = 50;
    private int lastX;
    private int lastY;
    private boolean right = false;
    private boolean left = true;
    private boolean up = false;
    private boolean down = false;

    public void logic() {
        if (this.right) {
            this.moveRight();
        }
        if (this.left) {
            this.moveLeft();
        }
        if (this.up) {
            this.moveUp();
        }
        if (this.down) {
            this.moveDown();
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
        this.left = false;
        this.right = true;
        this.up = false;
        this.down = false;
    }

    public void setLeft() {
        this.left = true;
        this.right = false;
        this.up = false;
        this.down = false;
    }

    public void setUp() {
        this.left = false;
        this.right = false;
        this.up = false;
        this.down = true;
    }

    public void setDown() {
        this.left = false;
        this.right = false;
        this.up = true;
        this.down = false;
    }
}

