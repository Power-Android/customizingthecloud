package com.power.customizingthecloud.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/3/19.
 */

public class KaidianBean implements Serializable {

    /**
     * code : 1
     * message : ok
     * data : {"user_distribution":"0.00","user_name":"潇洒","user_avatar":"","team":[{"id":1,"inviter_id":11,"user_name":"123","user_avatar":"","total_price":0}]}
     */

    private int code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * user_distribution : 0.00
         * user_name : 潇洒
         * user_avatar :
         * team : [{"id":1,"inviter_id":11,"user_name":"123","user_avatar":"","total_price":0}]
         */

        private String user_distribution;
        private String user_name;
        private String user_avatar;
        private List<TeamBean> team;

        public String getUser_distribution() {
            return user_distribution;
        }

        public void setUser_distribution(String user_distribution) {
            this.user_distribution = user_distribution;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_avatar() {
            return user_avatar;
        }

        public void setUser_avatar(String user_avatar) {
            this.user_avatar = user_avatar;
        }

        public List<TeamBean> getTeam() {
            return team;
        }

        public void setTeam(List<TeamBean> team) {
            this.team = team;
        }

        public static class TeamBean {
            /**
             * id : 1
             * inviter_id : 11
             * user_name : 123
             * user_avatar :
             * total_price : 0
             */

            private int id;
            private int inviter_id;
            private String user_name;
            private String user_avatar;
            private int total_price;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getInviter_id() {
                return inviter_id;
            }

            public void setInviter_id(int inviter_id) {
                this.inviter_id = inviter_id;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getUser_avatar() {
                return user_avatar;
            }

            public void setUser_avatar(String user_avatar) {
                this.user_avatar = user_avatar;
            }

            public int getTotal_price() {
                return total_price;
            }

            public void setTotal_price(int total_price) {
                this.total_price = total_price;
            }
        }
    }
}
