package com.wxs.service;

import com.wxs.po.Userlogin;

/**
 * Author: jitwxs
 * Date: 2017-10-10
 * 登陆Service层
 */
public interface UserloginService {

    /**
     * 查找登陆信息
     * @param name 用户名
     * @return
     * @throws Exception
     */
    Userlogin findByName(String name);

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    Userlogin findById(Integer id);

    /**
     * 添加登录信息
     * @param userlogin Userlogin对象
     * @throws Exception
     */
    void save(Userlogin userlogin) throws Exception;

    /**
     * 删除登录信息
     * @param id
     * @throws Exception
     */
    void removeById(Integer id) throws Exception;

    /**
     * 更新登录信息
     * @param name 用户名
     * @param userlogin Userlogin对象
     */
    void updateByName(String name, Userlogin userlogin);

    /**
     * 根据id更新
     * @param id
     * @param userlogin
     */
    void updateById(Integer id,Userlogin userlogin);

}
