package com.smartscenicspot.mapper;

import com.smartscenicspot.db.pgql.entity.Admin;
import com.smartscenicspot.dto.AdminDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/31 14:00
 **/

@Mapper
public interface AdminMapper {

    AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);


    default AdminDto adminToAdminDto(Admin admin) {
        if(admin == null) {
            return null;
        }
        return AdminDto.builder()
                .username(admin.getAccount())
                .name(admin.getName())
                .roles(List.of(admin.getRoles().split(",")))
                .avatar(admin.getAvatar())
                .introduction(admin.getIntroduction())
                .build();
    }

    default Admin adminDtoToAdmin(AdminDto adminDto) {
        if(adminDto == null) {
            return null;
        }
        Admin admin = new Admin();
        BeanUtils.copyProperties(adminDto, admin);
        String roles = String.join(",", adminDto.getRoles());
        admin.setAccount(adminDto.getUsername());
        admin.setRoles(roles);
        admin.setLastActiveIp(adminDto.getIp());
        return admin;
    }
}
