package com.test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * 카카오 i 오픈빌더 응답 JSON 빌더
 *
 * @ author knight7024@naver.com
 * @ version 1.1.0
 */
public class SkillResponse {
    private final String version = "2.0"; // 스킬 응답의 version, <major-version>.<minor-version>의 모습
    private final JsonObject skillPayload = new JsonObject(); // 전체 JSON
    private final JsonObject template = new JsonObject(); // JSON - template
    private final JsonArray outputs = new JsonArray(); // JSON - template - outputs
    private final JsonArray quickReplies = new JsonArray(); // JSON - template - outputs

    public SkillResponse() {
        skillPayload.addProperty("version", version);
        skillPayload.add("template", template);
        template.add("outputs", outputs);
        template.add("quickReplies", quickReplies);
    }

    public JsonElement getSkillPayload() {
        if (outputs.size() == 0)
            throw new IllegalStateException("outputs는 필수 항목입니다.");
        return skillPayload;
    }

    public void addSimpleText(String text) {
        if (outputs.size() >= 3)
            throw new IllegalStateException("outputs의 제한은 1개 이상 3개 이하입니다.");
        JsonObject field = new JsonObject();
        JsonObject type = new JsonObject();
        field.addProperty("text", text);
        type.add(EnumResponseType.TemplateType.SIMPLETEXT.getTypeText(), field);

        outputs.add(type);
    }

    public void addSimpleImage(String imageUrl, String altText) {
        if (outputs.size() >= 3)
            throw new IllegalStateException("outputs의 제한은 1개 이상 3개 이하입니다.");
        JsonObject field = new JsonObject();
        JsonObject type = new JsonObject();
        field.addProperty("imageUrl", imageUrl);
        field.addProperty("altText", altText);
        type.add(EnumResponseType.TemplateType.SIMPLEIMAGE.getTypeText(), field);

        outputs.add(type);
    }

    public void addBasicCard(JsonElement cardProperties) {
        if (outputs.size() >= 3)
            throw new IllegalStateException("outputs의 제한은 1개 이상 3개 이하입니다.");
        outputs.add(cardProperties);
    }

    public void addCommerceCard(JsonElement cardProperties) {
        if (outputs.size() >= 3)
            throw new IllegalStateException("outputs의 제한은 1개 이상 3개 이하입니다.");
        outputs.add(cardProperties);
    }

    public void addQuickReplies(String label, String action, String messageText) {
        if (quickReplies.size() >= 10)
            throw new IllegalStateException("quickReplies의 제한은 10개 이하입니다.");
        if (!action.equals(EnumResponseType.QuickRepliesActionType.MESSAGE.getTypeText()))
            throw new IllegalStateException("quickReplies의 action이 올바르게 설정되지 않았습니다.");
        if (label.length() > 8)
            throw new IllegalStateException("quickReplies에서 label은 최대 8자 제한입니다.");

        JsonObject field = new JsonObject();
        field.addProperty("action", action);
        field.addProperty("label", label);
        field.addProperty("messageText", messageText);

        quickReplies.add(field);
    }

    public void addQuickReplies(String label, String action, String messageText, String blockId) {
        if (quickReplies.size() >= 10)
            throw new IllegalStateException("quickReplies의 제한은 10개 이하입니다.");
        if (!action.equals(EnumResponseType.QuickRepliesActionType.BLOCK.getTypeText()))
            throw new IllegalStateException("quickReplies의 action이 올바르게 설정되지 않았습니다.");
        if (label.length() > 8)
            throw new IllegalStateException("quickReplies에서 label은 최대 8자 제한입니다.");

        JsonObject field = new JsonObject();
        field.addProperty("action", action);
        field.addProperty("label", label);
        field.addProperty("messageText", messageText);
        field.addProperty("blockId", blockId);

        quickReplies.add(field);
    }
}

