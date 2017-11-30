<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.slr.model.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.prdcategory.model.*"%>
<%@ page import="com.prdimg.model.*"%>
<%
  SlrVO slrVO = (SlrVO) session.getAttribute("slrVO");
 
  if (slrVO == null) {                                             // 如為 null, 代表此user未登入過 , 才做以下工作
    session.setAttribute("location", request.getRequestURI());       //*工作1 : 同時記下目前位置 , 以便於login.jsp登入成功後 , 能夠直接導至此網頁(須配合controller)
    response.sendRedirect(request.getContextPath()+"/seller/login.jsp");   //*工作2 : 請該user去登入網頁(login.jsp) , 進行登入
    return;
  }

  PrdService prdSvc = new PrdService();
  List<PrdVO> list = prdSvc.findBySlrNo(slrVO.getSlr_no());     //商品
  pageContext.setAttribute("list",list);
    
%>
<jsp:useBean id="pcSvc" scope="page" class="com.prdcategory.model.PcService" />
<jsp:useBean id="prdImgSvc" scope="page" class="com.prdimg.model.PrdImgService" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>廠商會員中心</title>
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/images/houselogo1.png" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front/seller/css/main.css">
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    
	<script src="<%=request.getContextPath()%>/front/seller/js/zxxFile.js"></script>
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.0.0/sweetalert2.all.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.0.0/sweetalert2.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.0.0/sweetalert2.min.css"></script>
	
	<script>
		$(document).ready(function() {
			if(<%=request.getAttribute("updateSuccess")!=null%>){
				swal('<%=request.getAttribute("updateSuccess")%>', '','success')
				}
			if(<%=request.getAttribute("paymentSuccess")!=null%>){
				swal('<%=request.getAttribute("paymentSuccess")%>', '','success')
				}
		});
	</script>
	
	<script>
		$(document).ready(function() {
			if(<%=request.getAttribute("keyPrdNo")!=null%>){swal("新增成功!", '<h2>商品編號:<%=request.getAttribute("keyPrdNo")%><h2>','success')}
		});
	</script>
	
	<script>
		$(document).ready(function() {
			if(<%=request.getAttribute("stopSell")!=null%>){swal('商品編號:<%=request.getAttribute("prd_no")%>',"<h2>已下架!</h2>",'success')}
		});
	</script>
	
    
    <style type="text/css">
    body {
		font-family: Arial, Verdana, Helvetica, "LiHei Pro Medium", 微軟正黑體, sans-serif;
	}
    .panel-title {
        font-size: 24px;
    }
    .panelboxtitle{
        margin-top:-1.2em;
    }
    .areaInner{
        margin-top: 140px;
    }
    h2.panel-title {
        font-size: 24px;
    }
    #PrdList {
    	width: 1300px;
    	pading: 1em;
    }
    #imgDiv {
    	width: 200px;
    	margin-left: 20px;
    	margin-bottom: 10px;
    }
    #btnDiv {
    	width: 60px;
    }
  
    
    </style>
</head>

<body>

<nav class="navbar navbar-fixed-top main">
	<jsp:include page="/front/seller/navbar.jsp" />
</nav>



<div class="container-fluid">
    <div class="row">
            
    <!-- include 左側選單區塊  開始-->
	<jsp:include page="/front/seller/leftpanel.jsp" />
	<!-- include 左側選單區塊  結束-->
            

<!-- 右側內容區塊 開始-->
<!-- 更換的程式碼放下面 -->
<div class="col-xs-12 col-sm-7 maincontext" id="dashboard">
		
	<h3 class="text-center">上架商品列表</h3>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font color='red'>請修正以下錯誤:
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li>${message}</li>
			</c:forEach>
		</ul>
		</font>
	</c:if>
	
	
	<table class="table table-hover" border="1" id="PrdList" style="border:3px #cccccc solid;">
		<tr align="center" valign="middle">
			<th width=90px class="text-center">商品編號</th>
			<th width=70px class="text-center">商品類別 </th>
			<th width=90px class="text-center">商品名稱</th>
			<th width=180px class="text-center">商品詳情</th>
			<th width=60px class="text-center">數量</th>
			<th width=80px class="text-center">單價</th>
			<th width=300px class="text-center">圖片</th>
			<th width=60px class="text-center">修改</th>
			<th width=60px class="text-center">下架</th>
		</tr>
		
		<%@ include file="pages/page1.file" %> <p>
		<c:forEach var="prdVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr valign='middle' ${(prdVO.prd_no==param.prd_no) ? 'bgcolor=#FFE4C4':''}><!--將修改的那一筆加入對比色而已-->
			<td align='center'>${prdVO.prd_no}</td>
			<td align='center'><c:forEach var="pcVO" items="${pcSvc.all}">
                    <c:if test="${prdVO.cate_no==pcVO.cate_no}">
	                    ${pcVO.cate_name}
                    </c:if>
                </c:forEach>
            </td>
			<td align='center'>${prdVO.prd_name}</td>
			<td>
				${prdVO.prd_desc}
			</td>
			<td align='center'>${prdVO.prd_stock}</td>
			<td align='center'>${prdVO.prd_price}</td>
			<td> <!-- 讀取該商品的所有photo -->
				<c:forEach var="prdImgVO" items="${prdImgSvc.findByPrdNo(prdVO.prd_no)}">
						<img src="<%=request.getContextPath()%>/DBImgReader?img_no=${prdImgVO.img_no}" height="120px">
				</c:forEach>
			</td>
			<td align='center'>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/seller/product/prd.do">
			     <input type="submit" value="修改" class="btn btn-info"> 
			     <input type="hidden" name="prd_no" value="${prdVO.prd_no}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td align='center'>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/seller/product/prd.do">
			    <input type="submit" value="下架" class="btn btn-warning">
			    <input type="hidden" name="prd_no" value="${prdVO.prd_no}">
			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			    <input type="hidden" name="action"value="stopSell"></FORM>
			</td>
		</tr>
			
		</c:forEach>
	</table>
	<%@ include file="pages/page2.file" %>
                  
</div>
<!-- 更換的程式碼放上面 -->        
<!-- 右側區塊 結束-->


    </div>
</div>


<jsp:include page="/front/seller/footer.jsp" />

</body>
</html>