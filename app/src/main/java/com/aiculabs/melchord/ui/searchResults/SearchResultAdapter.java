package com.aiculabs.melchord.ui.searchResults;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aiculabs.melchord.R;
import com.aiculabs.melchord.data.model.ArtistSearch;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ArtistSearchViewHolder> {

    private List<ArtistSearch> mArtistSearches;

    @Inject
    public SearchResultAdapter() {
        mArtistSearches = new ArrayList<>();
    }

    public void setRibots(List<ArtistSearch> artistSearches) {
        mArtistSearches = artistSearches;
    }

    @Override
    public ArtistSearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ribot, parent, false);
        return new ArtistSearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ArtistSearchViewHolder holder, int position) {
        ArtistSearch artistSearch = mArtistSearches.get(position);
        //holder.hexColorView.setBackgroundColor(Color.parseColor(artistSearch.profile.hexColor));
        holder.nameTextView.setText(String.format("%s %s",
                artistSearch.getName(), artistSearch.getArea()));
        //holder.emailTextView.setText(ribot.profile.email);
    }

    @Override
    public int getItemCount() {
        return mArtistSearches.size();
    }

    class ArtistSearchViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.view_hex_color) View hexColorView;
        @Bind(R.id.text_name) TextView nameTextView;
        @Bind(R.id.text_email) TextView emailTextView;

        public ArtistSearchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
