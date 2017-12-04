<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/taglib.jsp" %>
<div class="col-md-2">
    <ul class="nav nav-pills nav-stacked" id="nav">
        <li><a href="${ctx}/student/showCourse">所有课程<span class="glyphicon glyphicon-book pull-right"/></a></li>
        <li><a href="${ctx}/student/selectedCourse">已选课程<span class="glyphicon glyphicon-book pull-right"/></a></li>
        <li><a href="${ctx}/student/overCourse">已修课程<span class="glyphicon glyphicon-book pull-right"/></a></li>
        <li><a href="${ctx}/student/passwordRest">修改密码<sapn class="glyphicon glyphicon-pencil pull-right" /></a></li>
        <li><a href="${ctx}/student/logout">退出系统<sapn class="glyphicon glyphicon-log-out pull-right" /></a></li>
    </ul>
</div>