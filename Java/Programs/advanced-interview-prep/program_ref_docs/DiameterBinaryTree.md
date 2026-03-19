# 24. DiameterBinaryTree

**Author:** CodeChamps0419  
**Category:** `trees.medium`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Iterative postorder + subtree heights  

## 1. Problem statement

Find the longest path between any two nodes, measured in edges.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

Compute subtree heights from leaves upward, measuring the best left+right path.

**Time complexity:** `O(n)`  
**Auxiliary space complexity:** `O(n)`  

The executable implementation is in `src/DiameterBinaryTree.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static int diameter(Node root) {
    if (root == null) return 0;
    Map<Node, Integer> height = new IdentityHashMap<>();
    Deque<Node> todo = new ArrayDeque<>(), postorder = new ArrayDeque<>();
    todo.push(root);
    while (!todo.isEmpty()) {
        Node node = todo.pop();
        postorder.push(node);
        if (node.left != null) todo.push(node.left);
        if (node.right != null) todo.push(node.right);
    }
    int best = 0;
    while (!postorder.isEmpty()) {
        Node node = postorder.pop();
        int left = node.left == null ? 0 : height.get(node.left);
        int right = node.right == null ? 0 : height.get(node.right);
        best = Math.max(best, left + right);
        height.put(node, 1 + Math.max(left, right));
    }
    return best;
}
```

## 4. Input and expected output

```text
Diameter: 4
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** Compute subtree heights from leaves upward, measuring the best left+right path.

## 6. Edge cases and common beginner pitfalls

Diameter is in edges, whereas height here is in nodes.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Iterative postorder + subtree heights**. Compute subtree heights from leaves upward, measuring the best left+right path. The running time is **O(n)**, and the extra-space requirement is **O(n)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** Why can the longest path avoid the root?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build trees/medium/src/DiameterBinaryTree.java
java -cp build trees.medium.DiameterBinaryTree
```

© Dynamic Duo Learners — Program author: CodeChamps0419
