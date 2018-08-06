package com.power.customizingthecloud.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/8/6.
 */

public class MyBankListBean {


    /**
     * code : 1
     * data : [{"image":"http://39.107.91.92:84/bankcard/BOC.png","bank_card":"6217855000005783622","bank_type":"BOC","updated_at":"2018-08-06 15:53:24","user_id":35,"card_name":"刘新魁","bank_name":"中国银行","created_at":"2018-08-06 15:53:24","id":3,"card_mobile":"15811337458"}]
     * message : ok
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
         * image : http://39.107.91.92:84/bankcard/BOC.png
         * bank_card : 6217855000005783622
         * bank_type : BOC
         * updated_at : 2018-08-06 15:53:24
         * user_id : 35
         * card_name : 刘新魁
         * bank_name : 中国银行
         * created_at : 2018-08-06 15:53:24
         * id : 3
         * card_mobile : 15811337458
         */
        private String image;
        private String bank_card;
        private String bank_type;
        private String updated_at;
        private int user_id;
        private String card_name;
        private String bank_name;
        private String created_at;
        private int id;
        private String card_mobile;
        private boolean isChecked;

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setBank_card(String bank_card) {
            this.bank_card = bank_card;
        }

        public void setBank_type(String bank_type) {
            this.bank_type = bank_type;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public void setCard_name(String card_name) {
            this.card_name = card_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setCard_mobile(String card_mobile) {
            this.card_mobile = card_mobile;
        }

        public String getImage() {
            return image;
        }

        public String getBank_card() {
            return bank_card;
        }

        public String getBank_type() {
            return bank_type;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public int getUser_id() {
            return user_id;
        }

        public String getCard_name() {
            return card_name;
        }

        public String getBank_name() {
            return bank_name;
        }

        public String getCreated_at() {
            return created_at;
        }

        public int getId() {
            return id;
        }

        public String getCard_mobile() {
            return card_mobile;
        }
    }
}
