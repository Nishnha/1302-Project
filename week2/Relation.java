import java.io.*;
import java.util.*;

public class Relation {
    // Name of the relation.
    private String name;

    // Attribute names for the relation
    private ArrayList<String> attributes;

    // Domain classes or types of attributes; possible values: INTEGER, DECIMAL, VARCHAR
    private ArrayList<String> domains;

    // Actual data storage (list of tuples) for the relation.
    private ArrayList<Tuple> table;

    // METHODS

    // Constructor; set instance variables
    public Relation (String name, ArrayList<String> attributes, ArrayList<String> dNames) {
        this.name = name;
        this.attributes = attributes;
        this.domains = dNames;
        table = new ArrayList<Tuple>();
    };

    // Remove duplicate tuples from this relation
    public void removeDuplicates() {
        for (int i = 0; i < table.size() - 1; i++) {
            for (int j = i + 1; j < table.size(); j++) {
                if (table.get(i).equals(table.get(j))) {
                        table.remove(i);
                        // Move i back one because the old elements of the arraylist are moved over one
                        i--;
                }
            }
        }
    }

    // returns true if attribute with name aname exists in relation schema
    // false otherwise
    public boolean attributeExists(String aname) {
        for (String s : attributes) {
            if (aname.equals(s))
                return true;
        }
        return false;
    }


    // returns domain type of attribute aname; return null if not present
    public String attributeType(String aname) {
        for (int i = 0; i < attributes.size(); i++) {
            if (attributes.get(i).equals(aname))
                return domains.get(i);
        }
        return null;
    }

    // Print relational schema to screen.
    public void displaySchema() {
        for (String s : attributes)
            System.out.print(s + "");
    }

    // Set name of relation to rname
    public void setName(String rname) {
        name = rname;
    }

    // Add tuple tup to relation; Duplicates are fine.
    public void addTuple(Tuple tup) {
        table.add(tup);
    }

    // Print relation to screen
    public void displayRelation() {
        System.out.print(this.name + "(");
        for (int i = 0; i < attributes.size(); i++) {
            System.out.print(attributes.get(i) + ":" + domains.get(i));
            // don't add a comma on the last key value pair
            if (i < attributes.size() - 1)
                System.out.print(",");
        }
        System.out.println(")");
    }

    // New toString that works as intended (copy the output of displayRelation)
    public String toString() {
        String output = new String(this.name + "(");
        for (int i = 0; i < attributes.size(); i++) {
            output = output + attributes.get(i) + ":" + domains.get(i);
            // Don't add a comma on the last key, value pair
                if (i < attributes.size() - 1)
                    output = output + (",");
        }

        output = output + (")");
        output = output + "\nNumber of Tuples: " + table.size() + "\n\n";
        for (Tuple t : table)
            output = output + t.toString() + "\n";
        return output;
    }

}
