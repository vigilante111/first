package cn.inscu.bishe;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by apple on 2019/3/1.
 */

public class cmtadapter extends BaseQuickAdapter<Comment,BaseViewHolder> {
    public cmtadapter(int layoutResId, @Nullable List<Comment> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Comment item) {
        helper.setText(R.id.ccc,item.getAcomment());
        helper.setText(R.id.ccn,item.getUsername())
                .setText(R.id.cct,item.getUpdatedAt());

    }
}
