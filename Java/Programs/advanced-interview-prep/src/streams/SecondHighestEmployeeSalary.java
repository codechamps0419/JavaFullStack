package streams;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Find the second-highest distinct salary in a list of employees.
 *
 */
public class SecondHighestEmployeeSalary {
	record Employee(int id, String name, String department, int salary) {
	}

	static List<Employee> employees() {
		return List.of(new Employee(1, "Asha", "IT", 70000), new Employee(2, "Ravi", "HR", 50000),
				new Employee(3, "Neha", "IT", 90000), new Employee(4, "Kiran", "HR", 60000),
				new Employee(5, "Amit", "IT", 80000));
	}

	/*
	 * APPROACH 1 (PRIMARY): Stream distinct + descending sort + skip Complexity ~
	 * TIME : O(n log n), SPACE : O(n)
	 */
	static Optional<Integer> secondHighest(List<Employee> employees) {
		return employees.stream().map(Employee::salary).distinct().sorted(Comparator.reverseOrder()).skip(1)
				.findFirst();
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		System.out.println("Second highest salary: " + secondHighest(employees()).orElse(null));
	}
}

/*
 * Output : Second highest salary: 80000
 */
