package net.zengxin.easytalker.smartbutler.ui;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import net.zengxin.easytalker.smartbutler.R;
import net.zengxin.easytalker.smartbutler.adapter.CourierAdapter;
import net.zengxin.easytalker.smartbutler.entity.CourierData;
import net.zengxin.easytalker.smartbutler.utils.L;
import net.zengxin.easytalker.smartbutler.utils.StaticClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CourierActivity extends Activity {
    @BindView(R.id.et_name)
    public EditText et_name;

    @BindView(R.id.et_number)
    public EditText et_number;

    @BindView(R.id.mListView)
    public ListView mListView;

    private List<CourierData> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courier);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_get_courier)
    public void onClick(View v) {
        /**
         * 1.获取输入框的内容
         * 2.判断是否为空
         * 3.拿到数据去请求数据（Json）
         * 4.解析Json
         * 5.listview适配器
         * 6.实体类（item）
         * 7.设置数据/显示效果
         */

        //1.获取输入框的内容
        String name = et_name.getText().toString().trim();
        String number = et_number.getText().toString().trim();

        //拼接我们的url
        String url = "http://v.juhe.cn/exp/index?key=" + StaticClass.COURIER_KEY
                + "&com=" + name + "&no=" + number;

        //2.判断是否为空
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(number)) {
            //3.拿到数据去请求数据（Json）
            RxVolley.get(url, new HttpCallback() {
                @Override
                public void onSuccess(String t) {
                    L.i("Courier:" + t);
                    //4.解析Json
                    parsingJson(t);
                }
            });
        } else {
            Toast.makeText(this, getString(R.string.text_tost_empty), Toast.LENGTH_SHORT).show();
        }

    }



    //解析数据
    private void parsingJson(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONObject jsonResult = jsonObject.getJSONObject("result");
            JSONArray jsonArray = jsonResult.getJSONArray("list");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = (JSONObject) jsonArray.get(i);

                CourierData data = new CourierData();
                data.setRemark(json.getString("remark"));
                data.setZone(json.getString("zone"));
                data.setDatetime(json.getString("datetime"));
                mList.add(data);
            }
            //倒序
            Collections.reverse(mList);
            CourierAdapter adapter = new CourierAdapter(this,mList);
            mListView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



}
