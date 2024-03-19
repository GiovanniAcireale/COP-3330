// Giovanni Acireale, COP 3330, Unit 1 Homework, 1/19/24

import java.util.Scanner;

public class Unit1hw {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input student information
        System.out.print("Enter the Student’s Id: ");
        String studentId = scanner.nextLine();

        System.out.print("Enter the Student’s full name: ");
        String fullName = scanner.nextLine();

        // Input course information
        System.out.print("Enter crn/credit hours for the first class: ");
        String course1 = scanner.nextLine();
        String[] course1Info = course1.split("/");
        int crn1 = Integer.parseInt(course1Info[0]);
        int creditHours1 = Integer.parseInt(course1Info[1]);

        System.out.print("Enter crn/credit hours for the second class: ");
        String course2 = scanner.nextLine();
        String[] course2Info = course2.split("/");
        int crn2 = Integer.parseInt(course2Info[0]);
        int creditHours2 = Integer.parseInt(course2Info[1]);

        // Calculate total payments
        double creditHourCost = 120.25;
        double fees = 35.00;
        double totalPayment = (creditHours1 + creditHours2) * creditHourCost + fees;

        // Print fee invoice
        System.out.println("\nHERE IS THE FEE INVOICE...");
        System.out.println("SIMPLE COLLEGE\nORLANDO FL 10101\n*************************");
        System.out.println("Fee Invoice Prepared for:\n[" + fullName + "][" + studentId + "]");
        System.out.println("1 Credit Hour = $" + creditHourCost);
        System.out.println("CRN\tCREDIT HOURS");
        System.out.println(crn1 + "\t" + creditHours1 + "\t$" + (creditHours1 * creditHourCost));
        System.out.println(crn2 + "\t" + creditHours2 + "\t$" + (creditHours2 * creditHourCost));
        System.out.println("Health & id fees\t$" + fees);
        System.out.println("----------------------------------------");
        System.out.println("Total Payments\t$" + totalPayment);

        // Close the scanner
        scanner.close();
    }
}