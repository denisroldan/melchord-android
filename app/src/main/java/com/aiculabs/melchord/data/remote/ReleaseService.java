package com.aiculabs.melchord.data.remote;

import com.aiculabs.melchord.data.model.Artist;
import com.aiculabs.melchord.data.model.ArtistSearch;
import com.aiculabs.melchord.data.model.Release;
import com.aiculabs.melchord.data.model.ReleaseSet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;


public interface ReleaseService {
    String ENDPOINT = APIConfig.BASE_URL;

    @GET("release/{mbid}")
    Observable<Release> getRelease(@Path("mbid") String mbid);

    /******** Helper class that sets up a new services *******/
    class Creator {

        public static ReleaseService newReleaseService() {
            Gson gson = new GsonBuilder()
                    .setDateFormat(APIConfig.DATE_FORMAT)
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ReleaseService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(ReleaseService.class);
        }

    }
}
