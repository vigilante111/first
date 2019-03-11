package cn.inscu.bishe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class signActivity extends AppCompatActivity implements View.OnClickListener{
 private Button b;
 private EditText e1;
    private EditText e2;
    private EditText e3;
    private EditText e4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, "99f6b2a370f20144d09e24c195ce958d");
        setContentView(R.layout.activity_sign);
        b=(Button)findViewById(R.id.b1);
        e1=(EditText)findViewById(R.id.n1);
        e2=(EditText)findViewById(R.id.p1);
        e3=(EditText)findViewById(R.id.p2);
        e4=(EditText)findViewById(R.id.a1);
        b.setOnClickListener(this);
    }
private void signin(){
    final User user = new User();
    String a=e1.getText().toString();
    String b=e2.getText().toString();
    String c=e3.getText().toString();
    String d=e4.getText().toString();
    user.setUsername(a );
    user.setPassword(b );
    user.setMobilePhoneNumber(c);
    user.setEmail(d);
    user.signUp(new SaveListener<User>() {
        @Override
        public void done(User s, BmobException e) {
            if(e==null){
                Toast.makeText(signActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(signActivity.this,login.class);
                startActivity(intent);
            }else{
                Toast.makeText(signActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
            }
        }


    });

}
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.b1:
               signin();
                break;
        }
    }
}
