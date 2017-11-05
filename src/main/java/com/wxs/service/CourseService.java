package com.wxs.service;

import com.wxs.po.Course;

import java.util.List;

/**
 * Author: jitwxs
 * Date: 2017-10-10
 * 课程Service层
 */
public interface CourseService {
    /**
     * 更新课程
     * @param course course对象
     * @throws Exception
     */
    void upadteById(Course course) throws Exception;

    /**
     * 删除课程
     * @param id 课程id
     * @return 删除是否成功
     * @throws Exception
     */
    Boolean removeById(Integer id) throws Exception;

    /**
     * 分页查询课程
     * @param toPageNo 页码
     * @return CourseCustom列表
     * @throws Exception
     */
    List<Course> findByPaging(Integer toPageNo) throws Exception;

    /**
     * 添加课程
     * @param course course对象
     * @return 添加是否成功
     * @throws Exception
     */
    int save(Course course) throws Exception;

    /**
     * 获取课程数目
     * @return 课程数目
     * @throws Exception
     */
    int getCountCourse() throws Exception;

    /**
     * 根据课程id查询课程
     * @param id 课程id
     * @return CourseCustom对象
     * @throws Exception
     */
    Course findById(Integer id) throws Exception;

    /**
     * 根据课程名查询课程
     * @param name 课程名
     * @return CourseCustom对象
     * @throws Exception
     */
    List<Course> findByName(String name) throws Exception;

    /**
     * 根据教师id查找课程
     * @param id 教师id
     * @return
     * @throws Exception
     */
    List<Course> findByTeacherID(Integer id) throws Exception;
}
