package me.nishnha.database;

import java.util.ArrayList;

/**
 * Created by nishnha on 11/17/16.
 */
public class Query2 {
    public static void main(String[] args) {
        Database db = new Database();
        db.initializeDatabase(args[0]);
        Relation employee = db.getRelation("EMPLOYEE");
        Relation projects = db.getRelation("PROJECTS");
        Relation department = db.getRelation("DEPARTMENT");

        ArrayList<String> d_cnames = new ArrayList<String>();
            d_cnames.add("DNAME");
            d_cnames.add("DNUM");
            d_cnames.add("SSN");
            d_cnames.add("MGRSTARTDATE");
        ArrayList<String> cnames = new ArrayList<String>();
            cnames.add("PNUMBER");
            cnames.add("DNUM");
            cnames.add("LNAME");
            cnames.add("ADDRESS");
            cnames.add("BDATE");

        Relation r1 = projects.select("col", "PLOCATION", "=", "str", "Stafford");
        Relation r2 = department.rename(d_cnames);
        Relation r3 = r1.join(r2);
        Relation r4 = r3.join(employee);
        Relation r5 = r4.project(cnames);

        System.out.println(r5);
    }
}
