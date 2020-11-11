package Models;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class Student implements Comparable<Student>
{
    private String firstName, lastName;
    private int studNum;
    private HashMap<Course, Integer> courses;

    public Student(int studNum, String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
        setStudNum(studNum);
        courses = new HashMap<>();
    }

    public String getFirstName() {
        return firstName;
    }

    /**
     * This method validates that the first name starts with an upper case letter
     * and only contains letters.
     * @param firstName
     */
    public void setFirstName(String firstName) {
        if (firstName.matches("[A-Z][A-z]*[-]?[A-z]*"))
            this.firstName = firstName;
        else
            throw new IllegalArgumentException("first name must start with upper case letter");
    }

    /**
     * returns the last name of the student
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * This method validates that the last name starts with an upper case letter and any
     * combination of letters, dashes or a space
     * @param lastName
     */
    public void setLastName(String lastName) {
        if (lastName.matches("[A-Z][A-z]*[-\\s]?[A-z]*"))
            this.lastName = lastName;
        else
            throw new IllegalArgumentException("last name must start with an upper case letter");
    }

    public int getStudNum() {
        return studNum;
    }

    /**
     * This validates that the argument is greater than 0
     * @param studNum must be greater than 0
     */
    public void setStudNum(int studNum) {
        if (studNum>0)
            this.studNum = studNum;
        else
            throw new IllegalArgumentException("student number must be greater than 0");
    }

    /**
     * The HashMap that contains the courses and marks earned
     * @return keys are the course code, the value is the grade achieved
     */
    public HashMap<Course, Integer> getCourses() {
        return courses;
    }

    /**
     * This will add a course and grade to the DataStructures.Models.Student object
     * @param course -> must be a valid Course object
     * @param gradeAchieved -> must be in the range 0-100
     */
    public void addCourse(Course course, Integer gradeAchieved) {

        if (gradeAchieved<0 || gradeAchieved > 100)
            throw new IllegalArgumentException("grade must be in the range 0-100");

        courses.put(course,gradeAchieved);
    }

    /**
     * This method returns the average grade achieved for the student
     */
    public double getAvgMark()
    {
        if (courses.size() == 0)
            return 0;
        else {
            Collection<Integer> grades = courses.values();  // Put the grades for each course into a collection
            double sum = 0;

            for (Integer grade : grades)                    // loop through each course grade
                sum += grade;                               // get the sum of all grades

            return sum / courses.size();                    // return the average of the grades
        }
    }

    public String getAvgGradeString()
    {
        return String.format("%.1f %%", getAvgMark());
    }

    public int getNumOfCourses()
    {
        return courses.size();
    }

    /**
     * returns the DataStructures.Models.Student as a String
     * @return student number-first name last name and avg grade
     */
    public String toString()
    {
        return String.format("%d-%s %s",studNum,firstName,lastName);
    }

    @Override
    public int compareTo(Student otherStudent) {
        return Integer.compare(this.getStudNum(), otherStudent.getStudNum());
    }

    public boolean contains(String searchText)
    {
        String studentNumString = Integer.toString(studNum);    // Converts integer to string
        searchText = searchText.toLowerCase();                  // make search text lowercase

        return firstName.toLowerCase().contains(searchText) ||
                lastName.toLowerCase().contains(searchText) ||
                studentNumString.contains(searchText);
    }

    /**
     * Overrides the hash function
     * @return The student number
     */
//    @Override
//    public int hashCode()
//    {
//        return studNum;
//    }
//
//    @Override
//    public boolean equals(Object object)
//    {
//        // Comparing students against each other
//        if (object.getClass().equals(Student.class))
//        {
//            Student otherStudent = (Student) object;
//            return this.getStudNum() == otherStudent.getStudNum();
//        }
//        return false;
//    }
}
