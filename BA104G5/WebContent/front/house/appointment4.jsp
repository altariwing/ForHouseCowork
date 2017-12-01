<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.resrec.model.*"%>
<%@ page import="com.house.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="com.realtor.model.*" %>

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
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>forHouse</title>


<style type="text/css">
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
	margin-bottom: 30px
}

.check_bar li.active>a {
	background-color: #0064b1
}

/*hr美化*/
hr {
	height: 2px;
	background-color: #555;
	margin-top: 20px;
	margin-bottom: 20px;
	width: 100%;
}
/* 各標題 */
.sub_title {
	font-weight: bold;
	font-size: 2em;
	color: #f37748;
}

/* 預約房屋按鈕 */
.check_btn {
	background-color: #0678c2;
	color: white;
	box-shadow: 2px 2px 2px gray;
}
/* 取消房屋按鈕 */
.cancel_btn {
	background-color: #F6BF02;
	color: white;
	box-shadow: 2px 2px 2px gray;
}
/* 加入收藏按鈕 */
.follow_btn {
	background-color: #f6bf02;
	color: white;
	box-shadow: 2px 2px 2px gray;
}
/* 篩選條件的按鈕 */
.btn-block button {
	width: 80px;
	zoom: 1.3;
	box-shadow: 2px 2px 2px gray;
}
/* 下拉式選單按鈕的容器 */
.btn-group {
	margin-left: 5px;
}
/* 下拉式按鈕跳出的清單 */

/* 會讓物件置中 */
.vertical-horizontal::before {
	content: '';
	width: 0;
	height: 100%;
	display: inline-block;
	position: relative;
	vertical-align: middle;
	background: #f00;
}
/* 每一個物件*/
.list-item {
	box-shadow: 10px 10px 5px gray;
	border: solid #dddddd 1px;
	background-color: #ffffff;
	margin-top: 10px;
	margin-bottom: 30px;
	height: 170px;
}
/* 每一個物件的標題 */
.item-title {
	margin-top: 4px;
	color: blue;
	font-weight: bolder;
	font-family: Microsoft JhengHei;
}
/* 每一個物件的圖片 */
.list-item img {
	min-height: 200px;
	max-width: 200px;
	min-height: 170px;
	max-height: 170px
}
/* 每一個物件的價錢 */
.item-price {
	margin-top: 10px;
	margin-bottom: 5px;
	color: red;
	font-size: 2.3em;
	white-space: nowrap
}
/* 放大按鈕1.5倍 */
.bigger {
	zoom: 1.5
}

.item-subtitle {
	color: #a1a1a1
}

.item-detail li {
	width: 33.333333%;
	float: left
}
.fuevent .fc-event{
	background-color: white;
    color: black;
    cursor:pointer;
}
</style>
</head>
<body>
	<div class="backgroundpng">
		<img class="backgroundpng"
			src="<%=request.getContextPath()%>/images/sinyi_bg.png">
	</div>

	<div class="main">
		<nav class="navbar navbar-fixed-top">
			<jsp:include page="/front/navbar.jsp" />
		</nav>
	</div>

	<style type="text/css">

