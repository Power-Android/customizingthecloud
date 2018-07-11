package com.power.customizingthecloud.activity.mine;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.AddressManageBean;
import com.power.customizingthecloud.bean.BaseBean;
import com.power.customizingthecloud.bean.CityBean;
import com.power.customizingthecloud.bean.ProviceBean;
import com.power.customizingthecloud.bean.QuBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.callback.JsonCallback;
import com.power.customizingthecloud.utils.SoftKeyboardTool;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.TUtils;
import com.power.customizingthecloud.utils.Urls;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditAddressActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.title_content_right_tv)
    TextView titleContentRightTv;
    @BindView(R.id.name_et)
    EditText nameEt;
    @BindView(R.id.phone_et)
    EditText phoneEt;
    @BindView(R.id.adress_tv)
    TextView adressTv;
    @BindView(R.id.address_rl)
    RelativeLayout addressRl;
    @BindView(R.id.jiedao_tv)
    TextView jiedaoTv;
    @BindView(R.id.jiedao_rl)
    RelativeLayout jiedaoRl;
    @BindView(R.id.detail_address_et)
    EditText detailAddressEt;
    private String type;
    private AddressManageBean.DataBean dataBean;
    private List<ProviceBean.DataBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<CityBean>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<QuBean>>> options3Items = new ArrayList<>();
    private boolean isLoaded = false;
    private boolean isChangeAddress = false;
    private String user_areaid;
    private String user_cityid;
    private String user_provinceid;
    private String fileurl;
    private String provice;
    private String city;
    private String area;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);
        ButterKnife.bind(this);
        initView();
        initAreaData();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleBackIv.setOnClickListener(this);
        titleContentTv.setText("编辑收货地址");
        titleContentRightTv.setVisibility(View.VISIBLE);
        titleContentRightTv.setText("保存");
        titleContentRightTv.setOnClickListener(this);
        addressRl.setOnClickListener(this);
        type = getIntent().getStringExtra("type");
        if (TextUtils.equals("edit", type)) {
            dataBean = (AddressManageBean.DataBean) getIntent().getSerializableExtra("dataBean");
            if (dataBean != null) {
                nameEt.setText(dataBean.getTrue_name());
                phoneEt.setText(dataBean.getMobile());
                adressTv.setText(dataBean.getArea_info());
                user_areaid = dataBean.getArea_id() + "";
                user_cityid = dataBean.getCity_id() + "";
                user_provinceid = dataBean.getProvince_id() + "";
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.title_content_right_tv:
                if (TextUtils.equals("new", type)) {
                    initData();
                } else if (TextUtils.equals("edit", type)) {
                    initEdit();
                }
                break;
            case R.id.address_rl:
                SoftKeyboardTool.closeKeyboard(this);
                if (isLoaded) {
                    ShowPickerView();
                } else {
                    TUtils.showShort(mContext, "地址解析失败，请检查网络情况。");
                }
                break;
            case R.id.jiedao_rl:
                break;
        }
    }

    private void ShowPickerView() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText() +
                        options2Items.get(options1).get(options2).getCityname() +
                        options3Items.get(options1).get(options2).get(options3).getQuname();
                user_areaid = options3Items.get(options1).get(options2).get(options3).getId();
                user_cityid = options2Items.get(options1).get(options2).getId();
                user_provinceid = options1Items.get(options1).getId() + "";
                provice = options1Items.get(options1).getPickerViewText();
                city = options2Items.get(options1).get(options2).getCityname();
                area = options3Items.get(options1).get(options2).get(options3).getQuname();
                adressTv.setText(provice + " " + city + " " + area);
                isChangeAddress = true;
            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    private void initAreaData() {
        OkGo.<ProviceBean>get(Urls.BASEURL + "api/v2/area")
                .tag(this)
                .execute(new JsonCallback<ProviceBean>(ProviceBean.class) {
                    @Override
                    public void onSuccess(Response<ProviceBean> response) {
                        ProviceBean body = response.body();
                        if (body.getCode() == 1) {
                            options1Items = body.getData();
                            initJsonData();
                        } else {
                            TUtils.showShort(mContext, body.getMessage());
                        }
                    }
                });
    }

    private void initJsonData() {//解析数据

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */

        for (int i = 0; i < options1Items.size(); i++) {//遍历省份
            ArrayList<CityBean> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<QuBean>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            //如果无城市数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
            if (options1Items.get(i).getItems() == null
                    || options1Items.get(i).getItems().size() == 0) {
                CityBean cityBean = new CityBean();
                cityBean.setId("");
                cityBean.setCityname("");
                CityList.add(cityBean);
                ArrayList<QuBean> City_AreaList = new ArrayList<>();//该城市的所有地区列表
                QuBean quBean = new QuBean();
                quBean.setId("");
                quBean.setQuname("");
                City_AreaList.add(quBean);
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            } else {
                for (int c = 0; c < options1Items.get(i).getItems().size(); c++) {//遍历该省份的所有城市
                    CityBean cityBean = new CityBean();
                    String CityName = options1Items.get(i).getItems().get(c).getName();
                    int id = options1Items.get(i).getItems().get(c).getId();
                    cityBean.setCityname(CityName);
                    cityBean.setId(id + "");
                    CityList.add(cityBean);//添加城市
                    ArrayList<QuBean> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                    //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                    if (options1Items.get(i).getItems().get(c).getItems() == null
                            || options1Items.get(i).getItems().get(c).getItems().size() == 0) {
                        QuBean quBean = new QuBean();
                        quBean.setId("");
                        quBean.setQuname("");
                        City_AreaList.add(quBean);
                    } else {

                        for (int d = 0; d < options1Items.get(i).getItems().get(c).getItems().size(); d++) {//该城市对应地区所有数据
                            QuBean quBean = new QuBean();
                            String AreaName = options1Items.get(i).getItems().get(c).getItems().get(d).getName();
                            int id1 = options1Items.get(i).getItems().get(c).getItems().get(d).getId();
                            quBean.setQuname(AreaName);
                            quBean.setId(id1 + "");
                            City_AreaList.add(quBean);//添加该城市所有地区数据
                        }
                    }
                    Province_AreaList.add(City_AreaList);//添加该省所有地区数据
                }
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }

        isLoaded = true;
    }

    private void initEdit() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("true_name", nameEt.getText().toString() + "");
        params.put("area_id", user_areaid);
        params.put("city_id", user_cityid);
        params.put("province_id", user_provinceid);
        params.put("area_info", adressTv.getText().toString());
        params.put("address", detailAddressEt.getText().toString());
        params.put("mobile", phoneEt.getText().toString());
        params.put("address_id", dataBean.getId());
        OkGo.<BaseBean>post(Urls.BASEURL + "api/v2/address/edit")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<BaseBean>(this, BaseBean.class) {
                    @Override
                    public void onSuccess(Response<BaseBean> response) {
                        BaseBean body = response.body();
                        if (body.getCode() == 1) {
                            TUtils.showShort(mContext, body.getMessage());
                            setResult(1, new Intent());
                            finish();
                        } else {
                            TUtils.showShort(mContext, body.getMessage());
                        }
                    }
                });
    }

    private void initData() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("true_name", nameEt.getText().toString() + "");
        if (isChangeAddress) {
            params.put("area_id", user_areaid);
            params.put("city_id", user_cityid);
            params.put("province_id", user_provinceid);
            params.put("area_info", adressTv.getText().toString());
        }
        params.put("address", detailAddressEt.getText().toString());
        params.put("mobile", phoneEt.getText().toString() + "");
        OkGo.<BaseBean>post(Urls.BASEURL + "api/v2/address/add")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<BaseBean>(this, BaseBean.class) {
                    @Override
                    public void onSuccess(Response<BaseBean> response) {
                        BaseBean body = response.body();
                        if (body.getCode() == 1) {
                            TUtils.showShort(mContext, body.getMessage());
                            setResult(1, new Intent());
                            finish();
                        } else {
                            TUtils.showShort(mContext, body.getMessage());
                        }
                    }
                });
    }
}
