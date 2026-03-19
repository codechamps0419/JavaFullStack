# 36. NumberOfIslands

**Author:** CodeChamps0419  
**Category:** `graphs`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Iterative BFS flood fill  

## 1. Problem statement

Count connected 4-directional groups of 1 in a binary grid.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

Start BFS on each unvisited land cell and mark all connected land as visited.

**Time complexity:** `O(rows * cols)`  
**Auxiliary space complexity:** `O(rows * cols) worst case`  

The executable implementation is in `src/NumberOfIslands.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static int countIslands(char[][] grid) {
    if (grid.length == 0) return 0;
    int rows = grid.length, cols = grid[0].length, islands = 0;
    int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
    for (int r = 0; r < rows; r++) for (int c = 0; c < cols; c++) {
        if (grid[r][c] != '1') continue;
        islands++;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{r,c}); grid[r][c] = '0';
        while (!queue.isEmpty()) {
            int[] cell = queue.remove();
            for (int[] d : directions) {
                int nr = cell[0]+d[0], nc = cell[1]+d[1];
                if (nr>=0 && nr<rows && nc>=0 && nc<cols && grid[nr][nc]=='1') {
                    grid[nr][nc]='0'; queue.add(new int[]{nr,nc});
                }
            }
        }
    }
    return islands;
}
```

## 4. Input and expected output

```text
Islands: 3
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** Start BFS on each unvisited land cell and mark all connected land as visited.

## 6. Edge cases and common beginner pitfalls

Implementation mutates grid; clone it if input must stay intact.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Iterative BFS flood fill**. Start BFS on each unvisited land cell and mark all connected land as visited. The running time is **O(rows * cols)**, and the extra-space requirement is **O(rows * cols) worst case**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** How to handle diagonal adjacency?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build graphs/src/NumberOfIslands.java
java -cp build graphs.NumberOfIslands
```

© Dynamic Duo Learners — Program author: CodeChamps0419
