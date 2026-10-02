abstract class MembershipPlan {
    abstract double calculateFee();
}

class Monthly extends MembershipPlan {
    double calculateFee() {
        return 1000;
    }
}

class Quarterly extends MembershipPlan {
    double calculateFee() {
        return 1000 * 3 * 0.90;
    }
}

class Annual extends MembershipPlan {
    double calculateFee() {
        return 1000 * 12 * 0.75;
    }
}

class Member {
    String name;

    Member(String name) {
        this.name = name;
    }
}

class Membership {
    Member member;
    MembershipPlan plan;
    String status = "Active";

    Membership(Member member, MembershipPlan plan) {
        this.member = member;
        this.plan = plan;
    }

    void checkIn() {
        if (status.equals("Active"))
            System.out.println(member.name + " checked in successfully.");
        else
            System.out.println("Check-in denied: " + member.name +
                    "'s membership is " + status + ".");
    }

    void freeze() {
        if (status.equals("Active")) {
            status = "Frozen";
            System.out.println(member.name +
                    "'s membership frozen.");
            System.out.println("Status: " + status);
        } else {
            System.out.println("Cannot freeze an " +
                    status + " membership.");
        }
    }

    void unfreeze() {
        if (status.equals("Frozen")) {
            status = "Active";
            System.out.println(member.name +
                    "'s membership unfrozen.");
        } else {
            System.out.println("Cannot unfreeze an " +
                    status + " membership.");
        }
    }

    void expire() {
        status = "Expired";
        System.out.println(member.name +
                "'s membership expired.");
        System.out.println("Status: " + status);
    }
}

public class q4 {
    public static void main(String[] args) {
        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        Membership m1 =
                new Membership(asha, new Quarterly());

        Membership m2 =
                new Membership(ravi, new Monthly());

        System.out.printf("Quarterly membership created for Asha. Fee: ₹%.2f%n",
                m1.plan.calculateFee());
        System.out.println("Status: " + m1.status);

        System.out.printf("Monthly membership created for Ravi. Fee: ₹%.2f%n",
                m2.plan.calculateFee());
        System.out.println("Status: " + m2.status);

        m1.checkIn();
        m1.freeze();
        m1.checkIn();

        m2.expire();
        m2.freeze();
    }
}