// Giovanni Acireale

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class IdException extends Exception {
    public IdException(String message) {
        super(message);
    }
}

class Course {
    private int crn;
    private String prefix;
    private String title;
    private String type;
    private String modality;
    private String location;
    private boolean hasLab;
    private int creditHours;

    public Course(int crn, String prefix, String title, String type, String modality, String location, boolean hasLab, int creditHours) {
        this.crn = crn;
        this.prefix = prefix;
        this.title = title;
        this.type = type;
        this.modality = modality;
        this.location = location;
        this.hasLab = hasLab;
        this.creditHours = creditHours;
    }

    public int getCrn() {
        return crn;
    }

    public String getTitle() {
        return title;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getType() {
        return type;
    }

    public String getModality() {
        return modality;
    }

    public String getLocation() {
        return location;
    }

    public boolean hasLab() {
        return hasLab;
    }
}

class Labs {
    private int crn;
    private String location;

    public Labs(int crn, String location) {
        this.crn = crn;
        this.location = location;
    }

    public int getCrn() {
        return crn;
    }

    public String getLocation() {
        return location;
    }
}

abstract class Student {
    private String id;
    private String name;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    abstract public void printInvoice();
}

class UndergraduateStudent extends Student {
    private List<Integer> crnsTaken;
    private double gpa;
    private boolean resident;

    public UndergraduateStudent(String id, String name, List<Integer> crnsTaken, double gpa, boolean resident) {
        super(id, name);
        this.crnsTaken = crnsTaken;
        this.gpa = gpa;
        this.resident = resident;
    }

    @Override
    public void printInvoice() {
        System.out.println("VALENCE COLLEGE\nORLANDO FL 10101");
        System.out.println("\t\t---------------------");
        System.out.println("Fee Invoice Prepared for Student:");
        System.out.println(getId() + "-" + getName() + " (" + (resident ? "FL resident" : "Non-resident") + ", GPA: " + gpa + ")");
        System.out.println("\n1 Credit Hour = $" + 120.25 + "\n");
        System.out.println("\tCRN\tCR_PREFIX\tCR_HOURS\t\tAMOUNT");
        double totalTuition = 0;
        for (int crn : crnsTaken) {
            for (Course course : ProjectDriver.getCourses()) {
                if (course.getCrn() == crn) {
                    double courseCost = course.getCreditHours() * 120.25;
                    totalTuition += courseCost;
                    System.out.printf("\t%d\t%s\t%d\t\t$ %.2f\n", crn, course.getTitle(), course.getCreditHours(), courseCost);
                }
            }
        }
        System.out.println("\n\tHealth & ID Fees\t$ 35.00");
        System.out.println("--------------------------------------");
        double totalPayments = totalTuition + 35.00;
        System.out.printf("\t\t\t\t$ %.2f\n", totalPayments);
        if (gpa >= 3.5 && totalPayments > 500) {
            double discount = totalPayments * 0.25;
            System.out.printf("\t\t\t\t -$ %.2f\n", discount);
            System.out.println("\t\t\t\t----------");
            System.out.printf("\tTOTAL PAYMENTS \t\t$ %.2f\n", totalPayments - discount);
        } else {
            System.out.printf("\tTOTAL PAYMENTS \t\t$ %.2f\n", totalPayments);
        }
        System.out.println("\n----------------------------------------\n");
    }
}

abstract class GraduateStudent extends Student {
    public GraduateStudent(String id, String name) {
        super(id, name);
    }
}

class MsStudent extends GraduateStudent {
    private List<Integer> coursesTaken;

    public MsStudent(String id, String name, List<Integer> coursesTaken) {
        super(id, name);
        this.coursesTaken = coursesTaken;
    }

