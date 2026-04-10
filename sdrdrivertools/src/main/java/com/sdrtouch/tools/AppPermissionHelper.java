package com.sdrtouch.tools;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPermissionHelper {
    private static final String PREFS_NAME = "app_permission_prefs";
    private static final String KEY_PERMISSION_PREFIX = "permission_";

    public enum PermissionState {
        ALLOW_ONCE,
        ALLOW_EVERY_TIME,
        DENY
    }

    private AppPermissionHelper() {}

    public static PermissionState getPermissionState(Context ctx, String packageName) {
        SharedPreferences prefs = ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        int ordinal = prefs.getInt(KEY_PERMISSION_PREFIX + packageName, -1);
        if (ordinal == -1) {
            return null;
        }
        return PermissionState.values()[ordinal];
    }

    public static void setPermissionState(Context ctx, String packageName, PermissionState state) {
        SharedPreferences prefs = ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putInt(KEY_PERMISSION_PREFIX + packageName, state.ordinal()).apply();
    }

    public static void clearPermission(Context ctx, String packageName) {
        SharedPreferences prefs = ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().remove(KEY_PERMISSION_PREFIX + packageName).apply();
    }
}