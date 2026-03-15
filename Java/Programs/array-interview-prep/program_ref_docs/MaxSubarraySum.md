# Maximum Subarray Sum — Kadane's Algorithm

## Problem
Find the contiguous subarray with the largest sum and return the sum along with its start and end indices.

---

## Approach — Kadane's Algorithm with Index Tracking (Dynamic Programming)

### Core Idea
At each index, decide: should I extend the previous subarray or start fresh?
- If `arr[i] > currentSum + arr[i]` → restart from `arr[i]` (previous sum was dragging us down).
- Else → extend existing subarray by adding `arr[i]`.

Whenever a new maximum is found, confirm the `start` and `end` indices.

### Step-by-step logic
1. Initialize `currentSum = arr[0]`, `maxSum = arr[0]`.
2. Initialize `start = 0`, `end = 0` → final subarray indices.
3. Initialize `tempStart = 0` → temporary start index when restarting subarray.
4. Loop from index `1`:
   - If `arr[i] > currentSum + arr[i]` → start new subarray: `currentSum = arr[i]`, `tempStart = i`.
   - Else → extend: `currentSum = currentSum + arr[i]`.
   - If `currentSum > maxSum` → new maximum found:
     - Update `maxSum = currentSum`.
     - Confirm `start = tempStart`, `end = i`.
5. Return `{maxSum, start, end}`.

### Dry Run
```
arr = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
currentSum=-2, maxSum=-2, start=0, end=0, tempStart=0

i=1(1):   1 > -2+1=-1? Yes → restart: currentSum=1, tempStart=1
          1 > -2? Yes → maxSum=1, start=1, end=1

i=2(-3):  -3 > 1-3=-2? No  → extend: currentSum=-2
          -2 > 1? No

i=3(4):   4 > -2+4=2? Yes  → restart: currentSum=4, tempStart=3
          4 > 1? Yes → maxSum=4, start=3, end=3

i=4(-1):  -1 > 4-1=3? No   → extend: currentSum=3
          3 > 4? No

i=5(2):   2 > 3+2=5? No    → extend: currentSum=5
          5 > 4? Yes → maxSum=5, start=3, end=5

i=6(1):   1 > 5+1=6? No    → extend: currentSum=6
          6 > 5? Yes → maxSum=6, start=3, end=6

i=7(-5):  -5 > 6-5=1? No   → extend: currentSum=1
          1 > 6? No

i=8(4):   4 > 1+4=5? No    → extend: currentSum=5
          5 > 6? No

Result: maxSum=6, start=3, end=6 → subarray arr[3..6] = [4,-1,2,1] ✓
```

### Complexity


| Time  | O(n) | Single pass — each element visited exactly once. |
| Space | O(1) | Only scalar variables used — `currentSum`, `maxSum`, `start`, `end`, `tempStart`. |

---

## Why `tempStart` is needed

`tempStart` acts as a candidate start index whenever we restart the subarray.
It is only promoted to `start` when a new maximum is confirmed — this avoids updating `start` for restarts that never become the global maximum.

```
Example: arr = [-2, 1, -3, 4, -1, 2, 1, -5, 4]

At i=1: restart → tempStart=1, maxSum=1, start=1 (confirmed)
At i=3: restart → tempStart=3, maxSum=4, start=3 (confirmed)
At i=8: currentSum=5 < maxSum=6 → tempStart never promoted → start stays 3 ✓
```

---

## Edge Cases
- All negative numbers → returns the least negative element with its index as both start and end.
- Single element → returns that element, start=0, end=0.
- All positive → returns total sum, start=0, end=n-1.

## Interview Tip
- The basic version (return only maxSum) is the most asked. In that case, remove start, end and tempstart logic. Keep only currentSum and maxSum logic.
- Index tracking (`start`, `end`, `tempStart`) is the common follow-up — this solution covers it.
- Always clarify: "Return just the sum, or also the subarray / indices?"


