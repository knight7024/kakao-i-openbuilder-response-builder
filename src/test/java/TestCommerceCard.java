import com.test.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCommerceCard {
    @Test
    public void test() {
        SkillResponse result = new SkillResponse();
        result.addCommerceCard(new CommerceCardBuilder()
                .description("따끈따끈한 보물 상자 팝니다")
                .price(10000)
                .discount(1000)
                .thumbnails(new ThumbnailBuilder()
                        .imageUrl("http://k.kakaocdn.net/dn/83BvP/bl20duRC1Q1/lj3JUcmrzC53YIjNDkqbWK/i_6piz1p.jpg")
                        .link(new LinkBuilder()
                                .web("https://store.kakaofriends.com/kr/products/1542")
                                .build())
                        .build())
                .profile(new ProfileBuilder()
                        .imageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT4BJ9LU4Ikr_EvZLmijfcjzQKMRCJ2bO3A8SVKNuQ78zu2KOqM")
                        .nickname("보물상자 팝니다")
                        .build())
                .buttons(new ButtonBuilder()
                        .label("구매하기")
                        .action(EnumResponseType.ButtonActionType.WEBLINK.getTypeText())
                        .webLinkUrl("https://store.kakaofriends.com/kr/products/1542")
                        .build())
                .buttons(new ButtonBuilder()
                        .label("전화하기")
                        .action(EnumResponseType.ButtonActionType.PHONE.getTypeText())
                        .phoneNumber("354-86-00070")
                        .build())
                .buttons(new ButtonBuilder()
                        .label("공유하기")
                        .action(EnumResponseType.ButtonActionType.SHARE.getTypeText())
                        .build())
                .build());

        assertEquals("{\"version\":\"2.0\",\"template\":{\"outputs\":[{\"commerceCard\":{\"description\":\"따끈따끈한 보물 상자 팝니다\",\"price\":10000,\"discount\":1000,\"currency\":\"won\",\"thumbnails\":[{\"imageUrl\":\"http://k.kakaocdn.net/dn/83BvP/bl20duRC1Q1/lj3JUcmrzC53YIjNDkqbWK/i_6piz1p.jpg\",\"link\":{\"web\":\"https://store.kakaofriends.com/kr/products/1542\"}}],\"profile\":{\"imageUrl\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT4BJ9LU4Ikr_EvZLmijfcjzQKMRCJ2bO3A8SVKNuQ78zu2KOqM\",\"nickname\":\"보물상자 팝니다\"},\"buttons\":[{\"label\":\"구매하기\",\"action\":\"webLink\",\"webLinkUrl\":\"https://store.kakaofriends.com/kr/products/1542\"},{\"label\":\"전화하기\",\"action\":\"phone\",\"phoneNumber\":\"354-86-00070\"},{\"label\":\"공유하기\",\"action\":\"share\"}]}}]}}", result.getSkillPayload().toString());
    }
}
