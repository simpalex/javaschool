package stackTrace;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Date;

@State(Scope.Benchmark)
public class HowDependsDepth {

    @Param({"1", "2", "10", "100", "1000"})
    int depth;

    @Benchmark
    public Object throwD() {
        try {
            return recursive(depth);
        } catch (Exception e) {
            return e;
        }
    }

    public Object recursive(int depth) {
        if (depth == 0) return new Exception();
        return recursive(--depth);
    }

    public static void main(String[] args) throws RunnerException {

        Options options = new OptionsBuilder()
                .include(HowDependsDepth.class.getSimpleName())
                .threads(1)
                .forks(1)
                .build();
        new Runner(options).run();
    }
}
