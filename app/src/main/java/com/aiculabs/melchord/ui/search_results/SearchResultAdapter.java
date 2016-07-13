package com.aiculabs.melchord.ui.search_results;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aiculabs.melchord.R;
import com.aiculabs.melchord.util.CustomItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ResultViewHolder> {

    private List<HashMap<String, String>> mResults;
    private CustomItemClickListener mListener;

    @Inject SearchResultAdapter(CustomItemClickListener listener) {
        mListener = listener;
        mResults = new ArrayList<>();
    }


    public void setResults(List<HashMap<String, String>> results) {
        mResults = results;
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search_result, parent, false);
        final ResultViewHolder mViewHolder = new ResultViewHolder(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(v, mViewHolder.getAdapterPosition());
            }
        });

        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(final ResultViewHolder holder, int position) {
        HashMap<String, String> result = mResults.get(position);
        if (result.get("name") != null) {
            holder.titleTextView.setText(result.get("name"));
        }
        if (result.get("comment") != null && !result.get("comment").equals("")) {
            holder.commentTextView.setText(result.get("comment"));
        }

        holder.id = result.get("id");
    }

    @Override
    public int getItemCount() {
        return mResults.size();
    }

    class ResultViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.row_search_result_title) TextView titleTextView;
        @BindView(R.id.row_search_result_comment) TextView commentTextView;
        public String id;

        public ResultViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
