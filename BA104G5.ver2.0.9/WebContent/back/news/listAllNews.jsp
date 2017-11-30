<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.news.model.*"%>

<%-- 採用 EL 的寫法取值 --%>

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
<!-- 為了new NewsTypeService用 -04系列 -->
<jsp:useBean id="newstypeSvc" scope="page"
	class="com.newstype.model.NewsTypeService" />

<!-- 後端include -->
<jsp:include page="/back/backend/backend_page.jsp" flush="true" />
<!-- 後端include -->

<!-- 一定要留住的<div> ================================================================================== -->
<div class="col-xs-12 col-sm-10 maincontext">
	<!-- 一定要留住的<div> ================================================================================== -->

	<!-- 以下是你可以放的內容 ================================================================================== -->
	<!-- 新增房市最新消息 ========================================================================================= -->
	<div class="container cooper_house">
		<div class="row">
			<div class="col-xs-12 col-sm-12 table_bgcolor">
				<table class="table table-hover table_main">
					<caption class="title_pr">
						房市最新消息<a href='#house_jump' data-toggle="modal"
							class="btn btn_cooper modal_jump cooper_ann_href"> 新增房市最新消息 </a>
					</caption>
					<thead>
						<tr class="col_title">
							<th>新聞編號</th>
							<th>新聞種類</th>
							<th>新聞標題</th>
							<th>新聞內容</th>
							<th>新聞狀態</th>
							<th>新聞照片</th>
							<th>新增時間</th>
							<th>員工編號</th>
							<th>修改新聞內容</th>
						</tr> 
					</thead>
					<tbody>
						<%@include file="page_news/pagenews.file"%>
						<c:forEach var="newsVO" items="${list}" begin="<%=pageIndex%>"
							end="<%=pageIndex+rowsPerPage-1%>">
							<tr class="col_name"
								${(newsVO.news_no==param.news_no) ? 'bgcolor=#54FF9F' : ''}>
								<!--將修改的那一筆加入對比色而已-->
								<td>${newsVO.news_no}</td>
								<!-- 舊版的寫法 -->
								<%-- <td>${newsVO.ntype_no}</td> --%>

								<!-- 新版從別新聞種類的table join過來 -04系列-->
								<%-- <td>${newstypeSvc.getOne(newsVO.ntype_no).news_type}</td> --%>

								<td>${newsVO.ntype_no}<br>
									[${newstypeSvc.getOne(newsVO.ntype_no).news_type}]
								</td>

								<td class="text_overflow">${newsVO.news_title}</td>
								<td class="text_overflow">${newsVO.news_content}</td>
								<td>
									<div class="dropdown">${newsVO.news_state}
										<!-- <select class="form-control btn_cooper" id="sel1"> -->
										<!-- <option class="onitem">公告中</option> -->
										<!-- <option class="offitem">已撤銷</option> -->
										<!-- </select> -->
									</div>
								</td>
								<td><div class="" style="height: 145.8px; width: 194px;">
										<img style="height: 145.8px; width: 194px;"
											src="<%=request.getContextPath()%>/tool/showimage.do?action=news_photo&news_no=${newsVO.news_no}">
									</div></td>
								<td>${newsVO.news_date}</td>
								<td>${newsVO.emp_no}</td>
								<td>
									<form METHOD="post"
										ACTION="<%=request.getContextPath()%>/back/news/news.do">
										<input class="btn btn_cooper modal_jump cooper_ann_href"
											type="submit" value="修改新聞公告"> <input type="hidden"
											name="news_no" value="${newsVO.news_no}"> <input
											type="hidden" name="requestURL"
											value="<%=request.getServletPath()%>">
										<!--送出本網頁的路徑給Controller-->
										<input type="hidden" name="whichPage" value="<%=whichPage%>">
										<!--送出當前是第幾頁給Controller-->
										<input type="hidden" name="action" value="getOne_For_Update">
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<table class="table_main">
					<tr>
						<td>
							<h4>
								<a
									href="<%=request.getContextPath()%>/back/backend/select_page_home.jsp"><img
									src="<%=request.getContextPath()%>/images/back1.gif"
									width="100" height="32" border="0">回後端首頁</a>
							</h4>
						</td>
					</tr>
					<tr>
						<td>
							<FORM METHOD="post" ACTION="news.do">
								<b>選擇新聞編號:</b> <select size="1" name="news_no">
									<c:forEach var="newsVO" items="${list}">
										<option value="${newsVO.news_no}">${newsVO.news_no}
									</c:forEach>
								</select> <input type="hidden" name="action" value="getOne_For_Display">
								<input type="submit" value="送出">
							</FORM>
						</td>
					</tr>
					<tr>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/back/newstype/newstype.do">
								<b>選擇新聞種類:</b> <select size="1" name="ntype_no">
									<c:forEach var="newstypeVO" items="${newstypeSvc.getAll()}">
										<option value="${newstypeVO.ntype_no}">${newstypeVO.ntype_no}-&nbsp;[${newstypeVO.news_type}]</option>
									</c:forEach>
								</select> <input type="hidden" name="action"
									value="listNews_ByNtype_No_B"> <input type="submit"
									value="送出">
							</FORM>
						</td>
					</tr>
					<div class="container">
						<div class="row">
							<div class="col-sm-12">
								<%@include file="page_news/pagenews2.file"%>
							</div>
						</div>
					</div>
					<tr>
						<td>
							<%-- 錯誤表列 --%> <c:if test="${not empty errorMsgs}">
								<font style="color: red">請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color: red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>
						</td>
					</tr>
				</table>

				<%
					if (request.getAttribute("listNews_ByNtype_No") != null) {
				%>
				<jsp:include page="/back/newstype/listNews_ByNtype_No.jsp" />
				<%
					}
				%>


			</div>
		</div>
	</div>
	<!-- 彈出新增房市最新消息=====================================================================================-->
	<div class="modal fade" id="house_jump">
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

						<div class="form-group">
							<label for="ntype_no">新聞種類</label> <select
								class="form-control btn_cooper" id="sel1" name="ntype_no">
								<option name="default_item1" value="">請選擇</option>
								<option class="onitem" value="LK002"
									${(newsVO.ntype_no=='LK002')? 'selected': ''}>LK002</option>
								<option class="onitem" value="GH003"
									${(newsVO.ntype_no=='GH003')? 'selected': ''}>GH003</option>
								<option class="onitem" value="HN001"
									${(newsVO.ntype_no=='HN001')? 'selected': ''}>HN001</option>
							</select>
						</div>

						<div class="form-group">
							<label for="news_title">新聞標題</label> <input type="text"
								class="form-control" name="news_title"
								value="<%=(newsVO == null) ? "新增新聞標題" : newsVO.getNews_title()%>" />
						</div>
						<div class="form-group">
							<label for="news_content">新聞內容</label>
							<textarea rows="10" class="form-control" name="news_content"><%=(newsVO == null) ? "請新增新聞內容" : newsVO.getNews_content()%></textarea>
						</div>
						<div class="form-group dropdown">
							<label for="news_content">修改新聞狀態</label> <select
								name="news_state" class="form-control btn_cooper" id="sel1">
								<option name="default_item2" value="">請選擇</option>
								<option class="onitem" value="公告中"
									${(newsVO.news_state=='公告中')? 'selected': ''}>公告中</option>
								<option class="offitem" value="已撤銷"
									${(newsVO.news_state=='已撤銷')? 'selected': ''}>已撤銷</option>
							</select>
						</div>
						<div class="form-group">
							<label for="news_photo">照片預覽</label> <input type="file"
								name="news_photo" onchange="readURL(this);"> <img
								name="news_photo" id="imgpreview" alt="照片預覽" />
						</div>
						<div class="form-group">
							<label for="empno_no">員工編號</label><input type="text"
								class="form-control" name="emp_no"
								value="<%=(newsVO == null) ? "EM00000002" : newsVO.getEmp_no()%>" />
						</div>
						<div class="modal-footer">
							<input type="hidden" name="action" value="insert">
							<button type="submit" class="btn btn_cooper" value="送出新增">送出新增</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- 結束房市最新消息 ================================================================================== -->
	<!-- 以上是你可以放的內容 ================================================================================== -->

	<!-- 一定要留住的</div> ================================================================================== -->
</div>
<!-- 一定要留住的</div> ================================================================================== -->