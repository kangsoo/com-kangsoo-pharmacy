/*
 * Copyright 2012 GitHub Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kangsoo.pharmacy.fragment;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.inject.Inject;
import com.kangsoo.pharmacy.R;
import com.kangsoo.pharmacy.activity.LabelDrawableSpan;
import com.kangsoo.pharmacy.activity.PagedItemFragment;
import com.kangsoo.pharmacy.activity.ResourcePager;
import com.kangsoo.pharmacy.model.ShoppingCategory;
import com.kangsoo.pharmacy.model.ShoppingItem;
import com.kangsoo.pharmacy.task.ShoppingItemAsyncTask;
import com.kangsoo.pharmacy.util.AvatarLoader;

import org.eclipse.egit.github.core.Issue;

import java.util.ArrayList;
import java.util.List;

import wishlist.SingleTypeAdapter;

import static android.app.Activity.RESULT_OK;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.kangsoo.pharmacy.activity.Intents.EXTRA_ISSUE;
import static com.kangsoo.pharmacy.activity.Intents.EXTRA_ISSUE_FILTER;
import static com.kangsoo.pharmacy.activity.Intents.EXTRA_REPOSITORY;
import static com.kangsoo.pharmacy.activity.RequestCodes.ISSUE_CREATE;
import static com.kangsoo.pharmacy.activity.RequestCodes.ISSUE_FILTER_EDIT;
import static com.kangsoo.pharmacy.activity.RequestCodes.ISSUE_VIEW;

/**
 * Fragment to display a list of issues
 */
public class IssuesFragment extends PagedItemFragment<ShoppingItem> {

    private ShoppingItem shoppingItem;

    private ShoppingCategory shoppingCategory;

    private Activity mActivity;

    private List<ShoppingItem> items;

    private View filterHeader;

    private TextView state;

    private ImageView assigneeAvatar;

    private View assigneeArea;

    private TextView assignee;

    private TextView labels;

    private TextView milestone;

    @Inject
    private AvatarLoader avatars;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mActivity = activity;
        shoppingCategory = getSerializableExtra(EXTRA_ISSUE_FILTER);
//        repository = getSerializableExtra(EXTRA_REPOSITORY);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (shoppingItem == null) {
            ShoppingItemAsyncTask cache = new ShoppingItemAsyncTask(mActivity);
            items = new ArrayList<ShoppingItem>(
                    cache.doInBackground(shoppingCategory.get_groupId(),
                            shoppingCategory.get_categoryId()));
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        filterHeader = getLayoutInflater(savedInstanceState).inflate(
                R.layout.issues_filter_header, null);
        state = (TextView) filterHeader.findViewById(R.id.tv_filter_state);
        labels = (TextView) filterHeader.findViewById(R.id.tv_filter_labels);
        milestone = (TextView) filterHeader
                .findViewById(R.id.tv_filter_milestone);
        assigneeArea = filterHeader.findViewById(R.id.ll_assignee);
        assignee = (TextView) filterHeader.findViewById(R.id.tv_filter_assignee);
        //kskim to-do : no image
//        assigneeAvatar = (ImageView) filterHeader.findViewById(R.id.iv_assignee_avatar);
        updateFilterSummary();

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void configureList(Activity activity, ListView listView) {
        super.configureList(activity, listView);

        getListAdapter().addHeader(filterHeader, shoppingCategory, true);
    }

    private void updateFilterSummary() {
        /*if (filter.isOpen())
            state.setText(R.string.open_issues);
        else
            state.setText(R.string.closed_issues);
*/
        //name
        state.setText(R.string.name);

//        Collection<Label> filterLabels = filter.getLabels();
        if (items != null && !items.isEmpty()) {
            LabelDrawableSpan.setText(labels, items);
            labels.setVisibility(VISIBLE);
        } else
            labels.setVisibility(GONE);

//        Milestone filterMilestone = filter.getMilestone();
//        if (filterMilestone != null) {
//            milestone.setText(filterMilestone.getTitle());
//            milestone.setVisibility(VISIBLE);
//        } else
//            milestone.setVisibility(GONE);
//
//        User user = filter.getAssignee();
//        if (user != null) {
//            avatars.bind(assigneeAvatar, user);
//            assignee.setText(user.getLogin());
//            assigneeArea.setVisibility(VISIBLE);
//        } else
//            assigneeArea.setVisibility(GONE);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setEmptyText(R.string.no_issues);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //kskim to-do
//        if (position == 0)
//            startActivityForResult(
//                    EditIssuesFilterActivity.createIntent(filter),
//                    ISSUE_FILTER_EDIT);
//        else
//            startActivityForResult(
//                    IssuesViewActivity.createIntent(items, repository, position
//                            - getListAdapter().getHeadersCount()), ISSUE_VIEW);
    }

    @Override
    public void onCreateOptionsMenu(Menu optionsMenu, MenuInflater inflater) {
        inflater.inflate(R.menu.issues, optionsMenu);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = optionsMenu.findItem(R.id.m_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

        Bundle args = new Bundle();
        //kskim to-do
        args.putSerializable(EXTRA_REPOSITORY, shoppingCategory);
        searchView.setAppSearchData(args);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!isUsable())
            return false;
        switch (item.getItemId()) {
            case R.id.create_issue:
//                startActivityForResult(EditIssueActivity.createIntent(repository),ISSUE_CREATE);
                return true;
            case R.id.m_filter:
//                startActivityForResult(EditIssuesFilterActivity.createIntent(filter), ISSUE_FILTER_EDIT);
                return true;
            case R.id.m_bookmark:
//                cache.addIssueFilter(filter, new RequestFuture<IssueFilter>() {
//
//                    public void success(IssueFilter response) {
//                        ToastUtils.show(getActivity(), R.string.message_filter_saved);
//                    }
//                });
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == ISSUE_FILTER_EDIT
                && data != null) {

            ShoppingItem newFilter = (ShoppingItem) data.getSerializableExtra(EXTRA_ISSUE_FILTER);
//            if (!filter.equals(newFilter)) {
//                filter = newFilter;
//                updateFilterSummary();
//                pager.reset();
//                refreshWithProgress();
//                return;
//            }
        }

        if (requestCode == ISSUE_VIEW) {
            notifyDataSetChanged();
            forceRefresh();
            return;
        }

        if (requestCode == ISSUE_CREATE && resultCode == RESULT_OK) {
            Issue created = (Issue) data.getSerializableExtra(EXTRA_ISSUE);
            forceRefresh();
            //kskim to-do
//            startActivityForResult(IssuesViewActivity.createIntent(created, repository), ISSUE_VIEW);
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    //kskim to-do
/*
    @Override
    protected ResourcePager<Issue> createPager() {
        return new IssuePager(store) {

            @Override
            public PageIterator<Issue> createIterator(int page, int size) {
                return service.pageIssues(repository, filter.toFilterMap(),
                        page, size);
            }
        };
    }
*/

    @Override
    protected ResourcePager<ShoppingItem> createPager() {
        return null;
    }


    @Override
    protected int getLoadingMessage() {
        return R.string.loading_issues;
    }

    @Override
    protected int getErrorMessage(Exception exception) {
        return R.string.error_issues_load;
    }

    @Override
    protected SingleTypeAdapter<ShoppingItem> createAdapter(List<ShoppingItem> items) {
        return null;
//        return new RepositoryIssueListAdapter(
//                getActivity().getLayoutInflater(),
//                items.toArray(new Issue[items.size()]), avatars);
    }
}
