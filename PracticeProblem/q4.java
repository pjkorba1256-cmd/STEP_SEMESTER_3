
import java.util.*;

public class q4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> names = new ArrayList<>();
        List<int[]> marksList = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            int bracket = line.indexOf('[');
            if (bracket == -1) continue;

            String name = line.substring(0, bracket).trim();
            String values = line.substring(
                bracket + 1, line.indexOf(']')).trim();

            String[] parts = values.split("\\s*,\\s*");

            int[] marks = new int[3];
            for (int i = 0; i < 3; i++) {
                marks[i] = Integer.parseInt(parts[i]);
            }

            names.add(name);
            marksList.add(marks);
        }

        int n = names.size();
        int[][] marks = new int[n][3];
        int[] totals = new int[n];
        int[] subjectTotals = new int[3];

        for (int i = 0; i < n; i++) {
            marks[i] = marksList.get(i);

            for (int j = 0; j < 3; j++) {
                totals[i] += marks[i][j];
                subjectTotals[j] += marks[i][j];
            }
        }

        System.out.print("Totals ");
        for (int i = 0; i < n; i++) {
            if (i > 0) System.out.print(", ");
            System.out.print(names.get(i) + " " + totals[i]);
        }
        System.out.println();

        System.out.print("averages ");
        for (int j = 0; j < 3; j++) {
            if (j > 0) System.out.print(", ");
            double average = n == 0 ? 0.0
                : (double) subjectTotals[j] / n;
            System.out.printf(Locale.US, "%.2f", average);
        }
        System.out.println();

        if (n > 0) {
            int topper = 0;

            for (int i = 1; i < n; i++) {
                if (totals[i] > totals[topper]) {
                    topper = i;
                }
            }

            System.out.println("topper " + names.get(topper)
                + " (" + totals[topper] + ")");
        }

        sc.close();
    }
}
