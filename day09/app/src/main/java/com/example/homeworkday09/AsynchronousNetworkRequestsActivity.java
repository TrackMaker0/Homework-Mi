package com.example.homeworkday09;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AsynchronousNetworkRequestsActivity extends AppCompatActivity implements Interceptor {

    public static final String TAG = "MyNetworkRequest";
    private OkHttpClient okHttpClient;
    private Request request;
    private TextView responseBodyView;
    public static final String BASE_URL_RETROFIT = "https://api.github.com/";
    public static final String BASE_URL_OKHTTP = "https://api.github.com/users/octocat/repos";
    private String USER = "octocat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynchronous_network_requests);

        Button okhttpBtn = findViewById(R.id.btn_okhttp);
        Button retrofitBtn = findViewById(R.id.btn_retrofit);
        responseBodyView = findViewById(R.id.tv_response_body);
        responseBodyView.setMovementMethod(new ScrollingMovementMethod());

        okhttpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeRequest1();
            }
        });

        retrofitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeRetrofitRequest();
            }
        });
    }

    private void makeRetrofitRequest() {
        // 创建 Retrofit 实例
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_RETROFIT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 创建 ApiService 实例
        ApiService apiService = retrofit.create(ApiService.class);

        retrofit2.Call<List<MyRepo>> reposCall = apiService.listRepos(USER);

        reposCall.enqueue(new retrofit2.Callback<List<MyRepo>>() {
            @Override
            public void onResponse(retrofit2.Call<List<MyRepo>> call, retrofit2.Response<List<MyRepo>> response) {
                if (response.isSuccessful()) {
                    List<MyRepo> repos = response.body();
                    StringBuilder stringBuilder = new StringBuilder();

                    if (repos != null) {
                        for (MyRepo repo : repos) {
                            // 使用 repo.toString() 直接获取每个 repo 的字符串表示
                            Log.d(TAG, "Retrofit onResponse Data: " + repo.toString());
                            stringBuilder.append(repo.toString()).append("\n");
                        }

                        // 更新UI
                        runOnUiThread(() -> responseBodyView.setText(stringBuilder.toString()));
                    } else {
                        Log.e(TAG, "Response body is null");
                    }
                } else {
                    Log.e(TAG, "Retrofit Request failed with code: " + response.code());
                }
            }

            @Override
            public void onFailure(retrofit2.Call<List<MyRepo>> call, Throwable t) {
                Log.e(TAG, "Retrofit onFailure : Request failed", t);
            }
        });
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Log.i(TAG, "intercept: MyIntercept");

        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();
        // 添加请求头信息
        requestBuilder.addHeader("UserId", "UserId");
        requestBuilder.addHeader("token", "token");
        requestBuilder.addHeader("versionCode", "120");
        requestBuilder.addHeader("versionName", "1.2.0");

        // get接口添加公共参数
        if (TextUtils.equals(request.method(), "GET")) {
            HttpUrl.Builder httpUrlBuilder = request.url().newBuilder();
            httpUrlBuilder.addQueryParameter("userId", "userId");
            httpUrlBuilder.addQueryParameter("token", "token");

            requestBuilder.url(httpUrlBuilder.build());
        }
        return chain.proceed(requestBuilder.build());
    }

    // 发起网络请求的方法
    public void makeRequest1() {

        // 创建OkHttpClient请求
        request = new Request.Builder()
                .get()
                .url(BASE_URL_OKHTTP)
                .build();

        // 创建OkHttpClient，并添加拦截器
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(this)
                .build();

        setOkHttpClientCall();
    }

    // 使用实现Interceptor的匿名类
    public void makeRequest2() {

        // 创建一个应用拦截器
        Interceptor appInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();

                // 添加自定义头
                Request modifiedRequest = originalRequest.newBuilder()
                        .header("Authorization", "Bearer YOUR_TOKEN_HERE")
                        .header("User-Agent", "Your-App-Name")
                        .build();

                return chain.proceed(modifiedRequest);
            }
        };

        // 将拦截器添加到OkHttpClient
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(appInterceptor)  // 添加应用拦截器
                .build();

        // 创建OkHttpClient请求
        request = new Request.Builder()
                .get()
                .url("https://www.baidu.com")
                .build();

        setOkHttpClientCall();
    }

    public void setOkHttpClientCall() {
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String responseData = response.body().string();

                    // JSON转为Java对象
                    Type listType = new TypeToken<List<MyRepo>>() {}.getType();
                    List<MyRepo> repos = new Gson().fromJson(responseData, listType);

                    // 转为StringBuilder
                    StringBuilder stringBuilder = new StringBuilder();
                    for (MyRepo repo : repos) {
                        Log.d(TAG, "OkHttp onResponse Data: " + repo.toString());
                        stringBuilder.append(repo).append("\n");
                    }

                    // 更新UI
                    runOnUiThread(() -> responseBodyView.setText(stringBuilder.toString()));
                } else {
                    Log.e(TAG, "OkHttp Request failed with code: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e(TAG, "OkHttp onFailure : Request failed", e);
                e.printStackTrace();
            }
        });
    }
}