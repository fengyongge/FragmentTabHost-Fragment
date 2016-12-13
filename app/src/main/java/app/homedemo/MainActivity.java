package app.homedemo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import app.homedemo.fragment.Fragment1;
import app.homedemo.fragment.Fragment2;
import app.homedemo.fragment.Fragment3;
import app.homedemo.fragment.Fragment4;
import app.homedemo.fragment.Fragment5;

public class MainActivity extends FragmentActivity {
    /**
     * FragmentTabhost
     */
    private FragmentTabHost mTabHost,tabhost;

    /**
     * 布局填充器
     *
     */
    private LayoutInflater mLayoutInflater;

    /**
     * Fragment数组界面
     *
     */
    private Class mFragmentArray[] = { Fragment1.class, Fragment2.class,
            Fragment3.class, Fragment4.class, Fragment5.class };
    /**
     * 存放图片数组
     *
     */
    private int mImageArray[] = { R.drawable.tab_home_btn,
            R.drawable.tab_message_btn, R.drawable.tab_selfinfo_btn,
            R.drawable.tab_square_btn, R.drawable.tab_more_btn };

    /**
     * 选修卡文字
     *
     */
    private String mTextArray[] = { "首页", "消息", "好友", "搜索", "更多" };
    /**
     *
     *
     */
    private String TAB_TASK="工作",TAB_CUSTOM="会员",TAB_MESSAGE="消息",TAB_ME="我的";
    TabIndicatorView taskIndicator,cusstomIndicator,messageIndicator,mecoverIndicator;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init2();
    }

//    /**
//     * 初始化组件
//     */
//    private void initView() {
//        mLayoutInflater = LayoutInflater.from(this);
//
//        // 找到TabHost
//        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
//        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
//        // 得到fragment的个数
//        int count = mFragmentArray.length;
//        for (int i = 0; i < count; i++) {
//            // 给每个Tab按钮设置图标、文字和内容
//            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextArray[i])
//                    .setIndicator(getTabItemView(i));
//            // 将Tab按钮添加进Tab选项卡中
//            mTabHost.addTab(tabSpec, mFragmentArray[i], null);
//            // 设置Tab按钮的背景
//            mTabHost.getTabWidget().getChildAt(i)
//                    .setBackgroundResource(R.drawable.selector_tab_background);
//        }
//    }

    public void init2(){
        tabhost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabhost.setup(this, getSupportFragmentManager(),
                R.id.realtabcontent);

        TabHost.TabSpec spec = tabhost.newTabSpec(TAB_TASK);
        taskIndicator = new TabIndicatorView(this);
        taskIndicator.setTabIcon(R.drawable.main_task_out,
                R.drawable.main_task_on);
        taskIndicator.setTabTitle("工作");
        spec.setIndicator(taskIndicator);
        tabhost.addTab(spec, Fragment1.class, null);


        spec = tabhost.newTabSpec(TAB_CUSTOM);
        cusstomIndicator = new TabIndicatorView(this);
        cusstomIndicator.setTabIcon(R.drawable.main_member_out,
                R.drawable.main_member_on);
        cusstomIndicator.setTabTitle("会员");
        spec.setIndicator(cusstomIndicator);
        tabhost.addTab(spec, Fragment2.class, null);

        spec = tabhost.newTabSpec(TAB_MESSAGE);
        messageIndicator = new TabIndicatorView(this);
        messageIndicator.setTabIcon(R.drawable.main_message_out,
                R.drawable.main_message_on);
        messageIndicator.setTabTitle("消息");
        messageIndicator.setTabUnreadCount(10);
        spec.setIndicator(messageIndicator);
        tabhost.addTab(spec, Fragment3.class, null);

        spec = tabhost.newTabSpec(TAB_ME);
        mecoverIndicator = new TabIndicatorView(this);
        mecoverIndicator.setTabIcon(R.drawable.main_admin_out,
                R.drawable.main_admin_on);
        mecoverIndicator.setTabTitle("我的");
        spec.setIndicator(mecoverIndicator);
        tabhost.addTab(spec, Fragment4.class, null);

        // 去掉分割线
//        tabhost.getTabWidget().setDividerDrawable(android.R.color.white);
        tabhost.getTabWidget().setDividerDrawable(R.color.white);

        // 初始化 tab选中
        tabhost.setCurrentTabByTag(TAB_TASK);
        taskIndicator.setTabSelected(true);

        // 设置tab切换的监听

        tabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tag) {
                taskIndicator.setTabSelected(false);
                cusstomIndicator.setTabSelected(false);
                mecoverIndicator.setTabSelected(false);
                messageIndicator.setTabSelected(false);

                if (TAB_TASK.equals(tag)) {
                    taskIndicator.setTabSelected(true);
                } else if (TAB_CUSTOM.equals(tag)) {
                    cusstomIndicator.setTabSelected(true);
                } else if (TAB_MESSAGE.equals(tag)) {
                    messageIndicator.setTabSelected(true);
                } else if (TAB_ME.equals(tag)) {
                    mecoverIndicator.setTabSelected(true);
                }
            }
        });

    }

    /**
     *
     * 给每个Tab按钮设置图标和文字
     */
    private View getTabItemView(int index) {
        View view = mLayoutInflater.inflate(R.layout.tab_item_view, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageArray[index]);
        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTextArray[index]);

        return view;
    }

}
