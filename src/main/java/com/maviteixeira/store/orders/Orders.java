package com.maviteixeira.store.orders;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class Orders {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getOrders() {
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getOrder(@PathVariable("id") String id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createOrder() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "{id}/payment", method = RequestMethod.POST)
    public ResponseEntity createPayment() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateOrder(@PathVariable("id") String id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/refund", method = RequestMethod.PUT)
    public ResponseEntity refundOrder(@PathVariable("id") String id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{orderId}/items/{itemId}/refund", method = RequestMethod.PUT)
    public ResponseEntity refundOrder(@PathVariable("orderId") String orderId,
                                      @PathVariable("itemId") String itemId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
