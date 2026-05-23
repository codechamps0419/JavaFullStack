# Java String Interview — Advanced String Problems

---

## 24. Group Anagrams
**File:** `advanced/GroupAnagrams.java`

### Logic (Sorted Key)
- Sort each word's characters → canonical key (all anagrams share same key).
- Group words by key using `computeIfAbsent`.

### Logic (Frequency Array Key — Optimal)
- Build `int[26]` frequency array for each word.
- Convert to string key: `Arrays.toString(freq)` → `"[1,0,0,...] "`.
- Avoids sorting → O(k) per word instead of O(k log k).

### Flow
```
Input: ["eat","tea","tan","ate","nat","bat"]

"eat" → sort → "aet" → group: {aet: [eat]}
"tea" → sort → "aet" → group: {aet: [eat,tea]}
"tan" → sort → "ant" → group: {aet:[eat,tea], ant:[tan]}
"ate" → sort → "aet" → group: {aet:[eat,tea,ate], ant:[tan]}
"nat" → sort → "ant" → group: {aet:[eat,tea,ate], ant:[tan,nat]}
"bat" → sort → "abt" → group: {aet:[...], ant:[...], abt:[bat]}

Output: [[eat,tea,ate],[tan,nat],[bat]]
```

### Complexity Comparison
| Approach | Time | Space |
|---|---|---|
| Sorted key | O(n × k log k) | O(n × k) |
| Frequency key ✓ | O(n × k) | O(n × k) |

### Interview Answer
> "Two approaches: sorted key is simpler but O(k log k) per word. Frequency array key is O(k) per word — better for long strings. computeIfAbsent is cleaner than getOrDefault+put. Both produce the same grouping."

---

## 25. Longest Repeating Substring
**File:** `advanced/LongestRepeatingSubstring.java`

### Logic (Binary Search + Rolling Hash)
- Binary search on answer length L (1 to n-1).
- For each candidate L: use rolling hash to check if any substring of length L appears twice.
- If duplicate found → try longer (lo = mid+1).
- Else → try shorter (hi = mid-1).

### Binary Search Logic
```
s = "abcabc", n=6
lo=1, hi=5
mid=3: search(3) → found "abc" → bestLen=3, lo=4
mid=4: search(4) → not found → hi=3
lo > hi → stop
Output: "abc"
```

### Complexity
| Time | O(n log n) |
| Space | O(n) |

### Interview Answer
> "Binary search on the answer length combined with rolling hash for O(n log n) total. The key insight: if a substring of length L repeats, then a substring of length L-1 also repeats (monotonic property enables binary search). Rolling hash gives O(n) per length check."

---

## 26. Word Break Problem
**File:** `advanced/WordBreak.java`

### Logic (1D DP)
- `dp[i]` = true if `s[0..i-1]` can be segmented using dictionary words.
- Base: `dp[0] = true` (empty string).
- For each position `i`, check all `j < i`:
  - If `dp[j] == true` AND `s[j..i-1]` is in dictionary → `dp[i] = true`, break.
- Use HashSet for O(1) dictionary lookup.

### Flow
```
s="leetcode", dict={"leet","code"}
dp = [T, F, F, F, F, F, F, F, F]

i=4: j=0: dp[0]=T, "leet" in dict → dp[4]=T
i=8: j=4: dp[4]=T, "code" in dict → dp[8]=T

Output: true
```

### Complexity
| Time | O(n²) |
| Space | O(n) |

### Interview Answer
> "Classic DP where `dp[i]` means the first i characters can be segmented. For each end position i, I check all possible last word start positions j. HashSet lookup is O(1). The break statement optimizes by stopping as soon as `dp[i]` is set to true."

---

## 27. Interleaving Strings
**File:** `advanced/InterleavingStrings.java`

### Logic (2D DP)
- `dp[i][j]` = true if `s3[0..i+j-1]` is formed by interleaving `s1[0..i-1]` and `s2[0..j-1]`.
- Transition:
  - `dp[i][j] = (dp[i-1][j] && s1[i-1] == s3[i+j-1])` ← took from s1
  - `OR (dp[i][j-1] && s2[j-1] == s3[i+j-1])` ← took from s2


### Flow
```
s1="ab", s2="bc", s3="abbc"

    ""  b   c
""   T  F   F
a    T  F   F
b    F  T   T

dp[2][2]=T → "abbc" is interleaving of "ab" and "bc" ✓
```

### Complexity
| Time | O(n × m) |
| Space | O(n × m) — optimizable to O(m) |

