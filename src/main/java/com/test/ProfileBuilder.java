package com.test;

import com.google.gson.JsonObject;

/**
 * ProfileBuilder
 * @ author knight7024@naver.com
 * @ version 1.1.0
 */
public class ProfileBuilder {
    private String nickname;
    private String imageUrl;

    public ProfileBuilder nickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ProfileBuilder imageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public JsonObject build() {
        if (nickname == null)
            throw new IllegalStateException("Profile에서 nickname은 필수 설정 항목입니다.");
        JsonObject profile = new JsonObject();
        profile.addProperty("nickname", nickname);
        if (imageUrl != null)
            profile.addProperty("imageUrl", imageUrl);
        return profile;
    }
}
