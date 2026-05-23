# Merge Two Sorted Arrays Without Extra Space

## Problem
Given two sorted arrays `arr1` (size m) and `arr2` (size n), merge them in-place so that
`arr1` holds the first m smallest elements and `arr2` holds the remaining n elements — both sorted.

## Approach — Gap Method (Shell Sort based)

### Why Gap Method?
- Naive approach: insert elements from arr2 into arr1 → O(m*n), O(1) space.
- Extra array: O(m+n) space — not allowed.
- Gap method: O((m+n) log(m+n)) time, O(1) space — best in-place solution.

### Step-by-step logic
1. Treat both arrays as one virtual array of size `m + n`.
2. Start with `gap = ceil((m+n) / 2)`.
3. Compare elements at distance `gap` apart across the virtual array.
4. If left > right → swap them.
5. Reduce gap: `gap = ceil(gap / 2)`. Repeat until `gap == 1`.

### Virtual index mapping
- Index `i < m` → `arr1[i]`
- Index `i >= m` → `arr2[i - m]`

### Dry Run
```
arr1=[1,3], arr2=[0,2,4]  → virtual=[1,3,0,2,4], m+n=5
gap=3: compare (0,3),(1,4) → swap if needed
gap=2: compare (0,2),(1,3),(2,4)
gap=1: compare all adjacent pairs
Result: arr1=[0,1], arr2=[2,3,4]
```

## Complexity


| Time  | O((m+n) log(m+n)) |
| Space | O(1) |

## Edge Cases
- One array is empty → no changes needed.
- Already merged order → no swaps happen.

## Interview Tip
Most candidates give the O(m*n) naive solution. Knowing the Gap Method sets you apart.
Mention it's derived from Shell Sort's gap reduction strategy.


