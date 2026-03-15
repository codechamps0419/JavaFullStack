# Find First and Last Position of Target in Sorted Array

## Problem
Given a sorted array with duplicates, find the first and last index of a target value.
Return `[-1, -1]` if not found. Must run in O(log n).

## Approach — Binary Search Twice

### Why two binary searches?
One binary search finds any occurrence. We need to specifically find the leftmost and rightmost.
We run binary search twice — once biased left, once biased right.

### findFirst logic
- When `arr[mid] == target` → record `result = mid`, then go LEFT (`right = mid - 1`) to find earlier occurrence.

### findLast logic
- When `arr[mid] == target` → record `result = mid`, then go RIGHT (`left = mid + 1`) to find later occurrence.

### Dry Run
```
arr = [5, 7, 7, 8, 8, 10], target = 8

findFirst:
left=0, right=5, mid=2 → arr[2]=7 < 8 → left=3
left=3, right=5, mid=4 → arr[4]=8 == 8 → result=4, right=3
left=3, right=3, mid=3 → arr[3]=8 == 8 → result=3, right=2
left=3 > right=2 → stop → first = 3

findLast:
left=0, right=5, mid=2 → arr[2]=7 < 8 → left=3
left=3, right=5, mid=4 → arr[4]=8 == 8 → result=4, left=5
left=5, right=5, mid=5 → arr[5]=10 > 8 → right=4
left=5 > right=4 → stop → last = 4

Result: [3, 4]
```

## Complexity


| Time  | O(log n) — two binary searches |
| Space | O(1) |

## Edge Cases
- Target not in array → `[-1, -1]`.
- Single occurrence → first == last.
- All elements are the target → `[0, n-1]`.

## Interview Tip
LeetCode #34. Key insight: don't return immediately when target is found — keep narrowing the search window.


