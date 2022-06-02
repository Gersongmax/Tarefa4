package inf300.servico;

import java.util.concurrent.TimeUnit;
import org.junit.Test;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 5, warmups = 1)
@Warmup(iterations = 3, time = 200, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 200, timeUnit = TimeUnit.MILLISECONDS)
public class BenchmarkTest {


    public static void main(String[] args) throws Exception {
        //org.openjdk.jmh.Main.main(args);
        final Options opt = new OptionsBuilder()
                .include(BenchmarkTest.class.getSimpleName())
                .forks(5)
                .result("result.txt")
                .build();
        new Runner(opt).run();
    }
    
    //@Test(timeout = 120000)
    public void benchmarkTest() throws Exception {
        main(null);
    }

    @Benchmark
    public static void bestSellers() throws InterruptedException, Throwable {
        final String[] SUBJECTS = {"ARTS", "BIOGRAPHIES", "BUSINESS", "CHILDREN",
        "COMPUTERS", "COOKING", "HEALTH", "HISTORY", "HOME", "HUMOR", "LITERATURE", "MYSTERY",
        "NON-FICTION", "PARENTING", "POLITICS", "REFERENCE", "RELIGION", "ROMANCE",
        "SELF-HELP", "SCIENCE-NATURE", "SCIENCE_FICTION", "SPORTS", "YOUTH", "TRAVEL"};
        for (int i = 0; i < SUBJECTS.length; i++) {
            Bookstore.getInstance().getBestSellers(SUBJECTS[i]);
        }

    }
    



}
