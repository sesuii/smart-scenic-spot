package com.smartscenicspot.service;

import com.smartscenicspot.vo.PageVo;

/**
*
*
* @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
* @since 2023/3/23 22:49
**/
public interface StaffService {
    PageVo<?> getAllDtos(int page, int size);
}
