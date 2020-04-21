public class Food {
    int x;
    int y;

    public void newFood() {
        this.x = (int) ((Math.random() * ((32 - 0) + 1)) + 0);
        this.y = (int) ((Math.random() * ((32 - 0) + 1)) + 0);
    }

    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
}
