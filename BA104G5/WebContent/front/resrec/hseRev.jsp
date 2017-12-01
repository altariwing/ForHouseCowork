<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.resrec.model.*"%>
<%@ page import="com.house.model.*"%>
<%@ page import="java.util.List"%>

<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	if (memVO == null) { // 如為 null, 代表此user未登入過 , 才做以下工作
		session.setAttribute("location", request.getRequestURI()); //*工作1 : 同時記下目前位置 , 以便於login.jsp登入成功後 , 能夠直接導至此網頁(須配合LoginHandler.java)
		response.sendRedirect(request.getContextPath() + "/login.jsp"); //*工作2 : 請該user去登入網頁(login.jsp) , 進行登入
		return;
	}

	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0);

	List<ResRecVO> resRecVO = new ResRecService().getMem_kuei(memVO.getMem_no());
	pageContext.setAttribute("List",resRecVO);
%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/houselogo1.png" />
<title>For House</title>
<body>
    <!-- 背景圖 -->
    <div class="container-fluid backgroundpng">
        <img class="row" src="<%=request.getContextPath()%>/images/fixed_bg.png">
    </div>
    <!-- 上方的 header (navbar) -->
    <nav class="navbar navbar-fixed-top">
        <jsp:include page="/front/navbar.jsp" />
    </nav>
    <div class="col-xs-12 memtitle">
        <h2 class="text-center member_center">會員中心</h2>
    </div>
    <!-- 下方大區塊 start -->
    <div class="container-fluid areaOutter">
        <div class="row areaOutter">
            <!-- include 左側選單區塊  開始-->
            <jsp:include page="/front/member/leftpanel.jsp" />
            <!-- include 左側選單區塊  結束-->
            <!-- 右側頁面區塊  開始 -->
            <div class="col-xs-12 col-sm-10 areaInner">
                <div class="tab-content">

<style>
#top {
	background: #eee;
	border-bottom: 1px solid #ddd;
	padding: 0 10px;
	line-height: 40px;
	font-size: 12px;
	color: #000;
}

#top .selector {
	display: inline-block;
	margin-right: 10px;
}

#top select {
	font: inherit; /* mock what Boostrap does, don't compete  */
}

.memtitle {
	margin-top: 70px;
}

.member_center {
	color: #00ADEE;
}

.areaOutter {
	margin-top: 40px;
}

.areaInner {
	margin-top: 20px;
}

h3.panel-title {
	font-size: 24px;
}

.left {
	float: left
}

.right {
	float: right
}

.clear {
	clear: both
}

#eventUrl {
	color: #fff;
}

.trashicon {
	color: #5C9CCC;
	font-size: 2em;
}
.resr_no{
	display:none;
}
.tablebackground{
background-color: #fff;
}
td:nth-child(1)>div{ 
 	width:100px; 
} 
td:nth-child(2)>div{
	width:150px; 
}
th{
    text-align: center;
}
textarea {
	overflow-y: scroll;
    resize: none;
}
.ratetip{
/* 	background-color:#000; */
	margin-top: 1.2px;
    font-weight: 900;
    color: darkseagreen;
    width: 100px;
	display:none;
	font-size:20px;
}
/* .RATERTR{ */
/* 	MARGIN: 0PX 30PX; */
/* } */
</style>

<link rel="stylesheet" href="<%=request.getContextPath()%>/tools/ratestar/jquery.rateyo.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/tools/sweetalert2/dist/sweetalert2.min.css">
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/tools/ratestar/jquery.min.js"></script> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/tools/ratestar/jquery.rateyo.js"></script>
<script src='<%=request.getContextPath()%>/tools/sweetalert2/dist/sweetalert2.all.min.js '></script>
<script src="<%=request.getContextPath()%>/tools/sweetalert2/dist/sweetalert2.min.js"></script>

