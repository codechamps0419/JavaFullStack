package streams;

import java.util.*;
import java.util.stream.*;

/**
 * Author: CodeChamps0419
 *
 * Convert objects to Map keyed by department while merging duplicate keys.
 *
 */
public class ListToMapDuplicateKeys {
	record Employee(int id, String name, String department, int salary) {
	}

	static List<Employee> employees() {
		return List.of(new Employee(1, "Asha", "IT", 70000), new Employee(2, "Ravi", "HR", 50000),
				new Employee(3, "Neha", "IT", 90000), new Employee(4, "Kiran", "HR", 60000),
				new Employee(5, "Amit", "IT", 80000));
	}

	/*
	 * APPROACH 1 (PRIMARY): Collectors.toMap with merge function Complexity ~ TIME
	 * : O(n) expected, SPACE : O(d)
	 */
	static Map<String, Integer> salaryTotals(List<Employee> employees) {
		return employees.stream()
				.collect(Collectors.toMap(Employee::department, Employee::salary, Integer::sum, TreeMap::new));
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		System.out.println("Salary totals: " + salaryTotals(employees()));
	}
}

/*
 * Output : Salary totals: {HR=110000, IT=240000}
 */
