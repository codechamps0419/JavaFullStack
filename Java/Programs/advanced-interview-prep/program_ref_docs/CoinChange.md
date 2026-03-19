# 45. CoinChange

**Author:** CodeChamps0419  
**Category:** `backtrackingdp`  
**Difficulty / focus:** Coding interview, Java  
**Primary approach:** Bottom-up dynamic programming  

## 1. Problem statement

Minimum number of unlimited coins to reach a target or -1 if impossible.

## 2. Why this question matters

This problem tests your ability to select an appropriate data structure, maintain correct invariants, handle boundary cases, and articulate trade-offs during a Java backend coding interview. Start by clarifying assumptions with the interviewer and identifying the constraints.

## 3. Primary approach — iterative / non-recursive

dp[sum] represents the minimum coins for that subtotal.

**Time complexity:** `O(amount * number of coins)`  
**Auxiliary space complexity:** `O(amount)`  

The executable implementation is in `src/CoinChange.java`. The approach is annotated immediately above its implementation. Walk through the code in the debugger, observing how variables change after every iteration.

### Representative code

```java
static int minCoins(int[] coins, int amount) {
    if(amount<0) return -1;
    int[] dp=new int[amount+1];
    Arrays.fill(dp,amount+1);
    dp[0]=0;
    for(int sum=1;sum<=amount;sum++) {
        for(int coin:coins) {
            if(coin<=0) throw new IllegalArgumentException("Positive coins only");
            if(coin<=sum) dp[sum]=Math.min(dp[sum],dp[sum-coin]+1);
        }
    }
    return dp[amount]>amount?-1:dp[amount];
}
```

## 4. Input and expected output

```text
Minimum coins: 3
```

Run the `main()` method to reproduce this output. The class creates its own example inputs and does not require Spring Boot or other dependencies.

## 5. Dry-run guidance

1. Write the input on paper or in your IDE watch window.
2. Identify the initial state of the data structure(s) used by the primary approach.
3. Execute one loop iteration at a time and record changed variables.
4. Repeat until the loop termination condition is reached.
5. Compare the return value against the expected output above.

**State to track:** dp[sum] represents the minimum coins for that subtotal.

## 6. Edge cases and common beginner pitfalls

Unreachable sums stay at the sentinel.

Also distinguish an algorithm's auxiliary space from its unavoidable output storage. Never assume an input constraint without stating it.

## 7. Senior interview-ready explanation

> I would begin by confirming the input constraints and required behavior for edge cases. For the main solution, I would use **Bottom-up dynamic programming**. dp[sum] represents the minimum coins for that subtotal. The running time is **O(amount * number of coins)**, and the extra-space requirement is **O(amount)**. I would test the normal example and boundary cases, then explain where the implementation has limitations or requires more validation in a production codebase.

## 8. Interview follow-up

**Question:** Why does a greedy choice fail for some coin systems?

**Discussion direction:** Discuss invariants, memory usage, correctness proof, performance bottlenecks, and whether mutation or concurrency changes the solution.

## 9. Additional approach

No additional recursive implementation is included. The Java file prioritizes the iterative/non-recursive solution.

## 10. Run command

From the project root:

```bash
javac -d build backtrackingdp/src/CoinChange.java
java -cp build backtrackingdp.CoinChange
```

© Dynamic Duo Learners — Program author: CodeChamps0419
