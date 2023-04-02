package com.smartscenicspot.service.Impl;

import com.smartscenicspot.dto.TourGroupDto;
import com.smartscenicspot.mapper.TourGroupMapper;
import com.smartscenicspot.pojo.TourGroup;
import com.smartscenicspot.pojo.User;
import com.smartscenicspot.repository.TourGroupRepository;
import com.smartscenicspot.service.TourGroupService;
import com.smartscenicspot.service.UserService;
import com.smartscenicspot.utils.UniqueInvCodeUtil;
import com.smartscenicspot.vo.PageVo;
import com.smartscenicspot.vo.TourGroupVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/23 22:53
 **/

@Service
public class TourGroupServiceImpl implements TourGroupService {

    @Resource
    TourGroupRepository tourGroupRepository;

    @Resource
    UserService userService;
    @Override
    public boolean joinGroup(String inviteCode) {
        TourGroup tourGroup = tourGroupRepository.findByInviteCode(inviteCode).orElse(null);
        if(tourGroup == null) {
            return false;
        }
        String account = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByOpenid(account);
        user.setTourGroup(tourGroup);
        tourGroupRepository.updateGroupSizeBy(tourGroup.getGroupSize() + 1);
        return true;
    }

    @Override
    public TourGroupVo getVoById(Long id) {
        TourGroup tourGroup = tourGroupRepository.findById(id).orElse(null);
        return TourGroupMapper.INSTANCE.toVo(tourGroup);
    }

    @Override
    public PageVo<?> getAllDtos(int page, int size) {
        Page<TourGroup> tourGroups = tourGroupRepository.findAll(PageRequest.of(page, size));
        return PageVo.builder()
                .data(Collections.singletonList(TourGroupMapper.INSTANCE
                        .toDtoList(tourGroups.getContent())))
                .totalElements(tourGroups.getTotalElements())
                .totalPages(tourGroups.getTotalPages())
                .build();
    }

    @Override
    public TourGroupDto createNewGroup(TourGroupDto tourGroupDto) {
        String account = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByOpenid(account);
        tourGroupDto.setInviteCode(UniqueInvCodeUtil.generateInvCode());
        TourGroup tourGroup = TourGroupMapper.INSTANCE.dtoToEntity(tourGroupDto);
        tourGroup.setCreator(user);
        user.setTourGroup(tourGroup);
        tourGroupRepository.save(tourGroup);
        return TourGroupMapper.INSTANCE.toDto(tourGroup);
    }
}
