// Unit 5 HW
// Giovanni Acireale


public class DriverClass {
    // DO NOT CHANGE ANYTHING IN THIS CLASS (DriverClass)
    public static void main(String[] args) {
        Student s;
        //****************************************************
        s = new PhdStudent ("Zaydoun BenSellam", "zb5954", "Gary Richarson", "Fuzzy Topology", 2599 );

        s.printInvoice();

        //****************************************************
        int [] gradCrnsTaken = {7587, 8997};
        s = new MsStudent ("Emily Jones", "em1254", gradCrnsTaken, 1997);

        s.printInvoice();

        //****************************************************
        int [] undergradCrnsTaken = {4587, 2599};
        s = new UndergraduateStudent ("Jamila Jones", "ja5225", undergradCrnsTaken, 3.0, false);

        s.printInvoice();
    }
}

class Course {
    private int crn;
    private String courseName;
    private int creditHours;

    public Course(int crn, String courseName, int creditHours) {
        this.crn = crn;
        this.courseName = courseName;
        this.creditHours = creditHours;
    }

    public int getCrn() {
        return crn;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCreditHours() {
        return creditHours;
    }
}

abstract class Student {
    private String name;
    private String id;

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    abstract public void printInvoice();
}

class UndergraduateStudent extends Student {
    private int[] crnsTaken;
    private double gpa;
    private boolean resident;

    public UndergraduateStudent(String name, String id, int[] crnsTaken, double gpa, boolean resident) {
        super(name, id);
        this.crnsTaken = crnsTaken;
        this.gpa = gpa;
        this.resident = resident;
    }

    @Override
    public void printInvoice() {
        Course[] courses = {
                new Course(4587, "MAT 236", 4),
                new Course(4599, "COP 220", 3),
                new Course(8997, "GOL 124", 1),
                new Course(9696, "COP 100", 3),
                new Course(4580, "MAT 136", 1),
                new Course(2599, "COP 260", 3),
                new Course(1997, "CAP 424", 1),
                new Course(5696, "KOL 110", 2),
                new Course(7587, "MAT 936", 5),
                new Course(1599, "COP 111", 3),
                new Course(6997, "GOL 109", 1),
                new Course(2696, "COP 101", 3),
                new Course(5580, "MAT 636", 2),
                new Course(2099, "COP 268", 3),
                new Course(4997, "CAP 427", 1),
                new Course(3696, "KOL 910", 2)
        };

        // Calculate the invoice based on the provided courses and other details
        double tuitionPerCreditHour = 120.25;
        double totalTuition = 0;

        for (int crn : crnsTaken) {
            for (Course course : courses) {
                if (course.getCrn() == crn) {
                    totalTuition += course.getCreditHours() * tuitionPerCreditHour;
                    break;
                }
            }
        }

        double healthAndIdFees = 35.00;
        double totalPayments = totalTuition + healthAndIdFees;

        System.out.println("VALENCE COLLEGE\nORLANDO FL 10101");
        System.out.println("---------------------\n");
        System.out.println("Fee Invoice Prepared for Student:");
        System.out.println(getId() + "-" + getName() + "\t(" + (resident ? "FL resident" : "Out-of-state") + ")");
        System.out.println("\n1 Credit Hour = $" + tuitionPerCreditHour + "\n");
        System.out.println("\tCRN\tCR_PREFIX\tCR_HOURS\t\tAmount");
        for (int crn : crnsTaken) {
            for (Course course : courses) {
                if (course.getCrn() == crn) {
                    System.out.printf("%d\t%s\t%d\t\t$ %.2f\n", course.getCrn(), course.getCourseName(), course.getCreditHours(), course.getCreditHours() * tuitionPerCreditHour);
                    break;
                }
            }
        }
        System.out.println("\n\tHealth & id fees\t\t$ " + healthAndIdFees);
        System.out.println("\n--------------------------------------");
        System.out.println("\t\t\t\t$ " + totalPayments);
        if (gpa >= 3.5 && totalPayments > 500) {
            double discount = totalPayments * 0.25;
            System.out.println("\t\t\t -$ " + discount);
            System.out.println("\t\t\t----------");
            System.out.println("\t\tTOTAL PAYMENTS\t$ " + (totalPayments - discount));
        }
    }
}

abstract class GraduateStudent extends Student {
    private int taForCrn;

