package net.zengxin.easytalker.smartbutler;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import net.zengxin.easytalker.smartbutler.fragment.ButlerFragment;
import net.zengxin.easytalker.smartbutler.fragment.GirlFragment;
import net.zengxin.easytalker.smartbutler.fragment.UserFragment;
import net.zengxin.easytalker.smartbutler.fragment.WechatFragment;
import net.zengxin.easytalker.smartbutler.ui.SettingActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    //TabLayout
    @BindView(R.id.mTabLayout)
    public TabLayout mTabLayout;
    //ViewPager
    @BindView(R.id.mViewPager)
    public ViewPager mViewPager;
    //Title
    private List<String> mTitle;
    //Fragment
    private List<Fragment> mFragment;
    //悬浮窗
    @BindView(R.id.fb_setting)
    public FloatingActionButton fb_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //去掉顶部阴影
        getSupportActionBar().setElevation(0);

        initData();
        initView();

    }

    private void initData() {
        mTitle = new ArrayList<>();
        mTitle.add(getString(R.string.title_butler));
        mTitle.add(getString(R.string.title_wechat));
        mTitle.add(getString(R.string.title_girl));
        mTitle.add(getString(R.string.title_user));

        mFragment = new ArrayList<>();
        mFragment.add(new ButlerFragment());
        mFragment.add(new WechatFragment());
        mFragment.add(new GirlFragment());
        mFragment.add(new UserFragment());

    }

    private void initView() {
        //设置viewpager的大小
        mViewPager.setOffscreenPageLimit(mFragment.size());

        fb_setting.setVisibility(View.GONE);
        //mViewPager滑动监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
               // Log.i("TAG","Position:"+position);
                if(position==0){
                    fb_setting.setVisibility(View.GONE);
                }else {
                    fb_setting.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //设置适配器
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            @Override
            public int getCount() {
                return mFragment.size();
            }

            //设置标题


            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle.get(position);
            }
        });

        //绑定
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @OnClick(R.id.fb_setting)
    public void ClickFB() {
        startActivity(new Intent(this, SettingActivity.class));
    }

}
