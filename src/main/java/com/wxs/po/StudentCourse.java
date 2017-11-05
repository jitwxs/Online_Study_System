package com.wxs.po;

public class StudentCourse {
    private Integer courseId;

    private Integer studentId;

    private Integer progress;

    private Integer mark;

    private Boolean hasExam;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Boolean getHasExam() {
        return hasExam;
    }

    public void setHasExam(Boolean hasExam) {
        this.hasExam = hasExam;
    }
}