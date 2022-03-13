package tank_game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener, Runnable {
    private MyTank mt;
    private Vector<EnemyTank> enemyTanks = new Vector<>();

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //paint background to black
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1000, 750);

        //画己方坦克
        drawTank(mt.getX(), mt.getY(), g, mt.getDirection(), 0);
        //画己方子弹
        if (mt.getBullet() != null && mt.getBullet().isLive()) {
            g.setColor(Color.WHITE);
            g.fillRect(mt.getBullet().getX(), mt.getBullet().getY(), 2, 2);
        }

        for (EnemyTank e : enemyTanks) {
            //画敌方坦克
            drawTank(e.getX(), e.getY(), g, e.getDirection(), 1);
            //画敌方子弹
            for (int i = 0; i < e.bullets.size(); i++) {
                Bullet bullet = e.bullets.get(i);
                if (!bullet.isLive()) {
                    e.bullets.remove(i);
                    --i;//不能跳过原先的第(i+1)个元素
                    continue;
                }
                g.setColor(Color.WHITE);
                g.fillRect(bullet.getX(), bullet.getY(), 2, 2);
            }

        }


    }

    public MyPanel() {
        mt = new MyTank(100, 100);
        //mt.setSpeed(1);


        for (int i = 0; i < 3; i++) {
            int x = 100 * (i + 1), y = 0;
            EnemyTank enemyTank = new EnemyTank(x, y);//创建敌方坦克
            enemyTank.setDirection(1);

            //每个坦克初始化一颗子弹
            enemyTank.bullets.add(new Bullet(x + 20, y + 60, 1));
            new Thread(enemyTank.bullets.get(0)).start();

            enemyTanks.add(enemyTank);
        }
    }

    /**
     * @param x         坦克横坐标
     * @param y         坦克纵坐标
     * @param g         画笔
     * @param direction 坦克朝向
     * @param type      敌方或是自己
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


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
            mt.setDirection(0);
            mt.moveUp();
        } else if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
            mt.setDirection(1);
            mt.moveDown();
        } else if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
            mt.setDirection(2);
            mt.moveLeft();
        } else if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
            mt.setDirection(3);
            mt.moveRight();
        }

        if (keyCode == KeyEvent.VK_J) {
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
            repaint();
        }
    }
}
