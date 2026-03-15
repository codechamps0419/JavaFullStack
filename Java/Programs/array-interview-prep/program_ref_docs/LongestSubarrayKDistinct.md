# Longest Subarray with At Most K Distinct Elements

## Problem
Find the length of the longest subarray that contains at most K distinct elements.

## Approach — Sliding Window + HashMap

### Why HashMap?
We need to track the frequency of each element in the current window.
When distinct count exceeds k, shrink from left until it's back to k.

### Step-by-step logic
1. `freq = {}`, `left = 0`, `maxLen = 0`.
2. Expand window: add `arr[right]` to freq map.
3. While `freq.size() > k` (too many distinct elements):
   - Decrement `arr[left]` frequency.
   - If frequency hits 0 → remove from map.
   - `left++`.
4. Update `maxLen = max(maxLen, right - left + 1)`.

### Dry Run
```
arr = [1,2,1,2,3], k=2
right=0(1): freq={1:1}, size=1 ≤ 2, maxLen=1
right=1(2): freq={1:1,2:1}, size=2 ≤ 2, maxLen=2
right=2(1): freq={1:2,2:1}, size=2 ≤ 2, maxLen=3
right=3(2): freq={1:2,2:2}, size=2 ≤ 2, maxLen=4
right=4(3): freq={1:2,2:2,3:1}, size=3 > 2
  shrink: freq[1]=1, left=1
  size=3 > 2 → shrink: freq[2]=1, left=2
  size=2 ≤ 2, maxLen=max(4,3)=4
Result: 4 → [1,2,1,2]
```

## Complexity


| Time  | O(n) |
| Space | O(k) — map holds at most k+1 entries |

## Edge Cases
- k >= number of distinct elements → return array length.
- k = 1 → find longest run of same element.
- k = 0 → return 0.

## Interview Tip
This pattern (sliding window + frequency map) also solves "Longest Substring with K Distinct Characters" — exact same logic, just on a char array.


