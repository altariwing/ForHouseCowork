<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.slr.model.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.prdcategory.model.*"%>
<%@ page import="com.prdimg.model.*"%>
<%
  SlrVO slrVO = (SlrVO) session.getAttribute("slrVO");
  PrdVO prdVOtmp = (PrdVO) request.getAttribute("prdVOtmp");
  PrdVO prdVO = (PrdVO) request.getAttribute("prdVO");

  PcService pcSvc = new PcService();
  List<PcVO> list = pcSvc.getAll();
  pageContext.setAttribute("list",list);
%>
<jsp:useBean id="prdSvc" scope="page" class="com.product.model.PrdService" />
<jsp:useBean id="prdImgSvc" scope="page" class="com.prdimg.model.PrdImgService" />

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
    
<!-- 預覽上傳的圖片 -->
	<script src="<%=request.getContextPath()%>/front/seller/js/zxxFile.js"></script>
	
	<!-- 編輯器 -->
    <script src="<%=request.getContextPath()%>/tools/ckeditor/ckeditor.js"></script>
    
    
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

    #prdcatename {
    	width: 120px;
    }
    #PrdTbl {
    	width: 700px;
    }
    #submitBtn {
    	margin-left: 150px;
    	width: 550px;
    }
    .updateDiv {
    	margin-top: 120px;
    }
    #goback {
    	border: 2px solid #FFAC55;
    	backgroundcolor: #FFAC55;
    	border-radius: 8px;
    	margin: 0px auto;
    	width: 120px;
    	height: 40px;
    	color: white;
    	padding: 5px;
    }
    .imgtd {
    	text-align: center;
    	vertical-align: middle;
    }
    .deletebtn {
    	padding-top: 100px;
    }
    
    
    /* 預覽圖片的css */
