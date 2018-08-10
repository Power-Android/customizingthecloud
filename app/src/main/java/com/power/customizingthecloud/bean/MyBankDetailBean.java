package com.power.customizingthecloud.bean;

/**
 * Created by Administrator on 2018/8/8.
 */

public class MyBankDetailBean {

    /**
     * code : 1
     * data : {"image":"http://39.107.91.92:84/bankcard/CMBC.png","bank_card":"4654613213213","bank_type":"CMBC","updated_at":"2018-08-02 12:06:54","user_id":11,"danbi":30000,"card_name":"王兵","bank_name":"民生银行","created_at":"2018-08-02 12:06:54","id":1,"day":50000,"card_mobile":"15250735030"}
     * message : ok
     */
    private int code;
    private DataEntity data;
    private String message;

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public DataEntity getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public static class DataEntity {
        /**
         * image : http://39.107.91.92:84/bankcard/CMBC.png
         * bank_card : 4654613213213
         * bank_type : CMBC
         * updated_at : 2018-08-02 12:06:54
         * user_id : 11
         * danbi : 30000
         * card_name : 王兵
         * bank_name : 民生银行
         * created_at : 2018-08-02 12:06:54
         * id : 1
         * day : 50000
         * card_mobile : 15250735030
         */
        private String image;
        private String bank_card;
        private String bank_type;
        private String updated_at;
        private int user_id;
        private int danbi;
        private String card_name;
        private String bank_name;
        private String created_at;
        private int id;
        private int day;
        private String card_mobile;

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

        public void setDanbi(int danbi) {
            this.danbi = danbi;
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

        public void setDay(int day) {
            this.day = day;
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

        public int getDanbi() {
            return danbi;
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

        public int getDay() {
            return day;
        }

        public String getCard_mobile() {
            return card_mobile;
        }
    }
}
