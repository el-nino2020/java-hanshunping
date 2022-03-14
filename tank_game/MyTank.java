package tank_game;

import java.util.Vector;

public class MyTank extends Tank {

    Vector<Bullet> bullets = new Vector<>();
    private long lastShotTime = 0;//上一次子弹发射的时间
    private long shotPeriod = 100;//两次子弹发射间隔，单位是毫秒

    public MyTank(int x, int y) {
        super(x, y);
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

}
