package stackTrace;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Date;

public class HowExpensiveException {

    @Benchmark
    public Object date() {
        return new Date();
    }

    @Benchmark
    public Object exception() {
        return new Exception();
    }

    @Benchmark
    public Object exceptionCutted() {
        return new Exception(){
            @Override
            public synchronized Throwable fillInStackTrace() {
                return this;
            }
        };
    }

    public static void main(String[] args) throws RunnerException {

        Options options = new OptionsBuilder()
                .include(HowExpensiveException.class.getSimpleName())
                .threads(1)
                .forks(1)
                .build();
        new Runner(options).run();
    }
}
