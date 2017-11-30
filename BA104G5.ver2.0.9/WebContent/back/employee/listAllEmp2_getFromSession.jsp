<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.employee.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
<%
//這檔案是舊版 新版名稱叫: listAllEmp.jsp
//  List<EmpVO> list = (List<EmpVO>)session.getAttribute("list"); //EmpServlet.java(Concroller), 存入session的list物件
%> 

<%-- 以下等同第7行--%>
<jsp:useBean id="list" scope="session" type="java.util.List<EmployeeVO>" />



<!DOCTYPE html>


<head>
<title>所有員工資料 - listAllEmp2_getFromSession.jsp</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Bootstrap CSS (Optional theme) -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Bootstrap JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

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
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
 
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>
<div class="container pull-left " >
<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1 " >
	
		 <h3>所有員工資料 - listAllEmp2_getFromSession.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table class="table table-striped table-bordered">
	<tr>
		<th>員工編號</th>
		<th>員工帳號</th>
		<th>員工姓名</th>
		
		<th>上次登入日期</th>
		<th>上次錯誤登入</th>
		<th>錯誤嘗試次數</th>
		
		<th>照片</th>
		
		<th>帳號狀態</th>
		<th>新增日期</th>
	</tr>
	<%@ include file="page1.file" %> 
	
	
	<c:forEach var="empVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
			<td>${empVO.emp_no}</td>
			<td>${empVO.emp_id}</td>
			<td>${empVO.emp_name}</td>			
			<td>${empVO.emp_lstlog}</td>
			<td>${empVO.emp_badlog}</td>
			<td>${empVO.emp_badlogtry}</td>
			<td><img id="ItemPreview" src="data:image/png;base64,${empVO.emp_photo}" /></td>
			<td>${empVO.emp_state}</td>
			<td>${empVO.emp_newdate}</td>
		</tr>
	</c:forEach>
</table>

<%@ include file="page2.file" %>


</div>
</body>
</html>