package net.zengxin.easytalker.smartbutler.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.zengxin.easytalker.smartbutler.R;

import butterknife.ButterKnife;

/**
 * 引导页
 */
public class GuideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        ButterKnife.bind(this);

    }
}
