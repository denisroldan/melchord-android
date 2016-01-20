package com.aiculabs.melchord.data.remote;

import com.aiculabs.melchord.data.model.Release;
import com.aiculabs.melchord.data.model.Song;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;


public interface SongService {
    String ENDPOINT = APIConfig.BASE_URL;

    @GET("song/{mbid}")
    Observable<List<Song>> getSongs(@Path("mbid") String mbid);

    /******** Helper class that sets up a new services *******/
    class Creator {

        public static SongService newSongService() {
            Gson gson = new GsonBuilder()
                    .setDateFormat(APIConfig.DATE_FORMAT)
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(SongService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(SongService.class);
        }

    }
}
