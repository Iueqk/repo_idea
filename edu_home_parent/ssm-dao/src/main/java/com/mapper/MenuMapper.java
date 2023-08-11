package com.mapper;

import com.pojo.Menu;

import java.util.List;

public interface MenuMapper {
    /**
     * 查询全部的父子菜单信息
     * */
    public List<Menu> findSubMenuListByPid(Integer pid);

    List<Menu> findAllMenu();

    Menu findMenuById(Integer id);

}