    @Override
    public void printInvoice() {
        System.out.println("VALENCE COLLEGE\nORLANDO FL 10101");
        System.out.println("\t\t---------------------");
        System.out.println("Fee Invoice Prepared for Student:");
        System.out.println(getId() + "-" + getName());
        System.out.println("\n1 Credit Hour = $300.00\n");
        System.out.println("\tCRN\tCR_PREFIX\tCR_HOURS\t\tAMOUNT");
        double totalPayments = 0;
        for (int crn : coursesTaken) {
            for (Course course : ProjectDriver.getCourses()) {
                if (course.getCrn() == crn) {
                    double courseCost = course.getCreditHours() * 300.00;
                    totalPayments += courseCost;
                    System.out.printf("\t%d\t%s\t%d\t\t$ %.2f\n", crn, course.getTitle(), course.getCreditHours(), courseCost);
                }
            }
        }
        totalPayments += 35.00;
        System.out.println("\n\tHealth & ID Fees\t$ 35.00");
        System.out.println("--------------------------------------");
        System.out.printf("\tTOTAL PAYMENTS \t\t$ %.2f\n", totalPayments);
        System.out.println("\n----------------------------------------\n");
    }
}

class PhdStudent extends GraduateStudent {
    private String advisor;
    private String researchSubject;
    private List<String> supervisedLabs;

    public PhdStudent(String id, String name, String advisor, String researchSubject, List<String> supervisedLabs) {
        super(id, name);
        this.advisor = advisor;
        this.researchSubject = researchSubject;
        this.supervisedLabs = supervisedLabs;
    }

    @Override
    public void printInvoice() {
        System.out.println("VALENCE COLLEGE\nORLANDO FL 10101");
        System.out.println("\t\t---------------------");
        System.out.println("Fee Invoice Prepared for Student:");
        System.out.println(getId() + "-" + getName() + "\n" + getName() + "’s research subject: " + researchSubject + ". " + getName() + " supervises three labs or more.");
        double researchFee = 700.00;
        double healthAndIdFees = 35.00;
        double totalPayments = researchFee + healthAndIdFees;
        System.out.println("\t\tRESEARCH");
        System.out.printf("\t%s\t\t$ %.2f\n", researchSubject, -researchFee);
        if (supervisedLabs.size() >= 3) {
            totalPayments = healthAndIdFees;
        } else if (supervisedLabs.size() == 2) {
            totalPayments -= researchFee / 2;
        }
        System.out.printf("\tHealth & id fees\t$ %.2f\n", healthAndIdFees);
        System.out.println("--------------------------------------");
        System.out.printf("\tTotal Payments\t$ %.2f\n", totalPayments);
        System.out.println("\n----------------------------------------\n");
    }
}

public class ProjectDriver {
    public static ArrayList<Course> courses = new ArrayList<>();
    public static ArrayList<Labs> labs = new ArrayList<>();
    public static ArrayList<Student> students = new ArrayList<>();

    public static List<Course> getCourses() {
        return courses;
    }

