// Giovanni Acireale

import java.text.DecimalFormat;

public class DriverClass {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        
        list.addNewEmployee(new Employee ("Kim Oz", 1235.5, 3));
        list.addNewEmployee(new Employee ("Rim Oz", 8235.5, 1));
        list.addNewEmployee(new Employee ("Dane Ali ", 3235.5, 0));
        list.addNewEmployee(new Employee ("Aidan Jones ", 2035.5, 2));
        list.addNewEmployee(new Employee ("Nadia Jones", 5035.5, 3));
        list.addNewEmployee(new Employee ("Ed Renu", 6035, 2));
        list.addNewEmployee(new Employee ("Naadi Jones", 36035.75, 5));
        
        //The TAs may use less or more names. 
        
        list.printAllEmployees();
        
        System.out.println("The highest net salary = " + list.highestNetSalary());
        
        list.deleteEmployeeByName("Rim Oz");
        list.deleteEmployeeByName("Nadia Jones");
        
        System.out.println( list.searchByName("Gary D. Richardson") );
        
        list.printAllEmployees();
        
        
    }
}//end of DriverClass    

class LinkedList {
    Node company;

    public LinkedList() {
        company = null;
    }

    public void printAllEmployees() {
        Node current = company;
        while (current != null) {
            Node temp = current;
            while (temp != null) {
                System.out.println(temp.getEmployee());
                temp = temp.getNext();
            }
            current = current.getBelow();
        }
    }

    public void addNewEmployee(Employee e) {
        Node newNode = new Node(e);
        if (company == null) {
            company = newNode;
            return;
        }

        Node current = company;
        while (current.getBelow() != null) {
            current = current.getBelow();
        }
        current.setBelow(newNode);
    }

    public boolean searchByName(String name) {
        Node current = company;
        while (current != null) {
            Node temp = current;
            while (temp != null) {
                if (temp.getEmployee().getName().equalsIgnoreCase(name)) {
                    return true;
                }
                temp = temp.getNext();
            }
            current = current.getBelow();
        }
        return false;
    }

    public double highestNetSalary() {
        double highestSalary = Double.MIN_VALUE;
        Node current = company;
        while (current != null) {
            Node temp = current;
            while (temp != null) {
                double netSalary = temp.getEmployee().calculateNetSalary();
                if (netSalary > highestSalary) {
                    highestSalary = netSalary;
                }
                temp = temp.getNext();
            }
            current = current.getBelow();
        }
        return highestSalary;
    }

    public void deleteEmployeeByName(String name) {
        if (company == null) {
            return;
        }
    
        if (company.getEmployee().getName().equalsIgnoreCase(name)) {
            company = company.getBelow();
            return;
        }
    
        Node current = company;
        Node prev = null;
    
        while (current != null) {
            Node temp = current;
            Node prevTemp = prev;
            while (temp != null) {
                if (temp.getEmployee().getName().equalsIgnoreCase(name)) {
                    if (prevTemp != null) {
                        prevTemp.setNext(temp.getNext());
                    }
                    if (prevTemp != null && prevTemp.getBelow() == temp) {
                        prevTemp.setBelow(temp.getBelow());
                    }
                    break;
                }
                prevTemp = temp;
                temp = temp.getNext();
            }
            if (current.getEmployee().getName().equalsIgnoreCase(name)) {
                break;
            }
            prev = current;
            current = current.getBelow();
        }
    }
}

class Employee {
    private String name;
    private int id;
    private int numberOfDependents;
    private double salary;

    public Employee(String name, double salary, int numberOfDependents) {
        this.name = name;
        this.salary = salary;
        this.numberOfDependents = numberOfDependents;
        this.id = calculateId(name);
    }

    private int calculateId(String name) {
        int id = 0;
        String upperCaseName = name.toUpperCase();
        for (char c : upperCaseName.toCharArray()) {
            id += (int) c;
        }
        return id;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.00");
        double netSalary = calculateNetSalary();
        return "[" + id + "," + name + "," + df.format(netSalary) + "]";
    }

    public double calculateNetSalary() {
        return salary * 0.91 + (numberOfDependents * 0.01 * salary);
    }

    public String getName() {
        return name;
    }
}

class Node {
    private Employee e;
    private Node next;
    private Node below;

    public Node(Employee e) {
        this.e = e;
        this.next = null;
        this.below = null;
    }

    public Employee getEmployee() {
        return e;
    }

    public Node getNext() {
        return next;
    }

    public Node getBelow() {
        return below;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setBelow(Node below) {
        this.below = below;
    }
}