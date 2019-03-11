package cn.inscu.bishe;

import cn.bmob.v3.BmobUser;

/**
 * Created by apple on 2019/2/12.
 */

public class User extends BmobUser {
  private String area;
  private String profess;
  private int score;
  private String level;
    public String getArea() {
        return area;
    }
    public String getProfess() {
        return profess;
    }
    public int getScore() {
        return score;
    }
    public String getlevel() {
        return level;
    }
    public User setScore(int score) {
        this.score= score;
        return this;
    }
    public User setProfess(String profess) {
        this.profess=profess;
        return this;
    }
    public User setArea(String area) {
        this.area = area;
        return this;
    }
    public User setLevel(String level) {
        this.level = level;
        return this;
    }
}
