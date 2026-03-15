# Valid Parentheses

## Problem
Given a string containing only `(`, `)`, `{`, `}`, `[`, `]`, determine if the input string is valid.
A string is valid if every opening bracket is closed by the same type in correct order.

---

## Solution 1 — Stack ✅ Optimal (handles all bracket types)

### Why Stack?
LIFO property of stack perfectly matches bracket nesting — the most recently opened bracket must be closed first.

### Step-by-step logic
1. For each character `c` in string:
   - If `c` is opening bracket `(`, `{`, `[` → push onto stack.
   - If `c` is closing bracket:
     - If stack is empty → return `false` (no matching opening).
     - Pop top of stack → if it doesn't match `c` → return `false`.
2. After loop → return `stack.isEmpty()` (all opened brackets must be closed).

### Dry Run
```
s = "{[]}"

c='{': push → stack=[{]
c='[': push → stack=[{, []
c=']': pop '[' → matches ']' ✓ → stack=[{]
c='}': pop '{' → matches '}' ✓ → stack=[]
stack.isEmpty() = true → return true ✓

s = "([)]"

c='(': push → stack=[(]
c='[': push → stack=[(, []
c=')': pop '[' → '[' != '(' → return false ✓
```

### Complexity


| Time  | O(n) | Single pass — each character pushed/popped at most once. |
| Space | O(n) | Stack holds at most n/2 opening brackets in worst case. |

---

## Solution 2 — Counter (single bracket type only)

Works only when input has just `(` and `)`. No stack needed.

```java
int count = 0;
for (char c : s.toCharArray()) {
    if (c == '(') count++;
    else if (c == ')') count--;
    if (count < 0) return false;
}
return count == 0;
```

### Step-by-step logic
1. `count = 0`.
2. For each char: `(` → increment, `)` → decrement.
3. If `count < 0` at any point → more closing than opening → return `false`.
4. At end → return `count == 0`.

### Dry Run
```
s = "(())"

c='(': count=1
c='(': count=2
c=')': count=1
c=')': count=0
count==0 → true ✓

s = ")("

c=')': count=-1 → return false ✓
```

### Complexity


| Time  | O(n) | Single pass. |
| Space | O(1) | Only one counter variable. |

---

## Comparison

| Solution | Bracket Types | Space | Notes |
---|
| 1 — Stack | All types `(){}[]` | O(n) | General solution |
| 2 — Counter | Single type `()` only | O(1) | Optimized for simple case |

---

## Edge Cases
- Empty string → `true` (vacuously valid).
- Single bracket → `false`.
- Only opening brackets → `false` (stack not empty).
- Only closing brackets → `false` (stack empty on first pop).

## Interview Tip
- LeetCode #20. Always use Solution 1 — interviewers expect the stack approach.
- Common follow-up: "What if we also need to return which bracket is unmatched?" → store index along with char in stack.
- Another follow-up: "Minimum brackets to remove to make valid?" → count unmatched opens + unmatched closes.
