package streams;

import java.util.*;
import java.util.stream.*;

/**
 * Author: CodeChamps0419
 *
 * Group employee names by their department.
 *
 */
public class GroupEmployeesByDepartment {
	record Employee(int id, String name, String department, int salary) {
	}

	static List<Employee> employees() {
		return List.of(new Employee(1, "Asha", "IT", 70000), new Employee(2, "Ravi", "HR", 50000),
				new Employee(3, "Neha", "IT", 90000), new Employee(4, "Kiran", "HR", 60000),
				new Employee(5, "Amit", "IT", 80000));
	}

	/*
	 * APPROACH 1 (PRIMARY): Collectors.groupingBy with downstream mapping
	 * Complexity ~ TIME : O(n) expected, SPACE : O(n)
	 */
	static Map<String, List<String>> group(List<Employee> employees) {
		return employees.stream().collect(Collectors.groupingBy(Employee::department, TreeMap::new,
				Collectors.mapping(Employee::name, Collectors.toList())));
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		System.out.println("Groups: " + group(employees()));
	}
}

/*
 * Output : Groups: {HR=[Ravi, Kiran], IT=[Asha, Neha, Amit]}
 */
