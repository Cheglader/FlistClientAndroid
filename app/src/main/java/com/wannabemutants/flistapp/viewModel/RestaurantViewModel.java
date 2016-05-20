package com.wannabemutants.flistapp.viewModel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.view.View;

import com.wannabemutants.flistapp.model.Restaurant;

/**
 * Created by cheglader on 5/17/16.
 */
public class RestaurantViewModel extends BaseObservable {
    private Context context;
    private Restaurant restaurant;

    public RestaurantViewModel(Context context, Restaurant restaurant) {
        this.context = context;
        this.restaurant = restaurant;
    }

    public String getRestaurantName() { return restaurant.name; }

    public View.OnClickListener onClickRestaurant() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Post.PostType postType = post.postType;
                if (postType == Post.PostType.JOB || postType == Post.PostType.STORY) {
                    launchStoryActivity();
                } else if (postType == Post.PostType.ASK) {
                    launchCommentsActivity();
                }*/
            }
        };
    }

    public View.OnClickListener onClickUpvote() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Post.PostType postType = post.postType;
                if (postType == Post.PostType.JOB || postType == Post.PostType.STORY) {
                    launchStoryActivity();
                } else if (postType == Post.PostType.ASK) {
                    launchCommentsActivity();
                }*/
            }
        };
    }

    public View.OnClickListener onClickDownvote() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Post.PostType postType = post.postType;
                if (postType == Post.PostType.JOB || postType == Post.PostType.STORY) {
                    launchStoryActivity();
                } else if (postType == Post.PostType.ASK) {
                    launchCommentsActivity();
                }*/
            }
        };
    }

    /*
    private void launchStoryActivity() {
        String packageName = CustomTabsHelper.getPackageNameToUse(context);

        if (packageName == null) {
            context.startActivity(ViewStoryActivity.getStartIntent(context, post));
        } else {
            CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();
            Intent intent = intentBuilder.build().intent;
            intent.setData(Uri.parse(post.url));
            context.startActivity(intent);
        }
    }

    private void launchCommentsActivity() {
        context.startActivity(CommentsActivity.getStartIntent(context, post));
    }*/
}
