# Decode Ways

## Problem
A string of digits can be decoded where `'1'→'A'`, `'2'→'B'`, ..., `'26'→'Z'`.
Given a digit string, return the number of ways to decode it.

---

## Solution 1 — Bottom-Up DP

### Core Idea
`dp[i]` = number of ways to decode `s[0..i-1]`.
At each position, try decoding the last 1 digit and last 2 digits.

### Step-by-step logic
1. If `s[0] == '0'` → return 0 (leading zero, invalid).
2. `dp[0] = 1` (empty string), `dp[1] = 1` (first char already validated).
3. For each `i` from 2 to n:
   - `oneDigit = s[i-1]` → if `oneDigit >= 1` → `dp[i] += dp[i-1]`.
   - `twoDigit = s[i-2..i-1]` → if `10 <= twoDigit <= 26` → `dp[i] += dp[i-2]`.
4. Return `dp[n]`.

### Dry Run
```
s = "226"
dp = [1, 1, 0, 0]

i=2: oneDigit=2 ≥ 1 → dp[2]+=dp[1]=1
     twoDigit=22, 10≤22≤26 → dp[2]+=dp[0]=1 → dp[2]=2
     dp = [1, 1, 2, 0]

i=3: oneDigit=6 ≥ 1 → dp[3]+=dp[2]=2
     twoDigit=26, 10≤26≤26 → dp[3]+=dp[1]=1 → dp[3]=3
     dp = [1, 1, 2, 3]

Result: dp[3] = 3 ✓
("BBF"=2,2,6 | "BZ"=2,26 | "VF"=22,6)
```

### Complexity


| Time  | O(n) | Single pass through the string. |
| Space | O(n) | dp array of size n+1. |

---

## Solution 2 — O(1) Space DP ✅ Optimal

Only the previous two dp values are needed at any point.

```java
int prev2 = 1, prev1 = 1;
for (int i = 2; i <= n; i++) {
    int curr = 0;
    if (oneDigit >= 1)                    curr += prev1;
    if (twoDigit >= 10 && twoDigit <= 26) curr += prev2;
    prev2 = prev1;
    prev1 = curr;
}
return prev1;
```

### Step-by-step logic
1. `prev2 = 1` (dp[0]), `prev1 = 1` (dp[1]).
2. For each position: compute `curr` from `prev1` and `prev2`.
3. Slide: `prev2 = prev1`, `prev1 = curr`.
4. Return `prev1`.

### Dry Run
```
s = "226"
prev2=1, prev1=1

i=2: oneDigit=2, twoDigit=22
  curr = prev1(1) + prev2(1) = 2
  prev2=1, prev1=2

i=3: oneDigit=6, twoDigit=26
  curr = prev1(2) + prev2(1) = 3
  prev2=2, prev1=3

Result: prev1 = 3 ✓
```

### Complexity


| Time  | O(n) | Single pass. |
| Space | O(1) | Only two variables instead of full dp array. |

---

## Comparison

| Solution | Time | Space |

| 1 — Bottom-Up DP | O(n) | O(n) |
| 2 — O(1) Space DP | O(n) | O(1) |

---

## Edge Cases
- Leading `'0'` → 0 ways.
- `'0'` in middle: `oneDigit=0` → can't decode as single digit. Must be part of `10` or `20`.
- `'30'`, `'40'`... → `twoDigit > 26` → can't decode as two digits either → 0 ways.
- Empty string → 1 way (base case).

## Interview Tip
- LeetCode #91. Top Amazon/Google DP string question.
- The `'0'` handling is the tricky part — always clarify: single `'0'` is invalid, but `'10'` and `'20'` are valid two-digit decodings.
- Follow-up: "Return all decoded strings" → backtracking instead of DP.
