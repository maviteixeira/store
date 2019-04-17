package com.maviteixeira.store.items;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
public class ItemsController {

    @GetMapping
    public ResponseEntity getItens() {
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }

}
