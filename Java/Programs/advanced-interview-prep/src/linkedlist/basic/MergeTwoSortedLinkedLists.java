package linkedlist.basic;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Merge two ascending linked lists without allocating new data nodes.
 *
 */
public class MergeTwoSortedLinkedLists {
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
	 * APPROACH 1 (PRIMARY): Iterative dummy-head merging Complexity ~ TIME : O(n +
	 * m), SPACE : O(1)
	 */
	static Node merge(Node a, Node b) {
		Node dummy = new Node(0), tail = dummy;
		while (a != null && b != null) {
			if (a.value <= b.value) {
				tail.next = a;
				a = a.next;
			} else {
				tail.next = b;
				b = b.next;
			}
			tail = tail.next;
		}
		tail.next = a != null ? a : b;
		return dummy.next;
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		System.out.println(show(merge(list(1, 3, 5), list(2, 4, 6))));
	}
}

/*
 * Output : 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null
 */
