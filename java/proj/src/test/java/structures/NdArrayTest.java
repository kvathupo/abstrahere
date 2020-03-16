package structures;

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
        int[2][3] c_arr;
        
        c_arr[0][0] = 1;

    }
