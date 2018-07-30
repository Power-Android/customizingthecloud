package com.power.customizingthecloud.login.bean;

/**
 * Created by Administrator on 2018/3/14.
 */

public class LoginBean {


    /**
     * code : 1
     * data : {"user_id":11,"user_mobile":"15250735031","refresh_ttl":40320,"ttl":20160,"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjExLCJpc3MiOiJodHRwOi8vMzkuMTA3LjkxLjkyOjg0L2FwaS92Mi9tb2JpbGUtbG9naW4iLCJpYXQiOjE1MzI5Mzc5OTMsImV4cCI6MTUzNDE0NzU5MywibmJmIjoxNTMyOTM3OTkzLCJqdGkiOiJkelVuUHBGeWZUR1BGUm9FIn0.e0AKDEXDqNGjD7KoH1FQV7EVLEzc8WAksYYAqjwOvZI"}
     * message : 登录成功
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
         * user_id : 11
         * user_mobile : 15250735031
         * refresh_ttl : 40320
         * ttl : 20160
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjExLCJpc3MiOiJodHRwOi8vMzkuMTA3LjkxLjkyOjg0L2FwaS92Mi9tb2JpbGUtbG9naW4iLCJpYXQiOjE1MzI5Mzc5OTMsImV4cCI6MTUzNDE0NzU5MywibmJmIjoxNTMyOTM3OTkzLCJqdGkiOiJkelVuUHBGeWZUR1BGUm9FIn0.e0AKDEXDqNGjD7KoH1FQV7EVLEzc8WAksYYAqjwOvZI
         */
        private int user_id;
        private String user_mobile;
        private int refresh_ttl;
        private int ttl;
        private String token;

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public void setUser_mobile(String user_mobile) {
            this.user_mobile = user_mobile;
        }

        public void setRefresh_ttl(int refresh_ttl) {
            this.refresh_ttl = refresh_ttl;
        }

        public void setTtl(int ttl) {
            this.ttl = ttl;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getUser_id() {
            return user_id;
        }

        public String getUser_mobile() {
            return user_mobile;
        }

        public int getRefresh_ttl() {
            return refresh_ttl;
        }

        public int getTtl() {
            return ttl;
        }

        public String getToken() {
            return token;
        }
    }
}