/* 強迫把網頁內容縮到這個大小 */
.limited {
	width: 64.3em;
}
</style>


	<div class="row check_bar">
		<ul class="nav nav-pills nav-justified ">
			<li><a href="#">1.確認房屋</a></li>
			<li class="active"><a href="#">2.預約時間</a></li>
			<li><a href="#">3.預約仲介</a></li>
			<li><a href="#">4.確認預約</a></li>
			<li><a href="#">5.預約成功</a></li>
		</ul>
	</div>


	<div class="container-fluid">

		<div class="row">
			<h3 class="sub_title">預約清單(VO版，裡面為HouseVO)</h3>
		</div>
		<div class="checkedList">
			<div class="col-xs-12 col-sm-5 fuevent">
			<c:forEach var="houseVO" items="${checkedHouseList}">
				<div id="all${houseVO.house_no}" class="list-item fc-event">
					<!--這是物件列表裡其中一欄物件-->
					<!-- 物件左邊區塊 -->
					<div class="col-xs-12 col-sm-3">
						<div class="row">
							<a href="<%=request.getContextPath()%>/house/houseServlet.do?action=getOneHouseInfo_b&house_no=${houseVO.house_no}">
								<img class="" src="<%=request.getContextPath()%>/house/ImageReader/${houseVO.house_no}">
							</a>
						</div>
					</div>
					<!-- 物件中間區塊 -->
					<div class="col-xs-12 col-sm-7">
						<div class="row">
							<div class="col-xs-12 col-sm-12">
								<a href="<%=request.getContextPath()%>/house/houseServlet.do?action=getOneHouseInfo_b&house_no=${houseVO.house_no}">
								</a>
								<h3 class="item-title">${houseVO.title}</h3>

								<h5 class="item-subtitle">${houseVO.location}</h5>
							</div>
						</div>
						<ul class="item-detail">
							<li>${houseVO.house_type }</li>
							<li>${houseVO.age}年</li>
							<li>${houseVO.floor}</li>
							<li>土地：${houseVO.land_pings}坪</li>
							<li>總坪數：${houseVO.total_pings}坪</li>
							<li>主建坪：${houseVO.main_pings}坪</li>
							<li>${houseVO.pattern}</li>
							<li>${houseVO.re_no}-${houseVO.house_serial_number}</li>

						</ul>
					</div>

					<!-- 物件右邊區塊 -->
					<div class="col-xs-12 col-sm-2 row">


						<div class="col-xs-12 col-sm-12">
							<div class="col-xs-12 col-sm-12 row">
								<div class="item-price">${houseVO.price}萬</div>
							</div>
							<div class="col-xs-12 col-sm-12 row">
								
							</div>
						</div>
						<!-- 一欄物件結束 -->
					</div>
				</div>

			</c:forEach>
		</div>
	
<link rel="stylesheet"	href="<%=request.getContextPath()%>/tools/MyCalendar/css/jquery-ui.min.css">
<link href="<%=request.getContextPath()%>/tools/MyCalendar/css/fullcalendar.min.css"	rel='stylesheet' type="text/css" />
<link href='<%=request.getContextPath()%>/tools/MyCalendar/css/fullcalendar.print.min.css' type="text/css" rel='stylesheet' media='print' />
<link rel="stylesheet" href="<%=request.getContextPath()%>/tools/sweetalert2/dist/sweetalert2.min.css">
<style>
.rightpart {
    margin-left: -80px;
}

body {
    margin: 0;
    padding: 0;
    font-size: 14px;
}

#top,
#calendar.fc-unthemed {
    font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
}

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
    font: inherit;
    /* mock what Boostrap does, don't compete  */
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
    float: left;
}

.right {
    float: right;
}

.clear {
    clear: both;
}

#calendar {
    max-width: 900px;
    margin: auto;
    padding: 0 10px;
}

#eventUrl {
    color: #fff;
}

#cancelchange {
    margin-right: 17em;
}

.trashicon {
    color: #5C9CCC;
    font-size: 2em;
}

.fc-future:hover {
    cursor: pointer;
    background: #5C9CCC;
}

.fc-disabled-day {
    background: #F0F0F0;
}

