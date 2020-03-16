package proj.structures;

import java.io.Serializable;
import java.lang.Cloneable;
import java.lang.RuntimeException;
import java.lang.IndexOutOfBoundsException;
import java.lang.String;
import java.util.Arrays;

/**
 * A class implementing a multi-dimensional array as a one-dimensional one.
 * <p>
 * Serves to test the cache locality of the default implementation of 
 * multi-dimensional arrays, which are coded as Iliffe Vectors. 
 *
 * @author Kalinda Vathupola
 */


// TODO:
//   - implement Cloneable, Serializable
//   - Tranform method from backing array to multi-dimensional
public class NdArray<E> {
    // Fields (NdArray)
    private static final int MAXDIM = 512;
    int dim;
    int[] dimLen;
    E[] backingArr;

    // Constructors (NdArray)
    /**
     * Create one-dimensional array with capacity 100 by default.
     * <p>
     * Create generic array as in ArrayList constructor source
     */
    public NdArray() {
        dim = 1;
        dimLen = new int[] {100};
        backingArr = (E[]) new Object[100];
    }
    /**
     * Initialize backing array.
     *
     * @param   dims                Create backing array with dims.length
     *                              dimensions. The capacity of the ith 
     *                              dimension corresponds to dims[i].
     * @throws  RuntimeException    If requesting 512 or more dimensions 
     *                              (the limit of the JVM)
     */
    public NdArray(int... dims) throws RuntimeException {
        dim = dims.length;
        if (dim >= MAXDIM) {
            String errMsg = String.format("NdArray: dimension request of %d" + 
                    " exceeds 512, max allowed by JVM", dim);
            throw new RuntimeException(errMsg);
        } else {
            dim = dims.length;
            dimLen = dims.clone();

            int capacity = 0;
            for (int i = 0; i < dim; i++) {
                capacity += dims[i];
            }
            backingArr = (E[]) new Object[capacity];
        }
    }
    
    // Methods (NdArray)
    public int getDimensionCount() {
        return dim;
    }
    public int[] getCapacity() {
        return dimLen.clone();
    }
    /**
     * Return element from the backing array with index corresponding to 
     * that of the multi-dimensional one.
     * WARNING: No bounds checking!
     *
     * @param  index                        Requested index
     * @throws RuntimeException             If dimension of index does not match
     *                                      that of the backing array.
     * @return                              Value at multidimensional array
     *                                      index.
     */
    public E get(int... index) 
            throws RuntimeException {
        if (index.length != dim) {
            String errMsg = String.format("Index of dimension %d does not " + 
                    "match backing array's dimension of %d", index.length, dim);
            throw new RuntimeException("NdArray.getEntry: " +  errMsg);
        } else {
            int backingInd = 0;
            for (int ind : index) {
                backingInd += ind;
            }
            return backingArr[backingInd];
        }
    }
    /**
     * Return element from the backing array with index corresponding to 
     * that of the multi-dimensional one.
     *
     * @param  index                        Requested index
     * @throws IndexOutOfBoundsException    If index out of bounds
     * @throws RuntimeException             If dimension of index does not match
     *                                      that of the backing array.
     * @return                              Value at multidimensional array
     *                                      index.
     */
    public E getEntry(int... index) 
            throws IndexOutOfBoundsException, RuntimeException {
        if (index.length != dim) {
            String errMsg = String.format("Index of dimension %d does not " + 
                    "match backing array's dimension of %d", index.length, dim);
            throw new RuntimeException("NdArray.getEntry: " +  errMsg);
        } else if (isIndexOutOfBounds(index)) {
            throw new IndexOutOfBoundsException("NdArray.getEntry");
        } else {
            int backingInd = 0;
            for (int ind : index) {
                backingInd += ind;
            }
            return backingArr[backingInd];
        }
    }
    /**
     * Assigns element.
     *
     * @param  index                        Requested index
     * @throws RuntimeException             If dimension of index does not match
     *                                      that of the backing array.
     */
    public void put(E ele, int... index) 
            throws RuntimeException {
        if (index.length != dim) {
            String errMsg = String.format("Index of dimension %d does not " + 
                    "match backing array's dimension of %d", index.length, dim);
            throw new RuntimeException("NdArray.getEntry: " +  errMsg);
        } else {
            int backingInd = 0;
            for (int ind : index) {
                backingInd += ind;
            }
            backingArr[backingInd] = ele;
        }
    }
    /**
     * Is the requested index out of bounds for the backing array?
     *
     * @param   index       Requested index
     * @return  rval        true if index does exceed the capacity of any 
     *                      dimension of the backing array. 
     *                      false if valid index is requested.
     */
    public boolean isIndexOutOfBounds(int... index) {
        for (int i = 0; i < dim; i++) {
            if (dimLen[i] <= index[i]) {
                return true;
            }
        }
        return false;
    }

}
