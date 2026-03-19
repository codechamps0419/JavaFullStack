package linkedlist.basic;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Return the node at which a linked-list cycle begins.
 *
 */
public class CycleStartNode {
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
	 * APPROACH 1 (PRIMARY): Floyd detection followed by two-pointer convergence
	 * Complexity ~ TIME : O(n), SPACE : O(1)
	 */
	static Node cycleStart(Node head) {
		Node slow = head, fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				Node finder = head;
				while (finder != slow) {
					finder = finder.next;
					slow = slow.next;
				}
				return finder;
			}
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		Node head = list(1, 2, 3, 4);
		head.next.next.next.next = head.next;
		System.out.println("Cycle starts at: " + cycleStart(head).value);
	}
}

/*
 * Output : Cycle starts at: 2
 */
