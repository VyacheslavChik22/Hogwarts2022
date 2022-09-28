package ru.hogwarts.school.servise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class ServicePort {
    private final Logger logger = LoggerFactory.getLogger(ServicePort.class);

    @Value("${server.port}")
    private String port;

    public String getInfoPort() {
        logger.debug("Request method getInfoPort :");
        return port;
    }

    public Integer getInfoIntegerValue() {
        Long start = System.currentTimeMillis();
        int sum1 = Stream.iterate(1, a -> a + 1).limit(1_000_000).reduce(0, (a, b) -> a + b);
        Long end = System.currentTimeMillis();
        logger.info("Разница во времени для одного потока составляет: " + (end - start));

        start = System.currentTimeMillis();
        int sum = Stream.iterate(1, a -> a + 1).limit(1_000_000).parallel().reduce(0, (a, b) -> a + b);
        end = System.currentTimeMillis();
        logger.info("Разница во времени для нескольких потоков составляет: " + (end - start));

        return sum;
    }
}
