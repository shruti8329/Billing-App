package com.Software.GoMart.Entites;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "receipt")
public class ReceiptMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int receiptId;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerMaster customer;

    @Column(name = "product_names")
    private String productNames;

    @Column(name = "qty")
    private int qty;

    @Column(name = "sub_total")
    private double subTotal;

    @Column(name = "discount_percentage")
    private double discountPercentage;

    @Column(name = "discount_amount")
    private double discountAmount;

    @Column(name = "net_bill")
    private double netBill;

    @Column(name = "order_no")
    private String orderNo;

    @Column(name = "created_on")
    private LocalDate createdOn;

    // Getters and Setters

    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }

    public CustomerMaster getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerMaster customer) {
        this.customer = customer;
    }

    public String getProductNames() {
        return productNames;
    }

    public void setProductNames(String productNames) {
        this.productNames = productNames;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getNetBill() {
        return netBill;
    }

    public void setNetBill(double netBill) {
        this.netBill = netBill;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }
}
