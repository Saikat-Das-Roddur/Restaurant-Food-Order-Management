/*
 * Copyright (c)
 * Author : Saikat Das
 * Created on : 10/22/19 8:02 PM
 * All rights reserved
 */

package View;

import Controller.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomeLayout extends JPanel{
    private JLabel entryMember, foodOrder, reportview;
    private MemberEntrylayout member = new MemberEntrylayout();
    private FoodOrderLayout  order = new FoodOrderLayout();
    private ViewReportLayout report = new ViewReportLayout();
    private Controller controller;
    public HomeLayout(){
        entryMember = new JLabel("<HTML><U>Member Entry</U></HTML>");
        entryMember.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        foodOrder = new JLabel("<HTML><U>Order For Food</U></HTML>");
        foodOrder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        reportview = new JLabel("<HTML><U>View Report</U></HTML>");
        reportview.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        controller = new Controller();

        entryMember.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                connect();
                member.setLocationRelativeTo(null);
                member.setVisible(true);
                order.setVisible(false);
                report.setVisible(false);
            }
        });
        foodOrder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                connect();
                refreshCodeBox();
                order.setLocationRelativeTo(null);
                order.setVisible(true);
                member.setVisible(false);
                report.setVisible(false);
            }
        });
        reportview.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                connect();
                report.setCodeListener(new ComboBoxListener() {
                    @Override
                    public void refreshEventOccurred() {

                    }
                });
                report.setLocationRelativeTo(null);
                report.setVisible(true);
                order.setVisible(false);
                member.setVisible(false);


            }
        });
        member.setMemberListener(new MemberLayoutListener() {
            @Override
            public void memberEventOccurred(MemberEvent e) {
                controller.addMember(e);
                    controller.save();
                    JOptionPane.showMessageDialog(member,"This person Successfully Added",
                            "Successful",JOptionPane.INFORMATION_MESSAGE);
            }

        });


        setLayout(new GridBagLayout());
        layoutComponents();
        TitledBorder innerBorder = BorderFactory.createTitledBorder("Home");
        Border outerBorder = BorderFactory.createEmptyBorder(15,15,15,15);
        setBorder(BorderFactory.createCompoundBorder(outerBorder,innerBorder));

    }
    private void refreshCodeBox(){
        order.setOrderListener(new FoodOrderLayoutListener() {
            @Override
            public void foodOrderEventOccurred(FoodOrderEvent e) {
                controller.addOrder(e);
                controller.saveFoodOrder();
                JOptionPane.showMessageDialog(order,"This order has been saved",
                            "Successful",JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
    private void connect() {
        try {
            controller.connect();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(member,"Problem to connect Database",
                    "Database connection problem",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void layoutComponents() {
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 1;
        gc.insets = new Insets(0,0,10,0);
        add(entryMember,gc);
        gc.gridx = 0;
        gc.gridy ++;
        gc.insets = new Insets(0,0,10,0);
        add(foodOrder,gc);
        gc.gridx = 0;
        gc.gridy ++;
        add(reportview,gc);
    }

}
