package tank_game;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable {
    private Vector<EnemyTank> enemyTanks = new Vector<>();

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    @Override
    public void run() {
        try {
            while (isLive()) {
                int pace = (int) (Math.random() * 40);
                switch (getDirection()) {
                    case 0:
                        for (int i = 0; i < pace; ++i) {
                            if (getY() > 0 && !collideOtherTank()) {
                                moveUp();
                            } else {
                                break;
                            }
                            Thread.sleep(50);
                            shot();
                        }
                        break;
                    case 1:
                        for (int i = 0; i < pace; ++i) {
                            if (getY() + 60 < MyPanel.backgroundHeight
                                    && !collideOtherTank()) {
                                moveDown();
                            } else {
                                break;
                            }
                            Thread.sleep(50);
                            shot();
                        }
                        break;
                    case 2:
                        for (int i = 0; i < pace; ++i) {
                            if (getX() > 0 && !collideOtherTank()) {
                                moveLeft();
                            } else {
                                break;
                            }
                            Thread.sleep(50);
                            shot();
                        }
                        break;
                    case 3:
                        for (int i = 0; i < pace; ++i) {
                            if (getX() + 60 < MyPanel.backgroundWidth
                                    && !collideOtherTank()) {
                                moveRight();
                            } else {
                                break;
                            }
                            Thread.sleep(50);
                            shot();
                        }
                        break;

                }
                setDirection((int) (Math.random() * 4));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean collideOtherTank() {
        int direction = getDirection();
        switch (direction) {
            case 0://上
                for (int i = 0; i < enemyTanks.size(); ++i) {
                    EnemyTank e = enemyTanks.get(i);
                    if (!e.isLive() || (e == this)) {
                        continue;
                    }
                    int d = e.getDirection();
                    if (d == 0 || d == 1) {//e为上/下
                        //左上角
                        if (getX() >= e.getX() && getX() <= e.getX() + 40
                                && getY() >= e.getY() && getY() <= e.getY() + 60) {
                            return true;
                        }
                        //右上角
                        if (getX() + 40 >= e.getX() && getX() + 40 <= e.getX() + 40
                                && getY() >= e.getY() && getY() <= e.getY() + 60) {
                            return true;
                        }

                    } else {//e为左/右
                        //左上角
                        if (getX() >= e.getX() && getX() <= e.getX() + 60
                                && getY() >= e.getY() && getY() <= e.getY() + 40) {
                            return true;
                        }
                        //右上角
                        if (getX() + 40 >= e.getX() && getX() + 40 <= e.getX() + 60
                                && getY() >= e.getY() && getY() <= e.getY() + 40) {
                            return true;
                        }
                    }
                }

                break;
            case 1://下
                for (int i = 0; i < enemyTanks.size(); ++i) {
                    EnemyTank e = enemyTanks.get(i);
                    if (!e.isLive() || (e == this)) {
                        continue;
                    }
                    int d = e.getDirection();
                    if (d == 0 || d == 1) {//e为上/下
                        //左下角
                        if (getX() >= e.getX() && getX() <= e.getX() + 40
                                && getY() + 60 >= e.getY() && getY() + 60 <= e.getY() + 60) {
                            return true;
                        }
                        //右下角
                        if (getX() + 40 >= e.getX() && getX() + 40 <= e.getX() + 40
                                && getY() + 60 >= e.getY() && getY() + 60 <= e.getY() + 60) {
                            return true;
                        }

                    } else {//e为左/右
                        //左下角
                        if (getX() >= e.getX() && getX() <= e.getX() + 60
                                && getY() + 60 >= e.getY() && getY() + 60 <= e.getY() + 40) {
                            return true;
                        }
                        //右下角
                        if (getX() + 40 >= e.getX() && getX() + 40 <= e.getX() + 60
                                && getY() + 60 >= e.getY() && getY() + 60 <= e.getY() + 40) {
                            return true;
                        }
                    }
                }
                break;
            case 2://左
                for (int i = 0; i < enemyTanks.size(); ++i) {
                    EnemyTank e = enemyTanks.get(i);
                    if (!e.isLive() || (e == this)) {
                        continue;
                    }
                    int d = e.getDirection();
                    if (d == 0 || d == 1) {//e为上/下
                        //左上角
                        if (getX() >= e.getX() && getX() <= e.getX() + 40
                                && getY() >= e.getY() && getY() <= e.getY() + 60) {
                            return true;
                        }
                        //左下角
                        if (getX() >= e.getX() && getX() <= e.getX() + 40
                                && getY() + 40 >= e.getY() && getY() + 40 <= e.getY() + 60) {
                            return true;
                        }

                    } else {//e为左/右
                        //左上角
                        if (getX() >= e.getX() && getX() <= e.getX() + 60
                                && getY() >= e.getY() && getY() <= e.getY() + 40) {
                            return true;
                        }
                        //左下角
                        if (getX() >= e.getX() && getX() <= e.getX() + 60
                                && getY() + 40 >= e.getY() && getY() + 40 <= e.getY() + 40) {
                            return true;
                        }
                    }
                }
                break;
            case 3://右
                for (int i = 0; i < enemyTanks.size(); ++i) {
                    EnemyTank e = enemyTanks.get(i);
                    if (!e.isLive() || (e == this)) {
                        continue;
                    }
                    int d = e.getDirection();
                    if (d == 0 || d == 1) {//e为上/下
                        //右上角
                        if (getX() + 60 >= e.getX() && getX() + 60 <= e.getX() + 40
                                && getY() >= e.getY() && getY() <= e.getY() + 60) {
                            return true;
                        }
                        //右下角
                        if (getX() + 60 >= e.getX() && getX() + 60 <= e.getX() + 40
                                && getY() + 40 >= e.getY() && getY() + 40 <= e.getY() + 60) {
                            return true;
                        }

                    } else {//e为左/右
                        //右上角
                        if (getX() + 60 >= e.getX() && getX() + 60 <= e.getX() + 60
                                && getY() >= e.getY() && getY() <= e.getY() + 40) {
                            return true;
                        }
                        //右下角
                        if (getX() + 60 >= e.getX() && getX() + 60 <= e.getX() + 60
                                && getY() + 40 >= e.getY() && getY() + 40 <= e.getY() + 40) {
                            return true;
                        }
                    }
                }

                break;
        }
        return false;
    }

    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }
}
