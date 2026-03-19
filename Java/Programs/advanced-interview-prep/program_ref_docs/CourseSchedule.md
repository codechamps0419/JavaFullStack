# 40. CourseSchedule

**Author:** CodeChamps0419  
**Category:** `graphs`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Kahn topological sort with indegrees  

## 1. Problem statement

Determine whether all courses can be completed given prerequisite edges.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

Take indegree-zero courses, removing their outgoing dependency edges.

**Time complexity:** `O(V + E)`  
**Auxiliary space complexity:** `O(V + E)`  

The executable implementation is in `src/CourseSchedule.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static boolean canFinish(int courses, int[][] prerequisites) {
    List<List<Integer>> graph=new ArrayList<>();
    for(int i=0;i<courses;i++) graph.add(new ArrayList<>());
    int[] indegree=new int[courses];
    for(int[] p:prerequisites) { graph.get(p[1]).add(p[0]); indegree[p[0]]++; }
    Queue<Integer> ready=new ArrayDeque<>();
    for(int i=0;i<courses;i++) if(indegree[i]==0) ready.add(i);
    int finished=0;
    while(!ready.isEmpty()) {
        int course=ready.remove(); finished++;
        for(int dependent:graph.get(course)) if(--indegree[dependent]==0) ready.add(dependent);
    }
    return finished==courses;
}
```

## 4. Input and expected output

```text
Can finish: true
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** Take indegree-zero courses, removing their outgoing dependency edges.

## 6. Edge cases and common beginner pitfalls

Cycles leave unfinished courses with positive indegree.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Kahn topological sort with indegrees**. Take indegree-zero courses, removing their outgoing dependency edges. The running time is **O(V + E)**, and the extra-space requirement is **O(V + E)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** How can you return an actual course order?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build graphs/src/CourseSchedule.java
java -cp build graphs.CourseSchedule
```

© Dynamic Duo Learners — Program author: CodeChamps0419
