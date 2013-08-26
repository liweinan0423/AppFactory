package com.appfactory.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.appfactory.R;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

public class App extends Application {

	private static Application mApp;
	private static String serverIP;
	private static String imageCachePath;

	// private static final String tag = "Application";

	@Override
	public void onCreate() {
		super.onCreate();
		mApp = this;
		initialize();
		createFileFolders();
	}

	private void initialize() {
		DeviceInfo.initialize(this);
		getServerConfig();
	}

	private void getServerConfig() {
		Properties pro = new Properties();
		InputStream is;
		try {
			is = mApp.getResources().openRawResource(R.raw.config);
			pro.load(is);
			serverIP = pro.getProperty("ServerIP", "");
			is.close();
		} catch (IOException e) {
			Toast.makeText(this, "config.property File Not Found!",
					Toast.LENGTH_SHORT).show();
		}

	}

	private void createFileFolders() {

		boolean sdCardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);

		if (!sdCardExist) {
			Toast.makeText(this, "SDcard Not Found!", Toast.LENGTH_SHORT)
					.show();
		} else {
			imageCachePath = Environment.getExternalStorageDirectory()
					+ File.separator + getPackageName() + File.separator
					+ "imgcache/";
			File imgf = new File(imageCachePath);
			if (!imgf.exists()) {
				imgf.mkdirs();
			}
		}

	}

	public static String getServerIP() {
		return serverIP;
	}

	public static String getImageCacheFolder() {
		return imageCachePath;
	}

	/**
	 * @return Context get application context. Must not use this context with
	 *         UI components.
	 */
	public static Context getContext() {
		return mApp;
	}
}
