package com.power.customizingthecloud.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.power.customizingthecloud.adapter.GridViewAddImgesAdapter;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.BaseBean;
import com.power.customizingthecloud.callback.DialogCallback;
import com.power.customizingthecloud.fragment.market.bean.UploadPhotoBean;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.utils.Urls;
import com.power.customizingthecloud.view.MyGridView;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.NormalSelectionDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.zelory.compressor.Compressor;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FeedbackActivity extends BaseActivity {

    @BindView(R.id.content_et)
    EditText contentEt;
    @BindView(R.id.uppic_iv)
    MyGridView gridview;
    @BindView(R.id.phone_et)
    EditText phoneEt;
    @BindView(R.id.commit_tv)
    TextView commitTv;
    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    private List<String> cameraList;
    private List<LocalMedia> selectList = new ArrayList<>();
    List<LocalMedia> list = new ArrayList<>();
    List<LocalMedia> listAll = new ArrayList<>();
    private GridViewAddImgesAdapter addImgesAdpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("意见反馈");

        /**
         * 添加照片adapter
         */
        addImgesAdpter = new GridViewAddImgesAdapter(list, this);
        gridview.setAdapter(addImgesAdpter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                cameraList = new ArrayList<>();
                cameraList.add("从相册中选择");
                cameraList.add("拍照");
                showCamera();
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
        PictureSelector.create(FeedbackActivity.this)
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
        PictureSelector.create(FeedbackActivity.this)
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
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    listAll.addAll(selectList);
                    selectList.clear();
                    addImgesAdpter.setList(listAll);
                    addImgesAdpter.notifyDataSetChanged();
                    break;
            }
        }
    }

    @OnClick({R.id.commit_tv, R.id.title_back_iv, R.id.title_content_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.commit_tv:
                //                if (!TextUtils.isEmpty(phoneEt.getText().toString())){
                //                    boolean mobileNO = MyUtils.isMobileNO(phoneEt.getText().toString());
                //                    if (!mobileNO){
                //                        Toast.makeText(this, "请输入正确格式的手机号~", Toast.LENGTH_SHORT).show();
                //                        return;
                //                    }
                //                }
                photoCount = 0;
                if (listAll != null && listAll.size() > 0) {
                    commit(listAll.get(0).getPath());
                } else {
                    submit("", false);
                }
                break;
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.title_content_tv:
                break;
        }
    }

    private int photoCount;
    private StringBuilder stringBuilder = new StringBuilder();

    //同步上传
    private void commit(String path) {
        //压缩一下再上传，不然拍照基本都四五兆一张图片，上传太耗时间，而且服务器也有限制，不接受3M以上的图片
        new Compressor(this)
                .compressToFileAsFlowable(new File(path))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<File>() {
                    @Override
                    public void accept(File file) {
                        pushPhoto(file);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    private void pushPhoto(File file) {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(this, "token", ""));
        HttpParams params = new HttpParams();
        params.put("file", file);
        params.put("path", "feed");
        OkGo.<UploadPhotoBean>post(Urls.BASEURL + "api/v2/file/store")
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<UploadPhotoBean>(FeedbackActivity.this, UploadPhotoBean.class) {
                    @Override
                    public void onSuccess(Response<UploadPhotoBean> response) {
                        UploadPhotoBean photoBean = response.body();
                        int code = photoBean.getCode();
                        if (code == 0) {
                            Toast.makeText(FeedbackActivity.this, photoBean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            photoCount++;
                            stringBuilder.append(photoBean.getData().getFileurl() + "@");
                            if (photoCount == listAll.size()) {
                                //图片全部上传完毕
                                String substring = stringBuilder.substring(0, stringBuilder.length() - 1);
                                stringBuilder = new StringBuilder();
                                stringBuilder.append(substring);
                                submit(stringBuilder.toString(), true);
                            } else {
                                commit(listAll.get(photoCount).getPath());
                            }
                        }
                    }

                    @Override
                    public void onError(Response<UploadPhotoBean> response) {
                        super.onError(response);
                    }
                });
    }

    private void submit(String images, boolean b) {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", "Bearer " + SpUtils.getString(this, "token", ""));
        HttpParams params = new HttpParams();
        params.put("content", contentEt.getText().toString());
        if (!TextUtils.isEmpty(phoneEt.getText().toString())) {
            params.put("mobile", phoneEt.getText().toString());
        }
        if (b)
            params.put("images", images);
        OkGo.<BaseBean>post(Urls.BASEURL + "api/v2/kefu/feed-back")
                .headers(headers)
                .params(params)
                .execute(new DialogCallback<BaseBean>(FeedbackActivity.this, BaseBean.class) {
                    @Override
                    public void onSuccess(Response<BaseBean> response) {
                        BaseBean bean = response.body();
                        int code = bean.getCode();
                        if (code == 0) {
                            Toast.makeText(FeedbackActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            Toast.makeText(FeedbackActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
    }
}
