/*
 * Copyright (c)
 * Author : Saikat Das
 * Created on : 10/22/19 8:01 PM
 * All rights reserved
 */

package View;


import java.sql.Date;
import java.util.EventObject;

public class FoodOrderEvent extends EventObject {
    private String code;
    private String foodItem;
    private double price;
    private int quantity;
    private double total;
    private Date date;
    public FoodOrderEvent(Object source, String code, String foodItem, double price, int quantity, double total, Date date) {
        super(source);
        this.code = code;
        this.foodItem = foodItem;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
        this.date = date;

    }

    public FoodOrderEvent(Object source, String code, Date date) {
        super(source);
        this.code = code;
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
