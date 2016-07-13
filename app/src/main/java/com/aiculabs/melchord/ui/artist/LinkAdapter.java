package com.aiculabs.melchord.ui.artist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aiculabs.melchord.R;
import com.aiculabs.melchord.data.model.Link;
import com.aiculabs.melchord.util.CustomItemClickListener;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LinkAdapter extends RecyclerView.Adapter<LinkAdapter.LinkViewHolder> {
    private List<Link> mLinks;
    private CustomItemClickListener mListener;

    @Inject
    public LinkAdapter(CustomItemClickListener listener) {
        mListener = listener;
        mLinks = new ArrayList<>();
    }

    public void setLinks(List<Link> releases) {
        mLinks = releases;
    }


    @Override
    public LinkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_link, parent, false);
        final LinkViewHolder mViewHolder = new LinkViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(v, mViewHolder.getAdapterPosition());
            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(LinkViewHolder holder, int position) {
        Link link = mLinks.get(position);

        Glide.with(holder.linkImage.getContext())
                .load(link.getDrawableId())
                .placeholder(R.drawable.ic_link_white_48dp)
                .fitCenter()
                .error(R.drawable.ic_link_white_48dp)
                .into(holder.linkImage);

        holder.linkTitle.setText(link.getTitle());


    }

    @Override
    public int getItemCount() {
        return mLinks.size();
    }

    class LinkViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.link_name)
        TextView linkTitle;
        @BindView(R.id.link_img)
        ImageView linkImage;

        public LinkViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
