package cn.inscu.bishe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class login extends AppCompatActivity implements View.OnClickListener {
    private Button b1;
    private Button b2;
    private Button b3;
    private EditText e1;
    private EditText e2;
    private CheckBox c1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       Bmob.initialize(this, "99f6b2a370f20144d09e24c195ce958d");
        setContentView(R.layout.activity_login);
        b1=(Button)findViewById(R.id.button1);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
        e1=(EditText)findViewById(R.id.zhanghao);
        e2=(EditText)findViewById(R.id.password);
        c1=(CheckBox)findViewById(R.id.remember);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
    }
    private void login() {
    final BmobUser user = new BmobUser();;
        //此处替换为你的用户名
      final String z=e1.getText().toString();
      final String p=e2.getText().toString();
        user.setUsername(z);
        //此处替换为你的密码
        user.setPassword(p);
        user.login(new SaveListener<User>() {
            @Override
            public void done(User bmobUser, BmobException e) {
                if (e == null) {
                    Toast.makeText(login.this, "成功", Toast.LENGTH_SHORT).show();
                    Intent intent3 = new Intent(login.this, MainActivity.class);
                    startActivity(intent3);

                } else {
                    Toast.makeText(login.this, "失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
            login();
                break;
            case R.id.button2:
                Intent intent = new Intent(this, signActivity.class);
                startActivity(intent);
                break;
            case R.id.button3:
                Intent intent1 = new Intent(this, phonelogin.class);
                startActivity(intent1);
        }
    }
}
