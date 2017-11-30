<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.news.model.*"%>

<%
	NewsVO newsVO = (NewsVO) request.getAttribute("newsVO");
	NewsService newsSvc = new NewsService();
	List<NewsVO> list = newsSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<%
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>
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
	<!--修改新聞公告========================================================================================= -->
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-6 col-sm-offset-3 form_ann">
				<form role="form" METHOD="post" ACTION="news.do"
					enctype="multipart/form-data" name="news">
					<div class="modal-header">
						<h4 class="modal-title">修改房市最新消息</h4>
					</div>
					<div class="form-group">
						<label for="news_no">新聞編號</label>
						<div>
							<h5 class="update_color"><%=newsVO.getNews_no()%></h5>
						</div>
					</div>
					<!-- 舊版的寫法 -->
					<!-- <div class="form-group"> -->
					<!-- <label for="ntype_no">新聞種類</label> <select -->
					<!-- class="form-control btn_cooper" id="sel1" name="ntype_no"> -->
					<!-- <option name="default_item1" value="">請選擇</option> -->
					<!-- <option class="onitem" value="LK002" -->
					<%-- ${(newsVO.ntype_no=='LK002')? 'selected': ''}>LK002</option> --%>
					<!-- <option class="onitem" value="GH003" -->
					<%-- ${(newsVO.ntype_no=='GH003')? 'selected': ''}>GH003</option> --%>
					<!-- <option class="onitem" value="HN001" -->
					<%-- ${(newsVO.ntype_no=='HN001')? 'selected': ''}>HN001</option> --%>
					<!-- </select> -->
					<!-- </div> -->

					<!-- 新版從別新聞種類的table join過來 -->
					<jsp:useBean id="newstypeSvc" scope="page"
						class="com.newstype.model.NewsTypeService" />
					<div class="form-group">
						<label for="ntype_no">新聞種類</label> <select
							class="form-control btn_cooper" id="sel1" name="ntype_no">
							<option name="default_item1" value="">請選擇</option>
							<c:forEach var="newstypeVO" items="${newstypeSvc.all}">
								<option class="onitem" value="${newstypeVO.ntype_no}"
									${(newsVO.ntype_no==newstypeVO.ntype_no)? 'selected': ''}>[${newstypeVO.ntype_no}
									- ${newstypeVO.news_type}]</option>
							</c:forEach>
						</select>
					</div>


					<div class="form-group">
						<label for="news_title">新聞標題</label> <input type="text"
							class="form-control" name="news_title"
							value="<%=(newsVO == null) ? "修改新聞標題" : newsVO.getNews_title()%>">
					</div>
					<div class="form-group">
						<label for="news_content">新聞內容</label>
						<textarea rows="10" class="form-control" name="news_content"
							value="<%=(newsVO == null) ? "修改新聞內容" : newsVO.getNews_content()%>"></textarea>
					</div>
					<div class="form-group dropdown">
						<label for="news_content">修改新聞狀態</label> <select name="news_state"
							class="form-control btn_cooper" id="sel1">
							<option name="default_item2" value="">請選擇</option>
							<option class="onitem" value="公告中"
								${(newsVO.news_state=='公告中')? 'selected': ''}>公告中</option>
							<option class="offitem" value="已撤銷"
								${(newsVO.news_state=='已撤銷')? 'selected': ''}>已撤銷</option>
						</select>
					</div>
					<div class="form-group">
						<label for="news_photo">先前照片</label>
						<div class="" style="height: 145.8px; width: 194px;">
							<img style="height: 145.8px; width: 194px;"
								src="<%=request.getContextPath()%>/tool/showimage.do?action=news_photo&news_no=${newsVO.news_no}">
						</div>
					</div>
					<div class="form-group">
						<label for="news_content">照片預覽</label> <input type="file"
							name="news_photo" onchange="readURL(this);"> <img
							name="news_photo" id="imgpreview" alt="照片預覽" />
					</div>
					<div class="form-group">
						<label for="empno_no">員工編號</label><input type="text"
							class="form-control" name="emp_no"
							value="<%=(newsVO == null) ? "EM00000002" : newsVO.getEmp_no()%>" />
					</div>
					<div class="modal-footer">
						<input type="hidden" name="action" value="update"> <input
							type="hidden" name="news_no" value="${newsVO.news_no}"> <input
							type="hidden" name="requestURL"
							value="<%=request.getParameter("requestURL")%>">
						<!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
						<input type="hidden" name="whichPage"
							value="<%=request.getParameter("whichPage")%>">
						<!--只用於:istAllEmp.jsp-->
						<button type="submit" class="btn btn_cooper" value="送出修改">送出修改</button>
					</div>
				</form>
			</div>
		</div>
		<br>送出修改的來源網頁路徑:<br> <b> <font color=blue>request.getParameter("requestURL"):</font>
			<%=request.getParameter("requestURL")%><br> <font color=blue>request.getParameter("whichPage"):
		</font> <%=request.getParameter("whichPage")%> (此範例目前只用於:istAllEmp.jsp))
		</b>
	</div>
	<!-- 結束修改新聞公告 ===================================================================================== -->
	<!-- 以上是你可以放的內容 ================================================================================= -->

	<!-- 一定要留住的</div> ================================================================================== -->
</div>
<!-- 一定要留住的</div> ================================================================================== -->
