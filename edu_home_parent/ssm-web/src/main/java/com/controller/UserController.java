package com.controller;

import com.github.pagehelper.PageInfo;
import com.pojo.*;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo) {
        PageInfo pageInfo = userService.findAllUserByPage(userVo);
        return new ResponseResult(true, 200, "查询成功", pageInfo);
    }

    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(@RequestParam("id") Integer id, @RequestParam("status") String status) {

        if ("ENABLE".equalsIgnoreCase(status)) {
            status = "DISABLE";
        } else {
            status = "ENABLE";
        }

        userService.updateUserStatus(id, status);
        return new ResponseResult(true, 200, "修改", status);
    }

    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {

        User login = userService.login(user);

        ResponseResult result = null;
        if (login != null) {
            //保存access_token
            Map<String, Object> map = new HashMap<>();
            String access_token = UUID.randomUUID().toString();
            map.put("access_token", access_token);
            map.put("user_id", login.getId());

            HttpSession session = request.getSession();
            session.setAttribute("user_id", login.getId());
            session.setAttribute("access_token", access_token);

            result = new ResponseResult(true, 1, "响应成功", map);
        } else {
            result = new ResponseResult(true, 1, "用户名密码错误", null);
        }
        return result;
    }

    /*
        获取用户拥有的角色
    */
    @GetMapping("/findUserRoleById/{id}")
    public ResponseResult findUserRoleById(@PathVariable("id") int id) {
        List<Role> roleList = userService.findUserRelationRoleById(id);
        return new ResponseResult(true, 200, "分配角色回显成功", roleList);
    }

    @RequestMapping("/userContextRole")
    public ResponseResult findUserRoleById(@RequestBody UserRoleVo userRoleVo) {
        userService.userContextRole(userRoleVo);
        return new ResponseResult(true, 200, "分配角色回显成功", userRoleVo);
    }

    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request) {
        // 1.获取请求头中的token
        String header_token = request.getHeader("Authorization");
        // 2.获取登陆中存在session中的token
        String session_token = (String) request.getSession().getAttribute("access_token");
        // 3.判断session是否一致
        if (header_token.equals(session_token)) {
            ResponseResult result = userService.getUserPermissions((Integer) request.getSession().getAttribute("userId"));
            return result;
        }
        return new ResponseResult(true, 400, "获取菜单信息失败", null);
    }

    @RequestMapping("/registerUser")
    public ResponseResult registerUser() throws Exception {
        userService.registerUser();
        return new ResponseResult(true, 200, "注册成功", null);
    }
}
