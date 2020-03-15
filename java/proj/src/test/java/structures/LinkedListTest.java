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
        lst.insert(2);
        lst.insert(1);
        assertEquals(lst.size(), 2);
        assertEquals(lst.get(0), 1);
        assertEquals(lst.get(1), 2);
    }
}
