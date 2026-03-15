# Merge Overlapping Intervals

## Problem
Given a list of intervals, merge all overlapping intervals and return the result.

## Approach — Sort by Start + Greedy Merge

### Why sort first?
After sorting by start time, overlapping intervals are always adjacent.
We only need to compare each interval with the last merged one.

### Overlap condition
Two intervals `[a,b]` and `[c,d]` overlap if `c <= b` (next start <= current end).

### Step-by-step logic
1. Sort intervals by start time.
2. Initialize `merged` list with first interval.
3. For each subsequent interval:
   - If `interval[0] > last merged end` → no overlap → add as new interval.
   - Else → overlap → extend last merged end: `max(lastEnd, interval[1])`.
4. Return merged list.

### Dry Run
```
intervals = [[1,3],[2,6],[8,10],[15,18]] (already sorted)
Add [1,3] → merged=[[1,3]]
[2,6]: 2 <= 3 → merge → [1, max(3,6)=6] → merged=[[1,6]]
[8,10]: 8 > 6 → add → merged=[[1,6],[8,10]]
[15,18]: 15 > 10 → add → merged=[[1,6],[8,10],[15,18]]
```

## Complexity


| Time  | O(n log n) — dominated by sort |
| Space | O(n) — output list |

## Edge Cases
- Single interval → return as-is.
- All intervals overlap → one merged interval.
- Already sorted, no overlaps → return original.
- Contained interval: `[1,10]` and `[2,5]` → `[1,10]` (max end handles this).

## Interview Tip
LeetCode #56. The `max(lastEnd, interval[1])` handles the case where one interval is fully contained within another. Don't forget this — it's a common mistake.


