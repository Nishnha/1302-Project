package me.nishnha.database;

/**
 * Created by nishnha on 11/17/16.
 */
public class Query3 {
    public static void main(String[] args) {
        Database db = new Database();
        db.initializeDatabase(args[0]);
        Relation r1 = db.getRelation("EMPLOYEE");
        Relation r2 = db.getRelation("DEPARTMENT");

    }
}
