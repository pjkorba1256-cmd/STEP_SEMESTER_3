import java.util.*;

abstract class Room {
    int number;
    boolean booked = false;

    Room(int number) {
        this.number = number;
    }

    abstract double calculatePrice(int days);
}

class StandardRoom extends Room {
    StandardRoom(int number) {
        super(number);
    }

    double calculatePrice(int days) {
        return days * 100;
    }
}

class DeluxeRoom extends Room {
    DeluxeRoom(int number) {
        super(number);
    }

    double calculatePrice(int days) {
        return days * 150;
    }
}

class Reservation {
    Room room;
    String customer;
    int days;

    Reservation(Room room, String customer, int days) {
        this.room = room;
        this.customer = customer;
        this.days = days;
    }
}

public class q4 {
    static void reserve(Room room, String customer, int days) {
        if (room.booked) {
            System.out.println("Room " + room.number + " is not available.");
            return;
        }

        room.booked = true;

        System.out.println("Reservation confirmed for " + customer +
                ", Room " + room.number);
        System.out.println("Price: $" + room.calculatePrice(days));
    }

    static void cancel(Room room, String customer) {
        if (room.booked) {
            room.booked = false;
            System.out.println("Reservation for " + customer +
                    ", Room " + room.number + " cancelled successfully.");
        }
    }

    public static void main(String[] args) {
        Room standard = new StandardRoom(101);
        Room deluxe = new DeluxeRoom(201);

        System.out.println("Standard Room 101 is available.");
        reserve(standard, "Customer A", 4);

        reserve(standard, "Customer B", 4);

        cancel(standard, "Customer A");

        reserve(deluxe, "Customer C", 2);
    }
}