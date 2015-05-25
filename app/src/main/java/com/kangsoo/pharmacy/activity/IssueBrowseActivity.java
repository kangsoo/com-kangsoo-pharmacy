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
package com.kangsoo.pharmacy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.google.inject.Inject;
import com.kangsoo.pharmacy.R;
import com.kangsoo.pharmacy.activity.Intents.Builder;
import com.kangsoo.pharmacy.model.ShoppingCategory;

import static com.kangsoo.pharmacy.activity.Intents.EXTRA_ISSUE_FILTER;
import static com.kangsoo.pharmacy.activity.Intents.EXTRA_REPOSITORY;

/**
 * Activity for browsing a list of issues scoped to a single {@link ShoppingCategory}
 */
public class IssueBrowseActivity extends DialogFragmentActivity {

    private ShoppingCategory shop;

    /**
     * Create intent to browse the filtered issues
     *
     * @param filter
     * @return intent
     */
    public static Intent createIntent(ShoppingCategory filter) {
        return new Builder("issues.VIEW")
                .repo(filter.get_groupId())
                .add(EXTRA_ISSUE_FILTER, filter).toIntent();
    }

    @Inject
//    private AvatarLoader avatars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        shop = getSerializableExtra(EXTRA_REPOSITORY);

        setContentView(R.layout.repo_issue_list);

        setSupportActionBar((android.support.v7.widget.Toolbar) findViewById(R.id.toolbar));

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(shop.get_name());
        actionBar.setSubtitle(shop.get_description());
        actionBar.setDisplayHomeAsUpEnabled(true);
//        avatars.bind(actionBar, repo.getOwner());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
//                Intent intent = FiltersViewActivity.createIntent();
//                intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP | FLAG_ACTIVITY_SINGLE_TOP);
//                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
