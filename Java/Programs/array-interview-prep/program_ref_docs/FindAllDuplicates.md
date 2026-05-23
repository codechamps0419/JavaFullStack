# Find All Duplicates in an Array

## Problem
Given an array of n integers where each value is in range [1, n] and each element appears once or twice,
find all elements that appear twice. O(n) time, O(1) space (no HashSet).

## Approach — Index Marking (Negate as Visited Flag)

### Core Idea
Since values are in range [1, n], each value `v` maps to index `v-1`.
Use the sign of `arr[v-1]` as a visited flag:
- First visit → negate `arr[v-1]` (mark visited).
- Second visit → `arr[v-1]` is already negative → it's a duplicate.

### Step-by-step logic
1. For each `num` in array:
   - `idx = abs(num) - 1` (use abs because num might already be negated).
   - If `arr[idx] < 0` → already visited → `num` is a duplicate → add to result.
   - Else → negate `arr[idx]` to mark as visited.

### Dry Run
```
arr = [4, 3, 2, 7, 8, 2, 3, 1]
num=4: idx=3, arr[3]=7>0 → negate → arr=[4,3,2,-7,8,2,3,1]
num=3: idx=2, arr[2]=2>0 → negate → arr=[4,3,-2,-7,8,2,3,1]
num=2: idx=1, arr[1]=3>0 → negate → arr=[4,-3,-2,-7,8,2,3,1]
num=-7: idx=6, arr[6]=3>0 → negate → arr=[4,-3,-2,-7,8,2,-3,1]
num=8: idx=7, arr[7]=1>0 → negate → arr=[4,-3,-2,-7,8,2,-3,-1]
num=2: idx=1, arr[1]=-3<0 → DUPLICATE → add 2
num=-3: idx=2, arr[2]=-2<0 → DUPLICATE → add 3
num=-1: idx=0, arr[0]=4>0 → negate
Result: [2, 3]
```

## Complexity


| Time  | O(n) |
| Space | O(1) excluding output list |

## Edge Cases
- No duplicates → empty list.
- All elements duplicated → list of all values.

## Interview Tip
LeetCode #442. The trick of using array index as a hash map (via negation) is a classic space optimization pattern. Also works for "Find the Duplicate Number" (LeetCode #287).


