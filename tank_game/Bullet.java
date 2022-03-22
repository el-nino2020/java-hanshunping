package tank_game;

public class Bullet implements Runnable {
    private int x;
    private int y;
    private int direction;
    private int speed = 10;
    private boolean isLive = true;

    public Bullet(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    /**
     * 该方法被调用后，
     * 子弹被发射，其行为如下：
     * 从(x, y)点开始，朝着direction，以speed移动。直至与边界碰撞
     */
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.out.println("sleep() in Bullet.run() is interrupted");
            }

            switch (direction) {
                case 0://up
                    y -= speed;
                    break;
                case 1://down
                    y += speed;
                    break;
                case 2://left
                    x -= speed;
                    break;
                case 3://right
                    x += speed;
                    break;
            }
            int hashCode = this.hashCode();
            //与边界碰撞
            if (!(0 <= x && x < MyPanel.backgroundWidth && 0 <= y && y < MyPanel.backgroundHeight && isLive)) {
                isLive = false;
                //System.out.println("子弹@" + hashCode + "消亡");
                break;
            }
            //显示子弹信息，用于测试
            //System.out.println("子弹@" + hashCode + ": (" + x + ", " + y + ")");
        }
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

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }
}
