package proj.structures;

// JUnit
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(OrderAnnotation.class)
public class NdArrayTest {
    private int d1 = 100;
    private int d2 = 100;
    private int d3 = 100;

    @Test
    @Order(1)
    void creationTest() {
        NdArray<Integer> arr = new NdArray<>(d1, d2, d3);
        int[][][] c_arr = new int[d1][d2][d3];
        
        for (int i = 0; i < d1; i++) {
            for (int j = 0; j < d2; j++) {
                for (int k = 0; k < d3; k++) {
                    c_arr[i][j][k] = 2*i + 3*j + 5*k;
                    arr.put(2*i + 3*j + 5*k, i, j, k);
                }
            }
        }

        for (int i = 0; i < d1; i++) {
            for (int j = 0; j < d2; j++) {
                for (int k = 0; k < d3; k++) {
                    assertEquals(arr.get(i, j, k), c_arr[i][j][k]);
                }
            }
        }

    }
}
