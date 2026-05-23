# Count Frequency of Each Element in an Array

## Problem
Given an array, count how many times each element appears and return the frequency map.

---

## Solution 1 — HashMap ✅ Optimal

### Why HashMap?
O(1) average lookup and insert. Single pass through the array gives frequency of every element.

### Step-by-step logic
1. Create an empty `HashMap<Integer, Integer> freqMap`.
2. For each `num` in array:
   - `freqMap.put(num, freqMap.getOrDefault(num, 0) + 1)` → increment count, default 0 if not present.
3. Return `freqMap`.

### Dry Run
```
arr = [1, 2, 2, 3, 3, 3, 4]
freqMap = {}

num=1: freqMap={1:1}
num=2: freqMap={1:1, 2:1}
num=2: freqMap={1:1, 2:2}
num=3: freqMap={1:1, 2:2, 3:1}
num=3: freqMap={1:1, 2:2, 3:2}
num=3: freqMap={1:1, 2:2, 3:3}
num=4: freqMap={1:1, 2:2, 3:3, 4:1}

Result: {1=1, 2=2, 3=3, 4=1} ✓
```

### Complexity


| Time  | O(n) | Single pass — each element processed once with O(1) HashMap operations. |
| Space | O(n) | HashMap stores at most n distinct key-value pairs. |

---

## Solution 2 — Stream (`Collectors.groupingBy`)

Uses `Collectors.groupingBy` with `Collectors.counting()` to group and count elements in one pipeline.

```java
return Arrays.stream(arr)
        .boxed()
        .collect(Collectors.groupingBy(n -> n, Collectors.counting()));
```

### Step-by-step logic
1. `Arrays.stream(arr)` → creates an `IntStream`.
2. `.boxed()` → converts `IntStream` to `Stream<Integer>` (required for `groupingBy`).
3. `.collect(Collectors.groupingBy(n -> n, Collectors.counting()))` → groups elements by value and counts each group.
4. Returns `Map<Integer, Long>` — note count is `Long` not `Integer`.

### Dry Run
```
arr = [1, 2, 2, 3, 3, 3, 4]

Stream: [1, 2, 2, 3, 3, 3, 4]
boxed:  [1, 2, 2, 3, 3, 3, 4] (Integer stream)

groupingBy(n -> n):
  group 1 → [1]       → count = 1
  group 2 → [2, 2]    → count = 2
  group 3 → [3, 3, 3] → count = 3
  group 4 → [4]       → count = 1

Result: {1=1, 2=2, 3=3, 4=1} ✓
```

### Complexity


| Time  | O(n) | Single stream pass — each element grouped and counted once. |
| Space | O(n) | Internal grouping map stores all distinct elements. |

---

## Comparison

| Solution | Return Type | API | Space |

| 1 — HashMap | `Map<Integer, Integer>` | None | O(n) |
| 2 — Stream | `Map<Integer, Long>` | `Collectors.groupingBy` | O(n) |

> Note: Solution 2 returns `Long` counts due to `Collectors.counting()`. Cast to `int` if needed.

---

## Edge Cases
- All same elements → map has one entry with count = n.
- All distinct elements → map has n entries each with count = 1.
- Single element → map has one entry with count = 1.

## Interview Tip
- Follow-up: "Find the element with highest frequency" → iterate map entries and track max count.
- Follow-up: "Find all elements appearing more than n/3 times" → Boyer-Moore Voting Algorithm.
- If order of insertion matters, use `LinkedHashMap` instead of `HashMap`.


