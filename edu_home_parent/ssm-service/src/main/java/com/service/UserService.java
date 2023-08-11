package com.service;

import com.github.pagehelper.PageInfo;
import com.pojo.*;

import java.util.List;

public interface UserService {

    PageInfo findAllUserByPage(UserVo userVo);

    void updateUserStatus(Integer id,String status);

    User login(User user) throws Exception;

    List<Role> findUserRelationRoleById(Integer id);

    void userContextRole(UserRoleVo userRoleVo);

    void registerUser() throws Exception;

    /*
    获取用户权限 动态获取菜单
     */
    ResponseResult getUserPermissions(Integer userId);
}
