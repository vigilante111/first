package cn.inscu.bishe;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by apple on 2019/3/8.
 */

public class prefer extends BmobObject{
    private String user;
    private List<String> likes;
    public void setUser(String user){
        this.user=user;
    }
    public void setLikes(List<String> likes){
        this.likes=likes;
    }
    public String getUser(){
        return user;
    }
    public List<String> getLikes(){
        return likes;
    }
}
