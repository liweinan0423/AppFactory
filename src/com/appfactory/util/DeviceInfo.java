package com.appfactory.util;

import java.util.UUID;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.provider.Settings.Secure;
import android.util.Log;

public class DeviceInfo {

	private static final String LOG_TAG = "DeviceInfo";
	private static String deviceId; 
	private static String deviceName;
	private static String platformDescription;
	private static int screenSize;

	public static void initialize(final Context context) {

		String androidDeviceId = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);

		if (androidDeviceId == null) {
			androidDeviceId = "";
		}
		deviceId = UUID.nameUUIDFromBytes(androidDeviceId.getBytes()).toString();

		deviceName = Build.DEVICE;

		platformDescription = Build.MODEL + " - " + Build.VERSION.RELEASE;

		screenSize = context.getResources().getConfiguration().screenLayout
				& Configuration.SCREENLAYOUT_SIZE_MASK;

		Log.i(LOG_TAG, "DeviceID=" + deviceId + "    DeviceName=" + deviceName + "    Platform="
				+ platformDescription);
	}

	public static String getDeviceId() {
		return deviceId;
	}

	public static String getDeviceName() {
		return deviceName;
	}

	public static String getPlatformDescription() {
		return platformDescription;
	}

	public static int getScreenSize() {
		return screenSize;
	}

}
