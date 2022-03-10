package tank_game;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
    MyTank mt = null;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //paint background to black
        g.fillRect(0, 0, 1000, 750);
        drawTank(mt.getX(), mt.getY(), g, 0, 0);
        drawTank(500, 450, g, 0, 1);
    }

    public MyPanel() {
        mt = new MyTank(100, 100);
    }

    /**
     * @param x         坦克横坐标
     * @param y         坦克纵坐标
     * @param g         画笔
     * @param direction 坦克朝向
     * @param type      敌方或是自己
     */
    public void drawTank(int x, int y, Graphics g, int direction, int type) {
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
        }
    }

}
