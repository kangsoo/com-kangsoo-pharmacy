package com.kangsoo.pharmacy.activity;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.ListView;

import com.google.inject.Inject;
import com.kangsoo.pharmacy.R;
import com.kangsoo.pharmacy.model.ShoppingCategory;
import com.kangsoo.pharmacy.task.ShoppingCategoryAsyncTask;
import com.kangsoo.pharmacy.util.AvatarLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import wishlist.AsyncLoader;
import wishlist.SingleTypeAdapter;

/**
 * Fragment to display a list of {@link ShoppingCategory} objects
 */
public class FilterListFragment extends ItemListFragment<ShoppingCategory> implements
        Comparator<ShoppingCategory> {

    @Inject
    private AvatarLoader avatars;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setEmptyText(R.string.no_bookmarks);
    }

    @Override
    public Loader<List<ShoppingCategory>> onCreateLoader(int id, Bundle args) {
        return new AsyncLoader<List<ShoppingCategory>>(getActivity()) {

            @Override
            public List<ShoppingCategory> loadInBackground() {

                ShoppingCategoryAsyncTask cache = new ShoppingCategoryAsyncTask(getContext());

                List<ShoppingCategory> filters = new ArrayList<ShoppingCategory>(cache.doInBackground());
                Collections.sort(filters, FilterListFragment.this);
                return filters;
            }
        };
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ShoppingCategory filter = (ShoppingCategory) l.getItemAtPosition(position);
        //kskim to-do
//        startActivity(IssueBrowseActivity.createIntent(filter));
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    protected int getErrorMessage(Exception exception) {
        return R.string.error_bookmarks_load;
    }

    @Override
    protected SingleTypeAdapter<ShoppingCategory> createAdapter(List<ShoppingCategory> items) {
        return new FilterListAdapter(getActivity().getLayoutInflater(), items.toArray(new ShoppingCategory[items.size()]), avatars);
    }

    @Override
    public int compare(final ShoppingCategory lhs, final ShoppingCategory rhs) {
        //kskim to-do
//        int compare = CASE_INSENSITIVE_ORDER.compare(lhs.getRepository().generateId(), rhs.getRepository().generateId());
//        if (compare == 0)
//            compare = CASE_INSENSITIVE_ORDER.compare(lhs.toDisplay().toString(), rhs.toDisplay().toString());
//        return compare;

        return -1;
    }
}
