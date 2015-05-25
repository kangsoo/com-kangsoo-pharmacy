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
//@SuppressWarnings("serial")
public class ShoppingItem implements Comparable<ShoppingItem>, Serializable {

    public static final String ITEMID = "itemId";
    public static final String COMPANYID = "companyid";
    public static final String GROUPID = "groupid";
    public static final String USERID = "userid";
    public static final String USERNAME = "username";
    public static final String CREATEDATE = "createdate";
    public static final String MODIFIEDDATE = "modifieddate";
    public static final String CATEGORYID = "categoryid";
    public static final String SKU = "sku";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String PROPERTIES = "properties";
    public static final String FIELDS_ = "fields_";
    public static final String FIELDSQUANTITIES = "fieldsquantities";
    public static final String MINQUANTITY = "minquantity";
    public static final String MAXQUANTITY = "maxquantity";
    public static final String PRICE = "price";
    public static final String DISCOUNT = "discount";
    public static final String TAXABLE = "taxable";
    public static final String SHIPPING = "shipping";
    public static final String USESHIPPINGFORMULA = "useshippingformula";
    public static final String REQUIRESSHIPPING = "requiresshipping";
    public static final String STOCKQUANTITY = "stockquantity";
    public static final String FEATURED_ = "featured_";
    public static final String SALE_ = "sale_";
    public static final String SMALLIMAGE = "smallimage";
    public static final String SMALLIMAGEID = "smallimageid";
    public static final String SMALLIMAGEURL = "smallimageurl";
    public static final String MEDIUMIMAGE = "mediumimage";
    public static final String MEDIUMIMAGEID = "mediumimageid";
    public static final String MEDIUMIMAGEURL = "mediumimageurl";
    public static final String LARGEIMAGE = "largeimage";
    public static final String LARGEIMAGEID = "largeimageid";
    public static final String LARGEIMAGEURL = "largeimageurl";

    public long get_itemid() {
        return _itemid;
    }

    public void set_itemid(long _itemid) {
        this._itemid = _itemid;
    }

    public long get_companyid() {
        return _companyid;
    }

    public void set_companyid(long _companyid) {
        this._companyid = _companyid;
    }

    public long get_groupid() {
        return _groupid;
    }

    public void set_groupid(long _groupid) {
        this._groupid = _groupid;
    }

    public long get_userid() {
        return _userid;
    }

