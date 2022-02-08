//Name: Utsav Italiya
//CWID: 10475248

package hw3_U_Italiya;
import java.util.ArrayList;

class IDLList<E> {
	class Node<E> {
		E data;
		Node<E> next;
		Node<E> prev;

		Node(E elem) {
			data = elem;
		}

		Node(E elem, Node<E> prev, Node<E> next) {
			data = elem;
			this.prev = prev;
			this.next = next;
		}
	}

	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;

	public IDLList() {
		head = null;
		tail = null;
		size = 0;
		indices = new ArrayList<>();
		indices.clear();
	}

	public boolean add(int index, E elem) {
		Node<E> new_node = new Node<>(elem);
		if (index == 0) {
			return add(elem);
		} else if (index == size) {
			return append(elem);
		} else {
			try {
				Node<E> prev_node = indices.get(index - 1);
				Node<E> next_node = indices.get(index);
				new_node.prev = prev_node;
				new_node.next = next_node;
				prev_node.next = new_node;
				next_node.prev = new_node;
				indices.add(index, new_node);
				size++;
				return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
			return false;
		}
	}

	public boolean add(E elem) {
		Node<E> new_node = new Node<>(elem);
		if (size == 0) {
			head = new_node;
			tail = new_node;
			indices.add(0, new_node);
			size++;
		} else {
			new_node.next = head;
			head.prev = new_node;
			head = new_node;
			indices.add(0, new_node);
			size++;
		}
		return true;
	}

	public boolean append(E elem) {
		Node<E> new_node = new Node<>(elem);
		if (size == 0) {
			head = new_node;
			tail = new_node;
			indices.add(new_node);
			size++;
		} else {
			new_node.prev = tail;
			tail.next = new_node;
			tail = new_node;
			indices.add(new_node);
			size++;
		}
		return true;
	}

	public E get(int index) {
		Node<E> idx_node = indices.get(index);
		return idx_node.data;
	}

	public E getHead() {
		return head.data;
	}

	public E getLast() {
		return tail.data;
	}

	public int size() {
		return size;
	}

	public E remove() {
		head = head.next;
		head.prev = null;
		size--;
		return indices.remove(0).data;
	}

	public E removeLast() {
		tail = tail.prev;
		tail.next = null;
		size--;
		return indices.remove(size).data;
	}

	public E removeAt(int index) {
		if (index == 0) {
			return remove();
		} else if (index == size) {
			return removeLast();
		} else {
			try {
				Node<E> idx_node = indices.get(index);
				Node<E> prev_node = indices.get(index - 1);
				Node<E> next_node = indices.get(index + 1);
				prev_node.next = next_node;
				next_node.prev = prev_node;
				size--;
				return indices.remove(index).data;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e);
				return null;
			}
		}

	}

	public boolean remove(E elem) {
		int temp = 0;
		Node<E> head_node = head;
		while (head_node.next != null) {
			if (head_node.data.equals(elem)) {
				if (temp == 0) {
					head = head.next;
					size--;
					indices.remove(0);
					return true;
				}
				head_node.prev.next = head_node.next;
				head_node.next.prev = head_node.prev;
				indices.remove(temp);
				size--;
				return true;
			}
			head_node = head_node.next;
			temp++;
		}
		return false;
	}

//
	@Override
	public String toString() {
		Node curNode = head;
		if (head == null) {
			System.out.println("List is empty.");
		}
		String str = "";
		while (curNode != null) {
			str += curNode.data + " ";
			curNode = curNode.next;
		}
		return str;
	}

}