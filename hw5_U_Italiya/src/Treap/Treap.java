package Treap;

import java.util.Random;
import java.util.Stack;

public class Treap<E extends Comparable<E>> {
	private static class Node<E> {
		public E data;
		public int priority;
		public Node<E> left;
		public Node<E> right;

		public Node(E data, int priority) {
			if (data == null) {
				throw new IllegalArgumentException();
			} else {
				this.left = null;
				this.right = null;
				this.data = data;
				this.priority = priority;
			}
		}

		// for right rotation of node
		public Node<E> rotateRight() {

			Node<E> tempNode = this.left;
			Node<E> left = tempNode.right;
			tempNode.right = this;
			this.left = left;
			return tempNode;
		}

		// for left rotation of node
		public Node<E> rotateLeft() {
			Node<E> tempNode = this.right;
			Node<E> right = tempNode.left;
			tempNode.left = this;
			this.right = right;
			return tempNode;
		}

		@Override
		public String toString() {
			return this.data.toString();

		}
	}

	private Random priorityGenerator;
	private Node<E> root;

	public Treap() {

		priorityGenerator = new Random();
		root = null;
	}

	public Treap(long seed) {

		priorityGenerator = new Random(seed);
		root = null;
	}

	public void reheap(Node<E> child, Stack<Node<E>> stack) {
		while (!stack.isEmpty()) {
			Node<E> parent = stack.pop();
			if (parent.priority < child.priority) {
				if (parent.data.compareTo(child.data) > 0) {
					child = parent.rotateRight();
				} else {
					child = parent.rotateLeft();
				}
				if (!stack.isEmpty()) {
					if (stack.peek().left == parent) {
						stack.peek().left = child;
					} else {
						stack.peek().right = child;
					}
				} else {
					this.root = child;
				}
			} else {
				break;
			}
		}
	}

	boolean add(E key) {
		return add(key, priorityGenerator.nextInt());
	}

	boolean add(E key, int priority) {
		if (root == null) {
			root = new Node<E>(key, priority);
			return true;
		} else {
			Node<E> node = new Node<E>(key, priority);
			Stack<Node<E>> stack = new Stack<Node<E>>();
			Node<E> local = root;
			while (local != null) {
				local.data.compareTo(key);
				if (local.data.compareTo(key) == 0) {
					return false;
				} else {
					if (local.data.compareTo(key) < 0) {
						stack.push(local);
						if (local.right == null) {
							local.right = node;
							reheap(node, stack);
							return true;
						} else {
							local = local.right;
						}
					} else {
						stack.push(local);
						if (local.left == null) {
							local.left = node;
							reheap(node, stack);
							return true;
						} else {
							local = local.left;
						}
					}
				}
			}
			return false;
		}
	}

	public boolean delete(E key) {
		if (find(key) == false || root == null) {
			return false;
		} else {
			root = delete(key, root);
			return true;
		}
	}

	// This function help in delete
	private Node<E> delete(E key, Node<E> local) {
		if (local == null) {
			return local;
		} else {
			if (local.data.compareTo(key) < 0) {
				local.right = delete(key, local.right);
			} else {
				if (local.data.compareTo(key) > 0) {
					local.left = delete(key, local.left);
				} else {
					if (local.right == null) {
						local = local.left;
					} else if (local.left == null) {
						local = local.right;
					} else {
						if (local.right.priority < local.left.priority) {
							local = local.rotateRight();
							local.right = delete(key, local.right);
						} else {
							local = local.rotateLeft();
							local.left = delete(key, local.left);
						}
					}
				}
			}
		}
		return local;
	}

	/*
	 * Finds a node with the given key in the treap rooted at root and returns true
	 * if it finds it and false otherwise.
	 */
	private boolean find(Node<E> root, E key) {
		if (root == null) {
			return false;
		}
		if (key.compareTo(root.data) == 0) {
			return true;
		} else if (key.compareTo(root.data) < 0) {
			return find(root.left, key);
		} else {
			return find(root.right, key);
		}
	}

	// Finds a node with the given key in the treap and return true if find
	// otherwise return false
	public boolean find(E key) {
		if (key == null) {
			throw new NullPointerException("Key cannot null");
		}
		return find(root, key);
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		TraverseBefore(root, 1, stringBuilder);
		return stringBuilder.toString();
	}

	private void TraverseBefore(Node<E> node, int depth, StringBuilder stringBuilder) {
		for (int i = 1; i < depth; i++) {
			stringBuilder.append("  ");
		}
		if (node == null) {
			stringBuilder.append("null\n");
		} else {
			stringBuilder.append("(key = " + node.toString() + ", ");
			stringBuilder.append("priority = ");
			stringBuilder.append(node.priority);
			stringBuilder.append(")");
			stringBuilder.append("\n");
			TraverseBefore(node.left, depth + 1, stringBuilder);
			TraverseBefore(node.right, depth + 1, stringBuilder);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Treap<Integer> testTree = new Treap<Integer>();
		// Test for adding elements
		testTree.add(4, 19);
		testTree.add(2, 31);
		testTree.add(6, 70);
		testTree.add(1, 84);
		testTree.add(3, 12);
		testTree.add(5, 83);
		testTree.add(7, 26);
		System.out.println(testTree.toString());

	}

}