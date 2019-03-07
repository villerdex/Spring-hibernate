package com;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/Rest")
public class REST {

    @GetMapping("/get")
    public void getAll() {

    }

}
