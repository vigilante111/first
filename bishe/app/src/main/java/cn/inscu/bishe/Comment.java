package cn.inscu.bishe;

import cn.bmob.v3.BmobObject;

/**
 * Created by apple on 2019/3/1.
 */

public class Comment extends BmobObject{
    private String username;
    private String acomment;
    private String aprofess;
    public void setAprofess(String aprofess){
        this.aprofess=aprofess;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public void setAcomment(String acomment){
        this.acomment=acomment;
    }
    public String getUsername(){
        return username;
    }
    public String getAcomment(){
        return acomment;
    }
    public String getAprofess(){
        return aprofess;
    }
}
