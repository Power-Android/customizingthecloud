package com.power.customizingthecloud.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/2/11.
 */

public class AddressManageBean implements Serializable {

    /**
     * code : 1
     * message : 请求成功
     * data : [{"id":1,"user_id":1,"true_name":"王兵3","area_id":2,"city_id":3,"province_id":4,"area_info":"北京市 直辖区 东城区","address":"西二旗辉煌国际","mobile":"15250735030","is_default":0},{"id":3,"user_id":1,"true_name":"王兵3","area_id":2,"city_id":3,"province_id":4,"area_info":"北京市 直辖区 东城区","address":"西二旗辉煌国际","mobile":"15250735030","is_default":0}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * id : 1
         * user_id : 1
         * true_name : 王兵3
         * area_id : 2
         * city_id : 3
         * province_id : 4
         * area_info : 北京市 直辖区 东城区
         * address : 西二旗辉煌国际
         * mobile : 15250735030
         * is_default : 0
         */

        private int id;
        private int user_id;
        private String true_name;
        private int area_id;
        private int city_id;
        private int province_id;
        private String area_info;
        private String address;
        private String mobile;
        private int is_default;
        private boolean isEdit = false;

        public boolean isEdit() {
            return isEdit;
        }

        public void setEdit(boolean edit) {
            isEdit = edit;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getTrue_name() {
            return true_name;
        }

        public void setTrue_name(String true_name) {
            this.true_name = true_name;
        }

        public int getArea_id() {
            return area_id;
        }

        public void setArea_id(int area_id) {
            this.area_id = area_id;
        }

        public int getCity_id() {
            return city_id;
        }

        public void setCity_id(int city_id) {
            this.city_id = city_id;
        }

        public int getProvince_id() {
            return province_id;
        }

        public void setProvince_id(int province_id) {
            this.province_id = province_id;
        }

        public String getArea_info() {
            return area_info;
        }

        public void setArea_info(String area_info) {
            this.area_info = area_info;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getIs_default() {
            return is_default;
        }

        public void setIs_default(int is_default) {
            this.is_default = is_default;
        }
    }
}
