package cn.inscu.bishe;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by apple on 2019/2/27.
 */

public class professadapter extends BaseQuickAdapter<Profess,BaseViewHolder> {

    public professadapter(int layoutResId, @Nullable List<Profess> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Profess item) {
        helper.setText(R.id.zyschool,item.getSchool())
                .setText(R.id.zybelong,item.getBelong())
                .setText(R.id.zyname,item.getName())
                .setText(R.id.zymoney,item.getMoney()+"")
                .setText(R.id.zyscore,item.getScore()+"");
        helper.addOnClickListener(R.id.zybutton);
    }
}
