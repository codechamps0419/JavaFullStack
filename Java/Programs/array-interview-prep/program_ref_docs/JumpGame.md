# Jump Game — Can You Reach the Last Index?

## Problem
Given an array where each element represents the maximum jump length from that position,
determine if you can reach the last index starting from index 0.

## Approach — Greedy (Track Max Reachable Index)

### Core Idea
At each index, update the farthest index we can reach.
If at any point the current index exceeds `maxReach`, we're stuck — return false.

### Step-by-step logic
1. `maxReach = 0`.
2. For each index `i`:
   - If `i > maxReach` → index `i` is unreachable → return false.
   - Update `maxReach = max(maxReach, i + nums[i])`.
3. If loop completes → last index is reachable → return true.

### Dry Run
```
nums = [2, 3, 1, 1, 4]
i=0: 0<=0, maxReach=max(0,0+2)=2
i=1: 1<=2, maxReach=max(2,1+3)=4
i=2: 2<=4, maxReach=max(4,2+1)=4
i=3: 3<=4, maxReach=max(4,3+1)=4
i=4: 4<=4, maxReach=max(4,4+4)=8
Loop ends → return true ✓

nums = [3, 2, 1, 0, 4]
i=0: maxReach=3
i=1: maxReach=max(3,3)=3
i=2: maxReach=max(3,3)=3
i=3: maxReach=max(3,3)=3
i=4: 4 > 3 → return false ✓
```

## Complexity


| Time  | O(n) |
| Space | O(1) |

## Follow-up — Minimum Jumps (LeetCode #45)
Track jumps greedily: when you reach the end of current jump range, increment jump count and extend range.

## Edge Cases
- Single element → already at last index → true.
- First element is 0 and array length > 1 → false.
- All zeros → false (unless single element).

## Interview Tip
LeetCode #55. The greedy insight: "We don't care which path we take — we only care about the farthest we can reach." This avoids backtracking/DP entirely.


