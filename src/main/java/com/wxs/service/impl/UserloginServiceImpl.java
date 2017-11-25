package com.wxs.service.impl;

import com.wxs.mapper.UserloginMapper;
import com.wxs.po.Userlogin;
import com.wxs.po.UserloginExample;
import com.wxs.service.UserloginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: jitwxs
 * Date: 2017-10-10
 * 登陆Service层实现类
 */
@Service
public class UserloginServiceImpl implements UserloginService {

    @Autowired
    private UserloginMapper userloginMapper;

    /**
     * 查找登陆信息
     * @param name 用户名
     * @return
     * @throws Exception
     */
    @Override
    public Userlogin findByName(String name) {
        UserloginExample userloginExample = new UserloginExample();

        UserloginExample.Criteria criteria = userloginExample.createCriteria();
        criteria.andNameEqualTo(name);

        List<Userlogin> lists = userloginMapper.selectByExample(userloginExample);

        if (lists.size() > 0) {
            return lists.get(0);
        } else {
            return null;
        }
    }

    /**
     * 根据id查找
     * @param id
     * @return
     */
    @Override
    public Userlogin findById(Integer id) {
        UserloginExample userloginExample = new UserloginExample();

        UserloginExample.Criteria criteria = userloginExample.createCriteria();
        criteria.andIdEqualTo(id);

        List<Userlogin> list = userloginMapper.selectByExample(userloginExample);

        if(list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }

    /**
     * 添加登录信息
     * @param userlogin Userlogin对象
     * @throws Exception
     */
    @Override
    public void save(Userlogin userlogin) throws Exception {
        userloginMapper.insert(userlogin);
    }

    /**
     * 删除登录信息
     * @param id
     * @throws Exception
     */
    @Override
    public void removeById(Integer id) throws Exception {
        UserloginExample userloginExample = new UserloginExample();

        UserloginExample.Criteria criteria = userloginExample.createCriteria();
        criteria.andIdEqualTo(id);

        userloginMapper.deleteByExample(userloginExample);
    }

    /**
     * 更新登录信息
     * @param name 用户名
     * @param userlogin Userlogin对象
     */
    @Override
    public void updateByName(String name, Userlogin userlogin) {
        UserloginExample userloginExample = new UserloginExample();

        UserloginExample.Criteria criteria = userloginExample.createCriteria();
        criteria.andNameEqualTo(name);

        userloginMapper.updateByExample(userlogin, userloginExample);
    }

    /**
     * 根据id更新
     * @param id
     * @param userlogin
     */
    @Override
    public void updateById(Integer id, Userlogin userlogin) {
        UserloginExample userloginExample = new UserloginExample();

        UserloginExample.Criteria criteria = userloginExample.createCriteria();
        criteria.andIdEqualTo(id);

        userloginMapper.updateByExample(userlogin, userloginExample);
    }

}
