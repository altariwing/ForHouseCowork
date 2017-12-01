<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.realtor.model.*"%>
<%@ page import="com.realestate.model.*"%>
<%@ page import="com.article.model.*"%>

<jsp:useBean id="realestateSvc" scope="page" class="com.realestate.model.RealEstateService" />
<jsp:useBean id="articleSvc" scope="page" class="com.article.model.ArticleService" />

<%
	RealtorService realtorSvc = new RealtorService();
	RealtorVO realtorVO = (RealtorVO) session.getAttribute("realtorVO");
	
	String No = realtorVO.getRtr_no();
	Set<ArticleVO> list = null;
	list = realtorSvc.getArtByRtrNo(No);
	
	pageContext.setAttribute("list", list);
	
// 	if ((RealtorVO) session.getAttribute("realtorVO") != null) {
// 		RealtorVO realtorVO = (RealtorVO) session.getAttribute("realtorVO");
// 		String No = realtorVO.getRtr_no();
// 		list = realtorSvc.getArtByRtrNo(No);
// 		pageContext.setAttribute("list", list);
// 	} else {
// 		ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO");
// 		String No = articleVO.getRtr_no();
// 		list = realtorSvc.getArtByRtrNo(No);
// 		pageContext.setAttribute("list", list);
// 		out.print(No);
// 	}

	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>


<jsp:include page="/front/realtor/blogTop.jsp" />


<!-- 標籤面板：內容區 -->
<div class="tab-content">
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<div class="container" style="margin-top: 1em;">
		<div class="row">
			<div class="col-sm-6 col-sm-offset-3">
				<%@ include file="page_front_realtor/pageart.file"%>
			</div>
		</div>
	</div>

	<!-- 房仲簡介本體 -->
	<div class="container">
		<div class="row">
			<div class="panel panel-default col-sm-12  col-sm-8 col-sm-offset-2">
				<div class="panel-heading form-group">
					<h4>房仲簡介</h4>
					<div>
						<img
							src="<%=request.getContextPath()%>/front/tools/showimage.do?action=rtr_photo&rtr_no=${realtorVO.rtr_no}"
							class="img-thumbnail" style="width: 100%;">
					</div>
				</div>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/front/article/article.do"
					name="form1">
					<div class="form-group">
						<label for="rtr_name">房仲名稱</label>
						<div>
							<h5>${realtorVO.rtr_name}</h5>
						</div>
					</div>
					<div class="form-group">
						<label for="post_date">房仲公司</label>
						<div>
						
							<h5 class="">${realestateSvc.getOne(rtr_no).getRe_name()}</h5>
						</div>
					</div>
					<div class="form-group">
						<label for="post_date">服務地區</label>
						<div>
							<h5 class="">${realtorVO.rtr_area}</h5>
						</div>
					</div>
					<div class="form-group">
						<label for="rtr_intro">簡介</label>
						<textarea class="form-control" rows="7" readonly
							style="cursor: no-drop;" name="rtr_intro">${realtorVO.getRtr_intro()}</textarea>
					</div>
					<div class="input-group" style="margin-bottom: 10px;">
						<span class="input-group-btn"> </span>
					</div>
				</FORM>
			</div>
		</div>
	</div>
	<!-- 房仲簡介本體 -->

	<!-- 自身文章資料 =====================================-->
	<c:forEach var="articleVO" items="${list}" begin="<%=pageIndex%>"
		end="<%=pageIndex+rowsPerPage-1%>">
		<div class="container">
			<div class="row">
				<div class="panel panel-default col-sm-12  col-sm-8 col-sm-offset-2">
					<div class="panel-heading form-group">
						<h4>文章</h4>
					</div>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/front/article/article.do"
						name="form1">
						<div class="form-group">
							<label for="rtr_name">房仲名稱</label>
							<div>
								<h5>${realtorVO.rtr_name}</h5>
								<img
									src="<%=request.getContextPath()%>/front/tools/showimage.do?action=rtr_photo&rtr_no=${realtorVO.rtr_no}"
									class="img-thumbnail" style="width: 80px; height: 80px;">
							</div>
						</div>
						<div class="form-group">
							<label for="article_body">文章內容</label>
							<textarea rows="3"  id="article_context"  class="form-control" style="cursor: text;" name="article_body">${articleVO.article_body}</textarea>
							<div class="form-group" id="update_btn">
								<div class="form-group">
									<label for="article_body">選擇公開與否?</label>
									<input type="radio" name="state_check" value="ON">好，我願意
									<input type="radio" name="state_check" value="OFF">才不給你看哩~
								</div>
								<input type="button" value="修改文章" onclick="update(event, '${articleVO.article_no}', '${articleVO.rtr_no}')">
							</div>
							<input type="hidden" value="${articleVO.article_no}">
							<input type="button" value="刪除文章" id="${articleVO.article_no}" onclick="deleteArt(event);">
						</div>
						<div class="form-group">
							<label for="post_date">發佈日期</label>
							<div>
								<h5 class="">${articleVO.post_date}</h5>
							</div>
						</div>

						<!-- <div class="input-group" style="margin-bottom: 10px;"> -->
						<!-- <textarea class="form-control" name=""></textarea> -->
						<!-- <span class="input-group-btn"> -->
						<!-- <button class="btn btn-primary" type="submit" style="padding: 16px;">留言</button>  -->
						<!-- <input type="hidden" name="action" value="insert">  -->
						<!-- <input type="hidden" name="post_date" value=""> -->
						<!-- </span> -->
						<!-- </div> -->
					</FORM>
				</div>
			</div>
		</div>
	</c:forEach>
