package com.wannabemutants.flistapp.viewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.view.View;

import com.wannabemutants.flistapp.model.Category;
import com.wannabemutants.flistapp.model.Restaurant;

/**
 * Created by cheglader on 5/29/16.
 */
public class CategoryViewModel {
    private Context context;
    private Category category;

    public CategoryViewModel(Context context, Category category) {
        this.context = context;
        this.category = category;
    }

    public String getCategoryName() { return category.name; }

    public View.OnClickListener onClickCategory() {
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
}