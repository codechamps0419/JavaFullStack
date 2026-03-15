# Search in a Row-wise and Column-wise Sorted Matrix

## Problem
Given a matrix where each row and each column is sorted in ascending order, search for a target value efficiently.

## Approach — Start from Top-Right Corner

### Why top-right corner?
- Moving left → value decreases (same row, sorted left to right).
- Moving down → value increases (same column, sorted top to bottom).
- This gives us a binary decision at each step: eliminate a row or a column.

### Step-by-step logic
1. Start at `row = 0`, `col = n - 1` (top-right).
2. While `row < m` and `col >= 0`:
   - If `matrix[row][col] == target` → found, return true.
   - If `matrix[row][col] > target` → target must be to the left → `col--`.
   - If `matrix[row][col] < target` → target must be below → `row++`.
3. Return false if loop ends.

### Dry Run
```
matrix:
10 20 30 40
15 25 35 45
27 29 37 48
32 33 39 50
target = 29

Start: row=0, col=3 → 40 > 29 → col=2
row=0, col=2 → 30 > 29 → col=1
row=0, col=1 → 20 < 29 → row=1
row=1, col=1 → 25 < 29 → row=2
row=2, col=1 → 29 == 29 → return true ✓
```

## Complexity


| Time  | O(m + n) — at most m+n steps |
| Space | O(1) |

## Edge Cases
- Target smaller than all elements → col goes to -1.
- Target larger than all elements → row goes to m.
- Single element matrix → works.

## Interview Tip
You can also start from bottom-left (same logic, mirrored). Never start from top-left or bottom-right — those corners don't give a clear elimination direction.


