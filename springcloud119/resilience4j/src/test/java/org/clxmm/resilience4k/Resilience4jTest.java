package org.clxmm.resilience4k;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.vavr.CheckedFunction0;
import io.vavr.CheckedRunnable;
import io.vavr.control.Try;
import org.junit.Test;

import java.time.Duration;
import java.util.Date;

/**
 * @author clx
 * @date 2020-05-17 20:44
 */

public class Resilience4jTest {


    @Test
    public void test() {

        //获取 CircuitBreakerRegistry 实例 可以调用ofDefaults获取一个
        //CircuitBreakerRegistry实例，也可以自定义属性
        CircuitBreakerRegistry registry = CircuitBreakerRegistry.ofDefaults();


        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                // 故障率阈值百分比，超过这个阈值，断路器就会打开
                .failureRateThreshold(50)
                ///断路器保持打开的时间，在到达设置的时间之后，断路器会进入到 half open 状态
                .waitDurationInOpenState(Duration.ofMillis(1_000))
                // 当断路器处于half open 状态时，环形缓冲区的大小
                .ringBufferSizeInHalfOpenState(2)
                .ringBufferSizeInClosedState(2)
                .build();

        CircuitBreakerRegistry r1 = CircuitBreakerRegistry.of(config);

        CircuitBreaker cb1 = r1.circuitBreaker("clx");
        CircuitBreaker cb2 = r1.circuitBreaker("clx2", config);

        CheckedFunction0<String> supplier = CircuitBreaker.decorateCheckedSupplier(cb1, () -> "hello resilience4j");

        Try<String> map = Try.of(supplier)
                .map(v -> v + "hello world");


        System.out.println(map.isSuccess());
        System.out.println(map.get());


    }


    @Test
    public void test2() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                // 故障率阈值百分比，超过这个阈值，断路器就会打开
                .failureRateThreshold(50)
                ///断路器保持打开的时间，在到达设置的时间之后，断路器会进入到 half open 状态
                .waitDurationInOpenState(Duration.ofMillis(1_000))
                // 当断路器处于half open 状态时，环形缓冲区的大小
                .ringBufferSizeInHalfOpenState(2)
                .ringBufferSizeInClosedState(2)
                .build();
        CircuitBreakerRegistry r1 = CircuitBreakerRegistry.of(config);
        CircuitBreaker cb1 = r1.circuitBreaker("clx");

        //获取断路器的状态
        CircuitBreaker.State state = cb1.getState();
        System.out.println(state);


        cb1.onError(0, new RuntimeException());
        System.out.println(cb1.getState());
        cb1.onError(0, new RuntimeException());
        System.out.println(cb1.getState());

        CheckedFunction0<String> supplier = CircuitBreaker.decorateCheckedSupplier(cb1, () -> "hello resilience4j");

        Try<String> map = Try.of(supplier)
                .map(v -> v + "hello world");
        System.out.println(map.isSuccess());
        System.out.println(map.get());
    }


    @Test
    public void tst3() {

        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitRefreshPeriod(Duration.ofMillis(2_000))
                .limitForPeriod(3)
                .timeoutDuration(Duration.ofMillis(2_000))
                .build();

        RateLimiter rateLimiter = RateLimiter.of("clx", config);

        CheckedRunnable checkedRunnable = RateLimiter.decorateCheckedRunnable(rateLimiter, () -> {
            System.out.println(new Date());
        });

        Try.run(checkedRunnable)
                .andThenTry(checkedRunnable)
                .andThenTry(checkedRunnable)
                .andThenTry(checkedRunnable);
    }




    @Test
    public void test4() {
        RetryConfig config = RetryConfig.custom()
                //重试次数
                .maxAttempts(5)
                //重试间隔
                .waitDuration(Duration.ofMillis(500))
                //重试1异常
                .retryExceptions(RuntimeException.class)
                .build();

        Retry retry = Retry.of("clx", config);

        Retry.decorateRunnable(retry, new Runnable() {
            int count = 0;
            @Override
            public void run() {
                //开启了重试功能之后，run 方法执行时，如果抛出异常，会自动触发重试功能
                if (count++<3) {
                    System.out.println(count);
                    throw new RuntimeException();
                } else {
                    System.out.println(count);
                }
            }
        }).run();

    }

}
