/*
 * Copyright (c)
 * Author : Saikat Das
 * Created on : 10/22/19 7:58 PM
 * All rights reserved
 */

package View;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {

    private HomeLayout home;
    public MainFrame(){
        super("Restaurant Order Management");
        home= new HomeLayout();
        add(home);
        setSize(480,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }


}
