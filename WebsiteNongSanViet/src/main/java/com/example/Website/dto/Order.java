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
public class Order {
    private int cusId;
    private boolean status;
    private int amount;
    private long totalprice;
    public Order() {
    }

    public Order( int cusId, boolean status, int amount, long totalprice) {
        this.cusId = cusId;
        this.status = status;
        this.amount = amount;
        this.totalprice = totalprice;
    }

    public int getCusId() {
        return cusId;
    }

    public void setCusId(int cusId) {
        this.cusId = cusId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(long totalprice) {
        this.totalprice = totalprice;
    }

}
