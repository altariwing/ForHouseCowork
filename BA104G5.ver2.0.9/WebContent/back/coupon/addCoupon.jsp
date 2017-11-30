<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.news.model.*"%>
<%--採用 EL 的寫法取值 --%>

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

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color: red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color: red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


<!-- 一定要留住的<div> ================================================================================== -->
<div class="col-xs-12 col-sm-10 maincontext">
<!-- 一定要留住的<div> ================================================================================== -->

<!-- 以下是你可以放的內容 ================================================================================== -->

	<!-- 彈出新增房市最新消息=====================================================================================-->
	<div class="modal fade modal_jump" id="house_jump">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">新增房市最新消息</h4>
				</div>
				<div class="modal-body">
					<form role="form" METHOD="post" ACTION="news.do"
						enctype="multipart/form-data" name="house">
						<!-- 呼叫News_Type table-->
						<jsp:useBean id="newstypeSvc" scope="page"
							class="com.newstype.model.NewsTypeService" />
						<div class="form-group">
							<label for="ntype_no">新聞種類</label> <select
								class="form-control btn_cooper" id="sel1" name="ntype_no">
								<option class="default_item">請選擇</option>
								<c:forEach var="newstypeVO" items="${newstypeSvc.all}">
									<option value="${newtypeVO.ntype_no}"
										${(newsVO.ntype_no==newstypeVO.ntype_no) ? 'selected': '' }>${newstypeVO.n_type}</option>
								</c:forEach>
							</select>
						</div>

						<div class="form-group">
							<label for="news_title">新聞標題</label> <input type="text"
								class="form-control" name="news_title"
								value="<%=(newsVO == null) ? "新增新聞標題" : "newsVO.getNews_title()"%>">
						</div>
						<div class="form-group">
							<label for="news_content">新聞內容</label>
							<textarea rows="10" class="form-control" name="news_content"
								value="<%=(newsVO == null) ? "新增新聞內容" : newsVO.getNews_content()%>"></textarea>
						</div>
						<div class="form-group dropdown">
							<label for="ann_content">修改新聞狀態</label> <select name="news_state"
								class="form-control btn_cooper" id="sel1">
								<option class="default_item">請選擇</option>
								<option class="onitem" value="公告中"
									${(newsVO.news_state=='公告中')? 'selected': ''}>公告中</option>
								<option class="offitem" value="已撤銷"
									${(newsVO.news_state=='已撤銷')? 'selected': ''}>已撤銷</option>
							</select>
						</div>
						<div class="form-group">
							<input type="file" name="news_photo">
						</div>
						<div class="form-group">
							<label for="empno_no">員工編號</label><input type="text"
								class="form-control" name="emp_no"
								value="<%=(newsVO == null) ? "EM00000002" : newsVO.getEmp_no()%>" />
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">離開</button>
							<input type="hidden" name="action" value="insert">
							<button type="submit" class="btn btn_cooper" value="送出新增">送出新增</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- 結束房市最新消息 -->
<!-- 以上是你可以放的內容 ================================================================================== -->

<!-- 一定要留住的</div> ================================================================================== -->
</div>
<!-- 一定要留住的</div> ================================================================================== -->