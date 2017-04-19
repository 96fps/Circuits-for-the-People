import java.util.ArrayList;


public class ControlSet {
    ArrayList<Boolean> buttons = new ArrayList<Boolean>();
    ArrayList<Double> doubles = new ArrayList<Double>();
    ArrayList<Integer> ints = new ArrayList<Integer>();

    public ControlSet() {

    }

    public ControlSet(ArrayList<Boolean> b, ArrayList<Double> d, ArrayList<Integer> i) {
        buttons = b;
        doubles = d;
        ints = i;
    }

    public ControlSet(ArrayList<Boolean> b) {
        buttons = b;
    }
}
