# 26. ValidateBinarySearchTree

**Author:** CodeChamps0419  
**Category:** `trees.medium`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Iterative inorder traversal  

## 1. Problem statement

Check that each left-subtree value is smaller and each right-subtree value is greater.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

A strict BST yields a strictly increasing inorder traversal.

**Time complexity:** `O(n)`  
**Auxiliary space complexity:** `O(h)`  

The executable implementation is in `src/ValidateBinarySearchTree.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static boolean isValidBST(Node root) {
    Deque<Node> stack = new ArrayDeque<>();
    Node current = root;
    long previous = Long.MIN_VALUE;
    while (current != null || !stack.isEmpty()) {
        while (current != null) { stack.push(current); current = current.left; }
        current = stack.pop();
        if (current.value <= previous) return false;
        previous = current.value;
        current = current.right;
    }
    return true;
}
```

## 4. Input and expected output

```text
Valid BST: true
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** A strict BST yields a strictly increasing inorder traversal.

## 6. Edge cases and common beginner pitfalls

Using long sentinel supports Integer.MIN_VALUE nodes.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Iterative inorder traversal**. A strict BST yields a strictly increasing inorder traversal. The running time is **O(n)**, and the extra-space requirement is **O(h)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** Why are immediate-parent comparisons insufficient?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build trees/medium/src/ValidateBinarySearchTree.java
java -cp build trees.medium.ValidateBinarySearchTree
```

© Dynamic Duo Learners — Program author: CodeChamps0419
