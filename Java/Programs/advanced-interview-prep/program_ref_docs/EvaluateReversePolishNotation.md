# 17. EvaluateReversePolishNotation

**Author:** CodeChamps0419  
**Category:** `stackqueue.medium`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Iterative operand stack  

## 1. Problem statement

Evaluate an arithmetic expression written in reverse Polish notation.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

Numbers are pushed; operators consume the top two values in operand order.

**Time complexity:** `O(n)`  
**Auxiliary space complexity:** `O(n)`  

The executable implementation is in `src/EvaluateReversePolishNotation.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static int evaluate(String[] tokens) {
    Deque<Integer> stack = new ArrayDeque<>();
    for (String token : tokens) {
        if (Set.of("+", "-", "*", "/").contains(token)) {
            int b = stack.pop(), a = stack.pop();
            int value = switch (token) {
                case "+" -> a + b;
                case "-" -> a - b;
                case "*" -> a * b;
                default -> a / b;
            };
            stack.push(value);
        } else stack.push(Integer.parseInt(token));
    }
    if (stack.size() != 1) throw new IllegalArgumentException("Malformed expression");
    return stack.pop();
}
```

## 4. Input and expected output

```text
Result: 9
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** Numbers are pushed; operators consume the top two values in operand order.

## 6. Edge cases and common beginner pitfalls

Division by zero and malformed expression need handling.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Iterative operand stack**. Numbers are pushed; operators consume the top two values in operand order. The running time is **O(n)**, and the extra-space requirement is **O(n)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** Why is a-b not b-a?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build stackqueue/medium/src/EvaluateReversePolishNotation.java
java -cp build stackqueue.medium.EvaluateReversePolishNotation
```

© Dynamic Duo Learners — Program author: CodeChamps0419
