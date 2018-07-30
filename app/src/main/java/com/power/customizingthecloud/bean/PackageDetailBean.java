package com.power.customizingthecloud.bean;

/**
 * Created by Administrator on 2018/7/30.
 */

public class PackageDetailBean {

    /**
     * code : 1
     * data : {"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201807/ZQB1DjxnzHvqpQV2bmmu.jpg","address":{"address":"西二旗","user_id":11,"true_name":"王盼","province_id":2,"mobile":"15250735030","id":50,"area_info":"北京市 市辖区 东城区","area_id":4,"is_default":1,"city_id":3},"price":"123.00","name":"ceshi","package_id":7}
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
         * image : http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201807/ZQB1DjxnzHvqpQV2bmmu.jpg
         * address : {"address":"西二旗","user_id":11,"true_name":"王盼","province_id":2,"mobile":"15250735030","id":50,"area_info":"北京市 市辖区 东城区","area_id":4,"is_default":1,"city_id":3}
         * price : 123.00
         * name : ceshi
         * package_id : 7
         */
        private String image;
        private AddressEntity address;
        private String price;
        private String name;
        private int package_id;

        public void setImage(String image) {
            this.image = image;
        }

        public void setAddress(AddressEntity address) {
            this.address = address;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPackage_id(int package_id) {
            this.package_id = package_id;
        }

        public String getImage() {
            return image;
        }

        public AddressEntity getAddress() {
            return address;
        }

        public String getPrice() {
            return price;
        }

        public String getName() {
            return name;
        }

        public int getPackage_id() {
            return package_id;
        }

        public static class AddressEntity {
            /**
             * address : 西二旗
             * user_id : 11
             * true_name : 王盼
             * province_id : 2
             * mobile : 15250735030
             * id : 50
             * area_info : 北京市 市辖区 东城区
             * area_id : 4
             * is_default : 1
             * city_id : 3
             */
            private String address;
            private int user_id;
            private String true_name;
            private int province_id;
            private String mobile;
            private int id;
            private String area_info;
            private int area_id;
            private int is_default;
            private int city_id;

            public void setAddress(String address) {
                this.address = address;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public void setTrue_name(String true_name) {
                this.true_name = true_name;
            }

            public void setProvince_id(int province_id) {
                this.province_id = province_id;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setArea_info(String area_info) {
                this.area_info = area_info;
            }

            public void setArea_id(int area_id) {
                this.area_id = area_id;
            }

            public void setIs_default(int is_default) {
                this.is_default = is_default;
            }

            public void setCity_id(int city_id) {
                this.city_id = city_id;
            }

            public String getAddress() {
                return address;
            }

            public int getUser_id() {
                return user_id;
            }

            public String getTrue_name() {
                return true_name;
            }

            public int getProvince_id() {
                return province_id;
            }

            public String getMobile() {
                return mobile;
            }

            public int getId() {
                return id;
            }

            public String getArea_info() {
                return area_info;
            }

            public int getArea_id() {
                return area_id;
            }

            public int getIs_default() {
                return is_default;
            }

            public int getCity_id() {
                return city_id;
            }
        }
    }
}
