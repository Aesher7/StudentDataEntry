import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class Student {
    private String name;
    private String address;
    private double GPA;

    public Student(String name, String address, double GPA) {
        this.name = name;
        this.address = address;
        this.GPA = GPA;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public double getGPA() {
        return GPA;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nAddress: " + address + "\nGPA: " + GPA + "\n";
    }
}



public class StudentDataEntry {
    public static void main(String[] args) {
        List<Student> studentList = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);

        // Prompt user for student data
        while (true) {
            System.out.println("Enter student data (or type 'done' to finish):");
            System.out.print("Name: ");
            String name = scanner.nextLine();

            if (name.equalsIgnoreCase("done")) {
                break; // Exit loop if user types 'done'
            }

            System.out.print("Address: ");
            String address = scanner.nextLine();

            double GPA = getValidatedGPA();

            studentList.add(new Student(name, address, GPA));
        }

        // Sort the student list by name in ascending order
        Collections.sort(studentList, (s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()));

        // Output contents to a text file
        writeToFile(studentList);

        System.out.println("Student data has been written to the file.");
    }

    private static double getValidatedGPA() {
        Scanner scanner = new Scanner(System.in);
        double GPA;

        while (true) {
            System.out.print("GPA: ");
            try {
                GPA = scanner.nextDouble();
                if (GPA >= 0 && GPA <= 4.0) {
                    break;
                } else {
                    System.out.println("GPA must be between 0 and 4.0. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid numeric GPA.");
                scanner.nextLine(); // Consume the invalid input
            }
        }

        return GPA;
    }

    private static void writeToFile(List<Student> studentList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("student_data.txt"))) {
            for (Student student : studentList) {
                writer.write(student.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}
