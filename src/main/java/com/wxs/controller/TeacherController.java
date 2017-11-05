package com.wxs.controller;

import com.wxs.exception.CustomException;
import com.wxs.po.*;
import com.wxs.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: jitwxs
 * Date: 2017-10-10
 * 老师Controller层
 */

@Controller
@RequestMapping(value = "/teacher")
public class TeacherController {

    @Resource(name = "userloginServiceImpl")
    private UserloginService userloginService;

    @Resource(name = "teacherServiceImpl")
    private TeacherService teacherService;

    @Resource(name = "studentServiceImpl")
    private StudentService studentService;

    @Resource(name = "courseServiceImpl")
    private CourseService courseService;

    @Resource(name = "studentCourseServiceImpl")
    private StudentCourseService studentCourseService;

    /**
     * 获取当前用户名
     * @return 用户名
     */
    private String getUserName() {
        Subject subject = SecurityUtils.getSubject();
        String userName = (String) subject.getPrincipal();
        return userName;
    }

    /**
     * 获取当前登陆用户名的id
     * @return
     */
    private Integer getUserId() throws Exception{
        Userlogin userlogin = userloginService.findByName(getUserName());
        return userlogin.getId();
    }

    /**
     * 获取CourseCustom
     * @param course
     * @return
     * @throws Exception
     */
    private CourseCustom getCourseCustom(Course course) throws Exception{
        CourseCustom courseCustom = new CourseCustom();
        BeanUtils.copyProperties(course, courseCustom);
        Teacher teacher = teacherService.findById(course.getTeacherId());
        courseCustom.setTeacherName(teacher.getName());

        return courseCustom;
    }

    /**
     * 获取CourseCustom列表
     * @param list
     * @return
     * @throws Exception
     */
    private List<CourseCustom> getCourseCustomList(List<Course> list) throws Exception{
        List<CourseCustom> lists = new ArrayList<CourseCustom>();
        for (Course course: list) {
            lists.add(getCourseCustom(course));
        }
        return lists;
    }

    /**
     * 获取StudentCourseCustom
     * @param studentCourse
     * @return
     * @throws Exception
     */
    private StudentCourseCustom getStudentCourseCustom(StudentCourse studentCourse) throws Exception{
        Course course = courseService.findById(studentCourse.getCourseId());
        String teacherName = teacherService.findById(course.getTeacherId()).getName();
        String studentName = studentService.findById(studentCourse.getStudentId()).getName();

        StudentCourseCustom studentCourseCustom = new StudentCourseCustom();
        BeanUtils.copyProperties(studentCourse, studentCourseCustom);
        studentCourseCustom.setCourse(course);
        studentCourseCustom.setTeacherName(teacherName);
        studentCourseCustom.setStudentName(studentName);

        return studentCourseCustom;
    }

    /**
     * 获取StudentCourseCustom列表
     * @param list
     * @return
     * @throws Exception
     */
    private List<StudentCourseCustom> getStudentCourseCustomList(List<StudentCourse> list) throws Exception{
        List<StudentCourseCustom> lists = new ArrayList<StudentCourseCustom>();
        for (StudentCourse studentCourse: list) {
            lists.add(getStudentCourseCustom(studentCourse));
        }
        return lists;
    }

    /**
     * 退出登陆
     * @return
     */
    @RequestMapping(value = "/logout")
    public String logout(){
        return "redirect:/logout";
    }

    /**
     * 个人中心页面
     * @param model Model对象
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/showSelf")
    public String showSelf (Model model) throws Exception {
        Teacher teacher = teacherService.findById(getUserId());

        model.addAttribute("teacher", teacher);
        return "teacher/showSelf";
    }

    /**
     * 显示我的课程页面
     * @param model Model对象
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/showCourse")
    public String stuCourseShow(Model model) throws Exception {

        List<Course> list = courseService.findByTeacherID(getUserId());

        model.addAttribute("courseList", list);
        return "teacher/showCourse";
    }

    @RequestMapping(value = "/findCourse" ,method = {RequestMethod.POST})
    public String findCourse(String name, Model model) throws Exception {

        List<Course> list = courseService.findByName(name);

        model.addAttribute("courseList", list);
        return "teacher/showCourse";
    }

    /**
     * 查看课程成绩
     * @param id 课程id
     * @param model Model对象
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/gradeCourse")
    public String gradeCourse(Integer id, Model model) throws Exception {
        if (id == null) {
            return "";
        }
        List<StudentCourse> list = studentCourseService.findByCourseID(id);

        List<StudentCourseCustom> lists = getStudentCourseCustomList(list);

        model.addAttribute("StudentCourseCustomList", lists);
        return "teacher/showGrade";
    }

    /**
     * 打分UI页面
     * @param studentId
     * @param courseId
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mark", method = {RequestMethod.GET})
    public String markUI(Integer studentId, Integer courseId, Model model) throws Exception {

        StudentCourse studentCourse = studentCourseService.findOne(courseId, studentId);

        if(studentCourse.getHasExam() == false) {
            throw new CustomException("该学生未参加考试，无法评分！");
        }

        StudentCourseCustom studentCourseCustom = getStudentCourseCustom(studentCourse);
        model.addAttribute("studentCourseCustom", studentCourseCustom);

        return "teacher/mark";
    }

    /**
     * 打分处理函数
     * @param sc SelectedCourseCustom对象
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveMark", method = {RequestMethod.POST})
    public String mark(Integer courseId, Integer studentId, StudentCourse sc) throws Exception {
        StudentCourse studentCourse = studentCourseService.findOne(courseId, studentId);
        studentCourse.setMark(sc.getMark());
        studentCourseService.update(studentCourse);

        return "redirect:/teacher/gradeCourse?id="+sc.getCourseId();
    }

    /**
     * 修改密码
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/passwordRest")
    public String passwordRest() throws Exception {
        return "teacher/passwordRest";
    }

}
