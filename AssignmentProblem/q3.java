import java.util.*;

public class q3 {
    static void mostPopular(String[] orders) {
        HashMap<String, Integer> map = new HashMap<>();

        for (String item : orders)
            map.put(item, map.getOrDefault(item, 0) + 1);

        String popular = orders[0];
        int max = map.get(popular);

        for (String item : orders) {
            if (map.get(item) > max) {
                popular = item;
                max = map.get(item);
            }
        }

        System.out.println("(" + popular + ", " + max + ")");
    }

    public static void main(String[] args) {
        String[] orders = {
            "dosa", "idli", "vada", "dosa",
            "idli", "dosa", "tea"
        };

        mostPopular(orders);
    }
}