<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/taglib.jsp" %>

<html>
<head>
    <title>充值中心</title>

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
                        <h1 style="text-align: center;">氪金中心</h1>
                    </div>
                </div>

                <div class="panel-body">
                    <div class="form-group ">
                        <label class="col-sm-2 control-label" >账户余额</label>
                        <div class="col-sm-10">
                            <input readonly="readonly" type="text" class="form-control" name="balance" value="${student.balance}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" >充值金额</label>
                        <form action="${ctx}/student/updateBalance" method="post" id="rechargeForm">
                            <input type="hidden" name="id" value="${student.id}">
                            <label class="checkbox-inline">
                                <input type="radio" name="money" value="10" checked>10
                            </label>
                            <label class="checkbox-inline">
                                <input type="radio" name="money" value="20">20
                            </label>
                            <label class="checkbox-inline">
                                <input type="radio" name="money" value="50">50
                            </label>
                            <label class="checkbox-inline">
                                <input type="radio" name="money" value="100">100
                            </label>
                        </form>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-10">
                            <button type="submit" form="rechargeForm" class="btn btn-default btn-xs btn-info" value="Submit">立即氪金</button>
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
