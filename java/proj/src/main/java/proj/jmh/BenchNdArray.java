package proj.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

import proj.structures.NdArray;

@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class BenchNdArray {
    private int[][][] arrOrig1 = new int[100][100][100];
    private NdArray<Integer> arr1 = new NdArray<>(100, 100, 100);
    
    @Benchmark
    public void makeFieldsOrig() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                for (int h = 0; h < 100; h++) {
                    arrOrig1[i][j][h] = 2*i + 3*j + 5*h;
                }
            }
        } 
    }
    
    @Benchmark
    public void makeFields() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                for (int h = 0; h < 100; h++) {
                    arr1.put(2*i + 3*j + 5*h, i, j, h);
                }
            }
        } 
    }

    @Benchmark
    public int sumOrigOne() {
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                for (int h = 0; h < 100; h++) {
                    sum += arrOrig1[i][j][h];
                }
            }
        } 
        return sum;
    }

    @Benchmark
    public int sumOne() {
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                for (int h = 0; h < 100; h++) {
                    sum += arr1.get(i, j, h);
                }
            }
        } 
        return sum;
    }
    
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(BenchNdArray.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
