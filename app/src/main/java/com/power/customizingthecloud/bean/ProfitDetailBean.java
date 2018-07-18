package com.power.customizingthecloud.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/7/16.
 */

public class ProfitDetailBean {

    /**
     * code : 1
     * data : [{"note":"您认养的第二期驴妈妈获得收益到账1479.45元(本金10000.00元)","amount":"1479.45","data":"","user_id":1,"created_at":"2018.07.13","id":29,"type":"2"}]
     * message : 请求成功
     */
    private int code;
    private List<DataEntity> data;
    private String message;

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public static class DataEntity {
        /**
         * note : 您认养的第二期驴妈妈获得收益到账1479.45元(本金10000.00元)
         * amount : 1479.45
         * data :
         * user_id : 1
         * created_at : 2018.07.13
         * id : 29
         * type : 2
         */
        private String note;
        private String amount;
        private String data;
        private int user_id;
        private String created_at;
        private int id;
        private String type;

        public void setNote(String note) {
            this.note = note;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public void setData(String data) {
            this.data = data;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getNote() {
            return note;
        }

        public String getAmount() {
            return amount;
        }

        public String getData() {
            return data;
        }

        public int getUser_id() {
            return user_id;
        }

        public String getCreated_at() {
            return created_at;
        }

        public int getId() {
            return id;
        }

        public String getType() {
            return type;
        }
    }
}
