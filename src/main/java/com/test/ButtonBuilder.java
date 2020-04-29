package com.test;

import com.google.gson.JsonObject;

/**
 * ButtonBuilder
 *
 * @ author knight7024@naver.com
 * @ version 1.1.0
 */
public class ButtonBuilder {
    private String label; // 최대 8자
    private String action;
    private String webLinkUrl; // -> webLink
    private String messageText; // -> message or block
    private String phoneNumber; // -> phone
    private String blockId; // -> block
//        private Map<String, Object> extra = new HashMap<>();

    public ButtonBuilder label(String label) {
        if (label.length() > 8)
            throw new IllegalStateException("Button에서 label은 최대 8자 제한입니다.");
        this.label = label;
        return this;
    }

    public ButtonBuilder action(String action) {
        this.action = action;
        return this;
    }

    public ButtonBuilder webLinkUrl(String webLinkUrl) {
        this.webLinkUrl = webLinkUrl;
        return this;
    }

    public ButtonBuilder messageText(String messageText) {
        this.messageText = messageText;
        return this;
    }

    public ButtonBuilder phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public ButtonBuilder blockId(String blockId) {
        this.blockId = blockId;
        return this;
    }

    public JsonObject build() {
        if (label == null || action == null)
            throw new IllegalStateException("Button에서 label과 action은 필수 설정 항목입니다.");
        JsonObject button = new JsonObject();
        button.addProperty("action", action);
        button.addProperty("label", label);
        if (action.equals(EnumResponseType.ButtonActionType.WEBLINK.getTypeText())) {
            if (webLinkUrl == null)
                throw new IllegalStateException("Button에서 action이 webLink일 경우, webLinkUrl은 필수 설정 항목입니다.");
            button.addProperty("webLinkUrl", webLinkUrl);
        } else if (action.equals(EnumResponseType.ButtonActionType.MESSAGE.getTypeText())) {
            if (messageText == null)
                throw new IllegalStateException("Button에서 action이 message일 경우, messageText는 필수 설정 항목입니다.");
            button.addProperty("messageText", messageText);
        } else if (action.equals(EnumResponseType.ButtonActionType.PHONE.getTypeText())) {
            if (phoneNumber == null)
                throw new IllegalStateException("Button에서 action이 phone일 경우, phoneNumber은 필수 설정 항목입니다.");
            button.addProperty("phoneNumber", phoneNumber);
        } else if (action.equals(EnumResponseType.ButtonActionType.BLOCK.getTypeText())) {
            if (blockId == null)
                throw new IllegalStateException("Button에서 action이 block일 경우, blockId은 필수 설정 항목입니다.");
            button.addProperty("blockId", blockId);
        }

        return button;
    }
}