# Rotate Array by K Positions

## Problem
Given an array and a number k, rotate the array right (or left) by k positions.

---

## Solution 1 — Reversal Algorithm (Right Rotate) ✅ Optimal

### Why Reversal?
Reversing sub-sections of the array achieves rotation in O(n) time and O(1) space — no extra array needed.

### Step-by-step logic (Right Rotate by k)
1. `k = k % n` → handle cases where k > n.
2. Reverse entire array `[0, n-1]`.
3. Reverse first `k` elements `[0, k-1]`.
4. Reverse remaining `n-k` elements `[k, n-1]`.

### Dry Run (Right Rotate by 3)
```
arr = [1, 2, 3, 4, 5, 6, 7], k=3, n=7

Step 1: k = 3 % 7 = 3
Step 2: reverse [0,6] → [7, 6, 5, 4, 3, 2, 1]
Step 3: reverse [0,2] → [5, 6, 7, 4, 3, 2, 1]
Step 4: reverse [3,6] → [5, 6, 7, 1, 2, 3, 4]

Result: [5, 6, 7, 1, 2, 3, 4] ✓
```

### Complexity


| Time  | O(n) | Three reverse passes — each O(n), total O(3n) = O(n). |
| Space | O(1) | In-place reversal using only a temp variable. |

---

## Solution 2 — Reversal Algorithm (Left Rotate) ✅ Optimal

### Step-by-step logic (Left Rotate by k)
1. `k = k % n` → handle cases where k > n.
2. Reverse first `k` elements `[0, k-1]`.
3. Reverse remaining `n-k` elements `[k, n-1]`.
4. Reverse entire array `[0, n-1]`.

### Dry Run (Left Rotate by 3)
```
arr = [1, 2, 3, 4, 5, 6, 7], k=3, n=7

Step 1: k = 3 % 7 = 3
Step 2: reverse [0,2] → [3, 2, 1, 4, 5, 6, 7]
Step 3: reverse [3,6] → [3, 2, 1, 7, 6, 5, 4]
Step 4: reverse [0,6] → [4, 5, 6, 7, 1, 2, 3]

Result: [4, 5, 6, 7, 1, 2, 3] ✓
```

### Complexity


| Time  | O(n) | Three reverse passes — each O(n), total O(3n) = O(n). |
| Space | O(1) | In-place reversal using only a temp variable. |

---

## Solution 3 — Stream (Right Rotate, new array)

Uses `Arrays.stream()` with range slicing to concatenate last k elements followed by first n-k elements.

```java
int n = arr.length;
k = k % n;
return IntStream.concat(
    Arrays.stream(arr, n - k, n),   // last k elements
    Arrays.stream(arr, 0, n - k)    // first n-k elements
).toArray();
```

### Step-by-step logic
1. `k = k % n` → normalise k.
2. `Arrays.stream(arr, n-k, n)` → stream last k elements (these move to front).
3. `Arrays.stream(arr, 0, n-k)` → stream first n-k elements (these shift right).
4. `IntStream.concat()` → join both streams into a new array.

### Dry Run
```
arr = [1, 2, 3, 4, 5, 6, 7], k=3, n=7

Arrays.stream(arr, 4, 7) → [5, 6, 7]   (last k=3 elements)
Arrays.stream(arr, 0, 4) → [1, 2, 3, 4] (first n-k=4 elements)
concat                   → [5, 6, 7, 1, 2, 3, 4] ✓
```

### Complexity


| Time  | O(n) | Two stream slices + concat — each O(n). |
| Space | O(n) | New result array of size n allocated. |

---

## Comparison

| Solution | Direction | In-Place | Space |

| 1 — Reversal | Right Rotate | ✅ Yes | O(1) |
| 2 — Reversal | Left Rotate  | ✅ Yes | O(1) |
| 3 — Stream   | Right Rotate | ❌ No  | O(n) |

---

## Edge Cases
- k = 0 → no rotation, array unchanged.
- k = n → full rotation, array unchanged.
- k > n → `k % n` handles it correctly.
- Single element → no change.

## Interview Tip
- Always apply `k = k % n` first — interviewers check if you handle k > n.
- Left rotate by k = Right rotate by (n - k) — mention this relationship.
- The reversal trick is the expected O(1) space answer. Avoid the extra array approach.


