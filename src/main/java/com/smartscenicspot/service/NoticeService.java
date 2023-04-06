package com.smartscenicspot.service;

import com.smartscenicspot.dto.NoticeDto;
import com.smartscenicspot.vo.PageVo;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/23 22:50
 **/
public interface NoticeService {
    NoticeDto getDtoById(Long id);

    PageVo<?> getAllDtos(int page, int size);

    boolean addNewNotice(NoticeDto noticeDto);

    void broadCast(NoticeDto noticeDto);

    PageVo<?> getGroupNoticeDtos(int page, int size);

    void publishGroupNotice(NoticeDto noticeDto);

    boolean deleteGroupNotice(Long id);
}
