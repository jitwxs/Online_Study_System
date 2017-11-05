package com.wxs.po;

/**
 * Author: jitwxs
 * Date: 2017-10-09
 * 课程扩展类
 */
public class CourseCustom extends Course {

    /**
     * 授课老师名称
     */
    private String teacherName;

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherName() {
        return teacherName;
    }

}
