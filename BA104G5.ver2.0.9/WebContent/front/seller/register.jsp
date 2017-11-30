<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.slr.model.*"%>
<%
  SlrVO slrVOreg = (SlrVO) request.getAttribute("slrVOreg");
%>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/images/Houselogo1.png" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front/seller/css/main.css">
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<%--     <script src="<%=request.getContextPath()%>/front/seller/js/main.js"></script> --%>
    
    <!-- 神奇小按鈕的script 開始-->
    <script>
        $(document).ready(function() {
            $("#magicBtn1").click(function() {
                $("#slr_name").val("提拔米傢俱");
                $("#slr_taxid").val("88888888");
                $("#slr_id").val("tibame@gmail.com");
                $("#slr_psw").val("123456");
                $("#slr_psw2").val("123456");
                $("#slr_contact").val("米老鼠");
                $("#slr_phone").val("03-2345678");
                $("#slr_intro").val("聚焦雲端、行動、大數據、物聯網、數位金融、電商等專業領域及協助職涯成長的數位傢俱，提供多樣化型態的服務。");
                $("#aggrement2").attr("checked","enable");
            });
        });

    </script>
    <!-- 神奇小按鈕的script 結束-->
    
    <style type="text/css">
    body {
        font-family: Arial, Verdana, Helvetica, "LiHei Pro Medium", 微軟正黑體, sans-serif;
    }

    #systitle{
    	margin-top: 4em;
    	font-family: 微軟正黑體;
    	font-size: 200%;
    	color: #00ADEE;
    	font-weight: bold;
    }
    
    #outter2{
    	width: 60em;
    }
    
    #regform{
    	margin-top: 2em;
    }
    
    .savebutton{
    	width: 600px;
    }
    
    .magicdiv{
        margin-top: 8px;
    }
    </style>
    
</head>

<body>

<nav class="navbar navbar-fixed-top main">
	<jsp:include page="navbar.jsp" />
</nav>


	<div class="text-center" id="systitle">
		<h3>廠商註冊系統</h3>
	</div>

        

        <div class="container ctext-center" id="outter2">
        <div class="row">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h2 class="panel-title text-center">基本資料</h2>
                </div>
                <div class="panel-body">
                
                <%-- 錯誤表列 --%>
				<c:if test="${not empty regErrors}">
					<font style="color:red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${regErrors}">
						<li style="color:red">${message}</li>
					</c:forEach>
					</ul>
				</c:if>
		
                <form class="form-horizontal" action="slr.do" method="post" id="regform">
                <input type="hidden" name="action" value="slrRegister">
                    <div class="form-group">
                        <label for="slr_name" class="col-sm-2 control-label">公司名稱</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="slr_name" name="slr_name" placeholder="公司名稱" value="<%= (slrVOreg==null)? "" : slrVOreg.getSlr_name()%>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="slr_taxid" class="col-sm-2 control-label">公司統編</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="slr_taxid" name="slr_taxid" placeholder="公司統編" value="<%= (slrVOreg==null)? "" : slrVOreg.getSlr_taxid()%>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="slr_id" class="col-sm-2 control-label">登入帳號</label>
                        <div class="col-sm-9">
                            <input type="email" class="form-control" id="slr_id" name="slr_id" placeholder="請輸入e-mail" value="<%= (slrVOreg==null)? "" : slrVOreg.getSlr_id()%>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="slr_psw" class="col-sm-2 control-label">密碼</label>
                        <div class="col-sm-9">
                            <input type="password" class="form-control" id="slr_psw" name="slr_psw" placeholder="密碼" value="<%= (slrVOreg==null)? "" : slrVOreg.getSlr_psw()%>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="slr_psw2" class="col-sm-2 control-label">確認密碼</label>
                        <div class="col-sm-9">
                            <input type="password" class="form-control" id="slr_psw2" name="slr_psw2" placeholder="確認密碼" value="<%= (slrVOreg==null)? "" : slrVOreg.getSlr_psw()%>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="slr_contact" class="col-sm-2 control-label">聯絡人</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="slr_contact" name="slr_contact" placeholder="聯絡人" value="<%= (slrVOreg==null)? "" : slrVOreg.getSlr_contact()%>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="slr_phone" class="col-sm-2 control-label">聯絡電話</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="slr_phone" name="slr_phone" placeholder="聯絡電話" value="<%= (slrVOreg==null)? "" : slrVOreg.getSlr_phone()%>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="slr_intro" class="col-sm-2 control-label">公司簡介</label>
                        <div class="col-sm-9">
                            <textarea class="form-control" rows="5" id="slr_intro" name="slr_intro"><%= (slrVOreg==null)? "" : slrVOreg.getSlr_intro()%></textarea>
                        </div>
                    </div>
                    <div class="form-group">
		              <div class="col-sm-offset-2 col-sm-10"  id="aggrement">
		                  <div class="checkbox">
		                    <label>
		                      <input type="checkbox" name="aggrement" id="aggrement2" <%= (slrVOreg!=null && slrVOreg.getAggrement().equals("on"))? "checked" : "" %>/> 
		                    	 若要繼續註冊，請先閱讀並同意好房事的<a href="">服務條款</a> & <a href="">隱私權政策</a>
		                    </label>
		                  </div>
		              </div>
		            </div>
                    <div class="form-group">
                    	<label class="col-sm-2"></label>
                    	<div class="col-sm-9">
                        	<button type="submit" class="btn btn-warning btn-lg savebutton">加入</button>
                        	<div class="col-sm-offset-11 col-sm-1 magicdiv">
							<button type="button" class="btn btn-default btn-xs" id="magicBtn1">貼</button>
							</div>
                        </div>
                    </div>
                    
                </form>
            </div>
            </div>
        </div>
        </div>


</body>
</html>