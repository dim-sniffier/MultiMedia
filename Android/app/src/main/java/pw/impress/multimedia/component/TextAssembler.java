package pw.impress.multimedia.component;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 默认文本段落组装器
 */
public class TextAssembler implements Assembler {
    static final String IMAGE_SOURCE = "<p {{STYLE}}> {{TEXT}}</p>";
    public List<TextResource> texts;

    public TextAssembler(TextResource text) {
        if (text != null) {
            texts = new ArrayList<TextResource>();
            texts.add(text);
        }
    }

    public TextAssembler(List<TextResource> texts) {
        this.texts = texts == null ? new ArrayList<TextResource>() : texts;
    }

    @Override
    public String assemble() {
        String replacement = "";
        for (TextResource i : texts) {
            String style = "";
            if (!TextUtils.isEmpty(i.style))
                style += " " + i.style;
            if (i.indent)
                style += " text-indent:2em;";

            String text = "";
            if (!TextUtils.isEmpty(i.text))
                text += i.text.replaceAll("\n", "<br>");
            if (!TextUtils.isEmpty(i.html))
                text += i.html;
            style = " style='" + style + "'";

            replacement += IMAGE_SOURCE.replace("{{STYLE}}", style).replace("{{TEXT}}", text);
        }

        return replacement;
    }

    public static class TextResource {
        // 文本内容
        public String text;
        // HTML内容
        public String html;
        // 自定义样式
        public String style;
        // 段首缩进两个字符
        public boolean indent;

        public static TextResource create() {
            TextResource i = new TextResource();
            return i;
        }

        public TextResource html(String html) {
            this.html = html;
            return this;
        }

        public TextResource text(String text) {
            this.text = text;
            return this;
        }

        public TextResource style(String style) {
            this.style = style;
            return this;
        }

        public TextResource indent(boolean indent) {
            this.indent = indent;
            return this;
        }

        public TextAssembler build() {
            return new TextAssembler(this);
        }
    }
}
