package tank_game;

import javax.swing.*;

public class TankGame01 extends JFrame {
    private MyPanel mp ;

    public static void main(String[] args) {
        new TankGame01();
    }

    public TankGame01() {
        mp = new MyPanel();
        new Thread(mp).start();

        this.add(mp);
        this.addKeyListener(mp);
        this.setSize(1000, 750);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
