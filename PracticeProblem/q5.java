import java.util.*;

interface PaymentMethod {
    boolean processPayment(double amount);
}

class CreditCardPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        System.out.println("Processing Credit Card payment...");
        return true;
    }
}

class PayPalPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        System.out.println("Processing PayPal payment...");
        return false;
    }
}

class Product {
    String name;
    double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class Order {
    String customer;
    ArrayList<Product> products = new ArrayList<>();
    String status = "Pending";

    Order(String customer) {
        this.customer = customer;
    }

    void addProduct(Product p) {
        products.add(p);
    }

    double getTotal() {
        double total = 0;

        for (Product p : products)
            total += p.price;

        return total;
    }

    void pay(PaymentMethod method) {
        if (products.isEmpty()) {
            System.out.println("Cannot process payment for an empty order.");
            return;
        }

        System.out.println("Payment initiated for Order " + customer);

        if (method.processPayment(getTotal())) {
            status = "Paid";
            System.out.println("Payment successful.");
        } else {
            System.out.println("Payment failed.");
        }

        System.out.println("Order status: " + status);
    }
}

public class q5 {
    public static void main(String[] args) {
        Order order1 = new Order("X");

        order1.addProduct(new Product("Product A", 100));
        order1.addProduct(new Product("Product B", 50));

        System.out.println("Order created for Customer X.");
        order1.pay(new CreditCardPayment());

        Order order2 = new Order("Y");
        order2.pay(new CreditCardPayment());

        Order order3 = new Order("Z");
        order3.addProduct(new Product("Product C", 200));

        order3.pay(new PayPalPayment());
    }
}