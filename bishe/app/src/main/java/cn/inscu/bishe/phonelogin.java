package cn.inscu.bishe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

public class phonelogin extends AppCompatActivity implements View.OnClickListener{
    private EditText phonenumber;
    private EditText check;
    private Button login;
    private Button sendto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonelogin);
        Bmob.initialize(this, "99f6b2a370f20144d09e24c195ce958d");
        login=(Button)findViewById(R.id.buttonphone);
        phonenumber=(EditText)findViewById(R.id.pn);
        check=(EditText)findViewById(R.id.checkword);
        sendto=(Button)findViewById(R.id.send);
        login.setOnClickListener(this);
        sendto.setOnClickListener(this);
    }
    private void sendms(){
        final String phone=phonenumber.getText().toString();
        BmobSMS.requestSMSCode(phone, "志愿", new QueryListener<Integer>() {
            @Override
            public void done(Integer smsId, BmobException e) {
                if (e == null) {
                    Toast.makeText(phonelogin.this, "发送验证码成功，短信ID：" + smsId + "\n", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(phonelogin.this, "发送验证码失败：" + e.getErrorCode() + "-" + e.getMessage() + "\n", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void plogin(){
        final String phone=phonenumber.getText().toString();
        final String code=check.getText().toString();
        BmobSMS.verifySmsCode(phone, code, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Toast.makeText(phonelogin.this, "验证码验证成功，您可以在此时进行绑定操作！\n", Toast.LENGTH_SHORT).show();
                    User user = BmobUser.getCurrentUser(User.class);
                    user.setMobilePhoneNumber(phone);
                    user.setMobilePhoneNumberVerified(true);
                    user.update(new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                Toast.makeText(phonelogin.this, "绑定手机号码成功", Toast.LENGTH_SHORT).show();
                                Intent intent1 = new Intent(phonelogin.this,MainActivity.class);
                                startActivity(intent1);
                            } else {
                                Toast.makeText(phonelogin.this, "绑定手机号码失败：手机号已被注册" + e.getErrorCode() + "-" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(phonelogin.this, "验证码验证失败：" + e.getErrorCode() + "-" + e.getMessage() + "\n", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonphone:
                plogin();
                break;
            case R.id.send:
                sendms();
                break;
        }
    }
}
