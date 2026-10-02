abstract class Assignment {
    String title;
    int maxMarks;
    int dueDay;

    Assignment(String title, int maxMarks, int dueDay) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDay = dueDay;
    }

    abstract double applyPenalty(double marks, int lateDays);
}

class CodingAssignment extends Assignment {
    CodingAssignment(String title, int maxMarks, int dueDay) {
        super(title, maxMarks, dueDay);
    }

    double applyPenalty(double marks, int lateDays) {
        return marks * Math.max(0, 1 - 0.10 * lateDays);
    }
}

class WrittenAssignment extends Assignment {
    WrittenAssignment(String title, int maxMarks, int dueDay) {
        super(title, maxMarks, dueDay);
    }

    double applyPenalty(double marks, int lateDays) {
        return marks * Math.max(0, 1 - 0.20 * lateDays);
    }
}

class Student {
    String name;

    Student(String name) {
        this.name = name;
    }
}

class Submission {
    Student student;
    Assignment assignment;
    int submitDay;
    String status = "Submitted";
    double marks;

    Submission(Student student, Assignment assignment, int submitDay) {
        this.student = student;
        this.assignment = assignment;
        this.submitDay = submitDay;
    }

    void grade(double awardedMarks) {
        if (status.equals("Graded")) {
            System.out.println("Cannot grade again.");
            return;
        }

        int lateDays = Math.max(0, submitDay - assignment.dueDay);
        marks = assignment.applyPenalty(awardedMarks, lateDays);
        status = "Graded";

        System.out.printf("%s graded: %.0f/%d. Status: Graded.%n",
                student.name, marks, assignment.maxMarks);
    }

    void resubmit() {
        if (status.equals("Graded"))
            System.out.println("Cannot resubmit: '" +
                    assignment.title + "' has already been graded.");
    }
}

public class q2 {
    public static void main(String[] args) {
        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        Assignment coding =
                new CodingAssignment("Linked List Lab", 50, 10);

        Assignment written =
                new WrittenAssignment("Design Essay", 50, 12);

        Submission s1 = new Submission(asha, coding, 10);
        System.out.println("Asha's submission for 'Linked List Lab' received.");
        System.out.println("Status: " + s1.status);

        Submission s2 = new Submission(ravi, written, 14);
        System.out.println("Ravi's submission for 'Design Essay' received.");
        System.out.println("Status: " + s2.status);

        s1.grade(45);
        s2.grade(40);

        s1.resubmit();
    }
}