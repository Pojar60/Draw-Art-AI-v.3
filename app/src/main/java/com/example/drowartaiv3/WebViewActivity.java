package com.example.drowartaiv3;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_web_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        WebView webView = findViewById(R.id.webview);
        webView.setFocusable(false);
        webView.setFocusableInTouchMode(false);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Включение JavaScript
        webSettings.setLoadWithOverviewMode(true); // Загружать WebView, охватывая содержимое в ширину экрана
        webSettings.setUseWideViewPort(true); // Поддержка мета-тега "viewport" в HTML
        webSettings.setSupportZoom(true); // Отключение поддержки масштабирования
        webSettings.setBuiltInZoomControls(true); // Включает встроенные элементы управления масштабированием
        webSettings.setDisplayZoomControls(false);
//        webView.setInitialScale(50);



        webView.loadUrl("file:///android_asset/mobile.html");

        FloatingActionButton fabBack = findViewById(R.id.fabBack);
        fabBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();  // Closes the current activity, returning to the previous one
            }
        });
    }
}