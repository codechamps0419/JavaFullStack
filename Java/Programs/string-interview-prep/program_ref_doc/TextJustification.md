# Text Justification

## Problem
Given an array of words and `maxWidth`, format the text so that each line has exactly `maxWidth` characters and is fully left-right justified.

Rules:
- Pack as many words as possible per line.
- Distribute spaces evenly between words; extra spaces fill left gaps first.
- Last line is left-justified (single space between words, pad right).
- Single word on a line → left-justify (pad right).

---

## Solution — Greedy Line Packing ✅ Optimal

### Step-by-step logic
1. `i = 0` (current word index).
2. While `i < words.length`:
   - **Pack words**: greedily add words to line while `lineLen + 1 + nextWord.length <= maxWidth`. Advance `j`.
   - **Build line**:
     - If last line OR single word → left-justify: single space between words, pad right with spaces.
     - Else → distribute spaces:
       - `totalSpaces = maxWidth - sum of word lengths`
       - `spacePerGap = totalSpaces / numGaps`
       - `extraSpaces = totalSpaces % numGaps` (first `extraSpaces` gaps get one extra space)
   - Add line to result. `i = j`.
3. Return result.

### Dry Run
```
words = ["This","is","an","example","of","text","justification."], maxWidth=16

Line 1: pack "This","is","an" → len=4+1+2+1+2=10 ≤ 16
        try "example" → 10+1+7=18 > 16 → stop
        words=["This","is","an"], numGaps=2
        totalSpaces = 16 - (4+2+2) = 8
        spacePerGap = 8/2 = 4, extraSpaces = 8%2 = 0
        → "This    is    an" ✓ (4 spaces each gap)

Line 2: pack "example","of","text" → 7+1+2+1+4=15 ≤ 16
        try "justification." → 15+1+14=30 > 16 → stop
        words=["example","of","text"], numGaps=2
        totalSpaces = 16 - (7+2+4) = 3
        spacePerGap = 3/2 = 1, extraSpaces = 3%2 = 1
        → "example  of text" ✓ (gap0=2 spaces, gap1=1 space)

Line 3: "justification." → last line → left-justify
        → "justification.  " ✓ (pad right with 2 spaces)
```

### Complexity


| Time  | O(n × maxWidth) | Each word processed once; building each line takes O(maxWidth). |
| Space | O(maxWidth) | StringBuilder per line — result list not counted. |

---

## Edge Cases
- Single word on a line → left-justify (pad right).
- Last line → always left-justify regardless of word count.
- Words that exactly fill maxWidth → no extra spaces needed.

## Interview Tip
- LeetCode #68. Asked frequently at Google.
- Three special cases to handle clearly: last line, single word per line, normal line.
- The `extraSpaces % numGaps` distributes remainder spaces to leftmost gaps — state this explicitly.
- Follow-up: "What if words can be hyphenated?" → much more complex, mention it as an extension.
