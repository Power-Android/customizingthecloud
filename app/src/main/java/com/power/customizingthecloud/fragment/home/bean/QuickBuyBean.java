package com.power.customizingthecloud.fragment.home.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/26.
 */

public class QuickBuyBean {

    /**
     * code : 1
     * data : {"good_list":[{"good_num":1,"eselsohr_deduction":0,"good_price":"100.00","is_seckill":1,"buy_type":3,"end_time_state":true,"distribution_price":0,"good_type":0,"good_id":3,"is_voucher":0,"seckill_state":true,"seckill_storage":30,"seckill_start_time":1516621657,"storage_state":true,"good_unit":"罐","seckill_end_time":1529375559,"good_image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","seckill_price":"100.00","state":true,"start_time_state":true,"good_name":"特色有机奶粉","class_name":"休闲食品","seckill_note":"","is_distribution":0}],"address":{"address":"西二旗辉煌国际","user_id":1,"true_name":"王兵3","province_id":4,"mobile":"15250735030","id":1,"area_info":"北京市 直辖区 东城区","area_id":2,"is_default":1,"city_id":3},"order_good_total":"100.00","buy_type":3,"voucher":[{"end_date":"2018-09-19","updated_at":"2018-01-24 19:30:39","user_id":1,"price":10,"created_at":"2018-01-30 10:02:22","template_id":1,"id":1,"state":1,"title":"奶粉代金券","order_limit":30,"order_id":0,"start_date":"2018-01-23"}],"user_eselsohr":400,"eselsohr_total":0}
     * message : 请求成功
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
         * good_list : [{"good_num":1,"eselsohr_deduction":0,"good_price":"100.00","is_seckill":1,"buy_type":3,"end_time_state":true,"distribution_price":0,"good_type":0,"good_id":3,"is_voucher":0,"seckill_state":true,"seckill_storage":30,"seckill_start_time":1516621657,"storage_state":true,"good_unit":"罐","seckill_end_time":1529375559,"good_image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","seckill_price":"100.00","state":true,"start_time_state":true,"good_name":"特色有机奶粉","class_name":"休闲食品","seckill_note":"","is_distribution":0}]
         * address : {"address":"西二旗辉煌国际","user_id":1,"true_name":"王兵3","province_id":4,"mobile":"15250735030","id":1,"area_info":"北京市 直辖区 东城区","area_id":2,"is_default":1,"city_id":3}
         * order_good_total : 100.00
         * buy_type : 3
         * voucher : [{"end_date":"2018-09-19","updated_at":"2018-01-24 19:30:39","user_id":1,"price":10,"created_at":"2018-01-30 10:02:22","template_id":1,"id":1,"state":1,"title":"奶粉代金券","order_limit":30,"order_id":0,"start_date":"2018-01-23"}]
         * user_eselsohr : 400
         * eselsohr_total : 0
         */
        private List<GoodListEntity> good_list;
        private AddressEntity address;
        private String order_good_total;
        private int buy_type;
        private List<VoucherEntity> voucher;
        private int user_eselsohr;
        private int eselsohr_total;

        public void setGood_list(List<GoodListEntity> good_list) {
            this.good_list = good_list;
        }

        public void setAddress(AddressEntity address) {
            this.address = address;
        }

        public void setOrder_good_total(String order_good_total) {
            this.order_good_total = order_good_total;
        }

        public void setBuy_type(int buy_type) {
            this.buy_type = buy_type;
        }

        public void setVoucher(List<VoucherEntity> voucher) {
            this.voucher = voucher;
        }

        public void setUser_eselsohr(int user_eselsohr) {
            this.user_eselsohr = user_eselsohr;
        }

        public void setEselsohr_total(int eselsohr_total) {
            this.eselsohr_total = eselsohr_total;
        }

        public List<GoodListEntity> getGood_list() {
            return good_list;
        }

        public AddressEntity getAddress() {
            return address;
        }

        public String getOrder_good_total() {
            return order_good_total;
        }

        public int getBuy_type() {
            return buy_type;
        }

        public List<VoucherEntity> getVoucher() {
            return voucher;
        }

        public int getUser_eselsohr() {
            return user_eselsohr;
        }

        public int getEselsohr_total() {
            return eselsohr_total;
        }

        public static class GoodListEntity {
            /**
             * good_num : 1
             * eselsohr_deduction : 0
             * good_price : 100.00
             * is_seckill : 1
             * buy_type : 3
             * end_time_state : true
             * distribution_price : 0
             * good_type : 0
             * good_id : 3
             * is_voucher : 0
             * seckill_state : true
             * seckill_storage : 30
             * seckill_start_time : 1516621657
             * storage_state : true
             * good_unit : 罐
             * seckill_end_time : 1529375559
             * good_image : http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png
             * seckill_price : 100.00
             * state : true
             * start_time_state : true
             * good_name : 特色有机奶粉
             * class_name : 休闲食品
             * seckill_note :
             * is_distribution : 0
             */
            private int good_num;
            private int eselsohr_deduction;
            private String good_price;
            private int is_seckill;
            private int buy_type;
            private boolean end_time_state;
            private int distribution_price;
            private int good_type;
            private int good_id;
            private int is_voucher;
            private boolean seckill_state;
            private int seckill_storage;
            private int seckill_start_time;
            private boolean storage_state;
            private String good_unit;
            private int seckill_end_time;
            private String good_image;
            private String seckill_price;
            private boolean state;
            private boolean start_time_state;
            private String good_name;
            private String class_name;
            private String seckill_note;
            private int is_distribution;

            public void setGood_num(int good_num) {
                this.good_num = good_num;
            }

