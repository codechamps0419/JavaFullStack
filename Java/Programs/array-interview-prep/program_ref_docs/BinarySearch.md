# Binary Search on a Sorted Array

## Problem
Given a sorted array and a target, return the index of the target or -1 if not found.

## Approach — Divide and Conquer

### Why Binary Search?
Linear search is O(n). Binary search eliminates half the search space each step → O(log n).
Only works on sorted arrays.

### Step-by-step logic
1. Set `left = 0`, `right = arr.length - 1`.
2. While `left <= right`:
   - Compute `mid = left + (right - left) / 2`.
   - If `arr[mid] == target` → return `mid`.
   - If `arr[mid] < target` → target is in right half → `left = mid + 1`.
   - If `arr[mid] > target` → target is in left half → `right = mid - 1`.
3. Return `-1` if loop ends without finding target.

### Why `left + (right - left) / 2` instead of `(left + right) / 2`?
`(left + right)` can overflow for large indices. The safe formula avoids this.

### Dry Run
```
arr = [1, 3, 5, 7, 9, 11, 13], target = 7
left=0, right=6, mid=3 → arr[3]=7 == target → return 3
```

## Complexity


| Time  | O(log n) |
| Space | O(1)     |

## Edge Cases
- Target not in array → return -1.
- Single element array → works.
- Target at first or last index → works.

## Interview Tip
Always use `left + (right - left) / 2` — mention overflow prevention. This shows attention to detail.


