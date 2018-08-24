package net.zengxin.easytalker.smartbutler.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.zengxin.easytalker.smartbutler.MainActivity;
import net.zengxin.easytalker.smartbutler.R;
import net.zengxin.easytalker.smartbutler.entity.MyUser;
import net.zengxin.easytalker.smartbutler.utils.ShareUtils;
import net.zengxin.easytalker.smartbutler.view.CustomDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.btn_registered)
    public Button btn_registered;
    @BindView(R.id.et_name)
    public EditText et_name;
    @BindView(R.id.et_password)
    public EditText et_password;
    @BindView(R.id.btn_Login)
    public Button btn_Login;
    @BindView(R.id.keep_password)
    public CheckBox keep_password;
    @BindView(R.id.tv_forget)
    public TextView tv_forget;

    private CustomDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        
        initView();
    }

    private void initView() {
        boolean isKeep= ShareUtils.getBoolean(this,"keeppass",false);
        keep_password.setChecked(isKeep);



        dialog = new CustomDialog(this, 100, 100, R.layout.dialog_loding, R.style.Theme_dialog, Gravity.CENTER,R.style.pop_anim_style);
        dialog.setCancelable(false);


        if(isKeep){
            String name = ShareUtils.getString(this, "name", "");
            String password = ShareUtils.getString(this, "password", "");
            et_name.setText(name);
            et_password.setText(password);
        }

    }

    @OnClick(R.id.btn_Login)
    public void clickLogin(){
        //1.获取输入框的值
        String name = et_name.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        //2.判断是否为空
        if (!TextUtils.isEmpty(name) & !TextUtils.isEmpty(password)) {
            dialog.show();
            //登录
            final MyUser user = new MyUser();
            user.setUsername(name);
            user.setPassword(password);
            user.login(new SaveListener<MyUser>() {
                @Override
                public void done(MyUser myUser, BmobException e) {
                    dialog.dismiss();
                    //判断结果
                    if (e == null) {
                        //判断邮箱是否验证
                        if (user.getEmailVerified()) {
                            //跳转
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "请前往邮箱验证", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "登录失败：" + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(this, "输入框不能为空", Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick(R.id.btn_registered)
    public void clickRegister(){
        startActivity(new Intent(this, RegisteredActivity.class));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        //保存状态
        ShareUtils.putBoolean(this, "keeppass", keep_password.isChecked());

        //是否记住密码
        if (keep_password.isChecked()) {
            //记住用户名和密码
            ShareUtils.putString(this, "name", et_name.getText().toString().trim());
            ShareUtils.putString(this, "password", et_password.getText().toString().trim());
        } else {
            ShareUtils.deletShare(this, "name");
            ShareUtils.deletShare(this, "password");
        }

    }
}
