package cs211_lab2;

import java.util.Stack;

class Node {
	int data;
	Node left;
	Node right;
	
	Node(int data) {
		this.data = data;
	}
}

public class BST {
	Node root;

	public static void main(String args[]) {
		int a[] = { 3, 9, 7, 6, 5, 11, 13, 19, 16, 8 };
//      example
//        int a[]= {2,3,5,4,1};
		BST bst = new BST();
		for (int i = 0; i < a.length; i++) {
			bst.add(a[i]);
		}
		bst.delete(11);
		bst.add(10);
		bst.delete(16);
//        example
//          bst.delete(5);

		System.out.print("Preorder:  ");
		preorder(bst.root);
		System.out.println("\n-------------------------------");
		System.out.print("Inorder:   ");
		inorder(bst.root);
		System.out.println("\n-------------------------------");
		System.out.print("Postorder: ");
		postorder(bst.root);
	}

	static void preorder(Node node) {
		if (node == null)
			return;
		Stack<Node> s = new Stack<>();
		Node c = node;
		while (c != null || !s.isEmpty()) {
			while (c != null) {
				System.out.print(c.data + " ");
				s.push(c);
				c = c.left;
			}
			c = s.pop();
			c = c.right;
		}
	}
	
	static void inorder(Node node) {
		if (node == null)
			return;
		// Traverse left
		inorder(node.left);
		// Traverse root
		System.out.print(node.data + " ");
		// Traverse right
		inorder(node.right);
	}

	static void postorder(Node node) {
		if (node == null)
			return;
		// Traverse left
		postorder(node.left);
		// Traverse right
		postorder(node.right);
		// Traverse root
		System.out.print(node.data + " ");
	}
//  add 
	public void add(int data) {
		root = addRecursive(root, data);
	}
//  delete
	public void delete(int data) {
		root = deleteRecursive(root, data);
	}
//  addRecursive
	private Node addRecursive(Node current, int data) {
		if (current == null) {
			return new Node(data);
		}
		if (data < current.data) {
			current.left = addRecursive(current.left, data);
		} else if (data > current.data) {
			current.right = addRecursive(current.right, data);
		} else {
		}
		return current;
	}
//  deleteRecursive
	private Node deleteRecursive(Node current, int data) {
		if (current == null) {
			return null;
		}
		if (data == current.data) {
			// found the node
			if (current.left == null && current.right == null) {
				return null;
			}
			if (current.right == null) {
				return current.left;
			}
			if (current.left == null) {
				return current.right;
			}
			if (current.left != null && current.right != null) {
				int smallestValue = findSmallestValue(current.right);
				current.data = smallestValue;
				current.right = deleteRecursive(current.right, smallestValue);
				return current;
			}
		}
		if (data < current.data) {
			current.left = deleteRecursive(current.left, data);
			return current;
		}
		current.right = deleteRecursive(current.right, data);
		return current;
	}
//  findSmallestValue
	private int findSmallestValue(Node root) {
		return root.left == null ? root.data : findSmallestValue(root.left);
	}
}