package com.example.drowartaiv3;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class WebViewActivity extends AppCompatActivity {
    WebView webView;
    String internalHtmlFileName = "mobile.html";

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

        try {
            copyAssetToInternalStorage("mobile.html", internalHtmlFileName);
        } catch (IOException e) {
            Log.e("WebViewActivity", "Error copying asset to internal storage", e);
        }

        webView = findViewById(R.id.webview);
        webView.setFocusable(false);
        webView.setFocusableInTouchMode(false);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);

        webView.setWebViewClient(new WebViewClient());

        File internalFile = new File(getFilesDir(), internalHtmlFileName);
        if (internalFile.exists()) {
            String filePath = "file://" + internalFile.getAbsolutePath();
            Log.d("WebViewActivity", "Loading file from: " + filePath);
            webView.loadUrl(filePath);
        } else {
            // Файл не найден, логируем ошибку
            Log.e("WebViewActivity", "File not found: " + internalFile.getAbsolutePath());
        }

        FloatingActionButton fabBack = findViewById(R.id.fabBack);
        fabBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveHtmlContent();
                finish();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        webView.saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        webView.restoreState(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void copyAssetToInternalStorage(String assetFileName, String internalFileName) throws IOException {
        File file = new File(getFilesDir(), internalFileName);
        if (!file.exists()) {
            try (InputStream is = getAssets().open(assetFileName);
                 FileOutputStream fos = new FileOutputStream(file)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, length);
                }
                Log.d("WebViewActivity", "File copied to internal storage: " + file.getAbsolutePath());
            }
        } else {
            Log.d("WebViewActivity", "File already exists in internal storage: " + file.getAbsolutePath());
        }
    }

    private void saveHtmlContent() {
        webView.evaluateJavascript("(function() { return document.documentElement.outerHTML; })();", html -> {
            try (FileOutputStream fos = openFileOutput(internalHtmlFileName, MODE_PRIVATE)) {
                fos.write(html.getBytes());
                Log.d("WebViewActivity", "HTML content saved to: " + internalHtmlFileName);
            } catch (IOException e) {
                Log.e("WebViewActivity", "Error saving HTML content", e);
            }
        });
    }
}
