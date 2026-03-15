# Maximum Product Subarray

## Problem
Find the contiguous subarray with the largest product. Array can have negatives and zeros.

## Approach — Track Max and Min Product

### Why track minimum too?
A negative number can flip the minimum product into the maximum.
Example: `[-2, 3, -4]` → min at index 1 is -6, but -6 * -4 = 24 (new max).

### Step-by-step logic
1. Initialize `maxProd = arr[0]`, `minProd = arr[0]`, `result = arr[0]`.
2. For each element from index 1:
   - If `arr[i] < 0` → swap `maxProd` and `minProd` (negative flips them).
   - `maxProd = max(arr[i], maxProd * arr[i])` — extend or restart.
   - `minProd = min(arr[i], minProd * arr[i])` — track most negative.
   - Update `result = max(result, maxProd)`.

### Dry Run
```
arr = [2, 3, -2, 4]
Start: max=2, min=2, res=2
i=1(3):  max=max(3,6)=6,  min=min(3,6)=3,  res=6
i=2(-2): swap→max=3,min=6; max=max(-2,-6)=-2, min=min(-2,-12)=-12, res=6
i=3(4):  max=max(4,-8)=4, min=min(4,-48)=-48, res=6
Result: 6 → [2,3]
```

## Complexity


| Time  | O(n) |
| Space | O(1) |

## Edge Cases
- Zero in array → resets both max and min to current element (restart).
- All negatives, even count → product of all is positive (max).
- All negatives, odd count → exclude one negative.

## Interview Tip
The swap trick when `arr[i] < 0` is the key insight. Without it, you'd need to check all 3 candidates: `arr[i]`, `maxProd*arr[i]`, `minProd*arr[i]` — which also works but is less elegant.


