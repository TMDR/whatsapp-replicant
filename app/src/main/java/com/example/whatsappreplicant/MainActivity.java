package com.example.whatsappreplicant;

import androidx.annotation.NonNull;
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
import android.os.PersistableBundle;
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
    private static final String jsFunc ="javaScript:{var list = [\"فرقة مار جرجس\",\"مدلان\",\"EB4:A Elissar Abboud\"]; \n" +
            "var listsElements = [];\n" +
            "var parentOfParent = document.evaluate('/html/body/div/div[1]/div[1]/div[3]/div/div[2]', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;\n" +
            "var parent = parentOfParent.children[0].children[0].children[0];\n" +
            "var len = parent.children.length;\n" +
            "for (var i = 0; i < len; i++) {\n" +
            "  var tableChild = parent.children[i];\n" +
            "  if(tableChild != null && tableChild != undefined && !list.includes(tableChild.children[0].children[0].children[1].children[0].children[0].children[0].title)){\n" +
            "        tableChild.style.width = \"0px\";\n" +
            "        tableChild.style.height = \"0px\";\n" +
            "        tableChild.transform = \"translateY(-\"+(72*i)+\"px)\";\n" +
            "try{\n" +
            "        tableChild.children[0].children[0].children[0].children[0].children[0].children[1].style.width = \"0px\";\n" +
            "        tableChild.children[0].children[0].children[0].children[0].children[0].children[1].style.height = \"0px\";\n" +
            "        tableChild.children[0].children[0].children[0].children[0].children[0].children[1].transform = \"translateY(-\"+(72*i)+\"px)\";\n" +
            "        tableChild.children[0].children[0].children[0].children[0].children[0].children[0].remove();\n" +
            "}catch(Exc){}\n" +
            "        tableChild.children[0].children[0].children[0].style.width = \"0px\";\n" +
            "        tableChild.children[0].children[0].children[0].style.height = \"0px\";\n" +
            "        tableChild.children[0].children[0].children[0].transform = \"translateY(-\"+(72*i)+\"px)\";\n" +
            "\n" +
            "        tableChild.children[0].children[0].children[1].style.width = \"0px\";\n" +
            "        tableChild.children[0].children[0].children[1].style.height = \"0px\";\n" +
            "        tableChild.children[0].children[0].children[1].transform = \"translateY(-\"+(72*i)+\"px)\";\n" +
            "\n" +
            "        tableChild.children[0].children[0].children[1].children[1].children[1].remove();\n" +
            "\n" +
            "        tableChild.children[0].children[0].children[0].children[0].style.width = \"0px\";\n" +
            "        tableChild.children[0].children[0].children[0].children[0].style.height = \"0px\";\n" +
            "        tableChild.children[0].children[0].children[0].children[0].transform = \"translateY(-\"+(72*i)+\"px)\";\n" +
            "tableChild.replaceWith(tableChild.cloneNode(true));\n" +
            "  }\n" +
            "  else if(tableChild != null && tableChild != undefined){\n" +
            "    listsElements.push(tableChild);\n" +
            "  }\n" +
            "}\n" +
            "console.log(listsElements);\n" +
            "len = listsElements.length;\n" +
            "listsElements[0].children[0].children[0].children[1].click();\n" +
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            whatsappWeb = findViewById(R.id.wpwebView);

            configureWebView();

            whatsappWeb.loadUrl("https://web.whatsapp.com/");

        }

        Window window = getWindow();

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        window.setStatusBarColor(ContextCompat.getColor(this, R.color.my_statusbar_color));
    }

    private void configureWebView(){
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

        whatsappWeb.setWebChromeClient(new WebChromeClient() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onPermissionRequest(final PermissionRequest request) {
                request.grant(request.getResources());
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        whatsappWeb.saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        whatsappWeb = findViewById(R.id.wpwebView);
        configureWebView();
        whatsappWeb.restoreState(savedInstanceState);
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