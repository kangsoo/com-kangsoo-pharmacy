package com.kangsoo.pharmacy.task;

import android.content.Context;
import android.os.AsyncTask;

import com.kangsoo.pharmacy.model.ShoppingItem;
import com.kangsoo.pharmacy.util.SettingsUtil;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.v62.shoppingitem.ShoppingItemService;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by bsnc on 2015-05-05.
 */
public class ShoppingItemAsyncTask extends AsyncTask<Long, Void, ArrayList<ShoppingItem>> {

    private Context mContext;

    public ShoppingItemAsyncTask(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public ArrayList<ShoppingItem> doInBackground(Long... params) {

        long groupId = params[0];
        long categoryId = params[1];

        ArrayList<ShoppingItem> shoppingItems = new ArrayList<ShoppingItem>();

        Session session = SettingsUtil.getSession();

        ShoppingItemService service = new ShoppingItemService(session);

        try {

            JSONArray jsonArray = service.getItems(groupId, categoryId);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);

                shoppingItems.add(new ShoppingItem(jsonObj));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return shoppingItems;

    }

}
