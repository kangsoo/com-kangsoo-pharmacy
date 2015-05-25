// Generated code from Butter Knife. Do not modify!
package com.kangsoo.pharmacy.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class MainActivity$$ViewInjector<T extends com.kangsoo.pharmacy.ui.activity.MainActivity> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689582, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131689582, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131689649, "method 'startRanging'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.startRanging();
        }
      });
    view = finder.findRequiredView(source, 2131689650, "method 'startMonitoring'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.startMonitoring();
        }
      });
    view = finder.findRequiredView(source, 2131689651, "method 'startForegroundBackgroundScan'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.startForegroundBackgroundScan();
        }
      });
  }

  @Override public void reset(T target) {
    target.toolbar = null;
  }
}
