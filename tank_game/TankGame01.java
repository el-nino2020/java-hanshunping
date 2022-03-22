package tank_game;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankGame01 extends JFrame {
    public static final int frameWidth = 1100;
    public static final int frameHeight = 750;
    private MyPanel mp;

    public static void main(String[] args) {
        new TankGame01();
    }

    public TankGame01() {
        mp = new MyPanel();
        new Thread(mp).start();

        this.add(mp);
        this.addKeyListener(mp);
        this.setSize(frameWidth, frameHeight);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //关闭程序窗口时触发该方法
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.writeInfo();
            }
        });
    }
}
