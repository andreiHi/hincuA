package ru.job4j.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.Map;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 10.05.2018.
 * @version $Id$.
 * @since 0.1.
 */
@Controller
public class SpringController {
    private static final Logger LOG = LogManager.getLogger(SpringController.class);

    @PostMapping(value = "/data", produces = "application/json")
    public ResponseEntity<String> data(@RequestBody Map request) throws IOException {
        System.out.println(request);
        System.out.println("|HELLOO");
        return ResponseEntity.ok("ok");
    }
    @GetMapping(value = "/data")
    public ResponseEntity<String> start() {
        return ResponseEntity.ok("ok");
    }
}
