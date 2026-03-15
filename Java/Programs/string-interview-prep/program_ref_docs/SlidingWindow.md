# Java String Interview — Sliding Window

---

## 16. Longest Substring Without Repeating Characters
**File:** `window/LongestSubstringNoRepeat.java`

### Logic
- HashMap stores character → last seen index.
- `left` pointer marks the start of the current valid window.
- For each char at `right`:
  - If char was seen AND its last index >= left → move left to `lastIndex + 1`.
  - Update char's latest index.
  - Update max = `right - left + 1`.

### Flow
```
Input: "abcabcbb"

right=0 'a': map={a:0}, left=0, max=1
right=1 'b': map={a:0,b:1}, left=0, max=2
right=2 'c': map={a:0,b:1,c:2}, left=0, max=3
right=3 'a': a seen at 0 >= left(0) → left=1, map={a:3,b:1,c:2}, max=3
right=4 'b': b seen at 1 >= left(1) → left=2, map={a:3,b:4,c:2}, max=3
right=5 'c': c seen at 2 >= left(2) → left=3, map={a:3,b:4,c:5}, max=3
right=6 'b': b seen at 4 >= left(3) → left=5, max=3
right=7 'b': b seen at 6 >= left(5) → left=7, max=3

Output: 3
```

### Why Check `lastIndex >= left`?
```
Input: "dvdf"
right=2 'd': d seen at 0, but left=1 (already past it) → DON'T move left
Without this check: left would wrongly move backward
```

### Complexity
| Time | O(n) |
| Space | O(k) — k = charset size |

### Interview Answer
> "The HashMap stores the last seen index of each character. The critical check is `lastIndex >= left` — without it, we'd incorrectly shrink the window past its current start. This handles cases where a character was seen before the current window began."

---

## 17. Minimum Window Substring
**File:** `window/MinimumWindowSubstring.java`

### Logic
- Build frequency map for pattern `t`.
- `required` = number of unique chars in `t` that still need to be satisfied.
- Expand `right`: when a needed char reaches required frequency → decrement `required`.
- When `required == 0` (all chars covered):
  - Record window if smallest so far.
  - Shrink from `left`: if removed char was needed → increment `required`.
- Repeat until `right` reaches end.

### Flow
```
s="ADOBECODEBANC", t="ABC"
need={A:1,B:1,C:1}, required=3

Expand until required=0:
  right covers A,D,O,B,E,C → required=0, window="ADOBEC" (len=6)
Shrink:
  remove A → required=1, minWindow="ADOBEC"
Expand:
  right covers O,D,E,B,A → required=0, window="DOBECODEBA"
Shrink:
  remove D,O,B,E,C → required increases
  ...
Final minWindow = "BANC"
```

### Complexity
| Time | O(n + m) |
| Space | O(m) — frequency maps |

### Interview Answer
> "Classic expand-then-shrink sliding window. The `required` counter tracks how many unique characters are fully satisfied. When required hits 0, I try to shrink from left. The window map tracks current counts — when a char drops below needed, required goes back up."

---

## 18. Longest Palindromic Substring
**File:** `window/LongestPalindromicSubstring.java`

### Logic
- For each index `i`, expand outward in two cases:
  1. Odd-length: center at `i` → `expand(i, i)`
  2. Even-length: center between `i` and `i+1` → `expand(i, i+1)`
- Expand while `left >= 0`, `right < n`, and `s[left] == s[right]`.
- Return `right - left - 1` as the palindrome length.
- Track start index: `start = i - (len-1)/2`

### Why Two Cases?
```
"racecar" → odd palindrome, center at 'e' (index 3)
"abba"    → even palindrome, center between 'b' and 'b'
```

### Flow
```
Input: "babad"
i=0 'b': odd=1, even=0 → len=1
i=1 'a': odd expand: b==d? No → len=1; even: a==b? No → len=0
i=2 'b': odd expand: a==a? Yes → expand: b==b? No → len=3 (aba)
         even: b==a? No → len=0
         maxLen=3, start=1
i=3 'a': odd expand: b==d? No → len=1
...
Output: "bab" (or "aba" depending on first max)
```

### Complexity
| Time | O(n²) |
| Space | O(1) |

### Interview Answer
> "Expand-around-center is O(1) space vs O(n²) for DP. For each of the 2n-1 possible centers (n odd + n-1 even), I expand outward while characters match. The start index formula `i - (len-1)/2` correctly handles both odd and even cases."

---

## 19. Count All Palindromic Substrings
**File:** `window/CountPalindromicSubstrings.java`

### Logic
- Same expand-around-center as above.
- Instead of tracking max length, count every successful expansion.
- Each expansion that succeeds = one palindromic substring found.
- Single characters always count as palindromes (odd expand starts with count=1).

### Flow
```
Input: "aaa"
i=0 'a':
  odd:  expand(0,0) → "a"(count=1), can't expand more → +1
  even: expand(0,1) → "aa"(count=1), can't expand more → +1
i=1 'a':
  odd:  expand(1,1) → "a"(+1), expand(0,2) → "aaa"(+1) → +2
  even: expand(1,2) → "aa"(+1), can't expand → +1
i=2 'a':
  odd:  expand(2,2) → "a"(+1) → +1
  even: expand(2,3) → out of bounds → +0

Total: 1+1+2+1+1 = 6
("a","a","a","aa","aa","aaa")
```

### Complexity
| Time | O(n²) |
| Space | O(1) |

### Interview Answer
> "Same expand-around-center technique as finding the longest palindrome, but instead of tracking max, I count every successful expansion. The count for each center is the number of times we can expand before a mismatch — each expansion is a distinct palindromic substring."

---
