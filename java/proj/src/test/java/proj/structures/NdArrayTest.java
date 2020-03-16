package proj.structures;

// JUnit
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(OrderAnnotation.class)
public class NdArrayTest {

    @Test
    @Order(1)
    void creationTest() {
        NdArray<Integer> arr = new NdArray<>(2, 3);
        int[][] c_arr = new int[2][3];
        
        c_arr[0][0] = 1;
        arr.put(1, 0, 0);

        c_arr[1][0] = 2;
        arr.put(2, 1, 0);

        assertEquals(arr.get(1, 0), c_arr[1][0]);
    }
}
