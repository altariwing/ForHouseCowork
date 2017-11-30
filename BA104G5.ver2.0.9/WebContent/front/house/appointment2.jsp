<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.resrec.model.*"%>
<%@ page import="com.house.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="com.realtor.model.*"%>
<%@ page import="com.realestate.model.*"%>
<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	if (memVO == null) { // 如為 null, 代表此user未登入過 , 才做以下工作
		session.setAttribute("location", request.getRequestURI()); //*工作1 : 同時記下目前位置 , 以便於login.jsp登入成功後 , 能夠直接導至此網頁(須配合LoginHandler.java)
		response.sendRedirect(request.getContextPath() + "/login.jsp"); //*工作2 : 請該user去登入網頁(login.jsp) , 進行登入
		return;
	}

	// 	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	// 	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	// 	response.setDateHeader("Expires", 0);

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
	height: 100px;
}
/* 每一個物件的標題 */
.item-title {
	margin-top: 4px;
	color: blue;
	font-size: 1.5em;
	font-weight: bolder;
	font-family: Microsoft JhengHei;
}
/* 每一個物件的圖片 */
.list-item img {
	min-height: 150px;
	max-width: 150px;
	min-height: 100px;
	max-height: 100px
}
/* 每一個物件的價錢 */
.item-price {
	margin-top: 10px;
	margin-bottom: 5px;
	color: red;
	font-size: 1.7em;
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

.fuevent .h-event {
	background-color: white;
	color: black;
	cursor: pointer;
	border: none;
}

.rtrbar {
	background-color: lightgray;
	cursor: pointer;
}
.hrbar div{
	border: 1px solid lightblue;
	background-color: white;
    height: 80px;
    margin-bottom: 20px;
}
.f-event{
	margin-top:15px;
	border: 1px solid lightblue;
	margin-bottom: 20px;
	margin-right: 1000px;
	cursor: pointer;
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
	<div class="row check_bar">
		<ul class="nav nav-pills nav-justified ">
			<li><a href="#">1.確認房屋</a></li>
			<li class="active"><a href="#">2.預約房仲&&房仲</a></li>
			<li><a href="#">3.確認預約</a></li>
			<li><a href="#">4.預約成功</a></li>
		</ul>
	</div>


<div class="container-fluid">
    <div class="col-xs-12 col-sm-4 fuevent">
        <div class="row">
            <h3 class="sub_title">房屋物件</h3>
        </div>
        <c:forEach var="houseVO" items="${checkedHouseList}">
            <div id="${houseVO.house_no}" class="list-item h-event">
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
                </div>
                <!-- 物件右邊區塊 -->
                <div class="col-xs-12 col-sm-2 row">
                    <div class="col-xs-12 col-sm-12">
                        <div class="col-xs-12 col-sm-12 row">
                            <div class="item-price">${houseVO.price}萬</div>
                        </div>
                    </div>
                    <!-- 一欄物件結束 -->
                </div>
            </div>
        </c:forEach>
        <div class="row">
            <h3 class="sub_title">追蹤房仲</h3>
        </div>
        <c:forEach var="realtorVO" items="${list}">
            <div id="${realtorVO.rtr_no}" class="rtrbar rtrName">
            	<input type="hidden" class="rtr_no" value="${realtorVO.rtr_no}">
                <div class="row">
                    <div class="col-xs-12 col-sm-5">
                        <img src="<%=request.getContextPath()%>/tool/showimage.do?action=rtr_photo&rtr_no=${realtorVO.rtr_no}" alt="${realtorVO.rtr_name}個人照片" style="width: 180px; height: 200px;">
                    </div>
                    <div class="col-xs-12 col-sm-5">
                        <ul class="list-unstyled info_estate">
                            <li class="rtr_name">房仲姓名 ${realtorVO.rtr_name}</li>
                            <li>服務地區 ${realtorVO.rtr_area}</li>
                        </ul>
                    </div>
                    <div class="col-sm-2">
                        <div class="wrapper"></div>
                    </div>
                </div>
            </div>
            <br>
        </c:forEach>
    </div>
    	
<link rel="stylesheet"	href="<%=request.getContextPath()%>/tools/MyCalendar/css/jquery-ui.min.css">
<link href="<%=request.getContextPath()%>/tools/MyCalendar/css/fullcalendar.min.css"	rel='stylesheet' type="text/css" />
<link href='<%=request.getContextPath()%>/tools/MyCalendar/css/fullcalendar.print.min.css' type="text/css" rel='stylesheet' media='print' />
<link rel="stylesheet" href="<%=request.getContextPath()%>/tools/sweetalert2/dist/sweetalert2.min.css">
<style>
#calendar {
    max-width: 900px;
    margin: auto;
    padding: 0 10px;
}
.fc-future:hover {
    cursor: pointer;
    background: #5C9CCC;
}

.fc-disabled-day {
    background: #F0F0F0;
}

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

//                     if ($.isArray(event.start._i)) {
//                         var startDate = event.start._i[0] + "年" + (event.start._i[1] + 1) + "月" + event.start._i[2] + "日";
//                         var sqlDate = event.start._i[0] + '-' + (event.start._i[1] + 1) + '-' + event.start._i[2];
//                         var dur = ((event.start._i[3] * 1 < 10) ? "0" + event.start._i[3] : event.start._i[3]) + ":" + ((event.start._i[4] == "0") ? "00" : event.start._i[4]) + "-" +
//                             ((event.end._i[3] * 1 < 10) ? "0" + event.end._i[3] : event.end._i[3]) + ":" + ((event.end._i[4] == "0") ? "00" : event.end._i[4]);
//                     } else {
//                         var date0 = event.start._i.split("T");
//                         var date1 = date0[0].split("-");
//                         var startDate = date1[0] + "年" + date1[1] + "月" + date1[2] + "日";
//                         var sqlDate = date1[0] + '-' + date1[1] + '-' + date1[2];
//                         var dur = date0[1] + "-" + event.end._i.split("T")[1];
//                     }

                },
                eventDrop: function(event, delta, revertFunc) {
                    //alert(event.title + " was dropped on " + event.start.format());
//                     var startDate = event.start._i[0] + "年" + (event.start._i[1] + 1) + "月" + event.start._i[2] + "日";
//                     var sqlDate = event.start._i[0] + '-' + (event.start._i[1] + 1) + '-' + event.start._i[2];
//                     var dur = ((event.start._i[3] * 1 < 10) ? "0" + event.start._i[3] : event.start._i[3]) + ":" + ((event.start._i[4] == "0") ? "00" : event.start._i[4]) + "-" +
//                         ((event.end._i[3] * 1 < 10) ? "0" + event.end._i[3] : event.end._i[3]) + ":" + ((event.end._i[4] == "0") ? "00" : event.end._i[4]);
                    //console.log("resr_no:" + event.id+",resr_date:"+sqlDate+",resr_period:"+dur);
//                     swal({
//                         title: '確定更改嗎?',
//                         html: "預約日期:" + startDate + "<br>" + " 預約時間:" + dur,
//                         type: 'warning',
//                         showCancelButton: true,
//                         confirmButtonColor: '#3085d6',
//                         cancelButtonColor: '#d33',
//                         confirmButtonText: '確定修改',
//                         cancelButtonText: '取消修改'

//                     }).catch(swal.noop);
//                     $(".swal2-confirm").click(function() {
//                         $.ajax({
<%--                             url: "<%=request.getContextPath()%>/resrec/resrec.do", --%>
//                             data: { action:"resrecChange", resr_no: event.id, resr_date: sqlDate, resr_period: dur },
//                             type: "POST",

//                             success: function(msg) {
//                                 swal(
//                                     '修改成功',
//                                      msg,
//                                     'success'
//                                 );
//                             },

//                             error: function(xhr, ajaxOption, thrownError) {
//                                 alert(xhr.status);
//                                 alert(thrownError);
//                             }
//                         });
//                     });

//                     $(".swal2-cancel").click(function() {
//                         revertFunc();
//                     });

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
                        //createEvent('zz', date.format(), end);
                    }
                },
                eventResize: function(event, delta, revertFunc) {
                    //alert(event.title + " end is now " + event.end.format());
//                     var startDate = event.start._i[0] + "年" + (event.start._i[1] + 1) + "月" + event.start._i[2] + "日";
//                     var sqlDate = event.start._i[0] + '-' + (event.start._i[1] + 1) + '-' + event.start._i[2];
//                     var dur = ((event.start._i[3] * 1 < 10) ? "0" + event.start._i[3] : event.start._i[3]) + ":" + ((event.start._i[4] == "0") ? "00" : event.start._i[4]) + "-" +
//                         ((event.end._i[3] * 1 < 10) ? "0" + event.end._i[3] : event.end._i[3]) + ":" + ((event.end._i[4] == "0") ? "00" : event.end._i[4]);
                    //console.log("resr_no:" + event.id+",resr_date:"+sqlDate+",resr_period:"+dur);
//                     swal({
//                         title: '確定更改嗎?',
//                         html: "預約日期:" + startDate + "<br>" + " 預約時間:" + dur,
//                         type: 'warning',
//                         showCancelButton: true,
//                         confirmButtonColor: '#3085d6',
//                         cancelButtonColor: '#d33',
//                         confirmButtonText: '確定修改',
//                         cancelButtonText: '取消修改'

//                     }).catch(swal.noop);
//                     $(".swal2-confirm").click(function() {
//                         $.ajax({
<%--                             url: "<%=request.getContextPath()%>/resrec/resrec.do", --%>
//                             data: { action:"resrecChange", resr_no: event.id, resr_date: sqlDate, resr_period: dur },
//                             type: "POST",

//                             success: function(msg) {
//                                 swal(
//                                     '修改成功',
//                                     msg,
//                                     'success'
//                                 )
//                             },

//                             error: function(xhr, ajaxOption, thrownError) {
//                                 alert(xhr.status);
//                                 alert(thrownError);
//                             }
//                         });
//                     });

//                     $(".swal2-cancel").click(function() {
//                         revertFunc();
//                     });
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
                dropAccept: '.f-event',
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
                        start: '2017-11-27T12:30',
                        end: '2017-11-27T14:00'
                    }
                ]
               , eventRender: function (event, element,view) {
            	   var title = ((event.title==undefined)? "":event.title.split(","));
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
            startEditable:false,
            durationEditable:false,
            color: '#f7ad02'
        };
        calendar.fullCalendar('renderEvent', evento, true);
    };
	$('.rtrName').each(function() {
		
		// store data so the calendar knows to render an event upon drop
		$(this).data('event', {
			title: $(this).find(".rtr_name").text(), // use the element's text as the event title
			stick: true // maintain when user navigates (see docs on the renderEvent method)
		});

// 		// make the event draggable using jQuery UI
		$(this).draggable({
			zIndex: 999,
			revert: true,      // will cause the event to go back to its
			revertDuration: 10  //  original position after the drag
		});

	});
    
    $('.fuevent .h-event').each(function() {
		
		// store data so the calendar knows to render an event upon drop
		$(this).data('event', {
			title: $(this).find(".item-title").text(), // use the element's text as the event title
			stick: true // maintain when user navigates (see docs on the renderEvent method)
		});

// 		// make the event draggable using jQuery UI
		$(this).draggable({
			zIndex: 999,
			revert: true,      // will cause the event to go back to its
			revertDuration: 100  //  original position after the drag
		});

	});
    
	
      
