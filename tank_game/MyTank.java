package tank_game;

public class MyTank extends Tank {
    public MyTank(int x, int y) {
        super(x, y);
        setShotPeriod(100);
        setSpeed(5);
    }
}
