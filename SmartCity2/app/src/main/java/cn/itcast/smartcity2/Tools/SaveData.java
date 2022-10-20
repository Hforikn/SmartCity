package cn.itcast.smartcity2.Tools;

import android.content.Context;
import android.content.SharedPreferences;

public class SaveData {
    public static void save_login(Context context, boolean bool){
        SharedPreferences sp = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isLogin",bool);
        editor.commit();
    }

    public static boolean get_login(Context context){
        SharedPreferences sp = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        boolean isLogin = sp.getBoolean("isLogin",false);
        return isLogin;
    }

    public static void save_token(Context context, String token){
        SharedPreferences sp = context.getSharedPreferences("token", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("Authorization",token);
        editor.commit();
    }

    public static String get_token(Context context){
        SharedPreferences sp = context.getSharedPreferences("token", Context.MODE_PRIVATE);
        String token = sp.getString("Authorization",null);
        return token;
    }
}
