/* @Author: Dynamic Duo Learners */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


class Employee {
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
    public String toString() {
        return String.format("Employee[ID=%d, Name='%s', Age=%d, Salary=%.2f]", id, name, age, salary);
    }
}

//Comparator for Name only (Alphabetical)
class NameComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee e1, Employee e2) {
        return e1.getName().compareTo(e2.getName());
      //return e2.getName().compareTo(e1.getName()); //for descending order
    }
}

//Comparator for Salary only
class SalaryComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee e1, Employee e2) {
        return Double.compare(e1.getSalary(), e2.getSalary());
      //return Double.compare(e2.getSalary(), e1.getSalary()); //for descending order
    }
}

//Multi-field Comparator for Name, Salary & Age
class NameSalaryAgeComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee e1, Employee e2) {
        //Compare by Name
        int nameCompare = e1.getName().compareTo(e2.getName());
        if (nameCompare != 0) {
            return nameCompare;
        }

        //If Names are identical, compare by Salary
        int salaryCompare = Double.compare(e1.getSalary(), e2.getSalary());
        if (salaryCompare != 0) {
            return salaryCompare;
        }

        //If both Name and Salary are identical, break tie by Age
        return Integer.compare(e1.getAge(), e2.getAge());
    }
}

public class ComparatorDemo {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(101, "Bob",   35, 60000.00));
        employees.add(new Employee(102, "Alice", 28, 95000.00));
        employees.add(new Employee(103, "Alice", 22, 75000.00));
        employees.add(new Employee(104, "Alice", 25, 75000.00)); 
        employees.add(new Employee(105, "Bob",   30, 68000.00));

        System.out.println("--- Original List ---");
        printList(employees);

        System.out.println("\n--- Sorted by Name Only ---");
        Collections.sort(employees, new NameComparator());
        printList(employees);

        System.out.println("\n--- Sorted by Salary Only ---");
        Collections.sort(employees, new SalaryComparator());
	//employees.sort((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary())) //modern java (8+) way
        printList(employees);

        System.out.println("\n--- Sorted by Name, then Salary, then Age ---");
        Collections.sort(employees, new NameSalaryAgeComparator());
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
Employee[ID=101, Name='Bob', Age=35, Salary=60000.00]
Employee[ID=102, Name='Alice', Age=28, Salary=95000.00]
Employee[ID=103, Name='Alice', Age=22, Salary=75000.00]
Employee[ID=104, Name='Alice', Age=25, Salary=75000.00]
Employee[ID=105, Name='Bob', Age=30, Salary=68000.00]

--- Sorted by Name Only ---
Employee[ID=102, Name='Alice', Age=28, Salary=95000.00]
Employee[ID=103, Name='Alice', Age=22, Salary=75000.00]
Employee[ID=104, Name='Alice', Age=25, Salary=75000.00]
Employee[ID=101, Name='Bob', Age=35, Salary=60000.00]
Employee[ID=105, Name='Bob', Age=30, Salary=68000.00]

--- Sorted by Salary Only ---
Employee[ID=101, Name='Bob', Age=35, Salary=60000.00]
Employee[ID=105, Name='Bob', Age=30, Salary=68000.00]
Employee[ID=103, Name='Alice', Age=22, Salary=75000.00]
Employee[ID=104, Name='Alice', Age=25, Salary=75000.00]
Employee[ID=102, Name='Alice', Age=28, Salary=95000.00]

--- Sorted by Name, then Salary, then Age ---
Employee[ID=103, Name='Alice', Age=22, Salary=75000.00]
Employee[ID=104, Name='Alice', Age=25, Salary=75000.00]
Employee[ID=102, Name='Alice', Age=28, Salary=95000.00]
Employee[ID=101, Name='Bob', Age=35, Salary=60000.00]
Employee[ID=105, Name='Bob', Age=30, Salary=68000.00]

*/