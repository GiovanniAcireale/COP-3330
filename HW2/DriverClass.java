// Unit2.hw
// Giovanni Acireale

import java.util.Scanner;

public class DriverClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the Student’s Id: ");
        String studentId = scanner.nextLine();

        System.out.print("Enter the Student’s full name: ");
        String studentName = scanner.nextLine();

        System.out.print("Enter crn/credit hours for the first class: ");
        String input1 = scanner.next();

        int slashIndex1 = input1.indexOf('/');
        int crn1 = Integer.parseInt(input1.substring(0, slashIndex1));
        int creditHours1 = Integer.parseInt(input1.substring(slashIndex1 + 1));

        System.out.print("Enter crn/credit hours for the second class: ");
        String input2 = scanner.next();

        int slashIndex2 = input2.indexOf('/');
        int crn2 = Integer.parseInt(input2.substring(0, slashIndex2));
        int creditHours2 = Integer.parseInt(input2.substring(slashIndex2 + 1));

        FeeInvoice feeInvoice = new FeeInvoice(studentName, studentId, crn1, creditHours1, crn2, creditHours2);

        System.out.println("Thank you!\nHERE IS THE FEE INVOICE...");
        feeInvoice.printFeeInvoice();

        scanner.close();
    }
}

class FeeInvoice {
    private String studentName;
    private String studentId;
    private int crn1;
    private int creditHours1;
    private int crn2;
    private int creditHours2;

    public FeeInvoice(String studentName, String studentId, int crn1, int creditHours1, int crn2, int creditHours2) {
        this.studentName = studentName;
        this.studentId = studentId;
        this.crn1 = crn1;
        this.creditHours1 = creditHours1;
        this.crn2 = crn2;
        this.creditHours2 = creditHours2;
    }

    private double calculateTotalPayment() {
        double creditHourCost = 120.25;
        double healthIdFees = 35.00;

        return (creditHours1 + creditHours2) * creditHourCost + healthIdFees;
    }

    public void printFeeInvoice() {
        System.out.println("\nSIMPLE COLLEGE\nORLANDO FL 10101");
        System.out.println("*************************\n");
        System.out.println("Fee Invoice Prepared for:");
        System.out.println("[" + studentName + "][" + studentId + "]");
        System.out.println("\n1 Credit Hour = $120.25\n");

        System.out.printf("%-5s   %-13s   %-5s   %-39s\n", "CRN", "CREDIT HOURS", " ", " ");
        System.out.printf("%-5s   %-13s   %-5s   %-39s\n", crn1, creditHours1, "", " $" + (creditHours1 * 120.25));
        System.out.printf("%-5s   %-13s   %-5s   %-39s\n", crn2, creditHours2, "", " $" + (creditHours2 * 120.25));

        System.out.println("\nHealth & id fees $35.00\n");
        System.out.println("----------------------------------------");
        System.out.println("                  Total Payments $" + calculateTotalPayment());
    }
}