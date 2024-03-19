import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class DriverClass {
    private static College valenceCollege;

    private static String mainMenu(Scanner scanner) {
        System.out.println("Choose from the following options:");
        System.out.println("    1- Add a new student");
        System.out.println("    2- Add/Delete a course");
        System.out.println("    3- Search for a student");
        System.out.println("    4- Print fee invoice");
        System.out.println("    5- Print fee invoice sorted by crn");
        System.out.println("    0- Exit program\n");
        System.out.print("Enter your selection: ");
        return scanner.nextLine();
    }

    private static String subMenu(Scanner scanner, Student student) {
        System.out.println("Here are the courses [" + student.getStudentName() + "] is taking:");
        System.out.println("CRN PREFIX CR. HOURS");
        for (Integer crn : student.getListOfCrns()) {
            System.out.println(crn);
        }
        System.out.println("Choose from:");
        System.out.println("A- Add a new course for [" + student.getStudentName() + "]");
        System.out.println("D- Delete a course from [" + student.getStudentName() + "]'s schedule");
        System.out.println("C- Cancel operation");
        System.out.print("Enter your selection: ");
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        valenceCollege = new College();
        String choice;

        do {
            choice = mainMenu(scanner);

            switch (choice) {
                case "1":
                    // Add a new student
                    System.out.print("Enter the student's id: ");
                    int studentId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter student's name: ");
                    String studentName = scanner.nextLine();
                    System.out.print("Enter how many courses " + studentName + " is taking? ");
                    int numCourses = Integer.parseInt(scanner.nextLine());
                    ArrayList<Integer> listOfCrns = new ArrayList<>();
                    System.out.println("Enter the " + numCourses + " course numbers");
                    String[] courseNumbersInput = scanner.nextLine().split(" ");
                    for (String courseNumber : courseNumbersInput) {
                        listOfCrns.add(Integer.parseInt(courseNumber));
                    }
                    System.out.print("Enter " + studentName + "'s current gpa: ");
                    double gpa = Double.parseDouble(scanner.nextLine());
                    if (gpa < 0 || gpa > 4) {
                        System.out.println("Invalid GPA. Please enter a value between 0 and 4.");
                        break;
                    }
                    if (gpa >= 3.5) {
                        System.out.println("Congratulations! " + studentName + " is eligible for a 25% discount.");
                    } else {
                        System.out.println("Sorry! " + studentName + " is not eligible for a discount.");
                    }
                
                    Student student = new Student(studentName, studentId, gpa, listOfCrns);
                    valenceCollege.enrollStudent(student);
                    System.out.println("Student added successfully!");
                    break;
                case "2":
                    // Add/Delete a course
                    System.out.print("Enter the student's id: ");
                    int studentId2 = Integer.parseInt(scanner.nextLine());
                    Student foundStudent = valenceCollege.searchById(studentId2);
                    if (foundStudent != null) {
                        String subChoice = subMenu(scanner, foundStudent);
                        if (subChoice.equalsIgnoreCase("A")) {
                            // Add a new course
                            System.out.print("Enter course Number to add: ");
                            int crnToAdd = Integer.parseInt(scanner.nextLine());
                            boolean added = valenceCollege.addCourse(studentId2, crnToAdd);
                            if (added) {
                                System.out.println("Course added successfully!");
                                valenceCollege.printInvoice(studentId2);
                            } else {
                                System.out.println("Failed to add course.");
                            }
                        } else if (subChoice.equalsIgnoreCase("D")) {
                            // Delete a course
                            System.out.print("Enter course Number to delete: ");
                            int crnToDelete = Integer.parseInt(scanner.nextLine());
                            boolean deleted = valenceCollege.deleteCourse(studentId2, crnToDelete);
                            if (deleted) {
                                System.out.println("Course deleted successfully!");
                                valenceCollege.printInvoice(studentId2);
                            } else {
                                System.out.println("Failed to delete course.");
                            }
                        } else {
                            System.out.println("Operation canceled.");
                        }
                    } else {
                        System.out.println("Student not found!");
                    }
                    break;
                case "3":
                    // Search for a student
                    System.out.print("Enter the student's id: ");
                    int searchId = Integer.parseInt(scanner.nextLine());
                    foundStudent = valenceCollege.searchById(searchId);
                    if (foundStudent != null) {
                        // A student with the specified ID was found
                        // Proceed with the rest of your code, such as printing the student's information
                        System.out.println("\nStudent found: " + foundStudent.getStudentName() + "\n");
                        // You can also perform other operations with the found student
                    } else {
                        // No student with the specified ID was found
                        System.out.println("\nNo Student found!\n");
                    }                    break;
                case "4":
                    // Print fee invoice
                    System.out.print("Enter the student's id: ");
                    int invoiceId = Integer.parseInt(scanner.nextLine());
                    valenceCollege.printInvoice(invoiceId);
                    break;
                case "5":
                    // Print fee invoice sorted by crn
                    System.out.print("Enter the student's id: ");
                    int sortedInvoiceId = Integer.parseInt(scanner.nextLine());
                    valenceCollege.printSortedInvoice(sortedInvoiceId);
                    break;
                case "0":
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter again.");
            }
        } while (!choice.equals("0"));

        scanner.close();
    }
}

