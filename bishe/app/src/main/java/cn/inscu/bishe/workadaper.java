package cn.inscu.bishe;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by apple on 2019/2/18.
 */

public class workadaper extends BaseQuickAdapter<Profess,BaseViewHolder> {
    public workadaper(int layoutResId, List data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, Profess item) {
        helper.setText(R.id.pname,item.getName());
        helper.setText(R.id.belong,item.getBelong())
                .setText(R.id.money,item.getMoney()+"");
    }
}