    public GraduateStudent(String name, String id, int taForCrn) {
        super(name, id);
        this.taForCrn = taForCrn;
    }

    public int getTaForCrn() {
        return taForCrn;
    }

    public void setTaForCrn(int taForCrn) {
        this.taForCrn = taForCrn;
    }
}

class MsStudent extends GraduateStudent {
    private int[] gradCrnsTaken;

    public MsStudent(String name, String id, int[] gradCrnsTaken, int crn) {
        super(name, id, crn);
        this.gradCrnsTaken = gradCrnsTaken;
    }

    @Override
    public void printInvoice() {
        Course[] courses = {
                new Course(4587, "MAT 236", 4),
                new Course(4599, "COP 220", 3),
                new Course(8997, "GOL 124", 1),
                new Course(9696, "COP 100", 3),
                new Course(4580, "MAT 136", 1),
                new Course(2599, "COP 260", 3),
                new Course(1997, "CAP 424", 1),
                new Course(5696, "KOL 110", 2),
                new Course(7587, "MAT 936", 5),
                new Course(1599, "COP 111", 3),
                new Course(6997, "GOL 109", 1),
                new Course(2696, "COP 101", 3),
                new Course(5580, "MAT 636", 2),
                new Course(2099, "COP 268", 3),
                new Course(4997, "CAP 427", 1),
                new Course(3696, "KOL 910", 2)
        };

        // Calculate the invoice based on the provided courses and other details
        double tuitionPerCreditHour = 300.00;
        double totalTuition = 0;

        for (int crn : gradCrnsTaken) {
            for (Course course : courses) {
                if (course.getCrn() == crn) {
                    totalTuition += course.getCreditHours() * tuitionPerCreditHour;
                    break;
                }
            }
        }

        double healthAndIdFees = 35.00;
        double totalPayments = totalTuition + healthAndIdFees;

        System.out.println("VALENCE COLLEGE\nORLANDO FL 10101\n\t---------------------\n");
        System.out.println("Fee Invoice Prepared for Student:");
        System.out.println(getId() + "-" + getName());
        System.out.println("\n1 Credit Hour = $" + tuitionPerCreditHour + "\n");
        System.out.println("\tCRN\tCR_PREFIX\tCR_HOURS\t\tAmount");
        for (int crn : gradCrnsTaken) {
            for (Course course : courses) {
                if (course.getCrn() == crn) {
                    System.out.printf("%d\t%s\t%d\t\t$ %.2f\n", course.getCrn(), course.getCourseName(), course.getCreditHours(), course.getCreditHours() * tuitionPerCreditHour);
                    break;
                }
            }
        }
        System.out.println("\n\tHealth & id fees\t\t$ " + healthAndIdFees);
        System.out.println("\n--------------------------------------");
        System.out.println("\t\t\t\t$ " + totalPayments);
    }
}

class PhdStudent extends GraduateStudent {
    private String advisor;
    private String researchSubject;

    public PhdStudent(String name, String id, String advisor, String researchSubject, int crn) {
        super(name, id, crn);
        this.advisor = advisor;
        this.researchSubject = researchSubject;
    }

    @Override
    public void printInvoice() {
        // Calculate the invoice based on the provided courses and other details
        double researchFee = 700.00;
        double healthAndIdFees = 35.00;
        double totalPayments = researchFee + healthAndIdFees;

        System.out.println("VALENCE COLLEGE\nORLANDO FL 10101\n\t---------------------\n");
        System.out.println("Fee Invoice Prepared for Student:");
        System.out.println(getId() + "-" + getName());
        System.out.println("Advisor: " + advisor);
        System.out.println("Research Subject: " + researchSubject);
        System.out.println("\n\tRESEARCH\tAmount");
        System.out.printf("%s\t$ %.2f\n", researchSubject, researchFee);
        System.out.println("\n\tHealth & id fees\t\t$ " + healthAndIdFees);
        System.out.println("\n--------------------------------------");
        System.out.println("\t\t\t\t$ " + totalPayments);
    }
}