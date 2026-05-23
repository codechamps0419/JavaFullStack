# Java String Interview — Number & Type Conversions

---

## 20. String to Integer — myAtoi()
**File:** `conversion/StringToInteger.java`

### Logic (4 Steps)
1. Strip leading/trailing whitespace.
2. Read optional sign (`+` or `-`), default = positive.
3. Read digits until non-digit or end of string.
4. Clamp to `[Integer.MIN_VALUE, Integer.MAX_VALUE]` on overflow.

### Flow
```
Input: "  -42"
Step 1: strip → "-42"
Step 2: sign=-1, i=1
Step 3: digit '4' → result=4, digit '2' → result=42
Step 4: result*sign = -42, within bounds
Output: -42
```

### Edge Cases Table
| Input | Output | Reason |

| "42" | 42 | Normal |
| "  -42" | -42 | Leading spaces + sign |
| "4193 with words" | 4193 | Stop at non-digit |
| "words and 987" | 0 | No leading digit |
| "99999999999" | 2147483647 | Overflow → MAX |
| "-99999999999" | -2147483648 | Underflow → MIN |
| "" | 0 | Empty |

### Complexity
| Time | O(n) |
| Space | O(1) |

### Interview Answer
> "Four steps: strip whitespace, read sign, read digits, clamp overflow. I use `long result` to detect overflow before casting to int. The overflow check `result * sign >= MAX_VALUE` catches it before the value wraps around. Stop immediately on any non-digit character."

---

## 21. Check if a String is a Valid Number
**File:** `conversion/ValidNumber.java`

### Logic (State Flags)
Track three boolean flags:
- `seenDigit` — at least one digit seen
- `seenDot` — decimal point seen
- `seenE` — exponent 'e'/'E' seen

### Rules
| Character | Rule |
|---|---|
| Digit | Set seenDigit=true |
| `+` or `-` | Only valid at index 0 OR right after 'e'/'E' |
| `.` | Only once, not after 'e'/'E' |
| `e` or `E` | Only once, must have digit before it; reset seenDigit (need digit after) |
| Anything else | Invalid → return false |

### Flow
```
Input: "2.5e-3"
i=0 '2': seenDigit=true
i=1 '.': seenDot=true
i=2 '5': seenDigit=true
i=3 'e': seenE=true, seenDigit=false (reset)
i=4 '-': prev is 'e' → valid sign
i=5 '3': seenDigit=true
End: seenDigit=true → valid ✓

Input: "1e"
i=0 '1': seenDigit=true
i=1 'e': seenE=true, seenDigit=false (reset)
End: seenDigit=false → invalid ✗
```

### Complexity
| Time | O(n) |
| Space | O(1) |

### Interview Answer
> "State machine approach with three flags. The trickiest part is the sign rule — '+'/'-' is only valid at position 0 or immediately after 'e'/'E'. After seeing 'e', I reset seenDigit to false because we need at least one digit in the exponent part."

---

## 22. Roman Numeral ↔ Integer Conversion
**File:** `conversion/RomanNumeralConversion.java`

### Roman to Integer Logic
- If current symbol value < next symbol value → subtract (subtractive notation).
- Otherwise → add current value.
- Single left-to-right pass.

### Subtractive Cases
| Roman | Value | Rule |

| IV | 4 | I(1) < V(5) → subtract |
| IX | 9 | I(1) < X(10) → subtract |
| XL | 40 | X(10) < L(50) → subtract |
| XC | 90 | X(10) < C(100) → subtract |
| CD | 400 | C(100) < D(500) → subtract |
| CM | 900 | C(100) < M(1000) → subtract |

### Integer to Roman Logic
- LinkedHashMap with values in descending order.
- Greedily subtract largest possible value, append its symbol.

### Flow — intToRoman(1994)
```
1994 >= 1000 → append "M",  num=994
994  >= 900  → append "CM", num=94
94   >= 90   → append "XC", num=4
4    >= 4    → append "IV", num=0
Output: "MCMXCIV"
```

### Complexity
| Time | O(1) — bounded by max value 3999 |
| Space | O(1) — fixed symbol table |

### Interview Answer
> "For Roman→Int: compare current and next value. If current < next, subtract (subtractive notation). For Int→Roman: greedy approach with a descending value map. Include all 13 values including the 6 subtractive combinations (IV, IX, XL, XC, CD, CM)."

---

## 23. Convert Number to Words
**File:** `conversion/NumberToWords.java`

### Logic
- Break number into groups of 3 digits (ones, thousands, millions, billions).
- Convert each 3-digit chunk using `threeDigits()` helper.
- Append scale word (Thousand/Million/Billion) for non-zero groups.
- Insert each group at the front of result (prepend).

### threeDigits() Helper
```
n < 20  → use ONES array directly (handles teens: 11-19)
n < 100 → TENS[n/10] + ONES[n%10]
n >= 100 → ONES[n/100] + "Hundred" + threeDigits(n%100)
```

### Flow — numberToWords(1234)
```
1234 % 1000 = 234 → threeDigits(234) = "Two Hundred Thirty Four", scale=""
  → prepend "Two Hundred Thirty Four"
1234 / 1000 = 1
1 % 1000 = 1 → threeDigits(1) = "One", scale="Thousand"
  → prepend "One Thousand Two Hundred Thirty Four"
```

### Edge Cases
| Input | Output |
|---|---|
| 0 | "Zero" |
| 13 | "Thirteen" |
| 1000000 | "One Million" |
| 1000010 | "One Million Ten" |

### Complexity
| Time | O(1) — max 10 digits |
| Space | O(1) |

### Interview Answer
> "Process number in groups of 3 from right to left. Each group gets a scale suffix (Thousand, Million, Billion). The threeDigits() helper handles 1-999. Special care for teens (11-19) since they don't follow the regular tens+ones pattern. Always handle 0 as a special case."

---
