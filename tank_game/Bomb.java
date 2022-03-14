package tank_game;

import javax.swing.*;
import java.awt.*;

public class Bomb {
    private static Image[] bombEffects;
    private int x;
    private int y;
    private int life = 12;//炸弹的生命周期
    private boolean isLive = true;

    //居然用到了静态代码块,すごい
    //预加载炸弹效果图片
    static {
        bombEffects = new Image[6];
        String formatter = "/bomb%d.jpg";
        for (int i = 1; i <= 6; ++i) {
            Image image = Toolkit.getDefaultToolkit().getImage(
                    JPanel.class.getResource(String.format(formatter, i)));
            bombEffects[i - 1] = image;
        }
    }

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void lifeDown() {
        if (life > 0) {
            --life;
        } else {
            isLive = false;
        }
    }

    public Image getBombEffectImage() {
        return bombEffects[Math.max(life / 2 - 1, 0)];
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }
}
