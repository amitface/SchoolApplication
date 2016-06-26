package com.school.amit.schoolapplication;

/**
 * Created by amit on 27/3/15.
 */


public class RowItem {
    private String name;
    private String phone1;
    private String phone2;

    public RowItem(String name, String phone1, String phone2) {
        this.name = name;
        this.phone1 = phone1;
        this.phone2 = phone2;
    }
    public String getname() {
        return name;
    }
    public void setname(String name) {
        this.name = name;
    }
    public String getphone2() {
        return phone2;
    }
    public void setphone2(String phone2) {
        this.phone2 = phone2;
    }
    public String getphone1() {
        return phone1;
    }
    public void setphone1(String phone1) {
        this.phone1 = phone1;
    }
    @Override
    public String toString() {
        return phone1 + "\n" + phone2;
    }
}