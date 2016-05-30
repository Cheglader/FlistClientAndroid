package com.wannabemutants.flistapp.data.remote;

import com.wannabemutants.flistapp.model.Category;
import com.wannabemutants.flistapp.model.Restaurant;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
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
    @GET("/restaurants?query={search_query}&format=json")
    Observable<List<Restaurant>> getRestaurantsSearch(@Path("search_query") String search_query);

    @GET("/categories/?format=json")
    Observable<List<Category>> getAllCategories();
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
