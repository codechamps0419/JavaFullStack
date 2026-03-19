package heap;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Merge k sorted linked lists into one sorted list.
 *
 */
public class MergeKSortedLists {
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
	 * APPROACH 1 (PRIMARY): Min heap of current list heads Complexity ~ TIME : O(N
	 * log k), SPACE : O(k)
	 */
	static Node merge(Node[] lists) {
		PriorityQueue<Node> heap = new PriorityQueue<>(Comparator.comparingInt(n -> n.value));
		for (Node node : lists)
			if (node != null)
				heap.offer(node);
		Node dummy = new Node(0), tail = dummy;
		while (!heap.isEmpty()) {
			Node node = heap.poll();
			if (node.next != null)
				heap.offer(node.next);
			tail.next = node;
			tail = node;
		}
		tail.next = null;
		return dummy.next;
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		Node result = merge(new Node[] { list(1, 4, 5), list(1, 3, 4), list(2, 6) });
		System.out.println(show(result));
	}
}

/*
 * Output : 1 -> 1 -> 2 -> 3 -> 4 -> 4 -> 5 -> 6 -> null
 */
