<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.slr.model.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.prdcategory.model.*"%>
<%
  SlrVO slrVO = (SlrVO) session.getAttribute("slrVO");
  PrdVO prdVOtmp = (PrdVO) request.getAttribute("prdVOtmp");
  if (slrVO == null) {                                             // 如為 null, 代表此user未登入過 , 才做以下工作
    session.setAttribute("location", request.getRequestURI());       //*工作1 : 同時記下目前位置 , 以便於login.jsp登入成功後 , 能夠直接導至此網頁(須配合controller)
    response.sendRedirect(request.getContextPath()+"/seller/login.jsp");   //*工作2 : 請該user去登入網頁(login.jsp) , 進行登入
    return;
  }
  PcService pcSvc = new PcService();
  List<PcVO> list = pcSvc.getAll();
  pageContext.setAttribute("list",list);
%>
<jsp:useBean id="prdSvc" scope="page" class="com.product.model.PrdService" />


<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>廠商會員中心</title>
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/images/houselogo1.png" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front/seller/css/main.css">
    <script src="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    
    <script src="https://code.jquery.com/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/front/seller/js/zxxFile.js"></script>
	<script src="<%=request.getContextPath()%>/tools/ckeditor/ckeditor.js"></script>
    
    <!-- 神奇小按鈕的script 開始-->
    <script>
        $(document).ready(function() {
            $("#magicBtn1").click(function() {
                $("#prd_name").val("筆電升降工作台(金鋼黑)");
                CKEDITOR.instances['prd_description'].setData("◆通過TUV德國萊茵認證<br>◆符合人體工學<br>◆專利氣壓調節設計<br>◆超輕薄 可自在移動位置<br>◆筆電專用");
                $("#prd_stock").val("27");
                $("#prd_price").val("3680");
            });
            $("#magicBtn2").click(function() {
                $("#prd_name").val("拼布風沙發");
                CKEDITOR.instances['prd_description'].setData("◆經典紅白藍三色拼接<br>◆異國色彩，麻繩手工走編<br>◆麻布質地，天然親膚");
                $("#prd_stock").val("12");
                $("#prd_price").val("4850");
            });
            $("#magicBtn3").click(function() {
                $("#prd_name").val("實木傢俱:自然行書櫃");
                CKEDITOR.instances['prd_description'].setData("◆採用北美天然扁柏(檜木類)原木<br>◆與永續林放射松健康合板<br>◆主結構手工卡榫製成安全結構<br>◆採用安全環保塗裝<br>◆細緻天然原木接觸面<br>■原木免組裝<br>◆大單抽屜/四層置物<br>◆餐櫥雜貨/圖書收納");
                $("#prd_stock").val("5");
                $("#prd_price").val("8990");
            });
        });
    </script>
    <!-- 神奇小按鈕的script 結束-->
    
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
    .addDiv {
    	margin-top: 120px;
    	margin-left: 25px;
    }

    #prdcatename {
    	width: 120px;
    }
    #addPrdTbl {
    	width: 600px;
    }
    th {
    	width: 18%;
    }
    #submitBtn {
    	width: 600px;
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
<div class="col-md-4 addDiv">
		
	<h3>上架新商品</h3>
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
		<table class="table table-hover" border="0" id="addPrdTbl">
			<tr>
				<th>商品類別 </th>
				<td><select class="form-control" id="prdcatename" size="1" name="cate_no">
					<c:forEach var="pcVO" items="${list}">
						<option value="${pcVO.cate_no}" ${(pcVO.cate_no==prdVOtmp.cate_no)? "selected" : ""} >${pcVO.cate_name}</option>
					</c:forEach>
				</select></td>
			</tr>
			<tr>
				<th>商品名稱</th>
				<td><input type="text" size="45" name="prd_name" id="prd_name" value='<%=(prdVOtmp==null)? "" : prdVOtmp.getPrd_name()%>'></td>
			</tr>
			<tr>
				<th>商品詳情</th>
				<td><textarea class="form-control" rows="5" id="prd_description" name="prd_description"><%=(prdVOtmp==null)? "" : prdVOtmp.getPrd_desc()%></textarea>
					<input type="hidden" id="prd_desc" name="prd_desc">
				</td>
				
			</tr>
			<tr>
				<th>數量</th>
				<td><input type="number" size="45" name="prd_stock" id="prd_stock" value='<%=(prdVOtmp==null)? "" : prdVOtmp.getPrd_stock()%>'></td>
			</tr>
			<tr>
				<th>單價</th>
				<td><input type="number" size="45" name="prd_price" id="prd_price" value='<%=(prdVOtmp==null)? "" : prdVOtmp.getPrd_price()%>'></td>
			</tr>
			<tr>
				<th>上傳圖片</th>
				<td>
				<input id="fileImage" type="file" size="30" name="fileselect[]" multiple accept="image/*" />
				
				</td>
			</tr>
		</table>
		<input type="hidden" name="slr_no" value="<%=slrVO.getSlr_no()%>">
		<input type="hidden" name="action" value="insert">
		<input type="submit" class="btn btn-primary btn-lg" id="submitBtn" value="送出新增">
	</form>


	<div class="col-sm-4 magicdiv">
        <button type="button" class="btn btn-default btn-xs" id="magicBtn1">貼1</button>
        <button type="button" class="btn btn-default btn-xs" id="magicBtn2">貼2</button>
        <button type="button" class="btn btn-default btn-xs" id="magicBtn3">貼3</button>
    </div>		


</div>
	
<div class="col-md-3 addDiv">
	<div id="preview" class="upload_preview"></div>
</div>
<!-- 更換的程式碼放上面 -->        
<!-- 右側區塊 結束-->


</div>



<jsp:include page="/front/seller/footer.jsp" />

<!-- 編輯器的 -->
<script type="text/javascript">
			$(document).ready(function(){
				CKEDITOR.replace( 'prd_description' );
				
				$("#prd_stock").focus(function(){	
					$("#prd_desc").val(CKEDITOR.instances['prd_description'].getData());
					console.log($("#prd_desc").val());
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
						'<a href="javascript:" class="upload_delete" title="删除" data-index="'+ i +'"><h4>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp删除</h4></a><br />' +
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