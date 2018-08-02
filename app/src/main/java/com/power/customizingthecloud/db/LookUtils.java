package com.power.customizingthecloud.db;

import android.content.ContentValues;

import com.power.customizingthecloud.MyApplication;
import com.power.customizingthecloud.utils.SpUtils;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Administrator on 2017/12/28.
 */

public class LookUtils {
    public static List<LookBean> search(String userid) {
        List<LookBean> all = DataSupport.order("time desc").where("user_id=?",userid).find(LookBean.class);//这样也行
        return all;
    }

    public static void deleteOne(String id) {
        DataSupport.deleteAll(LookBean.class, "good_id=?", id);
    }

    public static void deleteAll() {
        DataSupport.deleteAll(LookBean.class);
    }

    public static void add(LookBean lookBean) {
        if (isInserted(lookBean.getGood_id()+"")) {
        } else {
            lookBean.save();//添加完数据别忘了保存，这个save()方法是实体类继承的DataSupport类中的
        }
    }

    public static void updateTime(long time, String id) {
        ContentValues values = new ContentValues();
        values.put("time", time);
        DataSupport.updateAll(LookBean.class, values, "good_id=?", id);
    }

    public static boolean isInserted(String goodid) {
        String userid = SpUtils.getString(MyApplication.getGloableContext(), "userid", "");
        List<LookBean> all = search(userid);
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getGood_id()==Integer.parseInt(goodid)) {
                return true;
            }
        }
        return false;
    }
}