<script type="text/javascript">
$(document).ready(function() {
    var j = 0;
    <%int a = 0;%> 
    	<c:forEach var = "resRecVO" items = "${List}" >
        <c:if test = "${resRecVO.resr_states.equals('CONFIRM')}" >
        $("#n<%=a%>>td:nth-child(3)>div").rateYo({
            rating: ${resRecVO.resr_hpric},
            numStars: 5,
            precision: 2,
            readOnly: true, /////////////////////////////////////////////////////////////////
            starWidth: "30px",
            spacing: "3px",
            multiColor: {
                startColor: '#ffbf00',
                endColor: "#ffff00"
            }
        });
    $("#n<%=a%>>td:nth-child(4)>div").rateYo({
        rating: ${resRecVO.resr_hsize},
        numStars: 5,
        precision: 2,
        readOnly: true, /////////////////////////////////////////////////////////////////
        starWidth: "30px",
        spacing: "5px",
        multiColor: {
            startColor: '#ffbf00',
            endColor: "#ffff00"
        }
    });
    $("#n<%=a%>>td:nth-child(5)>div").rateYo({
        rating: ${resRecVO.resr_wall},
        numStars: 5,
        precision: 2,
        readOnly: true, /////////////////////////////////////////////////////////////////
        starWidth: "30px",
        spacing: "5px",
        multiColor: {
            startColor: '#ffbf00',
            endColor: "#ffff00"
        }
    });
    $("#n<%=a%>>td:nth-child(6)>div").rateYo({
        rating: ${resRecVO.resr_str},
        numStars: 5,
        precision: 2,
        readOnly: true, /////////////////////////////////////////////////////////////////
        starWidth: "30px",
        spacing: "5px",
        multiColor: {
            startColor: '#ffbf00',
            endColor: "#ffff00"
        }
    });
    $("#n<%=a%>>td:nth-child(7)>div").rateYo({
        rating: ${resRecVO.resr_lc},
        numStars: 5,
        precision: 2,
        readOnly: true, /////////////////////////////////////////////////////////////////
        starWidth: "30px",
        spacing: "5px",
        multiColor: {
            startColor: '#ffbf00',
            endColor: "#ffff00"
        }
    });   
    $("#m<%=a%>>td:nth-child(3)>.ratediv").rateYo({
        rating: ${resRecVO.mem_rate},
        numStars: 5,
        precision: 2,
        readOnly: true, /////////////////////////////////////////////////////////////////
        fullStar: true,
        starWidth: "30px",
        spacing: "5px",
        multiColor: {
            startColor: '#ffbf00',
            endColor: "#ffff00"
        }
    });
    var rateper =
        ((${resRecVO.resr_hpric} + ${resRecVO.resr_hsize} + ${resRecVO.resr_wall} + ${resRecVO.resr_str} + ${resRecVO.resr_lc}) / 25 * 100).toFixed(0) + "%";
    $("#n<%=a%> #ratensum").text(rateper);

    <%a++;%> </c:if> </c:forEach>
    $(".aa>div").attr('class', 'btn btn-info').text("修改評價");;

    $("td").on("rateyo.set", function(e, data) {
        sumRate(this.closest("tr"), print);

    });

    function print(a) {
        $(a).find("#ratensum").text(
            parseInt(j / 25 * 100) + "%");
    };

    function sumRate(a, e) {
        j = 0;

        $.each($(a).find(".ratediv"), function(index, value) {
            j += $(value).rateYo("rating");
            //console.log("j: "+j);
            e(a);
        });
    };
    
    
    
    $(".ratediv").rateYo().on("rateyo.change", function (e, data) {
    	
    	var rating = data.rating;
    	switch (rating) {
    	case 0:
        	$(this).next().show().text("爛到不行");
            break;
        case 1:
        	$(this).next().show().text("我覺得不行");
            break;
        case 2:
        	$(this).next().show().text("有點不行");
            break;
        case 3:
        	$(this).next().show().text("普普通通");
            break;
        case 4:
        	$(this).next().show().text("還不錯");
            break;
        case 5:
        	$(this).next().show().text("我覺得可以");
            break;
    }
    	
    });
    
    $(".memrev").attr("readonly", true); 
    $(".aa").click(function() {

        if ($(this).text().indexOf("修改評價") > 0) {
            //console.log($(this).find("div"));
            $(this).find("div").attr('class', 'btn btn-success').html("確定修改");
            $(this).closest("tr").find(".memrev").attr("readonly", false);
            $(this).closest("tr").find(".ratediv").rateYo(
                "option",
                "readOnly",
                false);
        } else {
            $(this).find("div").attr('class', 'btn btn-info').text("修改評價");
            $(this).closest("tr").find(".memrev").attr("readonly", true);
            $(this).closest("tr").find(".ratediv").rateYo(
                "option",
                "readOnly",
                true);
            
            var ratehrtr = $(this).closest("tr").find(".ratediv").rateYo("option", "rating");
            if(Array.isArray(ratehrtr)){  
            $.ajax({
                url: "<%=request.getContextPath()%>/resrec/resrec.do",
                data: {
                    action: "changehseRev",

                    resr_no: $(this).closest("tr").find(".resr_no").text(),
                    resr_hpric: ratehrtr[0],
                    resr_hsize: ratehrtr[1],
                    resr_wall: ratehrtr[2],
                    resr_str: ratehrtr[3],
                    resr_lc: ratehrtr[4]
                },

                type: "POST",

                success: function(msg) {
                	swal(
                            '修改成功',
                             msg,
                            'success'
                        );
                },

                error: function(xhr, ajaxOption, thrownError) {
                    alert(xhr.status);
                    alert(thrownError);
                }
            });
            }else{
            	//console.log("resr_no:" +$(this).closest("tr").find(".resr_no").text()+",mem_rate:"+ ratehrtr +",mem_rev:"+ $(this).closest("tr").find(".memrev").val());
            	$.ajax({
                    url: "<%=request.getContextPath()%>/resrec/resrec.do",
                    data: {
                        action: "changeRtrRev",
                        resr_no: $(this).closest("tr").find(".resr_no").text(),
                        mem_rate: ratehrtr,
                        mem_rev: $(this).closest("tr").find(".memrev").val()
                    },

                    type: "POST",

                    success: function(msg) {
                    	swal(
                                '修改成功',
                                 msg,
                                'success'
                            );
                    },

                    error: function(xhr, ajaxOption, thrownError) {
                        alert(xhr.status);
                        alert(thrownError);
                    }
                });
            	
            }
        }
    });
});
</script>
							
