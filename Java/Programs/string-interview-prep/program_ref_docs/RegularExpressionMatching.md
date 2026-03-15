# Regular Expression Matching

## Problem
Given a string `text` and pattern `pattern` with `.` and `*`, implement regular expression matching.
- `'.'` matches any single character.
- `'*'` matches zero or more of the preceding element.

> Key difference from Wildcard Matching: here `'*'` applies to the PRECEDING element (e.g. `"a*"` = zero or more `a`s). In Wildcard, `'*'` independently matches any sequence.

---

## Solution — Bottom-Up DP ✅ Optimal

### Step-by-step logic
1. `dp[i][j]` = true if `pattern[0..j-1]` matches `text[0..i-1]`.
2. Base cases:
   - `dp[0][0] = true` (empty matches empty).
   - `dp[0][j] = dp[0][j-2]` if `pattern[j-1] == '*'` (x* can match empty).
3. Transition for each `i`, `j`:
   - If `pattern[j-1] == '*'`:
     - Zero occurrences: `dp[i][j] = dp[i][j-2]`
     - One+ occurrences: if `matches(text[i-1], pattern[j-2])` → `dp[i][j] |= dp[i-1][j]`
   - Else: `dp[i][j] = dp[i-1][j-1] && matches(text[i-1], pattern[j-1])`
4. `matches(tc, pc)` = `pc == '.' || pc == tc`.

### Dry Run
```
text = "aab", pattern = "c*a*b"

    ""  c  *  a  *  b
""   T  F  T  F  T  F
a    F  F  F  T  T  F
a    F  F  F  F  T  F
b    F  F  F  F  F  T

dp[0][2]: pattern[1]='*' → dp[0][0]=T → dp[0][2]=T  (c* = empty)
dp[0][4]: pattern[3]='*' → dp[0][2]=T → dp[0][4]=T  (a* = empty)

dp[1][4]: pattern[3]='*'
  zero: dp[1][2]=F
  one+: matches('a','a')=T → dp[1][4] |= dp[0][4]=T → dp[1][4]=T

dp[2][4]: pattern[3]='*'
  zero: dp[2][2]=F
  one+: matches('a','a')=T → dp[2][4] |= dp[1][4]=T → dp[2][4]=T

dp[3][5]: pattern[4]='b', matches('b','b')=T → dp[3][5]=dp[2][4]=T ✓

Result: true ✓
```

### Complexity


| Time  | O(n × m) | Fill entire dp table of size (n+1) × (m+1). |
| Space | O(n × m) | dp table — optimisable to O(m) with two rows. |

---

## Wildcard vs Regex Matching

| | Wildcard (`WildcardMatching`) | Regex (`RegularExpressionMatching`) |

| `*` meaning | Any sequence independently | Zero or more of preceding element |
| `?` / `.` | `?` = any single char | `.` = any single char |
| Example | `"a*b"` matches `"xyzb"` | `"a*b"` matches `"b"`, `"ab"`, `"aab"` |
| Difficulty | Medium | Hard |

---

## Edge Cases
- `pattern = ".*"` → matches any string including empty.
- `pattern = "a*"` → matches `""`, `"a"`, `"aa"`, etc.
- Leading `'*'` in pattern → invalid regex (but handle gracefully).
- Empty text, non-empty pattern → only valid if pattern is all `x*` pairs.

## Interview Tip
- LeetCode #10. Harder follow-up to Wildcard Matching (LeetCode #44).
- Always clarify the difference between `'*'` in regex vs wildcard before coding.
- The `dp[0][j]` base case for `x*` patterns matching empty string is the most commonly missed detail.
