/* @Author: Dynamic Duo Learners */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


class Employee implements Comparable<Employee> {
    private int id;
    private String name;
    private int age;
    private double salary;

    public Employee(int id, String name, int age, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getSalary() { return salary; }

    @Override
    public int compareTo(Employee other) {
        //Compare by Name
        int nameCompare = this.name.compareTo(other.name); //asc order
        //int nameCompare = other.name.compareTo(this.name); //desc order

        if (nameCompare != 0) {
            return nameCompare;
        }

        //If Names match, compare by Salary
        int salaryCompare = Double.compare(this.salary, other.salary); //asc order
	//int salaryCompare = Double.compare(other.salary, this.salary); //desc order

        if (salaryCompare != 0) {
            return salaryCompare;
        }

        //If Names and Salaries match, break tie by Age
        return Integer.compare(this.age, other.age); //asc order
	//return Integer.compare(other.age, this.age); //desc order
    }

    @Override
    public String toString() {
        return String.format("Employee[ID=%d, Name='%-7s', Age=%d, Salary=%.2f]", id, name, age, salary);
    }
}

public class ComparableDemo {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(101, "Bob",   35, 60000.00));
        employees.add(new Employee(102, "Alice", 28, 95000.00));
        employees.add(new Employee(103, "Alice", 22, 75000.00));
        employees.add(new Employee(104, "Alice", 25, 75000.00)); 
        employees.add(new Employee(105, "Bob",   30, 68000.00));

        System.out.println("--- Original List ---");
        printList(employees);

        // Sorting using Collections.sort() without passing any external Comparator.
        // It automatically invokes the compareTo method inside the Employee class.
        Collections.sort(employees);

        System.out.println("\n--- Sorted List using Comparable (Name -> Salary -> Age) ---");
        printList(employees);
    }

    private static void printList(List<Employee> list) {
        for (Employee emp : list) {
            System.out.println(emp);
        }
    }
}


/*

Output:

--- Original List ---
Employee[ID=101, Name='Bob    ', Age=35, Salary=60000.00]
Employee[ID=102, Name='Alice  ', Age=28, Salary=95000.00]
Employee[ID=103, Name='Alice  ', Age=22, Salary=75000.00]
Employee[ID=104, Name='Alice  ', Age=25, Salary=75000.00]
Employee[ID=105, Name='Bob    ', Age=30, Salary=68000.00]

--- Sorted List using Comparable (Name -> Salary -> Age) ---
Employee[ID=103, Name='Alice  ', Age=22, Salary=75000.00]
Employee[ID=104, Name='Alice  ', Age=25, Salary=75000.00]
Employee[ID=102, Name='Alice  ', Age=28, Salary=95000.00]
Employee[ID=101, Name='Bob    ', Age=35, Salary=60000.00]
Employee[ID=105, Name='Bob    ', Age=30, Salary=68000.00]

*/