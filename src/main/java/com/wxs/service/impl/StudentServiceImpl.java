package com.wxs.service.impl;


import com.wxs.mapper.StudentCourseMapper;
import com.wxs.mapper.StudentMapper;
import com.wxs.po.*;
import com.wxs.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: jitwxs
 * Date: 2017-10-10
 * 学生Service层实现类
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentCourseMapper studentCourseMapper;

    @Override
    public void updataById(Student student) throws Exception {
        studentMapper.updateByPrimaryKey(student);
    }

    /**
     * 删除学生信息
     * @param id 学生id
     * @return 删除是否成功
     * @throws Exception
     */
    @Override
    public Boolean removeById(Integer id) throws Exception {
        //自定义查询条件
        StudentCourseExample example = new StudentCourseExample();
        StudentCourseExample.Criteria criteria = example.createCriteria();
        criteria.andStudentIdEqualTo(id);
        List<StudentCourse> list = studentCourseMapper.selectByExample(example);

        //如果selectedcourse表没有约束，直接删除
        if (list.size() == 0) {
            studentMapper.deleteByPrimaryKey(id);
            return true;
        }
        return false;

    }

    /**
     * 分页查询学生信息
     * @param toPageNo 页码
     * @return StudentCustom列表
     * @throws Exception
     */
    @Override
    public List<Student> findByPaging(Integer toPageNo) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);

        List<Student> list = studentMapper.findByPaging(pagingVO);

        return list;
    }

    @Override
    public int save(Student student) throws Exception {
        int i = studentMapper.insert(student);
        return i;
    }

    /**
     * 获取学生人数
     * @return 学生人数
     * @throws Exception
     */
    @Override
    public int getCountStudent() throws Exception {
        //自定义查询对象
        StudentExample studentExample = new StudentExample();
        //通过criteria构造查询条件
        StudentExample.Criteria criteria = studentExample.createCriteria();
        criteria.andIdIsNotNull();

        return studentMapper.countByExample(studentExample);
    }

    /**
     * 查询学生信息
     * @param id 学生id
     * @return StudentCustom对象
     * @throws Exception
     */
    @Override
    public Student findById(Integer id) throws Exception {

        Student student  = studentMapper.selectByPrimaryKey(id);

        return student;
    }

    /**
     * 根据名字查询学生信息
     * @param name
     * @return StudentCustom列表
     * @throws Exception
     */
    @Override
    public List<Student> findByName(String name) throws Exception {

        StudentExample studentExample = new StudentExample();
        //自定义查询条件
        StudentExample.Criteria criteria = studentExample.createCriteria();

        criteria.andNameLike("%" + name + "%");

        List<Student> list = studentMapper.selectByExample(studentExample);

        return list;
    }

}
