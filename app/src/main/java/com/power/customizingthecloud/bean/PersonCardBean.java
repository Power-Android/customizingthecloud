package com.power.customizingthecloud.bean;

/**
 * Created by Administrator on 2018/7/20.
 */

public class PersonCardBean {

    /**
     * code : 1
     * data : {"user_card":"321322199006164411","card_img":"http://ssyd.oss-cn-beijing.aliyuncs.com/users/201805/IvTrq1pnjtr9IA3Dnz8A.jpg","updated_at":"2018-07-16 10:35:24","user_id":1,"true_name":"王三","created_at":"2018-07-16 10:35:24","id":1}
     * message : 请求成功
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
         * user_card : 321322199006164411
         * card_img : http://ssyd.oss-cn-beijing.aliyuncs.com/users/201805/IvTrq1pnjtr9IA3Dnz8A.jpg
         * updated_at : 2018-07-16 10:35:24
         * user_id : 1
         * true_name : 王三
         * created_at : 2018-07-16 10:35:24
         * id : 1
         */
        private String user_card;
        private String card_img;
        private String updated_at;
        private int user_id;
        private String true_name;
        private String created_at;
        private int id;

        public void setUser_card(String user_card) {
            this.user_card = user_card;
        }

        public void setCard_img(String card_img) {
            this.card_img = card_img;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public void setTrue_name(String true_name) {
            this.true_name = true_name;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUser_card() {
            return user_card;
        }

        public String getCard_img() {
            return card_img;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public int getUser_id() {
            return user_id;
        }

        public String getTrue_name() {
            return true_name;
        }

        public String getCreated_at() {
            return created_at;
        }

        public int getId() {
            return id;
        }
    }
}
