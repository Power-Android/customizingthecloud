package com.power.customizingthecloud.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/2/11.
 */

public class AddressManageBean implements Serializable {
    private String name;
    private String phone;
    private String address;
    private String isMoren;
    private boolean isEdit = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIsMoren() {
        return isMoren;
    }

    public void setIsMoren(String isMoren) {
        this.isMoren = isMoren;
    }

    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }
}
