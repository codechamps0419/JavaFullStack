# Next Permutation

## Problem
Rearrange the array into the lexicographically next greater permutation in-place.
If already the largest, rearrange to the smallest (sorted ascending).

## Approach — Find Pivot + Swap + Reverse

### Key Observations
- The suffix in descending order is already the largest permutation of those elements.
- To get the next permutation, find where the descending suffix begins (the "pivot").
- Swap pivot with the smallest element in the suffix that's still greater than pivot.
- Reverse the suffix to make it the smallest possible arrangement.

### Step-by-step logic
1. Scan right to left to find `pivot` — last index where `arr[i] < arr[i+1]`.
2. If no pivot → array is fully descending → reverse entire array (wrap to smallest).
3. Scan right to left to find the rightmost element > `arr[pivot]` → swap with pivot.
4. Reverse the suffix from `pivot+1` to end (it's descending, reversing makes it ascending = smallest).

### Dry Run
```
arr = [1, 5, 8, 4, 7, 6, 5, 3, 1]
Step 1: Find pivot → arr[3]=4 < arr[4]=7 → pivot=3
Step 2: Find rightmost > arr[3]=4 in suffix → arr[7]=5 → swap(3,7)
        arr = [1, 5, 8, 5, 7, 6, 4, 3, 1]
Step 3: Reverse suffix from index 4 → [1,3,4,6,7]
        arr = [1, 5, 8, 5, 1, 3, 4, 6, 7] ✓
```

## Complexity


| Time  | O(n) |
| Space | O(1) |

## Edge Cases
- `[3,2,1]` → no pivot → reverse → `[1,2,3]`.
- `[1,2,3]` → pivot=1 → swap(1,2) → reverse → `[1,3,2]`.
- Single element → no change.

## Interview Tip
LeetCode #31. The three-step pattern (find pivot, swap, reverse) is the key. Practice explaining each step's "why" clearly.


