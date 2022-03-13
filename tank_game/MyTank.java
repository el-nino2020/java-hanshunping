package tank_game;

public class MyTank extends Tank {
    private Bullet bullet;

    public MyTank(int x, int y) {
        super(x, y);
    }

    /**
     * 创建Bullet对象，使其开始移动
     */
    public void shot() {
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
        new Thread(bullet).start();
    }

    public Bullet getBullet() {
        return bullet;
    }

    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }
}
