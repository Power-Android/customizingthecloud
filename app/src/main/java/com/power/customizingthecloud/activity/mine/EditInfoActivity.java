package com.power.customizingthecloud.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.renderscript.BaseObj;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.BaseBean;
import com.power.customizingthecloud.bean.EditInfoBean;
import com.power.customizingthecloud.bean.EventBean;
import com.power.customizingthecloud.bean.PicBean;
import com.power.customizingthecloud.bean.ProviceBean;
import com.power.customizingthecloud.bean.CityBean;
import com.power.customizingthecloud.bean.QuBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.callback.JsonCallback;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.TUtils;
import com.power.customizingthecloud.utils.Urls;
import com.power.customizingthecloud.view.BaseSelectPopupWindow;
import com.power.customizingthecloud.view.CircleImageView;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.NormalSelectionDialog;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class EditInfoActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_back_iv) ImageView titleBackIv;
    @BindView(R.id.title_content_tv) TextView titleContentTv;
    @BindView(R.id.title_content_right_tv) TextView titleContentRightTv;
    @BindView(R.id.edit_face_rl) RelativeLayout editFaceRl;
    @BindView(R.id.edit_face_iv) CircleImageView editFaceIv;
    @BindView(R.id.edit_name_tv) TextView editNameTv;
    @BindView(R.id.edit_name_rl) RelativeLayout editNameRl;
    @BindView(R.id.edit_sex_tv) TextView editSexTv;
    @BindView(R.id.edit_sex_rl) RelativeLayout editSexRl;
    @BindView(R.id.edit_age_tv) TextView editAgeTv;
    @BindView(R.id.edit_age_rl) RelativeLayout editAgeRl;
    @BindView(R.id.edit_location_tv) TextView editLocationTv;
    @BindView(R.id.edit_location_rl) RelativeLayout editLocationRl;
    private List<String> cameraList;
    private List<LocalMedia> selectList = new ArrayList<>();
    private String cutPath;
    private BaseSelectPopupWindow popWiw;// 昵称 编辑框
    private List<String> sexLiset;
    private int SEX = 1;

    private List<ProviceBean.DataBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<CityBean>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<QuBean>>> options3Items = new ArrayList<>();
    private boolean isLoaded = false;
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
        setContentView(R.layout.activity_edit_info);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("资料编辑");
        titleContentRightTv.setVisibility(View.VISIBLE);
        titleContentRightTv.setText("保存");
        titleBackIv.setOnClickListener(this);
        titleContentRightTv.setOnClickListener(this);
        editFaceRl.setOnClickListener(this);
        editNameRl.setOnClickListener(this);
        editSexRl.setOnClickListener(this);
        editAgeRl.setOnClickListener(this);
        editLocationRl.setOnClickListener(this);
        sexLiset = new ArrayList<>();
        sexLiset.add("男");
        sexLiset.add("女");

        initAreaData();
        initData();
    }

    private void initData() {//获取个人信息
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        OkGo.<EditInfoBean>get(Urls.BASEURL + "api/v2/user/show")
                .tag(this)
                .headers(headers)
                .execute(new DialogCallback<EditInfoBean>(this,EditInfoBean.class) {
                    @Override
                    public void onSuccess(Response<EditInfoBean> response) {
                        EditInfoBean body = response.body();
                        if (body.getCode() == 1){
                            Glide.with(mContext).load(body.getData().getUser_avatar()).into(editFaceIv);
                            editNameTv.setText(body.getData().getUser_name());
                            if (body.getData().getUser_sex() == 1){
                                editSexTv.setText("男");
                            }else {
                                editSexTv.setText("女");
                            }
                            editAgeTv.setText(body.getData().getUser_age()+"");
                            editLocationTv.setText(body.getData().getUser_areainfo());
                        }else {
                            TUtils.showShort(mContext,body.getMessage());
                        }
                    }
                });
    }

    private void initAreaData() {
        OkGo.<ProviceBean>get(Urls.BASEURL + "api/v2/area")
                .tag(this)
                .execute(new DialogCallback<ProviceBean>(EditInfoActivity.this,ProviceBean.class) {
                    @Override
                    public void onSuccess(Response<ProviceBean> response) {
                        ProviceBean body = response.body();
                        if (body.getCode() == 1){
                            options1Items = body.getData();
                            initJsonData();
                        }else {
                            TUtils.showShort(mContext,body.getMessage());
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.title_content_right_tv:
                initEditData();
                break;
            case R.id.edit_face_rl:
                cameraList = new ArrayList<>();
                cameraList.add("从相册中选择");
                cameraList.add("拍照");
                showCamera();
                break;
            case R.id.edit_name_rl:
                showNickName("name");
                break;
            case R.id.edit_sex_rl:
                showSex();
                break;
            case R.id.edit_age_rl:
                showNickName("age");
                break;
            case R.id.edit_location_rl:
                if (isLoaded){
                    ShowPickerView();
                }else {
                    TUtils.showShort(mContext,"地址解析失败，请检查网络情况。");
                }
                break;
        }
    }

    private void initEditData() {
        if (TextUtils.isEmpty(editNameTv.getText().toString())){
            TUtils.showShort(mContext,"请填写昵称");
            return;
        }
        if (TextUtils.isEmpty(editAgeTv.getText().toString())){
            TUtils.showShort(mContext,"请填写年龄");
            return;
        }
        if (TextUtils.isEmpty(editLocationTv.getText().toString())){
            TUtils.showShort(mContext,"请选择地区");
            return;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("user_name",editNameTv.getText().toString());
        if (fileurl != null){
            params.put("user_avatar",fileurl);
        }
        params.put("user_sex",SEX + "");
        params.put("user_age",editAgeTv.getText().toString());
        params.put("user_areaid",user_areaid);
        params.put("user_cityid",user_cityid);
        params.put("user_provinceid",user_provinceid);
        params.put("user_areainfo",provice + " " + city + " " + area);

        OkGo.<BaseBean>post(Urls.BASEURL + "api/v2/user/edit")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<BaseBean>(this,BaseBean.class) {
                    @Override
                    public void onSuccess(Response<BaseBean> response) {
                        BaseBean body = response.body();
                        if (body.getCode() == 1){
                            TUtils.showShort(mContext,body.getMessage());
                            EventBean eventBean = new EventBean("userinfo");
                            EventBus.getDefault().postSticky(eventBean);
                            finish();
                        }else {
                            TUtils.showShort(mContext,body.getMessage());
                        }
                    }
                });
    }

    private void showCamera() {
        new NormalSelectionDialog.Builder(this).setlTitleVisible(false)   //设置是否显示标题
                .setItemHeight(55)  //设置item的高度
                .setItemWidth(0.9f)  //屏幕宽度*0.9
                .setItemTextColor(R.color.text_black)  //设置item字体颜色
                .setItemTextSize(16)  //设置item字体大小
                .setCancleButtonText("取消")  //设置最底部“取消”按钮文本
                .setOnItemListener(new DialogInterface.OnItemClickListener<NormalSelectionDialog>() {

                    @Override
                    public void onItemClick(NormalSelectionDialog dialog, View button, int
                            position) {
                        switch (position){
                            case 0://从相册选择
                                requestPhoto();
                                break;
                            case 1://拍照
                                requestCamera();
                                break;
                        }
                        dialog.dismiss();
                    }
                })
                .setCanceledOnTouchOutside(true)  //设置是否可点击其他地方取消dialog
                .build()
                .setDatas(cameraList)
                .show();
    }

    private void requestPhoto() {
        // 进入相册 以下是例子：不需要的api可以不写
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(R.style.picture_default_style1)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                .maxSelectNum(1)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .imageSpanCount(4)// 每行显示个数
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片
                .previewVideo(false)// 是否可预览视频
                .enablePreviewAudio(false) // 是否可播放音频
                .compressGrade(Luban.THIRD_GEAR)// luban压缩档次，默认3档 Luban.FIRST_GEAR、Luban.CUSTOM_GEAR
                .isCamera(true)// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                .enableCrop(true)// 是否裁剪
                .compress(true)// 是否压缩
                .compressMode(PictureConfig.SYSTEM_COMPRESS_MODE)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
                //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .glideOverride(200, 200)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//                .withAspectRatio(aspect_ratio_x, aspect_ratio_y)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示
                .isGif(false)// 是否显示gif图片
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .circleDimmedLayer(true)// 是否圆形裁剪
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                .openClickSound(false)// 是否开启点击声音
//                .selectionMedia(list)// 是否传入已选图片
//                        .videoMaxSecond(15)
//                        .videoMinSecond(10)
                //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                //.cropCompressQuality(90)// 裁剪压缩质量 默认100
                //.compressMaxKB()//压缩最大值kb compressGrade()为Luban.CUSTOM_GEAR有效
                //.compressWH() // 压缩宽高比 compressGrade()为Luban.CUSTOM_GEAR有效
                //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                //.rotateEnabled() // 裁剪是否可旋转图片
                .scaleEnabled(false)// 裁剪是否可放大缩小图片
                //.videoQuality()// 视频录制质量 0 or 1
                //.videoSecond()//显示多少秒以内的视频or音频也可适用
                //.recordVideoSecond()//录制视频秒数 默认60s
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    private void requestCamera() {
        PictureSelector.create(this)
                .openCamera(PictureMimeType.ofImage())// 单独拍照，也可录像或也可音频 看你传入的类型是图片or视频
                .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles
                .enableCrop(true)// 是否裁剪
                .compress(true)// 是否压缩
                .compressMode(PictureConfig.SYSTEM_COMPRESS_MODE)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .circleDimmedLayer(true)// 是否圆形裁剪
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                .scaleEnabled(false)// 裁剪是否可放大缩小图片
//                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//                .selectionMedia(list)// 是否传入已选图片
//                .previewEggs(true)//预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    cutPath = selectList.get(0).getCutPath();
                    Glide.with(this).load(cutPath).into(editFaceIv);
                    File file = new File(cutPath);
                    initPicData(file);
                    break;
            }
        }
    }

    private void initPicData(File file) {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("file",file);
        params.put("path","users");
        OkGo.<PicBean>post(Urls.BASEURL + "api/v2/file/store")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new JsonCallback<PicBean>(PicBean.class) {
                    @Override
                    public void onSuccess(Response<PicBean> response) {
                        PicBean body = response.body();
                        if (body.getCode() == 1){
                            fileurl = body.getData().getFileurl();
                        }else {
                            TUtils.showShort(mContext,body.getMessage());
                        }
                    }
                });

    }

    private void showNickName(final String type) {
        if (popWiw == null) {
            popWiw = new BaseSelectPopupWindow(this, R.layout.edit_data);
            // popWiw.setOpenKeyboard(true);
            popWiw.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);

            popWiw.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            popWiw.setShowTitle(false);
        }
        popWiw.setFocusable(true);
        InputMethodManager im = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        im.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

        final ImageView send = (ImageView) popWiw.getContentView().findViewById(R.id.query_iv);
        final EditText edt = (EditText) popWiw.getContentView().findViewById(R.id.edt_content);
        final ImageView close = (ImageView) popWiw.getContentView().findViewById(R.id.cancle_iv);
        if (type.equals("name")){
            if (!TextUtils.isEmpty(edt.getText().toString())){
                edt.getText().clear();
            }
            edt.setHint("请输入昵称");
            edt.setInputType(EditorInfo.TYPE_CLASS_TEXT);
        }
        if (type.equals("age")){
            if (!TextUtils.isEmpty(edt.getText().toString())){
                edt.getText().clear();
            }
            edt.setHint("请输入年龄");
            edt.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        }
//        edt.setImeOptions(EditorInfo.IME_ACTION_SEND);
        edt.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (TextUtils.isEmpty(edt.getText())) {
                    send.setEnabled(false);
                } else {
                    send.setEnabled(true);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });

        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(edt.getText().toString().trim())) {
                    // 昵称
                    String content = edt.getText().toString().trim();
                    if (type.equals("name")) editNameTv.setText(content);
                    if (type.equals("age")) editAgeTv.setText(content);
                    popWiw.dismiss();
                }
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popWiw.dismiss();
            }
        });

        popWiw.showAtLocation(editNameRl, Gravity.BOTTOM
                | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    private void showSex() {
        new NormalSelectionDialog.Builder(this).setlTitleVisible(false)   //设置是否显示标题
                .setItemHeight(55)  //设置item的高度
                .setItemWidth(0.9f)  //屏幕宽度*0.9
                .setItemTextColor(R.color.text_black)  //设置item字体颜色
                .setItemTextSize(16)  //设置item字体大小
                .setCancleButtonText("取消")  //设置最底部“取消”按钮文本
                .setOnItemListener(new DialogInterface.OnItemClickListener<NormalSelectionDialog>() {

                    @Override
                    public void onItemClick(NormalSelectionDialog dialog, View button, int
                            position) {
                        switch (position){
                            case 0://男
                                editSexTv.setText("男");
                                SEX = 1;
                                break;
                            case 1://女
                                editSexTv.setText("女");
                                SEX = 2;
                                break;
                        }
                        dialog.dismiss();
                    }
                })
                .setCanceledOnTouchOutside(true)  //设置是否可点击其他地方取消dialog
                .build()
                .setDatas(sexLiset)
                .show();
    }

    private void ShowPickerView() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText()+
                        options2Items.get(options1).get(options2).getCityname()+
                        options3Items.get(options1).get(options2).get(options3).getQuname();
                user_areaid = options3Items.get(options1).get(options2).get(options3).getId();
                user_cityid = options2Items.get(options1).get(options2).getId();
                user_provinceid = options1Items.get(options1).getId()+"";
                provice = options1Items.get(options1).getPickerViewText();
                city = options2Items.get(options1).get(options2).getCityname();
                area = options3Items.get(options1).get(options2).get(options3).getQuname();
                editLocationTv.setText(provice + " " + city + " " + area);

            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器
        pvOptions.show();
    }

    private void initJsonData() {//解析数据

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */

        for (int i=0;i<options1Items.size();i++){//遍历省份
            ArrayList<CityBean> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<QuBean>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            //如果无城市数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
            if (options1Items.get(i).getItems() == null
                    || options1Items.get(i).getItems().size() == 0){
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
            }else {
                for (int c=0; c<options1Items.get(i).getItems().size(); c++){//遍历该省份的所有城市
                    CityBean cityBean = new CityBean();
                    String CityName = options1Items.get(i).getItems().get(c).getName();
                    int id = options1Items.get(i).getItems().get(c).getId();
                    cityBean.setCityname(CityName);
                    cityBean.setId(id+"");
                    CityList.add(cityBean);//添加城市
                    ArrayList<QuBean> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                    //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                    if (options1Items.get(i).getItems().get(c).getItems() == null
                            ||options1Items.get(i).getItems().get(c).getItems().size()==0) {
                        QuBean quBean = new QuBean();
                        quBean.setId("");
                        quBean.setQuname("");
                        City_AreaList.add(quBean);
                    }else {

                        for (int d=0; d < options1Items.get(i).getItems().get(c).getItems().size(); d++) {//该城市对应地区所有数据
                            QuBean quBean = new QuBean();
                            String AreaName = options1Items.get(i).getItems().get(c).getItems().get(d).getName();
                            int id1 = options1Items.get(i).getItems().get(c).getItems().get(d).getId();
                            quBean.setQuname(AreaName);
                            quBean.setId(id1+"");
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


}
