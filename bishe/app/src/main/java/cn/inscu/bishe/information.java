package cn.inscu.bishe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class information extends AppCompatActivity implements View.OnClickListener {
    private  static final String[] l={"","985高校","211高校","双非"};
   private static final String[] m={"","哲学","经济学","法学","教育学","文学","历史学","理学","工学","农学","医学","军事学","管理学","艺术学"};
   private static final String[] n={"","北京","天津","上海","重庆","河北","山西","辽宁","吉林","黑龙江","江苏","浙江","安徽","福建","江西","山东","河南","湖北","湖南","广东","海南","四川","贵州","云南","陕西","甘肃","青海","台湾"};
   private TextView view;
   private  int score0;
   private String temp1;
   private String temp2;
   private String temp3;
   private TextView view1;
   private TextView view2;
   private EditText point;
   private Button button1;
   private Button button2;
   private Spinner spin;
   private Spinner spin1;
   private Spinner spin2;
   private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> adapter1;
    private ArrayAdapter<String> adapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        point=(EditText)findViewById(R.id.e1);
        button1=(Button)findViewById(R.id.b0);
        button2=(Button)findViewById(R.id.b1);
        view=(TextView)findViewById(R.id.spinnertext);
        view1=(TextView)findViewById(R.id.spinnertext2);
        view2=(TextView)findViewById(R.id.spinnertext3);
        spin1=(Spinner)findViewById(R.id.spinner2);
        spin=(Spinner)findViewById(R.id.spinner);
        spin2=(Spinner)findViewById(R.id.spinner3);
        adapter=new ArrayAdapter<String>(this,R.layout.myspinneritem,m);
        adapter1=new ArrayAdapter<String>(this,R.layout.myspinneritem,n);
        adapter2=new ArrayAdapter<String>(this,R.layout.myspinneritem,l);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(adapter1);
        spin2.setAdapter(adapter2);
        spin.setAdapter(adapter);
        spin1.setOnItemSelectedListener(new SpinnerSelectedListener1());
        spin2.setOnItemSelectedListener(new SpinnerSelectedListener2());
        spin.setOnItemSelectedListener(new SpinnerSelectedListener());
        spin.setVisibility(View.VISIBLE);
        spin1.setVisibility(View.VISIBLE);
        spin2.setVisibility(View.VISIBLE);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }
    private void updateUser() {
        final User user = BmobUser.getCurrentUser(User.class);
        user.setArea(temp1);
        user.setScore(score0);
        user.setProfess(temp2);
        user.setLevel(temp3);
        user.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Toast.makeText(information.this, "提交成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(information.this, "提交失败", Toast.LENGTH_SHORT).show();
                    Log.e("error", e.getMessage());
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.b0:
               score0= Integer.parseInt(point.getText().toString());
                break;
            case R.id.b1:
                updateUser();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;

        }
    }

    private class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long iarg3) {
            view.setText("选择你喜欢的专业方向  "+m[arg2]);
            temp1=m[arg2];
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {

        }
    }
    private class SpinnerSelectedListener1 implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long iarg3) {
            view1.setText("选择你想去的地区  "+n[arg2]);
            temp2=n[arg2];
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {

        }
    }
    private class SpinnerSelectedListener2 implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long iarg3) {
            view2.setText("选择高校的档次  "+l[arg2]);
            temp3=l[arg2];
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {

        }
    }

}
