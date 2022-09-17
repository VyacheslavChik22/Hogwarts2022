package ru.hogwarts.school.servise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ServicePort {
    Logger logger = LoggerFactory.getLogger(ServicePort.class);

    @Value("${server.port}")
    private String port;

    public String getInfoPort() {
        logger.debug("Request method getInfoPort :");

        return port;
    }

}
