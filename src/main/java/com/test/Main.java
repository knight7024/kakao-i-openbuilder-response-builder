package com.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.domain.BasicCard;
import com.test.domain.Thumbnail;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        SkillResponse result = new SkillResponse();
        result.addBasicCard(new SkillResponse.BasicCardBuilder().title("카드입니다.").thumbnail(new SkillResponse.ThumbnailBuilder().imageUrl("url").build()).build());
        System.out.println(result.getSkillPayload().toString());
    }
}