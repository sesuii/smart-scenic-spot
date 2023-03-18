package com.smartscenicspot.service;

import com.smartscenicspot.domain.Admin;
import com.smartscenicspot.vo.AdminVo;

import java.util.Map;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/15 15:56
 **/
public interface AdminService {

    Map<String, String> toAdminLogin(AdminVo adminVo);

    Admin createAccount(AdminVo adminVo);

    Admin getOneByAccount(String account);

}
