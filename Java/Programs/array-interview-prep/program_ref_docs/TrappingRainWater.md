# Trapping Rain Water

## Problem
Given an array representing elevation heights, compute how much rainwater can be trapped between the bars.

## Approach — Two Pointer (Optimal)

### Core Idea
Water trapped at index `i` = `min(maxLeft[i], maxRight[i]) - height[i]`.
Instead of precomputing left/right max arrays (O(n) space), use two pointers to compute on the fly.

### Why process the smaller side?
If `height[left] < height[right]`, the water at `left` is determined by `leftMax` (not rightMax),
because we know there's a wall >= height[right] on the right side.

### Step-by-step logic
1. `left=0`, `right=n-1`, `leftMax=0`, `rightMax=0`, `water=0`.
2. While `left < right`:
   - If `height[left] < height[right]`:
     - If `height[left] >= leftMax` → update `leftMax` (no water, it's a new max wall).
     - Else → `water += leftMax - height[left]` (trapped water).
     - `left++`.
   - Else (process right side symmetrically):
     - If `height[right] >= rightMax` → update `rightMax`.
     - Else → `water += rightMax - height[right]`.
     - `right--`.

### Dry Run
```
height = [0,1,0,2,1,0,1,3,2,1,2,1]
left=0(0), right=11(1): 0<1 → leftMax=0, water+=0-0=0, left=1
left=1(1), right=11(1): 1>=1 → process right: rightMax=1, right=10
left=1(1), right=10(2): 1<2 → leftMax=1, left=2
left=2(0), right=10(2): 0<2 → water+=1-0=1, left=3
...
Total water = 6
```

## Complexity


| Time  | O(n) |
| Space | O(1) |

## Alternative — Prefix/Suffix Max Arrays
O(n) time, O(n) space. Easier to understand but uses extra space.

## Edge Cases
- All same height → no water trapped.
- Monotonically increasing/decreasing → no water trapped.
- Single or two bars → no water.

## Interview Tip
LeetCode #42. One of the most asked hard problems. Start with the O(n) space approach to show understanding, then optimize to two pointers.


