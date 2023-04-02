package com.smartscenicspot.service.Impl;

import com.alibaba.fastjson.JSON;
import com.smartscenicspot.dto.NoticeDto;
import com.smartscenicspot.mapper.NoticeMapper;
import com.smartscenicspot.pojo.Notice;
import com.smartscenicspot.pojo.TourGroup;
import com.smartscenicspot.pojo.User;
import com.smartscenicspot.repository.NoticeRepository;
import com.smartscenicspot.repository.UserRepository;
import com.smartscenicspot.service.NoticeService;
import com.smartscenicspot.service.WebSocketService;
import com.smartscenicspot.vo.PageVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/23 22:52
 **/

@Service
public class NoticeServiceImpl implements NoticeService {

    @Resource
    NoticeRepository noticeRepository;

    @Resource
    WebSocketService webSocketService;

    @Resource
    UserRepository userRepository;
    @Override
    public NoticeDto getDtoById(Long id) {
        Notice notice = noticeRepository.findById(id).orElse(null);
        if(notice == null) {
            return null;
        }
        return NoticeMapper.INSTANCE.toDto(notice);
    }

    @Override
    public PageVo<?> getAllDtos(int page, int size) {
        Page<Notice> notices = noticeRepository.findAllByScope((byte) 0,
                PageRequest.of(page, size, Sort.by("gmtCreate").descending())).orElse(null);
        if(notices == null) {
            return null;
        }
        return PageVo.builder()
                .data(Collections.singletonList(NoticeMapper.INSTANCE
                        .toDtoList(notices.getContent())))
                .totalElements(notices.getTotalElements())
                .totalPages(notices.getTotalPages())
                .build();
    }

    @Override
    public boolean addNewNotice(NoticeDto noticeDto) {
        Notice notice = NoticeMapper.INSTANCE.toEntity(noticeDto);
        noticeRepository.save(notice);
        return true;
    }

    @Override
    public boolean broadCast(NoticeDto noticeDto) {
        Notice notice = NoticeMapper.INSTANCE.toEntity(noticeDto);
        noticeRepository.save(notice);
        String message = JSON.toJSONString(noticeDto);
        try {
            webSocketService.broadCast(new TextMessage(message));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public PageVo<?> getGroupNoticeDtos(int page, int size) {
        String account = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByOpenid(account).orElse(null);
        if(user == null || user.getTourGroup() == null) {
            return null;
        }
        Page<Notice> notices = noticeRepository.findAllByTourGroup(user.getTourGroup(),
                PageRequest.of(page, size, Sort.by("gmt_create").descending())).orElse(null);
        if(notices == null) {
            return null;
        }
        return PageVo.builder()
                .data(Collections.singletonList(NoticeMapper.INSTANCE.toDtoList(notices.getContent())))
                .totalElements(notices.getTotalElements())
                .totalPages(notices.getTotalPages())
                .build();
    }

    @Override
    @Async
    @Transactional(rollbackOn = Exception.class)
    public boolean publishGroupNotice(NoticeDto noticeDto) {
        String account = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByOpenid(account).orElse(null);
        if(user == null || user.getTourGroup() == null) {
            return false;
        }
        TourGroup tourGroup = user.getTourGroup();
        if(tourGroup.getCreator() != user) {
            return false;
        }
        List<String> openids = tourGroup.getMembers().stream().map(User::getOpenid)
                .collect(Collectors.toList());
        Notice notice = NoticeMapper.INSTANCE.toEntity(noticeDto);
        notice.setScope((byte) 1);
        noticeRepository.save(notice);
        String message = JSON.toJSONString(noticeDto);
        try {
            webSocketService.sentMessage(openids, new TextMessage(message));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