### Interview Answer
> "2D DP where each cell `dp[i][j]` represents whether we can form s3 up to position i+j using i chars from s1 and j chars from s2. At each cell, we check if we can arrive from above (took from s1) or from left (took from s2). First check: len(s1)+len(s2) must equal len(s3)."

---

## 28. Edit Distance (Levenshtein)
**File:** `advanced/EditDistance.java`

### Logic (2D DP)
- `dp[i][j]` = min operations to convert `s1[0..i-1]` to `s2[0..j-1]`.
- Three operations: insert, delete, replace.
- Transition:
  - Match: `dp[i][j] = dp[i-1][j-1]`
  - Mismatch: `dp[i][j] = 1 + min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1])`


### DP Table — "horse" → "ros"
```
    ""  r  o  s
""   0  1  2  3
h    1  1  2  3
o    2  2  1  2
r    3  2  2  2
s    4  3  3  2
e    5  4  4  3

Output: 3
```

### Complexity
| Time | O(n × m) |
| Space | O(n × m) — optimizable to O(min(n,m)) |

### Interview Answer
> "Classic DP. The three choices at each mismatch correspond to the three edit operations. On a match, we inherit `dp[i-1][j-1]` with no cost. Space can be reduced to O(min(n,m)) by keeping only two rows since each cell only depends on the previous row and current row."

---

## 29. Wildcard Pattern Matching
**File:** `advanced/WildcardMatching.java`

### Logic (2D DP)
- `dp[i][j]` = true if `pattern[0..j-1]` matches `text[0..i-1]`.
- Base: `dp[0][j]` = true only if all pattern chars up to j are `*`.
- Transition:
  - `*`: `dp[i][j] = dp[i-1][j]` (match one more) OR `dp[i][j-1]` (match empty)
  - `?` or exact match: `dp[i][j] = dp[i-1][j-1]`

### `*` Behavior
```
"*" can match:
- Empty string:   dp[i][j] = dp[i][j-1]
- One character:  dp[i][j] = dp[i-1][j]
- Multiple chars: handled by dp[i-1][j] propagating upward
```

### Flow — isMatch("adceb", "*a*b")
```
pattern: * a * b
text:    a d c e b

dp[0][1]=T, dp[0][2]=F, dp[0][3]=F, dp[0][4]=F
dp[1][1]=T, dp[1][2]=T, dp[1][3]=T, dp[1][4]=F
...
dp[5][4]=T → match!
```

### Complexity
| Time | O(n × m) |
| Space | O(n × m) — optimizable to O(m) |

### Interview Answer
> "The `*` case is the key: it can match empty or one more character. The `dp[i-1][j]` propagation handles matching multiple characters because it cascades upward through the table. Base case: consecutive `*`s at pattern start can match empty text."

---

## 30. Serialize and Deserialize List of Strings
**File:** `advanced/SerializeDeserializeStrings.java`

### Logic (Length-Prefix Encoding)
- **Encode:** For each string, write `length + '#' + string`.
  - Example: `["hello","foo"]` → `"5#hello3#foo"`
- **Decode:** Read digits until `#`, parse as length, read exactly that many chars.

### Why Not Delimiter-Based?
| Approach | Problem |
|---|---|
| Comma-separated | Strings may contain commas |
| Pipe-separated | Strings may contain pipes |
| Escape-based | Complex, error-prone |
| Length-prefix ✓ | Unambiguous, handles any character |

### Flow
```
Encode: ["hello", "foo#bar", "", "42"]
→ "5#hello7#foo#bar0#2#42"

Decode: "5#hello7#foo#bar0#2#42"
i=0: j=1, len=5, read s[2..6]="hello",   i=7
i=7: j=8, len=7, read s[9..15]="foo#bar", i=16
i=16: j=17, len=0, read s[18..17]="",    i=18
i=18: j=19, len=2, read s[20..21]="42",  i=22
Output: ["hello","foo#bar","","42"] ✓
```

### Complexity
| Time | O(n) encode, O(n) decode |
| Space | O(n) |

### Interview Answer
> "Length-prefix encoding is the most robust approach. The '#' delimiter after the length is unambiguous because we always read exactly 'length' characters after it — the content of the string doesn't matter. This handles empty strings (0#), strings with special chars, and strings containing '#' itself."

---

## 31. Regular Expression Matching
**File:** `advanced/RegularExpressionMatching.java`

