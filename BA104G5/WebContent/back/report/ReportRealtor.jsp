<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.realtor.model.*"%>
<%@ page import="com.reportrealtor.model.*"%>
<%@ page import="java.text.SimpleDateFormat"%>

<%
EmployeeVO empVO = (EmployeeVO) session.getAttribute("empVO");
if (empVO == null) {                                             // 如為 null, 代表此user未登入過 , 才做以下工作
    session.setAttribute("location", request.getRequestURI());       //*工作1 : 同時記下目前位置 , 以便於login.jsp登入成功後 , 能夠直接導至此網頁(須配合LoginHandler.java)
    response.sendRedirect(request.getContextPath()+"/back/bklogin.jsp");   //*工作2 : 請該user去登入網頁(bklogin.jsp) , 進行登入
    return;
  }

RealtorService rtrSvc = new RealtorService();
pageContext.setAttribute("rtrSvc",rtrSvc);

RptrtrService rptrtrSvc = new RptrtrService();

List<RptrtrVO> newList = rptrtrSvc.getByState("處理中");
pageContext.setAttribute("newList",newList);

List<RptrtrVO> oldList = rptrtrSvc.getByState("已結案");
pageContext.setAttribute("oldList",oldList);

SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
pageContext.setAttribute("sdf",sdf);

%>

<head>
<title>審核檢舉</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/tools/sweetalert2/dist/sweetalert2.min.css">
<script src='<%=request.getContextPath()%>/tools/sweetalert2/dist/sweetalert2.all.min.js '></script>
<script src="<%=request.getContextPath()%>/tools/sweetalert2/dist/sweetalert2.min.js"></script>

<style >
#rightPanel {
	margin-left: 10px;
	width: 1500px;
}

.table {
	font-size: 24px;
}

.nav-pills li {
	background-color: #00f1e33d;
	width: 60px;
	text-align: center;
}

#panelTitle1 , #panelTitle2 {
	font-size: 28px;
}

</style>

</head>

<body>
<!-- 後端include -->
<jsp:include page="/back/backend/backend_page.jsp" flush="true" />
<!-- 後端include -->



<!-- 一定要留住的<div> ================================================================================== -->
<div class="col-xs-12 col-sm-10 maincontext">
<!-- 一定要留住的<div> ================================================================================== -->

	
	<h2 class="text-center">檢舉房仲案件</h2>
	

