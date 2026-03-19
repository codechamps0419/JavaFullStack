# 22. MaximumDepthBinaryTree

**Author:** CodeChamps0419  
**Category:** `trees.basic`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Iterative breadth-first search  

## 1. Problem statement

Find the number of tree levels from root to deepest leaf.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

Traverse one level at a time; each completed level increments depth.

**Time complexity:** `O(n)`  
**Auxiliary space complexity:** `O(w), w = max level width`  

The executable implementation is in `src/MaximumDepthBinaryTree.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static int maxDepth(Node root) {
    if (root == null) return 0;
    Queue<Node> queue = new ArrayDeque<>();
    queue.add(root);
    int depth = 0;
    while (!queue.isEmpty()) {
        int levelSize = queue.size();
        for (int i = 0; i < levelSize; i++) {
            Node current = queue.remove();
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
        depth++;
    }
    return depth;
}
```

## 4. Input and expected output

```text
Depth: 3
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** Traverse one level at a time; each completed level increments depth.

## 6. Edge cases and common beginner pitfalls

An empty tree has depth zero.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Iterative breadth-first search**. Traverse one level at a time; each completed level increments depth. The running time is **O(n)**, and the extra-space requirement is **O(w), w = max level width**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** When is iterative DFS preferable?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build trees/basic/src/MaximumDepthBinaryTree.java
java -cp build trees.basic.MaximumDepthBinaryTree
```

© Dynamic Duo Learners — Program author: CodeChamps0419
