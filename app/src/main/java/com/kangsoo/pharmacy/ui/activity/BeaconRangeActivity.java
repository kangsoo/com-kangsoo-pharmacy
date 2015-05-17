package com.kangsoo.pharmacy.ui.activity;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.kangsoo.pharmacy.R;
import com.kangsoo.pharmacy.adapter.BeaconBaseAdapter;
import com.kangsoo.pharmacy.dialog.PasswordDialogFragment;
import com.kangsoo.pharmacy.util.Utils;
import com.kontakt.sdk.android.configuration.BeaconActivityCheckConfiguration;
import com.kontakt.sdk.android.configuration.ForceScanConfiguration;
import com.kontakt.sdk.android.connection.OnServiceBoundListener;
import com.kontakt.sdk.android.data.RssiCalculators;
import com.kontakt.sdk.android.device.BeaconDevice;
import com.kontakt.sdk.android.device.Region;
import com.kontakt.sdk.android.manager.BeaconManager;
import com.kontakt.sdk.core.interfaces.BiConsumer;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class BeaconRangeActivity extends Fragment {

    private static final int REQUEST_CODE_ENABLE_BLUETOOTH = 1;

    private static final int REQUEST_CODE_CONNECT_TO_DEVICE = 2;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.device_list)
    ListView deviceList;

    private BeaconBaseAdapter adapter;
    private BeaconManager beaconManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.beacon_range_activity, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ButterKnife.inject(getActivity());

        adapter = new BeaconBaseAdapter(getActivity());
        beaconManager = BeaconManager.newInstance(getActivity());
        // Works only for Android L OS version
        beaconManager.setScanMode(BeaconManager.SCAN_MODE_BALANCED);
        //Calculate rssi value basing on arithmethic mean basing on 5 last notified values
        beaconManager.setRssiCalculator(RssiCalculators.newLimitedMeanRssiCalculator(5));
/*
        beaconManager.setRssiCalculator(new RssiCalculators.CustomRssiCalculator() { //Provide your own Rssi Calculator to estimate manipulate Rssi value
            @Override                                                                  //and thus Proximity from Beacon device
            public double calculateRssi(int beaconHashCode, int rssiValue) {
                return rssiValue;
            }

            @Override
            public void clear() {

            }
        });
*/

        //beaconManager.addFilter(Filters.newProximityUUIDFilter(BeaconManager.DEFAULT_KONTAKT_BEACON_PROXIMITY_UUID)); //accept Beacons with default Proximity UUID only
        //(f7826da6-4fa2-4e98-8024-bc5b71e0893e)
/*
        beaconManager.addFilter(Filters.newAddressFilter("00:00:00:00:00:00")); //accept Beacons with specified MAC address only
        beaconManager.addFilter(Filters.newBeaconUniqueIdFilter("myID"));         //accept Beacons with specified Unique Id only
        beaconManager.addFilter(Filters.newDeviceNameFilter("my_beacon_name"));   //accept Beacons with specified name only
        beaconManager.addFilter(Filters.newFirmwareFilter(26));                   //accept Beacons with specified Firmware version only
        beaconManager.addFilter(Filters.newMajorFilter(666));                     //accept Beacons with specified Major only
        beaconManager.addFilter(Filters.newMinorFilter(333));                     //accept Beacons with specified Minor only
        beaconManager.addFilter(Filters.newMultiFilterBuilder()                   //accept Beacon matching constraints specified in MultiFilter
                                        .setBeaconUniqueId("Boom")
                                        .setDeviceName("device_name")
                                        .setAddress("00:00:00:00:00:00")
                                        .setFirmware(26)
                                        .setProximityUUID(UUID.randomUUID())
                                        .build());
        beaconManager.addFilter(new Filters.CustomFilter() {                      //create your customized filter
            @Override
            public boolean filter(AdvertisingPackage advertisingPackage) {
                return advertisingPackage.getAccuracy() < 5;                     //accept beacons from distance 5m at most
            }
        });
*/

        beaconManager.setBeaconActivityCheckConfiguration(BeaconActivityCheckConfiguration.DEFAULT);
        beaconManager.setForceScanConfiguration(ForceScanConfiguration.DEFAULT);

/*
        BeaconManager.RangingListener NULL_RANGING_LISTENER = new BeaconManager.RangingListener() {
            public void onBeaconsDiscovered(Region region, List<BeaconDevice> beaconDevices) {
                Logger.d(new String[]{BeaconManager.TAG, ", NULL_RANGING_LISTENER - onBeaconsDiscovered(). Region: ", region.toString(), ", beacon devices count: " + beaconDevices.size()});
            }
        };

    void onBeaconsDiscovered(Region var1, List<BeaconDevice> var2);
*/

        beaconManager.registerRangingListener(new BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(final Region region,
                                            final List<BeaconDevice> beacons) {

/*
                v.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.replaceWith(beacons);
                    }
                });
*/

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.replaceWith(beacons);
                    }
                });
            }
        });

        deviceList = (ListView) getActivity().findViewById(R.id.device_list);
        deviceList.setAdapter(adapter);

        deviceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                onListItemClick(position);

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();

        if (!beaconManager.isBluetoothEnabled()) {
            final Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, REQUEST_CODE_ENABLE_BLUETOOTH);
        } else if (beaconManager.isConnected()) {
            startRanging();
        } else {
            connect();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (beaconManager.isConnected()) {
            beaconManager.stopRanging();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        beaconManager.disconnect();
        beaconManager = null;
        ButterKnife.reset(this);
    }

    void onListItemClick(final int position) {
        final BeaconDevice beacon = (BeaconDevice) adapter.getItem(position);
        if (beacon != null) {
            PasswordDialogFragment.newInstance(getString(R.string.format_connect, beacon.getAddress()),
                    getString(R.string.password),
                    getString(R.string.connect),
                    new BiConsumer<DialogInterface, String>() {
                        @Override
                        public void accept(DialogInterface dialogInterface, String password) {

                            beacon.setPassword(password.getBytes());

                            //kskim to=do Call Linstener to Call Framgment
//                            final Intent intent = new Intent(getActivity(), BeaconManagementActivity.class);
//                            intent.putExtra(BeaconManagementActivity.EXTRA_BEACON_DEVICE, beacon);
//
//                            startActivityForResult(intent, REQUEST_CODE_CONNECT_TO_DEVICE);
                        }
                    }).show(getActivity().getSupportFragmentManager(), "dialog");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_ENABLE_BLUETOOTH) {
            if (resultCode != Activity.RESULT_OK) {
                final String bluetoothNotEnabledInfo = getString(R.string.bluetooth_not_enabled);
                Toast.makeText(getActivity(), bluetoothNotEnabledInfo, Toast.LENGTH_LONG).show();
            }
            return;
        } else if (requestCode == REQUEST_CODE_CONNECT_TO_DEVICE) {
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getActivity(), "Sorry!", Toast.LENGTH_LONG).show();
//                Toast.makeText(getActivity(),
//                        String.format("Beacon authentication failure: %s", data.getExtras().getString(BeaconManagementActivity.EXTRA_FAILURE_MESSAGE, "")),
//                        Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void startRanging() {
        try {
            beaconManager.startRanging();
        } catch (RemoteException e) {
            Utils.showToast(getActivity(), e.getMessage());
        }
    }

    private void connect() {
        try {
            beaconManager.connect(new OnServiceBoundListener() {
                @Override
                public void onServiceBound() throws RemoteException {
                    beaconManager.startRanging();
                }
            });
        } catch (RemoteException e) {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
