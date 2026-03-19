package linkedlist.basic;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Reverse the links of a singly linked list and return its new head.
 *
 */
public class ReverseLinkedList {
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
	 * APPROACH 1 (PRIMARY): Iterative three-pointer reversal Complexity ~ TIME :
	 * O(n), SPACE : O(1)
	 */
	static Node reverse(Node head) {
		Node previous = null, current = head;
		while (current != null) {
			Node next = current.next; // Preserve the remaining nodes.
			current.next = previous; // Reverse this link.
			previous = current;
			current = next;
		}
		return previous;
	}

	// OPTIONAL APPROACH 2: Recursion (O(n) time, O(n) call-stack space).
	static Node reverseRecursive(Node head) {
		if (head == null || head.next == null)
			return head;
		Node newHead = reverseRecursive(head.next);
		head.next.next = head;
		head.next = null;
		return newHead;
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		Node head = list(10, 20, 30, 40, 50);
		System.out.println("Before : " + show(head));
		System.out.println("After  : " + show(reverse(head)));
	}
}

/*
 * Output : Before : 10 -> 20 -> 30 -> 40 -> 50 -> null After : 50 -> 40 -> 30
 * -> 20 -> 10 -> null
 */
