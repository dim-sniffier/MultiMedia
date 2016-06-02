package pw.impress.multimedia.component;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 默认图片组装器
 */
public class ImageAssembler implements Assembler {
    static final String IMAGE_SOURCE = "<p><img src='{{IMAGE}}' {{EXTRA}} /> {{ALT}}</p>";
    public List<ImageResource> images;

    public ImageAssembler(ImageResource image) {
        if (image != null) {
            this.images = new ArrayList<ImageResource>();
            images.add(image);
        }
    }

    public ImageAssembler(List<ImageResource> images) {
        this.images = images;
        if (images == null) this.images = new ArrayList<ImageResource>();
    }

    @Override
    public String assemble() {
        String replacement = "";
        for (ImageResource i : images) {
            String extra = "";
            if (!TextUtils.isEmpty(i.bigImageSrc))
                extra += " data-big-image-src='" + i.bigImageSrc + "'";
            if (!TextUtils.isEmpty(i.src))
                extra += " data-src='" + i.src + "'";
            if (!TextUtils.isEmpty(i.placeholder))
                extra += " data-placeholder='" + i.placeholder + "'";
            if (!TextUtils.isEmpty(i.errorholder))
                extra += " data-errorholder='" + i.errorholder + "'";
            if (!TextUtils.isEmpty(i.width))
                extra += " width='" + i.width + "'";
            if (!TextUtils.isEmpty(i.height))
                extra += " height='" + i.height + "'";

            String alt = "";
            if (!TextUtils.isEmpty(i.alt))
                alt += "<br><small>" + i.alt + "</small>";

            replacement += IMAGE_SOURCE.replace("{{EXTRA}}", extra).replace("{{IMAGE}}", i.src).replace("{{ALT}}", alt);
        }

        return replacement;
    }

    public static class ImageResource {
        // 图片资源地址
        public String src;
        // 大图地址(点击查看大图)
        public String bigImageSrc;
        // 预加载图片
        public String placeholder;
        // 加载失败的图片
        public String errorholder;
        // 图片描述
        public String alt;
        // 图片宽度(100%, 200px)
        public String width;
        // 图片高度(100%, 200px)
        public String height;

        public static ImageResource create() {
            ImageResource i = new ImageResource();
            return i;
        }

        public ImageResource src(String src) {
            this.src = src;
            return this;
        }

        public ImageResource alt(String alt) {
            this.alt = alt;
            return this;
        }

        public ImageResource height(String height) {
            this.height = height;
            return this;
        }

        public ImageResource width(String width) {
            this.width = width;
            return this;
        }

        public ImageResource placeholder(String placeholder) {
            this.placeholder = placeholder;
            return this;
        }

        public ImageResource errorholder(String errorholder) {
            this.errorholder = errorholder;
            return this;
        }

        public ImageResource bigImageSrc(String bigImageSrc) {
            this.bigImageSrc = bigImageSrc;
            return this;
        }

        public ImageAssembler build() {
            return new ImageAssembler(this);
        }
    }
}
