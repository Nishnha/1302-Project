import java.util.*;
import java.util.Arrays;

public class Tuple {

    // Instance variables
    private ArrayList<String> attributes;
    private ArrayList<String> domains;
    private ArrayList<Comparable> tuple;

    // Constructor
    public Tuple (ArrayList<String> attr, ArrayList<String> dom) {
        attributes = new ArrayList<String>(attr);
        domains = new ArrayList<String>(dom);
        tuple = new ArrayList<Comparable>();
    }

    // Return true if this is equal to compareTuple
    public boolean equals (Tuple compareTuple) {
        if (
                this.attributes.equals(compareTuple.attributes) &&
                this.domains.equals(compareTuple.domains) &&
                this.tuple.equals(compareTuple.tuple)
            )
            return true;
        return false;
    }

    // Add String s at the end of the tuple
    public void addStringComponent(String s) {
        try {
            tuple.add((String) s);
        } catch (Exception e) {
            System.out.println(s + "is not of type string");
        }
    }

    // Add Double d at the end of the tuple
    public void addDoubleComponent(Double d) {
        try {
            tuple.add((double) d);
        } catch (Exception e) {
            System.out.println(d + "is not of type double");
        }

    }

    // Add Integer i at the end of the tuple
    public void addIntegerComponent(Integer i) {
        try {
            tuple.add((int) i);
        } catch (Exception e) {
            System.out.println(i + "is not of type integer");
        }
    }

    // Returns a clone of a tuple with new attributes
    public Tuple clone(ArrayList<String> attr) {
        Tuple changedAttr = new Tuple(attr, this.domains);
        changedAttr.tuple.addAll(this.tuple);
        return changedAttr;
    }

    // Returns a tuple with only the requested domains
    public Tuple project(ArrayList<String> cnames) {
        ArrayList<String> doms = new ArrayList<>();
        ArrayList<Comparable> values = new ArrayList<>();

        for (int i = 0; i < cnames.size(); i++) {
            for (int j = 0; j < this.attributes.size(); j++) {
                if (cnames.get(i).equals(this.attributes.get(j))) {
                    doms.add(domains.get(j));
                    values.add(tuple.get(j));
                }
            }
        }

        Tuple projected = new Tuple(cnames, doms);
        projected.tuple = values;

        return projected;
    }

    // Concatenate two tuples
    public Tuple concatenate(Tuple t2, ArrayList<String> attrs, ArrayList<String> doms) {
        ArrayList<String> catAttr = new ArrayList<>(attrs);
        catAttr.addAll(this.attributes);
        ArrayList<String> catDoms = new ArrayList<>(doms);
        catDoms.addAll(this.domains);

        Tuple concatenated = new Tuple(
                                catAttr,
                                catDoms
                );

        concatenated.tuple.addAll(this.tuple);
        concatenated.tuple.addAll(t2.tuple);

        return concatenated;
    }

    // return String representation of tuple; See output of run for format.
    public String toString() {
        String output = "";

        for (Comparable c : tuple) {
            output = output + c + ":";
        }

        return output;
    }

}
