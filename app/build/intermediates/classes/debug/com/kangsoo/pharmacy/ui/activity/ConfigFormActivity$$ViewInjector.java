// Generated code from Butter Knife. Do not modify!
package com.kangsoo.pharmacy.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class ConfigFormActivity$$ViewInjector<T extends com.kangsoo.pharmacy.ui.activity.ConfigFormActivity> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689582, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131689582, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131689614, "field 'generateProximityUUIDButton' and method 'onGenerateRandomProximityUUID'");
    target.generateProximityUUIDButton = finder.castView(view, 2131689614, "field 'generateProximityUUIDButton'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onGenerateRandomProximityUUID();
        }
      });
    view = finder.findRequiredView(source, 2131689620, "field 'submitButton' and method 'onSubmit'");
    target.submitButton = finder.castView(view, 2131689620, "field 'submitButton'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onSubmit();
        }
      });
    view = finder.findRequiredView(source, 2131689613, "field 'proximityUUIDText'");
    target.proximityUUIDText = finder.castView(view, 2131689613, "field 'proximityUUIDText'");
    view = finder.findRequiredView(source, 2131689615, "field 'majorText'");
    target.majorText = finder.castView(view, 2131689615, "field 'majorText'");
    view = finder.findRequiredView(source, 2131689616, "field 'minorText'");
    target.minorText = finder.castView(view, 2131689616, "field 'minorText'");
    view = finder.findRequiredView(source, 2131689617, "field 'powerLevelText'");
    target.powerLevelText = finder.castView(view, 2131689617, "field 'powerLevelText'");
    view = finder.findRequiredView(source, 2131689618, "field 'advertisingIntervalText'");
    target.advertisingIntervalText = finder.castView(view, 2131689618, "field 'advertisingIntervalText'");
    view = finder.findRequiredView(source, 2131689619, "field 'beaconUniqueIdText'");
    target.beaconUniqueIdText = finder.castView(view, 2131689619, "field 'beaconUniqueIdText'");
  }

  @Override public void reset(T target) {
    target.toolbar = null;
    target.generateProximityUUIDButton = null;
    target.submitButton = null;
    target.proximityUUIDText = null;
    target.majorText = null;
    target.minorText = null;
    target.powerLevelText = null;
    target.advertisingIntervalText = null;
    target.beaconUniqueIdText = null;
  }
}
