class q5 {
    private final String bookingId;
    private final String[] seatNumbers;

    public q5(String id, String[] seats) {
        bookingId = id;
        seatNumbers = seats.clone();
    }

    public String[] getSeatNumbers() {
        return seatNumbers.clone();
    }

    public q5 withUpdatedSeat(int index, String seat) {
        String[] seats = seatNumbers.clone();
        seats[index] = seat;
        return new q5(bookingId, seats);
    }

    public static String processNightlySettlement(q5[] r) {
        int processed = 0, skipped = 0, group = 0, individual = 0;

        for (q5 b : r) {
            if (b == null)
                skipped++;
            else {
                processed++;
                if (b instanceof GroupBookingReceipt)
                    group++;
                else
                    individual++;
            }
        }

        return processed + " processed | " + skipped + " null skipped | "
                + group + " group | " + individual + " individual";
    }

    public static void main(String[] args) {
        q5 b = new q5("CH-1001", new String[]{"A1", "A2"});

        System.out.println(b.getSeatNumbers()[0]);

        q5 updated = b.withUpdatedSeat(1, "A3");

        System.out.println(updated.getSeatNumbers()[0]);
        System.out.println(updated.getSeatNumbers()[1]);

        q5[] r = {
            new GroupBookingReceipt("CH-2002", new String[]{"B1", "B2"}, 2),
            null,
            new q5("CH-3003", new String[]{"C1"})
        };

        System.out.println(q5.processNightlySettlement(r));
    }
}

class GroupBookingReceipt extends q5 {
    public GroupBookingReceipt(String id, String[] seats, int size) {
        super(id, seats);
    }
}