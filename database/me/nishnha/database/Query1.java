package me.nishnha.database;

import javax.print.DocFlavor;
import java.util.ArrayList;

/**
 * Created by nishnha on 11/13/16.
 */
public class Query1 {
    public static void main(String[] args) {
        Database db = new Database();
        db.initializeDatabase(args[0]);
        Relation r1 = db.getRelation("EMPLOYEE");
        Relation r2 = db.getRelation("DEPARTMENT");

        ArrayList<String> cnames = new ArrayList<String>() {{
            add("FNAME");
            add("LNAME");
            add("ADDRESS");
        }};
        Relation r3 = r2.project(cnames);
        System.out.println(r1);
        System.out.println(r3);
    }
}
