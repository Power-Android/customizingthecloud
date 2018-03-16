package com.power.customizingthecloud.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/25.
 */

public class RedPacketBean implements Serializable {

    /**
     * code : 1
     * message : ok
     * data : [{"id":3,"package_id":2,"name":"测试礼包2","price":"200.00","image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","start_time":"2018-03-12","end_time":"2018-04-30","state":1},{"id":1,"package_id":1,"name":"测试礼包","price":"100.00","image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","start_time":"2018-03-12","end_time":"2018-04-30","state":2},{"id":2,"package_id":1,"name":"测试礼包","price":"100.00","image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","start_time":"2018-03-12","end_time":"2018-04-30","state":1}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 3
         * package_id : 2
         * name : 测试礼包2
         * price : 200.00
         * image : http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png
         * start_time : 2018-03-12
         * end_time : 2018-04-30
         * state : 1
         */

        private int id;
        private int package_id;
        private String name;
        private String price;
        private String image;
        private String start_time;
        private String end_time;
        private int state;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPackage_id() {
            return package_id;
        }

        public void setPackage_id(int package_id) {
            this.package_id = package_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }
}
