package streams;

import java.util.*;
import java.util.stream.*;

/**
 * Author: CodeChamps0419
 *
 * Compute arithmetic mean salary for each department.
 *
 */
public class AverageSalaryByDepartment {
	record Employee(int id, String name, String department, int salary) {
	}

	static List<Employee> employees() {
		return List.of(new Employee(1, "Asha", "IT", 70000), new Employee(2, "Ravi", "HR", 50000),
				new Employee(3, "Neha", "IT", 90000), new Employee(4, "Kiran", "HR", 60000),
				new Employee(5, "Amit", "IT", 80000));
	}

	/*
	 * APPROACH 1 (PRIMARY): Collectors.averagingInt downstream Complexity ~ TIME :
	 * O(n) expected, SPACE : O(d)
	 */
	static Map<String, Double> averages(List<Employee> employees) {
		return employees.stream().collect(
				Collectors.groupingBy(Employee::department, TreeMap::new, Collectors.averagingInt(Employee::salary)));
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		System.out.println("Average salaries: " + averages(employees()));
	}
}

/*
 * Output : Average salaries: {HR=55000.0, IT=80000.0}
 */
