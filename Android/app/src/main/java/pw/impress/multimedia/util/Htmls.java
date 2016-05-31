package pw.impress.multimedia.util;

import android.content.Context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 读取本地HTML模板
 */
public class Htmls {
    static final Map<String, String> FILE_MAPS = new ConcurrentHashMap<String, String>();

    /**
     * 获取模板数据
     *
     * @param context  上下文
     * @param filename assets文件夹下的文件名称
     * @return
     */
    public static String template(Context context, String filename) {
        if (FILE_MAPS.containsKey(filename))
            return FILE_MAPS.get(filename);

        return "";
    }

    private static void read(Context context, String filename) {
//        context.getAssets().open(filename);
    }
}
