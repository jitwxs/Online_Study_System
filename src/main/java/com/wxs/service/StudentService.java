package com.wxs.service;

import com.wxs.po.Student;

import java.util.List;

/**
 * Author: jitwxs
 * Date: 2017-10-10
 * 学生Service层
 */
public interface StudentService {

    /**
     * 更新学生信息
     * @param student Student对象
     * @throws Exception
     */
    void updataById(Student student) throws Exception;

    /**
     * 删除学生信息
     * @param id 学生id
     * @return 删除是否成功
     * @throws Exception
     */
    Boolean removeById(Integer id) throws Exception;

    /**
     * 分页查询学生信息
     * @param toPageNo 页码
     * @return StudentCustom列表
     * @throws Exception
     */
    List<Student> findByPaging(Integer toPageNo) throws Exception;

    /**
     * 添加学生信息
     * @param studentCustoms StudentCustom对象
     * @return 添加是否成功
     * @throws Exception
     */
    int save(Student studentCustoms) throws Exception;

    /**
     * 获取学生人数
     * @return 学生人数
     * @throws Exception
     */
    int getCountStudent() throws Exception;

    /**
     * 查询学生信息
     * @param id 学生id
     * @return StudentCustom对象
     * @throws Exception
     */
    Student findById(Integer id) throws Exception;

    /**
     * 根据名字查询学生信息
     * @param name
     * @return StudentCustom列表
     * @throws Exception
     */
    List<Student> findByName(String name) throws Exception;

}
