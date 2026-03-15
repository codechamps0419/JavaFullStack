# Java String Interview — Compression & Encoding

---

## 13. Run-Length Encoding (RLE Compression)
**File:** `compression/RunLengthEncoding.java`

### Logic
- Iterate through the string with a counter starting at 1.
- When current char == previous char: increment counter.
- When char changes (or end of string): append char + count to result.
- Only append count if > 1 (avoids "a1b1" for "ab").

### Flow
```
Input: "aaabbbcc"

i=1: 'a'=='a' → count=2
i=2: 'a'=='a' → count=3
i=3: 'b'!='a' → append 'a','3' → count=1
i=4: 'b'=='b' → count=2
i=5: 'b'=='b' → count=3
i=6: 'c'!='b' → append 'b','3' → count=1
i=7: 'c'=='c' → count=2
i=8: end      → append 'c','2'

Output: "a3b3c2"
```

### Edge Cases
| Input | Output | Reason |

| "abcd" | "abcd" | No compression (all unique) |
| "aaaaa" | "a5" | Full compression |
| "" | "" | Empty string |
| "aab" | "a2b" | Mixed |

### Complexity
| Time | O(n) |
| Space | O(n) — output string |

### Interview Answer
> "Single pass with a counter. The trick is iterating to `i <= s.length()` (one past end) so the last group is always flushed. I skip appending count when it's 1 to keep output clean, but this is configurable based on requirements."

---

## 14. Run-Length Decoding
**File:** `compression/RunLengthDecoding.java`

### Logic
- Iterate through encoded string.
- When a letter is found: read all following digit characters as the count.
- Append the letter `count` times using `String.repeat()` (Java 11+).
- If no digit follows a letter, default count = 1.

### Flow
```
Input: "a3b2c1"

i=0: char='a', digits="3" → count=3 → append "aaa"
i=4: char='b', digits="2" → count=2 → append "bb"
i=7: char='c', digits="1" → count=1 → append "c"

Output: "aaabbc"
```

### Handling Multi-digit Counts
```
Input: "a12b3"
i=0: char='a', digits="12" → count=12 → append "aaaaaaaaaaaa"
i=4: char='b', digits="3"  → count=3  → append "bbb"
Output: "aaaaaaaaaaaabbb"
```

### Complexity
| Time | O(n + m) — n=encoded, m=decoded length |
| Space | O(m) — decoded output |

### Interview Answer
> "I read the letter first, then collect all consecutive digit characters to form the count. Using StringBuilder with String.repeat() (Java 11+) is clean and efficient. The inner while loop handles multi-digit counts like '12' or '100'."

---

## 15. Check if String is a Rotation of Another
**File:** `compression/StringRotationCheck.java`

### Logic
- If `s2` is a rotation of `s1`, then `s2` must appear as a substring in `s1 + s1`.
- First verify lengths are equal (necessary condition).
- Use `String.contains()` which uses an optimized search internally.

### Why s1+s1 Contains All Rotations?
```
s1 = "abcde"
s1+s1 = "abcdeabcde"

All rotations of s1:
"abcde" → found at index 0
"bcdea" → found at index 1
"cdeab" → found at index 2
"deabc" → found at index 3
"eabcd" → found at index 4
```

### Flow
```
s1="abcde", s2="cdeab"
s1+s1 = "abcdeabcde"
"abcdeabcde".contains("cdeab") → true at index 2
Output: true
```

### Complexity
| Time | O(n) — contains() is O(n) |
| Space | O(n) — concatenated string |

### Interview Answer
> "The elegant insight is that s1+s1 contains every possible rotation of s1 as a substring. So checking if s2 is in s1+s1 is sufficient. This reduces a complex rotation check to a single contains() call. Always check lengths first to avoid false positives."

---
