//package com.ecommerce.ecommerce_application.entity;
//
//
//
//import jakarta.persistence.*;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Table(name = "customer_orders")
//public class CustomerOrderE {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "order_id")
//    private Long orderId;
//
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "customer_id", nullable = false)
//    private CustomerE customer;
//
//    @Column(name = "total_amount", nullable = false)
//    private BigDecimal totalAmount;
//
//    @Column(name = "order_status", nullable = false)
//    private String orderStatus;
//
//    @OneToMany(
//        mappedBy = "order",
//        cascade = CascadeType.ALL,
//        orphanRemoval = true
//    )
//    private List<OrderItem> items = new ArrayList<>();
//
//    public CustomerOrderE() {
//    }
//
//    public void addItem(OrderItem item) {
//        items.add(item);
//        item.setOrder(this);
//    }
//
//    public Long getOrderId() {
//        return orderId;
//    }
//
//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }
//
//    public BigDecimal getTotalAmount() {
//        return totalAmount;
//    }
//
//    public void setTotalAmount(BigDecimal totalAmount) {
//        this.totalAmount = totalAmount;
//    }
//
//    public String getOrderStatus() {
//        return orderStatus;
//    }
//
//    public void setOrderStatus(String orderStatus) {
//        this.orderStatus = orderStatus;
//    }
//
//    public List<OrderItem> getItems() {
//        return items;
//    }
//}