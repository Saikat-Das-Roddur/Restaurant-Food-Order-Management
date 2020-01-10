/*
 * Copyright (c)
 * Author : Saikat Das
 * Created on : 11/3/19 9:44 AM
 * All rights reserved
 */

package View;

import Controller.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MemberEntrylayout extends JFrame {
    private JTextField codeField,nameField,emailField,contactField;
    private JComboBox typeBox ;
    private JButton saveButton;
    private MemberEvent event;
    private Controller controller = new Controller();
    private MemberLayoutListener listener;
    MemberEntrylayout(){
        super("Member Entry");
        codeField = new JTextField(12);
        nameField = new JTextField(12);
        emailField = new JTextField(12);
        contactField = new JTextField(12);
        saveButton = new JButton("Save");
        typeBox = new JComboBox();
        Font font1 = new Font("SansSerif", Font.BOLD, 12);
        codeField.setFont(font1);
        nameField.setFont(font1);
        emailField.setFont(font1);
        contactField.setFont(font1);
        typeBox.setFont(font1);
        DefaultComboBoxModel typeCombo = new DefaultComboBoxModel();
        typeCombo.addElement("General");
        typeCombo.addElement("VIP");
        typeCombo.addElement("Guest");
        typeBox.setModel(typeCombo);
        typeBox.setPreferredSize(nameField.getPreferredSize());
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (valid()){
                    String code = codeField.getText();
                    String name = nameField.getText();
                    String email = emailField.getText();
                    String contactNo = contactField.getText();
                    String type = (String)typeBox.getSelectedItem();
                    event = new MemberEvent(this,code,name,email,contactNo,type);
                    if (listener!=null){
                        System.out.println(code+": "+name);
                        listener.memberEventOccurred(event);

                    }
                    codeField.setText("");
                    nameField.setText("");
                    emailField.setText("");
                    contactField.setText("");
                }


            }
        });
       // System.out.println("Working!!");


        layoutComponents();

        setSize(480,500);

    }
    private void layoutComponents() {
        GridBagConstraints gc = new GridBagConstraints();
        JPanel panel = new JPanel();


        EmptyBorder emptyBorder = new EmptyBorder(50,10,10,10);
        TitledBorder innerBorder = BorderFactory.createTitledBorder("Member Entry");
        Border border = BorderFactory.createCompoundBorder(innerBorder,emptyBorder);
        Border outerBorder = BorderFactory.createEmptyBorder(45,45,45,45);
        panel.setBorder(BorderFactory.createCompoundBorder(outerBorder,border));
        panel.setLayout(new GridBagLayout());

        ///////Code//////
        //
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = .1;
        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,15);
        panel.add(new JLabel("Code"),gc);
        gc.gridx = 1;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(codeField,gc);
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
        panel.add(new JLabel("Type"),gc);
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(typeBox,gc);
        /////Button/////

        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 2;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        panel.add(saveButton,gc);
        panel.setBackground(Color.YELLOW);
        setLayout(new BorderLayout());
        add(panel,BorderLayout.CENTER);

    }
    private boolean valid() {
        DefaultComboBoxModel dm = controller.retrieveCode();
        for (int i = 1; i <= dm.getSize(); i++) {
            if (codeField.getText().equals(dm.getElementAt(i))) {
                System.out.println("Checking " + codeField.getText() + " with " + dm.getElementAt(i));
                JOptionPane.showMessageDialog(this, "Sorry, Member already exists",
                        "Member Exists", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }

        }
        String s = codeField.getText();
        if (s.length() == 5) {
            //System.out.println(s);
            if (s.replaceAll("\\s+", "").length() == s.length())
                return true;
            else{
                JOptionPane.showMessageDialog(this,
                        "Please remove spaces");
                return false;
            }


        } else {
            JOptionPane.showMessageDialog(this,
                    "Please Enter a valid code with five Characters");
            return false;
        }
    }

    public void setMemberListener(MemberLayoutListener listener){
        this.listener = listener;
    }


}
