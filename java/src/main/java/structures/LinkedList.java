package structures;
import java.lang.IndexOutOfBoundsException;
import java.util.AbstractSequentialList;

/**
 * A basic implementation of a singly linked list with methods supporting 
 * ordered and unordered flavors.
 * <p>
 * Differences from JDK:
 * - Constructor doesn't throw NullPointerException
 *   - Has isEmpty() method
 * - Not Serializable
 *
 * @author  Kalinda Vathupola
 */
public class LinkedList<E> {
    private class Node {
        // Fields (Node)
        private transient E ele;
        private transient Node next;

        // Constructors (Node)
        public Node(E ele, Node next) {
            setContents(ele);
            setNext(next);
        }

        // Methods (Node)
        public E getContents() {
            return this.ele;
        }

        public Node getNext() {
            return this.next;
        }

        public void setContents(E ele) {
            this.ele = ele;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public boolean hasNext() {
            return next != null;
        }
    }
    
    // Fields (LinkedList)
    private transient Node head;
    private int size;

    // Constructors (LinkedList)
    public LinkedList() {
        LinkedList(null);
    }

    public LinkedList(Collection<? extends E> c) {
        if (c == null || c.isEmpty()) {
            head = new Node(null, null);
            size = 0;
        } else {
            Iterator<E> iter = c.iterator();
            head = new Node((E) iter.next(), null);
            size = 1;

            while (iter.hasNext()) {
                this.insert((E) iter.next());
                size++;
            }
        }
    }

    // Methods (LinkedList)
    /**
     * Checks if list is empty (contains single node with contents null)
     * <p>
     * Worst-case runtime: O(n)
     */
    public void insert(E ele) {
        if (isEmpty()) {
            head.setContents(ele);
        } else {
            head = new Node(ele, head);
        }
        size++;
    }

    /**
     * Finds the node containing the object of interest, deletes said node and
     * returns its contents.
     *
     * Worst-case runtime: O(n)
     *
     * @param   obj     Object to remove in the linked list. Is of the type 
     *                  Object since two objects may be of different type yet
     *                  return true from equals().
     * @return          The contents of the deleted node, or null.
     */
    public E delete(Object obj) {
        if (!isEmpty()) {
            E retval = null;
            Node<E> curr = head;
            Node<E> prev = curr;

            do {
                if (obj.equals(curr.getContents())) {
                    if (curr == head) {
                        retval = curr.getContents();
                        head = new Node(null, null);
                        break;
                    } else {
                        retval = curr.getContets();
                        prev.setNext(curr.getNext());
                        break;
                    }
                } else {
                    prev = curr;
                    curr = curr.getNext();
                }
            } while (prev.hasNext())
            size--;
            return retval;
        } else {
            return null;
        }
    }

    /**
     * Returns true if the equals() method of obj returns true for the contents
     * of any node.
     * <p>
     * Worst-case runtime: O(n)
     */
    public boolean contains(Object obj) {
        if (!isEmpty()) {
            boolean retVal = false;
            Node<E> curr = this.head;
            Node<E> prev = curr;

            do {
                if (obj.equals(curr.getContents())) {
                    retVal = true;
                    break;
                } else {
                    prev = curr;
                    curr = curr.getNext();
                }
            } while (prev.hasNext())
            return retVal;
        } else {
             return false;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size==0;
    }

    /* 
     * The above methods satisfy the use of LinkedList as a set.
     * (Unordered Linked List)
     * <p>
     * The methods below extend LinkedList to an Array.
     * (Ordered Linked List)
     */

    /**
     * Returns the contents of the node at specified index. 
     * <p>
     * Worst-case runtime: O(n) 
     *
     * @param   indx                        Index of the intended object
     * @throws  IndexOutOfBoundsException   If index out of bounds
     * @return                              Contents of the node at index of 
     *                                      argument.
     */
    public E get(int indx) throws IndexOutOfBoundsException {
        if (indx < 0 || size <= indx) {
            throw new IndexOutOfBoundsException("Index " + indx
                    + " out of bounds for list of size " + size);
        } else {
            Node<E> curr = head;

            while (indx > 0) {
                curr = curr.getNext();
                indx--;
            }
            return curr.getContents();
        }
    }

    /**
     * Inserts element at specified index. 
     * <p>
     * Worst-case runtime: O(n) 
     *
     * @param   indx                        Index of insertion.
     * @param   ele                         Object to insert.
     * @throws  IndexOutOfBoundsException   If index out of bounds.
     */
    public void insertAt(int indx, E ele) {
        if (indx < 0 || size <= indx) {
            throw new IndexOutOfBoundsException("Index " + indx
                    + " out of bounds for list of size " + size);
        } else if (indx == 0) {
            insert(ele);
        } else {
            Node<E> curr = head;
            Node<E> prev = head;

            while (indx > 0) {
                prev = curr;
                curr = curr.getNext();
                indx--;
            }
            prev.setNext(new Node(ele, curr));
        }

    }
}
