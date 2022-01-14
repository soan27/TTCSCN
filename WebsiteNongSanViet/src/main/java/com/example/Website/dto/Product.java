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
public class Product {
    public int id;
    public String name;
    public int real_price;
    public int percent_discount;
    public int cateId;
    public boolean isHot;
    public String desc;
    public String brief_desc;
    public int quantityProd;
    public String image;

    public Product() {
    }

    public Product(int id, String name, int real_price, int percent_discount, int cateId, boolean isHot, String desc, String brief_desc, int quantityProd, String image) {
        this.id = id;
        this.name = name;
        this.real_price = real_price;
        this.percent_discount = percent_discount;
        this.cateId = cateId;
        this.isHot = isHot;
        this.desc = desc;
        this.brief_desc = brief_desc;
        this.quantityProd = quantityProd;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReal_price() {
        return real_price;
    }

    public void setReal_price(int real_price) {
        this.real_price = real_price;
    }

    public int getPercent_discount() {
        return percent_discount;
    }

    public void setPercent_discount(int percent_discount) {
        this.percent_discount = percent_discount;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public boolean isIsHot() {
        return isHot;
    }

    public void setIsHot(boolean isHot) {
        this.isHot = isHot;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getBrief_desc() {
        return brief_desc;
    }

    public void setBrief_desc(String brief_desc) {
        this.brief_desc = brief_desc;
    }

    public int getQuantityProd() {
        return quantityProd;
    }

    public void setQuantityProd(int quantityProd) {
        this.quantityProd = quantityProd;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    
}
