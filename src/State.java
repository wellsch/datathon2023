import java.util.List;
import java.util.Map;

public class State {

    private final String name;
    private final int year;
    private final List<Double> msnVals;
    private final int assistance;
    private final int numInvestments;
    private final double emissions;

    public State(String name, int year, List<Double> msnVals, int assistance, int numInvestments, double emissions) {
        this.name = name;
        this.year = year;
        this.msnVals = msnVals;
        this.assistance = assistance;
        this.numInvestments = numInvestments;
        this.emissions = emissions;
    }

    @Override
    public String toString() {
        StringBuilder retval = new StringBuilder(this.name + "," + this.year);
        for (Double val : this.msnVals) {
            retval.append(",").append(val);
        }
        return retval.append(",").append(this.emissions).append(",").append(this.numInvestments).append(",").append(this.assistance).append("\n").toString();
    }
}
