package com.aiculabs.melchord.data.remote;

import com.aiculabs.melchord.data.model.Artist;
import com.aiculabs.melchord.data.model.FeaturedArtist;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.GET;
import rx.Observable;


public interface FeaturedArtistService {
    String ENDPOINT = APIConfig.BASE_URL;

    @GET("artists/featured")
    Observable<List<FeaturedArtist>> getFeaturedArtists();

    /******** Helper class that sets up a new services *******/
    class Creator {

        public static FeaturedArtistService newFeaturedArtistsService() {
            Gson gson = new GsonBuilder()
                    .setDateFormat(APIConfig.DATE_FORMAT)
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(FeaturedArtistService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(FeaturedArtistService.class);
        }
        
    }
}
