package com.smartscenicspot;

import com.smartscenicspot.dto.ShowplaceDto;
import com.smartscenicspot.repository.ShowplaceRepository;
import com.smartscenicspot.service.ShowplaceService;
import com.smartscenicspot.vo.ShowplaceVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

/**
 * 景区业务层测试
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/24 17:20
 **/
@SpringBootTest
public class ShowplaceServiceTest {
    @Resource
    ShowplaceService showplaceService;

    @Resource
    ShowplaceRepository showplaceRepository;

    @Test
    @Transactional
    @Commit
    void addShowplaceTest() {
        ShowplaceVo showplaceVo = ShowplaceVo.builder()
                .name("庐山")
                .address("江西省九江市庐山市牯岭镇")
                .longitude(115.7168154)
                .latitude(29.3922753)
                .imgsUrl("https://img.zcool.cn/community/01ad915610d4926ac7251df8ec6edb.jpg@1280w_1l_2o_100sh.jpg")
                .score(4.5)
                .openNote("全年 全天开放；庐山交通索道营业时间：旺季（5月-10月）07:30-20:30；淡季（11月-次年4月）07:30-18:30； " +
                        "庐山旅游观光车营业时间：旺季（4月1日-10月31日）07:00-16:30；淡季（11月1日-次年3月31日）07:30-16:20\n")
                .category("风景名胜;5A景区;")
                .tels("0792-8296565,0792-8296666")
                .introduction("庐山风景名胜区位于九江市鄱阳湖湖畔，它的风光或许不如五岳奇险，但人文底蕴丰富。" +
                        "历代文人墨客留下了不少赞美庐山的佳句，尤其是苏轼那句“不识庐山真面目，只缘身在此山中”，让人充满向往。")
                .capacity(10000)
                .statusMsg("正常开放")
                .build();

        boolean saved = showplaceService.addNewShowplace(showplaceVo);
        assert saved;
    }

    @Test
    void matchFuzzyTest() {
        double lat = 29.392275, lng = 115.716815;
        ShowplaceVo showplaceVo = showplaceService.matchFuzzyPosition(lat, lng);
        assert Objects.equals(showplaceVo.getName(), "庐山");
    }

    @Test
    void getShowplacesList() {
        List<ShowplaceVo> showplaces = showplaceService.getAllShowplaces();
        assert showplaces.size() == 1;
        System.out.println(showplaces.get(0));
    }


    @Test
    void getShowplace() {
        ShowplaceDto showplaceDto = showplaceService.getDTOById(8L);
        assert !Objects.equals(null, showplaceDto);
        assert showplaceDto.getAttractions().size() == 1;
    }
}
