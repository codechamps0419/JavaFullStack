# 44. PalindromePartitioning

**Author:** CodeChamps0419  
**Category:** `backtrackingdp`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Iterative explicit DFS with precomputed palindrome table  

## 1. Problem statement

Partition a string into every sequence of palindrome substrings.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

Precompute palindromes; DFS explores every valid substring boundary.

**Time complexity:** `O(n * 2^n) output-sensitive`  
**Auxiliary space complexity:** `O(n^2 + output)`  

The executable implementation is in `src/PalindromePartitioning.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
record State(int start, List<String> parts) { }
static List<List<String>> partitions(String s) {
    int n=s.length();
    boolean[][] palindrome=new boolean[n][n];
    for(int length=1;length<=n;length++) for(int i=0;i+length<=n;i++) {
        int j=i+length-1;
        palindrome[i][j]=s.charAt(i)==s.charAt(j)
                && (length<=2 || palindrome[i+1][j-1]);
    }
    List<List<String>> result=new ArrayList<>();
    Deque<State> stack=new ArrayDeque<>();
    stack.push(new State(0,new ArrayList<>()));
    while(!stack.isEmpty()) {
        State state=stack.pop();
        if(state.start()==n) { result.add(state.parts()); continue; }
        for(int end=n-1;end>=state.start();end--) {
            if(!palindrome[state.start()][end]) continue;
            List<String> next=new ArrayList<>(state.parts());
            next.add(s.substring(state.start(),end+1));
            stack.push(new State(end+1,next));
        }
    }
    return result;
}
```

## 4. Input and expected output

```text
Partitions: [[a, a, b], [aa, b]]
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** Precompute palindromes; DFS explores every valid substring boundary.

## 6. Edge cases and common beginner pitfalls

For empty string, returns one empty partition.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Iterative explicit DFS with precomputed palindrome table**. Precompute palindromes; DFS explores every valid substring boundary. The running time is **O(n * 2^n) output-sensitive**, and the extra-space requirement is **O(n^2 + output)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** How does precomputation change repeated palindrome checks?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build backtrackingdp/src/PalindromePartitioning.java
java -cp build backtrackingdp.PalindromePartitioning
```

© Dynamic Duo Learners — Program author: CodeChamps0419
