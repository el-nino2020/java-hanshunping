package tank_game;

import javax.swing.*;

public class TankGame01 extends JFrame {
    MyPanel my = null;

    public static void main(String[] args) {
        new TankGame01();
    }

    public TankGame01() {
        my = new MyPanel();
        this.add(my);
        this.setSize(1000, 750);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
