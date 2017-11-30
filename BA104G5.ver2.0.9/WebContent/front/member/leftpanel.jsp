<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%
  MemVO memVO = (MemVO) session.getAttribute("memVO");
%>
<html>
<head>
    
    <style type="text/css">
    body {
        font-family: Arial, Verdana, Helvetica, "LiHei Pro Medium", 微軟正黑體, sans-serif;
    }
    #headerBar{
    	width: 95em;
    }
    
    #loginArea{
    	width: 130px;
    }

</style>

</head>
<body>
 
            <div class="col-xs-12 col-sm-2 areaInner main_panel"><!-- col-sm-3 改為 col-sm-2-->
                <div class="panel-group" id="accordion2" role="tablist">

                   <!-- 區塊1 -->
                   <div class="panel panel-info">
                     <div class="panel-heading" role="tab" id="panel1">
						<a href="#aaa" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed">
						<h3 class="panel-title text-center">個人資料管理</h3></a>
                     </div>
                     <div id="aaa" class="panel-collapse collapse">
                       <div class="list-group">
                         <a href="<%=request.getContextPath()%>/front/member/memdata.jsp" class="list-group-item text-center">
                         	<div id="basedata">基本資料</div>
                         </a>
                         <a href="<%=request.getContextPath()%>/front/member/mempsw.jsp" class="list-group-item text-center">
                         	<div id="changepsw">修改密碼</div>
                         </a>
                       </div>
                     </div>
                   </div>

                   <!-- 區塊2 -->
                   <div class="panel panel-info">
                     <div class="panel-heading" role="tab" id="panel2">
                         <a href="#bbb" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed">
                         <h3 class="panel-title text-center">管理追蹤房仲</h3></a>
                     </div>
                     <div id="bbb" class="panel-collapse collapse">
                       <div class="list-group">
                         <a href="<%=request.getContextPath()%>/followRTR/FRServlet.do?action=findByMemNo&mem_no=${memVO.mem_no}" class="list-group-item text-center">
                        	<div id="collect">追蹤清單</div>
                         </a>
                         <a href="你的url" class="list-group-item text-center">房仲xx</a>
                       </div>
                     </div>
                   </div>

                   <!-- 區塊3 -->
                   <div class="panel panel-info">
                     <div class="panel-heading" role="tab" id="panel3">
                         <a href="#ccc" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed">
                           <h3 class="panel-title text-center">管理預約紀錄</h3>
                         </a>
                     </div>
                     <div id="ccc" class="panel-collapse collapse">
                       <div class="list-group">
                         <a href="<%=request.getContextPath()%>/front/resrec/resrecdata.jsp" class="list-group-item text-center">最新預約</a>
                         <a href="你的url" class="list-group-item text-center">歷史預約</a>
                       </div>
                     </div>
                   </div>

                   <!-- 區塊4 -->
                   <div class="panel panel-info">
                     <div class="panel-heading" role="tab" id="panel4">
                         <a href="#ddd" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed">
                           <h3 class="panel-title text-center">管理商品收藏</h3>
                         </a>
                     </div>
                     <div id="ddd" class="panel-collapse collapse">
                       <div class="list-group">
                         <a href="prdcol.jsp" class="list-group-item text-center">
                         	<div id="prdcollect">收藏的商品</div>
                         </a>
                         <a href="##" class="list-group-item text-center">
                         	<div id="changepsw">商品xxx</div>
                         </a>
                       </div>
                     </div>
                   </div>
                 
             
	             <!-- 區塊5 -->
	             <div class="panel panel-info">
	               <div class="panel-heading" role="tab" id="panel5">
	                   <a href="#eee" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed">
	                     <h3 class="panel-title text-center">管理房屋收藏</h3>
	                   </a>
	               </div>
	               <div id="eee" class="panel-collapse collapse">
	                 <div class="list-group">
	                   <a href="<%=request.getContextPath()%>/hesColl/HseCollServlet.do?action=getHesCollByMenNo" class="list-group-item text-center">房屋收藏</a>
	                 </div>
	               </div>
	             </div>
	             
	             <!-- 區塊6 -->
	             <div class="panel panel-info">
	               <div class="panel-heading" role="tab" id="panel6">
	                   <a href="#fff" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed">
	                     <h3 class="panel-title text-center">管理看屋紀錄</h3>
	                   </a>
	               </div>
	               <div id="fff" class="panel-collapse collapse">
	                 <div class="list-group">
	                   <a href="<%=request.getContextPath()%>/front/resrec/hseRev.jsp" class="list-group-item text-center">最新訂單</a>
	                   <a href="#tabxxx" class="list-group-item text-center">看屋心得</a>
	                 </div>
	               </div>
	             </div>
	             
	             <!-- 區塊7 -->
	             <div class="panel panel-info">
	               <div class="panel-heading" role="tab" id="panel7">
	                   <a href="#ggg" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed">
	                     <h3 class="panel-title text-center">優惠抽抽樂</h3>
	                   </a>
	               </div>
	               <div id="ggg" class="panel-collapse collapse">
	                 <div class="list-group">
	                   <a href="#tabxxx" class="list-group-item text-center">抽抽樂</a>
	                   <a href="#tabxxx" class="list-group-item text-center">抽抽樂</a>
	                 </div>
	               </div>
	             </div>
	             
	             <!-- 區塊8 -->
	             <div class="panel panel-info">
	               <div class="panel-heading" role="tab" id="panel8">
	                   <a href="#hhh" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed">
	                     <h3 class="panel-title text-center">查詢優惠券</h3>
	                   </a>
	               </div>
	               <div id="hhh" class="panel-collapse collapse">
	                 <div class="list-group">
	                   <a href="#tabxxx" class="list-group-item text-center">優惠券</a>
	                   <a href="#tabxxx" class="list-group-item text-center">優惠券</a>
	                 </div>
	               </div>
	             </div>
	             
	             <!-- 區塊9 -->
	             <div class="panel panel-info">
	               <div class="panel-heading" role="tab" id="panel9">
	                   <a href="#iii" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed">
	                     <h3 class="panel-title text-center">管理訂單</h3>
	                   </a>
	               </div>
	               <div id="iii" class="panel-collapse collapse">
	                 <div class="list-group">
	                   <a href="<%=request.getContextPath()%>/front/member/MemNewOdr.jsp" class="list-group-item text-center">購買清單</a>
	                   <a href="<%=request.getContextPath()%>/front/member/MemHistoryOdr.jsp" class="list-group-item text-center">歷史購買紀錄</a>
	                 </div>
	               </div>
	             </div>
           	</div>
          </div>
<!--         </div> -->
<!--       </div> -->
<!--     </div>  -->
</body>
</html>