            public void setEselsohr_deduction(int eselsohr_deduction) {
                this.eselsohr_deduction = eselsohr_deduction;
            }

            public void setGood_price(String good_price) {
                this.good_price = good_price;
            }

            public void setIs_seckill(int is_seckill) {
                this.is_seckill = is_seckill;
            }

            public void setBuy_type(int buy_type) {
                this.buy_type = buy_type;
            }

            public void setEnd_time_state(boolean end_time_state) {
                this.end_time_state = end_time_state;
            }

            public void setDistribution_price(int distribution_price) {
                this.distribution_price = distribution_price;
            }

            public void setGood_type(int good_type) {
                this.good_type = good_type;
            }

            public void setGood_id(int good_id) {
                this.good_id = good_id;
            }

            public void setIs_voucher(int is_voucher) {
                this.is_voucher = is_voucher;
            }

            public void setSeckill_state(boolean seckill_state) {
                this.seckill_state = seckill_state;
            }

            public void setSeckill_storage(int seckill_storage) {
                this.seckill_storage = seckill_storage;
            }

            public void setSeckill_start_time(int seckill_start_time) {
                this.seckill_start_time = seckill_start_time;
            }

            public void setStorage_state(boolean storage_state) {
                this.storage_state = storage_state;
            }

            public void setGood_unit(String good_unit) {
                this.good_unit = good_unit;
            }

            public void setSeckill_end_time(int seckill_end_time) {
                this.seckill_end_time = seckill_end_time;
            }

            public void setGood_image(String good_image) {
                this.good_image = good_image;
            }

            public void setSeckill_price(String seckill_price) {
                this.seckill_price = seckill_price;
            }

            public void setState(boolean state) {
                this.state = state;
            }

            public void setStart_time_state(boolean start_time_state) {
                this.start_time_state = start_time_state;
            }

            public void setGood_name(String good_name) {
                this.good_name = good_name;
            }

            public void setClass_name(String class_name) {
                this.class_name = class_name;
            }

            public void setSeckill_note(String seckill_note) {
                this.seckill_note = seckill_note;
            }

            public void setIs_distribution(int is_distribution) {
                this.is_distribution = is_distribution;
            }

            public int getGood_num() {
                return good_num;
            }

            public int getEselsohr_deduction() {
                return eselsohr_deduction;
            }

            public String getGood_price() {
                return good_price;
            }

            public int getIs_seckill() {
                return is_seckill;
            }

            public int getBuy_type() {
                return buy_type;
            }

            public boolean isEnd_time_state() {
                return end_time_state;
            }

            public int getDistribution_price() {
                return distribution_price;
            }

            public int getGood_type() {
                return good_type;
            }

            public int getGood_id() {
                return good_id;
            }

            public int getIs_voucher() {
                return is_voucher;
            }

            public boolean isSeckill_state() {
                return seckill_state;
            }

            public int getSeckill_storage() {
                return seckill_storage;
            }

            public int getSeckill_start_time() {
                return seckill_start_time;
            }

            public boolean isStorage_state() {
                return storage_state;
            }

            public String getGood_unit() {
                return good_unit;
            }

            public int getSeckill_end_time() {
                return seckill_end_time;
            }

            public String getGood_image() {
                return good_image;
            }

            public String getSeckill_price() {
                return seckill_price;
            }

            public boolean isState() {
                return state;
            }

            public boolean isStart_time_state() {
                return start_time_state;
            }

            public String getGood_name() {
                return good_name;
            }

            public String getClass_name() {
                return class_name;
            }

            public String getSeckill_note() {
                return seckill_note;
            }

            public int getIs_distribution() {
                return is_distribution;
            }
        }

        public static class AddressEntity {
            /**
             * address : 西二旗辉煌国际
             * user_id : 1
             * true_name : 王兵3
             * province_id : 4
             * mobile : 15250735030
             * id : 1
             * area_info : 北京市 直辖区 东城区
             * area_id : 2
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

        public static class VoucherEntity {
            /**
             * end_date : 2018-09-19
             * updated_at : 2018-01-24 19:30:39
             * user_id : 1
             * price : 10
             * created_at : 2018-01-30 10:02:22
             * template_id : 1
             * id : 1
             * state : 1
             * title : 奶粉代金券
             * order_limit : 30
             * order_id : 0
             * start_date : 2018-01-23
             */
            private String end_date;
            private String updated_at;
            private int user_id;
            private int price;
            private String created_at;
            private int template_id;
            private int id;
            private int state;
            private String title;
            private int order_limit;
            private int order_id;
            private String start_date;

            public void setEnd_date(String end_date) {
                this.end_date = end_date;
            }

            public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public void setTemplate_id(int template_id) {
                this.template_id = template_id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setState(int state) {
                this.state = state;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setOrder_limit(int order_limit) {
                this.order_limit = order_limit;
            }

            public void setOrder_id(int order_id) {
                this.order_id = order_id;
            }

            public void setStart_date(String start_date) {
                this.start_date = start_date;
            }

            public String getEnd_date() {
                return end_date;
            }

            public String getUpdated_at() {
                return updated_at;
            }

            public int getUser_id() {
                return user_id;
            }

            public int getPrice() {
                return price;
            }

            public String getCreated_at() {
                return created_at;
            }

            public int getTemplate_id() {
                return template_id;
            }

            public int getId() {
                return id;
            }

            public int getState() {
                return state;
            }

            public String getTitle() {
                return title;
            }

            public int getOrder_limit() {
                return order_limit;
            }

            public int getOrder_id() {
                return order_id;
            }

            public String getStart_date() {
                return start_date;
            }
        }
    }
}
