<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>

<jsp:useBean id="list" scope="request" type="java.util.List<MemVO>" />

<%
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>

<!-- 房仲前端 -->
<jsp:include page="/front/realtor/frontPage.jsp" flush="true" />
<!-- 房仲前端 -->

<style>
input[type=text] {
    width: 90%;
    padding: 12px 20px;
    margin: 8px 0;
    box-sizing: border-box;
    border: 3px solid #ccc;
    -webkit-transition: 0.5s;
    transition: 0.5s;
    outline: none;
}

input[type=text]:focus {
    border: 3px solid #f26649;
}

.cooperhide{
	height:50px;
}

.cooperhide:hover{
	background-color: #f26649;
}
</style>

<body>

	<!-- 右邊房仲相關資料區include資料用 =====================================-->
	<div class="col-sm-12 col-sm-8 form_realtor">
		<!-- 顯示資料 =================================================-->
		<div class="modal-header">
			<h4 class="modal-title">開放找房的會員</h4>
		</div>

		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<!--Search Bar-->
		<div>
			<form method="post"
				action="<%=request.getContextPath()%>/front/realtor/realtor.do">
				<div class="form-group">
					<input class="memOpen" type="text" class="form-control"	name="MEM_ADDR" value="" placeholder="請輸入台北地區:中正區、中壢區"> 
					<input type="hidden" name="action" value="memOpen"> 
					<button type="submit" value="搜尋" class="cooperhide btn btn-outline-primary">搜尋</button>
				</div>
			</form>
		</div><br>
		<table class="table table-hover table_main">
			<thead>
				<tr class="col_title">
					<th>會員姓名</th>
					<th>會員地址</th>
					<th>選擇聊天</th>
				</tr>
				<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
			</thead>
			<tbody>
				<c:forEach var="memVO" items="${list}">
					<tr class="col_name"
						${(memVO.mem_no==param.mem_no) ? 'bgcolor=skyblue':''}>
						<!--將修改的那一筆加入對比色而已-->
						<td>${memVO.mem_name}</td>
						<td>${memVO.mem_addr}</td>
						<td>
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>"
								style="margin-bottom: 0px;">
								<button type="submit" value="聊天" class="cooperhide btn btn-outline-primary">聊天</button>
								<input type="hidden" name="" value="${memVO.mem_no}"> 
								<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
								<!--送出本網頁的路徑給Controller-->
								<input type="hidden" name="action" value="">
							</FORM>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!-- 右邊房仲相關資料區include資料用end =================================================-->

	<!-- 充版面用 -->
	<div class="col-sm-1"></div>
	<!-- 充版面用 -->

	<!-- 不可刪掉的div -->
	</div>
	</div>
	<!-- 不可刪掉的div -->


	<!-- footer -->
	<footer class="container-fluid" id="cooper_footer">
		<div class="container text-center">
			<div class="col-xs-12 col-sm-2">
				<a href="#">HOME</a>
			</div>
			<div class="col-xs-12 col-sm-2">
				<a href="#">OUR TEAM</a>
			</div>
			<div class="col-xs-12 col-sm-2">
				<a href="#">COMMUNITIES</a>
			</div>
			<div class="col-xs-12 col-sm-2">
				<a href="#">COMMUNITIES</a>
			</div>
			<div class="col-xs-12 col-sm-2">
				<a href="#">COMMUNITIES</a>
			</div>
			<div class="col-xs-12 col-sm-2">
				<a href="#">CONTACT US</a>
			</div>
		</div>
		<div class="copyri text-center">
			<p>
				<small>Copyright © 2017 For House</small>
			</p>
		</div>
	</footer>
	<!-- footer -->
</body>
