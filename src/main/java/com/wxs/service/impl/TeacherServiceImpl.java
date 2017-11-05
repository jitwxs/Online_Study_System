package com.wxs.service.impl;


import com.wxs.exception.CustomException;
import com.wxs.mapper.CourseMapper;
import com.wxs.mapper.TeacherMapper;
import com.wxs.po.*;
import com.wxs.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: jitwxs
 * Date: 2017-10-10
 * 教师Service层实现类
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public void updateById(Teacher teacher) throws Exception {
        teacherMapper.updateByPrimaryKey(teacher);
    }

    /**
     * 删除教师信息
     * @param id 教师id
     * @throws Exception
     */
    @Override
    public boolean removeById(Integer id) throws Exception {
        CourseExample courseExample = new CourseExample();

        CourseExample.Criteria criteria = courseExample.createCriteria();
        criteria.andTeacherIdEqualTo(id);
        List<Course> list = courseMapper.selectByExample(courseExample);

        if (list.size() == 0) {
            teacherMapper.deleteByPrimaryKey(id);
            return  true;
        }
        return false;
    }

    /**
     * 分页查询教师信息
     * @param toPageNo 页码
     * @return TeacherCustom列表
     * @throws Exception
     */
    @Override
    public List<Teacher> findByPaging(Integer toPageNo) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);

        List<Teacher> list = teacherMapper.findByPaging(pagingVO);

        return list;
    }

    @Override
    public int save(Teacher teacher) throws Exception {

        int i = teacherMapper.insert(teacher);
        return i;
    }

    /**
     * 获取教师人数
     * @return 教师人数
     * @throws Exception
     */
    @Override
    public int getCountTeacher() throws Exception {
        //自定义查询对象
        TeacherExample teacherExample = new TeacherExample();
        //通过criteria构造查询条件
        TeacherExample.Criteria criteria = teacherExample.createCriteria();
        criteria.andIdIsNotNull();

        return teacherMapper.countByExample(teacherExample);
    }

    @Override
    public Teacher findById(Integer id) throws Exception {
        Teacher teacher = teacherMapper.selectByPrimaryKey(id);
        return teacher;
    }

    /**
     * 根据教师名称查询教师
     * @param name 教师名称
     * @return TeacherCustom对象
     * @throws Exception
     */
    @Override
    public List<Teacher> findByName(String name) throws Exception {
        TeacherExample teacherExample = new TeacherExample();
        //自定义查询条件
        TeacherExample.Criteria criteria = teacherExample.createCriteria();

        criteria.andNameLike("%" + name + "%");

        List<Teacher> list = teacherMapper.selectByExample(teacherExample);

        return list;
    }

    /**
     * 查询所有教师
     * @return TeacherCustom列表
     * @throws Exception
     */
    @Override
    public List<Teacher> findAll() throws Exception {

        TeacherExample teacherExample = new TeacherExample();
        TeacherExample.Criteria criteria = teacherExample.createCriteria();

        criteria.andIdIsNotNull();

        List<Teacher> list = teacherMapper.selectByExample(teacherExample);

        return list;
    }
}
