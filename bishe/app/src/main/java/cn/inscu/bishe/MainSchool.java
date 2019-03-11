package cn.inscu.bishe;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainSchool extends AppCompatActivity implements View.OnClickListener {
   private Button b1;
    String name;
    String g1;
    String g2;
    String g3;
    String level;
    String introduction;
    private TextView t1;
    private TextView t2;
   private Button b2;
   private Button b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_school);
        Intent intent=getIntent();
        introduction=intent.getStringExtra("intro");
       name=intent.getStringExtra("schname");
       level=intent.getStringExtra("lv");
       g1=intent.getStringExtra("i1");
        g2=intent.getStringExtra("i2");
        g3=intent.getStringExtra("i3");
        ini();
      inifrag();
    }
    private void inifrag(){
        Fragment1 f1= new Fragment1();
        replaceFragment(f1);
        Bundle bundle = new Bundle();
        bundle.putString("i1",g1);
        bundle.putString("i2",g2);
        bundle.putString("i3",g3);
        bundle.putString("iiii", introduction);
        f1.setArguments(bundle);
    }

    private  void  ini(){
        t1=(TextView)findViewById(R.id.ss1);
        t1.setText(name);
        t2=(TextView)findViewById(R.id.ss2);
        t2.setText(level);
        b1=(Button)findViewById(R.id.jianjie);
        b2=(Button)findViewById(R.id.mulu);
        b3=(Button)findViewById(R.id.liuyan);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.jianjie:
               Fragment1 f1= new Fragment1();
                replaceFragment(f1);
                Bundle bundle = new Bundle();
                bundle.putString("i1",g1);
                bundle.putString("i2",g2);
                bundle.putString("i3",g3);
                bundle.putString("iiii", introduction);
                f1.setArguments(bundle);
                break;
            case R.id.mulu:
                replaceFragment(new Fragment2());
                Bundle bundle1 = new Bundle();
                bundle1.putString("name",name);
                break;
            case R.id.liuyan:
                replaceFragment(new Fragment3());
                break;
        }
    }
    public void  replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.framex,fragment);
        transaction.commit();

    }
}
