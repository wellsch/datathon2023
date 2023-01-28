import java.util.Arrays;

public class Entry {

    private final int id;
    private final String msn;
    private final String stateCode;
    private final int year;
    private double amount;
    private final String state;
    private final double emissions;
    private final int numInvestments;
    private final int assistance;

    public Entry(int id, String msn, String stateCode, int year, double amount, String state, double emissions, int numInvestments, int assistance) {
        this.id = id;
        this.msn = msn;
        this.stateCode = stateCode;
        this.year = year;
        this.amount = amount;
        this.state = state;
        this.emissions = emissions;
        this.numInvestments = numInvestments;
        this.assistance = assistance;
    }

    public static Entry readLine(String line) {
        String[] arr = line.split(",");

        if (Arrays.asList(arr).contains("") || arr.length < 9) return null;

        return new Entry(Integer.parseInt(arr[0]), arr[1], arr[2], Integer.parseInt(arr[3]),
                Double.parseDouble(arr[4]), arr[5], Double.parseDouble(arr[6]),
                Integer.parseInt(arr[7]), Integer.parseInt(arr[8]));
    }

    public double getEmissions() {
        return this.emissions;
    }

    public double getAmount() {
        return this.amount;
    }

    public int getAssistance() {
        return this.assistance;
    }

    public int getId() {
        return this.id;
    }

    public int getNumInvestments() {
        return this.numInvestments;
    }

    public int getYear() {
        return this.year;
    }

    public String getMsn() {
        return this.msn;
    }

    public String getState() {
        return this.state;
    }

    public String getStateCode() {
        return this.stateCode;
    }

    @Override
    public String toString() {
        return this.id + "," + this.msn + "," + this.stateCode + "," + this.year
                + "," + this.amount + "," + this.state + "," + this.emissions
                + "," + this.numInvestments + "," + this.assistance + "\n";
    }

    public void setAmount(double val) {
        this.amount = val;
    }
}
