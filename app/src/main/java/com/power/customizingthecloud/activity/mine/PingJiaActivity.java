package com.power.customizingthecloud.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.power.customizingthecloud.MyApplication;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.adapter.GridViewAddImgesAdapter2;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.BaseBean;
import com.power.customizingthecloud.bean.MyOderBean;
import com.power.customizingthecloud.bean.OrderDetailBean;
import com.power.customizingthecloud.bean.PicBean;
import com.power.customizingthecloud.bean.PingjiaPushBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.callback.JsonCallback;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.TUtils;
import com.power.customizingthecloud.utils.Urls;
import com.power.customizingthecloud.view.MyGridView;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.NormalSelectionDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.zelory.compressor.Compressor;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PingJiaActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.commit_tv)
    TextView commitTv;
    @BindView(R.id.recycler_pingjia)
    RecyclerView recycler_pingjia;
    private List<String> cameraList = new ArrayList<>();
    private List<LocalMedia> selectList = new ArrayList<>();
    private List<LocalMedia> listAll = new ArrayList<>();
    private StringBuilder stringBuilder = new StringBuilder();
    private int picposition;
    private int mytype;
    private List<MyOderBean.DataEntity.GoodsEntity> goods1;
    private List<OrderDetailBean.DataEntity.GoodsEntity> goods2;
    private OrderDetailPingAdapter orderDetailPingAdapter;
    private MyOrderPingAdapter myOrderPingAdapter;
    private String order_id;
    private int adapterPosition;
    private int goodCount;
    private int goodPosition;
    private List<PingjiaPushBean> pingjiaPushBeanList = new ArrayList<>();
    private List<String> listAll2 = new ArrayList<>();
    private HashMap<Integer, List<LocalMedia>> hashMap_photos = new HashMap<>();
    private HashMap<Integer, List<String>> hashMap_photos2 = new HashMap<>();
    private HashMap<Integer, GridViewAddImgesAdapter2> hashMap_adapters2 = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping_jia);
        ButterKnife.bind(this);
        initview();
    }

    private void initview() {
        recycler_pingjia.setNestedScrollingEnabled(false);
        recycler_pingjia.setLayoutManager(new LinearLayoutManager(this));
        cameraList.add("从相册中选择");
        cameraList.add("拍照");
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("发表评论");
        order_id = getIntent().getStringExtra("order_id");
        String type = getIntent().getStringExtra("type");
        if (type.equals("myorder")) {
            mytype = 0;
            goods1 = (List<MyOderBean.DataEntity.GoodsEntity>) getIntent().getSerializableExtra("data");
            myOrderPingAdapter = new MyOrderPingAdapter(R.layout.item_orderpingjia, goods1);
            recycler_pingjia.setAdapter(myOrderPingAdapter);
        } else {
            mytype = 1;
            goods2 = (List<OrderDetailBean.DataEntity.GoodsEntity>) getIntent().getSerializableExtra("data");
            orderDetailPingAdapter = new OrderDetailPingAdapter(R.layout.item_orderpingjia, goods2);
            recycler_pingjia.setAdapter(orderDetailPingAdapter);
        }
    }

    private class MyOrderPingAdapter extends BaseQuickAdapter<MyOderBean.DataEntity.GoodsEntity, BaseViewHolder> {

        public MyOrderPingAdapter(@LayoutRes int layoutResId, @Nullable List<MyOderBean.DataEntity.GoodsEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, MyOderBean.DataEntity.GoodsEntity item) {
            helper.setText(R.id.name_tv, item.getGoods_name())
                    .setText(R.id.fenlei_tv, item.getGoods_class());
            Glide.with(MyApplication.getGloableContext()).load(item.getGoods_image()).into((ImageView) helper.getView(R.id.face_iv));
            /**
             * 添加照片adapter
             */
            MyGridView gridView = helper.getView(R.id.uppic_iv);
            GridViewAddImgesAdapter2 addImgesAdpter = new GridViewAddImgesAdapter2(listAll2, PingJiaActivity.this);
            gridView.setAdapter(addImgesAdpter);
            hashMap_adapters2.put(helper.getAdapterPosition(), addImgesAdpter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    adapterPosition = helper.getAdapterPosition();
                    showCamera();
                }
            });
        }
    }

    private class OrderDetailPingAdapter extends BaseQuickAdapter<OrderDetailBean.DataEntity.GoodsEntity, BaseViewHolder> {

        public OrderDetailPingAdapter(@LayoutRes int layoutResId, @Nullable List<OrderDetailBean.DataEntity.GoodsEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, OrderDetailBean.DataEntity.GoodsEntity item) {
            helper.setText(R.id.name_tv, item.getGoods_name())
                    .setText(R.id.fenlei_tv, item.getGoods_class());
            Glide.with(MyApplication.getGloableContext()).load(item.getGoods_image()).into((ImageView) helper.getView(R.id.face_iv));
            /**
             * 添加照片adapter
             */
            MyGridView gridView = helper.getView(R.id.uppic_iv);
            GridViewAddImgesAdapter2 addImgesAdpter = new GridViewAddImgesAdapter2(listAll2, PingJiaActivity.this);
            gridView.setAdapter(addImgesAdpter);
            hashMap_adapters2.put(helper.getAdapterPosition(), addImgesAdpter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    adapterPosition = helper.getAdapterPosition();
                    showCamera();
                }
            });
        }
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
                        switch (position) {
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
        PictureSelector.create(PingJiaActivity.this)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(R.style.picture_default_style1)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                .maxSelectNum(9)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .imageSpanCount(4)// 每行显示个数
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片
                .previewVideo(false)// 是否可预览视频
                .enablePreviewAudio(false) // 是否可播放音频
                .compressGrade(Luban.THIRD_GEAR)// luban压缩档次，默认3档 Luban.FIRST_GEAR、Luban.CUSTOM_GEAR
                .isCamera(true)// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                .enableCrop(false)// 是否裁剪
                .compress(true)// 是否压缩
                .compressMode(PictureConfig.SYSTEM_COMPRESS_MODE)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
                //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .glideOverride(200, 200)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                //                .withAspectRatio(aspect_ratio_x, aspect_ratio_y)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示
                .isGif(false)// 是否显示gif图片
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .circleDimmedLayer(false)// 是否圆形裁剪
                .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
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
                //.scaleEnabled()// 裁剪是否可放大缩小图片
                //.videoQuality()// 视频录制质量 0 or 1
                //.videoSecond()//显示多少秒以内的视频or音频也可适用
                //.recordVideoSecond()//录制视频秒数 默认60s
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    private void requestCamera() {
        PictureSelector.create(PingJiaActivity.this)
                .openCamera(PictureMimeType.ofImage())// 单独拍照，也可录像或也可音频 看你传入的类型是图片or视频
                .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles
                .glideOverride(200, 200)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                //                .selectionMedia(list)// 是否传入已选图片
                .previewEggs(true)//预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    selectList.clear();
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    if (selectList.size() > 0) {
                        /*
                        之前用hashmap存List<LocalMedia>的数据，就会出现一些稀奇古怪的bug，折磨了3天，整了他妈的3天没弄好，
                        以为是逻辑的问题，就一直调，最后改成List<String>就好了，真是他妈的奇葩，太邪门了
                         */
//                        List<LocalMedia> localMedias = hashMap_photos.get(adapterPosition);
//                        if (localMedias != null && localMedias.size() > 0) {
//                            localMedias.addAll(selectList);
//                            hashMap_photos.put(adapterPosition, localMedias);
//                            GridViewAddImgesAdapter gridViewAddImgesAdpter = hashMap_adapters.get(adapterPosition);
//                            gridViewAddImgesAdpter.setList(localMedias);
//                            gridViewAddImgesAdpter.notifyDataSetChanged();
//                        } else {
//                            hashMap_photos.put(adapterPosition, selectList);
//                            GridViewAddImgesAdapter gridViewAddImgesAdpter = hashMap_adapters.get(adapterPosition);
//                            gridViewAddImgesAdpter.setList(selectList);
//                            gridViewAddImgesAdpter.notifyDataSetChanged();
//                        }
                        List<String> localMedias = hashMap_photos2.get(adapterPosition);
                        if (localMedias != null && localMedias.size() > 0) {
                            for (int i = 0; i < selectList.size(); i++) {
                                String path = selectList.get(i).getPath();
                                localMedias.add(path);
                            }
                            hashMap_photos2.put(adapterPosition, localMedias);
                            GridViewAddImgesAdapter2 gridViewAddImgesAdpter = hashMap_adapters2.get(adapterPosition);
                            gridViewAddImgesAdpter.setList(localMedias);
                            gridViewAddImgesAdpter.notifyDataSetChanged();
                        } else {
                            List<String> strList=new ArrayList<>();
                            for (int i = 0; i < selectList.size(); i++) {
                                String path = selectList.get(i).getPath();
                                strList.add(path);
                            }
                            hashMap_photos2.put(adapterPosition, strList);
                            GridViewAddImgesAdapter2 gridViewAddImgesAdpter = hashMap_adapters2.get(adapterPosition);
                            gridViewAddImgesAdpter.setList(strList);
                            gridViewAddImgesAdpter.notifyDataSetChanged();
                        }
                    }
                    break;
            }
        }
    }

    @OnClick({R.id.commit_tv, R.id.title_back_iv, R.id.title_content_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.commit_tv:
                pushPhoto1();
                break;
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.title_content_tv:
                break;
        }
    }

    private void pushPhoto3(File file, final int i) {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(mContext, "token", ""));
        HttpParams params = new HttpParams();
        params.put("file", file);
        params.put("path", "goods");
        OkGo.<PicBean>post(Urls.BASEURL + "api/v2/file/store")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new JsonCallback<PicBean>(PicBean.class) {
                    @Override
                    public void onSuccess(Response<PicBean> response) {
                        PicBean body = response.body();
                        if (body.getCode() == 1) {
                            picposition++;
                            stringBuilder.append(body.getData().getFileurl() + "@");
                            if (picposition == listAll.size()) {
                                String content = "";
                                String substring = stringBuilder.substring(0, stringBuilder.length() - 1);
                                stringBuilder = new StringBuilder();
                                stringBuilder.append(substring);
                                if (mytype == 0) {
                                    EditText content_et = (EditText) myOrderPingAdapter.getViewByPosition(recycler_pingjia, goodPosition, R.id.content_et);
                                    content = content_et.getText().toString();
                                    PingjiaPushBean pingjiaPushBean = new PingjiaPushBean();
                                    pingjiaPushBean.setContent(content);
                                    pingjiaPushBean.setGood_id(goods1.get(i).getGoods_id() + "");
                                    pingjiaPushBean.setImages(stringBuilder.toString());
                                    pingjiaPushBeanList.add(pingjiaPushBean);
                                } else {
                                    EditText content_et = (EditText) orderDetailPingAdapter.getViewByPosition(recycler_pingjia, goodPosition, R.id.content_et);
                                    content = content_et.getText().toString();
                                    PingjiaPushBean pingjiaPushBean = new PingjiaPushBean();
                                    pingjiaPushBean.setContent(content);
                                    pingjiaPushBean.setGood_id(goods2.get(i).getGoods_id() + "");
                                    pingjiaPushBean.setImages(stringBuilder.toString());
                                    pingjiaPushBeanList.add(pingjiaPushBean);
                                }
                                goodPosition++;
                                if (goodPosition == goodCount) {
                                    commitPingjia(order_id, new Gson().toJson(pingjiaPushBeanList));
                                } else {
                                    listAll = hashMap_photos.get(goodPosition);
                                    picposition = 0;
                                    stringBuilder = new StringBuilder();
                                    pushPhoto2(goodPosition);
                                }
                            } else {
                                String compressPath = listAll.get(picposition).getCompressPath();
                                File file = new File(compressPath);
                                new Compressor(PingJiaActivity.this)
                                        .compressToFileAsFlowable(file)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new Consumer<File>() {
                                            @Override
                                            public void accept(File file) {
                                                pushPhoto3(file, picposition);
                                            }
                                        }, new Consumer<Throwable>() {
                                            @Override
                                            public void accept(Throwable throwable) {
                                                throwable.printStackTrace();
                                            }
                                        });
                            }
                        } else {
                            TUtils.showShort(mContext, body.getMessage());
                        }
                    }
                });
    }

    private void pushPhoto1() {
        if (mytype == 0) {
            if (goods1 != null && goods1.size() > 0) {
                goodCount = goods1.size();
                for (int i = 0; i < goodCount; i++) {
                    EditText content_et = (EditText) myOrderPingAdapter.getViewByPosition(recycler_pingjia, i, R.id.content_et);
                    String content = content_et.getText().toString();
                    if (TextUtils.isEmpty(content)) {
                        Toast.makeText(this, "请填写第" + (i + 1) + "件商品的评价~", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                listAll = hashMap_photos.get(0);
                picposition = 0;
                stringBuilder = new StringBuilder();
                pushPhoto2(0);
            }
        } else {
            if (goods2 != null && goods2.size() > 0) {
                goodCount = goods2.size();
                for (int i = 0; i < goodCount; i++) {
                    EditText content_et = (EditText) orderDetailPingAdapter.getViewByPosition(recycler_pingjia, i, R.id.content_et);
                    String content = content_et.getText().toString();
                    if (TextUtils.isEmpty(content)) {
                        Toast.makeText(this, "请填写第" + (i + 1) + "件商品的评价~", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                listAll = hashMap_photos.get(0);
                picposition = 0;
                stringBuilder = new StringBuilder();
                pushPhoto2(0);
            }
        }
    }

    private void pushPhoto2(int i) {
        if (this.listAll != null && this.listAll.size() > 0) {
            for (int i1 = 0; i1 < this.listAll.size(); i1++) {
                String cutPath = this.listAll.get(i1).getCompressPath();
                File file = new File(cutPath);
                //压缩一下再上传，不然拍照基本都四五兆一张图片，上传太耗时间，而且服务器也有限制，不接受3M以上的图片
                final int finalI = i1;
                new Compressor(this)
                        .compressToFileAsFlowable(file)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<File>() {
                            @Override
                            public void accept(File file) {
                                pushPhoto3(file, finalI);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                                throwable.printStackTrace();
                            }
                        });
            }
        } else {
            if (mytype == 0) {
                PingjiaPushBean pingjiaPushBean = new PingjiaPushBean();
                EditText content_et = (EditText) myOrderPingAdapter.getViewByPosition(recycler_pingjia, i, R.id.content_et);
                String content = content_et.getText().toString();
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(this, "请填写第" + (i + 1) + "件商品的评价~", Toast.LENGTH_SHORT).show();
                    return;
                }
                pingjiaPushBean.setContent(content);
                pingjiaPushBean.setGood_id(goods1.get(i).getGoods_id() + "");
                pingjiaPushBean.setImages("");//服务器要这个字段，即便是空
                pingjiaPushBeanList.add(pingjiaPushBean);
            } else {
                PingjiaPushBean pingjiaPushBean = new PingjiaPushBean();
                EditText content_et = (EditText) orderDetailPingAdapter.getViewByPosition(recycler_pingjia, i, R.id.content_et);
                String content = content_et.getText().toString();
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(this, "请填写第" + (i + 1) + "件商品的评价~", Toast.LENGTH_SHORT).show();
                    return;
                }
                pingjiaPushBean.setContent(content);
                pingjiaPushBean.setGood_id(goods2.get(i).getGoods_id() + "");
                pingjiaPushBean.setImages("");//服务器要这个字段，即便是空
                pingjiaPushBeanList.add(pingjiaPushBean);
            }
            goodPosition++;
            if (goodPosition == goodCount) {
                commitPingjia(order_id, new Gson().toJson(pingjiaPushBeanList));
            } else {
                listAll = hashMap_photos.get(goodPosition);
                picposition = 0;
                stringBuilder = new StringBuilder();
                pushPhoto2(goodPosition);
            }
        }
    }

    private void commitPingjia(String order_id, String data) {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(this, "token", ""));
        HttpParams params = new HttpParams();
        params.put("order_id", order_id);
        params.put("data", data);
        OkGo.<BaseBean>post(Urls.BASEURL + "api/v2/user/order-good-evaluate")
                .tag(this)
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<BaseBean>(PingJiaActivity.this, BaseBean.class) {
                             @Override
                             public void onSuccess(Response<BaseBean> response) {
                                 int code = response.code();
                                 BaseBean body = response.body();
                                 if (body == null) {
                                     return;
                                 }
                                 Toast.makeText(PingJiaActivity.this, body.getMessage(), Toast.LENGTH_SHORT).show();
                                 setResult(1, new Intent());
                                 finish();
                             }

                             @Override
                             public void onError(Response<BaseBean> response) {
                                 super.onError(response);
                             }
                         }
                );
    }
}