<div role="tabpanel">
    <!-- 標籤面板：標籤區 -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active">
            <a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">房屋</a>
        </li>
        <li role="presentation">
            <a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab">房仲</a>
        </li>
    </ul>
    <!-- 標籤面板：內容區 -->
    <div class="tab-content">
        <table role="tabpanel" class="table table-hover text-center tablebackground tab-pane active" id="tab1">
            <thead>
                <tr>
                    <th>預約日期</th>
                    <th>房屋名稱</th>
                    <th>總價</th>
                    <th>坪數</th>
                    <th>建材</th>
                    <th>格局</th>
                    <th>生活機能</th>
                    <th>整體滿意度</th>
                    <th colspan="2">評價更改</th>
                </tr>
            </thead>
            <tbody>
                <jsp:useBean id="hseSvc" scope="page" class="com.house.model.HouseService" />
                <%int b = 0; %>
                    <c:forEach var="resRecVO1" items="${List}">
                        <c:if test="${resRecVO1.resr_states.equals('CONFIRM')}">
                            <tr id="n<%=b%>">
                                <td>
                                    <div>${resRecVO1.resr_date}</div>
                                </td>
                                <td>
                                    <div>${hseSvc.getOneHouseInfo(resRecVO1.house_no).title}</div>
                                </td>
                                <td>
                                    <div class="ratediv"></div>
                                </td>
                                <td>
                                    <div class="ratediv"></div>
                                </td>
                                <td>
                                    <div class="ratediv"></div>
                                </td>
                                <td>
                                    <div class="ratediv"></div>
                                </td>
                                <td>
                                    <div class="ratediv"></div>
                                </td>
                                <td>
                                    <div id="ratensum"></div>
                                </td>
                                <td>
                                    <div class="resr_no">
                                        ${resRecVO1.resr_no}
                                    </div>
                                </td>
                                <td>
                                    <div class="btn-group aa">
                                        <div role="button"></div>
                                    </div>
                                </td>
                            </tr>
                            <%b++; %>
                        </c:if>
                    </c:forEach>
            </tbody>
        </table>
        <!-- 					        <div role="tabpanel" class="tab-pane" id="tab2">頭條標籤的內容</div> -->
        <table role="tabpanel" class="table table-hover text-center tablebackground tab-pane" id="tab2">
            <thead>
                <tr>
                    <th>預約日期</th>
                    <th>房仲名字</th>
                    <th>整體滿意度</th>
                    <th>評價房仲</th>
                    <th colspan="2">評價更改</th>
                </tr>
            </thead>
            <tbody>
                <jsp:useBean id="rtrSvc" scope="page" class="com.realtor.model.RealtorService" />
                <%int rtrrate = 0; %>
                    <c:forEach var="resRecVO1" items="${List}">
                        <c:if test="${resRecVO1.resr_states.equals('CONFIRM')}">
                            <tr id="m<%=rtrrate%>">
                                <td>
                                    <div>${resRecVO1.resr_date}</div>
                                </td>
                                <td>
                                    <div>${rtrSvc.getOne(resRecVO1.rtr_no).rtr_name}</div>
                                </td>
                                <td>
                                    <div class="ratediv ratertr" style="float:left" ></div>
                                    <div class="ratetip" style="float:right" ></div>
                                </td>
                                <td>
                                    <textarea class="memrev">${resRecVO1.mem_rev}</textarea>
                                </td>
                                <td>
                                    <div class="resr_no">
                                        ${resRecVO1.resr_no}
                                    </div>
                                </td>
                                <td>
                                    <div class="btn-group aa">
                                        <div role="button"></div>
                                    </div>
                                </td>
                            </tr>
                            <%rtrrate++; %>
                        </c:if>
                    </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</div>
</div>
</div>
</div>
<!-- 右側頁面區塊結束 -->
<!-- 下方大區塊 end -->
</body>

</html>