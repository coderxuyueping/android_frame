package com.example.admin.view.activity;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.base.AppStatus;
import com.example.admin.base.BaseActivity;
import com.example.admin.utils.ToastUtil;
import com.example.admin.view.adapter.base.CommonAdapter;
import com.example.admin.view.adapter.base.MultiItemTypeAdapter;
import com.example.admin.view.adapter.base.CommonViewHolder;
import com.example.admin.xyp_android_frame.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class Main3Activity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private List<String> data = new ArrayList<>();
    private MultiItemTypeAdapter commonAdapter;

    @Override
    public String initTitle() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main3;
    }

    @Override
    protected void init() {
        for (int i = 0; i < 10; i++) {
            data.add("数据" + i);
        }
        initMulti();
    }

    private void initCommon() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CommonAdapter<String>(data, R.layout.item_view) {
            @Override
            public void bindHolder(CommonViewHolder viewHolder, int itemType, String s) {
                TextView textView = (TextView) viewHolder.getView(R.id.tv);
                textView.setText(s);
            }
        });
    }

    private void initMulti() {
        commonAdapter = new MultiItemTypeAdapter<String>(data) {
            @Override
            public int getItemType(int position) {
                if (position == 0)
                    return 1;
                if (position == getItemCount() - 1)
                    return 2;
                return 0;
            }

            @Override
            public CommonViewHolder createHolder(ViewGroup parent, int viewType) {
                int layoutId = 0;
                if (viewType == 0)
                    layoutId = R.layout.item_view;
                else if (viewType == 1)
                    layoutId = R.layout.item_head;
                else if (viewType == 2)
                    layoutId = R.layout.item_foot;
                return CommonViewHolder.createViewHolder(parent, layoutId);
            }

            @Override
            public int getTypeCount() {
                return 2;
            }

            @Override
            public int getRealPotion(int position) {
                int realPotion = -1;
                if (getItemType(position) == 0)
                    realPotion = position - 1;
                return realPotion;
            }

            @Override
            public boolean isFullSpan(int position) {
                return false;
            }

            @Override
            public void bindHolder(CommonViewHolder viewHolder, int itemType, final String s) {
                if (itemType == 0) {
                    TextView textView = (TextView) viewHolder.getView(R.id.tv);
                    textView.setText(s);
                    viewHolder.getRootView().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ToastUtil.show(AppStatus.getInstance(), s);
                        }
                    });
                } else if (itemType == 1) {
                    Button headBtn = (Button) viewHolder.getView(R.id.head_btn);
                    headBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ToastUtil.show(AppStatus.getInstance(), "我是头");
                            data.remove(1);
                            notifyDataSetChanged();
                        }
                    });
                } else if (itemType == 2) {
                    Button footBtn = (Button) viewHolder.getView(R.id.foot_btn);
                    footBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ToastUtil.show(AppStatus.getInstance(), "我是尾");
                        }
                    });
                }
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(commonAdapter);
    }

    @OnClick(R.id.add)
    public void add() {
        data.add("新增数据");
        commonAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.dump)
    public void dump() {
        startActivity(Main4Activity.class);
    }
}
