package com.aiculabs.melchord.ui.release;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aiculabs.melchord.R;
import com.aiculabs.melchord.data.model.Song;
import com.aiculabs.melchord.util.CustomItemClickListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ReleaseAdapter extends RecyclerView.Adapter<ReleaseAdapter.SongViewHolder> {

    private List<Song> mSongs;
    private CustomItemClickListener mListener;

    @Inject
    public ReleaseAdapter(CustomItemClickListener listener) {
        mListener = listener;
        mSongs = new ArrayList<>();
    }

    public void setSongs(List<Song> songs) {
        mSongs = songs;
    }


    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_song, parent, false);
        final SongViewHolder mViewHolder = new SongViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(v, mViewHolder.getAdapterPosition());
            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {

        Song song = mSongs.get(position);

        holder.durationTextView.setText(song.getLength());
        holder.nameTextView.setText(song.getTitle());
        String number_string = " ";
        if (song.getNumber() != null) number_string = song.getNumber().toString();
        holder.trackNumberTextView.setText(number_string);

    }

    @Override
    public int getItemCount() {
        return mSongs.size();
    }

    class SongViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.song_trackNumberTextView)
        TextView trackNumberTextView;
        @Bind(R.id.song_name)
        TextView nameTextView;
        @Bind(R.id.song_duration)
        TextView durationTextView;

        public SongViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
