package cn.inscu.bishe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.bmob.v3.AsyncCustomEndpoints;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.CloudCodeListener;
import cn.bmob.v3.listener.FindListener;


public class recommend extends AppCompatActivity {
private TextView t1;
private String w;
    List<String> ansitems=new ArrayList<>();
List<String> a=new ArrayList<>();
    int total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);
        t1 = (TextView) findViewById(R.id.test);//test对应你刚刚创建的云函数名称
       getinfor();
    }

    private void getinfor() {
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
                    Toast.makeText(recommend.this, ""+total, Toast.LENGTH_SHORT).show();
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




                    }
                });
            }
        });
    }
}
