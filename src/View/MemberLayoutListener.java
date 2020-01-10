/*
 * Copyright (c)
 * Author : Saikat Das
 * Created on : 10/22/19 8:03 PM
 * All rights reserved
 */

package View;

import java.util.EventListener;

public interface MemberLayoutListener extends EventListener {
    public void memberEventOccurred(MemberEvent e);
}
