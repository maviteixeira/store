package com.maviteixeira.store.items;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
public class ItemsController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getItens() {
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }

}
