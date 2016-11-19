package me.nishnha.database;

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

//        System.out.println(r1);
//        System.out.println(r2);

        Relation r3 = r1.select("col", "DNO", "=", "num", "5");

        ArrayList<String> cnames = new ArrayList<String>();
            cnames.add("FNAME");
            cnames.add("LNAME");
            cnames.add("ADDRESS");

        Relation r4 = r3.project(cnames);
        r4.setName("ANSWER");
        System.out.println(r4);
    }
}
