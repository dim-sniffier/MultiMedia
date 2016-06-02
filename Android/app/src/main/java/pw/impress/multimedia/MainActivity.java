package pw.impress.multimedia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import pw.impress.multimedia.component.Assembler;
import pw.impress.multimedia.component.ImageAssembler;
import pw.impress.multimedia.component.TextAssembler;
import pw.impress.multimedia.util.Htmls;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewHolder holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        holder = new ViewHolder();
        holder.bind();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class ViewHolder {
        WebView container;

        ViewHolder() {
            container = (WebView) findViewById(R.id.main_webview);

            // 网页的属性设置
            container.getSettings().setJavaScriptEnabled(true);
        }

        void bind() {
            String content = "";
            List<Assembler> assemblers = new ArrayList<Assembler>();
            assemblers.add(ImageAssembler.ImageResource.create()
                    .src("http://yootheme.com/demo/themes/wordpress/2013/nano3/wp-content/uploads/yootheme/demo/default/home_warp_teaser.png")
                    .alt("你这是在逗我吗?")
                    .build());
            assemblers.add(TextAssembler.TextResource.create()
                    .text("早在去年8月，Chromium项目已经决定开始废弃的NPN协商协议（一个传输层安全协议扩展协议）。即将在5月31日发布的Chrome 51稳定版本中，NPN协商协议将被正式废除，仅支持ALPN协商协议。也就是说，所有目前基于NPN协商协议的HTTP/2协议都将无法使用，并被降级到HTTP/1.1。 这对于客户端来说没有太大影响，但是对于希望使用HTTP/2协议的服务端，将NPN升级到ALPN，并不是一件容易的事情，OpenSSL至少需要升级到1.0.2版本才可以支持ALPN。 下表是常见服务器操作系统版本中自带的OpenSSL版本")
                    .indent(true)
                    .html("<div style='height:80px;font-size:30px;'>aaaa</div>")
                    .build());

            for (Assembler a : assemblers) {
                content += a.assemble();
            }

            Log.d("==", content);

            String html = Htmls.defaults(getApplicationContext()).replace("{{web_content}}", content);
            container.loadDataWithBaseURL(Htmls.ASSETS_FOLDER, html, Htmls.MIME_TYPE, Htmls.HTML_ENCODING, "");
        }
    }
}
