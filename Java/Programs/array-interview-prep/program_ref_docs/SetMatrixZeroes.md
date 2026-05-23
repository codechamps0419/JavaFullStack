# Set Matrix Zeroes

## Problem
If `matrix[i][j] == 0`, set the entire row `i` and column `j` to 0. Do it in-place.

## Approach — Use First Row and Column as Markers

### Why not use extra arrays?
Using boolean arrays for rows/cols costs O(m+n). We can reuse the first row and first column of the matrix itself as markers → O(1) space.

### Step-by-step logic
1. Check if the first row originally contains any zero → `firstRowZero`.
2. Check if the first column originally contains any zero → `firstColZero`.
3. Scan the rest of the matrix (rows 1+, cols 1+):
   - If `matrix[i][j] == 0` → mark `matrix[i][0] = 0` and `matrix[0][j] = 0`.
4. Use markers to zero out cells (rows 1+, cols 1+):
   - If `matrix[i][0] == 0` or `matrix[0][j] == 0` → set `matrix[i][j] = 0`.
5. Finally, zero out first row if `firstRowZero`, first column if `firstColZero`.

### Why handle first row/col separately?
They serve as our marker storage. If we zero them out early, we lose the original zero information.

### Dry Run
```
matrix = [[1,1,1],[1,0,1],[1,1,1]]
firstRowZero=false, firstColZero=false
Scan: matrix[1][1]=0 → mark matrix[1][0]=0, matrix[0][1]=0
Apply markers:
  matrix[1][0]=0 → zero row 1: [0,0,0]
  matrix[0][1]=0 → zero col 1: all rows col 1 = 0
Result: [[1,0,1],[0,0,0],[1,0,1]]
```

## Complexity


| Time  | O(m*n) |
| Space | O(1) |

## Edge Cases
- Multiple zeros → all their rows and columns get zeroed.
- Zero in first row or column → handled by `firstRowZero`/`firstColZero` flags.

## Interview Tip
LeetCode #73. The naive approach (extra boolean arrays) is O(m+n) space. The O(1) approach using the matrix itself as markers is what interviewers want to see.


