package com.Software.GoMart.Entites;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "cartmaster")
public class CartMaster{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerMaster customer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "cart_product",
        joinColumns = @JoinColumn(name = "cartid"),
        inverseJoinColumns = @JoinColumn(name = "productid")
    )
    private Set<ProductMaster> products;

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

    @Column(name = "payment_type")  // Updated column name
    private String paymentType;

    @Column(name = "createdon")
    private LocalDate createdon;

    public CartMaster() {
        super();
    }

    public CartMaster(int cartid, Set<ProductMaster> products, int qty, double subTotal, double discountPercentage,
                      double discountAmount, double netBill, String orderNo, String paymentType, LocalDate createdon, CustomerMaster customer) {
        super();
        this.cartid = cartid;
        this.products = products;
        this.qty = qty;
        this.subTotal = subTotal;
        this.discountPercentage = discountPercentage;
        this.discountAmount = discountAmount;
        this.netBill = netBill;
        this.orderNo = orderNo;
        this.paymentType = paymentType;
        this.createdon = createdon;
        this.customer = customer;
    }

    // Getters and Setters

    public int getCartid() {
        return cartid;
    }

    public void setCartid(int cartid) {
        this.cartid = cartid;
    }

    public Set<ProductMaster> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductMaster> products) {
        this.products = products;
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

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public LocalDate getCreatedon() {
        return createdon;
    }

    public void setCreatedon(LocalDate createdon) {
        this.createdon = createdon;
    }

    public CustomerMaster getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerMaster customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "CartEntity [cartid=" + cartid + ", products=" + products + ", qty=" + qty + ", subTotal=" + subTotal
                + ", discountPercentage=" + discountPercentage + ", discountAmount=" + discountAmount + ", netBill="
                + netBill + ", orderNo=" + orderNo + ", paymentType=" + paymentType + ", createdon=" + createdon + ", customer="
                + customer + "]";
    }
}
