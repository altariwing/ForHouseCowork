<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.employee.model.*"%>

<%
  EmployeeVO empVO = (EmployeeVO) request.getAttribute("empVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>員工資料新增 - addEmp.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>

<!-- 後端include -->
<jsp:include page="/back/backend/backend_page.jsp" flush="true" />
<!-- 後端include -->

<body bgcolor='white'>
<!-- 一定要留住的<div> ================================================================================== -->
<div class="col-xs-12 col-sm-10 maincontext">
	<!-- 一定要留住的<div> ================================================================================== -->
	<!-- 以下是你可以放的內容 ================================================================================== -->

<h3>員工資料新增 - addEmp.jsp</h3>
<h4><a href="<%=request.getContextPath()%>/back/employee/select_page.jsp"><img src="<%=request.getContextPath()%>/back/employee/images/back_btn.jpg" width="auto" height="100" border="0">回管理員工</a></h4>
	

<h3>新增員工帳號:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message.value}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="emp.do" name="form1">
<table>
	<tr>
		<td>員工姓名:</td>
		<td><input type="text" name="emp_name" size="45" 
			 value="${param.emp_name}"/></td><td>${errorMsgs.emp_name}</td>
	</tr>
	<tr>
		<td>員工帳號:</td>
		<td><input type="email" name="emp_id" size="45"
			 value="${param.emp_id}"/></td><td>${errorMsgs.emp_id}</td>
	
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" id="insertBtn" value="送出新增" onclick="submitFunction()"></FORM><h3 id="dataID" style="color:red"></h3>
<script>
function submitFunction() {
	
    document.getElementById("dataID").innerHTML = "資料新增中";
}
</script>
	<!-- 以上是你可以放的內容 =========================================================================== -->

	<!-- 一定要留住的</div> ============================================================================ -->
</div>
<!-- 一定要留住的</div> ================================================================================ -->
</body>

</html>