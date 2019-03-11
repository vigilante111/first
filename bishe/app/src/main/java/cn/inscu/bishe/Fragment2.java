package cn.inscu.bishe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by apple on 2019/2/19.
 */

public class Fragment2 extends Fragment{
    professadapter zyadapter;
    String name;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2,container,false);
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.recyclerpro);
        LinearLayoutManager manager = new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        zyadapter= new professadapter(R.layout.zhuanyemulu,null);
        zyadapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        recyclerView.setAdapter(zyadapter);
        Bundle bundle1 =this.getArguments();
        if(bundle1!=null){
            name=bundle1.getString("name");
        }
        BmobQuery<Profess> professQuery = new BmobQuery<>();
        professQuery.addWhereEqualTo("name",name);
        professQuery.findObjects(new FindListener<Profess>() {
            @Override
            public void done(List<Profess> object, BmobException e) {
                if (e == null) {
                    for (Profess profess : object) {
                        //获得playerName的信息
                        profess.getName();
                        profess.getScore();
                        profess.getMoney();
                        profess.getSchool();
                        profess.getBelong();
                    }
                    zyadapter.setNewData(object);
                }
                else{

                }
            }
        });
        zyadapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Profess item=(Profess)adapter.getItem(position);
                String name=item.getName();
                String belong=item.getBelong();
                String school=item.getSchool();
                Intent intent1 = new Intent(getActivity(), Mainprofess.class);
                intent1.putExtra("n",name);
                intent1.putExtra("b",belong);
                intent1.putExtra("s",school);
                startActivity(intent1);
            }
        });
        return view;
    }

}
