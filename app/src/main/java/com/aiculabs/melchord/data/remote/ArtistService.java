package com.aiculabs.melchord.data.remote;

import com.aiculabs.melchord.data.model.Artist;
import com.aiculabs.melchord.data.model.ArtistSearch;
import com.aiculabs.melchord.data.model.FeaturedArtist;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;


public interface ArtistService {
    String ENDPOINT = APIConfig.BASE_URL;

    @GET("artist/{mbid}")
    Observable<Artist> getArtist(@Path("mbid") String mbid);

    @GET("artist/{term}")
    Observable<List<ArtistSearch>> getArtistSearchResults(@Path("term") String term);


    /******** Helper class that sets up a new services *******/
    class Creator {

        public static ArtistService newArtistsService() {
            Gson gson = new GsonBuilder()
                    .setDateFormat(APIConfig.DATE_FORMAT)
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ArtistService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(ArtistService.class);
        }

    }
}
