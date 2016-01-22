package com.aiculabs.melchord.ui.release;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aiculabs.melchord.R;
import com.aiculabs.melchord.data.model.Release;
import com.aiculabs.melchord.data.model.Ribot;
import com.aiculabs.melchord.data.model.Song;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ReleaseAdapter extends RecyclerView.Adapter<ReleaseAdapter.SongViewHolder> {

    private List<Song> mSongs;

    @Inject
    public ReleaseAdapter() {
        mSongs = new ArrayList<>();
    }

    public void setSongs(List<Song> songs) {
        mSongs = songs;
    }


    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_song, parent, false);
        return new SongViewHolder(itemView);
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

        @Bind(R.id.release_trackNumberTextView)
        TextView trackNumberTextView;
        @Bind(R.id.release_name)
        TextView nameTextView;
        @Bind(R.id.release_duration)
        TextView durationTextView;

        public SongViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
