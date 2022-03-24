package tank_game;

import java.io.*;
import java.util.Vector;

/**
 * 该类不需要创建对象，所有方法均为静态方法
 */
public final class Recorder {
    //将构造器私有，使该类不能被实例化，参考了Math类
    private Recorder() {
    }

    private static int totalKilledEnemy = 0;
    private static String filePath =
            "D:\\CODES(daima)\\idea_java\\hello\\src\\tank_game\\recordInfo.txt";

    private static Vector<EnemyTank> enemyTanks;

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    public static int getTotalKilledEnemy() {
        return totalKilledEnemy;
    }

    public static void setTotalKilledEnemy(int totalKilledEnemy) {
        Recorder.totalKilledEnemy = totalKilledEnemy;
    }

    public static void anEnemyKilled() {
        ++totalKilledEnemy;
    }

    /**
     * 查看路径为filepath的文件是否存在，如果不存在，新建该文件
     */
    private static void infoFileExist() {
        File file = new File(filePath);
        if (file.exists()) {
            return;
        } else {
            try {
                file.createNewFile();
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                bw.write("0");
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
            }
        }

    }


    /**
     * @param choice choice >=0，值越大，执行的操作越多(可拓展性)
     * @return 需要的信息
     */
    public static Vector<EnemyTank> readInfo(int choice) {
        infoFileExist();

        BufferedReader br = null;


        try {
            br = new BufferedReader(new FileReader(filePath));
            //choice等于0的情况
            totalKilledEnemy = Integer.parseInt(br.readLine());

            if (choice >= 1) {
                enemyTanks = new Vector<>();
                String line;
                while ((line = br.readLine()) != null) {
                    String[] strings = line.split(" ");
                    EnemyTank enemyTank =
                            new EnemyTank(Integer.parseInt(strings[0]),
                                    Integer.parseInt(strings[1]));
                    enemyTank.setDirection(Integer.parseInt(strings[2]));
                    enemyTanks.add(enemyTank);
                }
                return enemyTanks;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public static void writeInfo() {
        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter(filePath));
            bw.write(totalKilledEnemy + "");
            bw.newLine();

            for (int i = 0; i < enemyTanks.size(); ++i) {
                EnemyTank e = enemyTanks.get(i);
                if (e.isLive()) {
                    bw.write(e.getX() + " " + e.getY() + " " + e.getDirection());
                    bw.newLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
