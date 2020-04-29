package com.test;

import com.google.gson.JsonObject;

/**
 * ThumbnailBuilder
 * @ author knight7024@naver.com
 * @ version 1.1.0
 */
public class ThumbnailBuilder {
    private String imageUrl;
    private JsonObject link;
    private Boolean fixedRatio = false; // 기본값: false
    private Integer width;
    private Integer height;

    public ThumbnailBuilder imageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public ThumbnailBuilder link(JsonObject link) {
        this.link = link;
        return this;
    }

    public ThumbnailBuilder fixedRatio(Boolean fixedRatio) {
        this.fixedRatio = fixedRatio;
        return this;
    }

    public ThumbnailBuilder width(Integer width) {
        this.width = width;
        return this;
    }

    public ThumbnailBuilder height(Integer height) {
        this.height = height;
        return this;
    }

    public JsonObject build() {
        if (imageUrl == null)
            throw new IllegalStateException("Thumbnail에서 imageUrl은 필수 설정 항목입니다.");
        JsonObject thumbnail = new JsonObject();
        thumbnail.addProperty("imageUrl", imageUrl);
        if (link != null)
            thumbnail.add("link", link);
        if (fixedRatio) {
            if (width == null || height == null)
                throw new IllegalStateException("Thumbnail에서 fixedRatio가 true일 경우, width와 height는 필수 설정 항목입니다.");
            thumbnail.addProperty("fixedRatio", true);
            thumbnail.addProperty("width", width);
            thumbnail.addProperty("height", height);
        }
        return thumbnail;
    }
}