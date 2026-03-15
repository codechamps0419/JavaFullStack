# Rearrange Positive and Negative Numbers

## Problem
Given an array of positive and negative numbers, rearrange so all negatives appear on the left and all positives on the right.

---

## Solution 1 — Two Pointer (Partition) ✅ Optimal

### Why Two Pointer?
Same idea as QuickSort partition — use two pointers from both ends, swap misplaced elements. O(n) time, O(1) space. Relative order is NOT preserved.

### Step-by-step logic
1. `left = 0`, `right = arr.length - 1`.
2. While `left < right`:
   - Move `left` right while `arr[left] < 0` (already on correct side).
   - Move `right` left while `arr[right] >= 0` (already on correct side).
   - If `left < right` → swap `arr[left]` and `arr[right]`, `left++`, `right--`.

### Dry Run
```
arr = [-3, 5, -2, 8, -1, 4]
left=0, right=5

left=0: arr[0]=-3 < 0 → move left → left=1
left=1: arr[1]=5  >= 0 → stop
right=5: arr[5]=4 >= 0 → move right → right=4
right=4: arr[4]=-1 < 0 → stop
swap(arr[1], arr[4]) → arr=[-3,-1,-2,8,5,4], left=2, right=3

left=2: arr[2]=-2 < 0 → move left → left=3
left=3: arr[3]=8 >= 0 → stop
right=3: arr[3]=8 >= 0 → move right → right=2
left=3 > right=2 → stop

Result: [-3, -1, -2, 8, 5, 4] (negatives left, positives right) ✓
Note: relative order not preserved
```

### Complexity


| Time  | O(n) | Each element visited at most once by either pointer. |
| Space | O(1) | In-place swap — only two pointer variables used. |

---

## Solution 2 — Stream (Order Preserved)

Filters negatives and positives into separate streams, then concatenates — relative order IS preserved.

```java
int[] negatives = Arrays.stream(arr).filter(n -> n < 0).toArray();
int[] positives = Arrays.stream(arr).filter(n -> n >= 0).toArray();
return IntStream.concat(Arrays.stream(negatives), Arrays.stream(positives)).toArray();
```

### Step-by-step logic
1. `Arrays.stream(arr).filter(n -> n < 0).toArray()` → collect all negatives in original order.
2. `Arrays.stream(arr).filter(n -> n >= 0).toArray()` → collect all positives in original order.
3. `IntStream.concat()` → join negatives followed by positives into a new array.

### Dry Run
```
arr = [-3, 5, -2, 8, -1, 4]

filter(n < 0)  → [-3, -2, -1]
filter(n >= 0) → [5, 8, 4]
concat         → [-3, -2, -1, 5, 8, 4] ✓
Note: relative order preserved
```

### Complexity


| Time  | O(n) | Two filter passes — O(n)+O(n) = O(n). |
| Space | O(n) | Two intermediate arrays + result array allocated. |

---

## Comparison

| Solution | In-Place | Order Preserved | Space |

| 1 — Two Pointer | ✅ Yes | ❌ No | O(1) |
| 2 — Stream | ❌ No (new array) | ✅ Yes | O(n) |

---

## Edge Cases
- All negatives → no change needed.
- All positives → no change needed.
- Single element → no change.
- Zeros → treated as non-negative (>= 0) → placed on right side.

## Interview Tip
- Always clarify: "Should relative order be preserved?" — determines which solution to use.
- If order must be preserved with O(1) space → use insertion sort style: for each positive found, shift elements right and insert. O(n²) time, O(1) space.
- Follow-up: "Alternate positive and negative" → more complex, requires order preservation + alternating placement.


