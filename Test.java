

public class Test {
    public static void main(String[] args) {
        Account account = new Account();
        Thread thread = new Thread(account);
        thread.setName("A");
        Thread thread2 = new Thread(account);
        thread2.setName("B");
        thread.start();
        thread2.start();
    }
}

class Account implements Runnable {
    private int money = 10000;
    private boolean loop = true;

    private synchronized void withdraw() {
        if (money < 1000) {
            loop = false;
            System.out.println(Thread.currentThread().getName() + " 余额不足，无法取钱");
            return;
        }
        System.out.println(Thread.currentThread().getName()
                + "取出" + 1000 + "元, 还剩" + (money -= 1000) + "元");

    }

    @Override
    public void run() {
        while (loop) {
            withdraw();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
