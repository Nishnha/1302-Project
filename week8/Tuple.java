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
        }
        catch (Exception e) {
            System.out.println(s + "is not of type string");
        }
    }

    // Add Double d at the end of the tuple
    public void addDoubleComponent(Double d) {
        try {
            tuple.add((double) d);
        }
        catch (Exception e) {
            System.out.println(d + "is not of type double");
        }

    }

    // Add Integer i at the end of the tuple
    public void addIntegerComponent(Integer i) {
        try {
            tuple.add((int) i);
        }
        catch (Exception e) {
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

    // Takes a comparison condition and returns true if the tuple satisfies the condition
    public boolean select(String lopType, String lopValue, String comparison, String ropType, String ropValue) {
        if (lopType.equals("str") && ropType.equals("str")) {
            return evalStr(lopValue, comparison, ropValue);
        }
        else if (lopType.equals("num") && ropType.equals("num")){
            return evalNum(Double.parseDouble(lopValue), comparison, Double.parseDouble(ropValue));
        }
        else {
            return evalCol(lopType, lopValue, comparison, ropType, ropValue);
        }
    }

    // Evaluates column queries
    private boolean evalCol(String lopType, String lopValue, String comparison, String ropType, String ropValue) {
        String stringCol = new String();
        String stringCol2 = new String();
        double intCol = 0;

        if (lopType.equals("col") && ropType.equals("num")) {
            for (int i = 0; i < attributes.size(); i++)
                if (attributes.get(i).equals(lopValue))
                    if (domains.get(i).equals("INTEGER"))
                        intCol = (int) tuple.get(i);
                    else
                        intCol = (double) tuple.get(i);
            return evalNum(intCol, comparison, Double.parseDouble(ropValue));
        }
        else if (lopType.equals("num") && ropType.equals("col")) {
            for (int i = 0; i < attributes.size(); i++)
                if (attributes.get(i).equals(ropValue))
                    if (domains.get(i).equals("INTEGER"))
                        intCol = (int) tuple.get(i);
                    else
                        intCol = (double) tuple.get(i);
            return evalNum(Double.parseDouble(lopValue), comparison, intCol);
        }
        else if (lopType.equals("col") && ropType.equals("str")) {
            for (int i = 0; i < attributes.size(); i++)
                if (attributes.get(i).equals(lopValue))
                    stringCol = (String) tuple.get(i);
            return evalStr(stringCol, comparison, ropValue);
        }
        else if (lopType.equals("str") && ropType.equals("col")) {
            for (int i = 0; i < attributes.size(); i++)
                if (attributes.get(i).equals(lopValue))
                    stringCol = (String) tuple.get(i);
            return evalStr(lopValue, comparison, stringCol);
        }
        else if (lopType.equals("col") && ropType.equals("col")) {
            for (int i = 0; i < attributes.size(); i++)
                if (attributes.get(i).equals(lopValue))
                    stringCol = (String) tuple.get(i);
            for (int i = 0; i < attributes.size(); i++)
                if (attributes.get(i).equals(ropValue))
                    stringCol2 = (String) tuple.get(i);
            return evalStr(stringCol, comparison, stringCol2);
        }

        return false;
    }

    // Evaluates num queries
    private boolean evalNum(double lopInt, String comparison, double ropInt) {
        switch (comparison) {
            case "=" :
                return lopInt == ropInt;
            case "<>":
                return lopInt != ropInt;
            case ">" :
                return lopInt > ropInt;
            case "<" :
                return lopInt < ropInt;
            case ">=":
                return lopInt >= ropInt;
            case "<=":
                return lopInt <= ropInt;
        }

        return false;
    }

    // Evaluates string queries
    private boolean evalStr(String lopValue, String comparison, String ropValue) {
        int compareVal;

        if (comparison.equals("="))
            return lopValue.equals(ropValue);
        if (comparison.equals("<>"))
            return !lopValue.equals(ropValue);
        if (comparison.equals(">")) {
            compareVal = lopValue.compareTo(ropValue);
            if (compareVal > 0)
                return true;
            if (compareVal < 0)
                return false;
            else
                return false;
        }
        if (comparison.equals("<")) {
            compareVal = lopValue.compareTo(ropValue);
            if (compareVal > 0)
                return false;
            if (compareVal < 0)
                return true;
            else
                return false;
        }
        if (comparison.equals(">=")) {
            compareVal = lopValue.compareTo(ropValue);
            if (compareVal > 0)
                return true;
            if (compareVal < 0)
                return false;
            else
                return true;
        }
        if (comparison.equals("<=")) {
            compareVal = lopValue.compareTo(ropValue);
            if (compareVal > 0)
                return false;
            if (compareVal < 0)
                return true;
            else
                return true;
        }

        return false;
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
