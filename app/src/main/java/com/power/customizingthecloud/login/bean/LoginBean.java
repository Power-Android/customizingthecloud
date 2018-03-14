package com.power.customizingthecloud.login.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/14.
 */

public class LoginBean {

    /**
     * code : 1
     * data : [{"user_id":1,"user_mobile":"15250735030","refresh_ttl":40320,"ttl":20160,"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjEsImlzcyI6Imh0dHA6Ly93d3cuc3N5ZC5jb20vYXBpL3YyL2NvZGUtbG9naW4iLCJpYXQiOjE1MTYzNDcyOTMsImV4cCI6MTUxNzU1Njg5MywibmJmIjoxNTE2MzQ3MjkzLCJqdGkiOiJFMnAyUG9aeUIyTlBLS2hZIn0.hJNoONtN8MzkzM3tZ_dzcw4IeohCi5zyp88pXd4WeW0"}]
     * message : 登录成功
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
         * user_id : 1
         * user_mobile : 15250735030
         * refresh_ttl : 40320
         * ttl : 20160
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjEsImlzcyI6Imh0dHA6Ly93d3cuc3N5ZC5jb20vYXBpL3YyL2NvZGUtbG9naW4iLCJpYXQiOjE1MTYzNDcyOTMsImV4cCI6MTUxNzU1Njg5MywibmJmIjoxNTE2MzQ3MjkzLCJqdGkiOiJFMnAyUG9aeUIyTlBLS2hZIn0.hJNoONtN8MzkzM3tZ_dzcw4IeohCi5zyp88pXd4WeW0
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
