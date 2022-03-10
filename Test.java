import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Test extends JFrame {
    private MyPanel mp = null;

    public static void main(String[] args) {
        new Test();
    }

    Test() {
        mp = new MyPanel();
        this.add(mp);
        this.addKeyListener(mp); //使JFrame对象可以监听mp面板上发生的键盘事件
        this.setSize(200, 200);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

//KeyListener是监听器，用于监听键盘事件
class MyPanel extends JPanel implements KeyListener {
    private int x = 10;
    private int y = 10;


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval(x, y, 20, 20);
    }

    //字符输出时触发该方法
    @Override
    public void keyTyped(KeyEvent e) {

    }

    //按下按键时触发该方法
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            //边界检查
            if (y < 200 - 10)//面板的宽度
                ++y;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (y > 0)
                --y;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (x > 0)
                --x;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (x < 200 - 10)//面板的长度
                ++x;
        }
        repaint();//用于再次执行paint()方法，更新球的位置

    }

    //松开按键时触发该方法
    @Override
    public void keyReleased(KeyEvent e) {

    }
}