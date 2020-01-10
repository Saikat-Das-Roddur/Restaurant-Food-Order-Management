
/*
 * Copyright (c)
 * Author : Saikat Das
 * Created on : 10/22/19 8:00 PM
 * All rights reserved
 */

package Model;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ReportTableModel extends AbstractTableModel {

    private List<FoodOrderInfo> foodOrderInfos;
    private String[] ColNames = {"Item Name","Quantity","Total Price"};
    public void setData(List<FoodOrderInfo> foodOrderInfos){
        this.foodOrderInfos = foodOrderInfos;
    }

    @Override
    public String getColumnName(int column) {
        return ColNames[column];
    }

    @Override
    public int getRowCount() {
        return foodOrderInfos.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        FoodOrderInfo orderInfo = foodOrderInfos.get(rowIndex);
        switch (columnIndex){
            case 0:
                orderInfo.getFoodItem();
            case 1:
                orderInfo.getQuantity();
            case 2:
                orderInfo.getTotal();
        }
        return null;
    }
}
