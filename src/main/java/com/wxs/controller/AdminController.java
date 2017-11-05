package com.wxs.controller;

import com.wxs.controller.common.GlobalConstant;
import com.wxs.controller.common.SHA1Utils;
import com.wxs.exception.CustomException;
import com.wxs.po.*;
import com.wxs.service.*;
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
 * Date: 2017-11-3
 * 管理员Controller层
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource(name = "studentServiceImpl")
    private StudentService studentService;

    @Resource(name = "teacherServiceImpl")
    private TeacherService teacherService;

    @Resource(name = "courseServiceImpl")
    private CourseService courseService;

    @Resource(name = "studentCourseServiceImpl")
    private StudentCourseService studentCourseService;

    @Resource(name = "userloginServiceImpl")
    private UserloginService userloginService;

    /* ----- 普通方法区 START ----- */

    /**
     * List<Course>转List<CourseCustom>
     * @param courseList
     * @return
     * @throws Exception
     */
    List<CourseCustom> getCourseCustomList(List<Course> courseList) throws Exception{
        List<CourseCustom> list = new ArrayList<CourseCustom>();

        for (Course course : courseList) {
            CourseCustom courseCustom = new CourseCustom();
            BeanUtils.copyProperties(course,courseCustom);

            Integer teacherId = course.getTeacherId();

            if(teacherId != null) {
                Teacher teacher = teacherService.findById(teacherId);
                String teacherName = teacher.getName();
                courseCustom.setTeacherName(teacherName);
            } else {
                courseCustom.setTeacherName("");
            }

            list.add(courseCustom);
        }
        return list;
    }

    /**
     * Course转CourseCustom
     * @param course
     * @return
     * @throws Exception
     */
    CourseCustom getCourseCustom(Course course) throws Exception{
        CourseCustom courseCustom = new CourseCustom();
        BeanUtils.copyProperties(course,courseCustom);

        Integer teacherId = course.getTeacherId();

        if(teacherId != null) {
            Teacher teacher = teacherService.findById(teacherId);
            String teacherName = teacher.getName();
            courseCustom.setTeacherName(teacherName);
        } else {
            courseCustom.setTeacherName("");
        }
        return courseCustom;
    }

    /* ----- 普通方法区 END ----- */


    /* ----- 课程管理区 START ----- */

    @RequestMapping("/showCourse")
    public String showCourse(Model model, Integer page) throws Exception {

        List<Course> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(courseService.getCountCourse());
        if (page == null || page == 0) {
            pagingVO.setToPageNo(1);
            list = courseService.findByPaging(1);
        } else {
            pagingVO.setToPageNo(page);
            list = courseService.findByPaging(page);
        }

        List<CourseCustom> courseCustomList = getCourseCustomList(list);

        model.addAttribute("courseCustomList", courseCustomList);
        model.addAttribute("pagingVO", pagingVO);

        return "admin/showCourse";

    }

    @RequestMapping(value = "/editCourse", method = {RequestMethod.GET})
    public String editCourseUI(Integer id, Model model) throws Exception {
        if (id == null) {
            return "redirect:/admin/showCourse";
        }
        Course course = courseService.findById(id);
        if (course == null) {
            throw new CustomException("未找到该课程");
        }
        List<Teacher> list = teacherService.findAll();

        model.addAttribute("teacherList", list);
        model.addAttribute("course", course);

        return "admin/editCourse";
    }

    @RequestMapping(value = "/editCourse", method = {RequestMethod.POST})
    public String editCourse(Course course) throws Exception {

        courseService.upadteById(course);

        return "redirect:/admin/showCourse";
    }

    @RequestMapping("/removeCourse")
    public String removeCourse(Integer id) throws Exception {
        if (id == null) {
            return "admin/showCourse";
        }

        boolean flag = courseService.removeById(id);

        //删除失败，说明selectCourse表中存在关联数据,先删除关联信息
        while(flag == false) {
            List<StudentCourse> lists = studentCourseService.findByCourseID(id);
            for (StudentCourse studentCourse: lists) {
                studentCourseService.remove(studentCourse);
            }
            flag = courseService.removeById(id);
        }

        return "redirect:/admin/showCourse";
    }

    @RequestMapping(value = "/selectCourse", method = {RequestMethod.POST})
    public String selectCourse(String name, Model model) throws Exception {

        List<Course> list = courseService.findByName(name);

        List<CourseCustom> courseCustomList = getCourseCustomList(list);

        model.addAttribute("courseCustomList", courseCustomList);

        return "admin/showCourse";
    }

    @RequestMapping(value = "/addCourse", method = {RequestMethod.GET})
    public String addCourseUI(Model model) throws Exception {

        List<Teacher> list = teacherService.findAll();

        model.addAttribute("teacherList", list);

        return "admin/addCourse";
    }

    @RequestMapping(value = "/addCourse", method = {RequestMethod.POST})
    public String addCourse(Course course) throws Exception {

        courseService.save(course);

        return "redirect:/admin/showCourse";
    }

    /* ----- 课程管理区 END ----- */


    /* ----- 学生管理区 START ----- */

    @RequestMapping("/showStudent")
    public String showStudent(Model model, Integer page) throws Exception {
        List<Student> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(studentService.getCountStudent());
        if (page == null || page == 0) {
            pagingVO.setToPageNo(1);
            list = studentService.findByPaging(1);
        } else {
            pagingVO.setToPageNo(page);
            list = studentService.findByPaging(page);
        }

        model.addAttribute("studentList", list);
        model.addAttribute("pagingVO", pagingVO);

        return "admin/showStudent";

    }

    @RequestMapping(value = "/addStudent", method = {RequestMethod.GET})
    public String addStudentUI() throws Exception {
        return "admin/addStudent";
    }

    @RequestMapping(value = "/addStudent", method = {RequestMethod.POST})
    public String addStudent(Student student) throws Exception {
        Userlogin userlogin = null;
        if(userlogin != null) {
            throw new CustomException("该名称已被注册,无法添加!");
        } else {
            userlogin = new Userlogin();
            userlogin.setName(student.getName());
            userlogin.setPassword(SHA1Utils.entryptPassword(GlobalConstant.DEFAULT_PASSWD));
            userlogin.setRole(GlobalConstant.ROle_Type.STUDENT.getIndex());
            userloginService.save(userlogin);

            student.setId(userlogin.getId());
            student.setBalance(GlobalConstant.DEFAULT_BALANCE);
            studentService.save(student);
        }

        return "redirect:/admin/showStudent";
    }

    @RequestMapping(value = "/editStudent", method = {RequestMethod.GET})
    public String editStudentUI(Integer id, Model model) throws Exception {
        Student student = null;

        student = studentService.findById(id);
        if(student == null) {
            throw new CustomException("该用户不存在!");
        }

        model.addAttribute("student", student);

        return "admin/editStudent";
    }

    @RequestMapping(value = "/editStudent", method = {RequestMethod.POST})
    public String editStudent(Student student) throws Exception {

        Userlogin userLogin = userloginService.findById(student.getId());
        userLogin.setName(student.getName());
        userloginService.updateById(student.getId(),userLogin);

        studentService.updataById(student);

        return "redirect:/admin/showStudent";
    }

    @RequestMapping(value = "/removeStudent", method = {RequestMethod.GET} )
    public String removeStudent(Integer id) throws Exception {
        boolean flag = studentService.removeById(id);
        //flag false 表示该学生有课程,递归删除该学生课程
        while(flag == false){
            List<StudentCourse> lists = studentCourseService.findByStudentID(id);
            for (StudentCourse studentCourse: lists) {
                studentCourseService.remove(studentCourse);
            }
            flag = studentService.removeById(id);
        }

        userloginService.removeById(id);

        return "redirect:/admin/showStudent";
    }

    @RequestMapping(value = "selectStudent", method = {RequestMethod.POST})
    public String selectStudent(String name, Model model) throws Exception {

        List<Student> list = studentService.findByName(name);

        model.addAttribute("studentList", list);
        return "admin/showStudent";
    }

    /* ----- 学生管理区 END ----- */


    /* ----- 教师管理区 START ----- */

    @RequestMapping("/showTeacher")
    public String showTeacher(Model model, Integer page) throws Exception {

        List<Teacher> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(teacherService.getCountTeacher());
        if (page == null || page == 0) {
            pagingVO.setToPageNo(1);
            list = teacherService.findByPaging(1);
        } else {
            pagingVO.setToPageNo(page);
            list = teacherService.findByPaging(page);
        }

        model.addAttribute("teacherList", list);
        model.addAttribute("pagingVO", pagingVO);

        return "admin/showTeacher";

    }

    @RequestMapping(value = "/addTeacher", method = {RequestMethod.GET})
    public String addTeacherUI() throws Exception {

        return "admin/addTeacher";
    }

    @RequestMapping(value = "/addTeacher", method = {RequestMethod.POST})
    public String addTeacher(Teacher teacher) throws Exception {
        Userlogin userlogin = null;
        userlogin = userloginService.findByName(teacher.getName());
        if(userlogin != null) {
            throw new CustomException("该名称已被注册,无法注册!");
        } else {
            userlogin = new Userlogin();
            userlogin.setName(teacher.getName());
            userlogin.setPassword(SHA1Utils.entryptPassword(GlobalConstant.DEFAULT_PASSWD));
            userlogin.setRole(GlobalConstant.ROle_Type.TEACHER.getIndex());
            userloginService.save(userlogin);

            teacher.setId(userlogin.getId());
            teacherService.save(teacher);

        }
        return "redirect:/admin/showTeacher";
    }

    @RequestMapping(value = "/editTeacher", method = {RequestMethod.GET})
    public String editTeacherUI(Integer id, Model model) throws Exception {
        Teacher teacher = teacherService.findById(id);
        if (teacher == null) {
            throw new CustomException("未找到该教师");
        }
        model.addAttribute("teacher", teacher);

        return "admin/editTeacher";
    }

    @RequestMapping(value = "/editTeacher", method = {RequestMethod.POST})
    public String editTeacher(Teacher teacher) throws Exception {
        teacherService.updateById(teacher);

        return "redirect:/admin/showTeacher";
    }

    @RequestMapping("/removeTeacher")
    public String removeTeacher(Integer id) throws Exception {
        boolean flag = teacherService.removeById(id);
        if(flag == false) {
            throw new CustomException("该老师存在相应课程,无法删除");
        }
        userloginService.removeById(id);
        return "redirect:/admin/showTeacher";
    }

    @RequestMapping(value = "selectTeacher", method = {RequestMethod.POST})
    public String selectTeacher(String name, Model model) throws Exception {

        List<Teacher> list = teacherService.findByName(name);

        model.addAttribute("teacherList", list);
        return "admin/showTeacher";
    }

    /* ----- 教师管理区 END ----- */


    /* ----- 其他区 START ----- */

    @RequestMapping(value = "/logout")
    public String logout(){
        return "redirect:/logout";
    }

     /**
     * 普通用户密码重置UI处理
     * @return
     * @throws Exception
     */
    @RequestMapping("/userPasswordRest")
    public String userPasswordRestUI() throws Exception {
        return "admin/userPasswordRest";
    }

    /**
     * 普通用户密码重置处理函数
     * @param userlogin Userlogin对象
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/userPasswordRest", method = {RequestMethod.POST})
    public String userPasswordRest(Userlogin userlogin) throws Exception {

        Userlogin u = userloginService.findByName(userlogin.getName());

        if (u != null) {
            if (u.getRole() == 0) {
                throw new CustomException("该账户为管理员账户，无法修改");
            }
            u.setPassword(SHA1Utils.entryptPassword(userlogin.getPassword()));
            userloginService.updateByName(userlogin.getName(), u);
        } else {
            throw new CustomException("未找到该用户");
        }

        return "admin/userPasswordRest";
    }

    /**
     * 重置当前账户密码
     * @return
     * @throws Exception
     */
    @RequestMapping("/passwordRest")
    public String passwordRestUI() throws Exception {
        return "admin/passwordRest";
    }

    /* ----- 其他区 END ----- */
}
