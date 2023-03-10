package com.smartscenicspot.service.Impl;

import com.smartscenicspot.domain.Notice;
import com.smartscenicspot.dto.NoticeDto;
import com.smartscenicspot.mapper.NoticeMapper;
import com.smartscenicspot.repository.NoticeRepository;
import com.smartscenicspot.service.NoticeService;
import com.smartscenicspot.vo.PageVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/23 22:52
 **/

@Service
public class NoticeServiceImpl implements NoticeService {

    @Resource
    NoticeRepository noticeRepository;
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
        Page<Notice> notices = noticeRepository.findAll(PageRequest.of(page, size));
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

}
