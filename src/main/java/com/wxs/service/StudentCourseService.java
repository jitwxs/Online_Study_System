package com.wxs.service;

import com.wxs.po.StudentCourse;

import java.util.List;

/**
 * Author: jitwxs
 * Date: 2017-10-10
 * 选课Service层
 */
public interface StudentCourseService {

    /**
     * 根据课程ID查询课程
     * @param id 课程id
     * @return SelectedCourseCustom列表
     * @throws Exception
     */
    List<StudentCourse> findByCourseID(Integer id) throws Exception;

    /**
     * 根据学生id查找课程
     * @param id 学生id
     * @return SelectedCourseCustom列表
     * @throws Exception
     */
    List<StudentCourse> findByStudentID(Integer id) throws Exception;

    /**
     * 根据学生id查找课程
     * @param id 学生id
     * @return SelectedCourseCustom列表
     * @throws Exception
     */
    List<StudentCourse> findByStudentID(Integer id,Boolean hasExam) throws Exception;

    /**
     * 获取课程学生人数
     * @param id 课程id
     * @return 学生人数
     * @throws Exception
     */
    Integer countByCourseID(Integer id) throws Exception;

    /**
     * 查询单条选课信息
     * @param courseId 课程id
     * @param studentId 学生id
     * @return SelectedCourseCustom对象
     * @throws Exception
     */
    StudentCourse findOne(Integer courseId, Integer studentId) throws Exception;

    /**
     * 更新选课信息
     * @param studentCourse studentCourse对象
     * @throws Exception
     */
    void update(StudentCourse studentCourse) throws Exception;

    /**
     * 保存选课信息
     * @param studentCourse studentCourse对象
     * @throws Exception
     */
    int save(StudentCourse studentCourse) throws Exception;

    /**
     * 删除选课信息
     * @param studentCourse studentCourse对象
     * @throws Exception
     */
    void remove(StudentCourse studentCourse) throws Exception;
}
