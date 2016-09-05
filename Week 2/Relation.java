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

  // Print relation to screen (see output of run for formatting)
  public void displayRelation() {
        System.out.print(this.name + "(");
        for (int i = 0; i < attributes.size(); i++) {
            System.out.print(attributes.get(i) + ":" + domains.get(i));
            // Don't add a comma on the last key, value pair
                if (i < attributes.size() - 1)
                    System.out.print(",");

        }
        System.out.print(")");
        System.out.print("\nNumber of Tuples: " + table.size() + "\n");
        for (Tuple t : table)
            System.out.println(t);

  }

  // Return String version of relation; See output of run for format.
  public String toString() {
        String output = this.name + "(";
        for (int i = 0; i < attributes.size(); i++) {
            output = output + attributes.get(i) + ":" + domains.get(i);
                // Don't add a comma on the last key, value pair
                if (i < attributes.size() - 1)
                    output = output + ",";
        }
        return output + ")";
  }

}
