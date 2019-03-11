package cn.inscu.bishe;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by apple on 2019/2/19.
 */

public class Fragment1 extends Fragment {
    String introduce;
    String i1;
    String i2;
    String i3;
    TextView t1;
    ImageView image1;
    ImageView image4;
    ImageView image3;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1,container,false);
        t1=(TextView)view.findViewById(R.id.j1);
        image1=(ImageView)view.findViewById(R.id.fg1);
        image4=(ImageView)view.findViewById(R.id.fg2);
        image3=(ImageView)view.findViewById(R.id.fg3);
        Bundle bundle =this.getArguments();
        if(bundle!=null){
            i1=bundle.getString("i1");
            i2=bundle.getString("i2");
            i3=bundle.getString("i3");
            introduce = bundle.getString("iiii");

        }
        Glide.with(this).load(i1).into(image1);
        Glide.with(this).load(i2).into(image4);
        Glide.with(this).load(i3).into(image3);
        t1.setText(introduce);
        return view;

    }


}
