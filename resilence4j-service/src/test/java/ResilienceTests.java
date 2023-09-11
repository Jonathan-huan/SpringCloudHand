
import com.hzh.eureka.Main;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.vavr.control.Try;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Main.class)
public class ResilienceTests {
    @Test
    public void testCircuitBreaker() {
        // 创建断路器配置
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(50) // 故障率阈值
                .waitDurationInOpenState(Duration.ofSeconds(2)) // 断路器打开后等待的时间
                .build();

        // 创建断路器
        CircuitBreaker circuitBreaker = CircuitBreaker.of("myCircuitBreaker", circuitBreakerConfig);

        int totalCalls = 100; // 总调用次数

        // 使用断路器执行操作，模拟一定失败率触发自动熔断
        for (int i = 0; i < totalCalls; i++) {
            Try<String> result = Try.of(() ->
                    CircuitBreaker.decorateSupplier(circuitBreaker, () -> {
                        // 模拟一定失败率
                        if (new Random().nextInt(100) < 40) {
                            throw new RuntimeException("Service Unavailable");
                        }
                        return "Success";
                    }).get()
            );

            if (result.isFailure()) {
                System.out.println("Call failed: " + result.getCause().getMessage());
            }
        }

        // 此时断路器可能已经打开
        System.out.println("Circuit Breaker State: " + circuitBreaker.getState());
    }
}
