<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/taglib.jsp" %>

<div class="col-md-2">
    <ul class="nav nav-pills nav-stacked" id="nav">
        <li><a href="${ctx}/admin/showCourse">课程管理<span class="glyphicon glyphicon-book pull-right"/></a></li>
        <li><a href="${ctx}/admin/showStudent">学生管理<span class="glyphicon glyphicon-user pull-right"/></a></li>
        <li><a href="${ctx}/admin/showTeacher">教师管理<span class="glyphicon glyphicon-user pull-right"/></a></li>
        <li><a href="${ctx}/admin/userPasswordRest">重置密码<sapn class="glyphicon glyphicon-repeat pull-right" /></a></li>
        <li><a href="${ctx}/admin/passwordRest">修改密码<sapn class="glyphicon glyphicon-pencil pull-right" /></a></li>
        <li><a href="${ctx}/admin/logout">退出系统<sapn class="glyphicon glyphicon-log-out pull-right" /></a></li>
    </ul>
</div>