package com.wxs.service.impl;


import com.wxs.mapper.CourseMapper;
import com.wxs.mapper.StudentCourseMapper;
import com.wxs.po.*;
import com.wxs.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: jitwxs
 * Date: 2017-11-03
 * 课程Service层实现类
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private StudentCourseMapper studentCourseMapper;

    @Override
    public void upadteById(Course course) throws Exception {
        courseMapper.updateByPrimaryKey(course);
    }

    /**
     * 删除课程
     * @param id 课程id
     * @return 删除是否成功
     * @throws Exception
     */
    @Override
    public Boolean removeById(Integer id) throws Exception {
        //自定义查询条件
        StudentCourseExample example = new StudentCourseExample();
        StudentCourseExample.Criteria criteria = example.createCriteria();
        criteria.andCourseIdEqualTo(id);
        List<StudentCourse> list = studentCourseMapper.selectByExample(example);

        //如果没有人选这门课，直接删除
        if (list.size() == 0) {
            courseMapper.deleteByPrimaryKey(id);
            return true;
        }
        return false;
    }

    /**
     * 分页查询课程
     * @param toPageNo 页码
     * @return CourseCustom列表
     * @throws Exception
     */
    @Override
    public List<Course> findByPaging(Integer toPageNo) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);

        List<Course> list = courseMapper.findByPaging(pagingVO);

        return list;
    }

    /**
     * 获取课程数目
     * @return
     * @throws Exception
     */
    @Override
    public int getCountCourse() throws Exception {
        //自定义查询对象
        CourseExample courseExample = new CourseExample();
        //通过criteria构造查询条件
        CourseExample.Criteria criteria = courseExample.createCriteria();
        criteria.andIdIsNotNull();

        return courseMapper.countByExample(courseExample);
    }

    /**
     * 根据课程id查询课程
     * @param id 课程id
     * @return CourseCustom对象
     * @throws Exception
     */
    @Override
    public Course findById(Integer id) throws Exception {
        Course course = courseMapper.selectByPrimaryKey(id);

        return course;
    }

    /**
     * 根据课程名查询课程
     * @param name 课程名
     * @return CourseCustom对象
     * @throws Exception
     */
    @Override
    public List<Course> findByName(String name) throws Exception {

        List<Course> list = courseMapper.findByName(name);

        return list;
    }

    /**
     * 根据教师id查找课程
     * @param id 教师id
     * @return
     * @throws Exception
     */
    @Override
    public List<Course> findByTeacherID(Integer id) throws Exception {
        CourseExample courseExample = new CourseExample();
        //自定义查询条件
        CourseExample.Criteria criteria = courseExample.createCriteria();
        //根据教师id查课程
        criteria.andTeacherIdEqualTo(id);

        List<Course> list = courseMapper.selectByExample(courseExample);

        return list;
    }

    /**
     * 添加课程
     * @param course course对象
     * @return 添加是否成功
     * @throws Exception
     */
    @Override
    public int save(Course course) throws Exception {
        int i = courseMapper.insert(course);
        return i;
    }
}
