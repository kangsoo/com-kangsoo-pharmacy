package com.kangsoo.pharmacy.activity;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.ListView;

import com.google.inject.Inject;
import com.kangsoo.pharmacy.R;
import com.kangsoo.pharmacy.model.ShoppingItem;
import com.kangsoo.pharmacy.task.ShoppingItemAsyncTask;
import com.kangsoo.pharmacy.util.AvatarLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import wishlist.AsyncLoader;
import wishlist.SingleTypeAdapter;

/**
 * Fragment to display a list of {@link ShoppingItem} objects
 */
public class ShoppingItemListFragment extends ItemListFragment<ShoppingItem> implements
        Comparator<ShoppingItem> {

    @Inject
    private AvatarLoader avatars;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setEmptyText(R.string.no_bookmarks);
    }

    @Override
    public Loader<List<ShoppingItem>> onCreateLoader(int id, Bundle args) {
        return new AsyncLoader<List<ShoppingItem>>(getActivity()) {

            @Override
            public List<ShoppingItem> loadInBackground() {

                ShoppingItemAsyncTask cache = new ShoppingItemAsyncTask(getContext());

                List<ShoppingItem> filters = new ArrayList<ShoppingItem>(cache.doInBackground(20181L, 23383L));
                Collections.sort(filters, ShoppingItemListFragment.this);
                return filters;
            }
        };
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ShoppingItem filter = (ShoppingItem) l.getItemAtPosition(position);

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
    protected SingleTypeAdapter<ShoppingItem> createAdapter(List<ShoppingItem> items) {
        return new ShoppingItemListAdapter(
                getActivity().getLayoutInflater(),
                items.toArray(new ShoppingItem[items.size()]),
                avatars);
    }

    @Override
    public int compare(final ShoppingItem lhs, final ShoppingItem rhs) {
        //kskim to-do
//        int compare = CASE_INSENSITIVE_ORDER.compare(lhs.getRepository().generateId(), rhs.getRepository().generateId());
//        if (compare == 0)
//            compare = CASE_INSENSITIVE_ORDER.compare(lhs.toDisplay().toString(), rhs.toDisplay().toString());
//        return compare;

        return -1;
    }
}
