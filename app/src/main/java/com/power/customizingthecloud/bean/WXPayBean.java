package com.power.customizingthecloud.bean;

/**
 * Created by Administrator on 2018/1/12.
 */

public class WXPayBean {


    /**
     * code : 1
     * data : {"appid":"wx5c1cdc0f4545b7b5","sign":"A4A116FCE8E94C44341683B6CC3CE505","package1":"Sign=WXPay","partnerid":"1501182421","prepayid":"wx05095902969314e1ec44f4d61032518599","noncestr":"7v9w1ohfo9115zhn05u3gxd4ldsapqlo","timestamp":1530755943}
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
         * appid : wx5c1cdc0f4545b7b5
         * sign : A4A116FCE8E94C44341683B6CC3CE505
         * package1 : Sign=WXPay
         * partnerid : 1501182421
         * prepayid : wx05095902969314e1ec44f4d61032518599
         * noncestr : 7v9w1ohfo9115zhn05u3gxd4ldsapqlo
         * timestamp : 1530755943
         */
        private String appid;
        private String sign;
        private String package1;
        private String partnerid;
        private String prepayid;
        private String noncestr;
        private long timestamp;

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public void setPackage1(String package1) {
            this.package1 = package1;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public String getAppid() {
            return appid;
        }

        public String getSign() {
            return sign;
        }

        public String getPackage1() {
            return package1;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public long getTimestamp() {
            return timestamp;
        }
    }
}
