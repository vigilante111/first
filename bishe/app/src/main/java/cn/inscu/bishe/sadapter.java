package cn.inscu.bishe;

import android.widget.ImageView;

import java.util.List;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by apple on 2019/2/17.
 */

public class sadapter extends BaseQuickAdapter<School,BaseViewHolder> {
    public sadapter(int layoutResId, List data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, School item) {

        helper.setText(R.id.school_name, item.getSchoolname());

        Glide.with(mContext) // 在MainActivity中调用Glide的API
                .load(item.getSchoolimage()) // 加载网络中的静态图片
                .placeholder(R.mipmap.ic_launcher) // 在图片没有加载出来或加载失败时显示ic_launcher图片
                .into((ImageView) helper.getView(R.id.school_image));// 将图片加载到一个ImageView对象中


       //helper.addOnClickListener(R.id.school_image);

    }
}
