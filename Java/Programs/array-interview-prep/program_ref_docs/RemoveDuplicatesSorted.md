# Remove Duplicates from a Sorted Array

## Problem
Given a sorted array, remove duplicates in-place. Return the count `k` of unique elements.
The first `k` positions of the array must hold the unique elements.

---

## Solution 1 — Two Pointer (Slow-Fast) ✅ Optimal

### Why this works on sorted arrays?
Since the array is sorted, all duplicates are adjacent. We don't need a HashSet — just compare neighbors.

### Step-by-step logic
1. If `arr.length == 0` → return 0 (empty array guard).
2. `slow = 0` → tracks the last written unique position.
3. `fast` starts at 1 and scans forward.
4. If `arr[fast] != arr[slow]` → new unique element found:
   - Increment `slow`.
   - Write `arr[fast]` into `arr[slow]`.
5. If equal → skip (duplicate), `fast` moves on.
6. Return `slow + 1` (1-indexed count of unique elements).

### Dry Run
```
arr = [1, 1, 2, 3, 3, 4, 5, 5]
slow=0, fast=1: arr[1]=1 == arr[0]=1 → skip
slow=0, fast=2: arr[2]=2 != arr[0]=1 → slow=1, arr[1]=2  → arr=[1,2,2,3,3,4,5,5]
slow=1, fast=3: arr[3]=3 != arr[1]=2 → slow=2, arr[2]=3  → arr=[1,2,3,3,3,4,5,5]
slow=2, fast=4: arr[4]=3 == arr[2]=3 → skip
slow=2, fast=5: arr[5]=4 != arr[2]=3 → slow=3, arr[3]=4  → arr=[1,2,3,4,3,4,5,5]
slow=3, fast=6: arr[6]=5 != arr[3]=4 → slow=4, arr[4]=5  → arr=[1,2,3,4,5,4,5,5]
slow=4, fast=7: arr[7]=5 == arr[4]=5 → skip
Result: k = slow+1 = 5, Arrays.copyOf(arr, 5) = [1,2,3,4,5] ✓
```

### Complexity


| Time  | O(n) | `fast` pointer traverses the array once — each element visited exactly once. |
| Space | O(1) | In-place modification — no extra array allocated, only two pointer variables used. |

---

## Solution 2 — Stream (`Arrays.stream().distinct()`)

Uses Java 8 `Arrays.stream(arr).distinct().toArray()` — filters duplicates and returns a new array of unique elements.

```java
return Arrays.stream(arr)
             .distinct()
             .toArray();
```

### Step-by-step logic
1. `Arrays.stream(arr)` → creates an `IntStream` from the array.
2. `.distinct()` → filters out duplicate elements, keeping only first occurrence of each.
3. `.toArray()` → collects the unique elements into a new `int[]` array.
4. Return the new array — original array is untouched.

### Dry Run
```
arr = [1, 1, 2, 3, 3, 4, 5, 5]

Arrays.stream(arr) → IntStream of [1, 1, 2, 3, 3, 4, 5, 5]
.distinct()        → IntStream of [1, 2, 3, 4, 5]  (duplicates removed)
.toArray()         → int[]        [1, 2, 3, 4, 5]

Result: [1, 2, 3, 4, 5] ✓
```

### Complexity


| Time  | O(n) | `distinct()` internally uses a `LinkedHashSet` — each element processed once. |
| Space | O(n) | A new array of unique elements is allocated — original array is not modified. |

---

## Comparison

| Solution | In-Place | Returns | API | Space |
---
| 1 — Two Pointer | ✅ Yes | Count `k` of unique elements | None | O(1) |
| 2 — Stream | ❌ No (new array) | New `int[]` of unique elements | `Arrays.stream().distinct()` | O(n) |

---

## Edge Cases
- Empty array → Solution 1 returns 0. Solution 2 returns empty array.
- No duplicates → Solution 1 returns `arr.length`. Solution 2 returns copy of original.
- All duplicates → Solution 1 returns 1. Solution 2 returns single-element array.

## Interview Tip
- Prefer Solution 1 — in-place, O(1) space, interviewer wants to see the two-pointer logic.
- Mention Solution 2 if asked for a Java 8+ approach, but call out the O(n) space trade-off and that it returns a new array rather than modifying in-place.
- If asked about unsorted arrays — use a `LinkedHashSet` to preserve insertion order while removing duplicates.


