# Minimum Window Subsequence

## Problem
Given strings `s1` and `s2`, find the minimum length substring of `s1` such that `s2` is a subsequence of that substring. Order of characters must be preserved.

> Key difference from Minimum Window Substring: here order matters (subsequence), there order doesn't matter (subset).

---

## Solution — Two Pointer (Forward + Backward Pass) ✅ Optimal

### Core Idea
- Forward pass: find the earliest end position where s2 is a subsequence.
- Backward pass: from that end, walk back to find the latest valid start (minimise window).
- Repeat from just after that start to find all candidate windows.

### Step-by-step logic
1. `i = 0` (pointer in s1), `result = ""`.
2. While `i < n`:
   - **Forward pass**: match all chars of s2 in order through s1 starting at `i`.
     - `j = 0` (pointer in s2).
     - While `i < n` and `j < m`: if `s1[i] == s2[j]` → `j++`. Always `i++`.
     - If `j < m` → s2 not found → break.
   - **Backward pass**: from current `i`, walk back to find minimum start.
     - `end = i`, `j = m-1`.
     - While `j >= 0`: if `s1[i-1] == s2[j]` → `j--`. Always `i--`.
   - Window = `s1[i..end-1]`. Update result if smaller.
   - `i++` to search for next window.
3. Return `result`.

### Dry Run
```
s1 = "abcdebdde", s2 = "bde"

i=0, j=0:
Forward pass:
  i=0 'a' ≠ 'b', i=1
  i=1 'b' == 'b' → j=1, i=2
  i=2 'c' ≠ 'd', i=3
  i=3 'd' == 'd' → j=2, i=4
  i=4 'e' == 'e' → j=3, i=5
  j==m → end=5

Backward pass from i=5:
  i=4: s1[4]='e' == s2[2]='e' → j=1, i=3
  i=3: s1[3]='d' == s2[1]='d' → j=0, i=2
  i=2: s1[2]='c' ≠ s2[0]='b', i=1
  i=1: s1[1]='b' == s2[0]='b' → j=-1, i=0
  j < 0 → stop

window = s1[0..4] = "abcde" (len=5), result="abcde"
i = 0+1 = 1

i=1, j=0:
Forward pass:
  i=1 'b'=='b' → j=1, i=2
  i=2 'c'≠'d', i=3
  i=3 'd'=='d' → j=2, i=4
  i=4 'e'=='e' → j=3, i=5
  end=5

Backward pass from i=5:
  i=4: 'e'=='e' → j=1, i=3
  i=3: 'd'=='d' → j=0, i=2
  i=2: 'c'≠'b', i=1
  i=1: 'b'=='b' → j=-1, i=0

window = s1[0..4] = "abcde" (len=5), not smaller

i=1, continue...

Eventually finds window "bcde" (len=4) → result="bcde" ✓
```

### Complexity


| Time  | O(n × m) | Each forward pass scans up to n chars; repeated for each starting position. |
| Space | O(1) | Only pointer variables used. |

---

## Edge Cases
- s2 not a subsequence of s1 → return `""`.
- s2 longer than s1 → return `""`.
- s1 == s2 → return s1.

## Interview Tip
- LeetCode #727. Harder follow-up to Minimum Window Substring.
- Key distinction to state: "subsequence preserves order, substring is contiguous — here we need a contiguous window that contains s2 as a subsequence."
- The backward pass is the key insight — it minimises the window after finding a valid end.
