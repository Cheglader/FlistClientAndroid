package com.wannabemutants.flistapp.data;

import android.content.Context;
import android.provider.ContactsContract;

import com.wannabemutants.flistapp.FlistAppApplication;
import com.wannabemutants.flistapp.data.remote.FlistService;
import com.wannabemutants.flistapp.injection.component.DaggerDataManagerComponent;
import com.wannabemutants.flistapp.injection.module.DataManagerModule;
import com.wannabemutants.flistapp.model.Category;
import com.wannabemutants.flistapp.model.Restaurant;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;

/**
 * Created by cheglader on 5/14/16.
 */
public class DataManager {
    @Inject
    protected FlistService mFlistService;
    @Inject
    protected Scheduler mSubscribeScheduler;

    public DataManager(Context context) {
        injectDependencies(context);
    }

    /* This constructor is provided so we can set up a DataManager with mocks from unit test.
     * At the moment this is not possible to do with Dagger because the Gradle APT plugin doesn't
     * work for the unit test variant, plus Dagger 2 doesn't provide a nice way of overriding
     * modules */
    public DataManager(FlistService watchTowerService,
                       Scheduler subscribeScheduler) {
        mFlistService = watchTowerService;
        mSubscribeScheduler = subscribeScheduler;
    }

    protected void injectDependencies(Context context) {
        DaggerDataManagerComponent.builder()
                .applicationComponent(FlistAppApplication.get(context).getComponent())
                .dataManagerModule(new DataManagerModule())
                .build()
                .inject(this);
    }

    public Scheduler getScheduler() {
        return mSubscribeScheduler;
    }

    public Observable<List<Category>> getAllCategories() {
        return mFlistService.getAllCategories();
    }

    public Observable<List<Restaurant>> getSearchQuery(String query, double lat, double lng) {
        return mFlistService.getRestaurantsSearch(query, lat, lng);
    }

    /*
    public Observable<Post> getTopStories() {
        return mHackerNewsService.getTopStories()
                .concatMap(new Func1<List<Long>, Observable<? extends Post>>() {
                    @Override
                    public Observable<? extends Post> call(List<Long> longs) {
                        return getPostsFromIds(longs);
                    }
                });
    }
    */

    /*
    public Observable<Post> getUserPosts(String user) {
        return mHackerNewsService.getUser(user)
                .concatMap(new Func1<User, Observable<? extends Post>>() {
                    @Override
                    public Observable<? extends Post> call(User user) {
                        return getPostsFromIds(user.submitted);
                    }
                });
    }
    */

    /*
    public Observable<Post> getPostsFromIds(List<Long> storyIds) {
        return Observable.from(storyIds)
                .concatMap(new Func1<Long, Observable<Post>>() {
                    @Override
                    public Observable<Post> call(Long aLong) {
                        return mHackerNewsService.getStoryItem(String.valueOf(aLong));
                    }
                }).flatMap(new Func1<Post, Observable<Post>>() {
                    @Override
                    public Observable<Post> call(Post post) {
                        return post.title != null ? Observable.just(post) : Observable.<Post>empty();
                    }
                });
    }
    */

    /*
    public Observable<Comment> getPostComments(final List<Long> commentIds, final int depth) {
        return Observable.from(commentIds)
                .concatMap(new Func1<Long, Observable<Comment>>() {
                    @Override
                    public Observable<Comment> call(Long aLong) {
                        return mHackerNewsService.getCommentItem(String.valueOf(aLong));
                    }
                }).concatMap(new Func1<Comment, Observable<Comment>>() {
                    @Override
                    public Observable<Comment> call(Comment comment) {
                        comment.depth = depth;
                        if (comment.kids == null || comment.kids.isEmpty()) {
                            return Observable.just(comment);
                        } else {
                            return Observable.just(comment)
                                    .mergeWith(getPostComments(comment.kids, depth + 1));
                        }
                    }
                }).filter(new Func1<Comment, Boolean>() {
                    @Override
                    public Boolean call(Comment comment) {
                        return (comment.by != null && !comment.by.trim().isEmpty()
                                && comment.text != null && !comment.text.trim().isEmpty());
                    }
                });
    }
    */
}
