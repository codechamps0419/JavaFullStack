# 51. ListToMapDuplicateKeys

**Author:** CodeChamps0419  
**Category:** `streams`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Collectors.toMap with merge function  

## 1. Problem statement

Convert objects to Map keyed by department while merging duplicate keys.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

toMap would throw duplicate key error without a merge function.

**Time complexity:** `O(n) expected`  
**Auxiliary space complexity:** `O(d)`  

The executable implementation is in `src/ListToMapDuplicateKeys.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static Map<String,Integer> salaryTotals(List<Employee> employees) {
    return employees.stream().collect(Collectors.toMap(Employee::department,
            Employee::salary, Integer::sum, TreeMap::new));
}
```

## 4. Input and expected output

```text
Salary totals: {HR=110000, IT=240000}
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** toMap would throw duplicate key error without a merge function.

## 6. Edge cases and common beginner pitfalls

Use long for potentially large salary totals to prevent int overflow.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Collectors.toMap with merge function**. toMap would throw duplicate key error without a merge function. The running time is **O(n) expected**, and the extra-space requirement is **O(d)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** What are the four arguments of toMap?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build streams/src/ListToMapDuplicateKeys.java
java -cp build streams.ListToMapDuplicateKeys
```

© Dynamic Duo Learners — Program author: CodeChamps0419
