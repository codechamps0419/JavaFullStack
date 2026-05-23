# Find Subarray with Given Sum

## Problem
Given an array and a target sum, find the contiguous sub-array that adds up to the target. Return its start and end indices.

> **Interview Line** — If the array contains only positive numbers, the optimal approach is Sliding Window. If the array contains both positive and negative numbers, Prefix Sum with HashMap is the optimal approach.

---

## Solution 1 — Sliding Window ✅ Optimal for Positive Numbers Only

### Why Sliding Window?
Since all elements are non-negative, adding more elements always increases the sum and removing always decreases it. This monotonic property lets us expand and shrink a window efficiently in a single pass.

### Step-by-step logic
1. `start = 0`, `currentSum = 0`.
2. Expand window by adding `arr[end]` to `currentSum`.
3. While `currentSum > target` → shrink from left: subtract `arr[start]`, increment `start`.
4. If `currentSum == target` → found, return `[start, end]`.
5. If loop ends without match → return `[-1, -1]`.

### Dry Run
```
arr = [1, 4, 20, 3, 10, 5], target = 33

end=0: currentSum=1
end=1: currentSum=5
end=2: currentSum=25
end=3: currentSum=28
end=4: currentSum=38 > 33 → shrink: sum=38-1=37,  start=1
                           → shrink: sum=37-4=33,  start=2
       currentSum==33 → return [2, 4] ✓

Subarray: arr[2..4] = [20, 3, 10]
```

### Complexity


| Time  | O(n) | Each element added and removed at most once — `start` and `end` both move forward only. |
| Space | O(1) | Only two pointer variables and `currentSum` used. |

### ⚠️ Limitation
Only works for non-negative numbers. If array has negative numbers, shrinking the window does not guarantee sum decreases — use Solution 2.

---

## Solution 2 — Prefix Sum + HashMap ✅ Works for Positive & Negative Numbers

### Core Idea
`prefixSum[i]` = sum of elements from index 0 to i.
If `prefixSum[i] - prefixSum[j] = target`, then subarray `(j+1, i)` has sum = target.
So we look for `prefixSum[i] - target` in the map.

### Step-by-step logic
1. `prefixMap = {0: -1}` → seed with 0 to handle subarray starting from index 0.
2. `currentSum = 0`.
3. For each index `i`:
   - `currentSum += arr[i]`.
   - If `currentSum - target` exists in map → subarray found:
     - `start = prefixMap.get(currentSum - target) + 1`.
     - `end = i`.
     - Return `[start, end]`.
   - Store `currentSum → i` in map using `putIfAbsent` (keep first occurrence only).
4. If loop ends → return `[-1, -1]`.

### Dry Run
```
arr = [10, 2, -2, -20, 10], target = -10
prefixMap = {0: -1}, currentSum = 0

i=0: currentSum=10,  look for 10-(-10)=20  → not in map, map={0:-1, 10:0}
i=1: currentSum=12,  look for 12-(-10)=22  → not in map, map={0:-1, 10:0, 12:1}
i=2: currentSum=10,  look for 10-(-10)=20  → not in map, putIfAbsent(10) → already exists, skip
i=3: currentSum=-10, look for -10-(-10)=0  → found at map[0]=-1
     start = -1+1 = 0, end = 3 → return [0, 3] ✓

Subarray: arr[0..3] = [10, 2, -2, -20]
```

### Why `prefixMap.put(0, -1)`?
If the subarray starts from index 0, `currentSum - target = 0` must be in the map.
Seeding `{0: -1}` ensures `start = -1 + 1 = 0` is correctly computed.

### Why `putIfAbsent`?
We store only the first occurrence of each prefix sum to get the longest (or earliest starting) subarray.

### Complexity


| Time  | O(n) | Single pass — each element processed once with O(1) HashMap operations. |
| Space | O(n) | HashMap stores at most n distinct prefix sum entries. |

---

## Comparison

| Solution | Works For | Time | Space |

| 1 — Sliding Window | Positive numbers only | O(n) | O(1) |
| 2 — Prefix Sum + HashMap | Positive & Negative numbers | O(n) | O(n) |

---

## Edge Cases
- Target = 0 with positives → only possible if array has 0s (Solution 1). Solution 2 handles naturally.
- No subarray found → both return `[-1, -1]`.
- Subarray starts at index 0 → Solution 2 handles via `prefixMap.put(0, -1)` seed.
- All negative numbers → Solution 1 fails, Solution 2 handles correctly.

## Interview Tip
- Always clarify: "Are all elements non-negative?" — determines which solution to use.
- If yes → Sliding Window (O(1) space).
- If negatives possible → Prefix Sum + HashMap (O(n) space).
- The `prefixMap.put(0, -1)` seed is the subtle detail interviewers look for in Solution 2.


