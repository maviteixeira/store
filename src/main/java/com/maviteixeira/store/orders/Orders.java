package com.maviteixeira.store.orders;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class Orders {

    @GetMapping
    public ResponseEntity getOrders() {
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getOrder(@PathVariable("id") String id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createOrder() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "{id}/payment")
    public ResponseEntity createPayment() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateOrder(@PathVariable("id") String id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/refund")
    public ResponseEntity refundOrder(@PathVariable("id") String id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{orderId}/items/{itemId}/refund")
    public ResponseEntity refundOrder(@PathVariable("orderId") String orderId,
                                      @PathVariable("itemId") String itemId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
