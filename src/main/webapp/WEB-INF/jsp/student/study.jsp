<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<html>
<head>
    <title>课程学习</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入bootstrap -->
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
    <!-- 引入JQuery  bootstrap.js-->
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>

</head>

<body>
<!-- 顶栏 -->
<jsp:include page="top.jsp"></jsp:include>
<!-- 中间主体 -->
<div class="container" id="content">
    <div class="row">
        <jsp:include page="menu.jsp"></jsp:include>
        <div class="col-md-10">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <h1 style="text-align: center;">课程学习</h1>
                    </div>
                </div>

                <div class="panel-body">
                    <div class="form-group ">
                        <label class="col-sm-2 control-label" >课程名</label>
                        <div class="col-sm-10">
                            <input readonly="readonly" type="text" class="form-control" name="name" value="${studentCourseCustom.course.name}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">总学时</label>
                        <div class="col-sm-10">
                            <input readonly="readonly" type="text" class="form-control" name="period" value="${studentCourseCustom.course.period}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">当前学时</label>
                        <div class="col-sm-10">
                            <input readonly="readonly" type="text" class="form-control" name="progress" value="${studentCourseCustom.progress}">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-10">
                            <h2>模拟学习部分...</h2>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-10">
                            <button class="btn btn-default btn-xs btn-info" onClick="location.href='/student/prePeriod?id=${studentCourseCustom.courseId}'">上一学时</button>
                            <button class="btn btn-default btn-xs btn-info" onClick="location.href='/student/backPeriod?id=${studentCourseCustom.courseId}'">下一学时</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="container" id="footer">
    <div class="row">
        <div class="col-md-12"></div>
    </div>
</div>

</body>

</html>
