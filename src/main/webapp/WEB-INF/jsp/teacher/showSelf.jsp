<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/taglib.jsp" %>

<html>
<head>
    <title>个人中心</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入bootstrap -->
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <!-- 引入JQuery  bootstrap.js-->
    <script src="${ctx}/js/jquery-3.2.1.min.js"></script>
    <script src="${ctx}/js/bootstrap.min.js"></script>

</head>

<body>
<!-- 顶栏 -->
<jsp:include page="top.jsp"/>
<!-- 中间主体 -->
<div class="container" id="content">
    <div class="row">
        <jsp:include page="menu.jsp"/>
        <div class="col-md-10">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <h1 style="text-align: center;">个人信息</h1>
                    </div>
                </div>

                <div class="panel-body">
                    <input type="hidden" name="id" value="${teacher.id}"/>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">姓名</label>
                        <div class="col-sm-10">
                            <input readonly="readonly" type="text" class="form-control"name="name" value="${teacher.name}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">性别</label>
                        <div class="col-sm-10">
                            <input readonly="readonly" type="text" class="form-control" name="sex" value="${teacher.sex}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">学历</label>
                        <div class="col-sm-10">
                            <input readonly="readonly" type="text" class="form-control" name="degree" value="${teacher.degree}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">出生日期</label>
                        <div class="col-sm-10">
                            <input readonly="readonly" type="date" class="form-control" name="birthday" value="<fmt:formatDate value="${teacher.birthday}" dateStyle="medium" pattern="yyyy-MM-dd" />"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" >注册日期</label>
                        <div class="col-sm-10">
                            <input readonly="readonly" type="date" class="form-control" name="registerTime" value="<fmt:formatDate value="${teacher.registerTime}" dateStyle="medium" pattern="yyyy-MM-dd" />"/>
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
