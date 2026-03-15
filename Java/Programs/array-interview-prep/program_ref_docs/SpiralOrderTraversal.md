# Spiral Order Traversal of a Matrix

## Problem
Given an m×n matrix, return all elements in spiral order (clockwise from outer to inner).

## Approach — Boundary Shrinking

### Four boundaries
- `top` → current top row
- `bottom` → current bottom row
- `left` → current left column
- `right` → current right column

After traversing each side, shrink the corresponding boundary inward.

### Step-by-step logic (one full loop = one layer)
1. Traverse left → right along `top` row, then `top++`.
2. Traverse top → bottom along `right` column, then `right--`.
3. If `top <= bottom`: traverse right → left along `bottom` row, then `bottom--`.
4. If `left <= right`: traverse bottom → top along `left` column, then `left++`.
5. Repeat until `top > bottom` or `left > right`.

### Dry Run
```
matrix = [[1,2,3],[4,5,6],[7,8,9]]
top=0,bottom=2,left=0,right=2

Round 1:
→ top row:    1,2,3  (top=1)
↓ right col:  6,9    (right=1)
← bottom row: 8,7    (bottom=1)
↑ left col:   4      (left=1)

Round 2:
→ top row:    5      (top=2)
top>bottom → stop

Result: [1,2,3,6,9,8,7,4,5]
```

## Complexity


| Time  | O(m*n) — every element visited once |
| Space | O(1) excluding output list |

## Edge Cases
- Single row → only left-to-right traversal.
- Single column → only top-to-bottom traversal.
- Non-square matrix → boundary checks (`top <= bottom`, `left <= right`) handle it.

## Interview Tip
LeetCode #54. The boundary checks before step 3 and 4 prevent double-counting rows/columns in non-square matrices.


