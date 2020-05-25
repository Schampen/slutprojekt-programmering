public class Food {
    int x;
    int y;
    int width = 50;
    int height = 50;
    boolean taken;

    public Food() {
        this.x = (int) ((Math.random() * ((15 - 0) + 1)) + 0);
        this.y = (int) ((Math.random() * ((15 - 0) + 1)) + 0);
        this.taken = false;
    }

    public void newFood() {
        this.x = (int) ((Math.random() * ((15 - 0) + 1)) + 0);
        this.y = (int) ((Math.random() * ((15 - 0) + 1)) + 0);
        this.taken = false;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setTaken() {
        this.taken = true;
    }

    public void update() {
        if (this.taken) {
            this.newFood();
        }
    }
}
