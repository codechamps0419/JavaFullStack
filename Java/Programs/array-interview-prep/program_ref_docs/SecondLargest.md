# Find the Second Largest Element in an Array

## Problem
Find the second distinct largest element. Duplicates of the largest should not count.

---

## Solution 1 — Single Pass, Two Variables ✅ Optimal

### Why not sort?
Sorting is O(n log n). We can do it in O(n) with two tracking variables.

### Step-by-step logic
1. Initialize `first = MIN_VALUE`, `second = MIN_VALUE`.
2. For each `num` in array:
   - If `num > first` → `second = first`, then `first = num` (new largest found, old largest becomes second).
   - Else if `num > second AND num != first` → `second = num` (new second largest found).
3. Return `second`.

### Dry Run
```
arr = [12, 35, 1, 10, 34, 1]
first = MIN_VALUE, second = MIN_VALUE

num=12 : 12 > MIN_VALUE       → second=MIN_VALUE, first=12
num=35 : 35 > 12              → second=12,        first=35
num=1  : 1 > 35? No. 1 > 12 && 1!=35? No (1 < 12) → no change
num=10 : 10 > 35? No. 10 > 12 && 10!=35? No (10 < 12) → no change
num=34 : 34 > 35? No. 34 > 12 && 34!=35? Yes → second=34
num=1  : 1 > 35? No. 1 > 34 && 1!=35? No (1 < 34) → no change

Result : second = 34
```

### Complexity


| Time  | O(n) | Single pass through the array — each element compared at most twice. |
| Space | O(1) | Only two variables `first` and `second` used — no extra data structure. |

---

## Solution 2 — Min Heap of size k (Top-K approach using PriorityQueue + Stream)

Maintains a min-heap of size `k=2`. After processing all elements, the top of the heap is the 2nd largest.

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();
Arrays.stream(arr).forEach(num -> {
    pq.offer(num);
    if (pq.size() > k) pq.poll(); // remove smallest when heap exceeds size k
});
return pq.peek();
```

### Step-by-step logic
1. Create a min-heap `pq`.
2. Stream through each `num` in array:
   - Offer `num` to the heap.
   - If `pq.size() > k` → poll (remove the smallest — heap top).
3. After all elements, heap holds the top-k largest → `pq.peek()` = kth largest (2nd largest when k=2).

### Dry Run
```
arr = [12, 35, 1, 10, 34, 1], k = 2

offer(12) → heap=[12],     size=1 ≤ 2 → no poll
offer(35) → heap=[12,35],  size=2 ≤ 2 → no poll
offer(1)  → heap=[1,12,35],size=3 > 2 → poll(1)  → heap=[12,35]
offer(10) → heap=[10,12,35],size=3 > 2 → poll(10) → heap=[12,35]
offer(34) → heap=[12,34,35],size=3 > 2 → poll(12) → heap=[34,35]
offer(1)  → heap=[1,34,35], size=3 > 2 → poll(1)  → heap=[34,35]

peek() = 34 → 2nd Largest = 34 ✓
```

### Complexity


| Time  | O(n log 2) → O(n) | Each element is offered to the heap once. Heap operations are O(log k) = O(log 2) = O(1) since k=2 is fixed. |
| Space | O(1) | Heap holds at most k=2 elements at any time — constant space. |

---

## Comparison

| Solution | Approach | Time | Space |

| 1 — Two Variables | Single pass, manual tracking | O(n) | O(1) |
| 2 — Min Heap | PriorityQueue + Stream, Top-K | O(n) | O(1) |

> Solution 2 generalises easily to "find Kth largest" by changing `k`. Solution 1 is hardcoded for 2nd largest only.

---

## Edge Cases
- All same elements → Solution 1 returns `MIN_VALUE` (no second largest). Solution 2 returns the same value (no distinct check).
- Array of size 1 → no second largest.
- `[5, 5, 3]` → Solution 1 returns 3 (duplicate check `num != first`). Solution 2 returns 5 (no distinct check — treats duplicates as valid).

## Interview Tip
- Prefer Solution 1 for "second largest distinct element" — the `num != first` guard handles duplicates correctly.
- Prefer Solution 2 when the problem asks for "Kth largest" (with duplicates allowed) — it generalises cleanly.


