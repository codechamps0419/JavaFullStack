# Java Interview Coding - Complete Collection

**Author:** CodeChamps0419  
**Learning series:** Dynamic Duo Learners  
**Problems:** 65 across 9 categories  

## Design and file structure

Each question includes a standalone compilable Java class (`src/`) and a matching reference note (`program_ref_docs/`). Every Java class contains the problem statement, a primary iterative/non-recursive approach, time/space complexity, concise explanatory comments, runnable `main()` and expected console output. Recursive code is included only as a clearly marked OPTIONAL second approach where pedagogically helpful.

## Recommended study order

Linked List → Stack/Queue → Trees → Heap → Graph → Backtracking/DP → Streams → Concurrency → Backend machine coding.


## Important constraints

- The self-contained examples demonstrate algorithms, not complete production services.
- Rate limiting, inventory, idempotency, caches and the LRU sample are single-JVM demonstrations. Real distributed systems need shared, durable coordination and operational controls.
- Backtracking uses iterative state exploration as requested. Output-sensitive complexity is explicitly called out.
- These examples focus on reading and explaining code; challenge yourself by rewriting each method without looking.

## Complete index

01. **ReverseLinkedList** - `linkedlist.basic` - Iterative three-pointer reversal
02. **MiddleOfLinkedList** - `linkedlist.basic` - Slow and fast pointers
03. **LinkedListCycle** - `linkedlist.basic` - Floyd slow/fast pointers
04. **CycleStartNode** - `linkedlist.basic` - Floyd detection followed by two-pointer convergence
05. **MergeTwoSortedLinkedLists** - `linkedlist.basic` - Iterative dummy-head merging
06. **RemoveNthFromEnd** - `linkedlist.medium` - Two pointers and dummy head
07. **IntersectionOfLinkedLists** - `linkedlist.medium` - Two-pointer head switching
08. **PalindromeLinkedList** - `linkedlist.medium` - Fast/slow pointers + in-place reversal of second half
09. **AddTwoNumbersLinkedList** - `linkedlist.medium` - Iterative addition with carry
10. **ReverseNodesInKGroups** - `linkedlist.advanced` - Iterative group reversal using boundaries
11. **StackUsingArray** - `stackqueue.basic` - Array-backed bounded stack
12. **QueueUsingTwoStacks** - `stackqueue.basic` - Two stacks with amortized transfers
13. **ValidParentheses** - `stackqueue.basic` - Iterative stack matching
14. **NextGreaterElement** - `stackqueue.basic` - Monotonic decreasing stack of indices
15. **MinStack** - `stackqueue.medium` - Stack of values paired with running minimum
16. **DailyTemperatures** - `stackqueue.medium` - Monotonic stack of unresolved day indices
17. **EvaluateReversePolishNotation** - `stackqueue.medium` - Iterative operand stack
18. **CircularQueue** - `stackqueue.medium` - Modular front/rear indexing
19. **SlidingWindowMaximum** - `stackqueue.advanced` - Monotonic deque of indices
20. **LargestRectangleHistogram** - `stackqueue.advanced` - Monotonic increasing stack with sentinel
21. **TreeTraversals** - `trees.basic` - Iterative stack traversals
22. **MaximumDepthBinaryTree** - `trees.basic` - Iterative breadth-first search
23. **LevelOrderTraversal** - `trees.basic` - Queue-based BFS
24. **DiameterBinaryTree** - `trees.medium` - Iterative postorder + subtree heights
25. **IdenticalBinaryTrees** - `trees.basic` - Iterative queue of node pairs
26. **ValidateBinarySearchTree** - `trees.medium` - Iterative inorder traversal
27. **LowestCommonAncestor** - `trees.medium` - Iterative parent map + ancestor set
28. **InvertBinaryTree** - `trees.basic` - Iterative breadth-first traversal
29. **KthSmallestBST** - `trees.medium` - Iterative inorder with counter
30. **MaximumPathSumBinaryTree** - `trees.advanced` - Iterative postorder dynamic programming
31. **KthLargestArray** - `heap` - Min heap of size k
32. **TopKFrequentElements** - `heap` - Frequency HashMap + min heap
33. **KClosestPoints** - `heap` - Max heap of size k on squared distance
34. **MergeKSortedLists** - `heap` - Min heap of current list heads
35. **MedianFromDataStream** - `heap` - Max heap for lower half and min heap for upper half
36. **NumberOfIslands** - `graphs` - Iterative BFS flood fill
37. **RottingOranges** - `graphs` - Multi-source breadth-first search
38. **CloneGraph** - `graphs` - Iterative BFS with identity mapping
39. **UndirectedGraphCycle** - `graphs` - Iterative BFS with parent tracking
40. **CourseSchedule** - `graphs` - Kahn topological sort with indegrees
41. **GenerateAllSubsets** - `backtrackingdp` - Iterative subset doubling
42. **GenerateAllPermutations** - `backtrackingdp` - Iterative insertion into every position
43. **CombinationSum** - `backtrackingdp` - Iterative explicit DFS state stack
44. **PalindromePartitioning** - `backtrackingdp` - Iterative explicit DFS with precomputed palindrome table
45. **CoinChange** - `backtrackingdp` - Bottom-up dynamic programming
46. **HouseRobber** - `backtrackingdp` - Constant-space bottom-up DP
47. **SecondHighestEmployeeSalary** - `streams` - Stream distinct + descending sort + skip
48. **GroupEmployeesByDepartment** - `streams` - Collectors.groupingBy with downstream mapping
49. **HighestPaidPerDepartment** - `streams` - groupingBy + maxBy collector
50. **AverageSalaryByDepartment** - `streams` - Collectors.averagingInt downstream
51. **ListToMapDuplicateKeys** - `streams` - Collectors.toMap with merge function
52. **SortEmployeesBySalaryAndName** - `streams` - Comparator chaining + stream sorted
53. **FlattenNestedLists** - `streams` - Stream.flatMap
54. **OddEvenTwoThreads** - `concurrency` - wait/notify coordination with shared monitor
55. **ProducerConsumerBlockingQueue** - `concurrency` - ArrayBlockingQueue with poison pill
56. **ThreadSafeCounter** - `concurrency` - AtomicInteger compare-and-swap operations
57. **CombineCompletableFutures** - `concurrency` - CompletableFuture.supplyAsync + thenCombine
58. **AvoidDeadlockLockOrdering** - `concurrency` - Global consistent lock acquisition order
59. **ThreadSafeSingleton** - `concurrency` - Initialization-on-demand holder idiom
60. **LruCache** - `backend` - LinkedHashMap access-order eviction
61. **TokenBucketRateLimiter** - `backend` - Synchronized token bucket using monotonic clock
62. **IdempotencyKeyManager** - `backend` - ConcurrentHashMap computeIfAbsent
63. **ThreadSafeInventory** - `backend` - AtomicInteger CAS update
64. **KeyValueStoreWithTtl** - `backend` - ConcurrentHashMap + expiration timestamp
65. **BoundedTaskExecutor** - `backend` - ThreadPoolExecutor with AbortPolicy

## Suggested 3-step practice

1. Read the reference note and explain the core idea out loud.
2. Reimplement the primary approach from memory and dry-run at least two cases.
3. Run the code, inspect failures/edge cases and answer the follow-up question.

© Dynamic Duo Learners
