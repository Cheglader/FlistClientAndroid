package com.wannabemutants.flistapp.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.wannabemutants.flistapp.R;
import com.wannabemutants.flistapp.databinding.ItemRestaurantBinding;
import com.wannabemutants.flistapp.model.Restaurant;
import com.wannabemutants.flistapp.viewModel.RestaurantViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cheglader on 5/15/16.
 */
public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.BindingHolder> {
    private List<Restaurant> mRestaurants;
    private Context mContext;

    public RestaurantAdapter(Context context) {
        mContext = context;
        mRestaurants = new ArrayList<>();
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemRestaurantBinding postBinding = DataBindingUtil.inflate(
        LayoutInflater.from(parent.getContext()),
        R.layout.item_restaurant,
        parent,
        false);
        return new BindingHolder(postBinding);
    }

@Override
public void onBindViewHolder(BindingHolder holder, int position) {
        ItemRestaurantBinding postBinding = holder.binding;
        postBinding.setViewModel(new RestaurantViewModel(mContext, mRestaurants.get(position)));
        }

@Override
public int getItemCount() {
        return mRestaurants.size();
        }

public void setItems(List<Restaurant> restaurants) {
        mRestaurants = restaurants;
        notifyDataSetChanged();
        }

public void addItem(Restaurant restaurant) {
        if (!mRestaurants.contains(restaurant)) {
        mRestaurants.add(restaurant);
        notifyItemInserted(mRestaurants.size() - 1);
        } else {
        mRestaurants.set(mRestaurants.indexOf(restaurant), restaurant);
        notifyItemChanged(mRestaurants.indexOf(restaurant));
        }
        }

public static class BindingHolder extends RecyclerView.ViewHolder {
    private ItemRestaurantBinding binding;

    public BindingHolder(ItemRestaurantBinding binding) {
        super(binding.cardView);
        this.binding = binding;
    }
}
        }
