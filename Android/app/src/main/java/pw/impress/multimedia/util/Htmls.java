package pw.impress.multimedia.util;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 读取本地HTML模板
 */
public class Htmls {
    static final Map<String, String> FILE_MAPS = new ConcurrentHashMap<String, String>();
    public static final String HTML_ENCODING = "utf-8";
    public static final String MIME_TYPE = "text/html";
    public static final int FONT_SIZE = 14;
    static final String DEFAULT_HTML_FILENAME = "multi-media.html";

    /**
     * 获取默认模板数据
     *
     * @param context 上下文
     * @return 模板内容
     */
    public synchronized static String defaults(Context context) {
        return template(context, DEFAULT_HTML_FILENAME);
    }

    /**
     * 获取模板数据
     *
     * @param context  上下文
     * @param filename assets文件夹下的文件名称
     * @return 模板内容
     */
    public synchronized static String template(Context context, String filename) {
        if (!FILE_MAPS.containsKey(filename))
            FILE_MAPS.put(filename, read(context, filename));

        return FILE_MAPS.get(filename);
    }

    private static String read(Context context, String filename) {
        InputStream is = null;
        try {
            is = context.getAssets().open(filename);
            int size = is.available();

            byte[] buffer = new byte[size];
            is.read(buffer);

            return new String(buffer);
        } catch (IOException e) {
            Log.w("Htmls", e.toString());
            return null;
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException e) {
                // 忽略
            }
        }
    }
}
