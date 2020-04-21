public class Head {
    int x;
    int y;
    int lastX;
    int lastY;

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
}
