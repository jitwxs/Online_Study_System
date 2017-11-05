package com.wxs.service.impl;

import com.wxs.mapper.StudentCourseMapper;
import com.wxs.po.StudentCourse;
import com.wxs.po.StudentCourseExample;
import com.wxs.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: jitwxs
 * Date: 2017-10-10
 * 选课Service层实现类
 */
@Service
public class StudentCourseServiceImpl implements StudentCourseService {

    @Autowired
    private StudentCourseMapper studentCourseMapper;

    /**
     * 根据课程ID查询课程
     * @param id 课程id
     * @return
     * @throws Exception
     */
    @Override
    public List<StudentCourse> findByCourseID(Integer id) throws Exception {

        StudentCourseExample example = new StudentCourseExample();
        StudentCourseExample.Criteria criteria = example.createCriteria();
        criteria.andCourseIdEqualTo(id);

        List<StudentCourse> list = studentCourseMapper.selectByExample(example);

        return list;
    }

    /**
     * 获取课程学生人数
     * @param id 课程id
     * @return 学生人数
     * @throws Exception
     */
    @Override
    public Integer countByCourseID(Integer id) throws Exception {
        StudentCourseExample example = new StudentCourseExample();
        StudentCourseExample.Criteria criteria = example.createCriteria();
        criteria.andCourseIdEqualTo(id);

        return studentCourseMapper.countByExample(example);
    }

    /**
     * 查询单条选课信息
     * @param courseId 课程id
     * @param studentId 学生id
     * @return SelectedCourseCustom对象
     * @throws Exception
     */
    @Override
    public StudentCourse findOne(Integer courseId, Integer studentId) throws Exception {

        StudentCourseExample example = new StudentCourseExample();
        StudentCourseExample.Criteria criteria = example.createCriteria();

        criteria.andCourseIdEqualTo(courseId);
        criteria.andStudentIdEqualTo(studentId);

        List<StudentCourse> list = studentCourseMapper.selectByExample(example);

        if (list.size() > 0) {
            return list.get(0);
        }

        return null;
    }

    /**
     * 更新选课信息
     * @param studentCourse studentCourse对象
     * @throws Exception
     */
    @Override
    public void update(StudentCourse studentCourse) throws Exception {
        StudentCourseExample example = new StudentCourseExample();
        StudentCourseExample.Criteria criteria = example.createCriteria();

        criteria.andCourseIdEqualTo(studentCourse.getCourseId());
        criteria.andStudentIdEqualTo(studentCourse.getStudentId());

        studentCourseMapper.updateByExample(studentCourse, example);

    }

    @Override
    public int save(StudentCourse studentCourse) throws Exception {
        if(studentCourse.getProgress() == null) {
            studentCourse.setProgress(0);
        }
        if(studentCourse.getHasExam() == null) {
            studentCourse.setHasExam(false);
        }
        int i = studentCourseMapper.insert(studentCourse);
        return i;
    }

    /**
     * 根据学生id查找课程
     * @param id 学生id
     * @return SelectedCourseCustom列表
     * @throws Exception
     */
    @Override
    public List<StudentCourse> findByStudentID(Integer id) throws Exception {
        StudentCourseExample example = new StudentCourseExample();
        StudentCourseExample.Criteria criteria = example.createCriteria();
        criteria.andStudentIdEqualTo(id);

        List<StudentCourse> list = studentCourseMapper.selectByExample(example);

        return list;
    }

    /**
     * 根据学生id查找课程
     * @param id 学生id
     * @return SelectedCourseCustom列表
     * @throws Exception
     */
    @Override
    public List<StudentCourse> findByStudentID(Integer id, Boolean hasExam) throws Exception {
        StudentCourseExample example = new StudentCourseExample();
        StudentCourseExample.Criteria criteria = example.createCriteria();
        criteria.andStudentIdEqualTo(id);
        criteria.andHasExamEqualTo(hasExam);

        List<StudentCourse> list = studentCourseMapper.selectByExample(example);

        return list;
    }


    /**
     * 删除选课信息
     * @param studentCourse studentCourse对象
     * @throws Exception
     */
    @Override
    public void remove(StudentCourse studentCourse) throws Exception {
        StudentCourseExample example = new StudentCourseExample();
        StudentCourseExample.Criteria criteria = example.createCriteria();

        criteria.andCourseIdEqualTo(studentCourse.getCourseId());
        criteria.andStudentIdEqualTo(studentCourse.getStudentId());

        studentCourseMapper.deleteByExample(example);
    }

}
