package cs211_lab1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Node {
	int data;
	Node pre;
	Node next;

	Node(int data) {
		this.data = data;
	}
}

public class Sort_Array_To_Balanced_BST {
	public static void main(String[] args) {
		int a[] = { 3, 7, 6, 9, 11, 14, 0, 5, -3, -8, 12, 15, 19, 1, -11 };
		Arrays.sort(a);
		Node node = add(a, 0, a.length - 1);
		show(node);

	}

	public static Node add(int[] a, int begin, int end) {
		if (begin > end) {
			return null;
		}
		
		// similar to binary sort
		int mid = (end + begin) / 2;
		Node node = new Node(a[mid]);
		node.pre = add(a, begin, mid - 1);
		node.next = add(a, mid + 1, end);
		return node;
	}

	// get the information
	public static void show(Node node) {
		Queue<Node> q = new LinkedList<Node>();
		q.add(node);
		while (!q.isEmpty()) {
			node = q.remove();
			System.out.print("--> " + node.data + " ");
			if (node.pre != null) {
				q.add(node.pre);
			}
			if (node.next != null) {
				q.add(node.next);
			}
		}

	}
}
