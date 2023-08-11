package com.service;

import com.pojo.Menu;

import java.util.List;

public interface MenuService {

    List<Menu> findSubMenuListByPid(Integer pid);

     List<Menu> findAllMenu();

     Menu findMenuById(Integer id);
}
