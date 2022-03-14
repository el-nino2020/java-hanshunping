import javax.swing.*;
import java.awt.*;

public class Test extends JFrame {
    MyPanel myPanel = new MyPanel();

    public static void main(String[] args) {
        new Test();
    }

    public Test() {
        this.add(myPanel);
        this.setSize(800, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class MyPanel extends JPanel {
    @Override
    public void paint(Graphics g) {

        super.paint(g);
        g.drawLine(60, 60, 230, 450);

        g.setColor(Color.GREEN);

        g.fillRect(20, 20, 400, 200);

        g.setColor(Color.RED);
        g.setFont(new Font("黑体", Font.ITALIC, 40));
        g.drawString("臭卷宝", 60, 150);

        //该图片的相对路径是/out/production/hello/9k=.jpg
        //hello是包名
        //注意，有些图片就是显示不出来，不知道为什么
        Image image = Toolkit.getDefaultToolkit().getImage(JPanel.class.getResource("/bomb1.jpg"));
        g.drawImage(image, 400, 20, 200, 200, this);
    }
}