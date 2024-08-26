package com.Software.GoMart.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Software.GoMart.Entites.CartMaster;
import com.Software.GoMart.Entites.ReceiptMaster;
import com.Software.GoMart.Services.CartService;
import com.Software.GoMart.Services.ReceiptService;


@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:5173")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ReceiptService receiptService;

    @PostMapping("/addCart")
    public ResponseEntity<CartMaster> addCart(@RequestBody CartMaster cart) {
        CartMaster newCart = cartService.addCart(cart);

        // Save receipt after cart is successfully saved
        receiptService.saveReceiptForCart(newCart);

        return ResponseEntity.status(HttpStatus.CREATED).body(newCart);
    }

    @PutMapping("/updateCart/{cartid}")
    public ResponseEntity<CartMaster> updateCart(@PathVariable int cartid, @RequestBody CartMaster cart) {
        CartMaster updatedCart = cartService.updateCart(cartid, cart);
        return ResponseEntity.ok(updatedCart);
    }
    
 // New method to get all receipts
    @GetMapping("/getAllReceipts")
    public ResponseEntity<List<ReceiptMaster>> getAllReceipts() {
        List<ReceiptMaster> receipts = receiptService.getAllReceipts();
        return ResponseEntity.ok(receipts);
    }
    
//    New method to get receipt by id
    @GetMapping("/receipt/{id}")
    public ResponseEntity<ReceiptMaster> getReceiptById(@PathVariable int id) {
        try {
            ReceiptMaster receipt = receiptService.getReceiptById(id);
            return ResponseEntity.ok(receipt);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    } 

    @DeleteMapping("/deleteCart/{cartid}")
    public ResponseEntity<Void> deleteCart(@PathVariable int cartid) {
        cartService.deleteCart(cartid);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getAllCarts")
    public ResponseEntity<List<CartMaster>> getAllCarts() {
        List<CartMaster> carts = cartService.getAllCarts();
        return ResponseEntity.ok(carts);
    }

    @GetMapping("/getCart/{cartid}")
    public ResponseEntity<CartMaster> getCartById(@PathVariable int cartid) {
        CartMaster cart = cartService.getCartById(cartid);
        return ResponseEntity.ok(cart);
    }
}
