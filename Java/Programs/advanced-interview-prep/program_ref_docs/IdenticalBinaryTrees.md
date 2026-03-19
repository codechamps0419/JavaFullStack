# 25. IdenticalBinaryTrees

**Author:** CodeChamps0419  
**Category:** `trees.basic`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Iterative queue of node pairs  

## 1. Problem statement

Check whether two binary trees have identical shape and values.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

Compare corresponding nodes and enqueue corresponding children.

**Time complexity:** `O(n)`  
**Auxiliary space complexity:** `O(w)`  

The executable implementation is in `src/IdenticalBinaryTrees.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
record Pair(Node a, Node b) { }
static boolean identical(Node a, Node b) {
    Queue<Pair> queue = new ArrayDeque<>();
    queue.add(new Pair(a, b));
    while (!queue.isEmpty()) {
        Pair pair = queue.remove();
        Node left = pair.a(), right = pair.b();
        if (left == null && right == null) continue;
        if (left == null || right == null || left.value != right.value) return false;
        queue.add(new Pair(left.left, right.left));
        queue.add(new Pair(left.right, right.right));
    }
    return true;
}
```

## 4. Input and expected output

```text
Identical: true
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** Compare corresponding nodes and enqueue corresponding children.

## 6. Edge cases and common beginner pitfalls

Use record pairs because ArrayDeque does not permit null elements, but the record can contain null fields.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Iterative queue of node pairs**. Compare corresponding nodes and enqueue corresponding children. The running time is **O(n)**, and the extra-space requirement is **O(w)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** What changes for subtree containment?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build trees/basic/src/IdenticalBinaryTrees.java
java -cp build trees.basic.IdenticalBinaryTrees
```

© Dynamic Duo Learners — Program author: CodeChamps0419
