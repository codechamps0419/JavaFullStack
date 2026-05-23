# Two Sum — Find Pair with Given Sum

## Problem
Given an array and a target, return indices of two numbers that add up to the target.
Exactly one solution exists. Cannot use the same element twice.

## Approach — HashMap (One Pass)

### Why HashMap?
Brute force is O(n²). HashMap gives O(1) lookup → O(n) overall.
For each element, check if its complement (`target - arr[i]`) was already seen.

### Step-by-step logic
1. Create `map: value → index`.
2. For each index `i`:
   - `complement = target - arr[i]`.
   - If `complement` is in map → return `[map[complement], i]`.
   - Else → store `arr[i] → i` in map.

### Dry Run
```
arr = [2, 7, 11, 15], target = 9
i=0: complement=7, not in map → map={2:0}
i=1: complement=2, in map at 0 → return [0, 1] ✓
```

## Complexity

| Time  | O(n) |
| Space | O(n) |

## Alternative — Two Pointer (sorted array only)
If array is sorted, use two pointers: left=0, right=n-1.
- sum < target → left++
- sum > target → right--
- sum == target → return [left, right]
O(n) time, O(1) space — but only works on sorted arrays.

## Edge Cases
- Negative numbers → HashMap handles correctly.
- Duplicate values → HashMap stores index, not value, so it works.

## Interview Tip
LeetCode #1. Most asked interview question. Know both HashMap (unsorted) and Two Pointer (sorted) approaches.


