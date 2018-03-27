package com.power.customizingthecloud.bean;

import com.bigkoo.pickerview.model.IPickerViewData;

/**
 * Created by power on 2018/3/27.
 */

public class CityBean implements IPickerViewData {
    @Override
    public String getPickerViewText() {
        return this.cityname;
    }
    private String cityname;
    private String id;

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
