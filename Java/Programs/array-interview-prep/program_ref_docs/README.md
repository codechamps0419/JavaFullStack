# Array Interview Prep — Java

30 most-asked array interview questions with optimized solutions and step-by-step documentation.

---

## Project Structure

```
array-interview-prep/
├── docs/                          ← Step-by-step explanation for each question
└── src/arrays/
    ├── basic/                     ← LargestAndSmallest – MissingNumber
    ├── searching/                 ← BinarySearch – KthLargestSmallest
    ├── subarray/                  ← MaxSubarraySum – CountSubarraysWithXOR
    ├── twopointer/                ← TwoSum – LongestSubarrayKDistinct
    ├── matrix/                    ← RotateMatrix90 – SetMatrixZeroes
    └── advanced/                  ← TrappingRainWater – JumpGame
```

---

## All 30 Questions

### Basic
| # | File | Topic | Time | Space |
|--------------
| 01 | LargestAndSmallest | Find largest & smallest | O(n) | O(1) |
| 02 | ReverseArray | Reverse array in-place | O(n) | O(1) |
| 03 | SecondLargest | Second largest element | O(n) | O(1) |
| 04 | RemoveDuplicatesSorted | Remove duplicates (sorted) | O(n) | O(1) |
| 05 | MissingNumber | Missing number 1 to N | O(n) | O(1) |

### Searching & Sorting
| # | File | Topic | Time | Space |
|--------------
| 06 | BinarySearch | Binary search | O(log n) | O(1) |
| 07 | SortZeroOneTwo | Dutch National Flag | O(n) | O(1) |
| 08 | FirstLastPosition | First & last position | O(log n) | O(1) |
| 09 | MergeTwoSortedArrays | Merge sorted arrays (no space) | O((m+n)log(m+n)) | O(1) |
| 10 | KthLargestSmallest | Kth largest/smallest | O(n log k) | O(k) |

### Subarray Problems
| # | File | Topic | Time | Space |
|--------------
| 11 | MaxSubarraySum | Kadane's Algorithm | O(n) | O(1) |
| 12 | SubarrayWithGivenSum | Subarray with given sum | O(n) | O(1) |
| 13 | LongestSubarraySumK | Longest subarray sum = k | O(n) | O(n) |
| 14 | MaxProductSubarray | Max product subarray | O(n) | O(1) |
| 15 | CountSubarraysWithXOR | Count subarrays with XOR = k | O(n) | O(n) |

### Two Pointer / Sliding Window
| # | File | Topic | Time | Space |
|--------------
| 16 | TwoSum | Two sum | O(n) | O(n) |
| 17 | ThreeSum | Three sum (all triplets) | O(n²) | O(1) |
| 18 | ContainerWithMostWater | Container with most water | O(n) | O(1) |
| 19 | MinSizeSubarraySum | Min size subarray sum >= k | O(n) | O(1) |
| 20 | LongestSubarrayKDistinct | Longest subarray k distinct | O(n) | O(k) |

### Matrix / 2D Array
| # | File | Topic | Time | Space |
|--------------
| 21 | RotateMatrix90 | Rotate matrix 90° | O(n²) | O(1) |
| 22 | SpiralOrderTraversal | Spiral order traversal | O(m*n) | O(1) |
| 23 | SearchSortedMatrix | Search sorted matrix | O(m+n) | O(1) |
| 24 | SetMatrixZeroes | Set matrix zeroes | O(m*n) | O(1) |

### Advanced
| # | File | Topic | Time | Space |
|--------------
| 25 | TrappingRainWater | Trapping rain water | O(n) | O(1) |
| 26 | NextPermutation | Next permutation | O(n) | O(1) |
| 27 | FindAllDuplicates | Find all duplicates | O(n) | O(1) |
| 28 | MergeOverlappingIntervals | Merge intervals | O(n log n) | O(n) |
| 29 | LongestConsecutiveSequence | Longest consecutive sequence | O(n) | O(n) |
| 30 | JumpGame | Jump game | O(n) | O(1) |

---

## Key Patterns to Master

| Pattern | Questions |
|--------------
| Two Pointer | ReverseArray, RemoveDuplicatesSorted, FirstLastPosition, TwoSum, ThreeSum, ContainerWithMostWater, TrappingRainWater |
| Sliding Window | SubarrayWithGivenSum, MinSizeSubarraySum, LongestSubarrayKDistinct |
| Prefix Sum / XOR + HashMap | LongestSubarraySumK, CountSubarraysWithXOR |
| Kadane's (DP) | MaxSubarraySum, MaxProductSubarray |
| Binary Search | BinarySearch, FirstLastPosition, MergeTwoSortedArrays |
| Greedy | ContainerWithMostWater, MergeOverlappingIntervals, LongestConsecutiveSequence, JumpGame |
| Index as Hash (in-place marking) | FindAllDuplicates |
| Matrix Manipulation | RotateMatrix90, SpiralOrderTraversal, SearchSortedMatrix, SetMatrixZeroes |

---

## How to Run Any Question

```bash
cd "H:\STS WS\array-interview-prep"
javac -d bin src/arrays/basic/LargestAndSmallest.java
java -cp bin arrays.basic.LargestAndSmallest
```

Replace the class path with any question you want to run.


