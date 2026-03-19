# 39. UndirectedGraphCycle

**Author:** CodeChamps0419  
**Category:** `graphs`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Iterative BFS with parent tracking  

## 1. Problem statement

Detect a cycle in an undirected graph adjacency list.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

Track parent so the reverse edge to the parent is not mistaken for a cycle.

**Time complexity:** `O(V + E)`  
**Auxiliary space complexity:** `O(V)`  

The executable implementation is in `src/UndirectedGraphCycle.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
record Visit(int node, int parent) { }
static boolean hasCycle(List<List<Integer>> graph) {
    boolean[] visited = new boolean[graph.size()];
    for(int start=0;start<graph.size();start++) {
        if(visited[start]) continue;
        Queue<Visit> queue=new ArrayDeque<>();
        visited[start]=true; queue.add(new Visit(start,-1));
        while(!queue.isEmpty()) {
            Visit v=queue.remove();
            for(int next:graph.get(v.node())) {
                if(!visited[next]) {
                    visited[next]=true;
                    queue.add(new Visit(next,v.node()));
                } else if(next!=v.parent()) return true;
            }
        }
    }
    return false;
}
```

## 4. Input and expected output

```text
Cycle: true
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** Track parent so the reverse edge to the parent is not mistaken for a cycle.

## 6. Edge cases and common beginner pitfalls

Assumes simple undirected graph with symmetric adjacency lists.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Iterative BFS with parent tracking**. Track parent so the reverse edge to the parent is not mistaken for a cycle. The running time is **O(V + E)**, and the extra-space requirement is **O(V)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** How does directed-cycle detection differ?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build graphs/src/UndirectedGraphCycle.java
java -cp build graphs.UndirectedGraphCycle
```

© Dynamic Duo Learners — Program author: CodeChamps0419
