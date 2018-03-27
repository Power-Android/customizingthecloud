package com.power.customizingthecloud.bean;

import com.bigkoo.pickerview.model.IPickerViewData;

/**
 * Created by power on 2018/3/27.
 */

public class QuBean implements IPickerViewData {

    @Override
    public String getPickerViewText() {
        return this.getQuname();
    }

    private String quname;
    private String id;

    public String getQuname() {
        return quname;
    }

    public void setQuname(String quname) {
        this.quname = quname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
