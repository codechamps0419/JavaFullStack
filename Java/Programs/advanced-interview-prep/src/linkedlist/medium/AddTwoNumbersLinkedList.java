package linkedlist.medium;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Add numbers stored in reverse-digit order in two linked lists.
 *
 */
public class AddTwoNumbersLinkedList {
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
	 * APPROACH 1 (PRIMARY): Iterative addition with carry Complexity ~ TIME :
	 * O(max(n,m)), SPACE : O(max(n,m)) output nodes
	 */
	static Node addNumbers(Node a, Node b) {
		Node dummy = new Node(0), tail = dummy;
		int carry = 0;
		while (a != null || b != null || carry != 0) {
			int sum = carry;
			if (a != null) {
				sum += a.value;
				a = a.next;
			}
			if (b != null) {
				sum += b.value;
				b = b.next;
			}
			tail.next = new Node(sum % 10);
			tail = tail.next;
			carry = sum / 10;
		}
		return dummy.next;
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		System.out.println(show(addNumbers(list(2, 4, 3), list(5, 6, 4))));
	}
}

/*
 * Output : 7 -> 0 -> 8 -> null
 */
