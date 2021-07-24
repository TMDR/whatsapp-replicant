package com.example.whatsappreplicant;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class MainActivity extends AppCompatActivity {

    private WebView whatsappWeb;
    private static final String DESKTOP_USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36";
    private static final String jsFunc ="javaScript:{var list = [\"roudy\",\"Mom\",\"Dad\"]; \n" +
            "var listsElements = [];\n" +
            "var parent =document.evaluate('/html/body/div/div[1]/div[1]/div[3]/div/div/div[1]/div/div', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue ;\n" +
            "var len = parent.children.length;\n" +
            "for (var i = 0; i < len; i++) {\n" +
            "  var tableChild = parent.children[i];\n" +
            "  if(tableChild != null && tableChild != undefined && !list.includes(tableChild.children[0].children[0].children[1].children[0].children[0].children[0].title)){\n" +
            "\n" +
            "        tableChild.style.width = \"0px\";\n" +
            "        tableChild.style.height = \"0px\";\n" +
            "        tableChild.transform = \"translateY(0px)\";\n" +
            "        tableChild.children[0].children[0].children[0].children[0].style.width = \"0px\";\n" +
            "        tableChild.children[0].children[0].children[0].children[0].style.height = \"0px\";\n" +
            "        tableChild.children[0].children[0].children[0].children[0].transform = \"translateY(0px)\";\n" +
            "  }\n" +
            "  else if(tableChild != null && tableChild != undefined){\n" +
            "    listsElements.push(tableChild);\n" +
            "  }\n" +
            "}\n" +
            "console.log(listsElements);\n" +
            "len = listsElements.length;\n" +
            "for(var i = 0; i < len; i++)\n" +
            "    listsElements[i].style.transform = \"translateY(\"+(i*72)+\"px)\";\n" +
            " try{\n" +
            " var classlist = document.evaluate('/html/body/div/div[1]/div[1]/div[3]/div/div[2]', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.classList;\n" +
            "classlist.remove('_3Bc7H');\n" +
            "classlist.remove('_20c87');\n" +
            "\n" +
            " document.evaluate('/html/body/div/div[1]/div[1]/div[3]/div/header', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.remove();\n" +
            "\n" +
            "\n" +
            " document.evaluate('/html/body/div/div[1]/div[1]/div[3]/div/div[1]', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.remove();}catch(exception_var){}}";

    private static final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 101;
    private static PermissionRequest myRequest;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        whatsappWeb = findViewById(R.id.wpwebView);

        whatsappWeb.setWebViewClient(new mWebViewClient());

        WebSettings settings = whatsappWeb.getSettings();
        settings.setUserAgentString(DESKTOP_USER_AGENT);
        settings.setDomStorageEnabled(true);
        settings.setAllowContentAccess(true);
        settings.setAllowFileAccess(true);
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setSaveFormData(true);
        settings.setCacheMode(WebSettings.LOAD_NORMAL);
        settings.setDatabaseEnabled(true);
        settings.setDisplayZoomControls(true);

        whatsappWeb.setHorizontalScrollBarEnabled(true);
        whatsappWeb.setVerticalScrollBarEnabled(true);

        whatsappWeb.setPadding(0, 0, 0, 0);
//        whatsappWeb.setInitialScale(getScale());

        whatsappWeb.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onPermissionRequest(final PermissionRequest request) {
                request.grant(request.getResources());
            }
        });

        whatsappWeb.loadUrl("https://web.whatsapp.com/");

        Window window = getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.my_statusbar_color));
    }

    private class mWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.d("FINISHED","FINISHED");
            whatsappWeb.loadUrl(jsFunc);
        }
    }
}