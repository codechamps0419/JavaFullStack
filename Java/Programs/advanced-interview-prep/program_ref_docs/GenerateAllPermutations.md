# 42. GenerateAllPermutations

**Author:** CodeChamps0419  
**Category:** `backtrackingdp`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Iterative insertion into every position  

## 1. Problem statement

Return all permutations of distinct input numbers.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

Insert each new value at every position of each partial permutation.

**Time complexity:** `O(n * n!)`  
**Auxiliary space complexity:** `O(n * n!) output`  

The executable implementation is in `src/GenerateAllPermutations.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static List<List<Integer>> permutations(int[] nums) {
    List<List<Integer>> result=new ArrayList<>();
    result.add(new ArrayList<>());
    for(int num:nums) {
        List<List<Integer>> next=new ArrayList<>();
        for(List<Integer> permutation:result) {
            for(int pos=0;pos<=permutation.size();pos++) {
                List<Integer> copy=new ArrayList<>(permutation);
                copy.add(pos,num);
                next.add(copy);
            }
        }
        result=next;
    }
    return result;
}
```

## 4. Input and expected output

```text
Permutations: [[3, 2, 1], [2, 3, 1], [2, 1, 3], [3, 1, 2], [1, 3, 2], [1, 2, 3]]
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** Insert each new value at every position of each partial permutation.

## 6. Edge cases and common beginner pitfalls

Distinct-input assumption avoids duplicate permutations.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Iterative insertion into every position**. Insert each new value at every position of each partial permutation. The running time is **O(n * n!)**, and the extra-space requirement is **O(n * n!) output**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** How to handle repeated values?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build backtrackingdp/src/GenerateAllPermutations.java
java -cp build backtrackingdp.GenerateAllPermutations
```

© Dynamic Duo Learners — Program author: CodeChamps0419
