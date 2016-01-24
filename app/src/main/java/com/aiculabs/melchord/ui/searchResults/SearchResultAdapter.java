package com.aiculabs.melchord.ui.searchResults;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aiculabs.melchord.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ResultViewHolder> {

    private List<HashMap<String, String>> mResults;

    @Inject
    public SearchResultAdapter() {
        mResults = new ArrayList<>();
    }

    public void setResults(List<HashMap<String, String>> results) {
        mResults = results;
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search_result, parent, false);
        return new ResultViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int position) {
        HashMap<String, String> result = mResults.get(position);
        if (result.get("name") != null) {
            holder.titleTextView.setText(result.get("name").toString());
        }
        if (result.get("comment") != null && !result.get("comment").equals("")) {
            holder.commentTextView.setText(result.get("comment").toString());
        }

        holder.mbid = result.get("mbid").toString();
    }

    @Override
    public int getItemCount() {
        return mResults.size();
    }

    class ResultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.row_search_result_title) TextView titleTextView;
        @Bind(R.id.row_search_result_comment) TextView commentTextView;
        public String mbid;

        public ResultViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), mbid, 3);
        }
    }
}
