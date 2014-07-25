package net.learn2develop.webbrowser;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        WebView webView = (WebView) findViewById(R.id.WebView01);
        webView.setWebViewClient(new Callback());
        WebSettings webSettings = webView.getSettings();
        webSettings.setBuiltInZoomControls(true);


        //---Part 1---
        //webView.loadUrl("http://www.android.com/images/whatsnew/jb-new-logo.png");       
                

        //---Part 2---
        final String mimeType = "text/html";
        final String encoding = "UTF-8";
        String html = 
        "<H1>A simple HTML page</H1><body>" +
        "<p>The quick brown fox jumps over the lazy dog</p></body>";
        //webView.loadDataWithBaseURL("", html, mimeType, encoding, "");
        
        //---Part 3---
        webView.loadUrl("file:///android_asset/index.html");
    }

    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, 
        String url) {
            return (false);
        }
    }    
}
