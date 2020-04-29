package com.test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * BasicCardBuilder
 * @ author knight7024@naver.com
 * @ version 1.1.0
 */
public class BasicCardBuilder {
    private String title; // 최대 2줄
    private String description; // 최대 230자
    private JsonObject thumbnail;
    private final JsonArray buttons = new JsonArray(); // 최대 3개

    public BasicCardBuilder title(String title) {
        if (title.split("\n").length >= 2)
            throw new IllegalStateException("title은 최대 2줄 제한입니다.");
        this.title = title;
        return this;
    }

    public BasicCardBuilder description(String description) {
        if (description.length() > 230)
            throw new IllegalStateException("description의 길이는 최대 230자 제한입니다.");
        this.description = description;
        return this;
    }

    public BasicCardBuilder thumbnail(JsonObject thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public BasicCardBuilder buttons(JsonObject button) {
        if (buttons.size() >= 3)
            throw new IllegalStateException("Button의 개수는 최대 3개 제한입니다.");
        buttons.add(button);
        return this;
    }

    public JsonElement build() {
        if (thumbnail == null)
            throw new IllegalStateException("thumbnail은 필수 항목입니다.");
        JsonObject field = new JsonObject();
        JsonObject type = new JsonObject();
        if (title != null)
            field.addProperty("title", title);
        if (description != null)
            field.addProperty("description", description);
        field.add("thumbnail", thumbnail);
        if (buttons.size() != 0)
            field.add("buttons", buttons);
        type.add(EnumResponseType.TemplateType.BASICCARD.getTypeText(), field);
        return type;
    }
}