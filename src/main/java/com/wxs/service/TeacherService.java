package com.wxs.service;

import com.wxs.po.Teacher;

import java.util.List;

/**
 * Author: jitwxs
 * Date: 2017-10-10
 * 教师Service层
 */
public interface TeacherService {

    void updateById(Teacher teacher) throws Exception;

    boolean removeById(Integer id) throws Exception;

    List<Teacher> findByPaging(Integer toPageNo) throws Exception;

    int save(Teacher teacher) throws Exception;

    int getCountTeacher() throws Exception;

    Teacher findById(Integer id) throws Exception;

    List<Teacher> findByName(String name) throws Exception;

    List<Teacher> findAll() throws Exception;
}
