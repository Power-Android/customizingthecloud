package com.power.customizingthecloud.login.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/14.
 */

public class GuidePageBean {

    /**
     * code : 1
     * data : {"guide":[{"imgurl":"guides/gudie-3.jpg","name":"引导页3","id":3,"sort":0,"state":1},{"imgurl":"guides/gudie-2.jpg","name":"引导页2","id":2,"sort":0,"state":1},{"imgurl":"guides/gudie-1.jpg","name":"引导页1","id":1,"sort":0,"state":1}]}
     * message : 获取成功
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
         * guide : [{"imgurl":"guides/gudie-3.jpg","name":"引导页3","id":3,"sort":0,"state":1},{"imgurl":"guides/gudie-2.jpg","name":"引导页2","id":2,"sort":0,"state":1},{"imgurl":"guides/gudie-1.jpg","name":"引导页1","id":1,"sort":0,"state":1}]
         */
        private List<GuideEntity> guide;

        public void setGuide(List<GuideEntity> guide) {
            this.guide = guide;
        }

        public List<GuideEntity> getGuide() {
            return guide;
        }

        public static class GuideEntity {
            /**
             * imgurl : guides/gudie-3.jpg
             * name : 引导页3
             * id : 3
             * sort : 0
             * state : 1
             */
            private String imgurl;
            private String name;
            private int id;
            private int sort;
            private int state;

            public void setImgurl(String imgurl) {
                this.imgurl = imgurl;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public void setState(int state) {
                this.state = state;
            }

            public String getImgurl() {
                return imgurl;
            }

            public String getName() {
                return name;
            }

            public int getId() {
                return id;
            }

            public int getSort() {
                return sort;
            }

            public int getState() {
                return state;
            }
        }
    }
}
