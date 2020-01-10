
/*
 * Copyright (c)
 * Author : Saikat Das
 * Created on : 10/22/19 7:57 PM
 * All rights reserved
 */

package Controller;

import Model.Database;
import Model.FoodOrderInfo;
import Model.MemberInfo;
import Model.Type;
import View.FoodOrderEvent;
import View.MemberEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class Controller {
    Database db = new Database();

    public List<String> loadPerson(String code)  {
        try {
            return db.loadPerson(code);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
    public void connect()  {
        try {
            db.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void disconnect(){
        db.disconnect();
    }
    public void save() {
        try {
            db.save();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void saveFoodOrder() {
        try {
            db.saveFoodOrder();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<String> loadFood(String foodPrice)  {
        try {
            return db.loadFood(foodPrice);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
    public String loadTotalAmount(String code, Date date) {
        try {
            return db.loadTotalAmount(code,date);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
    public DefaultTableModel loadReport(String code, Date date) {
        try {
            return db.loadReport(code, date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    public DefaultComboBoxModel retrieveFood() {
        try {
            connect();
            return db.foodLoad();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public DefaultComboBoxModel retrieveCode()  {
        try {
            return db.codeLoad();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    public void addMember(MemberEvent event){
        String code = event.getCode();
        String type = event.getType();
        String name = event.getName();
        String email = event.getEmail();
        String contactNo = event.getContactNo();
        Type userType = null;
        if (type.equals("General")){
            userType = Type.General;
        }else if (type.equals("VIP"))
            userType = Type.VIP;
        else if (type.equals("Guest"))
            userType = Type.Guest;
        MemberInfo member = new MemberInfo(code,name,email,contactNo,userType);
        db.addMember(member);
    }
    public void addOrder(FoodOrderEvent event){
        String code = event.getCode();
        String foodItem = event.getFoodItem();
        double price = event.getPrice();
        int quantity = event.getQuantity();
        double total = event.getTotal();
        Date date = event.getDate();
        FoodOrderInfo memberOrderInfo = new FoodOrderInfo(code, foodItem,  price, quantity, total, date);
        db.addFoodOrder(memberOrderInfo);
    }

}
