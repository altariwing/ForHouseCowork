<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.news.model.*"%>
<%@ page import="com.newstype.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%--
// 	NewsVO newsVO = (NewsVO) request.getAttribute("newsVO");
--%>

<!-- 取出Controller NewsServlet.java已存入request的newsVOreq物件-04系列 -->
<%
	NewsVO newsVO = (NewsVO) request.getAttribute("newsVO");
%>

<%
	NewsTypeService newstypeSvc = new NewsTypeService();
	NewsTypeVO newstypeVO = newstypeSvc.getOne(newsVO.getNtype_no());
%>
<%
response.setHeader("Cache-Control", "no-store");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
%>

<!-- 後端include -->
<jsp:include page="/back/backend/backend_page.jsp" flush="true" />
<!-- 後端include -->

<!-- 一定要留住的<div> ================================================================================== -->
<div class="col-xs-12 col-sm-10 maincontext">
	<!-- 一定要留住的<div> ================================================================================== -->

	<!-- 以下是你可以放的內容 ================================================================================ -->
	<!-- 房市最新消息 ========================================================================================= -->
	<div class="container cooper_house">
		<div class="row">
			<div class="col-xs-12 col-sm-12 table_bgcolor">
				<table class="table table-hover table_main">
					<caption class="title_pr">
						房市最新消息
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
						</tr>
					</thead>
					<tbody>
						<tr class="col_name">
							<td>${newsVO.news_no}</td>
							<!-- 舊版的寫法 -->
							<%--<td>${newsVO.ntype_no}</td> --%>

							<!-- 新版從別新聞種類的table join過來 -04系列-->
							<td>${newsVO.ntype_no}[<%=newstypeVO.getNews_type()%>]
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
						</tr>
					</tbody>
				</table>
				<table>
					<tr>
						<td>
							<h4>
								<a
									href="<%=request.getContextPath()%>/back/news/listAllNews.jsp"><img
									src="<%=request.getContextPath()%>/images/back1.gif"
									width="100" height="32" border="0">回房市最新消息</a>
							</h4>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<!-- 房市最新消息=====================================================================================-->



	<!-- 房市最新消息 ========================================================================================= -->
	<!-- 	<div class="container cooper_house"> -->
	<!-- 		<div class="row"> -->
	<!-- 			<div class="col-xs-12 col-sm-12 table_bgcolor"> -->
	<!-- 				<table class="table table-hover table_main"> -->
	<!-- 					<caption class="title_pr"> -->
	<!-- 						房市最新消息 <a href='#house_jump' data-toggle="modal" -->
	<!-- 							class="btn btn_cooper modal_jump cooper_ann_href"> 新增房市最新消息 </a> -->
	<!-- 					</caption> -->
	<!-- 					<thead> -->
	<!-- 						<tr class="col_title"> -->
	<!-- 							<th>新聞編號</th> -->
	<!-- 							<th>新聞種類</th> -->
	<!-- 							<th>新聞標題</th> -->
	<!-- 							<th>新聞內容</th> -->
	<!-- 							<th>新聞狀態</th> -->
	<!-- 							<th>新聞照片</th> -->
	<!-- 							<th>新增時間</th> -->
	<!-- 							<th>員工編號</th> -->
	<!-- 						</tr> -->
	<!-- 					</thead> -->
	<!-- 					<tbody> -->
	<!-- 						<tr class="col_name"> -->
	<%-- 							<td>${newsVO.news_no}</td> --%>
	<%-- 							<td>${newsVO.ntype_no}</td> --%>
	<%-- 							<td class="text_overflow">${newsVO.news_title}</td> --%>
	<%-- 							<td class="text_overflow">${newsVO.news_content}</td> --%>
	<!-- 							<td> -->
	<%-- 								<div class="dropdown">${newsVO.news_state} --%>
	<!-- 									<select class="form-control btn_cooper" id="sel1"> -->
	<!-- 									<option class="onitem">公告中</option> -->
	<!-- 									<option class="offitem">已撤銷</option> -->
	<!-- 									</select> -->
	<!-- 								</div> -->
	<!-- 							</td> -->
	<!-- 							<td><div class="" style="height: 145.8px; width: 194px;"> -->
	<!-- 									<img style="height: 145.8px; width: 194px;" -->
	<%-- 										src="<%=request.getContextPath()%>/tool/showimage.do?action=news_photo&news_no=${newsVO.news_no}"> --%>
	<!-- 								</div></td> -->
	<%-- 							<td>${newsVO.news_date}</td> --%>
	<%-- 							<td>${newsVO.emp_no}</td> --%>
	<!-- 						</tr> -->
	<!-- 					</tbody> -->
	<!-- 				</table> -->
	<!-- 				<table> -->
	<!-- 					<tr> -->
	<!-- 						<td> -->
	<!-- 							<h4> -->
	<!-- 								<a -->
	<%-- 									href="<%=request.getContextPath()%>/back/news/listAllNews.jsp"><img --%>
	<%-- 									src="<%=request.getContextPath()%>/images/back1.gif" --%>
	<!-- 									width="100" height="32" border="0">回房市最新消息</a> -->
	<!-- 							</h4> -->
	<!-- 						</td> -->
	<!-- 					</tr> -->
	<!-- 				</table> -->
	<!-- 			</div> -->
	<!-- 		</div> -->
	<!-- 	</div> -->
	<!-- 房市最新消息=====================================================================================-->




	<!-- 以上是你可以放的內容 ================================================================================== -->

	<!-- 一定要留住的</div> ================================================================================== -->
</div>
<!-- 一定要留住的</div> ================================================================================== -->
