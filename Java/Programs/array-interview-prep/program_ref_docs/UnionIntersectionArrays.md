# Union and Intersection of Two Sorted Arrays

## Problem
Given two sorted arrays, find their union (all distinct elements from both) and intersection (elements common to both).

---

## Solution 1 — Two Pointer ✅ Optimal

### Why Two Pointer?
Since both arrays are sorted, we can merge them in a single pass using two pointers — no HashSet needed, O(1) space.

### Step-by-step logic — Union
1. `i = 0`, `j = 0`, `result = []`.
2. While `i < m` and `j < n`:
   - Skip duplicates within each array.
   - If `arr1[i] < arr2[j]` → add `arr1[i]`, `i++`.
   - If `arr1[i] > arr2[j]` → add `arr2[j]`, `j++`.
   - If equal → add once, `i++`, `j++`.
3. Append remaining elements from either array (skip duplicates).

### Dry Run — Union
```
arr1 = [1, 2, 3, 4, 5], arr2 = [3, 4, 5, 6, 7]
i=0, j=0, result=[]

arr1[0]=1 < arr2[0]=3 → add 1, i=1  → result=[1]
arr1[1]=2 < arr2[0]=3 → add 2, i=2  → result=[1,2]
arr1[2]=3 == arr2[0]=3 → add 3, i=3, j=1 → result=[1,2,3]
arr1[3]=4 == arr2[1]=4 → add 4, i=4, j=2 → result=[1,2,3,4]
arr1[4]=5 == arr2[2]=5 → add 5, i=5, j=3 → result=[1,2,3,4,5]
i exhausted → append arr2[3]=6, arr2[4]=7

Result: [1, 2, 3, 4, 5, 6, 7] ✓
```

### Step-by-step logic — Intersection
1. `i = 0`, `j = 0`, `result = []`.
2. While `i < m` and `j < n`:
   - If `arr1[i] < arr2[j]` → `i++`.
   - If `arr1[i] > arr2[j]` → `j++`.
   - If equal → add to result (skip if duplicate of last added), `i++`, `j++`.

### Dry Run — Intersection
```
arr1 = [1, 2, 3, 4, 5], arr2 = [3, 4, 5, 6, 7]
i=0, j=0, result=[]

arr1[0]=1 < arr2[0]=3 → i=1
arr1[1]=2 < arr2[0]=3 → i=2
arr1[2]=3 == arr2[0]=3 → add 3, i=3, j=1 → result=[3]
arr1[3]=4 == arr2[1]=4 → add 4, i=4, j=2 → result=[3,4]
arr1[4]=5 == arr2[2]=5 → add 5, i=5, j=3 → result=[3,4,5]
i exhausted → stop

Result: [3, 4, 5] ✓
```

### Complexity


| Time  | O(m+n) | Single pass through both arrays — each element visited at most once. |
| Space | O(1) | Only two pointer variables — output list not counted. |

---

## Solution 2 — Stream

### Union using Stream
```java
return IntStream.concat(Arrays.stream(arr1), Arrays.stream(arr2))
        .distinct().sorted().boxed().collect(Collectors.toList());
```

### Intersection using Stream
```java
Set<Integer> set = Arrays.stream(arr1).boxed()
        .collect(Collectors.toCollection(LinkedHashSet::new));
return Arrays.stream(arr2).boxed()
        .filter(set::remove)
        .collect(Collectors.toList());
```

### Step-by-step logic — Union Stream
1. `IntStream.concat()` → merge both arrays into one stream.
2. `.distinct()` → remove duplicates.
3. `.sorted()` → sort the result.
4. `.boxed().collect()` → collect into a list.

### Step-by-step logic — Intersection Stream
1. Collect `arr1` into a `LinkedHashSet` for O(1) lookup.
2. Stream `arr2`, filter elements present in the set using `set::remove` — removes on match to avoid duplicates.
3. Collect matching elements into a list.

### Dry Run — Union Stream
```
arr1=[1,2,3,4,5], arr2=[3,4,5,6,7]

concat  → [1,2,3,4,5,3,4,5,6,7]
distinct → [1,2,3,4,5,6,7]
sorted  → [1,2,3,4,5,6,7] ✓
```

### Dry Run — Intersection Stream
```
arr1=[1,2,3,4,5], arr2=[3,4,5,6,7]

set = {1,2,3,4,5}
stream arr2, filter(set::remove):
  3 → in set, remove → keep  → result=[3]
  4 → in set, remove → keep  → result=[3,4]
  5 → in set, remove → keep  → result=[3,4,5]
  6 → not in set → skip
  7 → not in set → skip

Result: [3, 4, 5] ✓
```

### Complexity


| Time  | O((m+n) log(m+n)) | Union: concat O(m+n) + distinct O(m+n) + sorted O((m+n)log(m+n)). Intersection: O(m+n). |
| Space | O(m+n) | Intermediate collections and result list allocated. |

---

## Comparison

| Solution | Time | Space | Notes |

| 1 — Two Pointer | O(m+n) | O(1) | Optimal — requires sorted input |
| 2 — Stream | O((m+n)log(m+n)) | O(m+n) | Simpler code, works on unsorted too |

---

## Edge Cases
- One array empty → union = other array, intersection = empty.
- No common elements → intersection = empty.
- Identical arrays → union = either array, intersection = either array.
- Arrays with duplicates → both solutions handle correctly.

## Interview Tip
- Always clarify: "Are arrays sorted?" — Two Pointer only works on sorted arrays.
- For unsorted arrays → use HashSet: add arr1 to set, iterate arr2 and check membership.
- Union with HashSet: add all elements from both arrays to a TreeSet (sorted + distinct).


