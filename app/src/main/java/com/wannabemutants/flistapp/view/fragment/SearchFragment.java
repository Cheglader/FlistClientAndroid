package com.wannabemutants.flistapp.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wannabemutants.flistapp.FlistAppApplication;
import com.wannabemutants.flistapp.R;
import com.wannabemutants.flistapp.data.DataManager;
import com.wannabemutants.flistapp.model.Restaurant;
import com.wannabemutants.flistapp.view.activity.MainActivity;
import com.wannabemutants.flistapp.view.adapter.RestaurantAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by cheglader on 6/5/16.
 */
public class SearchFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.recycler_stories)
    RecyclerView mListPosts;

    @Bind(R.id.layout_offline)
    LinearLayout mOfflineContainer;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.text_welcome)
    TextView mTextWelcome;

    @Bind(R.id.restaurant_search_input)
    EditText mSearchInput;

    private DataManager mDataManager;
    private RestaurantAdapter mCategoryAdapter;
    private CompositeSubscription mSubscriptions;
    private List<Restaurant> mRestaurants;
    private String mUser;

    public static RestaurantsFragment newInstance(String user) {
        RestaurantsFragment storiesFragment = new RestaurantsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USER, user);
        storiesFragment.setArguments(args);
        return storiesFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO
        mTextWelcome.setText("Welcome ");
        mCategoryAdapter = new RestaurantAdapter(getActivity(), mUser != null);
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
        if (mCategoryAdapter != null) mCategoryAdapter.setItems(new ArrayList<Restaurant>());
        if (mUser != null) {
            //getUserStories();
        } else {
            getTestRestaurants();
        }
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
            if (mUser != null) {
                actionBar.setTitle(mUser);
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    private void setupRecyclerView() {
        mListPosts.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListPosts.setHasFixedSize(true);
        mCategoryAdapter.setItems(mRestaurants);
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
        mRestaurants.add(new Restaurant("Zen"));
        mRestaurants.add(new Restaurant("Chick-Fil-A"));
        mRestaurants.add(new Restaurant("Fat Sal's"));
        mRestaurants.add(new Restaurant("Ramen Tatsuya"));
        mRestaurants.add(new Restaurant("Wendy's"));
        mRestaurants.add(new Restaurant("Taco Cabana"));
        mRestaurants.add(new Restaurant("Franklin's BBQ"));
        mRestaurants.add(new Restaurant("The Salt Lick"));

    }

    private void hideLoadingViews() {
        mProgressBar.setVisibility(View.GONE);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void showHideOfflineLayout(boolean isOffline) {
        mOfflineContainer.setVisibility(isOffline ? View.VISIBLE : View.GONE);
        mListPosts.setVisibility(isOffline ? View.GONE : View.VISIBLE);
        mProgressBar.setVisibility(isOffline ? View.GONE : View.VISIBLE);
    }

    public View.OnClickListener onClickSearch() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search_query = mSearchInput.getText().toString();
                launchSearchActivity(search_query);
            }
        };
    }

    private void launchStoryActivity(String search_query) {
        ((MainActivity)getActivity()).addSearchResultsFragment(search_query);
    }


}
