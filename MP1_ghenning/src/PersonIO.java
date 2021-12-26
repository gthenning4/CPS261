import java.io.*;
import java.util.Scanner;

class Person implements Serializable {
    String name;
    Integer age;
    public Person(String name, Integer age){
        super();
        this.name=name;
        this.age=age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
class PersonIO{
    static void addPerson(Person newPerson, ObjectOutputStream oos){
        try {
            oos.writeObject(newPerson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        ObjectOutputStream oos= null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("objOut.dat"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean done =false;
        String menu ="Please choose an option \n0: quit\n1: add\n2: display";
        while(!done){
            System.out.println(menu);
            int input = keyboard.nextInt();
            if(input==0){
                System.out.println("Gooodbye!");
                done =true;
            }
            if(input == 1){
                System.out.println("Choose a name:");
                String name = keyboard.next();
                System.out.println("Choose an age:");
                Integer age = keyboard.nextInt();
                Person newPerson = new Person (name,age);
                addPerson(newPerson,oos);
            }
            if(input ==2){
                ObjectInputStream ois = null;
                try {
                    System.out.println("***************************");
                    ois = new ObjectInputStream(new FileInputStream("objOut.dat"));
                    boolean eof=false;
                    while(!eof){
                        try{System.out.println((Person)ois.readObject());}
                        catch(EOFException e){
                            eof=true;
                        }
                    }
                } catch (IOException | ClassNotFoundException e ) {
                    e.printStackTrace();
                }
                System.out.println("***************************");
            }
        }
    }
}