.upload_box{width:400px; margin:1em auto;}
<%-- .upload_drag_area{display:inline-block; width:60%; padding:4em 0; margin-left:6em; border:1px dashed #ddd; background:#fff url(<%=request.getContextPath()%>/seller/images/drag.png) no-repeat 10px; color:#999; text-align:center; } --%>
.upload_preview{border-top:1px solid #bbb; border-bottom:1px solid #bbb; background-color:#fff; overflow:hidden; _zoom:1;}
.upload_delete{margin-left:2em;}
.upload_image{max-height:250px; padding:5px;}
.upload_loading{height:200px; no-repeat center;}
.upload_progress{display:none; padding:5px; border-radius:10px; color:#fff; background-color:rgba(0,0,0,.6); position:absolute; left:25px; top:45px;}
    
    </style>
</head>

<body>

<nav class="navbar navbar-fixed-top main">
	<jsp:include page="/front/seller/navbar.jsp" />
</nav>




<div class="row">
            
    <!-- include 左側選單區塊  開始-->
	<jsp:include page="/front/seller/leftpanel.jsp" />
	<!-- include 左側選單區塊  結束-->
            

<!-- 右側內容區塊 開始-->
<!-- 更換的程式碼放下面 -->
<div class="col-md-5 updateDiv">
	<div class="row">
		<div class="col-md-8 text-center">
		<h3>修改商品</h3>
		</div>
		<div class="col-md-2 text-center">
			<a href="<%=request.getContextPath()%>/front/seller/product/PrdList.jsp">
				<div id="goback" style="font-size:1.5em;background-color:#FFAC55">回上頁</div>
			</a>
		</div>
	</div>
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
	
	<form id="uploadForm" action="prd.do" method="post" name="addprdform" enctype="multipart/form-data">
		<table class="table table-hover" border="0" id="PrdTbl">
			<tr>
				<th>商品編號</th>
				<td>${prdVO.prd_no}</td>
			</tr>
			<tr>
				<th>商品類別 </th>
				<td><select class="form-control" id="prdcatename" size="1" name="cate_no">
					<c:forEach var="pcVO" items="${list}">
						<option value="${pcVO.cate_no}" ${(pcVO.cate_no==prdVO.cate_no)? "selected" : ""} >${pcVO.cate_name}</option>
					</c:forEach>
				</select></td>
			</tr>
			<tr>
				<th>商品名稱</th>
				<td><input type="text" size="45" name="prd_name" id="prd_name" value='${prdVO.prd_name}'></td>
			</tr>
			<tr>
				<th>商品詳情</th>
				<td><textarea class="form-control" rows="5" id="prd_description" name="prd_description">${prdVO.prd_desc}</textarea>
					<input type="hidden" id="prd_desc" name="prd_desc">
				</td>
				
			</tr>
			<tr>
				<th>數量</th>
				<td><input type="number" size="45" name="prd_stock" id="prd_stock" value='${prdVO.prd_stock}'></td>
			</tr>
			<tr>
				<th>單價</th>
				<td><input type="number" size="45" name="prd_price" id="prd_price" value='${prdVO.prd_price}'></td>
			</tr>
			<tr>
				<th>上傳更多圖片</th>
				<td><input id="fileImage" type="file" size="30" name="fileselect[]" multiple accept="image/*">
				
				</td>
			</tr>
		</table>
		<input type="hidden" name="prd_no" value="${prdVO.prd_no}">
		<input type="hidden" name="action" value="update">
		<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
		<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">  <!--用於PrdList.jsp-->
		<input type="submit" class="btn btn-primary btn-lg" id="submitBtn" value="確認修改">
		
	</form>
	
</div>	
		
	<div class="col-md-3 updateDiv">
		
		<div style="font-size:1.5em" class="text-center">商品圖片</div>
		
		<div id="preview" class="upload_preview text-center"></div>  <!-- 顯示預覽圖片 -->
		
		<c:if test="${empty imgListAfterDelOne}">
		<table class="table table-hover" border="1" style="border:3px #cccccc solid;">
			<c:forEach var="prdImgVO" items="${prdImgSvc.findByPrdNo(prdVO.prd_no)}">
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/seller/product/prd.do" class="form-inline">
				<tr>
					<td class="imgtd">
					<img src="<%=request.getContextPath()%>/DBImgReader?img_no=${prdImgVO.img_no}" height="260px">
					</td>
					<td class="imgtd"><div class="deletebtn">
					<input type="submit" value="刪除" class="btn btn-default">
					<input type="hidden" name="prd_no" value="${prdVO.prd_no}">
					<input type="hidden" name="prd_name" value="${prdVO.prd_name}">
					<input type="hidden" name="prd_desc" value='${prdVO.prd_desc}'>
					<input type="hidden" name="prd_stock" value="${prdVO.prd_stock}">
					<input type="hidden" name="prd_price" value="${prdVO.prd_price}">
				    <input type="hidden" name="img_no" value="${prdImgVO.img_no}">
				    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
				    <input type="hidden" name="action"	value="deleteImg">
					</div></td>
			    </tr>
			    </FORM>
			</c:forEach>
		</table>
		</c:if>
			
		<c:if test="${not empty imgListAfterDelOne}">
		<table class="table table-hover" border="1" style="border:3px #cccccc solid;">
			<c:forEach var="prdImgVO" items="${imgListAfterDelOne}">
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/seller/product/prd.do" class="form-inline">
				<tr>
					<td class="imgtd">
						<img src="<%=request.getContextPath()%>/DBImgReader?img_no=${prdImgVO.img_no}" height="260px">
					</td>
					<td class="imgtd"><div class="deletebtn">
						<input type="submit" value="刪除" class="btn btn-default">
						<input type="hidden" name="prd_no" value="${prdVO.prd_no}">
						<input type="hidden" name="prd_name" value="${prdVO.prd_name}">
						<input type="hidden" name="prd_desc" value='${prdVO.prd_desc}'>
						<input type="hidden" name="prd_stock" value="${prdVO.prd_stock}">
						<input type="hidden" name="prd_price" value="${prdVO.prd_price}">
					    <input type="hidden" name="img_no" value="${prdImgVO.img_no}">
					    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
					    <input type="hidden" name="action"	value="deleteImg">
				    </div></td>
			    </tr>
			    </FORM>
			</c:forEach>
		</table>
		</c:if>
				
	</div>
		
</div>
<!-- 更換的程式碼放上面 -->        
<!-- 右側區塊 結束-->






<jsp:include page="/front/seller/footer.jsp" />

<!-- 編輯器的 -->
<script type="text/javascript">
			$(document).ready(function(){
				CKEDITOR.replace( 'prd_description' );
				
				$("#prd_stock").focus(function(){	
					$("#prd_desc").val(CKEDITOR.instances["prd_description"].getData());
					//console.log($("#prd_desc").val());
				});
			});
</script>

<script>
var params = {
	fileInput: $("#fileImage").get(0),
	dragDrop: $("#fileDragArea").get(0),
	
	url: $("#uploadForm").attr("action"),
	filter: function(files) {
		var arrFiles = [];
		for (var i = 0, file; file = files[i]; i++) {
			if (file.type.indexOf("image") == 0) {
				if (file.size >= 512000) {
					alert('您這張"'+ file.name +'"圖片size太大，請選擇小於1MB的檔案');	
				} else {
					arrFiles.push(file);	
				}			
			} else {
				alert('此檔案 "' + file.name + '" 不是圖片。');	
			}
		}
		return arrFiles;
	},
	onSelect: function(files) {
		var html = '', i = 0;
		$("#preview").html('<div class="upload_loading"></div>');
		var funAppendImage = function() {
			file = files[i];
			if (file) {
				var reader = new FileReader()
				reader.onload = function(e) {
					html = html + '<div>&nbsp</div><div id="uploadList_'+ i +'" class="upload_append_list"><p>' + 
						'<a href="javascript:" class="upload_delete" title="删除" data-index="'+ i +'">删除</a><br />' +
						'<img id="uploadImage_' + i + '" src="' + e.target.result + '" class="upload_image" /></p>'+ 
						'<span id="uploadProgress_' + i + '" class="upload_progress"></span>' +	'<div><hr></div><div> </div></div>';
					
					i++;
					funAppendImage();
				}
				reader.readAsDataURL(file);
			} else {
				$("#preview").html(html);
				if (html) {
					//删除方法
					$(".upload_delete").click(function() {
						ZXXFILE.funDeleteFile(files[parseInt($(this).attr("data-index"))]);
						return false;	
					});
						
				} 
			}
		};
		funAppendImage();		
	},
	onDelete: function(file) {
		$("#uploadList_" + file.index).fadeOut();
		$('#uploadForm')[0].reset();
	},
	onProgress: function(file, loaded, total) {
		var eleProgress = $("#uploadProgress_" + file.index), percent = (loaded / total * 100).toFixed(2) + '%';
		eleProgress.show().html(percent);
	},
	onFailure: function(file) {
		$("#uploadImage_" + file.index).css("opacity", 0.2);
	},
	onComplete: function() {
		//file控件value清空
		$('#uploadForm')[0].reset();
	}
};
ZXXFILE = $.extend(ZXXFILE, params);
ZXXFILE.init();
</script>

</body>
</html>