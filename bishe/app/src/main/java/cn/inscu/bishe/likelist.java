package cn.inscu.bishe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.bmob.v3.AsyncCustomEndpoints;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.CloudCodeListener;
import cn.bmob.v3.listener.FindListener;

public class likelist extends AppCompatActivity {
    professadapter zyadapter;
    professadapter zyadapter2;
    int total;
    List<String> ansitems=new ArrayList<>();
    List<String> like;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_likelist);
        first();
        getans();
       secend();
    }

    private void getans() {
        User user = BmobUser.getCurrentUser(User.class);
        final String n= user.getUsername();
        BmobQuery<prefer> preferQuery = new BmobQuery<>();
        preferQuery.findObjects(new FindListener<prefer>() {
            @Override
            public void done(List<prefer> object, BmobException e) {
                total= object.size();
                JSONObject params = new JSONObject();
//name是上传到云端的参数名称，值是bmob，云函数可以通过调用request.body.name获取这个值
                try {
                    params.put("name",n);
                    params.put("total",total);
                    Toast.makeText(likelist.this, ""+total, Toast.LENGTH_SHORT).show();
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
//创建云函数对象
                AsyncCustomEndpoints cloudCode = new AsyncCustomEndpoints();
//异步调用云函数
                cloudCode.callEndpoint( "javatest" , params, new CloudCodeListener() {
                    @Override
                    public void done(Object o, BmobException e) {

                        JSONObject params1 = null;
                        try {
                            params1 = new JSONObject(o.toString());
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                        try {
                            JSONArray temp =  params1.getJSONArray("aaa");
                            for (int i = 0; i < temp.length(); i++) {
                                //提取出family中的所有
                                String s1 = (String)temp.get(i);
                                ansitems.add(s1);
                            }
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                        BmobQuery<Profess> pro=new BmobQuery<>();
                        pro.addWhereContainedIn("objectId", ansitems);
                        pro.findObjects(new FindListener<Profess>() {
                            @Override
                            public void done(List<Profess> list, BmobException e) {
                                if(e==null){
                                    for(Profess p:list){
                                        p.getBelong();
                                        p.getMoney();
                                        p.getSchool() ;
                                        p.getName();
                                        p.getScore();
                                    }
                                    zyadapter2.addData(list);
                                }
                            }
                        });
                    }
                });
            }
        });
    }

    private void secend() {
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclertui);
        LinearLayoutManager manager = new LinearLayoutManager(likelist.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        zyadapter2= new professadapter(R.layout.zhuanyemulu,null);
        zyadapter2.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        recyclerView.setAdapter(zyadapter2);

    }

    private void first() {
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerlikes);
        LinearLayoutManager manager = new LinearLayoutManager(likelist.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        zyadapter= new professadapter(R.layout.zhuanyemulu,null);
        zyadapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        recyclerView.setAdapter(zyadapter);
        User user = BmobUser.getCurrentUser(User.class);
        final String nn= user.getUsername();
        BmobQuery<prefer> preferQuery = new BmobQuery<>();
        preferQuery.addWhereEqualTo("user",nn);
        preferQuery.findObjects(new FindListener<prefer>() {
            @Override
            public void done(final List<prefer> object, BmobException e) {
                if (e == null) {
                    for (prefer prefer : object) {
                        like=prefer.getLikes();
                    }
                    BmobQuery<Profess> pro=new BmobQuery<>();
                    pro.addWhereContainedIn("objectId", like);
                    pro.findObjects(new FindListener<Profess>() {
                        @Override
                        public void done(List<Profess> list, BmobException e) {
                            if(e==null){
                                for(Profess p:list){
                                    p.getBelong();
                                    p.getMoney();
                                    p.getSchool() ;
                                    p.getName();
                                    p.getScore();
                                }
                                zyadapter.addData(list);
                            }
                        }
                    });

                }
                else{
                    Toast.makeText(likelist.this, "未收藏任何", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
