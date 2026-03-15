# Count and Say

## Problem
The count-and-say sequence is defined as:
- n=1 → "1"
- n=2 → "11" (one 1)
- n=3 → "21" (two 1s)
- n=4 → "1211" (one 2, one 1)
- n=5 → "111221" (one 1, one 2, two 1s)

Given n, return the nth term of the sequence.

---

## Solution 1 — Iterative ✅ Optimal

### Step-by-step logic
1. Start with `result = "1"`.
2. Repeat `n-1` times:
   - Scan `result` left to right.
   - Count consecutive identical characters.
   - When char changes (or end): append `count` + `char` to new string.
   - Replace `result` with new string.
3. Return `result`.

### Dry Run
```
n=5, start with "1"

Iteration 1: "1"
  j=0: c='1', count=1 → end → append "11"
  result = "11"

Iteration 2: "11"
  j=0: c='1', count=2 → end → append "21"
  result = "21"

Iteration 3: "21"
  j=0: c='2', count=1 → j=1: c='1' ≠ '2' → append "12"
  j=1: c='1', count=1 → end → append "11"
  result = "1211"

Iteration 4: "1211"
  j=0: c='1', count=1 → j=1: c='2' ≠ '1' → append "11"
  j=1: c='2', count=1 → j=2: c='1' ≠ '2' → append "12"
  j=2: c='1', count=2 → end → append "21"
  result = "111221"

Output: "111221" ✓
```

### Complexity


| Time  | O(n × m) | n iterations, each processes string of length m (grows with n). |
| Space | O(m) | StringBuilder for current term only. |

---

## Solution 2 — Recursive

### Step-by-step logic
1. Base case: `n == 1` → return `"1"`.
2. Get previous term: `prev = countAndSay(n-1)`.
3. Scan `prev`, count consecutive chars, build and return new term.

### Dry Run
```
countAndSay(4)
  → prev = countAndSay(3) = "21"
  → scan "21": '2'×1 → "12", '1'×1 → "11"
  → return "1211" ✓
```

### Complexity


| Time  | O(n × m) | n recursive calls, each processes string of length m. |
| Space | O(n) | Recursion stack depth = n. |

---

## Comparison

| Solution | Time | Space | Notes |
---|
| 1 — Iterative | O(n × m) | O(m) | Preferred — no stack overhead |
| 2 — Recursive | O(n × m) | O(n) | Clean but uses call stack |

---

## Edge Cases
- n=1 → return `"1"` directly.
- Large n → string grows exponentially — iterative is safer (no stack overflow).

## Interview Tip
- LeetCode #38. Prefer iterative — avoids stack overflow for large n.
- Key insight: each term is a "description" of the previous term — read it aloud.
- Follow-up: "What is the maximum length of the nth term?" — grows roughly by 1.3× per step.
