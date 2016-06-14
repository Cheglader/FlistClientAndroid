package com.wannabemutants.flistapp.data.remote;

import com.wannabemutants.flistapp.model.Category;
import com.wannabemutants.flistapp.model.Restaurant;
import com.wannabemutants.flistapp.model.TextEntry;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by cheglader onetr 5/15/16.
 */
public interface FlistService {
    String ENDPOINT = "https://api.flistapp.com";

    /**
     * Return a list of the latest post IDs.
     */
    //@GET("/topstories.json")
    //Observable<List<Long>> getTopStories();
    @GET("/restaurants/?format=json")
    Observable<List<Restaurant>> getRestaurantsSearch(
            @Query("query") String query,
            @Query("lat") double lat,
            @Query("lng") double lng);

    @GET("/categories/?format=json")
    Observable<List<Category>> getAllCategories();

    @POST("/text_entries/")
    Observable<TextEntry> createTextEntry(@Body TextEntry textEntry);

    /**
     * Return a list of a users post IDs.
     */
    //@GET("/user/{user}.json")
    //Observable<User> getUser(@Path("user") String user);

    /**
     * Return story item.
     */
    //@GET("/item/{itemId}.json")
    //Observable<Post> getStoryItem(@Path("itemId") String itemId);

    /**
     * Returns a comment item.
     */
    //@GET("/item/{itemId}.json")
    //Observable<Comment> getCommentItem(@Path("itemId") String itemId);

}
