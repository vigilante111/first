package cn.inscu.bishe;

import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by apple on 2019/2/15.
 */

public class School {
    private String schoolname;
    private String area;
    private BmobFile schoolimage;
    private BmobFile image1;
    private BmobFile image2;
    private BmobFile image3;
    private int lowestpoint;
    private String level;
    private String introduction;

    public void setImage1(BmobFile image1){this.image1=image1;}
    public void setImage2(BmobFile image2){this.image2=image2;}
    public void setImage3(BmobFile image3){this.image3=image3;}
    public void setIntroduction(String introduction){this.introduction=introduction;}
    public void setSchoolname(String schoolname ){this.schoolname=schoolname;}
    public void setLevel(String level){this.level=level;}
    public void setarea(String area){this.area=area;}
    public void setSchoolimage(BmobFile schoolimage){this.schoolimage=schoolimage;}
    public void setLowestpoint(int lowestpoint){this.lowestpoint=lowestpoint;}
    public String getImage1(){return image1.getFileUrl();}
    public String getImage2(){return image2.getFileUrl();}
    public String getImage3(){return image3.getFileUrl();}
    public String getSchoolname(){
        return schoolname;
    }
    public String getSchoolimage(){return  schoolimage.getFileUrl();}
    public String getLevel(){return level;}
    public String getArea(){
        return  area;
    }
    public int getLowestpoint(){
        return  lowestpoint;
    }
    public String getIntroduction(){return introduction;}
}
