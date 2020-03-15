package structures;
import java.lang.IndexOutOfBoundsException;
import java.util.Collection;
import java.util.Iterator;

/**
 * A basic implementation of a singly linked list with methods supporting 
 * ordered and unordered flavors.
 * <p>
 * Differences from JDK:
 * - Overrides hashCode() method
 * - Constructor doesn't throw NullPointerException
 *   - Has isEmpty() method
 * - Not Serializable
 *
 * @author  Kalinda Vathupola
 */
public class LinkedList<E> {
    private class Node<E> {
        // Fields (Node)
        private transient E ele;
        private transient Node<E> next;

        // Constructors (Node)
        public Node(E ele, Node<E> next) {
            setContents(ele);
            setNext(next);
        }

        // Methods (Node)
        public E getContents() {
            return this.ele;
        }

        public Node<E> getNext() {
            return this.next;
        }

        public void setContents(E ele) {
            this.ele = ele;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public boolean hasNext() {
            return next != null;
        }
    }
    
    // Fields (LinkedList)
    private transient Node<E> head;
    private int size;
    private Class<E> type;

    // Constructors (LinkedList)
    public LinkedList() {
        this(null);
    }
    public LinkedList(Object c) {
        // Check c is instaceof Collection<? extends E>
        // null check -> instanceof Collection<?> -> isempty? -> element of type?
        // not null -> 
        if (c == null || !(c instanceof Collection<?>) ||
                    (((Collection<?>)c).isEmpty()) ||
                    !(type.isInstance(((Collection<?>) c).iterator().next()))){
            head = new Node<>(null, null);
            size = 0;
        } else {
            Collection<E> origC = (Collection<E>) c;
            Iterator<E> iter = origC.iterator();
            head = new Node<>(iter.next(), null);
            size = 1;

            while (iter.hasNext()) {
                insert(iter.next());
                size++;
            }
        }
    }


    // Methods (LinkedList)
    /**
     * Get the head of the linked list.
     *
     */
    private Node<E> getHead() {
        return head;
    }

    /**
     * Checks that the current linked list and the argument are of the same type
     * and equal as unordered lists.
     *
     * @param obj       Target object to check for equality
     * @return          true if both are of the same type, else false
     */
    public boolean equalsUnordered(LinkedList<E> lst) {
        Node<E> curr = head;

        do {
            if (!lst.contains(curr.getContents())) {
                return false;
            } else {
                curr = curr.getNext();
            }
        } while (curr != null);

        return true;
    }
    /**
     * Checks if list is empty (contains single node with contents null)
     * <p>
     */
    public void insert(E ele) {
        if (isEmpty()) {
            head.setContents(ele);
        } else {
            head = new Node<>(ele, head);
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
                        head = new Node<>(null, null);
                        break;
                    } else {
                        retval = curr.getContents();
                        prev.setNext(curr.getNext());
                        break;
                    }
                } else {
                    prev = curr;
                    curr = curr.getNext();
                }
            } while (prev.hasNext());
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
            } while (prev.hasNext());
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
     * Checks that the current linked list and the argument are of the same type
     * and equal as ordered lists.
     *
     * @param obj       Target object to check for equality
     * @return          true if both are of the same type, else false
     */
    public boolean equalsOrdered(LinkedList<E> lst) {
        // Are the lists of equal size? If not, short-circuit to save 
        // runtime
        if (lst.size() != size()) {
            return false;
        }

        Node<E> curr = head;
        Node<E> currTarget = lst.getHead();

        do {
            if (!curr.getContents().equals(currTarget.getContents())) {
                return false;
            } else {
                curr = curr.getNext();
                currTarget = currTarget.getNext();
            }
        } while (curr != null && currTarget != null);

        return true;
    }

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
            prev.setNext(new Node<>(ele, curr));
        }

    }
}
