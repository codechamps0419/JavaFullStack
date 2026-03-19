package linkedlist.medium;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Find the first shared node by reference, not by equal value.
 *
 */
public class IntersectionOfLinkedLists {
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
	 * APPROACH 1 (PRIMARY): Two-pointer head switching Complexity ~ TIME : O(n +
	 * m), SPACE : O(1)
	 */
	static Node intersection(Node a, Node b) {
		Node p = a, q = b;
		while (p != q) {
			p = p == null ? b : p.next;
			q = q == null ? a : q.next;
		}
		return p;
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		Node common = list(8, 9);
		Node a = list(1, 2), b = list(3);
		a.next.next = common;
		b.next = common;
		System.out.println("Intersection: " + intersection(a, b).value);
	}
}

/*
 * Output : Intersection: 8
 */
