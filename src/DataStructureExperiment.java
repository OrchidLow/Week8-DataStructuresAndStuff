import Models.Course;
import Models.Student;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;

public class DataStructureExperiment {
    public static void main(String[] args) {

        String[] names = {"Fred", "Barney", "wilma"};

        for (int i = 0; i < names.length; i++)
            System.out.println(names[i]);

        ArrayList namesAL = new ArrayList();
        namesAL.add("Fred");
        namesAL.add(new Student(10001, "Why", "Ugh"));
        namesAL.add(new Course("COMP 1113", "Java 3"));

        ArrayList<Student> studentAL = new ArrayList<>();
        Student st1 = new Student(10001, "Geralt", "Rivia");
        Student st2 = new Student(10002, "Yennifer", "Vengaberg");
        Student st3 = new Student(10003, "Sova", "Snow");
        Student st4 = new Student(10004, "Ciri", "Cintra");

        studentAL.add(st1);
        studentAL.addAll(Arrays.asList(st2, st3, st4));


        ArrayList<String> stringAl = new ArrayList<>();
        stringAl.add(st1.toString());
        stringAl.add(st2.toString());
        stringAl.add(st3.toString());
        stringAl.add(st4.toString());

        System.out.println("The list");
        // Type of object Name of variable : ArrayList
        for (String string:stringAl)
            System.out.println(string);

        //studentAL.remove(st4);

        studentAL.add(st1);

        System.out.println("\nThis is the student array list");
        for (Student student: studentAL)
            System.out.println(student + " || Hashcode: " + student.hashCode());

        // Sets can not have duplicates
        HashSet<Student> studentHS = new HashSet<>();
        studentHS.add(st1);
        studentHS.addAll(studentAL);

        Student st5 = new Student(10001, "Geralt", "Rivia");

        studentHS.add(st5);

        // Returns an unordered set
        System.out.println("\nPrinting the Hashset");
        for (Student student: studentHS)
            System.out.println(student);

        // Must be comparable and is ordered
        TreeSet<Student> studentTS = new TreeSet<>();
        studentTS.addAll(studentAL);

        // Returns student tree set, in specified order. Need to implement comparable in student model
        System.out.println("\nPrinting the tree set");
        for (Student student: studentTS)
            System.out.println(student);

        st1.addCourse(new Course("COMP 1011", "Life"), 99);

    }
}
