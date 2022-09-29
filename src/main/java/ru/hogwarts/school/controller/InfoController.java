package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.servise.ServicePort;

@RestController
public class InfoController {
    private final ServicePort servicePort;

    public InfoController(ServicePort servicePort) {
        this.servicePort = servicePort;
    }

    @GetMapping("/getPort")
    public String infoPort() {
        return servicePort.getInfoPort();
    }

    @GetMapping("integerValue")
    public Integer integerValue(){
        return servicePort.getInfoIntegerValue();
    }

}
