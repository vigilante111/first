package cn.inscu.bishe;

/**
 * Created by apple on 2019/2/18.
 */

public class Profess {
    private String name;
    private String school;
    private String belong;
    private int money;
    private int score;
    public void setName(String name){
        this.name=name;
    }
    public void setBelong(String belong){
        this.belong=belong;
    }
    public void setSchool(String school){
        this.school=school;
    }
    public void setMoney(int money){
        this.money=money;
    }
    public void setScore(int score){
        this.score=score;
    }
    public String getName(){
        return name;
    }
    public String getSchool(){
        return school;
    }
    public String getBelong(){
        return belong;
    }
    public int getMoney(){
        return money;
    }
    public int getScore(){
        return score;
    }
}
