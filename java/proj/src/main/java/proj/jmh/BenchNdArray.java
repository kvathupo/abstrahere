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
    private int[][][][][][] arrOrig1; 
    private NdArray<Integer> arr1;
    private int d1 = 2;
    private int d2 = 10;
    private int d3 = 10; 
    private int d4 = 5;
    private int d5 = 20;
    private int d6 = 10;

    private float[][] arrOrig2;
    private NdArray<Float> arr2;
    private int param2d = 20;

    @Setup
    public void setup() {
        arr1 = new NdArray<>(d1, d2, d3, d4, d5, d6);
        arr2 = new NdArray<>(param2d, param2d);
        makeFields();
        arrOrig1 = new int[d1][d2][d3][d4][d5][d6];
        arrOrig2 = new float[param2d][param2d];
        makeFieldsOrig();
    }
    
    public void makeFieldsOrig() {
        for (int i = 0; i < d1; i++) {
            for (int j = 0; j < d2; j++) {
                for (int h = 0; h < d3; h++) {
                    for (int h1 = 0; h1 < d4; h1++) {
                        for (int h2 = 0; h2 < d5; h2++) {
                            for (int h3 = 0; h3 < d6; h3++) {
                                arrOrig1[i][j][h][h1][h2][h3] = 2*i + 3*j + 5*h
                                    - 2*h1 - 3*h2 - 5*h3;
                            }
                        }
                    }
                }
            }
        } 
        for (int i = 0; i < param2d; i++) {
            for (int j = 0; j < param2d; j++) {
                arrOrig2[i][j] = 2*i + 3*j;
            }
        }
    }
    
    public void makeFields() {
        for (int i = 0; i < d1; i++) {
            for (int j = 0; j < d2; j++) {
                for (int h = 0; h < d3; h++) {
                    for (int h1 = 0; h1 < d4; h1++) {
                        for (int h2 = 0; h2 < d5; h2++) {
                            for (int h3 = 0; h3 < d6; h3++) {
                                arr1.put(2*i + 3*j + 5*h - 2*h1 - 3*h2 - 5*h3, i, j, h, h1, h2, h3);
                            }
                        }
                    }
                }
            }
        } 
        for (int i = 0; i < param2d; i++) {
            for (int j = 0; j < param2d; j++) {
                arr2.put((float) 2*i + 3*j, i, j);
            }
        }
    }

    @Benchmark
    public void twoDArrayIliffe(Blackhole bh) {
        for (int i = 0; i < param2d; i++) {
            for (int j = 0; j < param2d; j++) {
                bh.consume(arrOrig2[i][j]);
            }
        } 
    }
    @Benchmark
    public void twoDArrayProp(Blackhole bh) {
        for (int i = 0; i < param2d; i++) {
            for (int j = 0; j < param2d; j++) {
                bh.consume(arr2.get(i, j));
            }
        } 
    }

    public void sixDArrayIliffe(Blackhole bh) {
        for (int i = 0; i < d1; i++) {
            for (int j = 0; j < d2; j++) {
                for (int h = 0; h < d3; h++) {
                    for (int h1 = 0; h1 < d4; h1++) {
                        for (int h2 = 0; h2 < d5; h2++) {
                            for (int h3 = 0; h3 < d6; h3++) {
                                bh.consume(arrOrig1[i][j][h][h1][h2][h3]);
                            }
                        }
                    }
                }
            }
        } 
    }

    public void sixDArrayProp(Blackhole bh) {
        for (int i = 0; i < d1; i++) {
            for (int j = 0; j < d2; j++) {
                for (int h = 0; h < d3; h++) {
                    for (int h1 = 0; h1 < d4; h1++) {
                        for (int h2 = 0; h2 < d5; h2++) {
                            for (int h3 = 0; h3 < d6; h3++) {
                                bh.consume(arr1.get(i, j, h, h1, h2, h3));
                            }
                        }
                    }
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
