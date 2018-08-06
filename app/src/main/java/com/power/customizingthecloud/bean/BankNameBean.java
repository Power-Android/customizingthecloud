package com.power.customizingthecloud.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/8/6.
 */

public class BankNameBean {


    /**
     * code : 1
     * data : {"bank":"BOC","stat":"ok","validated":true,"cardType":"DC","bank_name":"中国银行","messages":[],"key":"6217855000005783642"}
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
         * bank : BOC
         * stat : ok
         * validated : true
         * cardType : DC
         * bank_name : 中国银行
         * messages : []
         * key : 6217855000005783642
         */
        private String bank;
        private String stat;
        private boolean validated;
        private String cardType;
        private String bank_name;
        private List<?> messages;
        private String key;

        public void setBank(String bank) {
            this.bank = bank;
        }

        public void setStat(String stat) {
            this.stat = stat;
        }

        public void setValidated(boolean validated) {
            this.validated = validated;
        }

        public void setCardType(String cardType) {
            this.cardType = cardType;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public void setMessages(List<?> messages) {
            this.messages = messages;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getBank() {
            return bank;
        }

        public String getStat() {
            return stat;
        }

        public boolean isValidated() {
            return validated;
        }

        public String getCardType() {
            return cardType;
        }

        public String getBank_name() {
            return bank_name;
        }

        public List<?> getMessages() {
            return messages;
        }

        public String getKey() {
            return key;
        }
    }
}
