/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Website.dto;

/**
 *
 * @author Hoang Xoan
 */
public class Cart {
    private int custId;
    private int productId;
    private String name;
    private String image;
    private int quantity;
    private long cost;
    private long totalcost_id;

    public Cart() {
    }

    public Cart(int custId, int productId, String name, String image, int quantity, long cost, long totalcost_id) {
        this.custId = custId;
        this.productId = productId;
        this.name = name;
        this.image = image;
        this.quantity = quantity;
        this.cost = cost;
        this.totalcost_id = totalcost_id;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public long getTotalcost_id() {
        return totalcost_id;
    }

    public void setTotalcost_id(long totalcost_id) {
        this.totalcost_id = totalcost_id;
    }
    
}
