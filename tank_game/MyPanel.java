package tank_game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener, Runnable {

    private MyTank mt;
    private Vector<EnemyTank> enemyTanks = new Vector<>();
    private Vector<Bomb> bombs = new Vector<>();
    private int enemyNum = 4;

    //panel和frame的大小如果设置相等，实际上前者会更大，导致坦克可以运动出边界
    //因此需要人为调小一点——在别的显示器上也许没有问题
    public static final int backgroundWidth = 800;
    public static final int backgroundHeight = TankGame01.frameHeight - 40;

    public MyPanel() {
        mt = new MyTank(500, 500);

        for (int i = 0; i < enemyNum; i++) {
            int x = (int) (Math.random() * backgroundWidth),
                    y = (int) (Math.random() * (backgroundHeight / 3));
            EnemyTank enemyTank = new EnemyTank(x, y);//创建敌方坦克
            enemyTank.setDirection(1);
            enemyTank.setEnemyTanks(enemyTanks);
            enemyTanks.add(enemyTank);

            new Thread(enemyTank).start();

            Recorder.setEnemyTanks(enemyTanks);
        }


        //执行Bomb类信息的预加载，不然第一次发生爆炸效果可能不成功
        new Bomb(0, 0);
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //paint background to black
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, backgroundWidth, backgroundHeight);

        //画己方坦克
        if (mt.isLive()) {
            drawTank(mt.getX(), mt.getY(), g, mt.getDirection(), 0);
        }
        //画己方子弹
        drawBullets(g, mt.bullets);

        for (int j = 0; j < enemyTanks.size(); ++j) {
            EnemyTank e = enemyTanks.get(j);
            //画敌方坦克
            if (e.isLive()) {
                drawTank(e.getX(), e.getY(), g, e.getDirection(), 1);
            }
            //坦克没了，它的留下的子弹也没了，这个坦克对象才彻底没用
            if (!e.isLive() && e.bullets.isEmpty()) {
                enemyTanks.remove(j--);
                continue;
            }
            //画敌方子弹，
            // 坦克死了，子弹并不会立刻消失
            drawBullets(g, e.bullets);

        }

        drawBomb(g);
        drawInfo(g);

    }

    private void drawInfo(Graphics g) {
        g.setColor(Color.black);
        Font font = new Font("黑体", Font.BOLD, 25);
        g.setFont(font);
        g.drawString("击毁的敌方坦克总数：", backgroundWidth + 20, 50);
        g.drawString(Recorder.getTotalKilledEnemy() + "", backgroundWidth + 100, 100);

        drawTank(backgroundWidth + 20, 70, g, 0, 1);

    }

    /**
     * @param bullets 某个坦克拥有的子弹集合;
     *                画出该子弹集合中的所有子弹，
     *                并删除生命周期消亡的子弹
     */
    private void drawBullets(Graphics g, Vector<Bullet> bullets) {
        for (int i = 0; i < bullets.size(); ++i) {
            Bullet bullet = bullets.get(i);
            if (bullet.isLive()) {
                g.setColor(Color.WHITE);
                g.fillRect(bullet.getX(), bullet.getY(), 2, 2);
            } else {
                bullets.remove(i--);
            }
        }
    }

    /**
     * 画出屏幕上应该有的所有炸弹，
     * 并删除生命周期结束的炸弹
     */
    private void drawBomb(Graphics g) {
        //画炸弹
        for (int i = 0; i < bombs.size(); ++i) {
            Bomb bomb = bombs.get(i);
            if (!bomb.isLive()) {
                bombs.remove(i--);
                continue;
            }
            Image image = bomb.getBombEffectImage();
            bomb.lifeDown();
            g.drawImage(image, bomb.getX(), bomb.getY(),
                    60, 60, this);
        }
    }

    /**
     * @param x         坦克横坐标
     * @param y         坦克纵坐标
     * @param g         画笔
     * @param direction 坦克朝向
     * @param type      敌方: 1; 自己: 0
     */
    private void drawTank(int x, int y, Graphics g, int direction, int type) {
        switch (type) {
            case 0://self
                g.setColor(Color.CYAN);
                break;
            case 1://enemy
                g.setColor(Color.ORANGE);
                break;
        }

        switch (direction) {
            case 0://up
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y, x + 20, y + 30);
                break;
            case 1://down
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 60, x + 20, y + 30);
                break;
            case 2://left
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x, y + 20);
                break;
            case 3://right
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x + 60, y + 20);
                break;
        }
    }

    /**
     * 子弹是否击中某台坦克
     * 子弹与坦克应该属于不同阵营
     */
    //由于不知道某颗子弹何时会击中某辆坦克，应放在run方法中判断
    private void hitTank(Bullet bullet, Tank tank) {

        if (!tank.isLive() || !bullet.isLive()) {
            return;
        }
        switch (tank.getDirection()) {
            case 0:
            case 1:
                if (tank.getX() < bullet.getX() && bullet.getX() < tank.getX() + 40
                        && tank.getY() < bullet.getY() && bullet.getY() < tank.getY() + 60) {
                    tank.setLive(false);
                    bullet.setLive(false);
                    bombs.add(new Bomb(tank.getX(), tank.getY()));
                    if (tank instanceof EnemyTank) {
                        Recorder.anEnemyKilled();
                    }
                }
                break;
            case 2:
            case 3:
                if (tank.getX() < bullet.getX() && bullet.getX() < tank.getX() + 60
                        && tank.getY() < bullet.getY() && bullet.getY() < tank.getY() + 40) {
                    tank.setLive(false);
                    bullet.setLive(false);
                    bombs.add(new Bomb(tank.getX(), tank.getY()));
                    if (tank instanceof EnemyTank) {
                        Recorder.anEnemyKilled();
                    }
                }
                break;

        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (!mt.isLive())
            return;

        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
            mt.setDirection(0);
            if (mt.getY() > 0) {
                mt.moveUp();
            }
        } else if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
            mt.setDirection(1);
            if (mt.getY() + 60 < backgroundHeight) {
                mt.moveDown();
            }
        } else if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
            mt.setDirection(2);
            if (mt.getX() > 0) {
                mt.moveLeft();

            }
        } else if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
            mt.setDirection(3);

            if (mt.getX() + 60 < backgroundWidth) {
                mt.moveRight();
            }
        } else if (keyCode == KeyEvent.VK_J) {
            mt.shot();
        }

        repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //己方子弹击中敌方坦克
            for (Bullet bullet : mt.bullets) {
                if (bullet.isLive()) {
                    for (int i = 0; i < enemyTanks.size(); ++i) {
                        EnemyTank enemyTank = enemyTanks.get(i);
                        hitTank(bullet, enemyTank);
                    }
                }
            }

            //不是我不想用迭代器循环或者增强for，
            //使用这两种循环方式，会抛出ConcurrentModificationException异常
            //详细原理看这个链接：https://www.cnblogs.com/dolphin0520/p/3933551.html
            //解决的方法，就是用普通的for循环，哎。。。。
            //敌方子弹击中己方坦克
            for (int i = 0; i < enemyTanks.size(); ++i) {
                EnemyTank enemyTank = enemyTanks.get(i);
                for (int j = 0; j < enemyTank.bullets.size(); ++j) {
                    hitTank(enemyTank.bullets.get(j), mt);
                }
            }


            repaint();
        }
    }
}