/* /*Allow pointer-events through*/ */
/* .fc-slats, /*horizontals*/ */
/* .fc-content-skeleton, /*day numbers*/ */
/* .fc-bgevent-skeleton /*events container*/{ */
/*     pointer-events:none */
/* } */

/* /*Turn pointer events back on*/ */
/* .fc-bgevent, */
/* .fc-event-container{ */
/*     pointer-events:auto; /*events*/ */
/* } */
</style>

<%-- <script src="<%=request.getContextPath()%>/tools/MyCalendar/js/jquery.js"></script> --%>
<script src="<%=request.getContextPath()%>/tools/MyCalendar/js/jquery-ui.min.js"></script>
<script src='<%=request.getContextPath()%>/tools/MyCalendar/js/moment.min.js'></script>
<%-- <script src='<%=request.getContextPath()%>/tools/MyCalendar/js/jquery.min.js'></script> --%>
<script src='<%=request.getContextPath()%>/tools/MyCalendar/js/fullcalendar.min.js'></script>

<script src='<%=request.getContextPath()%>/tools/MyCalendar/js/theme-chooser.js'></script>
<script src='<%=request.getContextPath()%>/tools/MyCalendar/js/zh-tw.js'></script>
<script src='<%=request.getContextPath()%>/tools/sweetalert2/dist/sweetalert2.all.min.js '></script>
<script src="<%=request.getContextPath()%>/tools/sweetalert2/dist/sweetalert2.min.js"></script>

<script>
$(document).ready(function() {
	
            $('#calendar').fullCalendar({
                theme: true,
                locale: 'zh-tw',
                eventOverlap: false,
                header: {
                    left: 'prev',
                    center: 'title',
                    right: 'today,month,agendaDay,listMonth,next'
                },
                eventClick: function(event, revertFunc) {

                    if ($.isArray(event.start._i)) {
                        var startDate = event.start._i[0] + "年" + (event.start._i[1] + 1) + "月" + event.start._i[2] + "日";
                        var sqlDate = event.start._i[0] + '-' + (event.start._i[1] + 1) + '-' + event.start._i[2];
                        var dur = ((event.start._i[3] * 1 < 10) ? "0" + event.start._i[3] : event.start._i[3]) + ":" + ((event.start._i[4] == "0") ? "00" : event.start._i[4]) + "-" +
                            ((event.end._i[3] * 1 < 10) ? "0" + event.end._i[3] : event.end._i[3]) + ":" + ((event.end._i[4] == "0") ? "00" : event.end._i[4]);
                    } else {
                        var date0 = event.start._i.split("T");
                        var date1 = date0[0].split("-");
                        var startDate = date1[0] + "年" + date1[1] + "月" + date1[2] + "日";
                        var sqlDate = date1[0] + '-' + date1[1] + '-' + date1[2];
                        var dur = date0[1] + "-" + event.end._i.split("T")[1];
                    }

                },
                eventDrop: function(event, delta, revertFunc) {
                    //	 				        alert(event.title + " was dropped on " + event.start.format());
                    var startDate = event.start._i[0] + "年" + (event.start._i[1] + 1) + "月" + event.start._i[2] + "日";
                    var sqlDate = event.start._i[0] + '-' + (event.start._i[1] + 1) + '-' + event.start._i[2];
                    var dur = ((event.start._i[3] * 1 < 10) ? "0" + event.start._i[3] : event.start._i[3]) + ":" + ((event.start._i[4] == "0") ? "00" : event.start._i[4]) + "-" +
                        ((event.end._i[3] * 1 < 10) ? "0" + event.end._i[3] : event.end._i[3]) + ":" + ((event.end._i[4] == "0") ? "00" : event.end._i[4]);
                    // 							console.log("resr_no:" + event.id+",resr_date:"+sqlDate+",resr_period:"+dur);
                    swal({
                        title: '確定更改嗎?',
                        html: "預約日期:" + startDate + "<br>" + " 預約時間:" + dur,
                        type: 'warning',
                        showCancelButton: true,
                        confirmButtonColor: '#3085d6',
                        cancelButtonColor: '#d33',
                        confirmButtonText: '確定修改',
                        cancelButtonText: '取消修改'

                    }).catch(swal.noop);
                    $(".swal2-confirm").click(function() {
                        $.ajax({
                            url: "<%=request.getContextPath()%>/resrec/resrec.do",
                            data: { action:"resrecChange", resr_no: event.id, resr_date: sqlDate, resr_period: dur },
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
                    });

                    $(".swal2-cancel").click(function() {
                        revertFunc();
                    });

                },
                validRange: function() {
                    var date = new Date();
                    date.setDate(date.getDate() - 1);
                    return {
                        start: date,
                    };

                },
                dayClick: function(date, jsEvent, view) {
                    ////////////////////////////////////////////////////////////////////////////新增預約時間
                    $('#calendar').fullCalendar('changeView', 'agendaDay');
                    $('#calendar').fullCalendar('gotoDate', date.format());
                    //alert('Clicked on: ' + date.format());

                    if (view.name === "agendaDay") {
                        // Here, is it possible to get a reference to the automatically created event and change its end date,
                        // instead of creating a new one and rendering it?

                        var durtime = date.format().split("T");
                        var endtime = parseInt(durtime[1].substring(0, 2)) + 2;
                        var end = durtime[0] + "T" + durtime[1].replace(durtime[1].substring(0, 2), endtime);
                        console.log("date.format() :" + date.format());
                        console.log("end :" + end);
                        createEvent('zz', date.format(), end);
                    }
                },
                eventResize: function(event, delta, revertFunc) {
                    //alert(event.title + " end is now " + event.end.format());
                    var startDate = event.start._i[0] + "年" + (event.start._i[1] + 1) + "月" + event.start._i[2] + "日";
                    var sqlDate = event.start._i[0] + '-' + (event.start._i[1] + 1) + '-' + event.start._i[2];
                    var dur = ((event.start._i[3] * 1 < 10) ? "0" + event.start._i[3] : event.start._i[3]) + ":" + ((event.start._i[4] == "0") ? "00" : event.start._i[4]) + "-" +
                        ((event.end._i[3] * 1 < 10) ? "0" + event.end._i[3] : event.end._i[3]) + ":" + ((event.end._i[4] == "0") ? "00" : event.end._i[4]);
                    //console.log("resr_no:" + event.id+",resr_date:"+sqlDate+",resr_period:"+dur);
                    swal({
                        title: '確定更改嗎?',
                        html: "預約日期:" + startDate + "<br>" + " 預約時間:" + dur,
                        type: 'warning',
                        showCancelButton: true,
                        confirmButtonColor: '#3085d6',
                        cancelButtonColor: '#d33',
                        confirmButtonText: '確定修改',
                        cancelButtonText: '取消修改'

                    }).catch(swal.noop);
                    $(".swal2-confirm").click(function() {
                        $.ajax({
                            url: "<%=request.getContextPath()%>/resrec/resrec.do",
                            data: { action:"resrecChange", resr_no: event.id, resr_date: sqlDate, resr_period: dur },
                            type: "POST",

                            success: function(msg) {
                                swal(
                                    '修改成功',
                                    msg,
                                    'success'
                                )
                            },

                            error: function(xhr, ajaxOption, thrownError) {
                                alert(xhr.status);
                                alert(thrownError);
                            }
                        });
                    });

                    $(".swal2-cancel").click(function() {
                        revertFunc();
                    });
                },

                minTime: "06:00:00",
                maxTime: "22:00:00",
                scrollTime: "08:00:00",
                //slotDuration: "02:00:00",
                allDaySlot: true,
                defaultDate: new Date(),
                weekNumbers: false,
                navLinks: true, // can click day/week names to navigate views
                editable: true,
                eventLimit: true, // allow "more" link when too many events
                droppable: true,
                drop: function() {
                	$(this).remove();
    			},
                events: [
                    <%for (ResRecVO aResR : resRecVO) {
				if (aResR.getResr_states().equals("ESTABLISH")) {%> {

                        id: '<%=aResR.getResr_no()%>',
                        title: '房仲:<%=new RealtorService().getOne(aResR.getRtr_no()).getRtr_name()%>,<%=new HouseService().getOneHouseInfo(aResR.getHouse_no()).getTitle()%>',
                        color: '#2E6E9E',
                        <%String[] betime = aResR.getResr_period().split("-");%>
                        startEditable:false,
                        durationEditable:false,
                        start: '<%=aResR.getResr_date()%>T<%=betime[0]%>',
                        end: '<%=aResR.getResr_date()%>T<%=betime[1]%>'

                    },
                    <%}}%> 
                    {
                        title: 'google it',
                        //rendering: 'background',
                        url: 'http://google.com',
                        overlap: false,
                        //color:'yellow',
                        start: '2017-11-27T12:30',
                        end: '2017-11-27T14:00',
                        constraint: 'availableForMeeting', // defined below
                        color: '#257e4a'

                    },
                    {
                        id: 'availableForMeeting',
                        start: '2017-11-27T12:30:00',
                        end: '2017-11-27T14:00:00',
                        rendering: 'background'

                    }
                ]
               , eventRender: function (event, element,view) {
            	   var title = event.title.split(",");
                   element.find('.fc-title').html(title[0]+"<br>"+((title[1]==undefined)? "":title[1]));
                   element.find('.fc-content').css({"text-overflow":"ellipsis" });
                   if (view.name == 'agendaDay') {
                	   element.find('.fc-time-grid-event').css({"background-color": "#1ac52d","border-color": "#fff"});
                       element.find('.fc-content').css({"text-align": "center", "font-size": "20px"});
                       element.find('.fc-time').css("font-weight","bold");
                  }
             		
               }
            });

    function createEvent(id, text, start, end) {
        var calendar = $('#calendar');
        var evento = {
            id: id,
            title: text,
            start: start,
            end: end,
            overlap: false,
            //resourceEditable: false,
            //constraint: 'availableFor',// defined below
            startEditable:false,
             color: '#f7ad02'
        };
        calendar.fullCalendar('renderEvent', evento, true);
    };

    
    $('.fuevent .fc-event').each(function() {
		
		// store data so the calendar knows to render an event upon drop
		$(this).data('event', {
			title: $(this).find(".item-title").text(), // use the element's text as the event title
			stick: true // maintain when user navigates (see docs on the renderEvent method)
		});

// 		// make the event draggable using jQuery UI
		$(this).draggable({
			zIndex: 999,
			revert: true,      // will cause the event to go back to its
			revertDuration: 0  //  original position after the drag
		});

	});


});
</script>
<!--更換的程式碼放下面 -->
							<div class="col-xs-12 col-sm-7">
								<div id='calendar'></div>
							</div>
							</div></div>
		
<%-- 		<c:forEach var="house_no" items="${house_nosCol}"> --%>
<!-- 		<div> -->
<!-- 		---------------------------- -->
<%-- 		${house_no} --%>
<!-- 		----------------------------  -->
<!-- 		</div>		 -->
<%-- 		</c:forEach>	 --%>
	


	<!-- 燈箱內容開始 -->
	<!-- 註冊燈箱 -->
	<div class="modal fade" id="registerForm">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<a href='#loginForm' data-toggle="modal"><span
						class="glyphicon glyphicon-log-in"></span> 登入</a>
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="text-center">
						<label class="control-label">註冊會員</label>
					</h4>
				</div>
				<form class="form-horizontal" action="" method="post">
					<div class="input-group margin-top">
						<input type="text" class="form-control" id="mem_mobile"
							name="mem_mobile" placeholder="手機號碼"> <span
							class="input-group-btn">
							<button type="button" class="btn btn-default" id="send_code">發送驗證碼</button>
						</span>
					</div>
					<div class="form-group margin-top">
						<input type="text" class="form-control box-width" id="get_code"
							name="get_code" placeholder="輸入驗證碼">
					</div>
					<div class="form-group margin-top">
						<input type="email" class="form-control box-width" id="mem_id"
							name="mem_id" placeholder="帳號(請輸入email)">
					</div>
					<div class="form-group margin-top">
						<input type="password" class="form-control box-width"
							id="password1" name="Password" placeholder="密碼">
					</div>
					<div class="form-group margin-top">
						<input type="password" class="form-control box-width"
							id="password2" placeholder="確認密碼">
					</div>
					<div class="form-group margin-top">
						<input type="text" class="form-control box-width" id="mem_name"
							name="memName" placeholder="姓名">
					</div>
					<div class="form-group margin-top">
						<input type="email" class="form-control box-width"
							id="mem_address" name="Address" placeholder="地址">
					</div>
					<div class="form-group">
						<div class="col-sm-offset-1 col-sm-11">
							<div class="checkbox">
								<label> <input type="checkbox" name="aggrement">
									若要繼續註冊，請先閱讀並同意好房事的服務條款 & 隱私權政策
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<button type="button"
							class="btn btn-primary btn-lg btn-block box-width">註冊</button>
						<div class="col-sm-offset-10 col-sm-2">
							<button type="button" class="btn btn-default btn-xs"
								id="magicBtn2">貼</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>


	<!-- 登入燈箱 -->
	<div class="modal fade" id="loginForm">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">登入</h4>
				</div>
				<form class="form-horizontal" action="loginCheck.do" method="post">
					<div class="form-group margin-top">
						<input type="email" class="form-control box-width" id="loginid"
							name="ID" placeholder="帳號(請輸入email)">
					</div>
					<div class="form-group margin-top">
						<input type="password" class="form-control box-width"
							id="password" name="Password" placeholder="密碼">
					</div>
					<div class="form-group">
						<button type="button"
							class="btn btn-primary btn-lg btn-block box-width">登入</button>
						<div class="col-sm-offset-10 col-sm-2">
							<button type="button" class="btn btn-default btn-xs"
								id="magicBtn1">貼</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- 燈箱內容結束 -->






</body>
</html>
