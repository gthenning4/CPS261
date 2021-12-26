import java.util.Arrays;
import java.util.Comparator;

class MySelectionSort implements Comparator<Student>{
    public int compare(Student s1, Student s2) {
        return s1.compareTo(s2);
    }
}
public class Student implements Comparable<Student>{
    private int studentID;
    private String name;
    private double gpa;
    public static int MAX_ID=1;

    public Student(String name, double gpa){
        this.studentID = MAX_ID;
        MAX_ID++;
        this.name = name;
        this.gpa = gpa;

    }
    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

//    @Override
//    public int compareTo(Student s) {
//        return this.studentID - s.studentID;
//    }
    public int compareTo(Student s) {
          int comp=0;
          int lengthComp= this.name.length() - s.getName().length();
          int length = this.name.length();
          if (length >= s.getName().length()){
              length = s.getName().length();
          }
          for (int i=0; i<length; i++){
              int diff = this.name.charAt(i) - s.getName().charAt(i);
              if (diff > 0){
                  comp = diff;
                  break;
              }
              else if(diff < 0){
                  comp = diff;
                  break;
              }
              else if(diff == 0){
                  comp=lengthComp;
                  continue;
              }
          }
          return comp;
      }
    @Override
    public String toString() {
        return "Student{" +
                "studentID=" + studentID +
                ", name='" + name + '\'' +
                ", gpa=" + gpa +
                '}';
    }



    public static void main(String[]args) {
        Student[] studArr ={
                new Student("George", 3.5),
                new Student("Michael", 2.3),
                new Student("Brittany", 3.3),
                new Student("Murphy", 4.0),
                new Student("Murph", 2.1)
        };
        studArr[2].setStudentID(14); //Used to test ID sorting
        System.out.println("Unsorted:");
        for(int i=0; i<studArr.length; i++){
            System.out.println(studArr[i]);
        }
        Arrays.sort(studArr, new MySelectionSort());

        System.out.println("Sorted:");
        for (int i=0; i<studArr.length; i++){
            System.out.println(studArr[i]);
        }


    }

}
