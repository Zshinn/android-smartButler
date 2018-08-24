package net.zengxin.easytalker.smartbutler.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import net.zengxin.easytalker.smartbutler.R;
import net.zengxin.easytalker.smartbutler.entity.MyUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisteredActivity extends BaseActivity {
    @BindView(R.id.et_user)
    public EditText et_user;
    @BindView(R.id.et_age)
    public EditText et_age;
    @BindView(R.id.et_desc)
    public EditText et_desc;
    @BindView(R.id.mRadioGroup)
    public RadioGroup mRadioGroup;
    @BindView(R.id.et_pass)
    public EditText et_pass;
    @BindView(R.id.et_password)
    public EditText et_password;
    @BindView(R.id.et_email)
    public EditText et_email;
    @BindView(R.id.btnRegistered)
    public Button btnRegistered;
    //性别
    private boolean isGender = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);

        ButterKnife.bind(this);


    }

    @OnClick(R.id.btnRegistered)
    public void click(){
        //获取到输入框的值
        String name = et_user.getText().toString().trim();
        String age = et_age.getText().toString().trim();
        String desc = et_desc.getText().toString().trim();
        String pass = et_pass.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        String email = et_email.getText().toString().trim();

        //判断是否为空
        if (!TextUtils.isEmpty(name) & !TextUtils.isEmpty(age) &
                !TextUtils.isEmpty(pass) &
                !TextUtils.isEmpty(password) &
                !TextUtils.isEmpty(email)) {

            //判断两次输入的密码是否一致
            if (pass.equals(password)) {

                //先把性别判断一下
                mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (checkedId == R.id.rb_boy) {
                            isGender = true;
                        } else if (checkedId == R.id.rb_girl) {
                            isGender = false;
                        }
                    }
                });

                //判断简介是否为空
                if (TextUtils.isEmpty(desc)) {
                    desc = getString(R.string.text_nothing);
                }

                //注册
                MyUser user = new MyUser();
                user.setUsername(name);
                user.setPassword(password);
                user.setEmail(email);
                user.setAge(Integer.parseInt(age));
                user.setSex(isGender);
                user.setDesc(desc);

                user.signUp(new SaveListener<MyUser>() {
                    @Override
                    public void done(MyUser myUser, BmobException e) {
                        if(e==null){
                            Toast.makeText(RegisteredActivity.this, R.string.text_registered_successful, Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(RegisteredActivity.this, getString(R.string.text_registered_failure) + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                Toast.makeText(this, R.string.text_two_input_not_consistent, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, R.string.text_tost_empty, Toast.LENGTH_SHORT).show();
        }


    }
}
