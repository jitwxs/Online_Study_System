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

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: jitwxs
 * Date: 2017-10-12
 * 学生Controller层
 */

@Controller
@RequestMapping(value = "/student")
public class StudentController {

    @Resource(name = "userloginServiceImpl")
    private UserloginService userloginService;

    @Resource(name = "courseServiceImpl")
    private CourseService courseService;

    @Resource(name = "studentServiceImpl")
    private StudentService studentService;

    @Resource(name = "teacherServiceImpl")
    private TeacherService teacherService;

    @Resource(name = "studentCourseServiceImpl")
    private StudentCourseService studentCourseService;

    /* ----- 普通方法区 START ----- */
    /**
     * 获取当前登陆用户名
     * @return
     */
    private String getUserName() {
        Subject subject = SecurityUtils.getSubject();
        return (String) subject.getPrincipal();
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

        StudentCourseCustom studentCourseCustom = new StudentCourseCustom();
        BeanUtils.copyProperties(studentCourse, studentCourseCustom);
        studentCourseCustom.setCourse(course);
        studentCourseCustom.setTeacherName(teacherName);


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

    /* ----- 普通方法区 END ----- */

    /* ----- 所有课程区 START ----- */
    /**
     * 显示所有课程
     * @param model Model对象
     * @param page 页码
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/showCourse")
    public String stuCourseShow(Model model, Integer page) throws Exception {

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

        List<CourseCustom> lists = getCourseCustomList(list);
        model.addAttribute("courseList", lists);
        model.addAttribute("pagingVO", pagingVO);

        return "student/showCourse";
    }

    @RequestMapping(value = "/findCourse")
    public String findCourse(String name, Model model) throws Exception {
        List<Course> list = courseService.findByName(name);
        List<CourseCustom> lists = getCourseCustomList(list);
        model.addAttribute("courseList", lists);
        return "student/showCourse";
    }

    /**
     * 选课操作
     * @param id 课程id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/stuSelectedCourse")
    public String stuSelectedCourse(int id) throws Exception {
        int stuId = getUserId();
        StudentCourse s = studentCourseService.findOne(id,stuId);

        //是否选过逻辑判断
        if (s == null) {
            //获取当前用户余额
            Student student = studentService.findById(stuId);
            int balance = student.getBalance();

            //获取当前课程售价
            Course course = courseService.findById(id);
            int price = course.getPrice();

            if(price > balance){
                throw new CustomException("当前账户余额不足，无法选课，请充值！");
            }else{
                //更新余额
                balance -= price;
                //只有在余额发生改变的情况下，才更新余额信息
                if(balance != student.getBalance()){
                    student.setBalance(balance);
                    studentService.updataById(student);
                }

                //保存选课信息
                StudentCourse studentCourse = new StudentCourse();
                studentCourse.setCourseId(id);
                studentCourse.setStudentId(stuId);
                studentCourseService.save(studentCourse);
            }
        } else {
            throw new CustomException("该门课程已经选过或修过！");
        }

        return "redirect:/student/selectedCourse";
    }

    /* ----- 所有课程区 END ----- */

    /* ----- 已选课程区 START ----- */
    /**
     * 显示所有已选课程
     * @param model Model对象
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selectedCourse")
    public String selectedCourse(Model model) throws Exception {
        int stuId = getUserId();

        try{
            List<StudentCourse> list = studentCourseService.findByStudentID(stuId, false);

            List<StudentCourseCustom> lists = getStudentCourseCustomList(list);

            model.addAttribute("StudentCourseCustomList", lists);

            return "student/selectCourse";
        }
        catch (Exception e){
            throw  new CustomException("未获取到已选课程！");
        }
    }

    /**
     * 课程学习操作
     * @param id 课程id
     * @param model Model对象
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/startStudy")
    public String startStudy(int id,Model model) throws Exception {
        int stuId = getUserId();

        StudentCourse studentCourse = studentCourseService.findOne(id, stuId);

        StudentCourseCustom studentCourseCustom = getStudentCourseCustom(studentCourse);

        model.addAttribute("studentCourseCustom", studentCourseCustom);
        return "/student/study";
    }

    /**
     * 课程学习中进入上一学时
     * @param id 课程id
     * @param model Model对象
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/prePeriod")
    public String changePeriod(int id,Model model) throws Exception {
        int stuId = getUserId();

        //得到当前学时数
        StudentCourse studentCourse = studentCourseService.findOne(id,stuId);
        int currentPeriod = studentCourse.getProgress();

        //如果当前学时大于1，进入上一学时
        if(currentPeriod > 0){
            currentPeriod -= 1;
            studentCourse.setProgress(currentPeriod);
            studentCourseService.update(studentCourse);
        }

        StudentCourseCustom studentCourseCustom = getStudentCourseCustom(studentCourse);
        model.addAttribute("studentCourseCustom", studentCourseCustom);

        return "/student/study";
    }

    /**
     * 课程学习中进入下一学时
     * @param id 课程id
     * @param model Model对象
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/backPeriod")
    public String backPeriod(int id,Model model) throws Exception {
        int stuId = getUserId();
        //得到当前学时数
        StudentCourse studentCourse = studentCourseService.findOne(id,stuId);
        int currentPeriod = studentCourse.getProgress();

        //得到所有学时数
        Course course = courseService.findById(id);
        int totalPeriod = course.getPeriod();

        //如果当前学时小于总学时数，进入下一学时
        if(currentPeriod < totalPeriod){
            currentPeriod += 1;
            studentCourse.setProgress(currentPeriod);
            studentCourseService.update(studentCourse);
        }

        StudentCourseCustom studentCourseCustom = getStudentCourseCustom(studentCourse);
        model.addAttribute("studentCourseCustom", studentCourseCustom);

        return "/student/study";
    }

    /**
     * 进入考试操作
     * @param id 课程id
     * @param model Model对象
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/startExam")
    public String startExam(int id, Model model) throws Exception {
        int stuId = getUserId();
        StudentCourse studentCourse = studentCourseService.findOne(id,stuId);

        int process = studentCourse.getProgress();

        //得到所有学时数
        Course course = courseService.findById(id);
        int period = course.getPeriod();

        if(process != period) {
            throw new CustomException("请先完成全部学时学习！");
        }else if (studentCourse.getHasExam() == true) {
            throw  new CustomException("您已参加过考试！");
        }

        StudentCourseCustom studentCourseCustom = getStudentCourseCustom(studentCourse);

        model.addAttribute("studentCourseCustom", studentCourseCustom);

        return "/student/exam";
    }


    /**
     * 保存考试信息
     * @param id 课程id
     * @param model Model对象
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveExam")
    public String saveExam(int id, Model model) throws Exception {
        int stuId = getUserId();
        StudentCourse studentCourse = studentCourseService.findOne(id,stuId);

        //设置是否考过试为true
        studentCourse.setHasExam(true);
        studentCourseService.update(studentCourse);

        List<StudentCourse> list = studentCourseService.findByStudentID(stuId);
        List<StudentCourseCustom> lists = getStudentCourseCustomList(list);

        model.addAttribute("StudentCourseCustomList", lists);

        return "/student/selectCourse";
    }

    /**
     * 退课操作
     * @param id 课程id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/outCourse")
    public String outCourse(int id) throws Exception {
        int stuId = getUserId();

        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setCourseId(id);
        studentCourse.setStudentId(stuId);

        studentCourseService.remove(studentCourse);

        return "redirect:/student/selectedCourse";
    }


    /* ----- 已选课程区 END ----- */

    /* ----- 已修课程区 START ----- */
    /**
     * 显示所有已修课程
     * @param model Model对象
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/overCourse")
    public String overCourse(Model model) throws Exception {
        int stuId = getUserId();

        try{
            List<StudentCourse> list = studentCourseService.findByStudentID(stuId, true);

            List<StudentCourseCustom> lists = getStudentCourseCustomList(list);

            model.addAttribute("StudentCourseCustomList", lists);
        }
        catch(Exception e) {
            throw new CustomException("未获取到已修课程！");
        }

        return "student/overCourse";
    }

    /* ----- 已修课程区 END ----- */

    /* ----- 其他区 START ----- */
    /**
     * 退出登陆
     * @return
     */
    @RequestMapping(value = "/logout")
    public String logout() {
        return "redirect:/logout";
    }

    /**
     * 显示个人中心
     * @param model Model对象
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/showSelf")
    public String showSelf(Model model) throws Exception {
        Student student = studentService.findById(getUserId());

        model.addAttribute("student", student);
        return "student/showSelf";
    }

    /**
     * 显示充值中心
     * @param model Model对象
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/recharge")
    public String recharge(Model model) throws Exception {
        Student student = studentService.findById(getUserId());
        model.addAttribute("student", student);
        return "student/recharge";
    }

    /**
     * 更新账户余额
     * @param money 修改的金额数值
     * @param model Model对象
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateBalance")
    public String updateBalance(Integer money, Model model) throws Exception {
        Student student = studentService.findById(getUserId());

        if (student != null) {
            student.setBalance(student.getBalance() + money);
        }
        studentService.updataById(student);

        model.addAttribute("student", student);
        return "student/recharge";
    }


    /**
     * 显示修改密码
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/passwordRest")
    public String passwordRest() throws Exception {
        return "student/passwordRest";
    }
    /* ----- 其他区 END ----- */
}
