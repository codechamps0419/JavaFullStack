package linkedlist.medium;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Remove the nth node counted backward from the tail.
 *
 */
public class RemoveNthFromEnd {
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
	 * APPROACH 1 (PRIMARY): Two pointers and dummy head Complexity ~ TIME : O(n),
	 * SPACE : O(1)
	 */
	static Node removeNth(Node head, int n) {
		if (n <= 0)
			throw new IllegalArgumentException("n must be positive");
		Node dummy = new Node(0);
		dummy.next = head;
		Node fast = dummy, slow = dummy;
		for (int i = 0; i < n; i++) {
			fast = fast.next;
			if (fast == null)
				throw new IllegalArgumentException("n exceeds list length");
		}
		while (fast.next != null) {
			fast = fast.next;
			slow = slow.next;
		}
		slow.next = slow.next.next;
		return dummy.next;
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		System.out.println(show(removeNth(list(10, 20, 30, 40, 50), 2)));
	}
}

/*
 * Output : 10 -> 20 -> 30 -> 50 -> null
 */
