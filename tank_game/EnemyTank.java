package tank_game;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable {

    Vector<Bullet> bullets = new Vector<>();

    public EnemyTank(int x, int y) {
        super(x, y);
    }


    @Override
    public void run() {
        try {
            while (isLive()) {
                int pace = (int) (Math.random() * 20);
                switch (getDirection()) {
                    case 0:
                        for (int i = 0; i < pace; ++i) {
                            moveUp();
                            Thread.sleep(50);
                        }
                        break;
                    case 1:
                        for (int i = 0; i < pace; ++i) {
                            moveDown();
                            Thread.sleep(50);
                        }
                        break;
                    case 2:
                        for (int i = 0; i < pace; ++i) {
                            moveLeft();
                            Thread.sleep(50);
                        }
                        break;
                    case 3:
                        for (int i = 0; i < pace; ++i) {
                            moveRight();
                            Thread.sleep(50);
                        }
                        break;

                }
                setDirection((int) (Math.random() * 4));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
