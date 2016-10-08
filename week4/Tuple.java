import java.util.*;
import java.util.Arrays;

public class Tuple {

    private ArrayList<String> attributes;
    private ArrayList<String> domains;
    private ArrayList<Comparable> tuple;

    // METHODS

    // Constructor; set instance variables
    public Tuple (ArrayList<String> attr, ArrayList<String> dom) {
        attributes = new ArrayList<String>(attr);
        domains = new ArrayList<String>(dom);
        tuple = new ArrayList<Comparable>();
    }

    // Return true if this tuple is equal to compareTuple
    public boolean equals (Tuple compareTuple) {
        if (this.tuple.equals(compareTuple.tuple))
            return true;
        return false;
    }

    // Add String s at the end of the tuple
    public void addStringComponent(String s) {
        tuple.add(s);
    }

    // Add Double d at the end of the tuple
    public void addDoubleComponent(Double d) {
        tuple.add(d);
    }

    // Add Integer i at the end of the tuple
    public void addIntegerComponent(Integer i) {
        tuple.add(i);
    }

    // Returns a clone of a tuple with a new attribute list
    public Tuple clone(ArrayList<String> attr) {
        Tuple newTuple = new Tuple(attr, this.domains);
        for (int i = 0; i < this.tuple.size(); i++) {
            switch (this.domains.get(i)) {
                case "VARCHAR":
                    newTuple.addStringComponent((String) this.tuple.get(i));
                    break;
                case "INTEGER":
                    newTuple.addIntegerComponent((int) this.tuple.get(i));
                    break;
                case "DOUBLE":
                    newTuple.addDoubleComponent((double) this.tuple.get(i));
                    break;
            }
        }
        return newTuple;
    }

    // return String representation of tuple; See output of run for format.
    public String toString() {
        String output = new String();
        for (Comparable c : tuple) {
            output = output + c + ":";
        }
        return output;
    }

}
