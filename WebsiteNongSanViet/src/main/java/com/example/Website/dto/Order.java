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
    private int id;
    private int cusid;
    private int status;
    private long totalprice;
    private String time;
    public Order() {
    }

    public Order(int id, int cusid, int status) {
        this.id = id;
        this.cusid = cusid;
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public long getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(long totalprice) {
        this.totalprice = totalprice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCusid() {
        return cusid;
    }

    public void setCusid(int cusid) {
        this.cusid = cusid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
