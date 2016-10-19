package cn.ucai.fulicenter.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ucai.fulicenter.I;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.bean.BoutiqueBean;
import cn.ucai.fulicenter.utils.ImageLoader;
import cn.ucai.fulicenter.view.FooterViewHolder;

public class BoutiqueAdapter extends Adapter {
    Context mContext;
    ArrayList<BoutiqueBean> mList;
    boolean isMore;

    public BoutiqueAdapter(Context context, ArrayList<BoutiqueBean> List) {
        mContext = context;
        mList = new ArrayList<>();
        mList.addAll(List);
    }

    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean more) {
        isMore = more;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = null;
        if (viewType == I.TYPE_FOOTER) {
            holder = new FooterViewHolder
                    (LayoutInflater.from(mContext)
                            .inflate(R.layout.item_footer, parent, false));
        } else {
            holder = new BoutiqueViewHolder(LayoutInflater.from(mContext)
                    .inflate(R.layout.item_boutique, parent, false));
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(holder instanceof FooterViewHolder){
            ((FooterViewHolder) holder).mTvFooter.setText(getFooterString());
        }
        if(holder instanceof  BoutiqueViewHolder){
            BoutiqueBean boutiqueBean = mList.get(position);
            ImageLoader.downloadImg(mContext,((BoutiqueViewHolder) holder).ivBoutiqueImg,boutiqueBean.getImageurl());
            ((BoutiqueViewHolder) holder).tvBoutiqueTitle.setText(boutiqueBean.getTitle());
            ((BoutiqueViewHolder) holder).tvBoutiqueName.setText(boutiqueBean.getName());
            ((BoutiqueViewHolder) holder).tvBoutiqueDescription.setText(boutiqueBean.getDescription());
        }
    }

    private int getFooterString() {
        return  isMore?R.string.load_more:R.string.no_more;
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() + 1 : 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return I.TYPE_FOOTER;
        }
        return I.TYPE_ITEM;
    }



    class BoutiqueViewHolder extends ViewHolder{
        @BindView(R.id.ivBoutiqueImg)
        ImageView ivBoutiqueImg;
        @BindView(R.id.tvBoutiqueTitle)
        TextView tvBoutiqueTitle;
        @BindView(R.id.tvBoutiqueName)
        TextView tvBoutiqueName;
        @BindView(R.id.tvBoutiqueDescription)
        TextView tvBoutiqueDescription;
        @BindView(R.id.layout_boutique_item)
        RelativeLayout layoutBoutiqueItem;

        BoutiqueViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
