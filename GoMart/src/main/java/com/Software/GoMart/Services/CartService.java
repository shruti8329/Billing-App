package com.Software.GoMart.Services;

import java.time.LocalDate;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Software.GoMart.Entites.CartMaster;
import com.Software.GoMart.Entites.CustomerMaster;
import com.Software.GoMart.Entites.ProductMaster;
import com.Software.GoMart.Repositiory.CartRepository;
import com.Software.GoMart.Repositiory.CustomerRepositiory;
import com.Software.GoMart.Repositiory.ProductRepositiroy;

@Service
public class CartService {

    @Autowired
    private CartRepository repository;

    @Autowired
    private ProductRepositiroy productRepository;

    @Autowired
    private CustomerRepositiory customerRepository;

    private int currentOrderNo = 1; // Tracks the current order number for the day
    private LocalDate lastOrderDate = LocalDate.now(); // Tracks the last date when an order was placed

    public CartMaster addCart(CartMaster cart) {
        // Fetch and set the products from the database
    	System.out.println("carts : "+cart.getProducts());
        Set<ProductMaster> products = cart.getProducts();
        System.out.println("products "+products);
        products = products.stream()
            .map(product -> productRepository.findById(product.getProductid())
            .orElseThrow(() -> new RuntimeException("Product not found")))
            .collect(Collectors.toSet());
        cart.setProducts(products);

        // Fetch and set the customer from the database
        CustomerMaster customer = customerRepository.findById(cart.getCustomer().getCustomer_id())
            .orElseThrow(() -> new RuntimeException("Customer not found"));
        cart.setCustomer(customer);

        // Handle order number and created date
        LocalDate today = LocalDate.now();
        if (!today.equals(lastOrderDate)) {
            currentOrderNo = 1;
            lastOrderDate = today;
        }
        String orderNo = "ORD-" + today.toString().replace("", "") + "_" + currentOrderNo++;
        cart.setOrderNo(orderNo);
        cart.setCreatedon(today);

        // Save and return the cart
        return repository.save(cart);
    }

    public CartMaster updateCart(int cartid, CartMaster cart) {
        // Similar to the original update method, fetch and update all necessary fields
        CartMaster existingCart = repository.findById(cartid)
            .orElseThrow(() -> new RuntimeException("Cart not found"));

        Set<ProductMaster> products = cart.getProducts();
        products = products.stream()
            .map(product -> productRepository.findById(product.getProductid())
            .orElseThrow(() -> new RuntimeException("Product not found")))
            .collect(Collectors.toSet());
        existingCart.setProducts(products);

        CustomerMaster customer = customerRepository.findById(cart.getCustomer().getCustomer_id())
            .orElseThrow(() -> new RuntimeException("Customer not found"));
        existingCart.setCustomer(customer);

        existingCart.setQty(cart.getQty());
        existingCart.setSubTotal(cart.getSubTotal());
        existingCart.setDiscountPercentage(cart.getDiscountPercentage());
        existingCart.setDiscountAmount(cart.getDiscountAmount());
        existingCart.setNetBill(cart.getNetBill());
        existingCart.setOrderNo(cart.getOrderNo());
        existingCart.setPaymentType(cart.getPaymentType());  // Updated here
        existingCart.setCreatedon(cart.getCreatedon());

        return repository.save(existingCart);
    }


    public void deleteCart(int cartid) {
        repository.deleteById(cartid);
    }

    public List<CartMaster> getAllCarts() {
        return repository.findAll();
    }

    public CartMaster getCartById(int cartid) {
        return repository.findById(cartid)
            .orElseThrow(() -> new RuntimeException("Cart not found"));
    }
}
