package com.kangsoo.pharmacy.fragment;

import android.app.Activity;

import com.google.inject.Inject;
import com.kangsoo.pharmacy.model.User;
import com.kangsoo.pharmacy.util.SettingsUtil;
import com.liferay.mobile.android.auth.SignIn;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.task.callback.typed.JSONObjectAsyncTaskCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bsnc on 2015-05-11.
 */
public class UserLoader extends JSONObjectAsyncTaskCallback {

    private User user;
    private Activity mActivity;
    private List<User> data;
    private UserLoaderCallbacks mCallbacks;

    @Inject
    public UserLoader(Activity activity) {
        mActivity = activity;
        data = new ArrayList<User>();
        mCallbacks = (UserLoaderCallbacks) activity;
    }

    public void init() {

        SettingsUtil.init(mActivity);

        Session session = SettingsUtil.getSession();

        SignIn.signIn(session, this);
    }

    @Override
    public void onSuccess(JSONObject result) {

        try {
            user = new User(result);
            data.add(user);
            mCallbacks.onUserInformationSelected(user);

        } catch (JSONException e) {
            onFailure(e);
        }

    }

    @Override
    public void onFailure(Exception exception) {

    }

    public interface UserLoaderCallbacks {
        void onUserInformationSelected(User user);
    }

}
