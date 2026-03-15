# Minimum Size Subarray with Sum >= k

## Problem
Find the minimum length of a contiguous subarray whose sum is >= target. Return 0 if none exists.

## Approach — Sliding Window

### Why Sliding Window?
All elements are positive → adding elements increases sum, removing decreases it.
This monotonic property allows a dynamic window that expands and shrinks efficiently.

### Step-by-step logic
1. `left = 0`, `currentSum = 0`, `minLen = MAX_VALUE`.
2. Expand window by adding `arr[right]` to `currentSum`.
3. While `currentSum >= target`:
   - Update `minLen = min(minLen, right - left + 1)`.
   - Shrink from left: subtract `arr[left]`, increment `left`.
4. Return `minLen` (or 0 if never updated).

### Dry Run
```
arr = [2,3,1,2,4,3], target = 7
right=0: sum=2
right=1: sum=5
right=2: sum=6
right=3: sum=8 >= 7 → minLen=4, shrink: sum=6, left=1
right=4: sum=10 >= 7 → minLen=4, shrink: sum=7 >= 7 → minLen=3, shrink: sum=4, left=3
right=5: sum=7 >= 7 → minLen=2, shrink: sum=5, left=4
Result: 2 → subarray [4,3]
```

## Complexity


| Time  | O(n) — each element added and removed at most once |
| Space | O(1) |

## Edge Cases
- No subarray found → return 0.
- Single element >= target → return 1.
- Entire array needed → return n.

## Interview Tip
LeetCode #209. If asked for O(n log n) solution (for follow-up), use binary search on prefix sums.


