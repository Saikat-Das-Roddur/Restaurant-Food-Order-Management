/*
 * Copyright (c)
 * Author : Saikat Das
 * Created on : 10/22/19 7:56 PM
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
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ViewReportLayout extends JFrame {
    private JTextField totalAmountField,nameField,emailField,contactField,typeField;
    private JComboBox codeBox;
    private JButton showButton;
    private JPanel tablePanel;
    private JDateChooser datePicker;
    private ComboBoxListener listener;
    private JTable reportTable;
    private Controller controller = new Controller();
    private List<String> member;
    public ViewReportLayout() {
        super("View Report");
        nameField = new JTextField(12);
        emailField = new JTextField();
        contactField = new JTextField(12);
        totalAmountField = new JTextField(12);
        typeField = new JTextField(12);
        showButton = new JButton("Show");
        codeBox = new JComboBox();
        reportTable = new JTable();
        datePicker = new JDateChooser();
        datePicker.setDateFormatString("dd/MM/yyyy");
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    String code = codeBox.getSelectedItem().toString();
                    String d1  = ((JTextField)datePicker.getDateEditor().getUiComponent()).getText();
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    java.sql.Date date = java.sql.Date.valueOf(LocalDate.parse(d1,df));
                if (code!=null){
                    if (listener!=null){
                        reportTable.setModel(controller.loadReport(code,date));
                        totalAmountField.setText(controller.loadTotalAmount(code, date));
                    }
                }
            }
        });
        setCodeBox();
        layoutComponents();
        setMinimumSize(new Dimension(480,500));
    }

    private void setFieldEmpty(){
        nameField.setText("");
        emailField.setText("");
        contactField.setText("");
        typeField.setText("");
        totalAmountField.setText("");
        reportTable.setModel(new DefaultTableModel());
    }

    private void setCodeBox() {
        codeBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()==1){
                    if (codeBox.getSelectedIndex()!=0){
                        member = controller.loadPerson(codeBox.getSelectedItem().toString());
                        nameField.setText(member.get(0));
                        emailField.setText(member.get(1));
                        contactField.setText(member.get(2));
                        typeField.setText(member.get(3));
                    }
                }

            }
        });

    }
    private void layoutComponents() {

        nameField.setEditable(false);
        emailField.setEditable(false);
        contactField.setEditable(false);
        typeField.setEditable(false);
        totalAmountField.setEditable(false);

        Font font1 = new Font("SansSerif", Font.BOLD, 14);
        codeBox.setFont(font1);
        nameField.setFont(font1);
        emailField.setFont(font1);
        contactField.setFont(font1);
        typeField.setFont(font1);
        datePicker.setFont(font1);
        totalAmountField.setFont(font1);
        codeBox.setAlignmentX(JComboBox.CENTER_ALIGNMENT);
        nameField.setHorizontalAlignment(JTextField.CENTER);
        emailField.setHorizontalAlignment(JTextField.CENTER);
        contactField.setHorizontalAlignment(JTextField.CENTER);
        typeField.setHorizontalAlignment(JTextField.CENTER);
        totalAmountField.setHorizontalAlignment(JTextField.CENTER);
        emailField.setPreferredSize(nameField.getPreferredSize());
        codeBox.setPreferredSize(nameField.getPreferredSize());
        datePicker.setPreferredSize(nameField.getPreferredSize());
        ((JTextFieldDateEditor)datePicker.getDateEditor()).setEditable(false);
        codeBox.setMaximumRowCount(3);
        reportTable.setBackground(Color.WHITE);
        reportTable.setPreferredScrollableViewportSize(new Dimension(100,45));
        //reportTable.setBorder(new EmptyBorder(0,100,0,100));

        GridBagConstraints gc = new GridBagConstraints();
        JPanel panel = new JPanel();
        tablePanel = new JPanel(new BorderLayout());
        Insets btmPadding = new Insets(0,0,10,15);
        Insets rightPadding = new Insets(0,0,5,15);
        EmptyBorder emptyBorder = new EmptyBorder(50,50,10,50);
        TitledBorder innerBorder = BorderFactory.createTitledBorder("View Report");
        Border border = BorderFactory.createCompoundBorder(innerBorder,emptyBorder);
        Border outerBorder = BorderFactory.createEmptyBorder(25,35,25,35);
        //tablePanel.add(reportTable);
        tablePanel.setBackground(Color.GREEN);
        tablePanel.add(reportTable);
        tablePanel.setBorder(new EmptyBorder(10,10,10,10));
        //tablePanel.setVisible(false);
        reportTable.setEnabled(false);
        panel.setBorder(BorderFactory.createCompoundBorder(outerBorder,border));
        //reportTable.setBorder(BorderFactory.createEtchedBorder());
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.GREEN);
        ///////Code//////
        //
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = .1;
        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = rightPadding;
        panel.add(new JLabel("Code"),gc);
        gc.gridx = 1;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(codeBox,gc);
        ///////Name//////
        gc.gridy ++;
        gc.weightx = 1;
        gc.weighty = .1;;
        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Name"),gc);
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(nameField,gc);
        ///////Email//////
        gc.gridy ++;
        gc.weightx = 1;
        gc.weighty = .1;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Email"),gc);
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(emailField,gc);
        ///////Contact//////
        gc.gridy ++;
        gc.weightx = 1;
        gc.weighty = .1;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Contact No."),gc);
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(contactField,gc);
        ///////Type//////
        gc.gridy ++;
        gc.weightx = 1;
        gc.weighty = .1;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = btmPadding;
        panel.add(new JLabel("Type"),gc);
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(typeField,gc);
        /////DatePicker//////
        gc.gridy ++;
        gc.weightx = 1;
        gc.weighty = .1;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = rightPadding;
        panel.add(new JLabel("Date"),gc);
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(datePicker,gc);
        /////Button/////
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = .1;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = btmPadding;
        panel.add(showButton,gc);
        /////Total Amount//////
        gc.gridy ++;
        gc.weightx = 1;
        gc.weighty = 2 ;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        panel.add(new JLabel("Total Amount"),gc);
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        panel.add(totalAmountField,gc);
        //gc.insets = new Insets(0,50,0,30);
        panel.add(new JScrollPane(reportTable),
                new GridBagConstraints(0,8,2,2,1,5,GridBagConstraints.CENTER,
                        GridBagConstraints.HORIZONTAL,new Insets(0,55,10,55),1,1));

        setLayout(new BorderLayout());
        add(panel,BorderLayout.CENTER);

    }
    public void setCodeListener(ComboBoxListener listener){
        this.listener = listener;
        codeBox.setModel(controller.retrieveCode());
        setFieldEmpty();
    }

}
