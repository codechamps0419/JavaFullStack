# Three Sum — Find All Triplets that Sum to Zero

## Problem
Find all unique triplets in the array that sum to zero. No duplicate triplets in output.

## Approach — Sort + Two Pointer

### Why sort first?
Sorting enables two-pointer technique and makes duplicate skipping easy.

### Step-by-step logic
1. Sort the array.
2. Fix element at index `i` (outer loop).
3. Use two pointers `left = i+1`, `right = n-1` to find pairs summing to `-arr[i]`.
4. If `sum == 0` → add triplet, skip duplicates for both pointers, move both inward.
5. If `sum < 0` → need larger value → `left++`.
6. If `sum > 0` → need smaller value → `right--`.
7. Skip duplicate values for `i` to avoid duplicate triplets.

### Dry Run
```
arr = [-4,-1,-1,0,1,2] (sorted)
i=0(-4): left=1,right=5 → sum=-4-1+2=-3<0→left++; sum=-4+0+2=-2<0→left++; ...no triplet
i=1(-1): left=2,right=5 → sum=-1-1+2=0 → add[-1,-1,2], skip dup, left=3,right=4
         left=3,right=4 → sum=-1+0+1=0 → add[-1,0,1], left=4,right=3 → stop
i=2(-1): arr[2]==arr[1] → skip (duplicate)
i=3(0):  left=4,right=5 → sum=0+1+2=3>0 → right--; left>=right → stop
Result: [[-1,-1,2],[-1,0,1]]
```

## Complexity


| Time  | O(n²) — O(n log n) sort + O(n²) two pointer |
| Space | O(1) excluding output list |

## Edge Cases
- Less than 3 elements → return empty.
- All zeros → `[0,0,0]` is one triplet.
- All positive or all negative → no triplets.

## Interview Tip
LeetCode #15. The duplicate-skipping logic is what trips people up. Practice explaining it clearly.


