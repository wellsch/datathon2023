import java.util.List;
import java.util.Map;

public class State {

    public String name;
    public int year;
    public List<Double> msnVals;

    public State(String name, int year, List<Double> msnVals) {
        this.name = name;
        this.year = year;
        this.msnVals = msnVals;
    }

    @Override
    public String toString() {
        StringBuilder retval = new StringBuilder(this.name + "," + this.year);
        for (Double val : msnVals) {
            retval.append(",").append(val);
        }
        return retval.append("\n").toString();
    }
}
