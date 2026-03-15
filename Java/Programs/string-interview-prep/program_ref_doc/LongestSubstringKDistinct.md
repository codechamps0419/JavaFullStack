# Longest Substring with At Most K Distinct Characters

## Problem
Given a string `s` and integer `k`, find the length of the longest substring that contains at most `k` distinct characters.

---

## Solution — Sliding Window + HashMap ✅ Optimal

### Why HashMap?
We need to track frequency of each character in the current window. When distinct count exceeds k, shrink from left until it's back to k.

### Step-by-step logic
1. If `k == 0` → return 0.
2. `freq = {}`, `left = 0`, `maxLen = 0`.
3. For each `right`:
   - Add `s[right]` to freq map (increment count).
   - While `freq.size() > k`:
     - Decrement `freq[s[left]]`.
     - If count hits 0 → remove from map.
     - `left++`.
   - Update `maxLen = max(maxLen, right - left + 1)`.
4. Return `maxLen`.

### Dry Run
```
s = "eceba", k = 2
freq={}, left=0

right=0 'e': freq={e:1}, size=1 ≤ 2, maxLen=1
right=1 'c': freq={e:1,c:1}, size=2 ≤ 2, maxLen=2
right=2 'e': freq={e:2,c:1}, size=2 ≤ 2, maxLen=3
right=3 'b': freq={e:2,c:1,b:1}, size=3 > 2
  → shrink: freq[e]=1, left=1 → size=3 > 2
  → shrink: freq[c]=0 remove, left=2 → freq={e:1,b:1}, size=2 ≤ 2, maxLen=3
right=4 'a': freq={e:1,b:1,a:1}, size=3 > 2
  → shrink: freq[e]=0 remove, left=3 → freq={b:1,a:1}, size=2 ≤ 2, maxLen=3

Result: 3 → "ece" ✓
```

### Complexity


| Time  | O(n) | Each character added and removed at most once. |
| Space | O(k) | Map holds at most k+1 entries before shrinking. |

---

## Edge Cases
- k = 0 → return 0.
- k >= number of distinct chars → return string length.
- k = 1 → find longest run of same character.
- All same characters → return string length.

## Interview Tip
- LeetCode #340. Direct follow-up to Longest Substring Without Repeating Characters (k=all unique).
- Same pattern also solves "Fruits into Baskets" (LeetCode #904) — exact same logic with k=2.
- Follow-up: "Exactly k distinct" → `atMostK(s, k) - atMostK(s, k-1)`.
