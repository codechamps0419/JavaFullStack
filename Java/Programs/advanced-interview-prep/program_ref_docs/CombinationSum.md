# 43. CombinationSum

**Author:** CodeChamps0419  
**Category:** `backtrackingdp`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Iterative explicit DFS state stack  

## 1. Problem statement

Find combinations of positive candidates that sum to target; reuse allowed.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

DFS states decide to include a candidate (same index) or skip it (next index).

**Time complexity:** `Output-sensitive, exponential worst case`  
**Auxiliary space complexity:** `O(target/min + output size)`  

The executable implementation is in `src/CombinationSum.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
record State(int index, int remaining, List<Integer> chosen) { }
static List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> result=new ArrayList<>();
    Deque<State> stack=new ArrayDeque<>();
    stack.push(new State(0,target,new ArrayList<>()));
    while(!stack.isEmpty()) {
        State state=stack.pop();
        if(state.remaining()==0) { result.add(state.chosen()); continue; }
        if(state.index()==candidates.length) continue;
        int value=candidates[state.index()];
        stack.push(new State(state.index()+1,state.remaining(),state.chosen()));
        if(value<=state.remaining()) {
            List<Integer> copy=new ArrayList<>(state.chosen());
            copy.add(value);
            stack.push(new State(state.index(),state.remaining()-value,copy));
        }
    }
    return result;
}
```

## 4. Input and expected output

```text
Combinations: [[2, 2, 3], [7]]
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** DFS states decide to include a candidate (same index) or skip it (next index).

## 6. Edge cases and common beginner pitfalls

Candidates must be distinct positive integers; zero/negative causes nontermination.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Iterative explicit DFS state stack**. DFS states decide to include a candidate (same index) or skip it (next index). The running time is **Output-sensitive, exponential worst case**, and the extra-space requirement is **O(target/min + output size)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** How to avoid exponential copying of partial choices?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build backtrackingdp/src/CombinationSum.java
java -cp build backtrackingdp.CombinationSum
```

© Dynamic Duo Learners — Program author: CodeChamps0419
