import java.util.*;

abstract class Question {
    String question;
    String correctAnswer;
    int marks;

    Question(String question, String correctAnswer, int marks) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.marks = marks;
    }

    abstract boolean evaluate(String answer);
}

class MCQ extends Question {
    MCQ(String q, String a, int marks) {
        super(q, a, marks);
    }

    boolean evaluate(String answer) {
        return answer.equalsIgnoreCase(correctAnswer);
    }
}

class TrueFalse extends Question {
    TrueFalse(String q, String a, int marks) {
        super(q, a, marks);
    }

    boolean evaluate(String answer) {
        return answer.equalsIgnoreCase(correctAnswer);
    }
}

class Attempt {
    HashMap<Question, String> answers = new HashMap<>();
    boolean submitted = false;

    void answer(Question q, String ans) {
        if (submitted) {
            System.out.println("Cannot change answers for a submitted examination.");
            return;
        }

        answers.put(q, ans);
        System.out.println("Answer recorded for the question.");
    }

    void submit() {
        submitted = true;
    }

    void result() {
        int total = 0;
        int obtained = 0;

        for (Question q : answers.keySet()) {
            if (q.evaluate(answers.get(q))) {
                obtained += q.marks;
                System.out.println("Correct (" + q.marks + " points)");
            } else {
                System.out.println("Incorrect (0 points)");
            }

            total += q.marks;
        }

        System.out.println("Total score: " + obtained + "/" + total);
    }
}

public class q3 {
    public static void main(String[] args) {
        Question q1 = new MCQ("Capital of India?", "C", 5);
        Question q2 = new TrueFalse("Java is OOP?", "True", 5);

        Attempt attempt = new Attempt();

        System.out.println("Exam A started by Student 1.");

        attempt.answer(q1, "C");
        attempt.answer(q2, "True");

        attempt.submit();
        System.out.println("Exam A submitted by Student 1.");

        attempt.result();

        attempt.answer(q1, "A");
    }
}