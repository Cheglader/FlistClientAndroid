package com.wannabemutants.flistapp.view.fragment;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.wannabemutants.flistapp.FlistAppApplication;
import com.wannabemutants.flistapp.R;
import com.wannabemutants.flistapp.data.DataManager;
import com.wannabemutants.flistapp.model.Category;
import com.wannabemutants.flistapp.util.DialogFactory;
import com.wannabemutants.flistapp.view.adapter.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

public class CategoriesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.swipe_container)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Bind(R.id.recycler_stories)
    RecyclerView mListPosts;

    @Bind(R.id.layout_offline)
    LinearLayout mOfflineContainer;

    @Bind(R.id.progress_indicator)
    ProgressBar mProgressBar;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    public static final String ARG_USER = "ARG_USER";
    public static final int DEFAULT_CATEGORY = 0;

    private DataManager mDataManager;
    private CategoryAdapter mCategoryAdapter;
    private CompositeSubscription mSubscriptions;
    private List<Category> mCategories;

    public static CategoriesFragment newInstance() {
        CategoriesFragment categoriesFragment = new CategoriesFragment();
        Bundle args = new Bundle();
        categoriesFragment.setArguments(args);
        return categoriesFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSubscriptions = new CompositeSubscription();
        mCategories = new ArrayList<>();
        mDataManager = FlistAppApplication.get(getActivity()).getComponent().dataManager();
        Bundle bundle = getArguments();
        mCategoryAdapter = new CategoryAdapter(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_restaurants, container, false);
        ButterKnife.bind(this, fragmentView);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.hn_orange);
        setupToolbar();
        setupRecyclerView();
        loadStoriesIfNetworkConnected();
        return fragmentView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSubscriptions.unsubscribe();
    }

    @Override
    public void onRefresh() {
        mSubscriptions.unsubscribe();
        if (mCategoryAdapter != null) mCategoryAdapter.setItems(new ArrayList<Category>());
            getTestRestaurants();
    }

    @OnClick(R.id.button_try_again)
    public void onTryAgainClick() {
        loadStoriesIfNetworkConnected();
    }

    private void setupToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            /*if (mUser != null) {
                actionBar.setTitle(mUser);
                actionBar.setDisplayHomeAsUpEnabled(true);
            }*/
        }
    }

    private void setupRecyclerView() {
        mListPosts.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListPosts.setHasFixedSize(true);
        mCategoryAdapter.setItems(mCategories);
        mListPosts.setAdapter(mCategoryAdapter);
    }

    private void loadStoriesIfNetworkConnected() {
        /*
        if (DataUtils.isNetworkAvailable(getActivity())) {
            showHideOfflineLayout(false);
            if (mUser != null) {
                //getUserStories();
            } else {
                //getTopStories();
            }
        } else {
            showHideOfflineLayout(true);
        }*/
        showHideOfflineLayout(false);
        getTestRestaurants();
    }

    private void getTestRestaurants() {
        /*
        mRestaurants.add(new Restaurant("Zen"));
        mRestaurants.add(new Restaurant("Chick-Fil-A"));
        mRestaurants.add(new Restaurant("Fat Sal's"));
        mRestaurants.add(new Restaurant("Ramen Tatsuya"));
        mRestaurants.add(new Restaurant("Wendy's"));
        mRestaurants.add(new Restaurant("Taco Cabana"));
        mRestaurants.add(new Restaurant("Franklin's BBQ"));
        mRestaurants.add(new Restaurant("The Salt Lick"));*/
        mSubscriptions.add(mDataManager.getAllCategories()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(mDataManager.getScheduler())
                .subscribe(new Subscriber<List<Category>>() {
                    @Override
                    public void onCompleted() { }

                    @Override
                    public void onError(Throwable e) {
                        hideLoadingViews();
                        Timber.e("There was a problem loading the top stories " + e);
                        e.printStackTrace();
                        DialogFactory.createSimpleOkErrorDialog(
                                getActivity(),
                                getString(R.string.error_stories)
                        ).show();
                    }

                    @Override
                    public void onNext(List<Category> categories) {
                        hideLoadingViews();
                        mCategoryAdapter.addItems(categories);
                    }
                }));
        hideLoadingViews();

    }
    /*
    private void getTopStories() {
        mSubscriptions.add(mDataManager.getTopStories()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(mDataManager.getScheduler())
                .subscribe(new Subscriber<Post>() {
                    @Override
                    public void onCompleted() { }

                    @Override
                    public void onError(Throwable e) {
                        hideLoadingViews();
                        Timber.e("There was a problem loading the top stories " + e);
                        e.printStackTrace();
                        DialogFactory.createSimpleOkErrorDialog(
                                getActivity(),
                                getString(R.string.error_stories)
                        ).show();
                    }

                    @Override
                    public void onNext(Post post) {
                        hideLoadingViews();
                        mPostAdapter.addItem(post);
                    }
                }));
    }

    private void getUserStories() {
        mSubscriptions.add(mDataManager.getUserPosts(mUser)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(mDataManager.getScheduler())
                .subscribe(new Subscriber<Post>() {
                    @Override
                    public void onCompleted() { }

                    @Override
                    public void onError(Throwable e) {
                        hideLoadingViews();
                        Timber.e("There was a problem loading the user stories " + e);
                        DialogFactory.createSimpleOkErrorDialog(
                                getActivity(),
                                getString(R.string.error_stories)
                        ).show();
                    }

                    @Override
                    public void onNext(Post story) {
                        hideLoadingViews();
                        mPostAdapter.addItem(story);
                    }
                }));
    }*/

    private void hideLoadingViews() {
        mProgressBar.setVisibility(View.GONE);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void showHideOfflineLayout(boolean isOffline) {
        mOfflineContainer.setVisibility(isOffline ? View.VISIBLE : View.GONE);
        mListPosts.setVisibility(isOffline ? View.GONE : View.VISIBLE);
        mProgressBar.setVisibility(isOffline ? View.GONE : View.VISIBLE);
    }

}
