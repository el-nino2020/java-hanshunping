//SmallChangeSys.java
package small_change;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class SmallChangeSys {

    //输出明细用，但这种存储方式不如动态数组
    String details = "--------------零钱通明细-----------------\n";
    //账户余额
    double balance = 0.0;

    //以下为功能变量
    Scanner scanner;
    Date date;
    SimpleDateFormat simpleDateFormat;


    public void mainMenu() {
        scanner = new Scanner(System.in);
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        while (true) {
            System.out.println("--------------零钱通菜单-----------------");
            System.out.println("\t\t\t1. 零钱通明细");
            System.out.println("\t\t\t2. 收益入账");
            System.out.println("\t\t\t3. 消费");
            System.out.println("\t\t\t4. 退出");
            System.out.print("请选择（1-4）：");

            String choice = scanner.next();

            //字符串常量调用equals可以防止choice为null所抛出的异常
            //不过在这里没有这种用处
            if ("1".equals(choice)) {
                this.showDetails();
            } else if ("2".equals(choice)) {
                this.income();
            } else if ("3".equals(choice)) {
                this.consume();
            } else if ("4".equals(choice)) {
                if (this.quit()) {
                    break;
                }
            }
            System.out.println();
        }
    }

    //展示零钱通明细
    private void showDetails() {
        System.out.println(details);
    }

    //接收入账金额
    private void income() {
        System.out.print("收入金额：");

        //这句话可能抛出异常
        double money = scanner.nextDouble();
        if (money <= 0) {
            System.out.println("输入金额应为正数");
            return;
        }

        balance += money;

        date = new Date();
        details += "收益入账 +" + money + " " + simpleDateFormat.format(date);
        details += " 余额 " + balance + "\n";
    }

    private void consume() {
        System.out.print("消费金额：");

        //这句话可能抛出异常
        double money = scanner.nextDouble();

        if (money <= 0) {
            System.out.println("消费金额应为正数");
            return;
        } else if (money > balance) {
            System.out.println("余额不足");
            return;
        }
        System.out.print("何种消费？");
        String reason = scanner.next();

        balance -= money;

        date = new Date();
        details += reason + " -" + money + " " + simpleDateFormat.format(date);
        details += " 余额 " + balance + "\n";
    }

    private boolean quit() {
        String choice;

        while (true) {
            System.out.println("确定要退出吗？");
            System.out.print("输入：y/n  ");
            choice = scanner.next();

            if ("y".equals(choice)) {
                return true;
            } else if ("n".equals(choice)) {
                return false;
            }
        }

    }

}
