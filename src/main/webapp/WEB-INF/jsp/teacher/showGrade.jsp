<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<title>查看成绩</title>

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
							<c:if test="${fn:length(selectedCourseList) == 0}">
								<h1>该课程学生名单</h1>
							</c:if>
							<c:if test="${fn:length(selectedCourseList) > 0}">
								<h1>《 ${selectedCourseList[0].courseCustom.coursename} 》学生名单</h1>
							</c:if>
						</div>
				    </div>
				    <table class="table table-bordered">
					        <thead>
					            <tr>
									<th>学号</th>
									<th>姓名</th>
									<th>学习进度</th>
									<th>分数</th>
									<th>操作</th>
					            </tr>
					        </thead>
					        <tbody>
								<c:forEach items="${StudentCourseCustomList}" var="item">
									<tr>
										<td>${item.studentId}</td>
										<td>${item.studentName}</td>
										<td>${item.progress}</td>
										<c:if test="${item.mark == null}">
											<c:if test="${item.course.period != item.progress}">
												<td>未打分</td>
												<td>无法打分：未完成学习</td>
											</c:if>
											<c:if test="${item.course.period == item.progress}">
												<td>未打分</td>
												<td>
													<button class="btn btn-default btn-xs btn-info" onClick="location.href='${ctx}/teacher/mark?studentId=${item.studentId}&courseId=${item.courseId}'">打分</button>
												</td>
											</c:if>
										</c:if>
										<c:if test="${item.mark != null}">
											<td>${item.mark}</td>
											<td>已打分</td>
										</c:if>
									</tr>
								</c:forEach>
					        </tbody>
				    </table>
				    <div class="panel-footer">
						<c:if test="${pagingVO != null}">
							<nav style="text-align: center">
								<ul class="pagination">
									<li><a href="${ctx}/student/showCourse?page=${pagingVO.upPageNo}">&laquo;上一页</a></li>
									<li class="active"><a href="">${pagingVO.curentPageNo}</a></li>
									<c:if test="${pagingVO.curentPageNo+1 <= pagingVO.totalCount}">
										<li><a href="${ctx}/student/showCourse?page=${pagingVO.curentPageNo+1}">${pagingVO.curentPageNo+1}</a></li>
									</c:if>
									<c:if test="${pagingVO.curentPageNo+2 <= pagingVO.totalCount}">
										<li><a href="${ctx}/student/showCourse?page=${pagingVO.curentPageNo+2}">${pagingVO.curentPageNo+2}</a></li>
									</c:if>
									<c:if test="${pagingVO.curentPageNo+3 <= pagingVO.totalCount}">
										<li><a href="${ctx}/student/showCourse?page=${pagingVO.curentPageNo+3}">${pagingVO.curentPageNo+3}</a></li>
									</c:if>
									<c:if test="${pagingVO.curentPageNo+4 <= pagingVO.totalCount}">
										<li><a href="${ctx}/student/showCourse?page=${pagingVO.curentPageNo+4}">${pagingVO.curentPageNo+4}</a></li>
									</c:if>
									<li><a href="${ctx}/student/showCourse?page=${pagingVO.totalCount}">最后一页&raquo;</a></li>
								</ul>
							</nav>
						</c:if>
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
		<%--设置菜单中--%>
		$("#nav li:nth-child(1)").addClass("active")
        <c:if test="${pagingVO != null}">
        if (${pagingVO.curentPageNo} == ${pagingVO.totalCount}) {
            $(".pagination li:last-child").addClass("disabled")
        };

        if (${pagingVO.curentPageNo} == ${1}) {
            $(".pagination li:nth-child(1)").addClass("disabled")
        };
        </c:if>

        $("#sub").click(function () {
            $("#form1").submit();
        });
	</script>
</html>