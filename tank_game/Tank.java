package tank_game;

import java.util.Vector;

public class Tank {
    //left up point (x, y) of the tank
    private int x;
    private int y;

    private int direction;//0 up, 1 down, 2 left, 3 right
    private int speed = 2;//speed of the tank

    private boolean isLive = true;

    Vector<Bullet> bullets = new Vector<>();
    private long lastShotTime = 0;//上一次子弹发射的时间
    private long shotPeriod = 1000;//两次子弹发射间隔，单位是毫秒


    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 创建Bullet对象，使其开始移动
     */
    public void shot() {
        long l = System.currentTimeMillis();
        if (l - lastShotTime < shotPeriod) {
            return;
        }
        lastShotTime = l;
        Bullet bullet = null;
        switch (getDirection()) {
            case 0:
                bullet = new Bullet(getX() + 20, getY(), 0);
                break;
            case 1:
                bullet = new Bullet(getX() + 20, getY() + 60, 1);
                break;
            case 2:
                bullet = new Bullet(getX(), getY() + 20, 2);
                break;
            case 3:
                bullet = new Bullet(getX() + 60, getY() + 20, 3);
                break;
        }
        bullets.add(bullet);

        new Thread(bullet).start();
    }

    public void moveUp() {
        y -= speed;
    }

    public void moveDown() {
        y += speed;
    }

    public void moveLeft() {
        x -= speed;
    }

    public void moveRight() {
        x += speed;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
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

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public void setShotPeriod(long shotPeriod) {
        this.shotPeriod = shotPeriod;
    }

}
