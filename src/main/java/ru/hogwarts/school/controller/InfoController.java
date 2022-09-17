package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.servise.ServicePort;

@RestController
public class InfoController {
    ServicePort servicePort = new ServicePort();
    @GetMapping("/getPort")
    public String infoPort(){
        return servicePort.getInfoPort();
    }
}
