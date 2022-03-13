import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            integers.add(2);
        }
        System.out.println(integers);

        for (int i = 0; i < integers.size(); i++) {
            if (integers.get(i) == 2) {
                integers.remove(i);
                --i;
            }
        }
        System.out.println(integers);

    }
}
