package tank_game;

public class Tank {
    private int x;
    private int y;
    //0 up, 1 down, 2 left, 3 right
    private int direction;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
