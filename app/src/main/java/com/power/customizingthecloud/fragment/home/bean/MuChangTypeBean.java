package com.power.customizingthecloud.fragment.home.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/14.
 */

public class MuChangTypeBean {

    /**
     * code : 1
     * data : [{"name":"毛驴运动A区","id":1},{"name":"毛驴运动B区","id":2},{"name":"粗饲料仓储区","id":3},{"name":"驴妈妈饲养区","id":4},{"name":"精料加工区","id":5},{"name":"驴妈妈繁殖区","id":6},{"name":"驴宝宝饲养区","id":7},{"name":"隔离检疫区","id":8},{"name":"屠宰车间","id":9},{"name":"驴肉食品及阿娇深加工车间","id":10}]
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
         * name : 毛驴运动A区
         * id : 1
         */
        private String name;
        private int id;

        public void setName(String name) {
            this.name = name;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }
    }
}
