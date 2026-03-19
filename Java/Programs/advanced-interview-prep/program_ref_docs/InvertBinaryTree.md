# 28. InvertBinaryTree

**Author:** CodeChamps0419  
**Category:** `trees.basic`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Iterative breadth-first traversal  

## 1. Problem statement

Swap left/right children at every node.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

Swap children on every visited node, then enqueue the resulting children.

**Time complexity:** `O(n)`  
**Auxiliary space complexity:** `O(w)`  

The executable implementation is in `src/InvertBinaryTree.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static Node invert(Node root) {
    if (root == null) return null;
    Queue<Node> queue = new ArrayDeque<>();
    queue.add(root);
    while (!queue.isEmpty()) {
        Node node = queue.remove();
        Node tmp = node.left;
        node.left = node.right;
        node.right = tmp;
        if (node.left != null) queue.add(node.left);
        if (node.right != null) queue.add(node.right);
    }
    return root;
}
```

## 4. Input and expected output

```text
New root children: 6, 2
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** Swap children on every visited node, then enqueue the resulting children.

## 6. Edge cases and common beginner pitfalls

This mutates the input tree.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Iterative breadth-first traversal**. Swap children on every visited node, then enqueue the resulting children. The running time is **O(n)**, and the extra-space requirement is **O(w)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** How would you return an inverted copy instead?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build trees/basic/src/InvertBinaryTree.java
java -cp build trees.basic.InvertBinaryTree
```

© Dynamic Duo Learners — Program author: CodeChamps0419
