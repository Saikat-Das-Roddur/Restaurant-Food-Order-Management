/*
 * Copyright (c)
 * Author : Saikat Das
 * Created on : 10/22/19 8:02 PM
 * All rights reserved
 */

package View;

import java.util.EventListener;

public interface FoodOrderLayoutListener extends EventListener{
    public void foodOrderEventOccurred(FoodOrderEvent e);
}
