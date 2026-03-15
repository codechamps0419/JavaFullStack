# Left Rotate Array by One Position

## Problem
Given an array, rotate it left by one position — the first element moves to the end.

---

## Solution 1 — Single Pass (Shift Left) ✅ Optimal

### Why Single Pass?
Store the first element, shift all others one position left, place stored element at the end. One pass, no extra array.

### Step-by-step logic
1. Store `first = arr[0]`.
2. Loop from index `0` to `arr.length - 2`:
   - `arr[i] = arr[i + 1]` → shift each element one position left.
3. `arr[arr.length - 1] = first` → place stored first element at the end.

### Dry Run
```
arr = [1, 2, 3, 4, 5]
first = 1

i=0: arr[0] = arr[1] = 2 → arr=[2,2,3,4,5]
i=1: arr[1] = arr[2] = 3 → arr=[2,3,3,4,5]
i=2: arr[2] = arr[3] = 4 → arr=[2,3,4,4,5]
i=3: arr[3] = arr[4] = 5 → arr=[2,3,4,5,5]

arr[4] = first = 1        → arr=[2,3,4,5,1] ✓
```

### Complexity


| Time  | O(n) | Single pass of n-1 shifts + one assignment = O(n). |
| Space | O(1) | Only one variable `first` used — in-place modification. |

---

## Solution 2 — Stream

Uses `IntStream.concat()` to join elements from index 1 onwards with the first element at the end.

```java
return IntStream.concat(
        Arrays.stream(arr, 1, arr.length),  // elements from index 1 to end
        Arrays.stream(arr, 0, 1)            // first element moved to end
).toArray();
```

### Step-by-step logic
1. `Arrays.stream(arr, 1, arr.length)` → stream elements from index 1 to end (shifted left).
2. `Arrays.stream(arr, 0, 1)` → stream only the first element (to be placed at end).
3. `IntStream.concat()` → join both streams into a new array.

### Dry Run
```
arr = [1, 2, 3, 4, 5]

Arrays.stream(arr, 1, 5) → [2, 3, 4, 5]
Arrays.stream(arr, 0, 1) → [1]
concat                   → [2, 3, 4, 5, 1] ✓
```

### Complexity


| Time  | O(n) | Two stream slices + concat — each O(n). |
| Space | O(n) | New result array of size n allocated. |

---

## Comparison

| Solution | In-Place | Space |

| 1 — Single Pass | ✅ Yes | O(1) |
| 2 — Stream | ❌ No (new array) | O(n) |

---

## Edge Cases
- Single element → no change.
- Two elements → swap positions.
- Already rotated → works correctly.

## Interview Tip
- Left rotate by one is the building block for "Left rotate by k" — call it k times (O(n×k)) or use the reversal algorithm for O(n).
- Right rotate by one: store last element, shift all right, place at front — mirror of this solution.


