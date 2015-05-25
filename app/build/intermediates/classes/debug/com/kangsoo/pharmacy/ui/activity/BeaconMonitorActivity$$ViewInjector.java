// Generated code from Butter Knife. Do not modify!
package com.kangsoo.pharmacy.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class BeaconMonitorActivity$$ViewInjector<T extends com.kangsoo.pharmacy.ui.activity.BeaconMonitorActivity> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689582, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131689582, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131689611, "field 'list'");
    target.list = finder.castView(view, 2131689611, "field 'list'");
  }

  @Override public void reset(T target) {
    target.toolbar = null;
    target.list = null;
  }
}
