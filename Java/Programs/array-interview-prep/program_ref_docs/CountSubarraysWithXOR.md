# Count Subarrays with Given XOR

## Problem
Count the number of subarrays whose XOR equals k.

## Approach — Prefix XOR + HashMap

### Core Idea (mirrors prefix sum logic)
Let `prefixXor[i]` = XOR of all elements from index 0 to i.
XOR of subarray `(j+1, i)` = `prefixXor[i] XOR prefixXor[j]`.
We want this = k, so we need `prefixXor[j] = prefixXor[i] XOR k`.

### Step-by-step logic
1. Initialize map with `{0: 1}` (empty prefix has XOR = 0).
2. `prefixXor = 0`, `count = 0`.
3. For each element:
   - Update `prefixXor ^= num`.
   - Look up `prefixXor ^ k` in map → add its frequency to `count`.
   - Increment `prefixXor` count in map.

### Dry Run
```
arr = [4, 2, 2, 6, 4], k = 6, map={0:1}
num=4: xor=4, look for 4^6=2 → not in map, map={0:1,4:1}
num=2: xor=6, look for 6^6=0 → count+=1, map={0:1,4:1,6:1}
num=2: xor=4, look for 4^6=2 → not in map, map={0:1,4:2,6:1}
num=6: xor=2, look for 2^6=4 → count+=2, map={0:1,4:2,6:1,2:1}
num=4: xor=6, look for 6^6=0 → count+=1, map={0:1,4:2,6:2,2:1}
Total count = 4
```

## Complexity


| Time  | O(n) |
| Space | O(n) |

## Edge Cases
- k = 0 → count subarrays with XOR = 0 (even occurrences of each element).
- Single element equal to k → counts as 1.

## Interview Tip
This is the XOR version of "count subarrays with sum k". The math is identical — just replace + with XOR and - with XOR (since a XOR a = 0, XOR is its own inverse).


