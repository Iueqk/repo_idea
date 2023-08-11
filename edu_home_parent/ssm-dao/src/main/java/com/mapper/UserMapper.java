package com.mapper;

import com.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    List<User> findAllUserByPage();

    void updateUserStatus(User user);

    /*根据用户名查询具体的用户信息*/
    User login(User user);

    void deleteUserRelationRoleByUserId(Integer userId);

    void userContextRole(User_Role_relation userRoleRelation);

    void registerUser(User user);

    /*
     *  1.根据用户ID获取到角色信息
     */
    List<Role> findUserRelationRoleById(Integer id);

    /*
     *  2.根据角色id查询父级菜单(-1)
     */
    List<Menu> findParentMenuByRoleId(List<Integer> ids);

    /*
        3.根据pid查询子级菜单
     */
    List<Menu> findSubMenuByPid(Integer pid);

    /*
        4.获取用户拥有的资源权限信息
     */
    List<Resource> findResourceByRoleId(List<Integer> roleIds);
}
