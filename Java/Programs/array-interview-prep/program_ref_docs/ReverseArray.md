# Reverse an Array

## Problem
Reverse the elements of an array.

---

## Solution 1 — Two Pointer (In-Place) ✅ Optimal

### Why two pointers?
We swap the outermost elements and move inward. Only n/2 swaps needed. No extra array required.

### Step-by-step logic
1. Set `left = 0`, `right = arr.length - 1`.
2. While `left < right`:
   - Swap `arr[left]` and `arr[right]`.
   - Increment `left`, decrement `right`.
3. Stop when pointers meet or cross.

### Dry Run
```
arr = [1, 2, 3, 4, 5]
Step 1: swap(1,5) → [5, 2, 3, 4, 1], left=1, right=3
Step 2: swap(2,4) → [5, 4, 3, 2, 1], left=2, right=2
left >= right → stop
Result: [5, 4, 3, 2, 1]
```

### Complexity


| Time  | O(n) | We iterate through n/2 elements performing a swap at each step. n/2 → O(n) as Big-O ignores constants. |
| Space | O(1) | Reversal is done in-place using only a single `temp` variable — no extra array allocated. |

---

## Solution 2 — Stream (New Array)

Uses `IntStream.range()` to map each index `i` to `arr[length - 1 - i]`, producing a new reversed array.

```java
return IntStream.range(0, arr.length)
        .map(i -> arr[arr.length - 1 - i])
        .toArray();
```

### Complexity


| Time  | O(n) | `IntStream.range()` iterates over all n indices once, applying the mapping function at each step. |
| Space | O(n) | `toArray()` allocates a brand new array of the same size n to hold the reversed result — original array is untouched. |

---

## Solution 3 — Inbuilt Method (`Collections.reverse()`)

Converts the array to a `List` view using `Arrays.asList()` and reverses it in-place using `Collections.reverse()`.

```java
Collections.reverse(Arrays.asList(arr)); // reverse method supports List
```

> Note: `Arrays.asList()` returns a fixed-size backed view of the original array, so `Collections.reverse()` modifies the original array in-place.

### Complexity


| Time  | O(n) | `Collections.reverse()` internally uses a two-pointer swap — iterates n/2 times. |
| Space | O(1) | `Arrays.asList()` creates a wrapper view, not a copy — no new array is allocated. |

---

## Comparison

| Solution | In-Place | API | Space |

| 1 — Two Pointer | ✅ Yes | None | O(1) |
| 2 — Stream | ❌ No (new array) | `IntStream.range()` | O(n) |
| 3 — Collections | ✅ Yes (via List view) | `Collections.reverse()` | O(1) |

---

## Edge Cases
- Empty array → nothing to reverse.
- Single element → already reversed.
- Even vs odd length → works for both (middle element stays in place for odd).

## Interview Tip
- Prefer Solution 1 in interviews — in-place, O(1) space, no library dependency. Interviewer wants to see the swap logic.
- Mention Solution 2 if asked for a functional/Stream-based approach, but call out the O(n) space trade-off.
- Mention Solution 3 as the quickest inbuilt option, but note it only works with `List` — `Arrays.asList()` is needed to wrap a primitive-boxed array.


