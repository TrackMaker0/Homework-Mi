package com.example.homeworkday09;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class MyInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();

        // 对请求进行修改，例如添加一个Header
        Request modifiedRequest = originalRequest.newBuilder()
                .header("Authorization", "Bearer YOUR_TOKEN_HERE")
                .header("User-Agent", "Your-App-Name")
                .build();

        // 继续请求链
        return chain.proceed(modifiedRequest);
    }
}
