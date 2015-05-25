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

import android.view.LayoutInflater;

import com.kangsoo.pharmacy.R;
import com.kangsoo.pharmacy.model.ShoppingItem;
import com.kangsoo.pharmacy.util.AvatarLoader;

import wishlist.SingleTypeAdapter;

/**
 * Adapter to display a list of {@link ShoppingItem} objects
 */
public class ShoppingItemListAdapter extends SingleTypeAdapter<ShoppingItem> {

    private final AvatarLoader avatars;

    /**
     * Create {@link ShoppingItem} list adapter
     *
     * @param inflater
     * @param elements
     * @param avatars
     */
    public ShoppingItemListAdapter(LayoutInflater inflater, ShoppingItem[] elements, AvatarLoader avatars) {
        super(inflater, R.layout.issues_filter_item);

        this.avatars = avatars;
        setItems(elements);
    }

    @Override
    protected int[] getChildViewIds() {
        return new int[]{R.id.iv_avatar, R.id.tv_repo_name, R.id.tv_filter_state,
                R.id.tv_filter_labels, R.id.tv_filter_milestone, R.id.ll_assignee,
                R.id.tv_filter_assignee, R.id.iv_assignee_avatar};
    }

    @Override
    protected void update(int position, ShoppingItem ShoppingItem) {

        //kskim to-do
//        avatars.bind(imageView(0), ShoppingItem.getRepository().getOwner());

        setText(1, ShoppingItem.get_name());
        setText(2, ShoppingItem.get_description());
        setText(3, ShoppingItem.get_smallimageurl());

//        Collection<Label> labels = ShoppingItem.getLabels();
//        if (labels != null && !labels.isEmpty()) {
//            TextView labelsText = textView(3);
//            LabelDrawableSpan.setText(labelsText, labels);
//            ViewUtils.setGone(labelsText, false);
//        } else
//            setGone(3, true);

//        Milestone milestone = ShoppingItem.getMilestone();
//        if (milestone != null)
//            ViewUtils.setGone(setText(4, milestone.getTitle()), false);
//        else
//            setGone(4, true);

//        User assignee = ShoppingItem.getAssignee();
//        if (assignee != null) {
//            avatars.bind(imageView(7), assignee);
//            ViewUtils.setGone(setText(6, assignee.getLogin()), false);
//        } else
//            setGone(5, true);
    }
}
