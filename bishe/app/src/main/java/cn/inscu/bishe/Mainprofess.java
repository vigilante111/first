package cn.inscu.bishe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class Mainprofess extends AppCompatActivity implements View.OnClickListener {
    cmtadapter cadapter;
String name;
    String oid;
String belong;
String school;
EditText e1;
Button b1;
Button like;
TextView t1;
TextView t2;
TextView  t3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, "99f6b2a370f20144d09e24c195ce958d");
        setContentView(R.layout.activity_mainprofess);
        Intent intent=getIntent();
        name=intent.getStringExtra("n");
        belong=intent.getStringExtra("b");
        school=intent.getStringExtra("s");
        ini();
        t1.setText(school);
        t2.setText(name);
        t3.setText(belong);
        inirc();
    }

    private void inirc() {
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerpp);
        LinearLayoutManager manager = new LinearLayoutManager(Mainprofess.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
       cadapter=new cmtadapter(R.layout.comment_item,null);
        cadapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        recyclerView.setAdapter(cadapter);
        BmobQuery<Comment> commetQuery = new BmobQuery<>();
        commetQuery.addWhereEqualTo("aprofess",name);
        commetQuery.findObjects(new FindListener<Comment>() {
            @Override
            public void done(List<Comment> object, BmobException e) {
                if (e == null) {
                    for (Comment comment : object) {
                        //获得playerName的信息
                       comment.getAcomment();
                       comment.getUsername();
                       comment.getUpdatedAt();
                    }
                    cadapter.setNewData(object);
                } else {
                }
            }
        });
    }


    private void ini() {
        like=(Button)findViewById(R.id.like);
        e1=(EditText)findViewById(R.id.ppl);
        b1=(Button)findViewById(R.id.ppb2);
        t1=(TextView)findViewById(R.id.ps);
        t2=(TextView)findViewById(R.id.ppn);
        t3=(TextView)findViewById(R.id.ppb);
        b1.setOnClickListener(this);
        like.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ppb2:
               final String cmt=e1.getText().toString();
                User user = BmobUser.getCurrentUser(User.class);
                String aname=user.getUsername();
                Comment ccc=new Comment();
                ccc.setUsername(aname);
                ccc.setAcomment(cmt);
                ccc.setAprofess(name);
                ccc.save(new SaveListener<String>() {
                    @Override
                    public void done(String objectId,BmobException e) {
                        if(e==null){
                            Toast.makeText(Mainprofess.this, "评论成功", Toast.LENGTH_SHORT).show();
                        }else{

                        }
                    }
                });
                inirc();
                break;
            case R.id.like:

                user = BmobUser.getCurrentUser(User.class);
                final String nn= user.getUsername();
              final String fullname=school+name;
              BmobQuery<prefer> perferQuery=new BmobQuery<>();
              perferQuery.addWhereEqualTo("user",nn);
                perferQuery.findObjects(new FindListener<prefer>() {
                    @Override
                    public void done(List<prefer> object, BmobException e) {
                        if (e == null) {
                            for (prefer prefer : object) {
                               oid=prefer.getObjectId();
                            }
                            //final String fullname=school+name;
                            prefer p=new prefer();
                            p.addUnique("likes",fullname);
                            p.update(oid,new UpdateListener() {
                                @Override
                                public void done(BmobException e) {
                                    if(e==null){
                                        Toast.makeText(Mainprofess.this, "收藏成功", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(Mainprofess.this, "更新失败", Toast.LENGTH_SHORT).show();
                                    }
                                }

                            });
                        }
                        else {   prefer p=new prefer();
                            p.setUser(nn);
                            p.addUnique("likes",fullname);
                            p.save(new SaveListener<String>() {
                                @Override
                                public void done(String objectId,BmobException e) {
                                    if(e==null){
                                        Toast.makeText(Mainprofess.this, "收藏成功", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(Mainprofess.this, "更新失败", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }
                });

                break;

        }
    }
}
