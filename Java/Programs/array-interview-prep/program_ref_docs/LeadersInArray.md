# Find All Leaders in an Array

## Problem
An element is a leader if it is greater than or equal to all elements to its right.
The rightmost element is always a leader. Find all leaders in the array.

---

## Solution 1 — Right to Left Scan ✅ Optimal

### Why right to left?
Scanning from right lets us track the running maximum from the right side. Any element >= that maximum is a leader.

### Step-by-step logic
1. Initialize `maxFromRight = arr[n-1]` — rightmost element is always a leader, add it to result.
2. Scan from index `n-2` down to `0`:
   - If `arr[i] >= maxFromRight` → it's a leader → update `maxFromRight = arr[i]`, add to front of result.
3. Return result list (leaders in left-to-right order).

### Dry Run
```
arr = [16, 17, 4, 3, 5, 2]

maxFromRight = 2, leaders = [2]

i=4: arr[4]=5  >= 2?  Yes → maxFromRight=5,  leaders=[5, 2]
i=3: arr[3]=3  >= 5?  No
i=2: arr[2]=4  >= 5?  No
i=1: arr[1]=17 >= 5?  Yes → maxFromRight=17, leaders=[17, 5, 2]
i=0: arr[0]=16 >= 17? No

Result: [17, 5, 2] ✓
```

### Complexity


| Time  | O(n) | Single right-to-left pass — each element visited once. |
| Space | O(1) | Only `maxFromRight` variable used — output list not counted. |

---

## Solution 2 — Stream

For each index `i`, checks if all elements to its right are <= `arr[i]` using a nested stream.

```java
return IntStream.range(0, arr.length)
        .filter(i -> IntStream.range(i + 1, arr.length).allMatch(j -> arr[i] >= arr[j]))
        .mapToObj(i -> arr[i])
        .collect(Collectors.toList());
```

### Step-by-step logic
1. `IntStream.range(0, arr.length)` → iterate over each index `i`.
2. `.filter(i -> IntStream.range(i+1, n).allMatch(j -> arr[i] >= arr[j]))` → keep index `i` only if all elements to its right are smaller or equal.
3. `.mapToObj(i -> arr[i])` → map index to value.
4. `.collect(Collectors.toList())` → collect leaders into a list.

### Dry Run
```
arr = [16, 17, 4, 3, 5, 2]

i=0(16): check [17,4,3,5,2] → 16>=17? No → not a leader
i=1(17): check [4,3,5,2]    → all <= 17? Yes → leader ✓
i=2(4):  check [3,5,2]      → 4>=5? No → not a leader
i=3(3):  check [5,2]        → 3>=5? No → not a leader
i=4(5):  check [2]          → 5>=2? Yes → leader ✓
i=5(2):  check []           → allMatch on empty = true → leader ✓

Result: [17, 5, 2] ✓
```

### Complexity


| Time  | O(n²) | For each element, inner stream checks all elements to its right — O(n) × O(n). |
| Space | O(1) | No extra data structure — stream processes lazily. Output list not counted. |

---

## Comparison

| Solution | Time | Space | Notes |

| 1 — Right to Left | O(n) | O(1) | Optimal — single pass |
| 2 — Stream | O(n²) | O(1) | Readable but less efficient |

---

## Edge Cases
- Single element → it is always a leader.
- Sorted descending `[5,4,3,2,1]` → all elements are leaders.
- Sorted ascending `[1,2,3,4,5]` → only last element is a leader.
- All same elements → all are leaders (>= condition).

## Interview Tip
- Always clarify: "strictly greater or greater than or equal?" — affects whether equal elements qualify.
- Solution 1 is the expected O(n) answer. Mention the right-to-left scan insight clearly.


