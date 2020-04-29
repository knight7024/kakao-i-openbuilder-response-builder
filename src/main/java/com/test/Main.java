package com.test;

import com.fasterxml.jackson.core.JsonProcessingException;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        SkillResponse result = new SkillResponse();
        result.addBasicCard(new BasicCardBuilder().title("카드입니다.").thumbnail(new ThumbnailBuilder().imageUrl("url").build()).build());
        System.out.println(result.getSkillPayload().toString());

        SkillResponse result2 = new SkillResponse();
        result2.addCommerceCard(new CommerceCardBuilder()
                .description("설명입니다.")
                .price(10000)
                .thumbnails(new ThumbnailBuilder()
                        .imageUrl("imageurl")
                        .build())
                .buttons(new ButtonBuilder()
                        .label("버튼입니다.")
                        .action(EnumResponseType.ButtonActionType.MESSAGE.getTypeText())
                        .messageText("메시지입니다.")
                        .build())
                .profile(new ProfileBuilder()
                        .nickname("knight7024")
                        .build())
                .build());
        System.out.println(result2.getSkillPayload().toString());
    }
}