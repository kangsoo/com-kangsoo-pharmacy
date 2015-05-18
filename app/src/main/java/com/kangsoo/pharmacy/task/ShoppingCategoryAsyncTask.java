package com.kangsoo.pharmacy.task;

import android.content.Context;
import android.os.AsyncTask;

import com.kangsoo.pharmacy.model.ShoppingCategory;
import com.kangsoo.pharmacy.util.SettingsUtil;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.v62.shoppingcategory.ShoppingCategoryService;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by bsnc on 2015-05-05.
 */
public class ShoppingCategoryAsyncTask extends AsyncTask<Void, Void, ArrayList<ShoppingCategory>> {

    private String _CLASS_NAME = ShoppingCategoryAsyncTask.class.getName();
    private Context mContext;

    public ShoppingCategoryAsyncTask(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    protected ArrayList<ShoppingCategory> doInBackground(Void... params) {

        ArrayList<ShoppingCategory> shoppingCategories = new ArrayList<ShoppingCategory>();

        Session session = SettingsUtil.getSession();

        ShoppingCategoryService service = new ShoppingCategoryService(session);


        try {

            JSONArray jsonArray = service.getCategories(20181);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);

                shoppingCategories.add(new ShoppingCategory(jsonObj));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return shoppingCategories;

    }

}
