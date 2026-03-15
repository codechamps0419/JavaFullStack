# Check if Array is Sorted (Ascending)

## Problem
Given an array, determine whether it is sorted in non-decreasing (ascending) order.

---

## Solution 1 — Single Pass ✅ Optimal

### Why Single Pass?
We only need to find one violation — any adjacent pair where `arr[i] > arr[i+1]` means unsorted. No need to scan further.

### Step-by-step logic
1. Loop from index `0` to `arr.length - 2`.
2. If `arr[i] > arr[i+1]` → array is not sorted → return `false`.
3. If loop completes without violation → return `true`.

### Dry Run
```
arr = [1, 2, 3, 4, 5]

i=0: arr[0]=1 > arr[1]=2? No
i=1: arr[1]=2 > arr[2]=3? No
i=2: arr[2]=3 > arr[3]=4? No
i=3: arr[3]=4 > arr[4]=5? No
Loop ends → return true ✓

arr = [1, 3, 2, 4, 5]

i=0: arr[0]=1 > arr[1]=3? No
i=1: arr[1]=3 > arr[2]=2? Yes → return false ✓
```

### Complexity

| Time  | O(n) | At most n-1 comparisons — early exit on first violation. |
| Space | O(1) | No extra data structure — only loop index used. |

---

## Solution 2 — Stream (`IntStream.range + allMatch`)

Uses `IntStream.range()` to check all adjacent pairs in a single stream pipeline.

```java
return IntStream.range(0, arr.length - 1)
                .allMatch(i -> arr[i] <= arr[i + 1]);
```

### Step-by-step logic
1. `IntStream.range(0, arr.length - 1)` → generates indices 0 to n-2.
2. `.allMatch(i -> arr[i] <= arr[i+1])` → returns `true` only if every adjacent pair satisfies the condition.
3. Short-circuits on first failure — same as Solution 1.

### Dry Run
```
arr = [1, 3, 2, 4, 5]

IntStream.range(0, 4) → indices [0, 1, 2, 3]
i=0: arr[0]=1 <= arr[1]=3? true
i=1: arr[1]=3 <= arr[2]=2? false → allMatch returns false ✓
```

### Complexity


| Time  | O(n) | `allMatch` short-circuits on first false — at most n-1 checks. |
| Space | O(1) | No extra data structure — stream processes elements lazily. |

---

## Comparison

| Solution | API | Short-circuits | Space |

| 1 — Single Pass | None | ✅ Yes | O(1) |
| 2 — Stream | `IntStream.allMatch` | ✅ Yes | O(1) |

---

## Edge Cases
- Empty array → return `true` (vacuously sorted).
- Single element → return `true`.
- All same elements → return `true` (non-decreasing allows equal).
- Descending array → returns `false` at first pair.

## Interview Tip
- Clarify: "sorted ascending, descending, or non-decreasing?" — equal adjacent elements are allowed in non-decreasing.
- Follow-up: "Check if sorted in either direction" — check both ascending and descending in one pass.


