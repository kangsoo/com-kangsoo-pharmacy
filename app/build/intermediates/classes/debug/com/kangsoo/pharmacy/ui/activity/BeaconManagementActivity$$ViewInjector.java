// Generated code from Butter Knife. Do not modify!
package com.kangsoo.pharmacy.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class BeaconManagementActivity$$ViewInjector<T extends com.kangsoo.pharmacy.ui.activity.BeaconManagementActivity> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689588, "field 'beaconForm'");
    target.beaconForm = finder.castView(view, 2131689588, "field 'beaconForm'");
    view = finder.findRequiredView(source, 2131689590, "field 'proximityUuidEntry' and method 'writeProximityUUID'");
    target.proximityUuidEntry = finder.castView(view, 2131689590, "field 'proximityUuidEntry'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.writeProximityUUID();
        }
      });
    view = finder.findRequiredView(source, 2131689591, "field 'majorEntry' and method 'writeMajor'");
    target.majorEntry = finder.castView(view, 2131689591, "field 'majorEntry'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.writeMajor();
        }
      });
    view = finder.findRequiredView(source, 2131689592, "field 'minorEntry' and method 'writeMinor'");
    target.minorEntry = finder.castView(view, 2131689592, "field 'minorEntry'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.writeMinor();
        }
      });
    view = finder.findRequiredView(source, 2131689593, "field 'powerLevelEntry' and method 'writePowerLevel'");
    target.powerLevelEntry = finder.castView(view, 2131689593, "field 'powerLevelEntry'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.writePowerLevel();
        }
      });
    view = finder.findRequiredView(source, 2131689594, "field 'advertisingIntervalEntry' and method 'writeAdvertisingInterval'");
    target.advertisingIntervalEntry = finder.castView(view, 2131689594, "field 'advertisingIntervalEntry'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.writeAdvertisingInterval();
        }
      });
    view = finder.findRequiredView(source, 2131689596, "field 'batteryLevelEntry'");
    target.batteryLevelEntry = finder.castView(view, 2131689596, "field 'batteryLevelEntry'");
    view = finder.findRequiredView(source, 2131689597, "field 'manufacturerNameEntry'");
    target.manufacturerNameEntry = finder.castView(view, 2131689597, "field 'manufacturerNameEntry'");
    view = finder.findRequiredView(source, 2131689598, "field 'modelNameEntry' and method 'writeModelName'");
    target.modelNameEntry = finder.castView(view, 2131689598, "field 'modelNameEntry'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.writeModelName();
        }
      });
    view = finder.findRequiredView(source, 2131689599, "field 'firmwareRevisionEntry'");
    target.firmwareRevisionEntry = finder.castView(view, 2131689599, "field 'firmwareRevisionEntry'");
    view = finder.findRequiredView(source, 2131689600, "field 'hardwareRevisionEntry'");
    target.hardwareRevisionEntry = finder.castView(view, 2131689600, "field 'hardwareRevisionEntry'");
    view = finder.findRequiredView(source, 2131689601, "field 'acceptProfileEntry' and method 'acceptProfile'");
    target.acceptProfileEntry = finder.castView(view, 2131689601, "field 'acceptProfileEntry'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.acceptProfile();
        }
      });
    view = finder.findRequiredView(source, 2131689602, "field 'applyConfigEntry' and method 'applyConfig'");
    target.applyConfigEntry = finder.castView(view, 2131689602, "field 'applyConfigEntry'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.applyConfig();
        }
      });
    view = finder.findRequiredView(source, 2131689589, "field 'progressBar'");
    target.progressBar = view;
    view = finder.findRequiredView(source, 2131689582, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131689582, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131689595, "method 'writePassword'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.writePassword();
        }
      });
    view = finder.findRequiredView(source, 2131689603, "method 'resetDevice'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.resetDevice();
        }
      });
    view = finder.findRequiredView(source, 2131689604, "method 'restoreDefaultSettings'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.restoreDefaultSettings();
        }
      });
  }

  @Override public void reset(T target) {
    target.beaconForm = null;
    target.proximityUuidEntry = null;
    target.majorEntry = null;
    target.minorEntry = null;
    target.powerLevelEntry = null;
    target.advertisingIntervalEntry = null;
    target.batteryLevelEntry = null;
    target.manufacturerNameEntry = null;
    target.modelNameEntry = null;
    target.firmwareRevisionEntry = null;
    target.hardwareRevisionEntry = null;
    target.acceptProfileEntry = null;
    target.applyConfigEntry = null;
    target.progressBar = null;
    target.toolbar = null;
  }
}
