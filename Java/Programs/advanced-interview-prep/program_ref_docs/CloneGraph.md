# 38. CloneGraph

**Author:** CodeChamps0419  
**Category:** `graphs`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Iterative BFS with identity mapping  

## 1. Problem statement

Deep-copy an undirected graph with cycles.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

Map original nodes to cloned nodes before traversing neighbors, preventing infinite cycles.

**Time complexity:** `O(V + E)`  
**Auxiliary space complexity:** `O(V)`  

The executable implementation is in `src/CloneGraph.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static class Node {
    int value;
    List<Node> neighbors = new ArrayList<>();
    Node(int value) { this.value=value; }
}
static Node cloneGraph(Node start) {
    if (start==null) return null;
    Map<Node,Node> copies = new IdentityHashMap<>();
    Queue<Node> queue = new ArrayDeque<>();
    copies.put(start,new Node(start.value)); queue.add(start);
    while(!queue.isEmpty()) {
        Node original=queue.remove();
        for(Node neighbor:original.neighbors) {
            if(!copies.containsKey(neighbor)) {
                copies.put(neighbor,new Node(neighbor.value));
                queue.add(neighbor);
            }
            copies.get(original).neighbors.add(copies.get(neighbor));
        }
    }
    return copies.get(start);
}
```

## 4. Input and expected output

```text
Cloned value: 1
Different object: true
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** Map original nodes to cloned nodes before traversing neighbors, preventing infinite cycles.

## 6. Edge cases and common beginner pitfalls

Preserve adjacency including duplicates and self-loops.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Iterative BFS with identity mapping**. Map original nodes to cloned nodes before traversing neighbors, preventing infinite cycles. The running time is **O(V + E)**, and the extra-space requirement is **O(V)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** Why use object identity rather than node value?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build graphs/src/CloneGraph.java
java -cp build graphs.CloneGraph
```

© Dynamic Duo Learners — Program author: CodeChamps0419
