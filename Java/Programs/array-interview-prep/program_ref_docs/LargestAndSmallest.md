# Find the Largest and Smallest Element in an Array

## Problem
Given an integer array, find the maximum and minimum values.

A better approach is using summaryStatistics() which computes both in a single pass (Solution 1).
---

## Solution 1 — Single Pass Linear Scan

### Why not sorting?
Sorting costs O(n log n). We only need one pass — O(n) is optimal.

### Step-by-step logic
1. Initialize `max = arr[0]` and `min = arr[0]` (assume first element is both).
2. Loop from index `1` to `arr.length - 1`.
3. If `arr[i] > max` → update `max`.
4. If `arr[i] < min` → update `min`.
5. Return `{max, min}`.

### Dry Run
```
arr = [3, 1, 7, 2, 9, 4]
Start : max=3, min=3
i=1   : arr[1]=1  → min=1
i=2   : arr[2]=7  → max=7
i=3   : arr[3]=2  → no change
i=4   : arr[4]=9  → max=9
i=5   : arr[5]=4  → no change
Result: max=9, min=1
```

### Complexity


| Time  | O(n) |
| Space | O(1) |

⏱️ Time Complexity
Loop runs → n-1 times
Each iteration → 2 comparisons

👉 Total operations ≈ 2(n-1)
👉 Time Complexity = O(n)

📦 Space Complexity
Only 2 variables used (min, max)
No extra data structure

👉 Space Complexity = O(1)

---

## Solution 2 — Two-Pass Linear Scan (Java 8 Streams)

Uses `Arrays.stream(arr).min()` and `Arrays.stream(arr).max()` — two separate stream passes.

```java
int min = Arrays.stream(arr).min().getAsInt();
int max = Arrays.stream(arr).max().getAsInt();
```

### Complexity


| Time  | O(n) — O(n) for max + O(n) for min = O(2n) → O(n) (constants ignored) |
| Space | O(1) |

⏱️ Time Complexity
min() → O(n)
max() → O(n)

👉 Total:

O(n) + O(n) = O(2n) ≈ O(n)
📦 Space Complexity
No extra storage
Stream is processed element by element

👉 O(1)

⚠️ Drawbacks

❌ Two separate traversals
❌ Slight overhead due to Stream API
❌ Not optimal for very large data
---

## Solution 3 — Single-Pass Aggregation (Java 8 Streams — `IntSummaryStatistics`)
It performs both min & max in a single pass / traversal

Uses `Arrays.stream(arr).summaryStatistics()` — single stream pass that collects all stats at once.

```java
IntSummaryStatistics stats = Arrays.stream(arr).summaryStatistics();
int max = stats.getMax();
int min = stats.getMin();
```

`IntSummaryStatistics` also exposes: `getSum()`, `getAverage()`, `getCount()`.

### Complexity


| Time  | O(n) |
| Space | O(1) |

---

## Comparison

| Solution | Approach    | Passes | API 
|-------------------------
| 1        | Manual loop | 1      | None
| 2        | Streams     | 2      | `IntStream.min()` / `max()` 
| 3        | Streams     | 1      | `IntSummaryStatistics` 

---

## Edge Cases
- Single element → max and min are the same.
- All same elements → max == min.
- Negative numbers → works correctly since we compare values directly.

## Interview Tip
- Prefer Solution 1 for interviews — explicit, zero dependencies, easiest to reason about.
- Mention Solution 3 if asked about Java 8+ — single pass with richer stats via `IntSummaryStatistics`.
- Always state you're avoiding sorting intentionally to keep it O(n).


