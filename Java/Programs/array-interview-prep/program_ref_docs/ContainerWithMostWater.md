# Container with Most Water

## Problem
Given an array where each element represents a vertical line's height, find two lines that together with the x-axis form a container holding the most water.

## Approach — Two Pointer (Greedy)

### Water formula
`area = min(height[left], height[right]) * (right - left)`

### Why move the shorter line?
- Width decreases as we move pointers inward.
- To potentially increase area, we need a taller line.
- Moving the taller line inward can only decrease or maintain the min height → area can only decrease.
- Moving the shorter line inward gives a chance to find a taller line → area might increase.

### Step-by-step logic
1. `left = 0`, `right = n - 1`.
2. While `left < right`:
   - Calculate `area = min(height[left], height[right]) * (right - left)`.
   - Update `maxArea`.
   - Move the pointer with the shorter height inward.

### Dry Run
```
height = [1,8,6,2,5,4,8,3,7]
left=0(1), right=8(7): area=min(1,7)*8=8,  maxArea=8,  move left (1<7)
left=1(8), right=8(7): area=min(8,7)*7=49, maxArea=49, move right (8>7)
left=1(8), right=7(3): area=min(8,3)*6=18, maxArea=49, move right
left=1(8), right=6(8): area=min(8,8)*5=40, maxArea=49, move right
...
Result: 49
```

## Complexity


| Time  | O(n) |
| Space | O(1) |

## Edge Cases
- Two elements → only one container possible.
- All same heights → area = height * (n-1).

## Interview Tip
LeetCode #11. The key insight to explain: "We move the shorter line because keeping it can never give us a better answer — the width only decreases from here."


