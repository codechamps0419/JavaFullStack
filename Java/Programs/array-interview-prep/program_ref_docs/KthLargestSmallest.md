# Find Kth Largest and Kth Smallest Element

## Problem
Given an unsorted array, find the Kth largest and Kth smallest element efficiently.

## Approach — Heap (Priority Queue)

### Kth Largest → Min-Heap of size k
Keep only the k largest elements seen so far in a min-heap.
The top of the heap (minimum of the k largest) = Kth largest.

### Kth Smallest → Max-Heap of size k
Keep only the k smallest elements seen so far in a max-heap.
The top of the heap (maximum of the k smallest) = Kth smallest.

### Step-by-step logic (Kth Largest)
1. Create a min-heap.
2. For each element:
   - Add to heap.
   - If heap size > k → remove the smallest (poll).
3. After all elements → heap top = Kth largest.

### Dry Run (Kth Largest, k=3)
```
arr = [7, 10, 4, 3, 20, 15]
Add 7  → heap=[7]
Add 10 → heap=[7,10]
Add 4  → heap=[4,7,10]
Add 3  → heap=[3,4,7,10] size=4>3 → poll 3 → heap=[4,7,10]
Add 20 → heap=[4,7,10,20] size=4>3 → poll 4 → heap=[7,10,20]
Add 15 → heap=[7,10,15,20] size=4>3 → poll 7 → heap=[10,15,20]
Top = 10 → 3rd Largest = 10 ✓
```

## Complexity


| Time  | O(n log k) |
| Space | O(k) |

## Alternative — QuickSelect
Average O(n), worst O(n²). Good for interviews if asked for O(n) average.

## Edge Cases
- k > array length → invalid input.
- k = 1 → find max (largest) or min (smallest).
- Duplicates → heap handles naturally.

## Interview Tip
Always clarify: "Kth largest distinct element or with duplicates?"
Heap solution is safer and more predictable than QuickSelect in interviews.


