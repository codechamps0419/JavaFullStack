# Java String Interview — Anagram & Permutation

---

## 6. Check if Two Strings are Anagrams
**File:** `anagram/AnagramCheck.java`

### Logic
- Use a single `int[26]` frequency array (for lowercase letters).
- Increment for each char in `s1`, decrement for each char in `s2`.
- If all values in array are 0 → anagrams.
- First check: lengths must be equal.

### Flow
```
s1 = "listen", s2 = "silent"
freq after s1: l=1,i=1,s=1,t=1,e=1,n=1
freq after s2: l=0,i=0,s=0,t=0,e=0,n=0
All zeros → true
```

### Why Not Sorting?
| Approach | Time | Space |

| Sort both strings | O(n log n) | O(n) |
| Frequency array ✓ | O(n) | O(1) |

### Interview Answer
> "Frequency array approach is optimal — O(n) time, O(1) space since the array is always size 26. Sorting is O(n log n) and creates new char arrays. I increment for s1 and decrement for s2 in the same loop, then verify all zeros."

---

## 7. Generate All Permutations of a String
**File:** `anagram/StringPermutations.java`

### Logic
- Use backtracking with in-place swapping on a char array.
- At each index, try placing every remaining character there.
- Use a `HashSet` at each recursion level to skip duplicate characters.
- After recursing, swap back (backtrack) to restore original state.

### Flow
```
Input: "abc", index=0
  swap(0,0)="abc" → recurse index=1
    swap(1,1)="abc" → recurse index=2 → add "abc"
    swap(1,2)="acb" → recurse index=2 → add "acb"
  swap(0,1)="bac" → recurse index=1
    swap(1,1)="bac" → add "bac"
    swap(1,2)="bca" → add "bca"
  swap(0,2)="cba" → recurse index=1
    ... → add "cab", "cba"
```

### Complexity
| Time | O(n × n!) |
| Space | O(n) — recursion stack |

### Interview Answer
> "Backtracking with in-place swapping avoids creating new strings at each step. The HashSet at each level prevents duplicate permutations when input has repeated characters. Time is O(n × n!) because there are n! permutations and each takes O(n) to record."

---

## 8. Find All Anagram Substrings (Sliding Window)
**File:** `anagram/AnagramSubstrings.java`

### Logic
- Fixed-size sliding window of length = `pattern.length()`.
- Maintain two `int[26]` frequency arrays: one for pattern, one for current window.
- Initialize both arrays for the first window.
- Slide right: add new right char, remove old left char.
- Compare arrays with `Arrays.equals()` — O(26) = O(1).

### Flow
```
text="cbaebabacd", pattern="abc"
pFreq: {a=1,b=1,c=1}

Window "cba" (i=0): wFreq={a=1,b=1,c=1} == pFreq → add index 0
Window "bae" (i=1): wFreq={a=1,b=1,e=1} ≠ pFreq
Window "aeb" (i=2): wFreq={a=1,b=1,e=1} ≠ pFreq
...
Window "bac" (i=6): wFreq={a=1,b=1,c=1} == pFreq → add index 6
Result: [0, 6]
```

### Complexity
| Time | O(n) — single pass, O(1) comparison |
| Space | O(1) — two fixed int[26] arrays |

### Interview Answer
> "Sliding window with two frequency arrays. The key insight is that Arrays.equals() on int[26] is O(26) = O(1), making the overall algorithm O(n). I initialize the first window separately, then slide by adding the new right char and removing the old left char."

---
