# Longest Consecutive Sequence

## Problem
Given an unsorted array, find the length of the longest sequence of consecutive integers.
Must run in O(n) time.

## Approach — HashSet with Smart Start Detection

### Why HashSet?
O(1) lookup. We add all elements to a set, then for each element check if it's the start of a sequence.

### Key Insight — Only start from sequence beginnings
An element `num` is the start of a sequence only if `num - 1` is NOT in the set.
If we start counting from every element, we'd recount sequences → O(n²).
By only starting from sequence beginnings, each element is visited at most twice → O(n).

### Step-by-step logic
1. Add all elements to a HashSet.
2. For each `num` in set:
   - If `num - 1` is NOT in set → `num` is a sequence start.
   - Count consecutive: `num, num+1, num+2, ...` while they exist in set.
   - Update `maxLen`.

### Dry Run
```
arr = [100, 4, 200, 1, 3, 2]
set = {100, 4, 200, 1, 3, 2}

num=100: 99 not in set → start. 101 not in set → length=1
num=4:   3 in set → not a start, skip
num=200: 199 not in set → start. 201 not in set → length=1
num=1:   0 not in set → start. 2,3,4 in set → length=4, maxLen=4
num=3:   2 in set → not a start, skip
num=2:   1 in set → not a start, skip
Result: 4 → [1,2,3,4]
```

## Complexity


| Time  | O(n) — each element processed at most twice |
| Space | O(n) — HashSet |

## Edge Cases
- Empty array → 0.
- All same elements → 1.
- Negative numbers → works (HashSet handles any integers).

## Interview Tip
LeetCode #128. The O(n log n) sort-based solution is intuitive but not optimal. The HashSet approach with "start detection" is the expected O(n) answer.


