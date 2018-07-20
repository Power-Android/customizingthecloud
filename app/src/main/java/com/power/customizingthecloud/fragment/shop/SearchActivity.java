package com.power.customizingthecloud.fragment.shop;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.power.customizingthecloud.R;
import com.power.customizingthecloud.base.BaseActivity;
import com.power.customizingthecloud.bean.SearchHistoryEntity;
import com.power.customizingthecloud.utils.SoftKeyboardTool;
import com.power.customizingthecloud.utils.SpUtils;
import com.power.customizingthecloud.view.FluidLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.power.customizingthecloud.R.id.et_search;
import static com.power.customizingthecloud.R.id.ll_hot;

public class SearchActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(et_search)
    EditText mEtSearch;
    @BindView(R.id.tv_search)
    TextView mTvSearch;
    @BindView(R.id.liushiview)
    FluidLayout liushiview;
    @BindView(ll_hot)
    LinearLayout mLlHot;
    private int position;
    private List<SearchHistoryEntity> mHistoryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        mIvBack.setOnClickListener(this);
        mTvSearch.setOnClickListener(this);
        initListener();
    }

    private void initListener() {
        mEtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (TextUtils.isEmpty(mEtSearch.getText().toString())) {
                        Toast.makeText(SearchActivity.this, "搜索内容不能为空！", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    SoftKeyboardTool.closeKeyboard(SearchActivity.this);
                    Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                    intent.putExtra("keyword", mEtSearch.getText().toString());
                    startActivity(intent);
                    doSavehistory(mEtSearch.getText().toString());
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        mHistoryList = getHistory();//从本地取出来
        if (mHistoryList != null && mHistoryList.size() > 0) {
            liushiview.removeAllViews();
            for (int i = 0; i < mHistoryList.size(); i++) {
                final TextView tv = (TextView) View.inflate(this, R.layout.search_hot_item, null);
                tv.setText(mHistoryList.get(i).getContent());
                FluidLayout.LayoutParams params = new FluidLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(12, 12, 12, 12);
                liushiview.addView(tv, params);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                        intent.putExtra("keyword", tv.getText().toString());
                        startActivity(intent);
                    }
                });
            }
        }
    }

    //判断本地数据中有没有存在搜索过的数据，查重
    private boolean isHasSelectData(String content) {
        if (mHistoryList == null || mHistoryList.size() == 0) {
            return false;
        }
        for (int i = 0; i < mHistoryList.size(); i++) {
            if (mHistoryList.get(i).getContent().equals(content)) {
                position = i;
                return true;
            }
        }
        return false;
    }

    private void doSavehistory(String content) {

        if (isHasSelectData(content)) {//查重
            mHistoryList.remove(position);
        }
        //后来搜索的文字放在集合中的第一个位置
        mHistoryList.add(0, new SearchHistoryEntity(content));

        //        if (mHistoryList.size() == 6) {//实现本地历史搜索记录最多不超过5个
        //            mHistoryList.remove(5);
        //        }
        //将这个mHistoryListData保存到sp中，其实sp中保存的就是这个mHistoryListData集合
        saveHistory();
    }

    /**
     * 保存历史查询记录
     */
    private void saveHistory() {
        SpUtils.putString(this, "history",
                new Gson().toJson(mHistoryList));//将java对象转换成json字符串进行保存
    }

    /**
     * 获取历史查询记录
     *
     * @return
     */
    private List<SearchHistoryEntity> getHistory() {
        String historyJson = SpUtils.getString(this, "history", "");
        if (historyJson != null && !historyJson.equals("")) {//必须要加上后面的判断，因为获取的字符串默认值就是空字符串
            //将json字符串转换成list集合
            return new Gson().fromJson(historyJson, new TypeToken<List<SearchHistoryEntity>>() {
            }.getType());
        }
        return new ArrayList<SearchHistoryEntity>();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_search:
                if (TextUtils.isEmpty(mEtSearch.getText().toString())) {
                    Toast.makeText(this, "搜索内容不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                SoftKeyboardTool.closeKeyboard(SearchActivity.this);
                Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                intent.putExtra("keyword", mEtSearch.getText().toString());
                startActivity(intent);
                doSavehistory(mEtSearch.getText().toString());
                break;
        }
    }
}
