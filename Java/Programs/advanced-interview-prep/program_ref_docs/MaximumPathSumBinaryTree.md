# 30. MaximumPathSumBinaryTree

**Author:** CodeChamps0419  
**Category:** `trees.advanced`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Iterative postorder dynamic programming  

## 1. Problem statement

Find maximum sum of any path in a binary tree (path may start/end anywhere).

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

Gain upward picks one branch, but the local best path can combine both branches.

**Time complexity:** `O(n)`  
**Auxiliary space complexity:** `O(n)`  

The executable implementation is in `src/MaximumPathSumBinaryTree.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static int maxPathSum(Node root) {
    if (root == null) throw new IllegalArgumentException("Tree must not be empty");
    Deque<Node> stack = new ArrayDeque<>(), reverse = new ArrayDeque<>();
    Map<Node, Integer> gain = new IdentityHashMap<>();
    stack.push(root);
    while (!stack.isEmpty()) {
        Node n = stack.pop();
        reverse.push(n);
        if (n.left != null) stack.push(n.left);
        if (n.right != null) stack.push(n.right);
    }
    int best = Integer.MIN_VALUE;
    while (!reverse.isEmpty()) {
        Node n = reverse.pop();
        int left = n.left == null ? 0 : Math.max(0, gain.get(n.left));
        int right = n.right == null ? 0 : Math.max(0, gain.get(n.right));
        best = Math.max(best, n.value + left + right);
        gain.put(n, n.value + Math.max(left, right));
    }
    return best;
}
```

## 4. Input and expected output

```text
Max path sum: 22
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** Gain upward picks one branch, but the local best path can combine both branches.

## 6. Edge cases and common beginner pitfalls

Negative gains are excluded; all-negative tree still returns its highest node.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Iterative postorder dynamic programming**. Gain upward picks one branch, but the local best path can combine both branches. The running time is **O(n)**, and the extra-space requirement is **O(n)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** Why can only one branch be returned to the parent?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build trees/advanced/src/MaximumPathSumBinaryTree.java
java -cp build trees.advanced.MaximumPathSumBinaryTree
```

© Dynamic Duo Learners — Program author: CodeChamps0419
