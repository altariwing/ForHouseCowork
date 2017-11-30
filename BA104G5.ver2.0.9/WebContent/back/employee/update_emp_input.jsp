<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.employee.model.*"%>

<%
  EmployeeVO empVO = (EmployeeVO) request.getAttribute("empVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<!-- 後端include -->
<jsp:include page="/back/backend/backend_page.jsp" flush="true" />
<!-- 後端include -->
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>員工資料修改 - update_emp_input.jsp</title>

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
	width: 450px;
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
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>員工資料修改 - update_emp_input.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back/employee/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="emp.do" name="form1">
<table>
	<tr>
		<td>員工編號:</td>
		<td><%=empVO.getEmp_no()%></td>
		<input type="hidden" name="emp_no" value="<%=empVO.getEmp_no()%>">
	</tr>
	<tr>
		<td>員工帳號:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="emp_id" size="45" value="<%=empVO.getEmp_id()%>" /></td>
	</tr>
	<tr>
		<td>員工密碼:<font color=red><b>*</b></font></td>
		
		<td><input type="TEXT" name="emp_psw" size="45" value="<%=empVO.getEmp_psw()%>" /></td>
	</tr>
	<tr>
		<td>員工姓名:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="emp_name" size="45"	value="<%=empVO.getEmp_name()%>" /></td>
	</tr>
	
	
	<tr>
		<td>帳號狀態:<font color=red><b>*</b></font></td>
		<td><select  name="emp_state">
			
				<option value="Active">啟用</option>
				<option value="InActive">未啟用</option>
			
		</select></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="empno" value="<%=empVO.getEmp_no()%>">
<input type="hidden" name="requestURL" value="<%=request.getAttribute("requestURL")%>"> <!--原送出修改的來源網頁路徑,從request取出後,再送給Controller準備轉交之用-->
<input type="hidden" name="whichPage"  value="<%=request.getAttribute("whichPage")%>">  <!--只用於:istAllEmp.jsp-->
<input type="submit" value="送出修改"></FORM>
</body>

</html>