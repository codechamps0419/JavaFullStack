# Move All Zeros to End of Array

## Problem
Given an array, move all zeros to the end while maintaining the relative order of non-zero elements.

---

## Solution 1 — Two Pointer ✅ Optimal

### Why Two Pointer?
`slow` tracks the next position to place a non-zero element. `fast` scans ahead. Non-zeros are compacted to the front, then remaining positions are filled with zeros.

### Step-by-step logic
1. `slow = 0` → tracks position to place next non-zero element.
2. `fast` scans from 0 to end:
   - If `arr[fast] != 0` → copy `arr[fast]` to `arr[slow]`, increment `slow`.
3. After loop, fill `arr[slow]` to `arr[n-1]` with zeros.

### Dry Run
```
arr = [0, 1, 0, 3, 12]
slow=0

fast=0: arr[0]=0  → skip
fast=1: arr[1]=1  → arr[0]=1, slow=1  → arr=[1,1,0,3,12]
fast=2: arr[2]=0  → skip
fast=3: arr[3]=3  → arr[1]=3, slow=2  → arr=[1,3,0,3,12]
fast=4: arr[4]=12 → arr[2]=12, slow=3 → arr=[1,3,12,3,12]

Fill zeros from slow=3 to end:
arr[3]=0, arr[4]=0 → arr=[1,3,12,0,0] ✓
```

### Complexity


| Time  | O(n) | Single pass for non-zero copy + single pass for zero fill — O(n)+O(n) = O(n). |
| Space | O(1) | In-place modification — no extra array allocated. |

---

## Solution 2 — Stream

Filters non-zeros and zeros into separate streams, then concatenates them.

```java
int[] nonZeros = Arrays.stream(arr).filter(n -> n != 0).toArray();
int[] zeros    = Arrays.stream(arr).filter(n -> n == 0).toArray();
return IntStream.concat(Arrays.stream(nonZeros), Arrays.stream(zeros)).toArray();
```

### Step-by-step logic
1. `Arrays.stream(arr).filter(n -> n != 0).toArray()` → collect all non-zero elements in order.
2. `Arrays.stream(arr).filter(n -> n == 0).toArray()` → collect all zeros.
3. `IntStream.concat()` → join non-zeros followed by zeros into a new array.

### Dry Run
```
arr = [0, 1, 0, 3, 12]

filter(n != 0) → [1, 3, 12]
filter(n == 0) → [0, 0]
concat         → [1, 3, 12, 0, 0] ✓
```

### Complexity


| Time  | O(n) | Two filter passes over the array — O(n)+O(n) = O(n). |
| Space | O(n) | Two intermediate arrays + final result array allocated. |

---

## Comparison

| Solution | In-Place | Order Preserved | Space |

| 1 — Two Pointer | ✅ Yes | ✅ Yes | O(1) |
| 2 — Stream | ❌ No (new array) | ✅ Yes | O(n) |

---

## Edge Cases
- All zeros → result is all zeros.
- No zeros → array unchanged.
- Single element → no change.
- Zeros at end already → no movement needed, still works correctly.

## Interview Tip
- Prefer Solution 1 — in-place, O(1) space, relative order preserved.
- Common follow-up: "Move zeros to front instead" — just flip the filter condition.
- Another follow-up: "Do it in a single pass without fill loop" — use swap instead of copy: swap `arr[fast]` with `arr[slow]` when `arr[fast] != 0`.


