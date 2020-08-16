package com.example.outlooksample.api;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.outlooksample.R;
import com.example.outlooksample.api.response.accessToken.AccessTokenResponse;
import com.example.outlooksample.api.response.calendars.CalendarsResponse;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class ApiActivity extends AppCompatActivity {

    private static final String TAG = ApiActivity.class.getSimpleName();

    private Handler handler = new Handler();

    private String code = null;
    private String accessToken = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate intent:" + getIntent());
        setContentView(R.layout.activity_api);
        findViewById(R.id.loginButton).setOnClickListener(view -> {
            login();
        });
        findViewById(R.id.getAccessTokenButton).setOnClickListener(view -> {
            getAccessToken();
        });
        findViewById(R.id.callApiButton).setOnClickListener(view -> {
            callApi();
        });
        getCode();
        updateView();
    }

    private void login() {
        String url = new StringBuilder()
                .append(getString(R.string.authorize_url))
                .append("?client_id=").append(getString(R.string.client_id))
                .append("&redirect_uri=").append(getString(R.string.redirect_uri))
                .append("&response_type=code&scope=Calendars.Read")
                .toString();
        String encodedUrl = null;
        try {
            encodedUrl = URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (encodedUrl == null)
            return;
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    private void getCode() {
        Uri uri = getIntent().getData();
        if (uri == null) {
            code = null;
            return;
        }
        String tempCode = uri.getQueryParameter("code");
        code = tempCode == null || tempCode.isEmpty() ? null : tempCode;
    }

    private void getAccessToken() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("grant_type", "authorization_code")
                .addFormDataPart("code", code)
                .addFormDataPart("redirect_uri", getString(R.string.redirect_uri))
                .addFormDataPart("client_id", getString(R.string.client_id))
                .addFormDataPart("client_secret", getString(R.string.client_secret))
                .build();
        Request request = new Request.Builder()
                .url(getString(R.string.access_token_url))
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();
                Gson gson = new Gson();
                AccessTokenResponse accessTokenResponse = gson.fromJson(result, AccessTokenResponse.class);
                accessToken = accessTokenResponse.getAccessToken();
                handler.post(() -> updateView());
            }
        });
    }

    private void callApi() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        Request request = new Request.Builder()
                .url(getString(R.string.calendars_url))
                .addHeader("Authorization", "Bearer " + accessToken)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();
                Log.d(TAG, "onResponse result" + result);
                Gson gson = new Gson();
                CalendarsResponse calendarsResponse = gson.fromJson(result, CalendarsResponse.class);
                handler.post(() -> updateView());
            }
        });
    }

    private void updateView() {
        findViewById(R.id.loginButton).setEnabled(code == null);
        findViewById(R.id.getAccessTokenButton).setEnabled(code != null && accessToken == null);
        findViewById(R.id.callApiButton).setEnabled(accessToken != null);
        ((TextView) findViewById(R.id.codeTextView)).setText(new StringBuilder().append("code:\n").append(code));
        ((TextView) findViewById(R.id.accessTokenTextView)).setText(new StringBuilder().append("accessToken:\n").append(accessToken));
    }
}
