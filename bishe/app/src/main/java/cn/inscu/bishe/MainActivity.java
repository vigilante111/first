package cn.inscu.bishe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    sadapter adapter;
    workadaper adapter2;
    private  Button selfb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bmob.initialize(this, "99f6b2a370f20144d09e24c195ce958d");
        ini();
        inirv();
        largeequal();
        inipro();
    }

    private void inipro() {
        BmobQuery<Profess> professQuery = new BmobQuery<>();
        professQuery.addWhereGreaterThanOrEqualTo("money",1000);
      professQuery.findObjects(new FindListener<Profess>() {
            @Override
            public void done(List<Profess> object, BmobException e) {
                if (e == null) {
                    for (Profess profess : object) {
                        //获得playerName的信息
                        profess.getName();
                        profess.getBelong();
                        profess.getMoney();
                    }
                    adapter2.setNewData(object);
                } else {
                }
            }
        });
    }

    private void ini(){
        selfb=(Button)findViewById(R.id.selfcheck);
        selfb.setOnClickListener(this);
    }
    private void inirv(){
        RecyclerView recyclerView2=(RecyclerView)findViewById(R.id.recycler2);
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler);
        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager manager2 = new LinearLayoutManager(MainActivity.this);
        manager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        recyclerView2.setLayoutManager(manager2);
         adapter = new sadapter(R.layout.school_item,null );
         adapter2=new workadaper(R.layout.professitem,null);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        adapter2.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        recyclerView.setAdapter(adapter);
       recyclerView2.setAdapter(adapter2);
       adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
           @Override
           public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
              // Toast.makeText(MainActivity.this, "dianji", Toast.LENGTH_SHORT).show();
               School item= (School) adapter.getItem(position);
               String i1=item.getImage1();
               String i2=item.getImage2();
               String i3=item.getImage3();
               String sname=item.getSchoolname();
               String sl=item.getLevel();
               String in=item.getIntroduction();
               Intent intent1 = new Intent(MainActivity.this, MainSchool.class);
               intent1.putExtra("i1",i1);
               intent1.putExtra("i2",i2);
               intent1.putExtra("i3",i3);
               intent1.putExtra("lv",sl);
               intent1.putExtra("intro",in);
               intent1.putExtra("schname",sname);
               startActivity(intent1);
           }
       });
    }
    private void largeequal(){
           final User user = BmobUser.getCurrentUser(User.class);
        int temp=user.getScore();
        BmobQuery<School> schoolQuery = new BmobQuery<>();
          schoolQuery.addWhereLessThanOrEqualTo("lowestpoint",temp);
        schoolQuery.findObjects(new FindListener<School>() {
            @Override
            public void done(List<School> object, BmobException e) {
                if (e == null) {
                    for (School school : object) {
                        //获得playerName的信息
                        school.getImage1();
                       school.getImage2();
                        school.getImage3();
                        school.getIntroduction();
                        school.getLevel();
                        school.getSchoolname();
                       school.getSchoolimage();
                    }
                    adapter.setNewData(object);
                } else {
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.selfcheck:
                Intent intent = new Intent(this, information.class);
                startActivity(intent);
        }
    }
}
