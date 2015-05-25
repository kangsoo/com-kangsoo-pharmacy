// Generated code from Butter Knife. Do not modify!
package com.kangsoo.pharmacy.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class BeaconRangeActivity$$ViewInjector<T extends com.kangsoo.pharmacy.ui.activity.BeaconRangeActivity> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689582, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131689582, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131689612, "field 'deviceList'");
    target.deviceList = finder.castView(view, 2131689612, "field 'deviceList'");
  }

  @Override public void reset(T target) {
    target.toolbar = null;
    target.deviceList = null;
  }
}
