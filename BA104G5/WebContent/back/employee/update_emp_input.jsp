<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.employee.model.*"%>

<%
  EmployeeVO empVO = (EmployeeVO) request.getAttribute("empVO"); //EmpServlet.java (Concroller), �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>

<html>
<!-- ���include -->
<jsp:include page="/back/backend/backend_page.jsp" flush="true" />
<!-- ���include -->
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>���u��ƭק� - update_emp_input.jsp</title>

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

<br><br><br><br><br><br>
<div class="col-sm-4" style="margin-top:10px;margin-left:20px">
<h3>��ƭק�:</h3>

<%-- ���~���C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="emp.do" name="form1">
<table class="table">
	<tr>
		<td width>���u�s��:</td>
		<td><%=empVO.getEmp_no()%></td>
		<input type="hidden" name="emp_no" value="<%=empVO.getEmp_no()%>">
	</tr>
	<tr>
		<td>���u�b��:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="emp_id" size="45" value="<%=empVO.getEmp_id()%>" /></td>
	</tr>
	<tr>
		<td>���u�K�X:<font color=red><b>*</b></font></td>
		
		<td><input type="TEXT" name="emp_psw" size="45" value="<%=empVO.getEmp_psw()%>" /></td>
	</tr>
	<tr>
		<td>���u�m�W:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="emp_name" size="45"	value="<%=empVO.getEmp_name()%>" /></td>
	</tr>
	
	
	<tr>
		<td>�b�����A:<font color=red><b>*</b></font></td>
		<td><select  name="emp_state">
			
				<option value="Active">�ҥ�</option>
				<option value="InActive">���ҥ�</option>
			
		</select></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="empno" value="<%=empVO.getEmp_no()%>">
<input type="hidden" name="requestURL" value="<%=request.getAttribute("requestURL")%>"> <!--��e�X�ק諸�ӷ��������|,�qrequest���X��,�A�e��Controller�ǳ���椧��-->
<input type="hidden" name="whichPage"  value="<%=request.getAttribute("whichPage")%>">  <!--�u�Ω�:istAllEmp.jsp-->
<input type="submit" value="�e�X�ק�"></FORM>


</div>
</body>

</html>