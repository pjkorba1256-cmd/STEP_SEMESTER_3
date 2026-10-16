
import java.util.*;

public class q3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Set<String> members = new HashSet<>();
        List<String> addOutput = new ArrayList<>();
        List<String> containsOutput = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] p = line.split("\\s+");

            if (p[0].equals("ADD")) {
                String roll = p[1];

                if (members.add(roll)) {
                    addOutput.add("Added");
                } else {
                    addOutput.add("duplicate rejected");
                }
            } else if (p[0].equals("CONTAINS")) {
                String roll = p[1];
                containsOutput.add(
                    "contains: " + members.contains(roll));
            }
        }

        for (String output : addOutput) {
            System.out.println(output);
        }

        System.out.println("member count " + members.size());

        for (String output : containsOutput) {
            System.out.println(output);
        }

        sc.close();
    }
}
