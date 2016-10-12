import java.io.*;
import java.util.*;

public class Relation {

    // Instance variables
    private String name;
    private ArrayList<String> attributes;
    private ArrayList<String> domains;
    private ArrayList<Tuple> table;


    // Constructor
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
                        table.remove(j);
                        j--;
                }
            }
        }
    }

    // Returns true if aname matches an attribute
    public boolean attributeExists(String aname) {
        for (String s : attributes) {
            if (aname.equals(s))
                return true;
        }
        return false;
    }

    // Returns domain type of attribute aname; returns null if not present
    public String attributeType(String aname) {
        for (int i = 0; i < attributes.size(); i++) {
            if (attributes.get(i).equals(aname))
                return domains.get(i);
        }
        return null;
    }

    // Prints relational schema to screen.
    public void displaySchema() {
        for (String s : attributes)
            System.out.print(s + "");
    }

    // Sets name of relation to rname
    public void setName(String rname) {
        name = rname;
    }

    // Renames the attribute names of the relation
    public Relation rename(ArrayList<String> cname) {
        Relation renamed = new Relation(
                this.name,
                cname,
                this.domains
           );

        renamed.table.addAll(this.table);

        return renamed;
    }

    // Outputs the crossproduct of two relations
    public Relation times(Relation r2) {

        ArrayList<String> catAttr = new ArrayList<>(this.attributes);
        catAttr.addAll(r2.attributes);

        for (int i = 0; i < this.attributes.size(); i++) {
            for (int j = 0; j < r2.attributes.size(); j++) {
                if (catAttr.get(i).equals(r2.attributes.get(j))) {
                    catAttr.set(i, this.name + "." + catAttr.get(i));
                    catAttr.set(i + this.attributes.size(), r2.name + "." + catAttr.get(i + this.attributes.size()));

                }
            }
        }

        ArrayList<String> catDom = new ArrayList<>(this.domains);
        catDom.addAll(r2.domains);

        ArrayList<Tuple> catTable = new ArrayList<>();

        for (int i = 0; i < this.table.size(); i++) {
            for (int j = 0; j < r2.table.size(); j++) {

                catTable.add(
                            this.table.get(i).concatenate(
                                    r2.table.get(j),
                                    r2.attributes,
                                    r2.domains
                                )
                        );

            }
        }

        Relation crossProduct = new Relation(
                    this.name + "_TIMES_" + r2.name,
                    catAttr,
                    catDom
                );

        crossProduct.table = catTable;

        return crossProduct;
    }

    // Add tuple tup to relation; Duplicates are fine.
    public void addTuple(Tuple tup) {
        this.table.add(tup);
    }

    // Check if the tuple t is in the relation
    public boolean member(Tuple t) {
        for (Tuple compared : this.table)
            if (t.equals(compared))
                return true;
        return false;
    }

    // Display the union of two relations
    // Adds both relations and then calls removeDuplicates
    public Relation union(Relation r2) {
        Relation r3 = new Relation("Union", this.attributes, this.domains);

        for (Tuple t : this.table)
            r3.addTuple(t);

        for (Tuple t : r2.table)
            r3.addTuple(t);

        r3.removeDuplicates();

        return r3;
    }

    // Return the intersection of two relations
    public Relation intersect(Relation r2) {
        Relation r3 = new Relation("Intersection", this.attributes, this.domains);

        for (Tuple t : this.table)
            for (Tuple u : r2.table)
                if (t.equals(u))
                    r3.addTuple(t);

        return r3;
    }

    // Return the difference of A and B (A-B)
    public Relation minus(Relation r2) {
        Relation r3 = new Relation("Minus", this.attributes, this.domains);

        for (Tuple t : this.table)
            if (!r2.member(t))
                r3.addTuple(t);

        return r3;
    }

    // Return a relation with only the requested columns
    public Relation project(ArrayList<String> cnames) {
        ArrayList<Tuple> projectedTable = new ArrayList<>();
        ArrayList<String> doms = new ArrayList<>();

        for (int i = 0; i < cnames.size(); i++) {
            for (int j = 0; j < this.attributes.size(); j++) {
                if (cnames.get(i).equals(this.attributes.get(j))) {
                    doms.add(this.domains.get(j));
                    for (int k = 0; k < table.size(); k++) {
                        projectedTable.add(table.get(k).project(cnames));
                    }

                }

            }
        }


        Relation project = new Relation(
                    "PROJECT_" + this.name,
                    cnames,
                    doms
                );
        project.table = projectedTable;
        project.removeDuplicates();

        return project;
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
