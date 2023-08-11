package com.pojo;

import java.util.List;

public class UserRoleVo {
    private Integer userId;

    private List<Integer> roleIdList;

    @Override
    public String toString() {
        return "UserRoleVo{" +
                "userId=" + userId +
                ", roleIdList=" + roleIdList +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Integer> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Integer> roleIdList) {
        this.roleIdList = roleIdList;
    }
}
