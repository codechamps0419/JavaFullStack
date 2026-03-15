# Find the Missing Number in an Array of 1 to N

## Problem
Given an array containing n-1 numbers from 1 to n with one number missing, find the missing number.

---

## Solution 1 — Math Formula (Sum of N natural numbers)

⚠️ Can exceed the maximum value that a data type can store for very large n.

### Why math formula?
Sum of first N natural numbers = `N * (N + 1) / 2`.
Subtract actual array sum from expected sum → gives the missing number.
No extra space, no sorting needed.

### Step-by-step logic
1. Calculate `expectedSum = n * (n + 1) / 2`.
2. Calculate `actualSum` by summing all elements in the array.
3. Return `expectedSum - actualSum`.

### Dry Run
```
arr = [1, 2, 4, 5, 6], n = 6

expectedSum = 6 * 7 / 2 = 21
actualSum   = 1+2+4+5+6 = 18
missing     = 21 - 18   = 3 ✓
```

### Complexity


| Time  | O(n) | One pass to compute `actualSum`. |
| Space | O(1) | Only two integer variables used. |

### ⚠️ Where Overflow Can Happen
**Line:** `int expectedSum = n * (n + 1) / 2;`

**When:** If `n` is large (e.g., n ≥ 46341 for `int`), the multiplication `n * (n + 1)` can exceed `Integer.MAX_VALUE` (2,147,483,647) before the division by 2.

**Example:**
```
n = 50000
n * (n + 1) = 50000 * 50001 = 2,500,050,000 > Integer.MAX_VALUE → overflow!
```

**Fix:** Use `long` for intermediate calculation:
```java
long expectedSum = (long) n * (n + 1) / 2;
```

---

## Solution 2 — XOR

XOR of a number with itself = 0, XOR with 0 = the number itself.
XOR all numbers 1 to n, then XOR with all array elements — paired numbers cancel out, leaving the missing number.

### Step-by-step logic
1. XOR all numbers from 1 to n → `xor1`.
2. XOR all elements in the array → `xor2`.
3. Return `xor1 ^ xor2` — all present numbers cancel, missing number remains.

### Dry Run
```
arr = [1, 2, 4, 5, 6], n = 6

xor1 = 1^2^3^4^5^6 = 7
xor2 = 1^2^4^5^6   = 4

xor1 ^ xor2 = 7 ^ 4
            = 0111 ^ 0100
            = 0011 = 3 ✓
```

### Complexity


| Time  | O(n) | Two separate passes — one over 1..n, one over array. O(n)+O(n) = O(n). |
| Space | O(1) | Only two XOR accumulator variables used. |

### ✅ No Overflow Risk
XOR is a bitwise operation — operates on individual bits, not numeric magnitude. No addition or multiplication involved, so no risk of exceeding `Integer.MAX_VALUE` regardless of how large `n` is.

---

## Solution 3 — Java 8 Streams (Sum formula)

⚠️ Can exceed the maximum value that a data type can store for very large n.

Uses `IntStream.rangeClosed()` for expected sum and `Arrays.stream()` for actual sum — same math as Solution 1 but with Streams API.

```java
int n = arr.length + 1; // total numbers including missing
int missing = IntStream.rangeClosed(1, n).sum() - Arrays.stream(arr).sum();
```

### Step-by-step logic
1. `n = arr.length + 1` → derive total count including the missing number (array has n-1 elements).
2. `IntStream.rangeClosed(1, n).sum()` → computes expected sum of 1 to n.
3. `Arrays.stream(arr).sum()` → computes actual sum of array elements.
4. Return the difference.

### Dry Run
```
arr = [1, 2, 4, 5, 6]

n = arr.length + 1 = 5 + 1 = 6

IntStream.rangeClosed(1, 6).sum() = 1+2+3+4+5+6 = 21
Arrays.stream(arr).sum()          = 1+2+4+5+6   = 18

missing = 21 - 18 = 3 ✓
```

### Complexity


| Time  | O(n) | Two stream passes — one over 1..n, one over array elements. |
| Space | O(1) | No extra data structure — streams process elements sequentially. |

### ⚠️ Where Overflow Can Happen
**Line:** `IntStream.rangeClosed(1, n).sum()`

**When:** The internal sum accumulator in `IntStream.sum()` is an `int`. If the sum of 1 to n exceeds `Integer.MAX_VALUE`, overflow occurs.

**Example:**
```
n = 50000
Sum = n * (n + 1) / 2 = 1,250,025,000 (fits in int)

n = 70000
Sum = 70000 * 70001 / 2 = 2,450,035,000 > Integer.MAX_VALUE → overflow!
```

**Fix:** Use `LongStream` instead:
```java
long missing = LongStream.rangeClosed(1, n).sum() - Arrays.stream(arr).asLongStream().sum();
```

---

## Comparison

| Solution | Approach | Overflow Risk | Time | Space |
---
| 1 — Math Formula | Manual loop + sum formula | ⚠️ Yes | O(n) | O(1) |
| 2 — XOR | Bitwise XOR cancellation | ✅ No | O(n) | O(1) |
| 3 — Streams | Stream sum formula | ⚠️ Yes | O(n) | O(1) |

---

## Edge Cases
- Missing first element (1) → all three solutions work.
- Missing last element (n) → all three solutions work.
- Large n → prefer Solution 2 (XOR) to avoid integer overflow.

## Interview Tip
- Start with Solution 1 — simple and intuitive.
- If interviewer asks about overflow, switch to Solution 2 (XOR) — no overflow risk since XOR is bitwise.
- Mention Solution 3 if asked for a Java 8+ approach.

---

## Follow-up Questions

**Q1. What if the array contains numbers from 0 to n instead of 1 to n?**
Adjust the expected sum formula to `n * (n + 1) / 2` where n = arr.length (since range is 0..n). XOR approach works as-is — just XOR from 0 to n instead of 1 to n.

**Q2. What if two numbers are missing instead of one?**
Sum formula gives the sum of two missing numbers (S = expectedSum - actualSum). XOR gives XOR of two missing numbers (X = xor1 ^ xor2). Use the rightmost set bit of X to split numbers into two groups, then XOR each group separately to find both missing numbers. Time O(n), Space O(1).

**Q3. What if the array has duplicates instead of a missing number — find the duplicate?**
Same sum formula: `actualSum - expectedSum` gives the duplicate. XOR approach also works identically — the duplicate is the number that doesn't cancel out.

**Q4. Why does the XOR approach not suffer from integer overflow?**
XOR is a bitwise operation — it operates on individual bits, not on the numeric magnitude. No addition or multiplication involved, so there is no risk of exceeding `Integer.MAX_VALUE`.

**Q5. Can this be solved using a HashSet?**
Yes — add all array elements to a HashSet, then iterate 1 to n and return the first number not in the set. Time O(n), but Space O(n) — worse than the O(1) space solutions above.

**Q6. What if n is very large (e.g., n = 10^9)?**
Use `long` instead of `int` for the sum variables in Solution 1 and 3 to avoid overflow. Or use Solution 2 (XOR) which has no overflow risk regardless of n.