### Logic (Bottom-Up DP)
- `dp[i][j]` = true if `pattern[0..j-1]` matches `text[0..i-1]`.
- Base: `dp[0][0] = true`; `dp[0][j] = dp[0][j-2]` if `pattern[j-1] == '*'` (x\* matches empty).
- Transition:
  - If `pattern[j-1] == '*'`: zero occurrences → `dp[i][j] = dp[i][j-2]`; one+ → if `matches(text[i-1], pattern[j-2])` → `dp[i][j] |= dp[i-1][j]`
  - Else: `dp[i][j] = dp[i-1][j-1] && matches(text[i-1], pattern[j-1])`

### Key Difference from Wildcard Matching
| | Wildcard | Regex |
|---|---|---|
| `*` meaning | Any sequence independently | Zero or more of preceding element |
| Example | `"a*b"` matches `"xyzb"` | `"a*b"` matches `""`, `"ab"`, `"aab"` |

### Flow — isMatch("aab", "c*a*b")
```
    ""  c  *  a  *  b
""   T  F  T  F  T  F
a    F  F  F  T  T  F
a    F  F  F  F  T  F
b    F  F  F  F  F  T  ← dp[3][5]=T ✓
```

### Complexity
| Time | O(n × m) |
| Space | O(n × m) — optimizable to O(m) with two rows |

### Interview Answer
> "The `*` in regex applies to the preceding element, unlike wildcard where `*` is independent. The two sub-cases for `*` are: zero occurrences (`dp[i][j-2]`) and one-or-more occurrences (`dp[i-1][j]` if preceding element matches). The `dp[0][j]` base case for x\* patterns matching empty string is the most commonly missed detail."

---

## 32. Text Justification
**File:** `advanced/TextJustification.java`

### Logic (Greedy Line Packing)
- Greedily pack as many words as possible per line.
- Three cases when building a line:
  1. Last line → left-justify (single space between words, pad right).
  2. Single word → left-justify (pad right).
  3. Normal line → distribute spaces evenly; extra spaces fill leftmost gaps first.
- `totalSpaces = maxWidth - sum of word lengths on line`
- `spacePerGap = totalSpaces / numGaps`, `extraSpaces = totalSpaces % numGaps`

### Flow
```
words=["This","is","an","example","of","text","justification."], maxWidth=16

Line 1: ["This","is","an"] → totalSpaces=8, gaps=2 → 4 spaces each
        → "This    is    an"

Line 2: ["example","of","text"] → totalSpaces=3, gaps=2 → gap0=2, gap1=1
        → "example  of text"

Line 3: ["justification."] → last line → left-justify
        → "justification.  "
```

### Complexity
| Time | O(n × maxWidth) |
| Space | O(maxWidth) — per line buffer |

### Interview Answer
> "Three special cases: last line, single word per line, and normal line. For normal lines, integer division gives base spaces per gap and the remainder tells how many leftmost gaps get one extra space. Always state these three cases explicitly before coding — interviewers look for this clarity."

---

## 33. Decode Ways
**File:** `advanced/DecodeWays.java`

### Logic (1D DP)
- `dp[i]` = number of ways to decode `s[0..i-1]`.
- Base: `dp[0] = 1` (empty string), `dp[1] = 1` (first char validated non-zero).
- Transition:
  - Single digit: if `s[i-1] != '0'` → `dp[i] += dp[i-1]`
  - Two digits: if `s[i-2..i-1]` in `[10..26]` → `dp[i] += dp[i-2]`

### Flow — s = "226"
```
dp = [1, 1, 0, 0]

i=2: oneDigit=2 ≥ 1 → dp[2]+=dp[1]=1
     twoDigit=22 ∈ [10,26] → dp[2]+=dp[0]=1 → dp[2]=2

i=3: oneDigit=6 ≥ 1 → dp[3]+=dp[2]=2
     twoDigit=26 ∈ [10,26] → dp[3]+=dp[1]=1 → dp[3]=3

Result: 3  ("BBF", "BZ", "VF")
```

### Comparison
| Approach | Time | Space |
|---|---|---|
| Bottom-Up DP | O(n) | O(n) |
| O(1) Space DP ✓ | O(n) | O(1) |

### Interview Answer
> "The `'0'` handling is the tricky part — a single `'0'` is always invalid as a single digit, but `'10'` and `'20'` are valid two-digit decodings. Space can be reduced to O(1) by keeping only the previous two dp values since each position only depends on `dp[i-1]` and `dp[i-2]`."

---
