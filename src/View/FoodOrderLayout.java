/*
 * Copyright (c)
 * Author : Saikat Das
 * Created on : 10/22/19 7:58 PM
 * All rights reserved
 */

package View;


import Controller.Controller;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class FoodOrderLayout extends JFrame {
    private JTextField nameField, emailField, contactField, priceField, quantityField, typeField;
    private JComboBox codeBox;
    private JComboBox foodItem;
    private JButton saveButton;
    private FoodOrderEvent event;
    private FoodOrderLayoutListener orderListener;
    private JDateChooser datePicker;
    private double totalAmount;
    private Controller controller = new Controller();
    private List<String> member;
    private List<String> food;

    public FoodOrderLayout() {
        super("Order for Food");

        System.out.println("Working food order!!");
        nameField = new JTextField(12);
        emailField = new JTextField(12);
        contactField = new JTextField(12);
        priceField = new JTextField(12);
        quantityField = new JTextField(12);
        typeField = new JTextField(12);
        codeBox = new JComboBox();
        foodItem = new JComboBox();

        saveButton = new JButton("Save");
        datePicker = new JDateChooser(new Date(), "dd/MM/yyyy");
        datePicker.setMinSelectableDate(new Date());
        totalAmount = 0;

        setCodeBox();
        setFoodItem();

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String code = codeBox.getSelectedItem().toString();
                String food = foodItem.getSelectedItem().toString();
                if (code != null && food != null) {
                    try {
                        double price = Double.parseDouble(priceField.getText());
                        int quantity = Integer.parseInt(quantityField.getText());
                        String d1 = ((JTextField) datePicker.getDateEditor().getUiComponent()).getText();
                        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        java.sql.Date date = java.sql.Date.valueOf(LocalDate.parse(d1, df));
                        double totalPrice = price * quantity;
                        totalAmount += totalPrice;
                        System.out.println("Total amount is : " + totalAmount);
                        event = new FoodOrderEvent(this, code, food, totalPrice, quantity, totalAmount, date);
                        if (orderListener != null) {
                            orderListener.foodOrderEventOccurred(event);
                        }
                    } catch (NumberFormatException e1) {

                    }

                }
            }
        });
        setCodeBox();
        layoutComponent();
        setSize(480, 500);

    }

    private void setFieldEmpty() {
        nameField.setText("");
        emailField.setText("");
        contactField.setText("");
        typeField.setText("");
        quantityField.setText("");
        priceField.setText("");
    }


    private void setCodeBox() {

        codeBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    if (codeBox.getSelectedIndex() != 0 ) {
                    member = controller.loadPerson(codeBox.getSelectedItem().toString());
                        nameField.setText(member.get(0));
                        emailField.setText(member.get(1));
                        contactField.setText(member.get(2));
                        typeField.setText(member.get(3));
                }else {foodItem.setSelectedIndex(0);
                    setFieldEmpty();}
            }
            }


        });
    }

    private void setFoodItem() {
        foodItem.setModel(controller.retrieveFood());
        foodItem.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    if (foodItem.getSelectedIndex() != 0) {
                        food = controller.loadFood(foodItem.getSelectedItem().toString());
                                    priceField.setText(food.get(0));
                    }else {
                        codeBox.setSelectedIndex(0);
                        setFieldEmpty();
                    }

                }
            }
        });

    }

    public void setOrderListener(FoodOrderLayoutListener orderListener) {
        this.orderListener = orderListener;
        codeBox.setModel(controller.retrieveCode());
        setFieldEmpty();
        foodItem.setSelectedIndex(0);
    }

    private void layoutComponent() {

        nameField.setEditable(false);
        emailField.setEditable(false);
        contactField.setEditable(false);
        typeField.setEditable(false);
        priceField.setEditable(false);
        ((JTextFieldDateEditor) datePicker.getDateEditor()).setEditable(false);
        Font font1 = new Font("SansSerif", Font.BOLD, 14);
        codeBox.setFont(font1);
        nameField.setFont(font1);
        emailField.setFont(font1);
        contactField.setFont(font1);
        typeField.setFont(font1);
        foodItem.setFont(font1);
        priceField.setFont(font1);
        quantityField.setFont(font1);
        datePicker.setFont(font1);
        nameField.setHorizontalAlignment(JTextField.CENTER);
        emailField.setHorizontalAlignment(JTextField.CENTER);
        contactField.setHorizontalAlignment(JTextField.CENTER);
        typeField.setHorizontalAlignment(JTextField.CENTER);
        priceField.setHorizontalAlignment(JTextField.CENTER);
        quantityField.setHorizontalAlignment(JTextField.CENTER);
        codeBox.setPreferredSize(nameField.getPreferredSize());
        codeBox.setMaximumRowCount(3);
        foodItem.setPreferredSize(codeBox.getPreferredSize());
        foodItem.setMaximumRowCount(3);
        datePicker.setPreferredSize(nameField.getPreferredSize());

        GridBagConstraints gc = new GridBagConstraints();
        JPanel panel = new JPanel();
        Insets btmPadding = new Insets(0, 0, 30, 15);
        Insets rightPadding = new Insets(0, 0, 0, 15);
        EmptyBorder emptyBorder = new EmptyBorder(50, 10, 10, 10);
        TitledBorder innerBorder = BorderFactory.createTitledBorder("Order for Food");
        Border border = BorderFactory.createCompoundBorder(innerBorder, emptyBorder);
        Border outerBorder = BorderFactory.createEmptyBorder(25, 25, 25, 25);
        panel.setBorder(BorderFactory.createCompoundBorder(outerBorder, border));
        panel.setLayout(new GridBagLayout());
        ///////Code//////
        //

        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = .1;
        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = rightPadding;
        panel.add(new JLabel("Code"), gc);
        gc.gridx = 1;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(codeBox, gc);
        ///////Name//////
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = .1;
        ;
        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Name"), gc);
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(nameField, gc);
        ///////Email//////
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = .1;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Email"), gc);
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(emailField, gc);
        ///////Contact//////
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = .1;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Contact No."), gc);
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(contactField, gc);
        ///////Type//////
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = .1;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = btmPadding;
        panel.add(new JLabel("Type"), gc);
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(typeField, gc);
        /////Food Item//////
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = .1;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = rightPadding;
        panel.add(new JLabel("Food Item"), gc);
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(foodItem, gc);
        /////Unit Price//////
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = .1;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Unit Price"), gc);
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(priceField, gc);
        /////Quantity//////
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = .1;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Quantity"), gc);
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(quantityField, gc);
        /////DatePicker//////
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = .1;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Date"), gc);
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(datePicker, gc);
        /////Button/////
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 2;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        panel.add(saveButton, gc);

        panel.setBackground(Color.RED);
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
    }
}
