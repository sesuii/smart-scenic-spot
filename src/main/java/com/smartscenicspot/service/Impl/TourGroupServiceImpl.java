package com.smartscenicspot.service.Impl;

import com.smartscenicspot.db.pgql.repository.UserRepository;
import com.smartscenicspot.dto.TourGroupDto;
import com.smartscenicspot.mapper.TourGroupMapper;
import com.smartscenicspot.db.pgql.entity.TourGroup;
import com.smartscenicspot.db.pgql.entity.User;
import com.smartscenicspot.db.pgql.repository.TourGroupRepository;
import com.smartscenicspot.service.TourGroupService;
import com.smartscenicspot.service.UserService;
import com.smartscenicspot.utils.UniqueInvCodeUtil;
import com.smartscenicspot.vo.PageVo;
import com.smartscenicspot.vo.TourGroupVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final UserRepository userRepository;

    public TourGroupServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean joinGroup(String inviteCode) {
        TourGroup tourGroup = tourGroupRepository.findByInviteCode(inviteCode).orElse(null);
        if(tourGroup == null) {
            return false;
        }
        String account = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByOpenid(account);
        if(user.getTourGroup() == tourGroup) {
            return false;
        }
        user.setTourGroup(tourGroup);
        tourGroupRepository.updateGroupSizeBy(tourGroup.getGroupSize() + 1);
        return true;
    }

    @Override
    @Transactional(value = "pgqlTransactionManger")
    public TourGroupVo getVo() {
        String account = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByOpenid(account);
        if(user == null || user.getTourGroup() == null) {
            return null;
        }
        return TourGroupMapper.INSTANCE.toVo(user.getTourGroup());
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
    @Transactional(value = "pgqlTransactionManger", rollbackFor = Exception.class)
    public TourGroupDto createNewGroup(TourGroupDto tourGroupDto) {
        String account = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByOpenid(account);
        tourGroupDto.setInviteCode(UniqueInvCodeUtil.generateInvCode());
        TourGroup tourGroup = TourGroupMapper.INSTANCE.dtoToEntity(tourGroupDto);
        tourGroup.setCreator(user);
        tourGroupRepository.save(tourGroup);
        userRepository.updateTourGroupByOpenid(tourGroup, account);
        return TourGroupMapper.INSTANCE.toDto(tourGroup);
    }

    @Override
    public boolean deleteGroup(Long groupId) {
        String account = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        TourGroup tourGroup = tourGroupRepository.findById(groupId).orElse(null);
        if(tourGroup == null || tourGroup.getCreator().getOpenid() != account) {
            return false;
        }
        tourGroupRepository.deleteById(groupId);
        return true;
    }

    @Override
    public boolean exitGroup() {
        String account = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userRepository.updateTourGroupByOpenid(null, account);
        return true;
    }

    @Override
    public int countActiveGroups() {
        return tourGroupRepository.activeGroupCount();
    }
}
