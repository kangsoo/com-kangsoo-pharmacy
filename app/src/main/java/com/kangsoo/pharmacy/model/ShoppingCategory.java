/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 * <p/>
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * <p/>
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.kangsoo.pharmacy.model;

import android.annotation.SuppressLint;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * @author Bruno Farache
 */
@SuppressWarnings("serial")
public class ShoppingCategory implements Comparable<ShoppingCategory>, Serializable {

    public static final String CATEGORY_ID = "categoryId";
    public static final String COMPANY_ID = "companyId";
    public static final String CREATE_DATE = "createDate";
    public static final String DESCRIPTION = "description";
    public static final String GROUP_ID = "groupId";
    public static final String MODIFIED_DATE = "modifiedDate";
    public static final String NAME = "name";
    public static final String PARENT_CATEGORY_ID = "parentCategoryId";
    public static final String USER_ID = "userId";
    public static final String USER_NAME = "userName";

    /*
            "categoryId": 23383,
            "companyId": 20154,
            "createDate": 1431871315397,
            "description": "실내 페인트, 야외 페인트, 바디 페인트",
            "groupId": 20181,
            "modifiedDate": 1431871315397,
            "name": "도료",
            "parentCategoryId": 0,
            "userId": 20434,
            "userName": "kangsoo kim"
*/

    public long get_categoryId() {
        return _categoryId;
    }

    public void set_categoryId(long _categoryId) {
        this._categoryId = _categoryId;
    }

    public long get_companyId() {
        return _companyId;
    }

    public void set_companyId(long _companyId) {
        this._companyId = _companyId;
    }

    public long get_createDate() {
        return _createDate;
    }

    public void set_createDate(long _createDate) {
        this._createDate = _createDate;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public long get_groupId() {
        return _groupId;
    }

    public void set_groupId(long _groupId) {
        this._groupId = _groupId;
    }

    public long get_modifiedDate() {
        return _modifiedDate;
    }

    public void set_modifiedDate(long _modifiedDate) {
        this._modifiedDate = _modifiedDate;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public long get_parentCategoryId() {
        return _parentCategoryId;
    }

    public void set_parentCategoryId(long _parentCategoryId) {
        this._parentCategoryId = _parentCategoryId;
    }

    public long get_userId() {
        return _userId;
    }

    public void set_userId(long _userId) {
        this._userId = _userId;
    }

    public String get_userName() {
        return _userName;
    }

    public void set_userName(String _userName) {
        this._userName = _userName;
    }

    private long _categoryId;
    private long _companyId;
    private long _createDate;
    private String _description;
    private long _groupId;
    private long _modifiedDate;
    private String _name;
    private long _parentCategoryId;
    private long _userId;
    private String _userName;


    public ShoppingCategory(JSONObject jsonObj) throws JSONException {

        _categoryId = jsonObj.getLong(CATEGORY_ID);
        _companyId = jsonObj.getLong(COMPANY_ID);
        _createDate  = jsonObj.getLong(CREATE_DATE);
        _description  = jsonObj.getString(DESCRIPTION);
        _groupId = jsonObj.getLong(GROUP_ID);
        _modifiedDate  = jsonObj.getLong(MODIFIED_DATE);
        _name = jsonObj.getString(NAME);
        _parentCategoryId = jsonObj.getLong(PARENT_CATEGORY_ID);
        _userId = jsonObj.getLong(USER_ID);
        _userName = jsonObj.getString(USER_NAME);

    }

    @SuppressLint("DefaultLocale")
    public int compareTo(ShoppingCategory user) {
        return _name.toLowerCase().compareTo(user.get_name().toLowerCase());
    }

    public String toString() {
        return _name;
    }

}