<div class="container" id="rightPanel">
	<div class="row">
		<div class="col-sm-12">
			<div role="tabpanel">
			    <!-- 標籤面板：標籤區 -->
			    <ul class="nav nav-pills" role="tablist">
			        <li role="presentation">
			           <h4><a href="#tab4" aria-controls="tab4" role="tab" data-toggle="tab">最新</a></h4>
			        </li>
			        <li role="presentation">
			            <h4><a href="#tab5" aria-controls="tab5" role="tab" data-toggle="tab">歷史</a></h4>
			        </li>
			    </ul>
			
			    <!-- 標籤面板：內容區 -->
			    <div class="tab-content">
			        <div role="tabpanel" class="tab-pane active" id="tab4">
			        	<div class="panel panel-primary">
						  <div class="panel-heading">
						    <h3 class="panel-title" id="panelTitle1">最新檢舉案件</h3>
						  </div>
						  <!-- <div class="panel-body"> -->
						    <c:if test="${empty newList}">
								<h2 class="text-center">&lt;目前無檢舉案件&gt;</h2>
							</c:if>
							
							<c:if test="${not empty newList}">
							<table class="table table-hover" border="1" id="rptprdVO" style="border:3px #cccccc solid;" cellpadding="5">
								<tr height="35px">
									<th class="text-center">案件編號</th>
									<th class="text-center">檢舉人</th>
									<th class="text-center">被檢舉房仲</th>
									<th class="text-center">檢舉原因</th>
									<th class="text-center">檢舉時間</th>
									<th class="text-center">處理</th>
								</tr>
								
								<c:forEach var="rptrtrVO" items="${newList}">
								
								<tr height="35px">
									<td class="text-center">${rptrtrVO.rr_no}</td>  <!-- 案件編號 -->
									<td class="text-center">${rptrtrVO.mem_no}</td>  <!-- 檢舉人 -->
									
									<td class="text-center">
										<a class="btn btn-lg btn-warning" role="button" data-toggle="collapse" href="#${rptrtrVO.rtr_no}">
											${rptrtrVO.rtr_no}
										</a>
										<div class="collapse" id="${rptrtrVO.rtr_no}">
											<div class="panel panel-primary">
											  <div class="panel-heading">
											    <h3 class="panel-title">房仲資訊</h3>
											  </div>
											   <table class="table table-hover">
											   		<tr>
											   			<th class="text-center" width="100px">姓名</th> <td>${rtrSvc.getOne(rptrtrVO.rtr_no).rtr_name}</td>
											   		</tr>
											   		<tr>
											   			<th class="text-center">簡介</th> <td>${rtrSvc.getOne(rptrtrVO.rtr_no).rtr_intro}</td>
											   		</tr>
											   		<tr>
											   			<th class="text-center">圖片</th>
											   			<td><img src="<%=request.getContextPath()%>/front/tools/showimage.do?action=rtr_photo&rtr_no=${rptrtrVO.rtr_no}" height="120px"></td>
											   		</tr>
											   </table>
											</div>
										</div>
									</td>
									
									<td class="text-center">${rptrtrVO.rr_reason}</td>  <!-- 檢舉原因 -->
									<td class="text-center">${sdf.format(rptrtrVO.rr_time)}</td>  <!-- 檢舉時間 -->
									<td class="text-center"><br>
										<FORM class="stopRealtor" METHOD="post" ACTION="<%=request.getContextPath()%>/front/realtor/realtor.do">
										     <input type="submit" value="停權帳號" class="btn btn-danger reportBtn"> 
										     <input type="hidden" class="rtr_no" name="rtr_no" value="${rptrtrVO.rtr_no}">
										     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
										     <input type="hidden" name="action"	value="stopRealtor"></FORM>
										     
										<FORM class="closeReport" METHOD="post" ACTION="<%=request.getContextPath()%>/front/realtor/RptRtr">
										     <input type="button" value="結案註記" class="btn btn-info reportBtn"> 
										     <input type="hidden" class="emp_no" name="emp_no" value="<%=empVO.getEmp_no()%>">
										     <input type="hidden" class="rr_no" name="rr_no" value="${rptrtrVO.rr_no}">
										     <input type="hidden" class="rr_result" name="rr_result" value="">
										     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
										     <input type="hidden" name="action"	value="closeReport"></FORM>
									</td>  <!-- 處理按鈕 -->
								</tr>
								</c:forEach>
							</table>
							</c:if>
						</div>
			    	</div><!-- 最新檢舉案件 end -->
			    	
			    	
			        <div role="tabpanel" class="tab-pane" id="tab5">
			        	<div class="panel panel-primary">
						  <div class="panel-heading">
						    <h3 class="panel-title" id="panelTitle2">已處理案件</h3>
						  </div>
						  <!-- <div class="panel-body"> -->
						    <c:if test="${empty oldList}">
								<h2 class="text-center">&lt;未有已處理案件&gt;</h2>
							</c:if>
							
							<c:if test="${not empty oldList}">
							<table class="table table-hover" border="1" id="rptprdVO" style="border:3px #cccccc solid;" cellpadding="5">
								<tr height="35px">
									<th class="text-center">案件編號</th>
									<th class="text-center">檢舉人</th>
									<th class="text-center">被檢舉房仲</th>
									<th class="text-center">檢舉原因</th>
									<th class="text-center">檢舉時間</th>
									<th class="text-center">處理員工</th>
									<th class="text-center">處理註記</th>
								</tr>
								
								<c:forEach var="oldRptrtrVO" items="${oldList}">
								
								<tr height="80px">
									<td class="text-center">${oldRptrtrVO.rr_no}</td>  <!-- 案件編號 -->
									<td class="text-center">${oldRptrtrVO.mem_no}</td>  <!-- 檢舉人 -->
									
									<!-- 檢舉房仲 start-->
									<td class="text-center">
										${oldRptrtrVO.rtr_no}<br>
										${rtrSvc.getOne(oldRptrtrVO.rtr_no).rtr_name}
									</td>
									<!-- 檢舉房仲 end-->
									
									<td class="text-center">${oldRptrtrVO.rr_reason}</td>  <!-- 檢舉原因 -->
									<td class="text-center">${sdf.format(oldRptrtrVO.rr_time)}</td>  <!-- 檢舉時間 -->
									<td class="text-center">${oldRptrtrVO.emp_no}</td>  <!-- 處理員工 -->
									<td class="text-center">${oldRptrtrVO.rr_result}</td>  <!-- 處理註記 -->
								</tr>
									
								</c:forEach>
							</table>
							</c:if>
						</div>
			        </div> <!-- 已處理案件 end -->
			        
			    </div>
			</div>
		</div>
	</div>
</div>

<!-- 一定要留住的</div> ================================================================================== -->
</div>
<!-- 一定要留住的</div> ================================================================================== -->

<script>

	$(document).ready(function() {
		if(<%=request.getAttribute("closeSuccess")!=null%>){swal('<%=request.getAttribute("closeSuccess")%>',"",'success')}
		if(<%=request.getAttribute("stopDone")!=null%>){swal('房仲編號:<%=request.getAttribute("rtr_no")%>',"<h2>已停權!</h2>",'success')}
	});

	$(".reportBtn").click(function(){
		swal({
			  title: '輸入處理註記',
			  input: 'text',
			  showCancelButton: true,
			  confirmButtonText: 'Submit',
			  showLoaderOnConfirm: false,
			  allowOutsideClick: false
		}).then((result) => {
			  $(this).closest(".closeReport").find(".rr_result").val(result);
			  $(this).closest(".closeReport").submit();
			})
	});


</script>

		
</body>
</html>