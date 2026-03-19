# 41. GenerateAllSubsets

**Author:** CodeChamps0419  
**Category:** `backtrackingdp`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Iterative subset doubling  

## 1. Problem statement

Return every subset of a list of distinct integers.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

For each element, duplicate all previously generated subsets and append the element to the copies.

**Time complexity:** `O(n * 2^n)`  
**Auxiliary space complexity:** `O(n * 2^n) output`  

The executable implementation is in `src/GenerateAllSubsets.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> result=new ArrayList<>();
    result.add(new ArrayList<>());
    for(int value:nums) {
        int existing=result.size();
        for(int i=0;i<existing;i++) {
            List<Integer> copy=new ArrayList<>(result.get(i));
            copy.add(value);
            result.add(copy);
        }
    }
    return result;
}
```

## 4. Input and expected output

```text
Subsets: [[], [1], [2], [1, 2]]
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** For each element, duplicate all previously generated subsets and append the element to the copies.

## 6. Edge cases and common beginner pitfalls

For duplicate input elements, deduplicate output if required.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Iterative subset doubling**. For each element, duplicate all previously generated subsets and append the element to the copies. The running time is **O(n * 2^n)**, and the extra-space requirement is **O(n * 2^n) output**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** Why are 2^n subsets unavoidable?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build backtrackingdp/src/GenerateAllSubsets.java
java -cp build backtrackingdp.GenerateAllSubsets
```

© Dynamic Duo Learners — Program author: CodeChamps0419
