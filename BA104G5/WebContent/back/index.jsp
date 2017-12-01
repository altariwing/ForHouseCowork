<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.realtor.model.*"%>
<%
	EmployeeVO empVO = (EmployeeVO) session.getAttribute("empVO");
	if (empVO == null) { // 如為 null, 代表此user未登入過 , 才做以下工作
		session.setAttribute("location", request.getRequestURI()); //*工作1 : 同時記下目前位置 , 以便於login.jsp登入成功後 , 能夠直接導至此網頁(須配合LoginHandler.java)
		response.sendRedirect("bklogin.jsp"); //*工作2 : 請該user去登入網頁(bklogin.jsp) , 進行登入
		return;
	}

	RealtorService realtorSvc = new RealtorService();
	List<RealtorVO> list = realtorSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<body>
	<!-- 後端include -->
	<jsp:include page="/back/backend/backend_page.jsp" flush="true" />
	<!-- 後端include -->

	<style>
table, th, td {
	border: 0px solid #CCCCFF;
}
</style>


	<!-- 一定要留住的<div> ================================================================================== -->
	<div class="col-xs-12 col-sm-10 maincontext">
		<!-- 一定要留住的<div> ================================================================================== -->

		<!-- 以下是你可以放的內容 ================================================================================ -->
		
		<%
			int i = 0;
		%>
		<c:forEach var="realtorVO" items="${list}">
			<%
				i++;
			%>
		</c:forEach>
		

		<div class="col-xs-12 col-sm-3">
			<div class="panel panel-danger ">
				<div class="panel-heading">
					<h2 class="panel-title paneltext">
						<span class="glyphicon glyphicon-tasks"></span>
						<p class="text-right panelboxtitle"><%=i%></p>
					</h2>
					<p>您有:<%=i%>未處理的房仲註冊審核</p>
				</div>
				<div class="panel-body">
				<a href="<%=request.getContextPath() %>/back/checkmail/RtrMailCheck.jsp">
					<p class="text-right">
						詳情 <span class="glyphicon glyphicon-circle-arrow-down"></span>
					</p>
				</a>	
				</div>
			</div>
		</div>


		<!-- 以上是你可以放的內容 ================================================================================= -->

		<!-- 一定要留住的</div> ================================================================================== -->
	</div>
	<!-- 一定要留住的</div> ================================================================================== -->
</body>
</html>