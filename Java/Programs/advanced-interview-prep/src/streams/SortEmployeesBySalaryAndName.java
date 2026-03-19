package streams;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Sort employees by descending salary and then ascending name.
 *
 */
public class SortEmployeesBySalaryAndName {
	record Employee(int id, String name, String department, int salary) {
	}

	static List<Employee> employees() {
		return List.of(new Employee(1, "Asha", "IT", 70000), new Employee(2, "Ravi", "HR", 50000),
				new Employee(3, "Neha", "IT", 90000), new Employee(4, "Kiran", "HR", 60000),
				new Employee(5, "Amit", "IT", 80000));
	}

	/*
	 * APPROACH 1 (PRIMARY): Comparator chaining + stream sorted Complexity ~ TIME :
	 * O(n log n), SPACE : O(n)
	 */
	static List<String> sortedNames(List<Employee> employees) {
		return employees.stream()
				.sorted(Comparator.comparingInt(Employee::salary).reversed().thenComparing(Employee::name))
				.map(Employee::name).toList();
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		System.out.println("Sorted names: " + sortedNames(employees()));
	}
}

/*
 * Output : Sorted names: [Neha, Amit, Asha, Kiran, Ravi]
 */
