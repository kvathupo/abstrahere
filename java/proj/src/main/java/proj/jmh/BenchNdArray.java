package proj.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
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
    private int[][][] arrOrig1; 
    private NdArray<Integer> arr1;

    @Setup
    public void setup() {
        arr1 = new NdArray<>(100, 100, 100);
        makeFields();
        arrOrig1 = new int[100][100][100];
        makeFieldsOrig();
    }
    
    public void makeFieldsOrig() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                for (int h = 0; h < 100; h++) {
                    arrOrig1[i][j][h] = 2*i + 3*j + 5*h;
                }
            }
        } 
    }
    
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
    public void sumOrigOne(Blackhole bh) {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                for (int h = 0; h < 100; h++) {
                    bh.consume(arrOrig1[i][j][h]);
                }
            }
        } 
    }

    @Benchmark
    public void sumOne(Blackhole bh) {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                for (int h = 0; h < 100; h++) {
                    bh.consume(arr1.get(i, j, h));
                }
            }
        } 
    }
    
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(BenchNdArray.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
