# Rotate a Matrix 90 Degrees Clockwise In-Place

## Problem
Given an n×n matrix, rotate it 90 degrees clockwise in-place (no extra matrix).

## Approach — Transpose + Reverse Each Row

### Why this works?
A 90° clockwise rotation = Transpose + Reverse each row.
- Transpose: `matrix[i][j]` ↔ `matrix[j][i]`
- Reverse rows: flip each row left-to-right.

### Step-by-step logic
**Step 1 — Transpose:**
Swap `matrix[i][j]` with `matrix[j][i]` for all `j > i` (upper triangle only to avoid double-swap).

**Step 2 — Reverse each row:**
For each row, use two pointers to reverse in-place.

### Dry Run
```
Original:
1 2 3
4 5 6
7 8 9

After Transpose:
1 4 7
2 5 8
3 6 9

After Reverse each row:
7 4 1
8 5 2
9 6 3  ← 90° clockwise ✓
```

### For 90° Counter-Clockwise:
Reverse each row first, then transpose.

## Complexity


| Time  | O(n²) |
| Space | O(1) |

## Edge Cases
- 1×1 matrix → no change.
- 2×2 matrix → works correctly.

## Interview Tip
LeetCode #48. Memorize: "Clockwise = Transpose then Reverse rows. Counter-clockwise = Reverse rows then Transpose."
Draw it out on paper during the interview to confirm.


