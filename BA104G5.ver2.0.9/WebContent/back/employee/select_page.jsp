<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.employee.model.*"%>
<%
EmployeeVO empVO = (EmployeeVO) session.getAttribute("empVO");
//select page是拿阿蓋的HTML作基底的
  
%>

<body>
<!-- include backend_page.jsp  -->
<jsp:include page="/back/backend/backend_page.jsp" flush="true" />
<!-- include backend_page.jsp  -->

<style>
table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

ul h4 {
	color: blue;
	display: inline;
}
</style>



	

<!-- �@�w�n�d��<div> ================================================================================== -->
<div class="col-xs-12 col-sm-10 maincontext">
	<!-- �@�w�n�d��<div> ================================================================================== -->
	<!-- �H�U�O�A�i�H�񪺤��e ================================================================================== -->
	<!-- �޲z���u�}�l ========================================================================================= -->
	<div class="container ">
		
	<h3>查詢所有員工:</h3>

	<ul>
		<li><a href="<%=request.getContextPath()%>/back/employee/listAllEmp.jsp"> List</a> all Emps
			<br> <br> <br></li>
	</ul>

	<h3>新增一位員工</h3>
	<ul>
		<li><a href='<%=request.getContextPath()%>/back/employee/addEmp.jsp'>Add</a> a new Emp.</li>
	</ul>

	
	
	</div>
	<!-- �H�W�O�A�i�H�񪺤��e =========================================================================== -->

	<!-- �@�w�n�d��</div> ============================================================================ -->
</div>
<!-- �@�w�n�d��</div> ================================================================================ -->
</body>
</html>