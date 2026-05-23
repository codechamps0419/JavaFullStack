# Java String Interview — Fundamentals

---

## 1. Reverse a String
**File:** `fundamentals/ReverseString.java`

### Logic
- Convert string to char array.
- Use two pointers: `left=0`, `right=n-1`.
- Swap `chars[left]` and `chars[right]`, move both inward.
- Stop when `left >= right`.

### Flow
```
Input:  "hello"
Array:  [h, e, l, l, o]
Step 1: swap(h,o) → [o, e, l, l, h]
Step 2: swap(e,l) → [o, l, l, e, h]
Step 3: left==right, stop
Output: "olleh"
```

### Complexity
| Time | O(n) |
| Space | O(n) — char array copy |

### Interview Answer
> "I use the two-pointer technique on a char array. Left pointer starts at 0, right at n-1. I swap and move both inward until they meet. This is O(n) time and avoids creating intermediate strings like StringBuilder.reverse() does internally."

---

## 2. Check if a String is a Palindrome
**File:** `fundamentals/PalindromeCheck.java`

### Logic
- Skip non-alphanumeric characters using `Character.isLetterOrDigit()`.
- Compare characters from both ends after converting to lowercase.
- If any mismatch → not a palindrome.

### Flow
```
Input:  "A man a plan a canal Panama"
Clean:  "amanaplanacanalpanama"
left=0 (a), right=19 (a) → match
left=1 (m), right=18 (m) → match
... all match → true
```

### Complexity
| Time | O(n) |
| Space | O(1) — no extra structure |

### Interview Answer
> "I use two pointers and skip non-alphanumeric characters in-place. No need to create a cleaned string. O(1) space because I only use two integer pointers."

---

## 3. Count Occurrences of Each Character
**File:** `fundamentals/CharacterFrequency.java`

### Logic
- Use `LinkedHashMap<Character, Integer>` to preserve insertion order.
- For each character, use `merge(c, 1, Integer::sum)` to increment count.
- Stream alternative uses `Collectors.groupingBy` with `counting()`.

### Flow
```
Input: "hello"
h → {h=1}
e → {h=1, e=1}
l → {h=1, e=1, l=1}
l → {h=1, e=1, l=2}
o → {h=1, e=1, l=2, o=1}
Output: {h=1, e=1, l=2, o=1}
```

### Complexity
| Time | O(n) |
| Space | O(k) — k = unique characters |

### Interview Answer
> "LinkedHashMap preserves insertion order so output matches character appearance order. merge() is cleaner than getOrDefault+put. For streams, groupingBy+counting() is the idiomatic Java 8+ approach."

---

## 4. Remove Duplicate Characters (Preserve Order)
**File:** `fundamentals/RemoveDuplicates.java`

### Logic
- `LinkedHashSet` maintains insertion order and automatically ignores duplicates.
- Iterate string, add each char to set.
- Build result from set entries.
- Stream alternative uses `chars().distinct()`.

### Flow
```
Input: "programming"
p → {p}
r → {p,r}
o → {p,r,o}
g → {p,r,o,g}
r → already exists, skip
a → {p,r,o,g,a}
m → {p,r,o,g,a,m}
m → skip
i → {p,r,o,g,a,m,i}
n → {p,r,o,g,a,m,i,n}
g → skip
Output: "progamin"
```

### Complexity
| Time | O(n) |
| Space | O(k) — k = unique characters |

### Interview Answer
> "LinkedHashSet is the perfect data structure here — O(1) add with duplicate rejection and insertion-order preservation. The stream version with distinct() is more readable but does the same thing internally."

---

## 5. First Non-Repeating Character
**File:** `fundamentals/FirstNonRepeatingChar.java`

### Logic
- Pass 1: Build `LinkedHashMap<Character, Integer>` with frequencies.
- Pass 2: Stream over entries, filter where value == 1, return first key.
- LinkedHashMap guarantees we check characters in their original order.

### Flow
```
Input: "aabbcde"
Freq map (in order): {a=2, b=2, c=1, d=1, e=1}
Filter value==1: c is first
Output: 'c'
```

### Complexity
| Time | O(n) |
| Space | O(k) — k = unique characters |

### Interview Answer
> "Two passes: first build frequency map, then find first with count 1. LinkedHashMap is critical — a regular HashMap would not preserve insertion order, so we might return the wrong character. Return a sentinel value like '_' if no unique char exists."

---
