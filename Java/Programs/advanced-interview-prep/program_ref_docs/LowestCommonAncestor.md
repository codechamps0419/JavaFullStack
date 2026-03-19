# 27. LowestCommonAncestor

**Author:** CodeChamps0419  
**Category:** `trees.medium`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Iterative parent map + ancestor set  

## 1. Problem statement

Find the lowest common ancestor of two existing nodes in a binary tree.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

Map child to parent, collect p ancestors, then walk upward from q.

**Time complexity:** `O(n)`  
**Auxiliary space complexity:** `O(n)`  

The executable implementation is in `src/LowestCommonAncestor.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static Node lowestCommonAncestor(Node root, Node p, Node q) {
    if (root == null || p == null || q == null) return null;
    Map<Node, Node> parent = new IdentityHashMap<>();
    Deque<Node> stack = new ArrayDeque<>();
    parent.put(root, null);
    stack.push(root);
    while (!stack.isEmpty() && (!parent.containsKey(p) || !parent.containsKey(q))) {
        Node node = stack.pop();
        if (node.left != null) { parent.put(node.left, node); stack.push(node.left); }
        if (node.right != null) { parent.put(node.right, node); stack.push(node.right); }
    }
    if (!parent.containsKey(p) || !parent.containsKey(q)) return null;
    Set<Node> ancestors = Collections.newSetFromMap(new IdentityHashMap<>());
    while (p != null) { ancestors.add(p); p = parent.get(p); }
    while (!ancestors.contains(q)) q = parent.get(q);
    return q;
}
```

## 4. Input and expected output

```text
LCA(1,3): 2
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** Map child to parent, collect p ancestors, then walk upward from q.

## 6. Edge cases and common beginner pitfalls

Node identity matters; return null if either node is absent.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Iterative parent map + ancestor set**. Map child to parent, collect p ancestors, then walk upward from q. The running time is **O(n)**, and the extra-space requirement is **O(n)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** How can you find LCA faster in a BST?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build trees/medium/src/LowestCommonAncestor.java
java -cp build trees.medium.LowestCommonAncestor
```

© Dynamic Duo Learners — Program author: CodeChamps0419
