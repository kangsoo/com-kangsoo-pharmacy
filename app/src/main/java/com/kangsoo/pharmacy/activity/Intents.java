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

import com.kangsoo.pharmacy.model.ShoppingCategory;
import com.kangsoo.pharmacy.model.User;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Helper for creating intents
 */
public class Intents {

    /**
     * Prefix for all intents created
     */
    public static final String INTENT_PREFIX = "com.kangsoo.pharmacy.";

    /**
     * Prefix for all extra data added to intents
     */
    public static final String INTENT_EXTRA_PREFIX = INTENT_PREFIX + "extra.";

    /**
     * Repository handle
     */
    public static final String EXTRA_REPOSITORY = INTENT_EXTRA_PREFIX
            + "REPOSITORY";

    /**
     * Repository ids collection handle
     */
    public static final String EXTRA_REPOSITORIES = INTENT_EXTRA_PREFIX
            + "REPOSITORIES";

    /**
     * Repository name
     */
    public static final String EXTRA_REPOSITORY_NAME = INTENT_EXTRA_PREFIX
            + "REPOSITORY_NAME";

    /**
     * Repository owner
     */
    public static final String EXTRA_REPOSITORY_OWNER = INTENT_EXTRA_PREFIX
            + "REPOSITORY_OWNER";

    /**
     * Issue number
     */
    public static final String EXTRA_ISSUE_NUMBER = INTENT_EXTRA_PREFIX
            + "ISSUE_NUMBER";

    /**
     * Issue handle
     */
    public static final String EXTRA_ISSUE = INTENT_EXTRA_PREFIX + "ISSUE";

    /**
     * Issue number collection handle
     */
    public static final String EXTRA_ISSUE_NUMBERS = INTENT_EXTRA_PREFIX
            + "ISSUE_NUMBERS";

    /**
     * Gist id
     */
    public static final String EXTRA_GIST_ID = INTENT_EXTRA_PREFIX + "GIST_ID";

    /**
     * List of Gist ids
     */
    public static final String EXTRA_GIST_IDS = INTENT_EXTRA_PREFIX
            + "GIST_IDS";

    /**
     * Gist handle
     */
    public static final String EXTRA_GIST = INTENT_EXTRA_PREFIX + "GIST";

    /**
     * Gist file handle
     */
    public static final String EXTRA_GIST_FILE = INTENT_EXTRA_PREFIX
            + "GIST_FILE";

    /**
     * User handle
     */
    public static final String EXTRA_USER = INTENT_EXTRA_PREFIX + "USER";

    /**
     * {@link ArrayList} handle of {@link User} objects
     */
    public static final String EXTRA_USERS = INTENT_EXTRA_PREFIX + "USERS";

    /**
     * Boolean value which indicates if a user is a collaborator on the repo
     */
    public static final String EXTRA_IS_COLLABORATOR = INTENT_EXTRA_PREFIX + "IS_COLLABORATOR";

    /**
     * Boolean value which indicates if a user is owner of the repo
     */
    public static final String EXTRA_IS_OWNER = INTENT_EXTRA_PREFIX + "IS_OWNER";

    /**
     * Issue filter handle
     */
    public static final String EXTRA_ISSUE_FILTER = INTENT_EXTRA_PREFIX
            + "ISSUE_FILTER";

    /**
     * Comment body
     */
    public static final String EXTRA_COMMENT_BODY = INTENT_EXTRA_PREFIX
            + "COMMENT_BODY";

    /**
     * Comments handle
     */
    public static final String EXTRA_COMMENTS = INTENT_EXTRA_PREFIX
            + "COMMENTS";

    /**
     * Comment handle
     */
    public static final String EXTRA_COMMENT = INTENT_EXTRA_PREFIX + "COMMENT";

    /**
     * Integer position
     */
    public static final String EXTRA_POSITION = INTENT_EXTRA_PREFIX
            + "POSITION";

    /**
     * Base commit name
     */
    public static final String EXTRA_BASE = INTENT_EXTRA_PREFIX + "BASE";

    /**
     * Base commit names
     */
    public static final String EXTRA_BASES = INTENT_EXTRA_PREFIX + "BASES";

    /**
     * Base commit name
     */
    public static final String EXTRA_HEAD = INTENT_EXTRA_PREFIX + "HEAD";

    /**
     * Handle to a {@link String} path
     */
    public static final String EXTRA_PATH = INTENT_EXTRA_PREFIX + "PATH";

    /**
     * Builder for generating an intent configured with extra data such as an
     * issue, repository, or gist
     */
    public static class Builder {

        private final Intent intent;

        /**
         * Create builder with suffix
         *
         * @param actionSuffix
         */
        public Builder(String actionSuffix) {
            // actionSuffix = e.g. "repos.VIEW"
            intent = new Intent(INTENT_PREFIX + actionSuffix);
        }

        /**
         * Add repository id to intent being built up
         *
         * @param shoppingCategory
         * @return this builder
         */
        public Builder repo(ShoppingCategory shoppingCategory) {
            return add(EXTRA_REPOSITORY_NAME, shoppingCategory.get_name()).add(
                    EXTRA_REPOSITORY_OWNER, shoppingCategory.get_description());
        }

        /**
         * Add repository to intent being built up
         *
         * @param groupId
         * @return this builder
         */
        public Builder repo(long groupId) {
            return add(EXTRA_REPOSITORY, groupId);
        }

        /**
         * Add user to intent being built up
         *
         * @param user
         * @return this builder;
         */
        public Builder user(User user) {
            return add(EXTRA_USER, user);
        }

        /**
         * Add extra field data value to intent being built up
         *
         * @param fieldName
         * @param value
         * @return this builder
         */
        public Builder add(String fieldName, String value) {
            intent.putExtra(fieldName, value);
            return this;
        }

        /**
         * Add extra field data values to intent being built up
         *
         * @param fieldName
         * @param values
         * @return this builder
         */
        public Builder add(String fieldName, CharSequence[] values) {
            intent.putExtra(fieldName, values);
            return this;
        }

        /**
         * Add extra field data value to intent being built up
         *
         * @param fieldName
         * @param value
         * @return this builder
         */
        public Builder add(String fieldName, int value) {
            intent.putExtra(fieldName, value);
            return this;
        }

        /**
         * Add extra field data value to intent being built up
         *
         * @param fieldName
         * @param values
         * @return this builder
         */
        public Builder add(String fieldName, int[] values) {
            intent.putExtra(fieldName, values);
            return this;
        }

        /**
         * Add extra field data value to intent being built up
         *
         * @param fieldName
         * @param values
         * @return this builder
         */
        public Builder add(String fieldName, boolean[] values) {
            intent.putExtra(fieldName, values);
            return this;
        }

        /**
         * Add extra field data value to intent being built up
         *
         * @param fieldName
         * @param value
         * @return this builder
         */
        public Builder add(String fieldName, Serializable value) {
            intent.putExtra(fieldName, value);
            return this;
        }

        /**
         * Get built intent
         *
         * @return intent
         */
        public Intent toIntent() {
            return intent;
        }
    }
}