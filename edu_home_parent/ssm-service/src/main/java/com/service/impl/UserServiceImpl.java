package com.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.UserMapper;
import com.pojo.*;
import com.service.UserService;
import com.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo findAllUserByPage(UserVo userVo) {

        PageHelper.startPage(userVo.getCurrentPage(), userVo.getPageSize());

        List<User> users = userMapper.findAllUserByPage();

        PageInfo<User> pageInfo = new PageInfo<>(users);

        return pageInfo;
    }

    @Override
    public void updateUserStatus(Integer id, String status) {
        User user = new User();
        user.setId(id);
        user.setStatus(status);
        user.setUpdate_time(new Date());

        userMapper.updateUserStatus(user);
    }

    @Override
    public User login(User user) throws Exception {
        User login = userMapper.login(user);
        if (login != null && Md5.verify(user.getPassword(), "cqut", login.getPassword())) {
            return login;
        }
        return null;
    }

    @Override
    public List<Role> findUserRelationRoleById(Integer id) {
        List<Role> roles = userMapper.findUserRelationRoleById(id);
        return roles;
    }


    @Override
    public void userContextRole(UserRoleVo userRoleVo) {
        /*要先把用户已经关联的中间表删除*/
        userMapper.deleteUserRelationRoleByUserId(userRoleVo.getUserId());

        for (Integer roleId : userRoleVo.getRoleIdList()) {
            Date date = new Date();
            User_Role_relation user_role_relation = new User_Role_relation();
            user_role_relation.setUserId(userRoleVo.getUserId());
            user_role_relation.setRoleId(roleId);
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);
            user_role_relation.setCreatedBy("iu");
            user_role_relation.setUpdatedby("iu");
            userMapper.userContextRole(user_role_relation);
        }
    }

    @Override
    public void registerUser() throws Exception {
        User user = new User();
        user.setName("19999999999");
        user.setPortrait("https://edu-lagou.oss-cn-beijing.aliyuncs.com/images/2020/06/28/15933251448762927.png");
        user.setPassword(Md5.md5("123456","cqut"));
        user.setReg_ip("重庆");
        user.setAccount_non_expired(1);
        user.setAccount_non_locked(1);
        user.setCredentials_non_expired(1);
        user.setStatus("ENABLE");
        user.setIs_del(0);
        user.setCreate_time(new Date());
        user.setUpdate_time(new Date());
        user.setPhone("19999999999");
        userMapper.registerUser(user);
    }

    @Override
    public ResponseResult getUserPermissions(Integer userId) {
        // 1.获取用户所拥有的角色
        List<Role> roles = userMapper.findUserRelationRoleById(userId);
        // 2.获取角色id
        ArrayList<Integer> roleIds = new ArrayList<>();
        for (Role role : roles) {
            roleIds.add(role.getId());
        }
        // 3.根据id查询一级菜单
        List<Menu> parentMenuByRoleId = userMapper.findParentMenuByRoleId(roleIds);
        // 4.查询一级菜单对应的子菜单
        for (Menu menu : parentMenuByRoleId) {
            List<Menu> subMenu = userMapper.findSubMenuByPid(menu.getId());
            menu.setSubMenuList(subMenu);
        }
        // 5.获取资源信息
        List<Resource> resourceByRoleId = userMapper.findResourceByRoleId(roleIds);
        // 6.封装数据并返回
        Map<String, Object> map = new HashMap<>();
        map.put("menuList", parentMenuByRoleId);
        map.put("resourceList", resourceByRoleId);
        return new ResponseResult(true, 200, "获取用户权限信息成功", map);
    }
}
