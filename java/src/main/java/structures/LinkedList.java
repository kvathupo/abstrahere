package structures;
import java.util.AbstractSequentialList;

/**
 * A basic implementation of an unordered, singly linked list.
 * <p>
 * Differences from JDK:
 * - Constructor doesn't throw NullPointerException
 *   - Exists isEmpty() method
 *
 * @author  Kalinda Vathupola
 */
public class LinkedList<E> extends AbstractSequentialList<E> {
    private class Node {
        // Fields (Node)
        private E ele;
        private Node next;

        // Constructors (Node)
        public Node(E ele, Node next) {
            this.ele = ele;
            this.next = next;
        }
    }
    
    // Fields (LinkedList)
    private Node head;
    private int size;

    // Constructors (LinkedList)
    public LinkedList() {
        LinkedList(null);
    }

    public LinkedList(Collection<? extends E> c) {
        if (c == null || c.isEmpty()) {
            this.head = new Node(null, null);
            this.size = 0;
        } else {
            Iterator<E> iter = c.iterator();
            this.head = new Node(iter.next(), null);
            this.size = 1;

            while (iter.hasNext()) {
                this.push(iter.next());
            }
        }
    }

    // Methods (LinkedList)
    public isEmpty() {
        return size==0;
    }
}
