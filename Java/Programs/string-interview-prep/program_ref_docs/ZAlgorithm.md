# Z-Algorithm — Pattern Search

## Problem
Given a text and pattern, find all starting indices where the pattern occurs in the text.

> Alternative to KMP — same O(n+m) complexity, different approach.

---

## Solution — Z-Algorithm ✅ Optimal

### What is the Z-Array?
For a string `s`, `Z[i]` = length of the longest substring starting at `s[i]` that is also a prefix of `s`.

```
s = "aabxaa"
Z = [_, 1, 0, 0, 2, 1]   (_ = undefined for Z[0])

Z[1]=1: s[1..1]="a" matches prefix s[0..0]="a"
Z[4]=2: s[4..5]="aa" matches prefix s[0..1]="aa"
```

### How to use for pattern search?
Concatenate: `combined = pattern + "$" + text`
- `$` is a separator that never appears in pattern/text — ensures Z values don't cross boundary.
- Wherever `Z[i] == pattern.length()` → pattern found at index `i - (m+1)` in text.

### Step-by-step logic — Build Z-Array
1. `left = 0`, `right = 0` (Z-box window).
2. For each `i` from 1 to n-1:
   - If `i < right` → `Z[i] = min(right - i, Z[i - left])` (use previously computed values).
   - Extend: while `s[Z[i]] == s[i + Z[i]]` → `Z[i]++`.
   - If `i + Z[i] > right` → update `left = i`, `right = i + Z[i]`.

### Dry Run
```
text = "abcabc", pattern = "abc"
combined = "abc$abcabc"
index:      0123456789

Build Z-array:
Z[0] = _ (undefined)
Z[1]: 'b' ≠ 'a' → Z[1]=0
Z[2]: 'c' ≠ 'a' → Z[2]=0
Z[3]: '$' ≠ 'a' → Z[3]=0
Z[4]: 'a'=='a','b'=='b','c'=='c','$'≠'a' → Z[4]=3
Z[5]: inside Z-box, Z[5]=min(right-5, Z[1])=min(5,0)=0, extend: 'b'≠'a' → Z[5]=0
Z[6]: Z[6]=min(right-6, Z[2])=0, extend: 'c'≠'a' → Z[6]=0
Z[7]: Z[7]=min(right-7, Z[0])=0, extend: 'a'=='a','b'=='b','c'=='c' → Z[7]=3
Z[8]: Z[8]=0, extend: 'b'≠'a' → Z[8]=0
Z[9]: Z[9]=0, extend: 'c'≠'a' → Z[9]=0

Z = [_, 0, 0, 0, 3, 0, 0, 3, 0, 0]

Z[4]=3 == pattern.length() → index = 4 - (3+1) = 0 ✓
Z[7]=3 == pattern.length() → index = 7 - (3+1) = 3 ✓

Result: [0, 3] ✓
```

### Complexity


| Time  | O(n + m) | Z-array built in linear time — each position processed at most twice. |
| Space | O(n + m) | Concatenated string + Z-array of same length. |

---

## KMP vs Z-Algorithm

| | KMP | Z-Algorithm |

| Preprocessing | LPS array | Z-array |
| Search | Uses LPS on mismatch | Uses Z-box optimisation |
| Time | O(n + m) | O(n + m) |
| Space | O(m) | O(n + m) |
| Intuition | Failure function | Prefix matching window |

---

## Edge Cases
- Pattern longer than text → empty result.
- Pattern not in text → empty result.
- Overlapping matches → all found correctly.

## Interview Tip
- Asked at Google and Amazon as an alternative to KMP.
- Key insight: the `$` separator prevents Z values from spanning pattern-text boundary.
- Mention both KMP and Z-Algorithm — shows breadth of knowledge.
