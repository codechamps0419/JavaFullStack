# 29. KthSmallestBST

**Author:** CodeChamps0419  
**Category:** `trees.medium`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Iterative inorder with counter  

## 1. Problem statement

Return the kth smallest value in a binary search tree.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

Inorder traverses BST values ascending; stop after kth visited node.

**Time complexity:** `O(h + k)`  
**Auxiliary space complexity:** `O(h)`  

The executable implementation is in `src/KthSmallestBST.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static int kthSmallest(Node root, int k) {
    if (k <= 0) throw new IllegalArgumentException("k must be positive");
    Deque<Node> stack = new ArrayDeque<>();
    Node cur = root;
    while (cur != null || !stack.isEmpty()) {
        while (cur != null) { stack.push(cur); cur = cur.left; }
        cur = stack.pop();
        if (--k == 0) return cur.value;
        cur = cur.right;
    }
    throw new IllegalArgumentException("k exceeds node count");
}
```

## 4. Input and expected output

```text
3rd smallest: 3
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** Inorder traverses BST values ascending; stop after kth visited node.

## 6. Edge cases and common beginner pitfalls

If tree is not a BST, result is not kth smallest.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Iterative inorder with counter**. Inorder traverses BST values ascending; stop after kth visited node. The running time is **O(h + k)**, and the extra-space requirement is **O(h)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** How to support frequent kth queries with subtree sizes?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build trees/medium/src/KthSmallestBST.java
java -cp build trees.medium.KthSmallestBST
```

© Dynamic Duo Learners — Program author: CodeChamps0419
