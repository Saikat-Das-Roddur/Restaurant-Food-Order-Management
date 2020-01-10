/*
 * Copyright (c)
 * Author : Saikat Das
 * Created on : 10/22/19 8:03 PM
 * All rights reserved
 */

package View;

import java.util.EventObject;

public class MemberEvent extends EventObject {
    private String code;
    private String name;
    private String email;
    private String contactNo;
    private String type;

    public MemberEvent(Object source, String code, String name, String email, String contactNo, String type) {
        super(source);
        this.code = code;
        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
