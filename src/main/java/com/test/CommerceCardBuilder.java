package com.test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * CommerceCardBuilder
 * @ author knight7024@naver.com
 * @ version 1.1.0
 */
public class CommerceCardBuilder {
    private String description; // 최대 76자
    private Integer price;
    private String currency = "won"; // 현재 won만 가능
    private Integer discount;
    private Integer discountRate;
    private Integer dicountedPrice;
    private final JsonArray thumbnails = new JsonArray(); // 현재 1개만 가능
    private JsonObject profile;
    private final JsonArray buttons = new JsonArray(); // 1개 이상, 3개 이하

    public CommerceCardBuilder description(String description) {
        if (description.length() > 76)
            throw new IllegalStateException("description의 길이는 최대 76자 제한입니다.");
        this.description = description;
        return this;
    }

    public CommerceCardBuilder price(Integer price) {
        if (price < 0)
            throw new IllegalStateException("price는 음수일 수 없습니다.");
        this.price = price;
        return this;
    }

    public CommerceCardBuilder discount(Integer discount) {
        if (discount < 0)
            throw new IllegalStateException("discount는 음수일 수 없습니다.");
        this.discount = discount;
        return this;
    }

    public CommerceCardBuilder discountRate(Integer discountRate) {
        if (discountRate < 0)
            throw new IllegalStateException("discountRate는 음수일 수 없습니다.");
        this.discountRate = discountRate;
        return this;
    }

    public CommerceCardBuilder dicountedPrice(Integer dicountedPrice) {
        if (dicountedPrice < 0)
            throw new IllegalStateException("dicountedPrice는 음수일 수 없습니다.");
        this.dicountedPrice = dicountedPrice;
        return this;
    }

    public CommerceCardBuilder thumbnails(JsonObject thumbnail) throws IllegalStateException {
        if (thumbnails.size() >= 1)
            throw new IllegalStateException("Thumbnail의 개수는 현재 1개만 가능합니다.");
        thumbnails.add(thumbnail);
        return this;
    }

    public CommerceCardBuilder profile(JsonObject profile) {
        this.profile = profile;
        return this;
    }

    public CommerceCardBuilder buttons(JsonObject button) throws IllegalStateException {
        if (buttons.size() >= 3)
            throw new IllegalStateException("Button의 개수는 최대 3개 제한입니다.");
        buttons.add(button);
        return this;
    }

    public JsonElement build() {
        if (description == null || price == null)
            throw new IllegalStateException("description과 price는 필수 항목입니다.");
        if (!"won".equals(currency))
            throw new IllegalStateException("현재 currency는 'won'만 가능합니다.");
        if (discountRate != null && dicountedPrice == null)
            throw new IllegalStateException("discountRate를 쓰는 경우 dicountedPrice는 필수 항목입니다.");
        if (thumbnails.size() == 0)
            throw new IllegalStateException("thumbnails는 필수 항목입니다.");
        if (buttons.size() == 0)
            throw new IllegalStateException("Button은 최소 1개 이상이어야 합니다.");
        JsonObject field = new JsonObject();
        JsonObject type = new JsonObject();
        field.addProperty("description", description);
        field.addProperty("price", price);
        field.addProperty("currency", currency);
        if (discount != null)
            field.addProperty("discount", discount);
        if (discountRate != null)
            field.addProperty("discountRate", discountRate);
        if (dicountedPrice != null)
            field.addProperty("dicountedPrice", dicountedPrice);
        field.add("thumbnails", thumbnails);
        if (profile != null)
            field.add("profile", profile);
        if (buttons.size() != 0)
            field.add("buttons", buttons);
        type.add(EnumResponseType.TemplateType.COMMERCECARD.getTypeText(), field);
        return type;
    }
}