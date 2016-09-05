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
        if (relations.containsKey(rname))
            return true;
        return false;
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
            System.out.println(r);
  }

}
