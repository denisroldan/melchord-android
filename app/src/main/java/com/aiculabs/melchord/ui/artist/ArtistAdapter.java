package com.aiculabs.melchord.ui.artist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aiculabs.melchord.R;
import com.aiculabs.melchord.data.model.Release;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Also on 22/1/16.
 */
public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ReleaseViewHolder> {
    private List<Release> mReleases;

        @Inject
        public ArtistAdapter() {
            mReleases = new ArrayList<>();
        }

        public void setReleases(List<Release> releases) {
            mReleases = new ArrayList<>();
            for (Release release: releases) {
                if (release.getType().equals("Album")) {
                    mReleases.add(release);
                }
            }
        }

        @Override
        public ReleaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_release, parent, false);
            return new ReleaseViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ReleaseViewHolder holder, int position) {
            Release release = mReleases.get(position);
            holder.releaseTitle.setText(release.getTitle());
            String launched = "";
            if (release.getLaunched() != null) {
                launched = release.getLaunched();
            }
            if (release.getThumbnail() != null) {
                // TODO: Poner un fondaco en error
                Picasso.with(holder.releaseImage.getContext()).load(release.getThumbnail()).error(R.drawable.bg).into(holder.releaseImage);
            } else {
                holder.releaseImage.setImageResource(0);
            }
            if (launched.length() > 4) {
                launched = launched.substring(0, 4);
            }
            holder.releaseDate.setText(launched);

        }

        @Override
        public int getItemCount() {
            return mReleases.size();
        }

        class ReleaseViewHolder extends RecyclerView.ViewHolder {

            @Bind(R.id.row_release_title) TextView releaseTitle;
            @Bind(R.id.row_release_date) TextView releaseDate;
            @Bind(R.id.row_release_image) ImageView releaseImage;

            public ReleaseViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
}

}
