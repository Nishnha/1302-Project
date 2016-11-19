package me.nishnha.database;

import java.io.*;
import java.util.*;

public class Database {

    private Map<String,Relation> relations;

    // METHODS

    // Constructor; creates the empty HashMap object
    public Database() {
        relations = new HashMap<String, Relation>();
    }

    // Add relation r with name rname to HashMap
    // if relation does not already exists.
    // Make sure to set the name within r to rname.
    // return true on successful add; false otherwise
    public boolean addRelation(String rname, Relation r) {
        if (relations.containsKey(rname) == true || relations.containsValue(r) == true)
            return false;
        relations.put(rname, r);
        return true;
    }

    // Create the database object by reading data from several files in directory dir
    public void initializeDatabase(String dir) {
        try {
            // Create a new FileInputStream and BufferedReader to parse through catalog.dat
            FileInputStream file = new FileInputStream(dir + "/catalog.dat");
            BufferedReader inFile = new BufferedReader(new InputStreamReader(file));

            // Read the first line of catalog.dat to find how many relations need to be made
            int numLoops = Integer.parseInt(inFile.readLine());

            for (int i = 0; i < numLoops; i++) {
                String name = inFile.readLine();
                int numAttr = Integer.parseInt(inFile.readLine());

                ArrayList<String> attr = new ArrayList<String>();
                ArrayList<String> dom = new ArrayList<String>();

                for (int j = 0; j < numAttr; j++) {
                    attr.add(inFile.readLine());
                    dom.add(inFile.readLine());
                }

                Relation r = new Relation(name, attr, dom);

                FileInputStream file2 = new FileInputStream(dir + "/" + name + ".dat");
                BufferedReader inFile2 = new BufferedReader(new InputStreamReader(file2));
                int numTuples = Integer.parseInt(inFile2.readLine());

                for (int j = 0; j < numTuples; j++) {

                    Tuple t = new Tuple(attr, dom);

                    for (String s : dom) {
                        switch (s) {
                            case "VARCHAR":
                                t.addStringComponent(inFile2.readLine());
                                break;
                            case "DECIMAL":
                                t.addDoubleComponent(Double.parseDouble(inFile2.readLine()));
                                break;
                            case "INTEGER":
                                t.addIntegerComponent(Integer.parseInt(inFile2.readLine()));
                                break;
                        }
                    }

                    // Add tuple t to relation r
                    r.addTuple(t);
                }
                // Close the inner file
                file2.close();
                // Add relation r to the database
                this.addRelation(name, r);
            }
            // Close the outer file
            file.close();
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
    }


    // Delete relation with name rname from HashMap
    // if relation exists. return true on successful delete; false otherwise
    public boolean deleteRelation(String rname) {
        if (relations.containsKey(rname)) {
            relations.remove(rname);
            return true;
        }
        return false;
    }

    // Return true if relation with name rname exists in HashMap
    // false otherwise
    public boolean relationExists(String rname) {
        return relations.containsKey(rname);
    }

    // Retrieve and return relation with name rname from HashMap;
    // return null if it does not exist.
    public Relation getRelation (String rname) {
        if (relationExists(rname))
            return relations.get(rname);
        return null;
    }

    // Print database schema to screen.
    public void displaySchema() {
        for (Relation r : relations.values())
            r.displayRelation();
        System.out.println();
    }

}
