package cn.itcast.smartcity2.Tools;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SendRequest {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final OkHttpClient client = new OkHttpClient();
    public static String IP = "http://124.93.196.45:10001";
    private static final Gson gson = new Gson();;
    //get请求获取数据
    public static void get_request(String url, final int pos, final Handler handler){
        Request request = new Request.Builder()
                .url(IP + url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                System.out.println("================================================");
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String data = response.body().string();
                System.out.println(data);
                Message message = new Message();
                message.obj = data;
                message.what = pos;
                handler.sendMessage(message);
            }
        });
    }

    //get带token获取数据请求获取数据
    public static void get_request_token(String url, final int pos, final Handler handler, String token){
        Request request = new Request.Builder()
                .url(IP + url)
                .addHeader("Authorization",token)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                System.out.println("================================================");
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String data = response.body().string();
                Message message = new Message();
                message.obj = data;
                message.what = pos;
                handler.sendMessage(message);
            }
        });
    }

    //post请求获取数据
    public static void post_request(String url, final int pos, final Handler handler, Map<String, Object> map){
//        JSONObject jsonObject = new JSONObject(map);
//        String str = jsonObject.toJSONString();

        String str = gson.toJson(map);
        RequestBody body = RequestBody.create(JSON,str);
        Request request = new Request.Builder()
                .url(IP + url)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                System.out.println("========================================================");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String data = response.body().string();
                Message message = new Message();
                message.obj = data;
                message.what = pos;
                handler.sendMessage(message);
            }
        });
    }

    //post_token请求获取数据
    public static void post_request_token(String url, final int pos, final Handler handler, Map<String, Object> map, String token){
//        JSONObject jsonObject = new JSONObject(map);
//        String str = jsonObject.toJSONString();
        String str = gson.toJson(map);
        RequestBody body = RequestBody.create(JSON,str);
        Request request = new Request.Builder()
                .url(IP + url)
                .addHeader("Authorization",token)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                System.out.println("========================================================");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String data = response.body().string();
                Message message = new Message();
                message.obj = data;
                message.what = pos;
                handler.sendMessage(message);
            }
        });
    }

    //put_token请求获取数据
    public static void put_request_token(String url, final int pos, final Handler handler, Map<String, Object> map, String token){
//        JSONObject jsonObject = new JSONObject(map);
//        String str = jsonObject.toJSONString();
        String str = gson.toJson(map);
        RequestBody body = RequestBody.create(JSON,str);
        Request request = new Request.Builder()
                .url(IP + url)
                .addHeader("Authorization",token)
                .put(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                System.out.println("========================================================");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String data = response.body().string();
                Message message = new Message();
                message.obj = data;
                message.what = pos;
                handler.sendMessage(message);
            }
        });
    }
}
