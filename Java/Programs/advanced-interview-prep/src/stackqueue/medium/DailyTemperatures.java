package stackqueue.medium;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Return the days until a warmer temperature for each day.
 *
 */
public class DailyTemperatures {

	/*
	 * APPROACH 1 (PRIMARY): Monotonic stack of unresolved day indices Complexity ~
	 * TIME : O(n), SPACE : O(n)
	 */
	static int[] warmerDays(int[] t) {
		int[] result = new int[t.length];
		Deque<Integer> stack = new ArrayDeque<>();
		for (int day = 0; day < t.length; day++) {
			while (!stack.isEmpty() && t[day] > t[stack.peek()]) {
				int previousDay = stack.pop();
				result[previousDay] = day - previousDay;
			}
			stack.push(day);
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		System.out.println(Arrays.toString(warmerDays(new int[] { 73, 74, 75, 71, 69, 72, 76, 73 })));
	}
}

/*
 * Output : [1, 1, 4, 2, 1, 1, 0, 0]
 */