    public static void main(String[] args) {
        loadCoursesAndLabs();
        Scanner scanner = new Scanner(System.in);
        String choice;
        do {
            System.out.println("----------------------------------------\n");
            System.out.println("Main Menu\n");
            System.out.println("1 : Student Management");
            System.out.println("2 : Course Management");
            System.out.println("0 : Exit");
            System.out.print("\n\tEnter your selection: ");
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    manageStudents(scanner);
                    break;
                case "2":
                    manageCourses(scanner);
                    break;
                case "0":
                    System.out.println("Take Care!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        } while (!choice.equals("0"));
        scanner.close();
    }

    private static void manageStudents(Scanner scanner) {
        String selection;
        do {
            System.out.println("----------------------------------------\n");
            System.out.println("Student Management Menu:\n");
            System.out.println("Choose one of:\n");
            System.out.println("\tA - Add a student");
            System.out.println("\tB - Delete a Student");
            System.out.println("\tC - Print Fee Invoice");
            System.out.println("\tD - Print List of Students");
            System.out.println("\tX - Back to Main Menu");
            System.out.print("\nEnter your selection: ");
            selection = scanner.nextLine();
            switch (selection.toUpperCase()) {
                case "A":
                    addStudent(scanner);
                    break;
                case "B":
                    deleteStudent(scanner);
                    break;
                case "C":
                    printStudentInvoice(scanner);
                    break;
                case "D":
                    printStudents();
                    break;
                case "X":
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.");
                    break;
            }
        } while (!selection.equalsIgnoreCase("X"));
    }

    private static void manageCourses(Scanner scanner) {
        String selection;
        do {
            System.out.println("----------------------------------------\n");
            System.out.println("Course Management Menu:\n");
            System.out.println("\tA - Search for a class or lab");
            System.out.println("\tB - Delete a class");
            System.out.println("\tC - Add a lab to a class");
            System.out.println("\tX - Back to Main Menu");
            System.out.print("\nEnter your selection: ");
            selection = scanner.nextLine().toUpperCase();

            switch (selection) {
                case "A":
                    searchClass(scanner);
                    break;
                case "B":
                    deleteClass(scanner);
                    break;
                case "C":
                    addLabToClass(scanner);
                    break;
                case "X":
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.");
                    break;
            }
        } while (!selection.equals("X"));
    }

    private static void searchClass(Scanner scanner) {
        System.out.print("Enter the Class/Lab Number: ");
        int number = scanner.nextInt();
        scanner.nextLine();
        boolean found = false;
        for (Course course : courses) {
            if (course.getCrn() == number) {
                System.out.println("Found Course: " + course.getTitle());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No course found with CRN: " + number);
        }
    }

    private static void deleteClass(Scanner scanner) {
        System.out.print("Enter the Class Number to delete: ");
        int number = scanner.nextInt();
        scanner.nextLine();
        boolean isRemovedCourse = courses.removeIf(course -> course.getCrn() == number);
        boolean isRemovedLab = labs.removeIf(lab -> lab.getCrn() == number);
        if (isRemovedCourse || isRemovedLab) {
            System.out.println("Class and associated labs deleted successfully.");
            updateLectFile(); // Save changes to the file
        } else {
            System.out.println("No class found with CRN: " + number);
        }
    }
    
    private static void addLabToClass(Scanner scanner) {
        System.out.print("Enter the Class Number to add a lab to: ");
        int classNumber = scanner.nextInt();
        scanner.nextLine();
        boolean classExists = false;
        for (Course course : courses) {
            if (course.getCrn() == classNumber) {
                classExists = true;
                break;
            }
        }
        if (classExists) {
            System.out.print("Enter Lab Location: ");
            String location = scanner.nextLine();
            labs.add(new Labs(classNumber, location));
            System.out.println("Lab added successfully to class " + classNumber);
            updateLectFile(); // Save changes to the file
        } else {
            System.out.println("No class found with CRN: " + classNumber);
        }
    }

    private static void printStudentInvoice(Scanner scanner) {
        System.out.print("Enter Student ID to print the invoice: ");
        String id = scanner.nextLine();
        boolean found = false;
        for (Student student : students) {
            if (student.getId().equals(id)) {
                System.out.println("Printing invoice for: " + student.getName());
                student.printInvoice();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Student not found with ID: " + id);
        }
    }

    private static void printStudents() {
        System.out.println("PhD Students:");
        students.stream().filter(s -> s instanceof PhdStudent).forEach(s -> System.out.println("- " + s.getName()));
        System.out.println("\nMS Students:");
        students.stream().filter(s -> s instanceof MsStudent).forEach(s -> System.out.println("- " + s.getName()));
        System.out.println("\nUndergraduate Students:");
        students.stream().filter(s -> s instanceof UndergraduateStudent).forEach(s -> System.out.println("- " + s.getName()));
    }

    private static void addStudent(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Student Type (PhD, MS, or Undergrad): ");
        String type = scanner.nextLine();
        try {
            switch (type.toUpperCase()) {
                case "UNDERGRAD":
                    System.out.print("Enter GPA: ");
                    double gpa = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline left-over
                    System.out.print("Resident (true/false): ");
                    boolean resident = scanner.nextBoolean();
                    scanner.nextLine(); // Consume newline left-over
                    System.out.print("Enter number of courses taken: ");
                    int numCourses = scanner.nextInt();
                    scanner.nextLine(); // Consume newline left-over
                    List<Integer> crnsTaken = new ArrayList<>();
                    for (int i = 0; i < numCourses; i++) {
                        System.out.print("Enter CRN for course " + (i + 1) + ": ");
                        int crn = scanner.nextInt();
                        scanner.nextLine(); // Consume newline left-over
                        crnsTaken.add(crn);
                    }
                    students.add(new UndergraduateStudent(id, name, crnsTaken, gpa, resident));
                    break;
                case "MS":
                    List<Integer> coursesTaken = new ArrayList<>();
                    System.out.print("Enter number of courses taken: ");
                    numCourses = scanner.nextInt();
                    scanner.nextLine(); // Consume newline left-over
                    for (int i = 0; i < numCourses; i++) {
                        System.out.print("Enter CRN for course " + (i + 1) + ": ");
                        int crn = scanner.nextInt();
                        scanner.nextLine(); // Consume newline left-over
                        coursesTaken.add(crn);
                    }
                    students.add(new MsStudent(id, name, coursesTaken));
                    break;
                case "PHD":
                    System.out.print("Enter Advisor: ");
                    String advisor = scanner.nextLine();
                    System.out.print("Enter Research Subject: ");
                    String researchSubject = scanner.nextLine();
                    System.out.print("Enter number of labs supervised: ");
                    int numLabs = scanner.nextInt();
                    scanner.nextLine(); // Consume newline left-over
                    List<String> supervisedLabs = new ArrayList<>();
                    for (int i = 0; i < numLabs; i++) {
                        System.out.print("Enter lab location for lab " + (i + 1) + ": ");
                        String location = scanner.nextLine();
                        supervisedLabs.add(location);
                    }
                    students.add(new PhdStudent(id, name, advisor, researchSubject, supervisedLabs));
                    break;
                default:
                    System.out.println("Invalid student type. Please try again.");
                    break;
            }
        } catch (java.util.InputMismatchException ex) {
            System.out.println("Invalid input. Please try again.");
            scanner.next(); // Clear buffer
        }
    }

    private static void deleteStudent(Scanner scanner) {
        System.out.print("Enter Student ID to delete: ");
        String id = scanner.nextLine();
        boolean isRemoved = students.removeIf(s -> s.getId().equals(id));
        if (isRemoved) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("No student found with ID: " + id);
        }
    }

    private static void loadCoursesAndLabs() {
        try (BufferedReader reader = new BufferedReader(new FileReader("lect.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 8) {
                    int crn = Integer.parseInt(parts[0]);
                    String prefix = parts[1];
                    String title = parts[2];
                    String type = parts[3];
                    String modality = parts[4];
                    String location = parts[5];
                    boolean hasLab = parts[6].equalsIgnoreCase("YES");
                    int creditHours = Integer.parseInt(parts[7]);
                    Course course = new Course(crn, prefix, title, type, modality, location, hasLab, creditHours);
                    courses.add(course);
                    if (hasLab) {
                        for (int i = 8; i < parts.length; i += 2) {
                            int labCrn = Integer.parseInt(parts[i]);
                            String labLocation = parts[i + 1];
                            labs.add(new Labs(labCrn, labLocation));
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to load courses and labs: " + e.getMessage());
        }
    }

    private static void updateLectFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("lect.txt"))) {
            for (Course course : courses) {
                writer.printf("%d,%s,%s,%s,%s,%s,%s,%d\n",
                    course.getCrn(),
                    course.getPrefix(),
                    course.getTitle(),
                    course.getType(),
                    course.getModality(),
                    course.getLocation(),
                    course.hasLab() ? "Yes" : "No",
                    course.getCreditHours()
                );
                if (course.hasLab()) {
                    for (Labs lab : labs) {
                        if (lab.getCrn() == course.getCrn()) {
                            writer.printf("%d,%s\n",
                                lab.getCrn(),
                                lab.getLocation());
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing to lect.txt: " + e.getMessage());
        }
    }
}