class Student {
    private int studentId;
    private String studentName;
    private double gpa;
    private ArrayList<Integer> listOfCrns;

    public Student(String studentName, int studentId, double gpa, ArrayList<Integer> listOfCrns) {
        this.studentName = studentName;
        this.studentId = studentId;
        this.gpa = gpa;
        this.listOfCrns = listOfCrns;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public ArrayList<Integer> getListOfCrns() {
        return listOfCrns;
    }

    public void setListOfCrns(ArrayList<Integer> listOfCrns) {
        this.listOfCrns = listOfCrns;
    }

    private double getCourseCost(int crn) {
        switch (crn) {
            case 4587:
                return 4 * 120.25;
            case 4599:
            case 2599:
                return 3 * 120.25;
            case 8997:
            case 4580:
            case 1997:
                return 1 * 120.25;
            case 9696:
                return 3 * 120.25;
            case 3696:
                return 2 * 120.25;
            default:
                return 0;
        }
    }

    public void printFeeInvoice() {
        System.out.println("VALENCE COLLEGE");
        System.out.println("ORLANDO FL 10101");
        System.out.println("---------------------");
        System.out.println("Fee Invoice Prepared for Student:");
        System.out.println(studentId + "-" + studentName);
        System.out.println("1 Credit Hour = $120.25");
        System.out.println("CRN CR_PREFIX CR_HOURS");
        double totalPayment = 0;
        for (Integer crn : listOfCrns) {
            System.out.printf("%d ", crn);
            switch (crn) {
                case 4587:
                    System.out.print("MAT 236 4 ");
                    totalPayment += 4 * 120.25;
                    break;
                case 4599:
                    System.out.print("COP 220 3 ");
                    totalPayment += 3 * 120.25;
                    break;
                case 8997:
                    System.out.print("GOL 124 1 ");
                    totalPayment += 1 * 120.25;
                    break;
                case 9696:
                    System.out.print("COP 100 3 ");
                    totalPayment += 3 * 120.25;
                    break;
                case 4580:
                    System.out.print("MAT 136 1 ");
                    totalPayment += 1 * 120.25;
                    break;
                case 2599:
                    System.out.print("COP 260 3 ");
                    totalPayment += 3 * 120.25;
                    break;
                case 1997:
                    System.out.print("CAP 424 1 ");
                    totalPayment += 1 * 120.25;
                    break;
                case 3696:
                    System.out.print("KOL 110 2 ");
                    totalPayment += 2 * 120.25;
                    break;
                default:
                    System.out.println("Unknown course number: " + crn);
            }
        }
        System.out.println("Health & id fees $ 35.00");
        System.out.println("--------------------------------------");
        System.out.printf("Total Payments $ %.2f%n", totalPayment);
        if (gpa >= 3.5 && totalPayment > 700) {
            double discount = totalPayment * 0.25;
            System.out.printf("-$ %.2f%n", discount);
            System.out.println("----------");
            System.out.printf("TOTAL PAYMENTS $ %.2f%n", totalPayment - discount);
        }
    }
}

class College {
    private ArrayList<Student> list;

    public College() {
        this.list = new ArrayList<>();
    }

    public void enrollStudent(Student student) {
        list.add(student);
    }

    public Student searchById(int studentId) {
        for (Student student : list) {
            if (student.getStudentId() == studentId) {
                return student;
            }
        }
        return null;
    }

    public Student searchByIdReturn(int studentId) {
        for (Student student : list) {
            if (student.getStudentId() == studentId) {
                return student;
            }
        }
        return null;
    }

    public boolean addCourse(int studentId, int crn) {
        Student student = searchByIdReturn(studentId);
        if (student != null) {
            student.getListOfCrns().add(crn);
            return true;
        }
        return false;
    }

    public boolean deleteCourse(int studentId, int crn) {
        Student student = searchByIdReturn(studentId);
        if (student != null) {
            ArrayList<Integer> listOfCrns = student.getListOfCrns();
            for (int i = 0; i < listOfCrns.size(); i++) {
                if (listOfCrns.get(i) == crn) {
                    listOfCrns.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    public void printInvoice(int studentId) {
        for (Student student : list) {
            if (student.getStudentId() == studentId) {
                student.printFeeInvoice();
                return;
            }
        }
        System.out.println("No student found with ID " + studentId);
    }

    public void printSortedInvoice(int studentId) {
        for (Student student : list) {
            if (student.getStudentId() == studentId) {
                Collections.sort(student.getListOfCrns());
                student.printFeeInvoice();
                return;
            }
        }
        System.out.println("No student found with ID " + studentId);
    }
}