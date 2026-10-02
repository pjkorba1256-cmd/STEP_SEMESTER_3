import java.util.*;

abstract class Vehicle {
    String name;
    boolean available = true;

    Vehicle(String name) {
        this.name = name;
    }

    abstract double calculateCharge(int days);
}

class Sedan extends Vehicle {
    Sedan(String name) {
        super(name);
    }

    double calculateCharge(int days) {
        return days * 50;
    }
}

class SUV extends Vehicle {
    SUV(String name) {
        super(name);
    }

    double calculateCharge(int days) {
        return days * 80;
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Rental {
    Vehicle vehicle;
    Customer customer;
    int days;

    Rental(Vehicle vehicle, Customer customer, int days) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.days = days;
    }
}

public class q1 {
    static void rent(Vehicle v, Customer c, int days) {
        if (!v.available) {
            System.out.println(v.name + " is currently unavailable.");
            return;
        }

        v.available = false;
        Rental r = new Rental(v, c, days);

        System.out.println(v.name + " rented successfully by " + c.name);
        System.out.println("Rental charge: $" + v.calculateCharge(days));
    }

    static void returnVehicle(Vehicle v, Customer c) {
        v.available = true;
        System.out.println(v.name + " returned by " + c.name);
    }

    public static void main(String[] args) {
        Customer c1 = new Customer("Customer 1");
        Customer c2 = new Customer("Customer 2");
        Customer c3 = new Customer("Customer 3");

        Vehicle sedan = new Sedan("Sedan A");
        Vehicle suv = new SUV("SUV B");

        rent(sedan, c1, 3);
        rent(sedan, c2, 2);

        returnVehicle(sedan, c1);

        rent(suv, c3, 5);
    }
}