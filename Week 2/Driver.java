import java.util.*;

public class Driver {
  public static void main(String args[]) {
    Database db = new Database();

    ArrayList<String> attr1 = new ArrayList<String>();
    attr1.add("SID");
    attr1.add("SNAME");
    attr1.add("MAJOR");
    attr1.add("GPA");

    ArrayList<String> dom1 = new ArrayList<String>();
    dom1.add("INTEGER");
    dom1.add("VARCHAR");
    dom1.add("VARCHAR");
    dom1.add("DECIMAL");

    Relation r1 = new Relation("STUDENT",attr1,dom1);

    Tuple t = new Tuple(attr1,dom1);
    t.addIntegerComponent(1111);
    t.addStringComponent("Robert Adams");
    t.addStringComponent("Computer Science");
    t.addDoubleComponent(4.00);
    r1.addTuple(t);

    t = new Tuple(attr1,dom1);
    t.addIntegerComponent(1112);
    t.addStringComponent("Charles Bailey");
    t.addStringComponent("Mathematics");
    t.addDoubleComponent(3.00);
    r1.addTuple(t);

    db.addRelation("STUDENT",r1);

    ArrayList<String> attr2 = new ArrayList<String>();
    attr2.add("CNUM");
    attr2.add("CTITLE");
    attr2.add("DESCRIPTION");
    attr2.add("CREDITS");

    ArrayList<String> dom2 = new ArrayList<String>();
    dom2.add("VARCHAR");
    dom2.add("VARCHAR");
    dom2.add("VARCHAR");
    dom2.add("INTEGER");

    Relation r2 = new Relation("COURSE",attr2,dom2);

    t = new Tuple(attr2,dom2);
    t.addStringComponent("CSc 1301");
    t.addStringComponent("Intro to CS I");
    t.addStringComponent("Java Programming and breadth topics");
    t.addIntegerComponent(4);
    r2.addTuple(t);

    t = new Tuple(attr2,dom2);
    t.addStringComponent("CSc 1302");
    t.addStringComponent("Intro to CS II");
    t.addStringComponent("In depth Java Programming and some breadth topics");
    t.addIntegerComponent(4);
    r2.addTuple(t);

    db.addRelation("COURSE",r2);

    System.out.println("Database Schema: \n");
    db.displaySchema();

    //System.out.println("Relation r1: \n"+r1);
    System.out.println();
    r1.displayRelation();

    //System.out.println("Relation r2: \n"+r2);
    System.out.println();
    r2.displayRelation();
  }
}