//     var rtrMap = new Map();
<%--     <%for (ResRecVO aResR : resRecVO) { --%>
<%--     		if (aResR.getResr_states().equals("ESTABLISH")) {%> --%>
<%--     rtrMap.set('<%=aResR.getRtr_no() %>', '<%=new RealtorService().getOne(aResR.getRtr_no()).getRtr_name()%>'); --%>
<%--     <%}};%> --%>

//     for (var [key, value] of rtrMap.entries()) {
//         $("#rtr").append('<li><input type="button" class="rtrName btn btn-warning" value=' + value + '><input type="hidden" class="rtr_no" value=' + key + '></li>');
//     };
	var fcnumber = 0;
	$( ".drophouse" ).droppable({
		accept: '.h-event',
      drop: function( event, ui ) {
        var id = ui.draggable[0].id;
        var title = ui.draggable[0].childNodes[9].innerText;
        $(this).append("<div>"+title+"<input type='hidden' value='"+id+"'></div>");
        $("#"+id).remove();
        if($(this).closest(".hrbar").find(".droprtr").text()!=""){
        	var fcno = "no"+ ++fcnumber;
        	$(this).closest(".hrbar").after('<div class="col-xs-12 col-sm-3 f-event '+fcno+'"></div>');
        	$('.f-event').draggable({
    			zIndex: 999,
    			revert: true,      // will cause the event to go back to its
    			revertDuration: 100  //  original position after the drag
    		});
        	$(this).closest(".outbar").find(".f-event").html($(this).closest(".hrbar").find(".droprtr").html()+$(this).html());
        	$(this).closest(".outbar").find(".f-event").data('event', {
				id:$(this).closest(".outbar").find(".f-event").find("input")[0].value+"_"+$(this).closest(".outbar").find(".f-event").find("input")[1].value,
    			title: $(this).closest(".outbar").find(".f-event").find("div")[0].innerText+","+$(this).closest(".outbar").find(".f-event").find("div")[1].innerText, // use the element's text as the event title
    			stick: true // maintain when user navigates (see docs on the renderEvent method)
    		});
        	
        	$(this).closest(".hrbar").find(".droprtr").empty();
        	$(this).empty();
         }
      }
    });
	
    $(".droprtr").droppable({
    	accept: '.rtrName',
        drop: function( event, ui ) {
          var id = ui.draggable[0].id;
          var name = ui.draggable[0].innerText.substring(ui.draggable[0].innerText.indexOf("房")+5,ui.draggable[0].innerText.indexOf("房")+8);
          var title = "房仲:"+name;
        
          $(this).append("<div>"+title+"<input type='hidden' value='"+id+"'></div>");
          rtrTime(id ,name );
          //$("#"+id).remove();
          //console.log($(this).closest(".hrbar").find(".drophouse").text()=="");
         if($(this).closest(".hrbar").find(".drophouse").text()!=""){
        	var fcno = "no"+ ++fcnumber;
        	$(this).closest(".hrbar").after('<div class="col-xs-12 col-sm-3 f-event '+fcno+'"></div>');
        	$('.f-event').draggable({
    			zIndex: 999,
    			revert: true,      // will cause the event to go back to its
    			revertDuration: 100  //  original position after the drag
    		});
        	$(this).closest(".outbar").find(".f-event").html($(this).html()+$(this).closest(".hrbar").find(".drophouse").html());
        	$(this).closest(".outbar").find(".f-event").data('event', {
				id:$(this).closest(".outbar").find(".f-event").find("input")[0].value+'_'+$(this).closest(".outbar").find(".f-event").find("input")[1].value,
    			title: $(this).closest(".outbar").find(".f-event").find("div")[0].innerText+","+$(this).closest(".outbar").find(".f-event").find("div")[1].innerText, // use the element's text as the event title
    			stick: true // maintain when user navigates (see docs on the renderEvent method)
    		});
        	$(this).closest(".hrbar").find(".drophouse").empty();
        	$(this).empty();
         }
         
         
        }
      });
    
    
    $(".confirmrr").click(function(){
    	var events = $('#calendar').fullCalendar('clientEvents');
    	var str="";
    	$.each( events, function( key, event ) {
    		if(event.id!=undefined){
     			if(event.id.indexOf("HSE")>0){
     			  var sqlDate = event.start._i[0] + '-' + (event.start._i[1] + 1) + '-' + event.start._i[2];
                  var dur = ((event.start._i[3] * 1 < 10) ? "0" + event.start._i[3] : event.start._i[3]) + ":" + ((event.start._i[4] == "0") ? "00" : event.start._i[4]) + "-" +
                  			((event.end._i[3] * 1 < 10) ? "0" + event.end._i[3] : event.end._i[3]) + ":" + ((event.end._i[4] == "0") ? "00" : event.end._i[4]);
     		 	  var rtr_no = event.id.split("_")[0];
     		 	  var house_no = event.id.split("_")[1];
     		 	  var mem_no = "<%=memVO.getMem_no()%>";
                  str += "{'rtr_no':'"+rtr_no +"', 'house_no':'"+ house_no +"','mem_no':'"+ mem_no +"','resr_date':'"+sqlDate +"', 'resr_period':'"+ dur +"'},";
          		}
    			
    		}
    	});
    	str = str.substring(0,str.lastIndexOf(","));
    	sendRR(str);
    });
    
    function sendRR(str){
    	console.log("{ action: 'preEstablich',"+str+"}");
    	
    	$.ajax({
            url: "<%=request.getContextPath()%>/appointment/AppointmentServlet.do",
            data: { action: "preEstablich", "list":"["+str+"]"},
            type: "POST",

            success: function(msg) {
            	window.location.href = "<%=request.getContextPath()%>/front/house/appointment3.jsp";
            },

            error: function(xhr, ajaxOption, thrownError) {
                alert(xhr.status);
                alert(thrownError);
            }
        });
    	
    };
	
    var rtrno;
    function rtrTime(id , name) {
        if (rtrno != id && rtrno != null) {
            $("#calendar").fullCalendar('removeEvents', rtrno);
        }else if(rtrno == id){
        	return;
        }

        rtrno = id;
        rtrname = name;
        $.ajax({
            url: "<%=request.getContextPath()%>/resrec/resrec.do",
            data: { action: "findRtr", rtr_no: rtrno , mem_no: "<%=memVO.getMem_no()%>" },
            type: "POST",
            dataType: 'json',

            success: function(msg) {
                for (var i = 0; i < msg.ResRecVO.length; i++) {
                    var id = rtrno;
                    var period = msg.ResRecVO[i].resr_period.split("-");
                    var text = rtrname;
                    var start = msg.ResRecVO[i].resr_date + "T" + period[0];
                    var end = msg.ResRecVO[i].resr_date + "T" + period[1];
                    createEvent(id, text, start, end);
                }
            },

            error: function(xhr, ajaxOption, thrownError) {
                alert(xhr.status);
                alert(thrownError);
            }
        });

    };


});
</script>

    <div class="col-xs-12 col-sm-8 outbar">
        <div class="hrbar" style="height: 80px;">
        	<div class="col-xs-12 col-sm-2 col-sm-offset-1">房屋</div>
			<div class="col-xs-12 col-sm-3 drophouse"></div>
			<div class="col-xs-12 col-sm-3">房仲</div>
			<div class="col-xs-12 col-sm-3 droprtr"></div>
        </div>
		
        <div class="">
        	<div id='calendar'></div>
        </div>
        <button type="button" class="btn btn-success confirmrr">確認預約</button>
    </div>
</div>

</body>
</html>
