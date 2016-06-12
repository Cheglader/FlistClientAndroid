package com.wannabemutants.flistapp.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.wannabemutants.flistapp.model.Restaurant;
import com.wannabemutants.flistapp.util.DataUtils;
import com.wannabemutants.flistapp.view.adapter.RestaurantAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by cheglader on 6/10/16.
 */
public class SearchResultsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.swipe_container)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Bind(R.id.recycler_stories)
    RecyclerView mListResults;

    @Bind(R.id.layout_offline)
    LinearLayout mOfflineContainer;

    @Bind(R.id.progress_indicator)
    ProgressBar mProgressBar;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    public static final String ARG_QUERY = "ARG_QUERY";
    public static final int DEFAULT_CATEGORY = 0;

    private DataManager mDataManager;
    private RestaurantAdapter mRestaurantAdapter;
    private CompositeSubscription mSubscriptions;
    private List<Restaurant> mRestaurants;
    private String mQuery;

    public static SearchResultsFragment newInstance(String query) {
        SearchResultsFragment searchResultsFragment = new SearchResultsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_QUERY, query);
        searchResultsFragment.setArguments(args);
        return searchResultsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSubscriptions = new CompositeSubscription();
        mRestaurants = new ArrayList<>();
        mDataManager = FlistAppApplication.get(getActivity()).getComponent().dataManager();
        Bundle bundle = getArguments();
        if (bundle != null) {
            mQuery = bundle.getString(ARG_QUERY, null);
        }
        mRestaurantAdapter = new RestaurantAdapter(getActivity());
        // TODO design change stuff
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_restaurants, container, false);
        ButterKnife.bind(this, fragmentView);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.hn_orange);
        setupToolbar();
        setupRecyclerView();
        loadResultsIfNetworkConnected();
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
        if (mRestaurantAdapter != null) mRestaurantAdapter.setItems(new ArrayList<Restaurant>());
        getSearchQueryResults();
    }

    @OnClick(R.id.button_try_again)
    public void onTryAgainClick() {
        loadResultsIfNetworkConnected();
    }

    private void setupToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            if (mQuery != null) {
                actionBar.setTitle(mQuery);
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    private void setupRecyclerView() {
        mListResults.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListResults.setHasFixedSize(true);
        mRestaurantAdapter.setItems(mRestaurants);
        mListResults.setAdapter(mRestaurantAdapter);
    }

    private void loadResultsIfNetworkConnected() {
        if (DataUtils.isNetworkAvailable(getActivity())) {
            showHideOfflineLayout(false);
            getSearchQueryResults();
        } else {
            showHideOfflineLayout(true);
        }
    }

    private void getSearchQueryResults() {

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
        mSubscriptions.add(mDataManager.getUserPosts(mQuery)
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
        mListResults.setVisibility(isOffline ? View.GONE : View.VISIBLE);
        mProgressBar.setVisibility(isOffline ? View.GONE : View.VISIBLE);
    }

}
