//Name: Utsav Italiya
//CWID: 10475248

package hw3_U_Italiya;

public class IDLLTest {

	public static void main(String[] args) {
		// creating idll named empty doubly linked list
		IDLList<Integer> idll = new IDLList();

		// adding element to link list
		idll.add(10);
		idll.add(20);
		System.out.println("Linked List after using add(): " + idll);

		// adding element on given index
		idll.add(1, 30);
		idll.add(0, 40);
		idll.add(4, 50);
		System.out.println("Linked List after using add() with index number: " + idll);

		// append 60 and 70 into linked list
		idll.append(60);
		idll.append(70);
		System.out.println("Linked List after using append(): " + idll);

		// getting element on given index
		System.out.println("Element on 3rd index: " + idll.get(3));

		// printing head of linked list
		System.out.println("Element on head: " + idll.getHead());

		// printing last element of link list
		System.out.println("Element on last index:" + idll.getLast());

		// showing size of linked list
		System.out.println("Size of linked list: " + idll.size());

		// removing element from head of the link list
		System.out.println("Element removed from head: " + idll.remove());
		System.out.println("Link list after removing head element: " + idll);

		// removing element from tail of the link list
		System.out.println("Element removed from tail: " + idll.removeLast());
		System.out.println("Link list after removing tail element: " + idll);

		// removing first occurance of given element
		System.out.println("Removed element of 2nd index: " + idll.removeAt(2));
		System.out.println("Link list after removing element of 2nd index: " + idll);

		idll.remove(30);
		System.out.println("Removing element that is first occurance of 30: ");
		System.out.println("Link list after removing first occurance of 30: " + idll);
	}

}