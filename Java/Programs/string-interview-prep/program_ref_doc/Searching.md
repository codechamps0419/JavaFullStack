# Java String Interview — Searching & Pattern Matching

---

## 9. Implement strStr() — KMP Algorithm
**File:** `searching/StrStr_KMP.java`

### Logic
**Step 1 — Build LPS Array:**
- LPS = Longest Proper Prefix which is also a Suffix.
- `lps[i]` = length of longest proper prefix of `pattern[0..i]` that is also a suffix.
- Used to skip redundant comparisons on mismatch.

**Step 2 — Search:**
- Compare text[i] with pattern[j].
- On match: advance both i and j.
- When j == pattern.length: match found at index `i - j`.
- On mismatch: if j > 0, set `j = lps[j-1]` (don't restart from 0).

### LPS Example
```
Pattern: "aabaab"
lps:     [0,1,0,1,2,3]

"aa"    → prefix "a" = suffix "a"     → lps[1]=1
"aab"   → no proper prefix=suffix     → lps[2]=0
"aaba"  → prefix "a" = suffix "a"     → lps[3]=1
"aabaa" → prefix "aa" = suffix "aa"   → lps[4]=2
"aabaab"→ prefix "aab" = suffix "aab" → lps[5]=3
```

### Why KMP over Naive?
| Approach | Time | Space |

| Naive (brute force) | O(n × m) | O(1) |
| KMP ✓ | O(n + m) | O(m) |

### Interview Answer
> "KMP avoids re-scanning characters by precomputing the LPS array. When a mismatch occurs at pattern[j], instead of resetting j to 0, we jump to lps[j-1]. This ensures each character in text is visited at most twice — O(n+m) total."

---

## 10. Longest Common Substring
**File:** `searching/LongestCommonSubstring.java`

### Logic
- 2D DP table: `dp[i][j]` = length of common substring ending at `s1[i-1]` and `s2[j-1]`.
- If characters match: `dp[i][j] = dp[i-1][j-1] + 1`
- If no match: `dp[i][j] = 0` (substring must be contiguous — reset!)
- Track max value and its ending index to extract the actual substring.

### DP Table Example
```
s1 = "abcdef", s2 = "zcdemf"

    ""  z  c  d  e  m  f
""   0  0  0  0  0  0  0
a    0  0  0  0  0  0  0
b    0  0  0  0  0  0  0
c    0  0  1  0  0  0  0
d    0  0  0  2  0  0  0
e    0  0  0  0  3  0  0  ← max=3, endIndex=5
f    0  0  0  0  0  0  1

Result: s1.substring(5-3, 5) = "cde"
```

### Complexity
| Time | O(n × m) |
| Space | O(n × m) |

### Interview Answer
> "The key difference from LCS is that substring must be contiguous, so we reset dp[i][j]=0 on mismatch instead of taking max of neighbors. I track the max value and its row index to reconstruct the actual substring."

---

## 11. Longest Common Subsequence (LCS)
**File:** `searching/LongestCommonSubsequence.java`

### Logic
- `dp[i][j]` = LCS length of `s1[0..i-1]` and `s2[0..j-1]`.
- If match: `dp[i][j] = dp[i-1][j-1] + 1`
- If no match: `dp[i][j] = max(dp[i-1][j], dp[i][j-1])`
- Backtrack through dp table to reconstruct actual subsequence.

### Substring vs Subsequence
| | Contiguous? | Example |

| Substring | Yes | "ace" in "abcde"? NO |
| Subsequence | No | "ace" in "abcde"? YES |

### Backtracking
```
When dp[i][j] came from dp[i-1][j-1]+1 → chars matched, include in LCS, go diagonal
When dp[i][j] came from dp[i-1][j]     → go up
When dp[i][j] came from dp[i][j-1]     → go left
```

### Complexity
| Time | O(n × m) |
| Space | O(n × m) |

### Interview Answer
> "Unlike substring, subsequence doesn't require contiguity, so on mismatch we take max of skipping one char from either string. Backtracking through the dp table reconstructs the actual sequence. Space can be optimized to O(min(n,m)) using two rows."

---

## 12. Find All Pattern Occurrences — Rabin-Karp
**File:** `searching/RabinKarpSearch.java`

### Logic
**Rolling Hash:**
- Compute hash of pattern and first window of text.
- Slide window: `newHash = (BASE × (oldHash - text[i-1] × h) + text[i+m-1]) % MOD`
- On hash match: verify with `regionMatches()` to handle collisions.

### Rolling Hash Visualization
```
text = "abcabc", pattern = "abc"

Window 0: hash("abc") == patHash → verify → match at 0
Window 1: hash("bca") ≠ patHash
Window 2: hash("cab") ≠ patHash
Window 3: hash("abc") == patHash → verify → match at 3
Result: [0, 3]
```

### Complexity
| Time | O(n + m) average |
| Space | O(1) |

### Interview Answer
> "Rabin-Karp uses rolling hash to avoid recomputing hash from scratch each window. The rolling formula removes the leftmost character's contribution and adds the new rightmost character. I always verify on hash match to handle collisions — this is the correctness guarantee."

---
