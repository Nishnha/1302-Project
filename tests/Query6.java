package me.nishnha.database;

import java.util.ArrayList;

public class Query6 {
    public static void main(String[] args) {
            Database db = new Database();
            db.initializeDatabase(args[0]);
            Relation r1 = db.getRelation("EMPLOYEE");
            Relation r2 = db.getRelation("DEPENDENT");

            ArrayList<String> ssn = new ArrayList<String>();
                ssn.add("SSN");
            ArrayList<String> essn = new ArrayList<String>();
                essn.add("ESSN");

            ArrayList<String> cnames = new ArrayList<String>();
                cnames.add("LNAME");
                cnames.add("FNAME");

            Relation rx = r1.project(ssn);
            Relation ry = r2.project(essn);
            ry = ry.rename(ssn);

            Relation r3 = rx.minus(ry);
            Relation r4 = r3.join(r1);
            Relation r5 = r4.project(cnames);

            r5.setName("ANSWER");
            System.out.println(r5);
        }
}
