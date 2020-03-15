package structures;

// JUnit
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(OrderAnnotation.class)
public class LinkedListTest {
    
    @Test
    @Order(1)
    void creationTest() {
        LinkedList<Integer> lst = new LinkedList<>();
        assertEquals(lst.size(), 0);
        assertTrue(lst.isEmpty());
        lst.insert(4);
        lst.insert(3);
        lst.insert(2);
        lst.insert(1);
        assertEquals(lst.size(), 4);
        assertEquals(lst.get(0), 1);
        assertEquals(lst.get(1), 2);
        assertEquals(lst.get(2), 3);
        assertEquals(lst.get(3), 4);
    }

    @Test
    @Order(2)
    void deleteTest() {
        LinkedList<Integer> lst = new LinkedList<>();
        LinkedList<Integer> lst2 = new LinkedList<>();

        lst.insert(4);
        lst.insert(3);
        lst.insert(2);
        lst.insert(1);

        lst2.insert(1);
        lst2.insert(2);
        lst2.insert(3);
        lst2.insert(4);
    
        assertEquals(lst.equalsUnordered(lst2), lst2.equalsUnordered(lst));
        
        lst.delete(1);
        lst.delete(2);
        lst.delete(3);
        lst.delete(4);

        lst2.delete(4);
        lst2.delete(3);
        lst2.delete(2);
        lst2.delete(1);

        assertTrue(lst.isEmpty());
        assertTrue(lst2.isEmpty());
    }
}
