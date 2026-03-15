# Longest Repeating Character Replacement

## Problem
Given a string `s` and integer `k`, find the length of the longest substring where you can replace at most `k` characters to make all characters the same.

---

## Solution — Sliding Window ✅ Optimal

### Core Idea
Window is valid if: `(windowSize - maxFreq) <= k`
- `windowSize` = total chars in window
- `maxFreq` = count of most frequent char in window
- `windowSize - maxFreq` = chars that need to be replaced

If replacements needed > k → shrink window from left.

### Step-by-step logic
1. `freq[26] = {0}`, `left = 0`, `maxFreq = 0`, `maxLen = 0`.
2. For each `right`:
   - Increment `freq[s[right]]`.
   - Update `maxFreq = max(maxFreq, freq[s[right]])`.
   - While `(right - left + 1) - maxFreq > k` → decrement `freq[s[left]]`, `left++`.
   - Update `maxLen = max(maxLen, right - left + 1)`.
3. Return `maxLen`.

### Dry Run
```
s = "AABABBA", k = 1
freq=[], left=0, maxFreq=0

right=0 'A': freq[A]=1, maxFreq=1, window=1, 1-1=0 ≤ 1, maxLen=1
right=1 'A': freq[A]=2, maxFreq=2, window=2, 2-2=0 ≤ 1, maxLen=2
right=2 'B': freq[B]=1, maxFreq=2, window=3, 3-2=1 ≤ 1, maxLen=3
right=3 'A': freq[A]=3, maxFreq=3, window=4, 4-3=1 ≤ 1, maxLen=4
right=4 'B': freq[B]=2, maxFreq=3, window=5, 5-3=2 > 1
  → shrink: freq[A]=2, left=1, window=4, 4-3=1 ≤ 1, maxLen=4
right=5 'B': freq[B]=3, maxFreq=3, window=5, 5-3=2 > 1
  → shrink: freq[A]=1, left=2, window=4, 4-3=1 ≤ 1, maxLen=4
right=6 'A': freq[A]=2, maxFreq=3, window=5, 5-3=2 > 1
  → shrink: freq[B]=2, left=3, window=4, 4-3=1 ≤ 1, maxLen=4

Result: 4 ✓ (window "ABBA" → replace one A with B → "BBBB")
```

### Complexity


| Time  | O(n) | Each character added and removed at most once. |
| Space | O(1) | Fixed `int[26]` frequency array — constant size. |

---

## Edge Cases
- All same characters → entire string is the answer.
- k >= string length → entire string is the answer.
- k = 0 → longest run of same character.

## Interview Tip
- LeetCode #424. Direct follow-up to Longest Substring Without Repeating Characters.
- Key insight: we never need to shrink `maxFreq` when shrinking the window — even if it becomes stale, it only causes the window to not grow, never to give wrong answer.
- Follow-up: "What if we need the actual substring?" → track `start` index when `maxLen` updates.
