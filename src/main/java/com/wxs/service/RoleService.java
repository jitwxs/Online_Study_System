package com.wxs.service;

import com.wxs.po.Role;

/**
 * Author: jitwxs
 * Date: 2017-10-10
 * 用户类型Service层
 */
public interface RoleService {

    /**
     * 根据用户类型查询
     * @param id 用户类型id
     * @return Role对象
     * @throws Exception
     */
    Role findByid(Integer id) throws Exception;

}
