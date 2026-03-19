# 37. RottingOranges

**Author:** CodeChamps0419  
**Category:** `graphs`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Multi-source breadth-first search  

## 1. Problem statement

Find minutes to rot all reachable fresh oranges, or -1.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

Each BFS level represents simultaneous spreading over one minute.

**Time complexity:** `O(rows * cols)`  
**Auxiliary space complexity:** `O(rows * cols)`  

The executable implementation is in `src/RottingOranges.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static int minutesToRot(int[][] grid) {
    int rows = grid.length, cols = grid[0].length, fresh = 0, minutes = 0;
    Queue<int[]> queue = new ArrayDeque<>();
    for (int r=0;r<rows;r++) for(int c=0;c<cols;c++) {
        if (grid[r][c] == 2) queue.add(new int[]{r,c});
        if (grid[r][c] == 1) fresh++;
    }
    int[][] directions={{1,0},{-1,0},{0,1},{0,-1}};
    while (fresh>0 && !queue.isEmpty()) {
        int count=queue.size();
        for(int i=0;i<count;i++) {
            int[] cell=queue.remove();
            for(int[] d:directions) {
                int r=cell[0]+d[0], c=cell[1]+d[1];
                if(r>=0 && r<rows && c>=0 && c<cols && grid[r][c]==1) {
                    grid[r][c]=2; fresh--; queue.add(new int[]{r,c});
                }
            }
        }
        minutes++;
    }
    return fresh==0?minutes:-1;
}
```

## 4. Input and expected output

```text
Minutes: 4
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** Each BFS level represents simultaneous spreading over one minute.

## 6. Edge cases and common beginner pitfalls

Grid mutation is intentional; validate rectangular nonempty input.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Multi-source breadth-first search**. Each BFS level represents simultaneous spreading over one minute. The running time is **O(rows * cols)**, and the extra-space requirement is **O(rows * cols)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** Why start BFS with all initially rotten oranges?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build graphs/src/RottingOranges.java
java -cp build graphs.RottingOranges
```

© Dynamic Duo Learners — Program author: CodeChamps0419
