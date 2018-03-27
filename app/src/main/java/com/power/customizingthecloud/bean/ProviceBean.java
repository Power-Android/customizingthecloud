package com.power.customizingthecloud.bean;

import com.bigkoo.pickerview.model.IPickerViewData;

import java.util.List;

/**
 * Created by power on 2018/3/27.
 */

public class ProviceBean {
    /**
     * code : 1
     * message : 请求成功
     * data : [{"id":2,"name":"北京市","pid":1,"items":[{"id":3,"name":"市辖区","pid":2,"items":[{"id":4,"name":"东城区","pid":3},{"id":5,"name":"西城区","pid":3},{"id":6,"name":"朝阳区","pid":3},{"id":7,"name":"丰台区","pid":3},{"id":8,"name":"石景山区","pid":3},{"id":9,"name":"海淀区","pid":3},{"id":10,"name":"门头沟区","pid":3},{"id":11,"name":"房山区","pid":3},{"id":12,"name":"通州区","pid":3},{"id":13,"name":"顺义区","pid":3},{"id":14,"name":"昌平区","pid":3},{"id":15,"name":"大兴区","pid":3},{"id":16,"name":"怀柔区","pid":3},{"id":17,"name":"平谷区","pid":3}]},{"id":18,"name":"县","pid":2,"items":[{"id":19,"name":"密云县","pid":18},{"id":20,"name":"延庆县","pid":18}]}]}]
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

    public static class DataBean implements IPickerViewData{
        /**
         * id : 2
         * name : 北京市
         * pid : 1
         * items : [{"id":3,"name":"市辖区","pid":2,"items":[{"id":4,"name":"东城区","pid":3},{"id":5,"name":"西城区","pid":3},{"id":6,"name":"朝阳区","pid":3},{"id":7,"name":"丰台区","pid":3},{"id":8,"name":"石景山区","pid":3},{"id":9,"name":"海淀区","pid":3},{"id":10,"name":"门头沟区","pid":3},{"id":11,"name":"房山区","pid":3},{"id":12,"name":"通州区","pid":3},{"id":13,"name":"顺义区","pid":3},{"id":14,"name":"昌平区","pid":3},{"id":15,"name":"大兴区","pid":3},{"id":16,"name":"怀柔区","pid":3},{"id":17,"name":"平谷区","pid":3}]},{"id":18,"name":"县","pid":2,"items":[{"id":19,"name":"密云县","pid":18},{"id":20,"name":"延庆县","pid":18}]}]
         */

        private int id;
        private String name;
        private int pid;
        private List<ItemsBeanX> items;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public List<ItemsBeanX> getItems() {
            return items;
        }

        public void setItems(List<ItemsBeanX> items) {
            this.items = items;
        }

        @Override
        public String getPickerViewText() {
            return this.name;
        }

        public static class ItemsBeanX implements IPickerViewData{
            /**
             * id : 3
             * name : 市辖区
             * pid : 2
             * items : [{"id":4,"name":"东城区","pid":3},{"id":5,"name":"西城区","pid":3},{"id":6,"name":"朝阳区","pid":3},{"id":7,"name":"丰台区","pid":3},{"id":8,"name":"石景山区","pid":3},{"id":9,"name":"海淀区","pid":3},{"id":10,"name":"门头沟区","pid":3},{"id":11,"name":"房山区","pid":3},{"id":12,"name":"通州区","pid":3},{"id":13,"name":"顺义区","pid":3},{"id":14,"name":"昌平区","pid":3},{"id":15,"name":"大兴区","pid":3},{"id":16,"name":"怀柔区","pid":3},{"id":17,"name":"平谷区","pid":3}]
             */

            private int id;
            private String name;
            private int pid;
            private List<ItemsBean> items;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public List<ItemsBean> getItems() {
                return items;
            }

            public void setItems(List<ItemsBean> items) {
                this.items = items;
            }

            @Override
            public String getPickerViewText() {
                return this.name;
            }

            public static class ItemsBean implements IPickerViewData{
                /**
                 * id : 4
                 * name : 东城区
                 * pid : 3
                 */

                private int id;
                private String name;
                private int pid;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getPid() {
                    return pid;
                }

                public void setPid(int pid) {
                    this.pid = pid;
                }

                @Override
                public String getPickerViewText() {
                    return this.name;
                }
            }
        }
    }
}
