# Find Max and Min Using Minimum Comparisons

## Problem
Find the maximum and minimum element in an array using the fewest possible comparisons.
Normal approach uses 2(n-1) comparisons. This approach uses only ~3n/2 comparisons.

---

## Solution 1 — Pair Comparison (Tournament Method) ✅ Optimal

### Why Pair Comparison?
Instead of comparing each element against both min and max (2 comparisons per element), compare elements in pairs first (1 comparison), then compare the pair winner/loser against max/min (1 comparison each). Total: 3 comparisons per 2 elements = 3n/2.

### Step-by-step logic
1. If array length is even → initialize `min` and `max` from first pair (`arr[0]`, `arr[1]`), start `i = 2`.
2. If array length is odd → initialize `min = max = arr[0]`, start `i = 1`.
3. Process remaining elements in pairs (`arr[i]`, `arr[i+1]`):
   - Compare `arr[i]` with `arr[i+1]`.
   - If `arr[i] < arr[i+1]` → compare `arr[i]` with `min`, compare `arr[i+1]` with `max`.
   - Else → compare `arr[i+1]` with `min`, compare `arr[i]` with `max`.
   - Advance `i += 2`.
4. Return `{min, max}`.

### Dry Run
```
arr = [3, 5, 1, 8, 2, 9, 4], length=7 (odd)

Init: min=3, max=3, i=1

Pair (arr[1]=5, arr[2]=1):
  5 > 1 → min=min(3,1)=1, max=max(3,5)=5, i=3

Pair (arr[3]=8, arr[4]=2):
  8 > 2 → min=min(1,2)=1, max=max(5,8)=8, i=5

Pair (arr[5]=9, arr[6]=4):
  9 > 4 → min=min(1,4)=1, max=max(8,9)=9, i=7

i=7 >= length → stop

Result: min=1, max=9 ✓

Comparisons: 1 (init) + 3 pairs × 3 = 10 ≈ 3n/2 = 10.5
Normal approach: 2(n-1) = 12
```

### Complexity


| Time  | O(n) | ~3n/2 comparisons — processes elements in pairs, each pair costs 3 comparisons. |
| Space | O(1) | Only `min`, `max`, and loop index used. |

---

## Solution 2 — Stream (`IntSummaryStatistics`)

Uses `Arrays.stream(arr).summaryStatistics()` — single stream pass collecting min, max, sum, count, average.

```java
IntSummaryStatistics stats = Arrays.stream(arr).summaryStatistics();
return new int[]{stats.getMin(), stats.getMax()};
```

### Step-by-step logic
1. `Arrays.stream(arr)` → creates an `IntStream`.
2. `.summaryStatistics()` → single pass collecting all stats: min, max, sum, count, average.
3. `stats.getMin()` → returns minimum value.
4. `stats.getMax()` → returns maximum value.

### Dry Run
```
arr = [3, 5, 1, 8, 2, 9, 4]

summaryStatistics() single pass:
  count=7, sum=32, min=1, max=9, average=4.57

stats.getMin() = 1
stats.getMax() = 9

Result: {1, 9} ✓
```

### Complexity


| Time  | O(n) | Single stream pass — all stats computed together. |
| Space | O(1) | `IntSummaryStatistics` object holds only 5 scalar values. |

---

## Comparison

| Solution | Comparisons | API | Space |

| 1 — Pair Comparison | ~3n/2 (minimum) | None | O(1) |
| 2 — Stream | Internal (not minimised) | `IntSummaryStatistics` | O(1) |

---

## Edge Cases
- Single element → min == max.
- All same elements → min == max.
- Two elements → one pair comparison.

## Interview Tip
- The key insight is processing in pairs to reduce comparisons from 2(n-1) to 3n/2.
- If interviewer asks "minimum comparisons" specifically — Solution 1 is the answer.
- Mention `IntSummaryStatistics` for Java 8+ — it also gives `getSum()`, `getAverage()`, `getCount()`.


