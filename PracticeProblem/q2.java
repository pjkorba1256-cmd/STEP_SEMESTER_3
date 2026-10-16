
import java.util.*;

abstract class Vehicle {
    String passNumber;
    String owner;

    Vehicle(String passNumber, String owner) {
        this.passNumber = passNumber;
        this.owner = owner;
    }

    abstract int getPassFee();

    abstract String getType();
}

interface Chargeable {
    boolean bookChargingBay();
}

class Bike extends Vehicle {
    Bike(String passNumber, String owner) {
        super(passNumber, owner);
    }

    int getPassFee() {
        return 300;
    }

    String getType() {
        return "Bike";
    }
}

class Car extends Vehicle {
    Car(String passNumber, String owner) {
        super(passNumber, owner);
    }

    int getPassFee() {
        return 1000;
    }

    String getType() {
        return "Car";
    }
}

class EBike extends Vehicle implements Chargeable {
    EBike(String passNumber, String owner) {
        super(passNumber, owner);
    }

    int getPassFee() {
        return 300;
    }

    String getType() {
        return "EBike";
    }

    public boolean bookChargingBay() {
        return true;
    }
}

class ECar extends Vehicle implements Chargeable {
    ECar(String passNumber, String owner) {
        super(passNumber, owner);
    }

    int getPassFee() {
        return 1000;
    }

    String getType() {
        return "ECar";
    }

    public boolean bookChargingBay() {
        return true;
    }
}

public class q2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Vehicle> vehicles = new LinkedHashMap<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] p = line.split("\\s+");

            if (p[0].equals("PASS")) {
                String type = p[1];
                String pass = p[2];
                String owner = p[3];

                Vehicle v = null;

                switch (type) {
                    case "Bike":
                        v = new Bike(pass, owner);
                        break;
                    case "Car":
                        v = new Car(pass, owner);
                        break;
                    case "EBike":
                        v = new EBike(pass, owner);
                        break;
                    case "ECar":
                        v = new ECar(pass, owner);
                        break;
                }

                if (v != null) {
                    vehicles.put(pass, v);
                    System.out.println(pass + " (" + v.getType()
                        + ") pass fee " + v.getPassFee());
                }
            } else if (p[0].equals("CHARGE")) {
                String pass = p[1];
                Vehicle v = vehicles.get(pass);

                if (v instanceof Chargeable) {
                    ((Chargeable) v).bookChargingBay();
                    System.out.println(pass + " charging bay allotted");
                } else {
                    System.out.println(
                        pass + " rejected: charging unsupported");
                }
            }
        }

        sc.close();
    }
}
