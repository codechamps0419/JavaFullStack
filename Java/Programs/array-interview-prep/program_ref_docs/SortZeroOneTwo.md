# Sort an Array of 0s, 1s, and 2s — Dutch National Flag

## Problem
Sort an array containing only 0s, 1s, and 2s in a single pass without using any sorting library.

## Approach — Three Pointer (Dutch National Flag by Dijkstra)

### Why not counting sort?
Counting sort needs 2 passes. This approach does it in 1 pass with O(1) space.

### Regions defined by pointers
```
[0..low-1]  → all 0s (sorted region)
[low..mid-1] → all 1s (sorted region)
[mid..high] → unknown (to be processed)
[high+1..n-1] → all 2s (sorted region)
```

### Step-by-step logic
1. `low = 0`, `mid = 0`, `high = n - 1`.
2. While `mid <= high`:
   - `arr[mid] == 0` → swap(low, mid), low++, mid++ (0 goes to front).
   - `arr[mid] == 1` → mid++ (1 is in correct region).
   - `arr[mid] == 2` → swap(mid, high), high-- (2 goes to end, don't increment mid — swapped element is unknown).

### Dry Run
```
arr = [0, 1, 2, 1, 0]
low=0, mid=0, high=4
mid=0: arr[0]=0 → swap(0,0), low=1, mid=1 → [0,1,2,1,0]
mid=1: arr[1]=1 → mid=2                   → [0,1,2,1,0]
mid=2: arr[2]=2 → swap(2,4), high=3       → [0,1,0,1,2]
mid=2: arr[2]=0 → swap(1,2), low=2, mid=3 → [0,0,1,1,2]
mid=3: arr[3]=1 → mid=4
mid=4 > high=3 → stop
Result: [0,0,1,1,2]
```

## Complexity


| Time  | O(n) — single pass |
| Space | O(1) |

## Edge Cases
- All same elements → works.
- Already sorted → works (no unnecessary swaps).

## Interview Tip
This is a classic problem. Name it "Dutch National Flag" — it impresses interviewers.
Explain why mid doesn't increment after swapping with high (the swapped element is unprocessed).


