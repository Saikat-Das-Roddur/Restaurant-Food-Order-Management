/*
 * Copyright (c)
 * Author : Saikat Das
 * Created on : 10/22/19 8:00 PM
 * All rights reserved
 */

package Model;

public class MemberInfo {
    private String code;
    private String name;
    private String email;
    private String contactNo;
    private Type type;
    public MemberInfo(String code,String name, String email, String contactNo, Type type) {
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return
                "code= " + code  +
                " name= " + name  +
                " email= " + email  +
                " contactNo= " + contactNo  +
                " type= " + type ;
    }
}
