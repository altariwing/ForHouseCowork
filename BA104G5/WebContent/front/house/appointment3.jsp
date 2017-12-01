<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.resrec.model.*"%>
<%@ page import="com.house.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="com.realtor.model.*" %>
<jsp:useBean id="houseSVC" class="com.house.model.HouseService" />
<jsp:useBean id="rtrSVC" class="com.realtor.model.RealtorService" />
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
	
	List<ResRecVO> resRecVOlist = (List<ResRecVO>)session.getAttribute("resrecvolist");
%>
<!DOCTYPE html>

<head>
    <title>forHouse</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/tools/sweetalert2/dist/sweetalert2.min.css">
    <style>
    thead {
        background-color: lightskyblue;
    }

    .tabletp {
        margin-top: 50px;
        font-size: 20px;
    }

    .check_bar {
        margin-bottom: 30px;
    }

    .check_bar a {
        background-color: #eeeeee;
        height: 2.3em;
        color: black;
        font-size: 1.5em;
        font-weight: bold;
        font-family: Microsoft JhengHei;
        margin-top: 50px;
        margin-bottom: 30px;
    }

    .check_bar li.active>a {
        background-color: #0064b1;
    }
    </style>
</head>

<body>
    <div class="backgroundpng">
        <img class="backgroundpng" src="<%=request.getContextPath()%>/images/sinyi_bg.png">
    </div>
    <div class="main">
        <nav class="navbar navbar-fixed-top">
            <jsp:include page="/front/navbar.jsp" />
        </nav>
    </div>
    <div class="row check_bar">
        <ul class="nav nav-pills nav-justified ">
            <li><a href="#">1.確認房屋</a></li>
            <li><a href="#">2.預約房仲&&時間</a></li>
            <li class="active"><a href="#">3.確認預約</a></li>
            <li><a href="#">4.預約成功</a></li>
        </ul>
    </div>
    <div class="container tabletp">
        <div class="col-xs-12 col-sm-12">
            <table class="table table-bordered table-hover table-striped">
                <thead>
                    <tr>
                        <th class="text-center">房屋名稱</th>
                        <th class="text-center">房仲名字</th>
                        <th class="text-center">預約日期</th>
                        <th class="text-center">預約時間</th>
                    </tr>
                </thead>
                <tbody class="text-center">
                    <c:forEach var="resrecVO" items="${resrecvolist}">
                        <tr>
                            <td>
                                <div>${houseSVC.getOneHouseInfo(resrecVO.house_no).getTitle()}</div>
                            </td>
                            <td>
                                <div>${rtrSVC.getOne(resrecVO.rtr_no).getRtr_name()}</div>
                            </td>
                            <td>
                                <div>${resrecVO.resr_date}</div>
                            </td>
                            <td>
                                <div>${resrecVO.resr_period}</div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-xs-12 col-sm-offset-11">
            <form id="snedrr" action="<%=request.getContextPath()%>/appointment/AppointmentServlet.do">
                <input type="hidden" name="action" value="Establich" />
                <input type="hidden" name="resr_states" value="ESTABLISH" />
                <button type="button" class="btn btn-primary rrestablish">確認預約</button>
            </form>
        </div>
    </div>
    <script src='<%=request.getContextPath()%>/tools/sweetalert2/dist/sweetalert2.all.min.js '></script>
    <script src="<%=request.getContextPath()%>/tools/sweetalert2/dist/sweetalert2.min.js"></script>
    <script type="text/javascript">
    $(document).ready(function() {
        $(".rrestablish").click(function() {
            swal(
                '恭喜預約成功',
                '祝您看房愉快',
                'success'
            ).then((result) => {

                if (result) {
                    $("#snedrr").submit();
                };
            });
        });
    });
    </script>
</body>

</html>
