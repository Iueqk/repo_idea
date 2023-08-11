package com.controller;

import com.pojo.Menu;
import com.pojo.ResponseResult;
import com.pojo.Role;
import com.pojo.RoleMenuVo;
import com.service.MenuService;
import com.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role) {
        List<Role> roles = roleService.findAllRole(role);
        return new ResponseResult(true, 200, "查询用户成功", roles);
    }

    @RequestMapping("/saveOrUpdateRole")
    public ResponseResult saveOrUpdateRole(@RequestBody Role role) {
        if (role.getId() == null) {
            roleService.saveRole(role);
            return new ResponseResult(true, 200, "添加角色成功", role);
        } else {
            roleService.updateRole(role);
            return new ResponseResult(true, 200, "更新角色成功", role);
        }
    }

    /*
    查询所有菜单信息
    */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu() {
        //-1 表示查询所有父级菜单数据(子菜单数据封装在subMenuList里面)
        List<Menu> menuList = menuService.findSubMenuListByPid(-1);
        Map<String, Object> map = new HashMap<>();
        map.put("parentMenuList", menuList);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
        return result;
    }

    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(@RequestParam("roleId") Integer roleId) {
        List<Integer> menus = roleService.findAllMenuByRoleId(roleId);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", menus);
        return result;
    }

    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVo roleMenuVo) {
        roleService.RoleContextMenu(roleMenuVo);
        List<Integer> menus = roleService.findAllMenuByRoleId(roleMenuVo.getRoleId());
        ResponseResult result = new ResponseResult(true, 200, "响应成功", menus);
        return result;
    }
}
