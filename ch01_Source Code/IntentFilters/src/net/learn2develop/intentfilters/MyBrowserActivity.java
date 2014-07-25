package net.learn2develop.intentfilters;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyBrowserActivity extends Activity { 
    @Override
    public void onCreate(Bundle savedInstanceState) 
    { 
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.browser);

        //Uri url = getIntent().getData();
        Uri url = Uri.parse(getIntent().getStringExtra("URL"));
        
        WebView webView = (WebView) findViewById(R.id.webView); 
        webView.setWebViewClient(new Callback()); 

        if (url!=null) {
            webView.loadUrl(url.toString());
        } else {
            webView.loadUrl("http://www.google.com");
        }
    }

    private class Callback extends WebViewClient { 
        @Override
        public boolean shouldOverrideUrlLoading( 
                WebView view, String url) {
            return(false);
        }
    } 
}

