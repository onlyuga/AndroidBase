package com.suri.example.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.suri.example.app.SuriApplication;

/**
 * Date: 18.01.2016
 * Time: 15:01
 *
 * @author Yuri Shmakov
 */
public class PrefUtils {
	private static final String PREF_NAME = "sur";

	public static SharedPreferences getPrefs() {
		return SuriApplication.getInstance().getApplicationComponent().getContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
	}

	public static SharedPreferences.Editor getEditor() {
		return getPrefs().edit();
	}
}
