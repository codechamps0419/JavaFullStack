package linkedlist.medium;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Determine whether the list reads the same forward and backward.
 *
 */
public class PalindromeLinkedList {
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
	 * APPROACH 1 (PRIMARY): Fast/slow pointers + in-place reversal of second half
	 * Complexity ~ TIME : O(n), SPACE : O(1)
	 */
	static boolean isPalindrome(Node head) {
		if (head == null || head.next == null)
			return true;
		Node slow = head, fast = head;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		Node second = reverse(slow.next);
		Node restoreHead = second;
		Node first = head;
		boolean equal = true;
		while (second != null) {
			if (first.value != second.value) {
				equal = false;
				break;
			}
			first = first.next;
			second = second.next;
		}
		slow.next = reverse(restoreHead); // Restore caller's original list.
		return equal;
	}

	static Node reverse(Node head) {
		Node prev = null;
		while (head != null) {
			Node next = head.next;
			head.next = prev;
			prev = head;
			head = next;
		}
		return prev;
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		Node head = list(1, 2, 3, 2, 1);
		System.out.println("Palindrome: " + isPalindrome(head));
		System.out.println("Restored  : " + show(head));
	}
}

/*
 * Output : Palindrome: true Restored : 1 -> 2 -> 3 -> 2 -> 1 -> null
 */
