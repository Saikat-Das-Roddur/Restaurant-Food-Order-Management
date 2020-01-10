/*
 * Copyright (c)
 * Author : Saikat Das
 * Created on : 10/22/19 7:59 PM
 * All rights reserved
 */


package Model;

import java.sql.Date;

public class FoodOrderInfo {
    private String code;
    private String foodItem;
    private double price;
    private int quantity;
    private double total;
    private Date date;
    public FoodOrderInfo(String code, String foodItem, double price, int quantity, double total, Date date){
        this.code = code;
        this.foodItem = foodItem;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
        this.date = date;
    }

    public FoodOrderInfo(String code, double total, Date date) {
        this.code = code;
        this.total = total;
        this.date = date;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }
    public String getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(String foodItem) {
        this.foodItem = foodItem;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}

