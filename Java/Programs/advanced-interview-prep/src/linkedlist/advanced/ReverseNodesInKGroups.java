package linkedlist.advanced;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Reverse nodes in groups of k; leave a short final group unchanged.
 *
 */
public class ReverseNodesInKGroups {
	static class Node {
		int value;
		Node next;

		Node(int value) {
			this.value = value;
		}
	}

	static Node list(int... values) {
		Node dummy = new Node(0), tail = dummy;
		for (int value : values) {
			tail.next = new Node(value);
			tail = tail.next;
		}
		return dummy.next;
	}

	static String show(Node head) {
		StringJoiner result = new StringJoiner(" -> ");
		int safety = 0;
		while (head != null && safety++ < 30) {
			result.add(String.valueOf(head.value));
			head = head.next;
		}
		result.add(head == null ? "null" : "...");
		return result.toString();
	}

	/*
	 * APPROACH 1 (PRIMARY): Iterative group reversal using boundaries Complexity ~
	 * TIME : O(n), SPACE : O(1)
	 */
	static Node reverseKGroup(Node head, int k) {
		if (k <= 0)
			throw new IllegalArgumentException("k must be positive");
		Node dummy = new Node(0);
		dummy.next = head;
		Node groupPrev = dummy;
		while (true) {
			Node kth = groupPrev;
			for (int i = 0; i < k && kth != null; i++)
				kth = kth.next;
			if (kth == null)
				break;
			Node groupNext = kth.next;
			Node prev = groupNext, cur = groupPrev.next;
			while (cur != groupNext) {
				Node next = cur.next;
				cur.next = prev;
				prev = cur;
				cur = next;
			}
			Node oldHead = groupPrev.next;
			groupPrev.next = kth;
			groupPrev = oldHead;
		}
		return dummy.next;
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		System.out.println(show(reverseKGroup(list(1, 2, 3, 4, 5), 2)));
	}
}

/*
 * Output : 2 -> 1 -> 4 -> 3 -> 5 -> null
 */
