package streams;

import java.util.*;
import java.util.stream.*;

/**
 * Author: CodeChamps0419
 *
 * Find highest-paid employee per department.
 *
 */
public class HighestPaidPerDepartment {
	record Employee(int id, String name, String department, int salary) {
	}

	static List<Employee> employees() {
		return List.of(new Employee(1, "Asha", "IT", 70000), new Employee(2, "Ravi", "HR", 50000),
				new Employee(3, "Neha", "IT", 90000), new Employee(4, "Kiran", "HR", 60000),
				new Employee(5, "Amit", "IT", 80000));
	}

	/*
	 * APPROACH 1 (PRIMARY): groupingBy + maxBy collector Complexity ~ TIME : O(n)
	 * expected, SPACE : O(d)
	 */
	static Map<String, String> highestPaid(List<Employee> employees) {
		return employees.stream()
				.collect(Collectors.groupingBy(Employee::department, TreeMap::new, Collectors.collectingAndThen(
						Collectors.maxBy(Comparator.comparingInt(Employee::salary)), opt -> opt.orElseThrow().name())));
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		System.out.println("Highest paid: " + highestPaid(employees()));
	}
}

/*
 * Output : Highest paid: {HR=Kiran, IT=Neha}
 */
