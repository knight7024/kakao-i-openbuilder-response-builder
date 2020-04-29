package com.test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * 카카오 i 오픈빌더 응답 JSON 빌더
 * @ author knight7024@naver.com
 * @ version 1.0.0
 */
public class SkillResponse {
    private final String version = "2.0"; // 스킬 응답의 version, <major-version>.<minor-version>의 모습
    private JsonObject skillPayload = new JsonObject(); // 전체 JSON
    private JsonObject template = new JsonObject(); // JSON - template
    private JsonArray outputs = new JsonArray(); // JSON - template - outputs
    private JsonArray quickReplies = new JsonArray(); // JSON - template - outputs

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

    public void addBasicCard(JsonElement cardProperties) throws IllegalStateException {
        if (outputs.size() >= 3)
            throw new IllegalStateException("outputs의 제한은 1개 이상 3개 이하입니다.");
        outputs.add(cardProperties);
    }

    public static class BasicCardBuilder {
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

        public BasicCardBuilder buttons(JsonObject button) throws IllegalStateException {
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

    public static class CommerceCardBuilder {
        private String description; // 최대 76자
        private Integer price;
        private String currency;
        private Integer discount;
        private Integer discountRate;
        private Integer dicountedPrice;
        private final JsonArray thumbnails = new JsonArray(); // 현재 1개만 가능
        private final JsonArray buttons = new JsonArray(); // 1개 이상, 3개 이하

        public CommerceCardBuilder description(String description) {
            if (description.length() > 76)
                throw new IllegalStateException("description의 길이는 최대 76자 제한입니다.");
            this.description = description;
            return this;
        }

        public CommerceCardBuilder price(Integer price) {
            if (description.length() > 76)
                throw new IllegalStateException("description의 길이는 최대 76자 제한입니다.");
            this.description = description;
            return this;
        }

        public CommerceCardBuilder thumbnail(JsonObject thumbnail) {
            if (buttons.size() >= 3)
                throw new IllegalStateException("Button의 개수는 최대 3개 제한입니다.");
            thumbnails.add(thumbnail);
            return this;
        }

        public CommerceCardBuilder buttons(JsonObject button) throws IllegalStateException {
            if (buttons.size() >= 3)
                throw new IllegalStateException("Button의 개수는 최대 3개 제한입니다.");
            buttons.add(button);
            return this;
        }

        public JsonElement build() {
            if (thumbnails.size() == 0)
                throw new IllegalStateException("thumbnail은 필수 항목입니다.");
            if (buttons.size() == 0)
                throw new IllegalStateException("Button은 최소 1개가 있어야 합니다.");
            JsonObject field = new JsonObject();
            JsonObject type = new JsonObject();
            if (description != null)
                field.addProperty("description", description);
            field.add("thumbnails", thumbnails);
            field.add("buttons", buttons);
            type.add(EnumResponseType.TemplateType.BASICCARD.getTypeText(), field);
            return type;
        }
    }

    public void addQuickReplies(String label, String action, String messageText) throws IllegalStateException {
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

    public void addQuickReplies(String label, String action, String messageText, String blockId) throws Exception {
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

    public static class ThumbnailBuilder {
        private String imageUrl;
        private String link;
        private Boolean fixedRatio = false; // 기본값: false
        private Integer width;
        private Integer height;

        public ThumbnailBuilder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public ThumbnailBuilder link(String link) {
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

        public JsonObject build() throws IllegalStateException {
            if (imageUrl == null)
                throw new IllegalStateException("Thumbnail에서 imageUrl은 필수 설정 항목입니다.");
            JsonObject thumbnail = new JsonObject();
            thumbnail.addProperty("imageUrl", imageUrl);
            if (link != null) thumbnail.addProperty("link", link);
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

    public static class ButtonsBuilder {
        private String label; // 최대 8자
        private String action;
        private String webLinkUrl; // -> webLink
        private String messageText; // -> message or block
        private String phoneNumber; // -> phone
        private String blockId; // -> block
//        private Map<String, Object> extra = new HashMap<>();

        public ButtonsBuilder label(String label) throws IllegalStateException {
            if (label.length() > 8)
                throw new IllegalStateException("Button에서 label은 최대 8자 제한입니다.");
            this.label = label;
            return this;
        }

        public ButtonsBuilder action(String action) {
            this.action = action;
            return this;
        }

        public ButtonsBuilder webLinkUrl(String webLinkUrl) {
            this.webLinkUrl = webLinkUrl;
            return this;
        }

        public ButtonsBuilder messageText(String messageText) {
            this.messageText = messageText;
            return this;
        }

        public ButtonsBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public ButtonsBuilder blockId(String blockId) {
            this.blockId = blockId;
            return this;
        }

        public JsonObject build() throws IllegalStateException {
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
            } else throw new IllegalStateException("Button의 action이 올바르게 설정되지 않았습니다.");

            return button;
        }
    }
}

