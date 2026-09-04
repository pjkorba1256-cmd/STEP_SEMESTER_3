import java.util.Scanner;

public class q3 {

    static void findLongestStreak(String signalLog) {

        int currentCount = 1;
        int maxCount = 1;

        char maxColor = signalLog.charAt(0);

        for (int i = 1; i < signalLog.length(); i++) {

            if (signalLog.charAt(i) == signalLog.charAt(i - 1)) {
                currentCount++;
            } 
            else {
                currentCount = 1;
            }

            if (currentCount > maxCount) {
                maxCount = currentCount;
                maxColor = signalLog.charAt(i);
            }
        }

        System.out.println(
            "Longest Streak: '" + maxColor +
            "' repeated " + maxCount + " times"
        );
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter signal log: ");
        String signalLog = sc.next();

        findLongestStreak(signalLog);

        sc.close();
    }
}
