package com.example.outlooksample;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.outlooksample.api.ApiActivity;
import com.example.outlooksample.sdk.SdkActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.apiButton).setOnClickListener(view -> startActivity(new Intent(this, ApiActivity.class)));
        findViewById(R.id.sdkButton).setOnClickListener(view -> startActivity(new Intent(this, SdkActivity.class)));
    }
}
