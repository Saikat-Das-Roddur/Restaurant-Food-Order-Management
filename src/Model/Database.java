/*
 * Copyright (c)
 * Author : Saikat Das
 * Created on : 10/22/19 7:58 PM
 * All rights reserved
 */

package Model;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.sql.Date;
import java.util.*;
public class Database {
    private List<MemberInfo> memberInfos = new ArrayList<>();
    private List<FoodOrderInfo> foodOrderInfos = new ArrayList<>();

    Connection connection;

    public Database() {
        memberInfos = new LinkedList<MemberInfo>();
        foodOrderInfos = new LinkedList<FoodOrderInfo>();
    }

    public void addMember(MemberInfo member) {
        memberInfos.add(member);
    }
    public void addFoodOrder(FoodOrderInfo foodOrderInfo) {
        foodOrderInfos.add(foodOrderInfo);
    }

    public void connect() throws Exception {
        if (connection != null) return;
        String url = "jdbc:mysql://localhost:3306/restaurant";
        connection = DriverManager.getConnection(url, "root", "Newpassword");
        System.out.println("Connected: " + connection);
    }

    public void disconnect() {
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public void save() throws SQLException {
        ResultSet resultSet;
        String sqlStatement = "select count(*) as count from restaurant.member where code=?";
        PreparedStatement checkSQL = connection.prepareStatement(sqlStatement);
        String insertSql = "insert into restaurant.member (code, name, email, contactno, usertype) values(?,?,?,?,?)";
        PreparedStatement insert = connection.prepareStatement(insertSql);

        for (MemberInfo member : memberInfos) {
            String code = member.getCode();
            String name = member.getName();
            String email = member.getEmail();
            String contactNo = member.getContactNo();
            Type type = member.getType();
            System.out.println("I'm here!!");
            checkSQL.setString(1, code);
            resultSet = checkSQL.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            if (count == 0) {
                System.out.println("Inserting person with id: " + code);
                int col = 1;
                insert.setString(col++, code);
                insert.setString(col++, name);
                insert.setString(col++, email);
                insert.setString(col++, contactNo);
                insert.setString(col++, type.name());
                insert.executeUpdate();
            }

        }
        insert.close();
        checkSQL.close();
    }

    public DefaultComboBoxModel codeLoad() throws Exception {
        connect();
        DefaultComboBoxModel code = new DefaultComboBoxModel();
        code.addElement("Select Code");
        String loadSql = "select * from restaurant.member";
        Statement load = connection.createStatement();
        ResultSet resultSet = load.executeQuery(loadSql);
        while (resultSet.next()) {
            code.addElement(resultSet.getString(1));
            //System.out.println(resultSet.getString(1)+"...."+resultSet.getString(2));
        }
        resultSet.close();
        load.close();
        return code;
    }

    public DefaultComboBoxModel foodLoad() throws Exception {
        connect();
        DefaultComboBoxModel food = new DefaultComboBoxModel();
        food.addElement("Select Food");
        String loadSql = "select * from restaurant.food";
        Statement load = connection.createStatement();
        ResultSet resultSet = load.executeQuery(loadSql);
        while (resultSet.next()) {
            food.addElement(resultSet.getString(1));
           // System.out.println(resultSet.getString(1) + "...." + resultSet.getString(2));
        }
        resultSet.close();
        load.close();
        return food;
    }

    public List<String> loadFood(String price) throws SQLException {
        List<String> food = new ArrayList<>();
        String loadSql = "select foodprice from restaurant.food WHERE fooditem=" + "'" + price + "'";
        Statement load = connection.createStatement();
        ResultSet resultSet = load.executeQuery(loadSql);
        while (resultSet.next()) {
            food.add(resultSet.getString("foodprice"));
        }
//        for (String i: food) {
//            System.out.println(i);
//        }
        load.close();
        resultSet.close();
        return food;
    }

    public String loadTotalAmount(String code,Date date) throws SQLException {
        foodOrderInfos.clear();
        String totalAmount = "";
        String loadSql = "select totalamount from restaurant.foodorder WHERE code=" + "'" + code + "' and orderdate="+ "'" + date + "'";
        Statement load = connection.createStatement();
        ResultSet resultSet = load.executeQuery(loadSql);
        while (resultSet.next()) {
            totalAmount = (resultSet.getString("totalamount"));
        }
//        for (String i: food) {
//            System.out.println(i);
//        }
        load.close();
        resultSet.close();
        return totalAmount;
    }

    public List<String> loadPerson(String code) throws SQLException {
        List<String> memberInfoList = new ArrayList<>();
        String loadSql = "select name,email,contactno,usertype from restaurant.member WHERE code=" + "'" + code + "'";
        Statement load = connection.createStatement();
        ResultSet resultSet = load.executeQuery(loadSql);
        while (resultSet.next()) {
            memberInfoList.add(resultSet.getString("name"));
            memberInfoList.add(resultSet.getString("email"));
            memberInfoList.add(resultSet.getString("contactno"));
            memberInfoList.add(resultSet.getString("usertype"));
        }
        load.close();
        resultSet.close();
        return memberInfoList;
    }

    public void saveFoodOrder() throws SQLException {
        String insertSql = "insert into restaurant.foodorder (code, fooditem, foodquantity, totalprice, totalamount,orderdate) values(?,?,?,?,?,?)";
        PreparedStatement insert = connection.prepareStatement(insertSql);
        for (FoodOrderInfo orderInfo:foodOrderInfos) {
            if (foodOrderInfos.size()==1){
                int col=1;
                String code=orderInfo.getCode();
                String foodItem = orderInfo.getFoodItem();
                int foodQuantity = orderInfo.getQuantity();
                double totalPrice = orderInfo.getPrice();
                double totalAmount = orderInfo.getTotal();
                java.sql.Date orderDate = orderInfo.getDate();
                insert.setString(col++,code);
                insert.setString(col++,foodItem);
                insert.setInt(col++,foodQuantity);
                insert.setDouble(col++,totalPrice);
                insert.setDouble(col++,totalAmount);
                insert.setDate(col++,orderDate);
                insert.executeUpdate();
            }
            foodOrderInfos.clear();
        }
        insert.close();

    }
    public DefaultTableModel loadReport(String code, Date date) throws Exception {
        connect();
        DefaultTableModel tableModel = new DefaultTableModel(
                new String[]{"Item Name","Quantity","Total Price"}, 0);
        String loadSql = "select fooditem,foodquantity,totalprice,totalamount from restaurant.foodorder WHERE code=" + "'" + code + "' AND orderdate="+ "'" + date + "'";
        Statement load = connection.createStatement();
        ResultSet resultSet = load.executeQuery(loadSql);
        while (resultSet.next()){
            String itemName = resultSet.getString("fooditem");
            int itemQuantity = resultSet.getInt("foodquantity");
            double totalPrice = resultSet.getDouble("totalprice");
            System.out.println(" Total Price "+totalPrice);
            tableModel.addRow(new Object[]{itemName,itemQuantity,totalPrice});
        }

        System.out.println(code+" : "+ date);
        return tableModel;
    }

    public List<MemberInfo> getMember() {
        return memberInfos;
    }
    public List<FoodOrderInfo> getFoodOrderInfos() {
        return foodOrderInfos;
    }
}

