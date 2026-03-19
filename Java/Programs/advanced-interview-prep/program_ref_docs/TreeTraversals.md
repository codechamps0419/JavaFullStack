# 21. TreeTraversals

**Author:** CodeChamps0419  
**Category:** `trees.basic`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Iterative stack traversals  

## 1. Problem statement

Print inorder, preorder, and postorder of a binary tree.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

Use stacks to control visit order without recursive calls.

**Time complexity:** `O(n) each`  
**Auxiliary space complexity:** `O(h) to O(n)`  

The executable implementation is in `src/TreeTraversals.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static List<Integer> inorder(Node root) {
    List<Integer> result = new ArrayList<>();
    Deque<Node> stack = new ArrayDeque<>();
    Node cur = root;
    while (cur != null || !stack.isEmpty()) {
        while (cur != null) { stack.push(cur); cur = cur.left; }
        cur = stack.pop();
        result.add(cur.value);
        cur = cur.right;
    }
    return result;
}
static List<Integer> preorder(Node root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) return result;
    Deque<Node> stack = new ArrayDeque<>();
    stack.push(root);
    while (!stack.isEmpty()) {
        Node node = stack.pop();
        result.add(node.value);
        if (node.right != null) stack.push(node.right);
        if (node.left != null) stack.push(node.left);
    }
    return result;
}
static List<Integer> postorder(Node root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) return result;
    Deque<Node> stack = new ArrayDeque<>();
    Deque<Node> out = new ArrayDeque<>();
    stack.push(root);
    while (!stack.isEmpty()) {
        Node node = stack.pop();
        out.push(node);
        if (node.left != null) stack.push(node.left);
        if (node.right != null) stack.push(node.right);
    }
    while (!out.isEmpty()) result.add(out.pop().value);
    return result;
}
```

## 4. Input and expected output

```text
Inorder  : [1, 2, 3, 4, 5, 6, 7]
Preorder : [4, 2, 1, 3, 6, 5, 7]
Postorder: [1, 3, 2, 5, 7, 6, 4]
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** Use stacks to control visit order without recursive calls.

## 6. Edge cases and common beginner pitfalls

For skewed trees auxiliary space reaches O(n).

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Iterative stack traversals**. Use stacks to control visit order without recursive calls. The running time is **O(n) each**, and the extra-space requirement is **O(h) to O(n)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** Can you implement postorder with one stack?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build trees/basic/src/TreeTraversals.java
java -cp build trees.basic.TreeTraversals
```

© Dynamic Duo Learners — Program author: CodeChamps0419
