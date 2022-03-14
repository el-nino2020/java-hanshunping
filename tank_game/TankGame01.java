package tank_game;

import javax.swing.*;

public class TankGame01 extends JFrame {
    public static int frameWidth = 1000;
    public static int frameHeight = 750;
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
    }
}
