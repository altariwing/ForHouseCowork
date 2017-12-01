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
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/houselogomin.png" />
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
	<div class="container-fluid areaOutter"><!-- 1.container 改為 container-fluid -->
		<div class="row areaOutter">

			<!-- include 左側選單區塊  開始-->
			<jsp:include
				page="/front/member/leftpanel.jsp" />
			<!-- include 左側選單區塊  結束-->

			<!-- 右側頁面區塊  開始 -->
			<div class="col-xs-12 col-sm-8 areaInner rightpart"><!-- 2.col-sm-8 改為 col-sm-10 -->

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

                    swal({
                        title: event.title,
                        html: "<p>預約日期:" + startDate + "</p><input type='hidden' id='resr_date' name='resr_date' value=" + sqlDate + "><br>" +
                            "<p>預約時間:" + dur + "</p><input type='hidden'id='resr_period' name='resr_period' value=" + dur + "><input type='hidden' class = 'id' name='resr_no' value=" + event.id + ">",
                        showCloseButton: true,
                        showCancelButton: false,
                        focusConfirm: false,
                        confirmButtonText: '取消預約',
                        confirmButtonAriaLabel: 'cancelchange',
                        // 			            	  cancelButtonText:
                        // 			            	  '<i class="fa fa-thumbs-down"></i>',
                        // 			            	  cancelButtonAriaLabel: 'Thumbs down',
                    }).catch(swal.noop);
                    $(".swal2-confirm").click(function() {


                        $.ajax({
                            url: "<%=request.getContextPath()%>/resrec/resrec.do",
                            data: {action:'resrecCancel', resr_no: $('.id').val(), resr_date: $('#resr_date').val(), resr_period: $('#resr_period').val() },
                            type: "POST",

                            success: function(msg) {

                                swal(
                                    'Deleted!',
                                    msg,
                                    'success'
                                );
                            },

                            error: function(xhr, ajaxOption, thrownError) {
                                alert(xhr.status);
                                alert(thrownError);
                            }
                        });
                        $("#calendar").fullCalendar('removeEvents', $('.id').val());

                    });
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
                allDaySlot: false,
                defaultDate: new Date(),
                weekNumbers: false,
                navLinks: true, // can click day/week names to navigate views
                editable: true,
                eventLimit: true, // allow "more" link when too many events
				
                events: [
                    <%for (ResRecVO aResR : resRecVO) {
				if (aResR.getResr_states().equals("ESTABLISH")) {%> {

                        id: '<%=aResR.getResr_no()%>',
                        title: '房仲:<%=new RealtorService().getOne(aResR.getRtr_no()).getRtr_name()%>,<%=new HouseService().getOneHouseInfo(aResR.getHouse_no()).getTitle()%>',
                        color: '#2E6E9E',
                        <%String[] betime = aResR.getResr_period().split("-");%>
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
       
    // 		$("div.fc-right").append('<span class="glyphicon glyphicon-trash trashicon ondragenter=\"myFunction(event)\""></span>');

    // 		$(".trashicon").on("dragenter", function(event) {
    // 		    event.preventDefault();  
    // 		    event.stopPropagation();
    // 		    $('#modalTitle').html(event.title);
    //             $('#modalBody').html("<p>預約日期:"+startDate+"</p><input type='hidden' name='resr_date' value="+event.start._i[0]+'-'+(event.start._i[1]+1)+'-'+event.start._i[2]+"><br>"+
    //             		"<p>預約時間:"+dur+"</p><input type='hidden' name='resr_period' value="+dur+"><input type='hidden' name='resr_no' value="+event.id+">");
    //             $('#eventUrl').attr('href');
    //             $('#fullCalModal').modal();
    // 		});

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

    $("#getevent").click(function() {

        var event = $('#calendar').fullCalendar('clientEvents', "1");
        console.log(event[0].title);
        console.log(event[0].start._i);
        console.log(event[0].end._i);
    });


	
	var rtrMap = new Map();
	<%for (ResRecVO aResR : resRecVO) {
		if (aResR.getResr_states().equals("ESTABLISH")) {%> 
	       rtrMap.set('<%=aResR.getRtr_no() %>','<%=new RealtorService().getOne(aResR.getRtr_no()).getRtr_name()%>');
	<%}};%>
	
	for (var [key, value] of rtrMap.entries()) {
		$("#rtr").append('<li><input type="button" class="rtrName btn btn-warning" value='+value+'><input type="hidden" class="rtr_no" value='+key+'></li>');
		};
	
	var rtrno;
	$(".rtrName").click(function(){
		if(rtrno != $(this).closest("li").find(".rtr_no").val()&&rtrno!=null){
			$("#calendar").fullCalendar('removeEvents', rtrno);
		}
		
		rtrno = $(this).closest("li").find(".rtr_no").val();
		rtrname = $(this).val();
		$.ajax({
			                        url: "<%=request.getContextPath()%>/resrec/resrec.do",
			                         data: { action:"findRtr", rtr_no: $(this).closest("li").find(".rtr_no").val(), mem_no:"<%=memVO.getMem_no()%>"},
			                         type: "POST",
			                         dataType:'json',
			                        
			                         success: function(msg) {
			                         	for (var i = 0; i < msg.ResRecVO.length; i++) {
			                         	    var id = rtrno;
			                         	    var period = msg.ResRecVO[i].resr_period.split("-");
			                         	    var text = rtrname;
			                         	    var start = msg.ResRecVO[i].resr_date+"T"+period[0];
			                         	    var end = msg.ResRecVO[i].resr_date+"T"+period[1];
			                         	    createEvent(id,text, start, end);
			                         	}
			                        	
			//                             swal(
			//                                 '修改成功',
			//                                 msg,
			//                                 'success'
			//                             )
			                         },

			                         error: function(xhr, ajaxOption, thrownError) {
			                             alert(xhr.status);
			                             alert(thrownError);
			                         }
			                     });
		
	});
});
// 	$("#calendar").fullCalendar('removeEvents', $('.id').val());
</script>
<!--更換的程式碼放下面 -->
							<div id='calendar'></div>
							
						</div>
							<div class="col-xs-12 col-sm-2">
								<ul id="rtr" class="list-unstyled">
								</ul>
							</div>
<!-- <button type="button" id="getevent" class="btn btn-info" >確定時間</button> --><!-- 新增用 -->							
							<!-- 更換的程式碼放上面 -->
					</div>
				</div>
			<!-- 右側頁面區塊結束 -->
	<!-- 下方大區塊 end -->
</body>

</html>