</div>
<div class="container" style="margin-top: 1em;">
	<div class="row">
		<div class="col-sm-6 col-sm-offset-3">
			<%@ include file="page_front_realtor/pageart2.file"%>
		</div>
	</div>
</div>
</div>
</div>
</div>
</div>


<c:if test="${realtorVO != null}"> 
<script>
	$("#showActive3").addClass("active");
</script>
</c:if>



<!-- 刪除用 -->
<script type="text/javascript">
	function deleteArt(event){//一定要用event
		$.ajax({
			type:'post',
			url: '<%=request.getContextPath()%>/front/article/article.do',
			
			/*這邊如果傳這種型態過去
			*controller一定要有東西接
			*要不然會選擇走下面的error
			*還是會執行，只是會被誤導....
			*/
			dataType: "json",
			data: {
				action: 'delete',
				article_no: event.target.id //一定要用event
			},
			success: function(result){
				alert("你知道有些事情，就像潑出去的水一樣收不回來嗎?");
				alert("已經刪除囉，不能回到過去了^^");
				//設置時間自動挑轉
				setTimeout(function(){location.reload()}, 1000);
			},
			error: function(xhr){
				alert('不能刪除喔');
			}
		});
	}
</script>
 <!-- ajax更新資料用 -->
    <script type="text/javascript">
    //更新資料用
    function update(event, artNo, rtrNo) {
    	
    	//選取checkbox裡面的值
     	var state = $("input:checked").val();
          
    	$.ajax({
            type: 'post',
            url: '<%=request.getContextPath()%>/front/article/article.do',
            data: {
                action: 'update',
                article_no: artNo,
                article_state: state,
                rtr_no: rtrNo,
                article_body: $('#article_context').val(),
                //img: $('#show_base64').val()
            },
            success: function(result) {
                alert('已修改完成');
              	//設置時間自動挑轉
			// setTimeout(function(){location.reload()}, 1000);
            },
            error: function(xhr) {
                alert('修改文章失敗');
            }
        });
    }
    //base64圖片用
    function InputLoadImageToBindImageElement(uploadElement, targetElement) {
        if (uploadElement.files && uploadElement.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e) {
                $(targetElement).val(e.target.result);
            }
            reader.readAsDataURL(uploadElement.files[0]);
        }
    }

    $("#upload_img").change(function() {
        InputLoadImageToBindImageElement(this, $('#show_base64'));
    });
    </script>
    



<!-- 回到最上面    -->
<div id="gotop">˄</div>



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
</html>

