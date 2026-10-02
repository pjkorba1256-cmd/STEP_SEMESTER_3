abstract class Seat {
    String id;

    Seat(String id) {
        this.id = id;
    }

    abstract double getPrice();
}

class Regular extends Seat {
    Regular(String id) {
        super(id);
    }

    double getPrice() {
        return 150;
    }
}

class Premium extends Seat {
    Premium(String id) {
        super(id);
    }

    double getPrice() {
        return 250;
    }
}

class Recliner extends Seat {
    Recliner(String id) {
        super(id);
    }

    double getPrice() {
        return 400;
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Show {
    String time;
    java.util.HashSet<String> booked = new java.util.HashSet<>();

    Show(String time) {
        this.time = time;
    }

    boolean isAvailable(Seat seat) {
        return !booked.contains(seat.id);
    }

    boolean book(Seat seat) {
        return booked.add(seat.id);
    }

    void release(Seat seat) {
        booked.remove(seat.id);
    }
}

class Booking {
    Customer customer;
    Show show;
    java.util.ArrayList<Seat> seats = new java.util.ArrayList<>();

    Booking(Customer customer, Show show) {
        this.customer = customer;
        this.show = show;
    }

    boolean addSeat(Seat seat) {
        if (seats.size() >= 6) {
            System.out.println("Maximum 6 seats allowed.");
            return false;
        }

        if (!show.isAvailable(seat)) {
            System.out.println("Seat " + seat.id +
                    " is already booked for this show.");
            return false;
        }

        show.book(seat);
        seats.add(seat);
        return true;
    }

    double getTotal() {
        double total = 0;

        for (Seat s : seats)
            total += s.getPrice();

        return total;
    }

    void cancel() {
        for (Seat s : seats)
            show.release(s);

        System.out.println(customer.name + "'s booking cancelled.");
        System.out.println("All seats released.");
    }
}

public class q3 {
    public static void main(String[] args) {
        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        Show show = new Show("7 PM");

        Booking b1 = new Booking(asha, show);
        b1.addSeat(new Regular("A1"));
        b1.addSeat(new Regular("A2"));
        b1.addSeat(new Premium("F5"));

        System.out.println("Booking confirmed for Asha.");
        System.out.printf("Total: ₹%.2f%n", b1.getTotal());

        Booking b2 = new Booking(ravi, show);
        b2.addSeat(new Regular("A2"));
        b2.addSeat(new Recliner("R1"));

        System.out.printf("Ravi's total: ₹%.2f%n", b2.getTotal());

        b1.cancel();

        Booking b3 = new Booking(neha, show);
        b3.addSeat(new Regular("A2"));

        System.out.printf("Neha's total: ₹%.2f%n", b3.getTotal());
    }
}