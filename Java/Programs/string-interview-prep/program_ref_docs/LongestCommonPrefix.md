# Longest Common Prefix

## Problem
Given an array of strings, find the longest common prefix shared among all strings. Return empty string if none exists.

---

## Solution 1 — Vertical Scan ✅ Optimal

### Why Vertical Scan?
Take the first string as reference. For each character position, check all strings at that position. Stop at first mismatch or when any string runs out.

### Step-by-step logic
1. If input is null or empty → return `""`.
2. Loop over each character index `i` of `strs[0]`:
   - For each other string `j`: if `i >= strs[j].length()` or `strs[j].charAt(i) != c` → return `strs[0].substring(0, i)`.
3. If loop completes → return `strs[0]` (entire first string is prefix).

### Dry Run
```
strs = ["flower", "flow", "flight"]

i=0: c='f' → all strings have 'f' ✓
i=1: c='l' → all strings have 'l' ✓
i=2: c='o' → "flight".charAt(2)='i' ≠ 'o' → return strs[0].substring(0,2) = "fl" ✓
```

### Complexity


| Time  | O(n × m) | n = number of strings, m = length of shortest string — worst case all chars match. |
| Space | O(1) | No extra data structure — only index variables used. |

---

## Solution 2 — Binary Search on Prefix Length

Binary search on the answer length (1 to minLen). For each mid, check if prefix of that length is common to all strings.

### Step-by-step logic
1. Find `minLen` = length of shortest string.
2. Binary search `lo=1`, `hi=minLen`:
   - `mid = (lo+hi)/2`.
   - If `strs[0].substring(0, mid)` is a prefix of all strings → `lo = mid+1`.
   - Else → `hi = mid-1`.
3. Return `strs[0].substring(0, (lo+hi)/2)`.

### Dry Run
```
strs = ["flower", "flow", "flight"], minLen=4

lo=1, hi=4, mid=2 → "fl" prefix of all? Yes → lo=3
lo=3, hi=4, mid=3 → "flo" prefix of "flight"? No → hi=2
lo=3 > hi=2 → stop
(lo+hi)/2 = 2 → return "fl" ✓
```

### Complexity


| Time  | O(n × m × log m) | Binary search O(log m) × prefix check O(n × m). |
| Space | O(1) | No extra data structure. |

---

## Comparison

| Solution | Time | Space | Best For |
---|
| 1 — Vertical Scan | O(n × m) | O(1) | General use — simpler and faster |
| 2 — Binary Search | O(n × m × log m) | O(1) | When prefix is very long — fewer full scans |

---

## Edge Cases
- Empty array → return `""`.
- Single string → return that string.
- No common prefix → return `""`.
- All strings identical → return the string itself.

## Interview Tip
- Prefer Solution 1 — simpler, faster, easier to explain.
- Mention Solution 2 as a follow-up when asked "can you do better if the prefix is long?"
- Always handle null/empty input first.
