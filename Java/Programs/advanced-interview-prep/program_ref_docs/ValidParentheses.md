# 13. ValidParentheses

**Author:** CodeChamps0419  
**Category:** `stackqueue.basic`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Iterative stack matching  

## 1. Problem statement

Check whether brackets are correctly nested and balanced.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

Push openers; on each closer pop and compare matching types.

**Time complexity:** `O(n)`  
**Auxiliary space complexity:** `O(n)`  

The executable implementation is in `src/ValidParentheses.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static boolean isValid(String text) {
    Deque<Character> stack = new ArrayDeque<>();
    for (char c : text.toCharArray()) {
        if (c == '(' || c == '[' || c == '{') stack.push(c);
        else if (c == ')' || c == ']' || c == '}') {
            if (stack.isEmpty()) return false;
            char open = stack.pop();
            if ((c == ')' && open != '(') || (c == ']' && open != '[')
                    || (c == '}' && open != '{')) return false;
        }
    }
    return stack.isEmpty();
}
```

## 4. Input and expected output

```text
Valid: true
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** Push openers; on each closer pop and compare matching types.

## 6. Edge cases and common beginner pitfalls

Non-bracket characters are ignored in this implementation.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Iterative stack matching**. Push openers; on each closer pop and compare matching types. The running time is **O(n)**, and the extra-space requirement is **O(n)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** What changes if non-bracket characters must be rejected?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build stackqueue/basic/src/ValidParentheses.java
java -cp build stackqueue.basic.ValidParentheses
```

© Dynamic Duo Learners — Program author: CodeChamps0419
