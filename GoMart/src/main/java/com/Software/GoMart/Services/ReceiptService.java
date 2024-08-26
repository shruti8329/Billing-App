package com.Software.GoMart.Services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.Software.GoMart.Entites.CartMaster;
import com.Software.GoMart.Entites.ProductMaster;
import com.Software.GoMart.Entites.ReceiptMaster;
import com.Software.GoMart.Repositiory.ReceiptRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;

    public ReceiptMaster saveReceiptForCart(CartMaster cart) {
        ReceiptMaster receipt = new ReceiptMaster();

        // Set customer details
        receipt.setCustomer(cart.getCustomer());

        // Populate product names
        String productNames = cart.getProducts().stream()
                                  .map(ProductMaster::getProductname)
                                  .collect(Collectors.joining(", "));
        receipt.setProductNames(productNames);

        // Populate other receipt details from cart
        receipt.setQty(cart.getQty());
        receipt.setSubTotal(cart.getSubTotal());
        receipt.setDiscountPercentage(cart.getDiscountPercentage());
        receipt.setDiscountAmount(cart.getDiscountAmount());
        receipt.setNetBill(cart.getNetBill());
        receipt.setOrderNo(cart.getOrderNo());
        receipt.setCreatedOn(cart.getCreatedon());

        // Save receipt to database
        return receiptRepository.save(receipt);
    }

    public ReceiptMaster getReceiptById(int id) {
        return receiptRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receipt not found"));
    }

    public List<ReceiptMaster> getAllReceipts() {
        return receiptRepository.findAll();
    }
}
