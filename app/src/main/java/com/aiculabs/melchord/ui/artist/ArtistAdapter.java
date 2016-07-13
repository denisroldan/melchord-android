package com.aiculabs.melchord.ui.artist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aiculabs.melchord.R;
import com.aiculabs.melchord.data.model.Link;
import com.aiculabs.melchord.data.model.Release;
import com.aiculabs.melchord.util.CustomItemClickListener;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ReleaseViewHolder> {
    private List<Release> mReleases;
    private List<Link> mLinks;
    private CustomItemClickListener mListener;

    @Inject
    public ArtistAdapter(CustomItemClickListener listener) {
        mListener = listener;
        mReleases = new ArrayList<>();
        mLinks = new ArrayList<>();
    }

    public void setReleases(List<Release> releases) {
        mReleases = releases;
    }

    public void setLinks(List<Link> links) {
        mLinks = links;
    }

    @Override
    public ReleaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_release, parent, false);
        final ReleaseViewHolder mViewHolder = new ReleaseViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(v, mViewHolder.getAdapterPosition());
            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(ReleaseViewHolder holder, int position) {
        Release release = mReleases.get(position);
        holder.releaseTitle.setText(release.getTitle());
        holder.releaseImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
        String launched = "";
        if (release.getDate() != null) {
            launched = release.getDate();
        }
        if (release.getThumbnail() != null) {
            Glide.with(holder.releaseImage.getContext())
                    .load(release.getThumbnail())
                    .placeholder(R.drawable.default_release)
                    .fitCenter()
                    .error(R.drawable.bg)
                    .into(holder.releaseImage);
        } else {
            Glide.with(holder.releaseImage.getContext())
                    .load(R.drawable.default_release)
                    .fitCenter()
                    .into(holder.releaseImage);
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

        @BindView(R.id.release_name)
        TextView releaseTitle;
        @BindView(R.id.release_date)
        TextView releaseDate;
        @BindView(R.id.releaseImageView)
        ImageView releaseImage;

        public ReleaseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    class LinkViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.link_img)
        ImageView linkImage;
        @BindView(R.id.link_name)
        TextView linkName;


        public LinkViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
