<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<title>在线打分</title>

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
					    	<h1 style="text-align: center;">在线打分</h1>
						</div>
				    </div>
				    <div class="panel-body">
						<form name="reset" class="form-horizontal" role="form" action="${ctx}/teacher/saveMark" id="editfrom" method="post" onsubmit="return check()">
							<div class="form-group">
								<div class="col-sm-10">
									<input  readonly="readonly" type="hidden" class="form-control" name="courseId" value="${studentCourseCustom.courseId}">
								</div>
							</div>
							<div class="form-group">
							    <label class="col-sm-2 control-label">学号</label>
							    <div class="col-sm-10">
							      <input  readonly="readonly" type="text" class="form-control" name="studentId" value="${studentCourseCustom.studentId}">
							    </div>
							  </div>
							  <div class="form-group">
							    <label class="col-sm-2 control-label">姓名</label>
							    <div class="col-sm-10">
							      <input  readonly="readonly" type="text" name="studentName" class="form-control" value="${studentCourseCustom.studentName}">
							    </div>
							  </div>
							  <div class="form-group">
							    <label class="col-sm-2 control-label">成绩</label>
							    <div class="col-sm-10">
							      <input type="number" name="mark" class="form-control" placeholder="请输入成绩">
							    </div>
							  </div>
							  <div class="form-group" style="text-align: center">
								  <button class="btn btn-default" onClick="location.href='${ctx}/teacher/saveMark?courseId=${studentCourseCustom.courseId}&studentId=${studentCourseCustom.studentId} '">提交</button>
								<button class="btn btn-default">重置</button>
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
<script>
    $("#nav li:nth-child(1)").addClass("active")
    function check() {
        if(reset.mark.value==""||reset.mark.value==null)
        {alert("请输入成绩");return false;}
    }
</script>
</html>