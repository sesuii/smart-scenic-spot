package com.smartscenicspot.service.Impl;

import com.smartscenicspot.pojo.Staff;
import com.smartscenicspot.mapper.StaffMapper;
import com.smartscenicspot.repository.StaffRepository;
import com.smartscenicspot.service.StaffService;
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
public class StaffServiceImpl implements StaffService {

    @Resource
    StaffRepository staffRepository;
    @Override
    public PageVo<?> getAllDtos(int page, int size) {
        Page<Staff> staffPage = staffRepository.findAll(PageRequest.of(page, size));
        return PageVo.builder()
                .data(Collections.singletonList(StaffMapper.INSTANCE
                        .toDtoList(staffPage.getContent())))
                .totalPages(staffPage.getTotalPages())
                .totalElements(staffPage.getTotalElements())
                .build();
    }
}
