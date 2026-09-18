class GymMember {
    protected String memberId;
    protected int monthlyFee;
    protected int sessionsAttended;

    public GymMember(String memberId, int monthlyFee) {
        if (memberId == null || memberId.trim().isEmpty() || memberId.length() < 4) {
            throw new IllegalArgumentException("Invalid member ID");
        }

        if (monthlyFee <= 0) {
            throw new IllegalArgumentException("Invalid monthly fee");
        }

        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
    }

    public void attendSession() {
        sessionsAttended++;
    }

    public String displayInfo() {
        return "Standard | Sessions: " + sessionsAttended;
    }
}

class PremiumMember extends GymMember {
    private String trainerName;

    public PremiumMember(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    @Override
    public String displayInfo() {
        return "Premium | Trainer: " + trainerName +
               " | Sessions: " + sessionsAttended;
    }
}

public class q4 {

    public static String batchPrint(GymMember[] members) {

        StringBuilder result = new StringBuilder();

        for (GymMember member : members) {

            result.append(member.displayInfo());

            if (member instanceof PremiumMember) {
                PremiumMember premium =
                    (PremiumMember) member;

                result.append(" [Trainer via downcast: ")
                      .append(premium.getTrainerName())
                      .append("]");
            }

            result.append(" | ");
        }

        return result.toString();
    }

    public static void main(String[] args) {

        GymMember standard =
            new GymMember("MEM6", 1000);

        PremiumMember premium =
            new PremiumMember("MEM7", 2000, "Coach Riya");

        GymMember[] members = {
            standard, premium
        };

        System.out.println(batchPrint(members));
    }
}