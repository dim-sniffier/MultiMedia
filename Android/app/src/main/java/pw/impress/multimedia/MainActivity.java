package pw.impress.multimedia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import pw.impress.multimedia.util.Htmls;

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

            String html = Htmls.defaults(getApplicationContext()).replace("{{web_content}}", content);
            container.loadDataWithBaseURL(Htmls.ASSETS_FOLDER, html, Htmls.MIME_TYPE, Htmls.HTML_ENCODING, "");
        }
    }
}
