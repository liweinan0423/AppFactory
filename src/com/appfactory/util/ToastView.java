package com.appfactory.util;

import com.appfactory.R;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ToastView {
	private static Handler handler = new Handler(Looper.getMainLooper());
	private static Toast toast = null;
	private static Object synObj = new Object();
	private static View mView = null;

	private static int TOAST_LONGTH = 3000;

	public static Toast createToast(Context context) {
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		mView = inflater.inflate(R.layout.toast_layout,
				(ViewGroup) (((Activity) context)
						.findViewById(R.id.toast_layout_root)));
		toast = new Toast(context);
		toast.setView(mView);
		return toast;
	}

	public static void show(Context context, int x, int y, int text) {
		showMessage(context, text, TOAST_LONGTH, x, y, 0);
	}

	public static void show(Context context, int text) {
		showMessage(context, text, TOAST_LONGTH, 0, 0, 0);
	}

	public static void show(Context context, String text) {
		showMessage(context, text, TOAST_LONGTH, 0, 0, 0);
	}

	public static void show(Context context, int x, int y, int text, float size) {
		showMessage(context, text, TOAST_LONGTH, x, y, size);
	}

	public static void show(Context context, int x, int y, String text) {
		showMessage(context, text, TOAST_LONGTH, x, y, 0);
	}

	public static void show(Context context, int x, int y, String text, int size) {
		showMessage(context, text, TOAST_LONGTH, x, y, size);
	}

	/**
	 * 显示提示信息
	 * 
	 * @param context
	 * @param msg
	 *            提示信息
	 * @param longth
	 *            提示显示时长
	 * @param x
	 *            x坐标
	 * @param y
	 *            y坐标
	 * @param size
	 *            字体大小
	 */
	private static void showMessage(final Context context, final String msg,
			final int longth, final int x, final int y, final float size) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				handler.post(new Runnable() {

					@Override
					public void run() {
						synchronized (synObj) {
							toast = Toast.makeText(context, "", TOAST_LONGTH);
							LinearLayout toastView = (LinearLayout) toast
									.getView();
							toastView.setOrientation(LinearLayout.HORIZONTAL);
							TextView textView = new TextView(context
									.getApplicationContext());
							textView.setText(msg);
							textView.setTextSize(16); // textView.setTextSize(size);
							textView.setGravity(Gravity.CENTER);
							toastView.addView(textView);
							toast.setGravity(Gravity.CENTER, x, y);
							toast.show();
						}
					}
				});
			}
		}).start();
	}

	public static void showMessage(final Context context, final int msg,
			final int longth, final int x, final int y, final float size) {
		String s = context.getString(msg);
		showMessage(context, s, TOAST_LONGTH, x, y, 0);
	}
}
