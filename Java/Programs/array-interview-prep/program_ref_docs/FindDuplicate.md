# Find the Duplicate Number

## Problem
Given an array of n+1 integers where each value is in range [1, n], exactly one number appears twice. Find the duplicate without modifying the array and using O(1) extra space.

---

## Solution 1 — Floyd's Cycle Detection (Tortoise and Hare) ✅ Optimal

### Why Floyd's Cycle Detection?
Treat the array as a linked list where `arr[i]` is the next node from index `i`. Since one value is duplicated, two indices point to the same next node — creating a cycle. The entry point of the cycle is the duplicate.

### Step-by-step logic
**Phase 1 — Detect cycle (find meeting point):**
1. `slow = arr[0]`, `fast = arr[0]`.
2. Move `slow` one step: `slow = arr[slow]`.
3. Move `fast` two steps: `fast = arr[arr[fast]]`.
4. Repeat until `slow == fast` (meeting point inside cycle).

**Phase 2 — Find cycle entry (duplicate number):**
1. Reset `slow = arr[0]`, keep `fast` at meeting point.
2. Move both one step at a time until `slow == fast`.
3. Return `slow` — this is the duplicate.

### Dry Run
```
arr = [1, 3, 4, 2, 2]
Index:  0  1  2  3  4

Linked list: 0→1→3→2→4→2→4→... (cycle at 2)

Phase 1:
slow=arr[0]=1, fast=arr[0]=1
Step 1: slow=arr[1]=3, fast=arr[arr[1]]=arr[3]=2
Step 2: slow=arr[3]=2, fast=arr[arr[2]]=arr[4]=2
slow==fast=2 → meeting point found

Phase 2:
slow=arr[0]=1, fast=2
Step 1: slow=arr[1]=3, fast=arr[2]=4
Step 2: slow=arr[3]=2, fast=arr[4]=2
slow==fast=2 → duplicate = 2 ✓
```

### Complexity


| Time  | O(n) | Both phases traverse at most n nodes. |
| Space | O(1) | Only two pointer variables — array not modified. |

---

## Solution 2 — Negative Marking (Modifies Array)

Uses index as a hash — negates `arr[idx]` as a visited marker. If already negative → duplicate found.

```java
int idx = Math.abs(arr[i]);
if (arr[idx] < 0) { duplicate = idx; break; }
arr[idx] = -arr[idx]; // mark visited
```

### Step-by-step logic
1. For each element `arr[i]`:
   - `idx = Math.abs(arr[i])` → map value to index (handles already-negated values).
   - If `arr[idx] < 0` → index `idx` was already visited → `idx` is the duplicate.
   - Else → negate `arr[idx]` to mark as visited.
2. Restore array by taking absolute values of all elements.
3. Return duplicate.

### Dry Run
```
arr = [1, 3, 4, 2, 2]

i=0: idx=1, arr[1]=3>0  → negate → arr=[1,-3,4,2,2]
i=1: idx=3, arr[3]=2>0  → negate → arr=[1,-3,4,-2,2]
i=2: idx=4, arr[4]=2>0  → negate → arr=[1,-3,4,-2,-2]
i=3: idx=2, arr[2]=4>0  → negate → arr=[1,-3,-4,-2,-2]
i=4: idx=2, arr[2]=-4<0 → DUPLICATE = 2 ✓

Restore: arr=[1,3,4,2,2]
```

### Complexity


| Time  | O(n) | Single pass — each element processed once. |
| Space | O(1) | No extra data structure — array modified in-place and restored. |

---

## Solution 3 — Stream (Sort-based)

Sorts a copy of the array and finds the first adjacent duplicate.

```java
int[] sorted = Arrays.stream(arr).sorted().toArray();
return IntStream.range(0, sorted.length - 1)
        .filter(i -> sorted[i] == sorted[i + 1])
        .map(i -> sorted[i])
        .findFirst().getAsInt();
```

### Step-by-step logic
1. `Arrays.stream(arr).sorted().toArray()` → create a sorted copy of the array.
2. `IntStream.range(0, n-1)` → iterate adjacent index pairs.
3. `.filter(i -> sorted[i] == sorted[i+1])` → find first adjacent duplicate.
4. `.map(i -> sorted[i]).findFirst()` → return the duplicate value.

### Dry Run
```
arr = [1, 3, 4, 2, 2]

sorted = [1, 2, 2, 3, 4]

i=0: sorted[0]=1 == sorted[1]=2? No
i=1: sorted[1]=2 == sorted[2]=2? Yes → return 2 ✓
```

### Complexity


| Time  | O(n log n) | Sorting dominates — `Arrays.stream().sorted()` is O(n log n). |
| Space | O(n) | New sorted array of size n allocated. |

---

## Comparison

| Solution | Time | Space | Modifies Array |

| 1 — Floyd's Cycle | O(n) | O(1) | ❌ No |
| 2 — Negative Marking | O(n) | O(1) | ⚠️ Yes (restored) |
| 3 — Stream Sort | O(n log n) | O(n) | ❌ No |

---

## Edge Cases
- Duplicate at start → both solutions find it correctly.
- Duplicate at end → both solutions find it correctly.
- Large n → Floyd's is preferred — O(n) time, O(1) space, no modification.

## Interview Tip
- LeetCode #287. Floyd's Cycle Detection is the expected O(n) time O(1) space answer.
- If array modification is allowed → Negative Marking is simpler to explain.
- Always clarify constraints: "Can I modify the array?" and "Can I use extra space?"


