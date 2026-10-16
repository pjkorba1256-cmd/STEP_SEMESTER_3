
import java.util.*;

abstract class Staff {
    String name;

    Staff(String name) {
        this.name = name;
    }

    abstract int calculatePay();

    abstract String getType();

    @Override
    public String toString() {
        return "Payslip[name=" + name
            + ", type=" + getType()
            + ", pay=" + calculatePay() + "]";
    }
}

class FullTime extends Staff {
    int salary;

    FullTime(String name, int salary) {
        super(name);
        this.salary = salary;
    }

    @Override
    int calculatePay() {
        return salary;
    }

    @Override
    String getType() {
        return "FullTime";
    }
}

class PartTime extends Staff {
    int hours;
    int rate;

    PartTime(String name, int hours, int rate) {
        super(name);
        this.hours = hours;
        this.rate = rate;
    }

    @Override
    int calculatePay() {
        return hours * rate;
    }

    @Override
    String getType() {
        return "PartTime";
    }
}

class Intern extends Staff {
    int stipend;

    Intern(String name, int stipend) {
        super(name);
        this.stipend = stipend;
    }

    @Override
    int calculatePay() {
        return stipend;
    }

    @Override
    String getType() {
        return "Intern";
    }
}

public class q5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Staff> staffList = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] p = line.split("\\s+");

            switch (p[0]) {
                case "FullTime":
                    staffList.add(new FullTime(
                        p[1], Integer.parseInt(p[2])));
                    break;

                case "PartTime":
                    staffList.add(new PartTime(
                        p[1],
                        Integer.parseInt(p[2]),
                        Integer.parseInt(p[3])));
                    break;

                case "Intern":
                    staffList.add(new Intern(
                        p[1], Integer.parseInt(p[2])));
                    break;
            }
        }

        int total = 0;
        Staff topEarner = null;

        for (Staff staff : staffList) {
            System.out.println(staff);

            int pay = staff.calculatePay();
            total += pay;

            if (topEarner == null
                    || pay > topEarner.calculatePay()) {
                topEarner = staff;
            }
        }

        System.out.println("Total " + total);

        if (topEarner != null) {
            System.out.println("top earner " + topEarner.name);
        }

        sc.close();
    }
}
