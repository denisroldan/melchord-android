package com.aiculabs.melchord.ui.song;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aiculabs.melchord.R;
import com.aiculabs.melchord.data.model.Tab;
import com.aiculabs.melchord.util.CustomItemClickListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.TabViewHolder> {

    private List<Tab> mTabs;
    private CustomItemClickListener mListener;

    @Inject
    public SongAdapter(CustomItemClickListener listener) {
        mListener = listener;
        mTabs = new ArrayList<>();
    }

    public void setTabs(List<Tab> tabs) {
        mTabs = tabs;
    }

    @Override
    public TabViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tab, parent, false);
        final TabViewHolder mViewHolder = new TabViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(v, mViewHolder.getAdapterPosition());
            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(TabViewHolder holder, int position) {

        Tab tab = mTabs.get(position);

        holder.tabTypeTextView.setText(tab.getType());
        holder.tabNameTextView.setText(tab.getTitle());

    }

    @Override
    public int getItemCount() {
        return mTabs.size();
    }

    class TabViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tab_type) TextView tabTypeTextView;
        @Bind(R.id.tab_name) TextView tabNameTextView;

        public TabViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
