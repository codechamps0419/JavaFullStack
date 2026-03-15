# Longest Subarray with Sum = K

## Problem
Find the length of the longest contiguous subarray whose sum equals K. Array can have negative numbers.

## Approach — Prefix Sum + HashMap

### Core Idea
`prefixSum[i]` = sum of elements from index 0 to i.
If `prefixSum[i] - prefixSum[j] = k`, then subarray `(j+1, i)` has sum = k.
So we look for `prefixSum[i] - k` in the map.

### Step-by-step logic
1. `prefixSum = 0`, `maxLen = 0`, `map = {}`.
2. For each index `i`:
   - Add `arr[i]` to `prefixSum`.
   - If `prefixSum == k` → entire subarray `[0..i]` has sum k → `maxLen = i + 1`.
   - If `prefixSum - k` exists in map → subarray from `map[prefixSum-k]+1` to `i` has sum k → update `maxLen`.
   - Store `prefixSum → i` in map only if not already present (we want the earliest index for longest subarray).

### Dry Run
```
arr = [10, 5, 2, 7, 1, 9], k = 15
i=0: sum=10, map={10:0}
i=1: sum=15, sum==k → maxLen=2, map={10:0,15:1}
i=2: sum=17, 17-15=2 not in map, map={...,17:2}
i=3: sum=24, 24-15=9 not in map, map={...,24:3}
i=4: sum=25, 25-15=10 in map at 0 → len=4-0=4, maxLen=4
i=5: sum=34, 34-15=19 not in map
Result: 4 → subarray [5,2,7,1]
```

## Complexity


| Time  | O(n) |
| Space | O(n) |

## Edge Cases
- Negative numbers → handled correctly by prefix sum.
- Multiple subarrays with sum k → returns the longest.
- `putIfAbsent` is critical — ensures we keep the earliest index for maximum length.

## Interview Tip
The `putIfAbsent` is the subtle but critical detail. If you overwrite with latest index, you get shorter subarrays.


