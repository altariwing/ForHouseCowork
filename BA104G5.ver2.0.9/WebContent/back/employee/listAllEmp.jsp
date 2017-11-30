<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.employee.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
<%
 EmployeeService empService = new EmployeeService();
 List<EmployeeVO> list = empService.getAll();
 pageContext.setAttribute("list", list);
%> 
<!-- 後端include -->
<jsp:include page="/back/backend/backend_page.jsp" flush="true" />
<!-- 後端include -->

<head>
<title>所有員工資料 - listAllEmp.jsp</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

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
.h4c {
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
<!-- 一定要留住的<div> ================================================================================== -->
<div class="col-xs-12 col-sm-10 maincontext">
	<!-- 一定要留住的<div> ================================================================================== -->
	<!-- 以下是你可以放的內容 ================================================================================== -->
	<!-- listAllemp開始 ========================================================================================= -->

<div class="container pull-left " >
<h4 class="h4c">此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1 " >
	
		 <h3>所有員工資料 - listAllEmp.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back/employee/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回管理員工首頁</a></h4>
	
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

<table class="table table-striped table-bordered" id ="listtable">
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
		<th>修改</th>
		<th>刪除</th>
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
			<td>${empVO.emp_state.equals('Active') ? '啟用':'未啟用'}</td>
			<td>${empVO.emp_newdate}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back/employee/emp.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="emp_no"  value="${empVO.emp_no}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back/employee/emp.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除" onclick="return confirm('確定要刪除 ?')">
			     <input type="hidden" name="emp_no"  value="${empVO.emp_no}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>





<%@ include file="page2.file" %>
</div>

	<!-- 以上是你可以放的內容 =========================================================================== -->

	<!-- 一定要留住的</div> ============================================================================ -->
</div>
<!-- 一定要留住的</div> ================================================================================ -->
</body>
</html>