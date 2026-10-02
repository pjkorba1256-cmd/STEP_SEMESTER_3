import java.util.*;

interface NotificationChannel {
    void send(Student student, String message);
}

class EmailChannel implements NotificationChannel {
    public void send(Student student, String message) {
        System.out.println("[Email → " + student.name + "] " + message);
    }
}

class SmsChannel implements NotificationChannel {
    public void send(Student student, String message) {
        System.out.println("[SMS → " + student.name + "] " + message);
    }
}

class AppChannel implements NotificationChannel {
    public void send(Student student, String message) {
        System.out.println("[App → " + student.name + "] " + message);
    }
}

class Student {
    String name;
    String department;
    ArrayList<NotificationChannel> channels = new ArrayList<>();

    Student(String name, String department) {
        this.name = name;
        this.department = department;
    }

    void addChannel(NotificationChannel channel) {
        channels.add(channel);
    }
}

class Notice {
    String title;
    HashSet<String> departments;

    Notice(String title, String... departments) {
        this.title = title;
        this.departments = new HashSet<>(Arrays.asList(departments));
    }

    boolean valid() {
        return title != null && !title.isEmpty()
                && !departments.isEmpty();
    }
}

class NoticeBoard {
    ArrayList<Student> students = new ArrayList<>();

    void addStudent(Student s) {
        students.add(s);
    }

    void post(Notice notice) {
        if (!notice.valid()) {
            System.out.println(
                    "Cannot post notice: At least one target department is required.");
            return;
        }

        System.out.println("Notice '" + notice.title +
                "' posted to " + notice.departments);

        for (Student s : students) {
            if (notice.departments.contains(s.department)) {
                for (NotificationChannel c : s.channels)
                    c.send(s, notice.title);
            }
        }
    }
}

public class q5 {
    public static void main(String[] args) {
        Student asha = new Student("Asha", "CSE");
        Student ravi = new Student("Ravi", "ECE");

        asha.addChannel(new EmailChannel());
        asha.addChannel(new AppChannel());

        ravi.addChannel(new SmsChannel());

        NoticeBoard board = new NoticeBoard();

        board.addStudent(asha);
        board.addStudent(ravi);

        board.post(new Notice(
                "Lab Closed Tomorrow", "CSE"));

        board.post(new Notice(
                "Fee Deadline Extended", "CSE", "ECE"));

        board.post(new Notice(
                "Sports Day"));
    }
}