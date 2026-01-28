/* @Author: Dynamic Duo Learners */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//Employee implements Comparable to define DEFAULT sorting (by ID)
class Employee implements Comparable<Employee> {
    private int id;
    private String name;
    private double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getSalary() { return salary; }

    // This is the default/natural sorting rule
    @Override
    public int compareTo(Employee other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return String.format("Employee[ID=%d, Name='%s', Salary=%.2f]", id, name, salary);
    }
}

//Separate class implementing Comparator to define ALTERNATIVE sorting (by Salary)
class SalarySort implements Comparator<Employee> {
    @Override
    public int compare(Employee e1, Employee e2) {
        return Double.compare(e1.getSalary(), e2.getSalary());
    }
}

public class CombinedSortingDemo {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        
        employees.add(new Employee(103, "Alice", 75000.00));
        employees.add(new Employee(101, "Bob",   62000.00));
        employees.add(new Employee(104, "Diana", 95000.00));
        employees.add(new Employee(102, "Charlie", 85000.00));

        System.out.println("--- Original List ---");
        printList(employees);

        // Using Comparable
        // We don't pass a second argument. It automatically uses Employee's compareTo()
        Collections.sort(employees);
        System.out.println("\n--- Sorted by Default Order (ID via Comparable) ---");
        printList(employees);

        // Using Comparator
        // We pass the explicit Comparator instance to override the default behavior
        Collections.sort(employees, new SalarySort());
        System.out.println("\n--- Sorted by Alternative Order (Salary via Comparator) ---");
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
Employee[ID=103, Name='Alice', Salary=75000.00]
Employee[ID=101, Name='Bob', Salary=62000.00]
Employee[ID=104, Name='Diana', Salary=95000.00]
Employee[ID=102, Name='Charlie', Salary=85000.00]

--- Sorted by Default Order (ID via Comparable) ---
Employee[ID=101, Name='Bob', Salary=62000.00]
Employee[ID=102, Name='Charlie', Salary=85000.00]
Employee[ID=103, Name='Alice', Salary=75000.00]
Employee[ID=104, Name='Diana', Salary=95000.00]

--- Sorted by Alternative Order (Salary via Comparator) ---
Employee[ID=101, Name='Bob', Salary=62000.00]
Employee[ID=103, Name='Alice', Salary=75000.00]
Employee[ID=102, Name='Charlie', Salary=85000.00]
Employee[ID=104, Name='Diana', Salary=95000.00]

*/