package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WhoController {

    @GetMapping("/who")
    public String who() throws Exception {
    	return "Version 2 -> " + java.net.InetAddress.getLocalHost().getHostName();
    }
}