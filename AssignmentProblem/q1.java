abstract class WashType {
    abstract int getDuration();
    abstract double getCharge();
}

class Quick extends WashType {
    int getDuration() { return 30; }
    double getCharge() { return 20; }
}

class Normal extends WashType {
    int getDuration() { return 45; }
    double getCharge() { return 30; }
}

class Heavy extends WashType {
    int getDuration() { return 60; }
    double getCharge() { return 45; }
}

class Student {
    String name;

    Student(String name) {
        this.name = name;
    }
}

class WashCycle {
    Student student;
    WashingMachine machine;
    WashType type;

    WashCycle(Student student, WashingMachine machine, WashType type) {
        this.student = student;
        this.machine = machine;
        this.type = type;
    }
}

class WashingMachine {
    String id;
    private boolean busy = false;

    WashingMachine(String id) {
        this.id = id;
    }

    void startWash(Student s, WashType type) {
        if (busy) {
            System.out.println("Machine " + id + " is currently busy.");
            return;
        }

        busy = true;
        new WashCycle(s, this, type);

        System.out.println(type.getClass().getSimpleName()
                + " wash started on " + id + " for " + s.name
                + " (" + type.getDuration() + " min).");
        System.out.printf("Charge: ₹%.2f%n", type.getCharge());
    }

    void completeWash() {
        if (busy) {
            busy = false;
            System.out.println(id + " cycle completed.");
            System.out.println(id + " is now free.");
        }
    }
}

public class q1 {
    public static void main(String[] args) {
        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        m1.startWash(asha, new Quick());
        m1.startWash(ravi, new Heavy());

        m2.startWash(ravi, new Heavy());

        m1.completeWash();

        m1.startWash(neha, new Normal());
    }
}