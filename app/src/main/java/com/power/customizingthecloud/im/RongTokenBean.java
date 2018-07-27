package com.power.customizingthecloud.im;

/**
 * Created by Administrator on 2018/7/26.
 */

public class RongTokenBean {


    /**
     * code : 1
     * data : {"userId":"user_1","token":"RQfsy06775PvPuRsiTzJdcSX1qFZ6RYaPq0iMXS8lbRFGb4oPLOMAXcqxX1BhOEkelp1tfTIOzc2znMxhrOxXw=="}
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
         * userId : user_1
         * token : RQfsy06775PvPuRsiTzJdcSX1qFZ6RYaPq0iMXS8lbRFGb4oPLOMAXcqxX1BhOEkelp1tfTIOzc2znMxhrOxXw==
         */
        private String userId;
        private String token;

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUserId() {
            return userId;
        }

        public String getToken() {
            return token;
        }
    }
}
