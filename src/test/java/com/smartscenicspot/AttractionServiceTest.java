package com.smartscenicspot;

import com.smartscenicspot.service.AttractionService;
import com.smartscenicspot.vo.AttractionVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 景点业务逻辑类测试
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/24 22:06
 **/

@SpringBootTest
public class AttractionServiceTest {

    @Resource
    AttractionService attractionService;

    @Test
    void getAllTest() {
    }

    @Test
    @Transactional
    @Commit
    void creatTest() {
        List<Map<String, String>> infrastructure = new ArrayList<>();
        infrastructure.add(Map.of("厕所", "3"));
        AttractionVo attractionVo = AttractionVo.builder()
                .name("白鹿洞书院")
                .address("海会镇环山公路27号")
                .longitude(116.041881)
                .latitude(29.517095)
                .imgsUrl("http://store.is.autonavi.com/showpic/7bd14f6913f7e2bb18f0d11672e4e5af,http://store.is.autonavi.com/showpic/ed132a4469bd154c29b39ea37014a823,http://store.is.autonavi.com/showpic/737884c8bb1a3fcf67e334f8d2a17913")
                .openNote("淡季08:00-17:00,旺季07:30-18:00")
                .category("风景名胜;")
                .infrastructure(infrastructure)
                .tel("0792-8296565")
                .introduction("白鹿洞书院位于五老峰东南，始建于公元940年，全院山地面积为3000亩，建筑面积为3800平方米。书院山环水合，幽静清邃，是中国重点文物保护单位。")
                .capacity(800)
                .score(4.8)
                .status((byte) 1)
                .amrt(30)
                .build();
        boolean saved = attractionService.addNewAttraction(attractionVo);
        assert saved;
    }
}
