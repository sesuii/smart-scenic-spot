package com.smartscenicspot.service.Impl;

import com.smartscenicspot.constant.ShowplaceConstant;
import com.smartscenicspot.db.pgql.entity.Showplace;
import com.smartscenicspot.db.pgql.repository.ShowplaceRepository;
import com.smartscenicspot.dto.ShowplaceDto;
import com.smartscenicspot.mapper.ShowplaceMapper;
import com.smartscenicspot.service.ShowplaceService;
import com.smartscenicspot.vo.ShowplaceVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/23 22:52
 **/

@Service
public class ShowplaceServiceImpl implements ShowplaceService {

    @Resource
    ShowplaceRepository showplaceRepository;

    @Override
    public ShowplaceVo matchFuzzyPosition(double lat, double lng) {
        List<Double> scope = calcSquareScope(lat, lng);
        Showplace showplace = showplaceRepository.findByFuzzyPosition(
                scope.get(0), scope.get(1), scope.get(2), scope.get(3)
        );
        if(showplace == null) {
            return null;
        }
        return convertToShowplaceVo(showplace);
    }

    @Override
    public List<ShowplaceVo> getAllShowplaces() {
        List<Showplace> showplaces = showplaceRepository.findAll();
        return showplaces.stream()
                .map(this::convertToShowplaceVo)
                .collect(Collectors.toList());
    }

    @Override
    public boolean addNewShowplace(ShowplaceVo showplaceVo) {
        Showplace showplace = new Showplace();
        BeanUtils.copyProperties(showplaceVo, showplace);
        System.out.println(showplace);
        showplaceRepository.save(showplace);
        return true;
    }

    @Override
    public ShowplaceDto getDTOById(Long id) {
        Optional<Showplace> optionalShowplace = showplaceRepository.findById(id);
        Showplace showplace = optionalShowplace.orElse(null);
        if(null == showplace) {
            return null;
        }
        return ShowplaceMapper.INSTANCE.toDto(showplace);
    }

    private List<Double> calcSquareScope(double lat, double lng) {
        double dlng = 2 * Math.asin(ShowplaceConstant.CALC_HALF /
                Math.cos(lat * Math.PI / 180)) * 180 / Math.PI;
        List<Double> square = new ArrayList<>();
        square.add(lat - ShowplaceConstant.CALC_LATITUDE);
        square.add(lat + ShowplaceConstant.CALC_LATITUDE);
        square.add(lng - dlng);
        square.add(lng + dlng);
        return square;
    }

    private ShowplaceVo convertToShowplaceVo(Showplace showplace) {
        ShowplaceVo showplaceVo = new ShowplaceVo();
        BeanUtils.copyProperties(showplace, showplaceVo);
        showplaceVo.setStatusMsg(showplace.getStatus());
        showplaceVo.setShowplaceId(showplace.getId());
        return showplaceVo;
    }
}
