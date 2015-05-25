// Generated code from Butter Knife. Do not modify!
package com.kangsoo.pharmacy.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class ProfilesActivity$$ViewInjector<T extends com.kangsoo.pharmacy.ui.activity.ProfilesActivity> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689671, "field 'profilesList' and method 'onListItemClick'");
    target.profilesList = finder.castView(view, 2131689671, "field 'profilesList'");
    ((android.widget.AdapterView<?>) view).setOnItemClickListener(
      new android.widget.AdapterView.OnItemClickListener() {
        @Override public void onItemClick(
          android.widget.AdapterView<?> p0,
          android.view.View p1,
          int p2,
          long p3
        ) {
          target.onListItemClick(p2);
        }
      });
    view = finder.findRequiredView(source, 2131689582, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131689582, "field 'toolbar'");
  }

  @Override public void reset(T target) {
    target.profilesList = null;
    target.toolbar = null;
  }
}
