package com.wannabemutants.flistapp.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.wannabemutants.flistapp.R;
import com.wannabemutants.flistapp.databinding.ItemCategoryBinding;
import com.wannabemutants.flistapp.model.Category;
import com.wannabemutants.flistapp.viewModel.CategoryViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cheglader on 5/15/16.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.BindingHolder> {
    private List<Category> mCategories;
    private Context mContext;

    public CategoryAdapter(Context context) {
        mContext = context;
        mCategories = new ArrayList<>();
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemCategoryBinding categoryBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_category,
                parent,
                false);
        return new BindingHolder(categoryBinding);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        ItemCategoryBinding categoryBinding = holder.binding;
        categoryBinding.setViewModel(new CategoryViewModel(mContext, mCategories.get(position)));
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    public void setItems(List<Category> categories) {
        mCategories = categories;
        notifyDataSetChanged();
    }

    public void addItem(Category category) {
        if (!mCategories.contains(category)) {
            mCategories.add(category);
            notifyItemInserted(mCategories.size() - 1);
        } else {
            mCategories.set(mCategories.indexOf(category), category);
            notifyItemChanged(mCategories.indexOf(category));
        }
    }

    public void addItems(List<Category> categories) {
        for (Category category : categories) {
            this.addItem(category);
        }
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private ItemCategoryBinding binding;

        public BindingHolder(ItemCategoryBinding binding) {
            super(binding.cardView);
            this.binding = binding;
        }
    }
}