    public void set_userid(long _userid) {
        this._userid = _userid;
    }

    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }

    public long get_createdate() {
        return _createdate;
    }

    public void set_createdate(long _createdate) {
        this._createdate = _createdate;
    }

    public long get_modifieddate() {
        return _modifieddate;
    }

    public void set_modifieddate(long _modifieddate) {
        this._modifieddate = _modifieddate;
    }

    public long get_categoryid() {
        return _categoryid;
    }

    public void set_categoryid(long _categoryid) {
        this._categoryid = _categoryid;
    }

    public String get_sku() {
        return _sku;
    }

    public void set_sku(String _sku) {
        this._sku = _sku;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public String get_properties() {
        return _properties;
    }

    public void set_properties(String _properties) {
        this._properties = _properties;
    }

    public long get_fields_() {
        return _fields_;
    }

    public void set_fields_(long _fields_) {
        this._fields_ = _fields_;
    }

    public String get_fieldsquantities() {
        return _fieldsquantities;
    }

    public void set_fieldsquantities(String _fieldsquantities) {
        this._fieldsquantities = _fieldsquantities;
    }

    public long get_minquantity() {
        return _minquantity;
    }

    public void set_minquantity(long _minquantity) {
        this._minquantity = _minquantity;
    }

    public long get_maxquantity() {
        return _maxquantity;
    }

    public void set_maxquantity(long _maxquantity) {
        this._maxquantity = _maxquantity;
    }

    public long get_price() {
        return _price;
    }

    public void set_price(long _price) {
        this._price = _price;
    }

    public long get_discount() {
        return _discount;
    }

    public void set_discount(long _discount) {
        this._discount = _discount;
    }

    public long get_taxable() {
        return _taxable;
    }

    public void set_taxable(long _taxable) {
        this._taxable = _taxable;
    }

    public long get_shipping() {
        return _shipping;
    }

    public void set_shipping(long _shipping) {
        this._shipping = _shipping;
    }

    public long get_useshippingformula() {
        return _useshippingformula;
    }

    public void set_useshippingformula(long _useshippingformula) {
        this._useshippingformula = _useshippingformula;
    }

    public long get_requiresshipping() {
        return _requiresshipping;
    }

    public void set_requiresshipping(long _requiresshipping) {
        this._requiresshipping = _requiresshipping;
    }

    public long get_stockquantity() {
        return _stockquantity;
    }

    public void set_stockquantity(long _stockquantity) {
        this._stockquantity = _stockquantity;
    }

    public long get_featured_() {
        return _featured_;
    }

    public void set_featured_(long _featured_) {
        this._featured_ = _featured_;
    }

    public long get_sale_() {
        return _sale_;
    }

    public void set_sale_(long _sale_) {
        this._sale_ = _sale_;
    }

    public long get_smallimage() {
        return _smallimage;
    }

    public void set_smallimage(long _smallimage) {
        this._smallimage = _smallimage;
    }

    public long get_smallimageid() {
        return _smallimageid;
    }

    public void set_smallimageid(long _smallimageid) {
        this._smallimageid = _smallimageid;
    }

    public String get_smallimageurl() {
        return _smallimageurl;
    }

    public void set_smallimageurl(String _smallimageurl) {
        this._smallimageurl = _smallimageurl;
    }

    public long get_mediumimage() {
        return _mediumimage;
    }

    public void set_mediumimage(long _mediumimage) {
        this._mediumimage = _mediumimage;
    }

    public long get_mediumimageid() {
        return _mediumimageid;
    }

    public void set_mediumimageid(long _mediumimageid) {
        this._mediumimageid = _mediumimageid;
    }

    public String get_mediumimageurl() {
        return _mediumimageurl;
    }

    public void set_mediumimageurl(String _mediumimageurl) {
        this._mediumimageurl = _mediumimageurl;
    }

    public long get_largeimage() {
        return _largeimage;
    }

    public void set_largeimage(long _largeimage) {
        this._largeimage = _largeimage;
    }

    public long get_largeimageid() {
        return _largeimageid;
    }

    public void set_largeimageid(long _largeimageid) {
        this._largeimageid = _largeimageid;
    }

    public String get_largeimageurl() {
        return _largeimageurl;
    }

    public void set_largeimageurl(String _largeimageurl) {
        this._largeimageurl = _largeimageurl;
    }

    private long _itemid;
    private long _companyid;
    private long _groupid;
    private long _userid;
    private String _username;
    private long _createdate;
    private long _modifieddate;
    private long _categoryid;
    private String _sku;
    private String _name;
    private String _description;
    private String _properties;
    private long _fields_;
    private String _fieldsquantities;
    private long _minquantity;
    private long _maxquantity;
    private long _price;
    private long _discount;
    private long _taxable;
    private long _shipping;
    private long _useshippingformula;
    private long _requiresshipping;
    private long _stockquantity;
    private long _featured_;
    private long _sale_;
    private long _smallimage;
    private long _smallimageid;
    private String _smallimageurl;
    private long _mediumimage;
    private long _mediumimageid;
    private String _mediumimageurl;
    private long _largeimage;
    private long _largeimageid;
    private String _largeimageurl;

    public ShoppingItem(JSONObject jsonObj) throws JSONException {

        _itemid = jsonObj.getLong(ITEMID);
        _companyid = jsonObj.getLong(COMPANYID);
        _groupid = jsonObj.getLong(GROUPID);
        _userid = jsonObj.getLong(USERID);
        _username = jsonObj.getString(USERNAME);
        _createdate = jsonObj.getLong(CREATEDATE);
        _modifieddate = jsonObj.getLong(MODIFIEDDATE);
        _categoryid = jsonObj.getLong(CATEGORYID);
        _sku = jsonObj.getString(SKU);
        _name = jsonObj.getString(NAME);
        _description = jsonObj.getString(DESCRIPTION);
        _properties = jsonObj.getString(PROPERTIES);
        _fields_ = jsonObj.getLong(FIELDS_);
        _fieldsquantities = jsonObj.getString(FIELDSQUANTITIES);
        _minquantity = jsonObj.getLong(MINQUANTITY);
        _maxquantity = jsonObj.getLong(MAXQUANTITY);
        _price = jsonObj.getLong(PRICE);
        _discount = jsonObj.getLong(DISCOUNT);
        _taxable = jsonObj.getLong(TAXABLE);
        _shipping = jsonObj.getLong(SHIPPING);
        _useshippingformula = jsonObj.getLong(USESHIPPINGFORMULA);
        _requiresshipping = jsonObj.getLong(REQUIRESSHIPPING);
        _stockquantity = jsonObj.getLong(STOCKQUANTITY);
        _featured_ = jsonObj.getLong(FEATURED_);
        _sale_ = jsonObj.getLong(SALE_);
        _smallimage = jsonObj.getLong(SMALLIMAGE);
        _smallimageid = jsonObj.getLong(SMALLIMAGEID);
        _smallimageurl = jsonObj.getString(SMALLIMAGEURL);
        _mediumimage = jsonObj.getLong(MEDIUMIMAGE);
        _mediumimageid = jsonObj.getLong(MEDIUMIMAGEID);
        _mediumimageurl = jsonObj.getString(MEDIUMIMAGEURL);
        _largeimage = jsonObj.getLong(LARGEIMAGE);
        _largeimageid = jsonObj.getLong(LARGEIMAGEID);
        _largeimageurl = jsonObj.getString(LARGEIMAGEURL);
    }

    @SuppressLint("DefaultLocale")
    public int compareTo(ShoppingItem user) {
        return _name.toLowerCase().compareTo(user.get_name().toLowerCase());
    }

    public String toString() {
        return _name;
    }
}