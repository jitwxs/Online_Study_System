<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<title></title>

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
					    	<h1 style="text-align: center;">修改教师信息</h1>
						</div>
				    </div>
				    <div class="panel-body">
						<form class="form-horizontal" role="form" action="${ctx}/admin/editTeacher" id="editfrom" method="post">
							<input type="hidden" name="id" value="${teacher.id}"/>
							  <div class="form-group">
							    <label class="col-sm-2 control-label">姓名</label>
							    <div class="col-sm-10">
							      <input type="text" readonly="true" class="form-control" name="name" value="${teacher.name}">
							    </div>
							  </div>
							  <div class="form-group">
							    <label class="col-sm-2 control-label">性别</label>
							    <div class="col-sm-10">
								    <label class="checkbox-inline">
									   	<input type="radio" name="sex" value="男" checked>男
									</label>
									<label class="checkbox-inline">
										<input type="radio" name="sex" value="女">女
									</label>
							    </div>
							  </div>
							  <div class="form-group">
							    <label class="col-sm-2 control-label">出生日期</label>
							    <div class="col-sm-10">
								    <input type="date" class="form-control" value="<fmt:formatDate value="${teacher.birthday}" dateStyle="medium" pattern="yyyy-MM-dd" />" name="birthday"/>
							    </div>
							  </div>
							  <div class="form-group">
								<label class="col-sm-2 control-label" >学历：</label>
								<div class="col-sm-10">
									<select class="form-control" name="degree" id="degree">
										<option value="本科">本科</option>
										<option value="硕士">硕士</option>
										<option value="博士">博士</option>
									</select>
								</div>
							  </div>
							  <div class="form-group">
							    <label class="col-sm-2 control-label">注册日期</label>
							    <div class="col-sm-10">
								    <input type="date" class="form-control" readonly="true" value="<fmt:formatDate value="${teacher.registerTime}" dateStyle="medium" pattern="yyyy-MM-dd" />" name="registerTime"/>
							    </div>
							  </div>
							  <div class="form-group" style="text-align: center">
								<button class="btn btn-default" type="submit">提交</button>
								<button class="btn btn-default" type="reset">重置</button>
							  </div>
						</form>
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
	<script type="text/javascript">
		$("#nav li:nth-child(3)").addClass("active");

        var degreeSelect = $("#degree option");
        for (var i=0; i<degreeSelect.length; i++) {
            if (degreeSelect[i].value == '${teacher.degree}') {
                degreeSelect[i].selected = true;
            }
        }

	</script>
</html>