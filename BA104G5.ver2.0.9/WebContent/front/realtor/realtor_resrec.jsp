<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.realtor.model.*"%>
<%@ page import="com.resrec.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.house.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="com.breaktime.model.*" %>
<%
	RealtorVO realtorVO = (RealtorVO) session.getAttribute("realtorVO");
	session.setAttribute("realtorVO",realtorVO);
	// 如為 null, 代表此user未登入過 , 才做以下工作.
	//*工作1 : 同時記下目前位置 , 以便於login.jsp登入成功後 , 能夠直接導至此網頁
	//*工作2 : 請該user去登入網頁(login.jsp) , 進行登入
	if (realtorVO == null) {
		response.sendRedirect("realtor_register.jsp"); //*工作2 : 請該user去房仲註冊網頁
		return;
	}
	
	List<ResRecVO> resRecVO = new ResRecService().getRtr_kuei(realtorVO.getRtr_no());
%>
<%
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>

<!-- 房仲前端 --> 
<jsp:include page="/front/realtor/frontPage.jsp" flush="true" />
<!-- 房仲前端 -->

<!-- 右邊房仲相關資料區include資料用 =====================================-->
<div class="col-sm-12 col-sm-8 form_realtor">
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
    margin: 20px 10px;
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
a.fc-event {
  -webkit-transition: 0s;
  transition: 0s;
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
<jsp:useBean id="btSvc" scope="page" class="com.breaktime.model.BreakTimeService" />
<script>
$(document).ready(function() {
	var clickCount = 0 ;
            $('#calendar').fullCalendar({
                theme: true,
                locale: 'zh-tw',
                Overlap: false,
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
                    //alert(event.title + " was dropped on " + event.start.format());
                    var startDate = event.start._i[0] + "年" + (event.start._i[1] + 1) + "月" + event.start._i[2] + "日";
                    var sqlDate = event.start._i[0] + '-' + (event.start._i[1] + 1) + '-' + event.start._i[2];
                    var dur = ((event.start._i[3] * 1 < 10) ? "0" + event.start._i[3] : event.start._i[3]) + ":" + ((event.start._i[4] == "0") ? "00" : event.start._i[4]) + "-" +
                        ((event.end._i[3] * 1 < 10) ? "0" + event.end._i[3] : event.end._i[3]) + ":" + ((event.end._i[4] == "0") ? "00" : event.end._i[4]);
                    //console.log("rtr_no:" + event.id+",brk_date:"+sqlDate+",brk_period:"+dur);
                    swal({
                        title: '確定更改嗎?',
                        html: "<b>休假日期:</b>" + startDate + "<br>" + "休假時間:  " + dur,
                        type: 'warning',
                        showCancelButton: true,
                        confirmButtonColor: '#3085d6',
                        cancelButtonColor: '#d33',
                        confirmButtonText: '確定修改',
                        cancelButtonText: '取消修改'

                    }).catch(swal.noop);
                    $(".swal2-confirm").click(function() {
                        $.ajax({
                            url: "<%=request.getContextPath()%>/breaktime/bt.do",
                            data: { action:"btChange",brk_no:event.id, rtr_no: "${realtorVO.rtr_no}", brk_date: sqlDate, brk_period: dur },
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
                        var endtime = parseInt(durtime[1].substring(0, 2)) + 4;
                        var end = durtime[0] + "T" + durtime[1].replace(durtime[1].substring(0, 2), endtime);
                        //console.log("date.format() :" + date.format());
                        //console.log("end :" + end);
                        var ecount = "No."+ ++clickCount;
                        createEvent(ecount,"休假", date.format(), end);
                    }
                },
                eventResize: function(event, delta, revertFunc) {
                    //alert(event.title + " end is now " + event.end.format());
                    var startDate = event.start._i[0] + "年" + (event.start._i[1] + 1) + "月" + event.start._i[2] + "日";
                    var sqlDate = event.start._i[0] + '-' + (event.start._i[1] + 1) + '-' + event.start._i[2];
                    var dur = ((event.start._i[3] * 1 < 10) ? "0" + event.start._i[3] : event.start._i[3]) + ":" + ((event.start._i[4] == "0") ? "00" : event.start._i[4]) + "-" +
                        ((event.end._i[3] * 1 < 10) ? "0" + event.end._i[3] : event.end._i[3]) + ":" + ((event.end._i[4] == "0") ? "00" : event.end._i[4]);
                    //console.log("rtr_no:" + event.id+",brk_date:"+sqlDate+",brk_period:"+dur);
                    swal({
                        title: '確定更改嗎?',
                        html: "休假日期:" + startDate + "<br>" + " 休假時間:" + dur,
                        type: 'warning',
                        showCancelButton: true,
                        confirmButtonColor: '#3085d6',
                        cancelButtonColor: '#d33',
                        confirmButtonText: '確定修改',
                        cancelButtonText: '取消修改'

                    }).catch(swal.noop);
                    $(".swal2-confirm").click(function() {
                        $.ajax({
                            url: "<%=request.getContextPath()%>/breaktime/bt.do",
                            data: { action:"btChange",brk_no:event.id, rtr_no: "${realtorVO.rtr_no}", brk_date: sqlDate, brk_period: dur},
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
                eventDragStop: function(event,jsEvent) {

                    var trashEl = $('#calendarTrash');
                    var ofs = trashEl.offset();

                    var x1 = ofs.left;
                    var x2 = ofs.left + trashEl.outerWidth(true);
                    var y1 = ofs.top;
                    var y2 = ofs.top + trashEl.outerHeight(true);

                    if (jsEvent.pageX >= x1 && jsEvent.pageX<= x2 &&
                        jsEvent.pageY >= y1 && jsEvent.pageY <= y2) {
                        		$("#calendar").fullCalendar('removeEvents', event.id);
                    	
                    	var startDate = event.start._i[0] + "年" + (event.start._i[1] + 1) + "月" + event.start._i[2] + "日";
                        var sqlDate = event.start._i[0] + '-' + (event.start._i[1] + 1) + '-' + event.start._i[2];
                        var dur = ((event.start._i[3] * 1 < 10) ? "0" + event.start._i[3] : event.start._i[3]) + ":" + ((event.start._i[4] == "0") ? "00" : event.start._i[4]) + "-" +
                            ((event.end._i[3] * 1 < 10) ? "0" + event.end._i[3] : event.end._i[3]) + ":" + ((event.end._i[4] == "0") ? "00" : event.end._i[4]);
                        //console.log("rtr_no:" + event.id+",brk_date:"+sqlDate+",brk_period:"+dur);
                        $.ajax({
                            url: "<%=request.getContextPath()%>/breaktime/bt.do",
                            data: {action:'btCancel', brk_no:event.id, brk_date: $('#brk_date').val(), brk_period: $('#brk_period').val() },
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
                    }
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
				if (aResR.getResr_states().equals("ESTABLISH")) {%> 
					{
                        id: '<%=aResR.getResr_no()%>',
                        title: '會員:<%=new MemService().getOneByNo(aResR.getMem_no()).getMem_name()%>,<%=new HouseService().getOneHouseInfo(aResR.getHouse_no()).getTitle()%>',
                        color: '#2E6E9E',
                        <%String[] betime = aResR.getResr_period().split("-");%>
                        start: '<%=aResR.getResr_date()%>T<%=betime[0]%>',
                        end: '<%=aResR.getResr_date()%>T<%=betime[1]%>',
                        startEditable:false,
                        durationEditable:false,
                        constraint: 'constraint'
                    },
                    {
                        id: 'constraint',
                        start: '<%=aResR.getResr_date()%>T<%=betime[0]%>',
                        end: '<%=aResR.getResr_date()%>T<%=betime[1]%>',
                        rendering: 'background'

                    },
                    <%}}%> 
                    <c:forEach items='${btSvc.getRtr_bt_kuei(realtorVO.getRtr_no())}' var='btvo'>
                    {
                        id:'${btvo.brk_no}',
                    	title: '休假',
                        //rendering: 'background',
                        //url: 'http://google.com',
                        //overlap: false,
                        //color:'yellow',
                        <c:set var = "betime" value = "${btvo.getBrk_period().split('-')}"/>
                        start: '${btvo.brk_date}T${betime[0]}',
                        end: '${btvo.brk_date}T${betime[1]}', // defined below
                        color: '#257e4a'

                    }, </c:forEach>
                   
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
       
    // 		$("div.fc-right").append('<span class="glyphicon glyphicon-trash trashicon ondragenter=\"myFunction(event)\""></span>');

    // 		$(".trashicon").on("dragenter", function(event) {
    // 		    event.preventDefault();  
    // 		    event.stopPropagation();
    // 		    $('#modalTitle').html(event.title);
    //             $('#modalBody').html("<p>預約日期:"+startDate+"</p><input type='hidden' name='brk_date' value="+event.start._i[0]+'-'+(event.start._i[1]+1)+'-'+event.start._i[2]+"><br>"+
    //             		"<p>預約時間:"+dur+"</p><input type='hidden' name='brk_period' value="+dur+"><input type='hidden' name='rtr_no' value="+event.id+">");
    //             $('#eventUrl').attr('href');
    //             $('#fullCalModal').modal();
    // 		});

    function createEvent(id, text, start, end) {
        var evento = {
            id: id,
            title: text,
            start: start,
            end: end,
            overlap: false,
            color: '#f7ad02'
        };
       $('#calendar').fullCalendar('renderEvent', evento, true);
    };
	
    
    $(".comfirmbt").click(function(){
    	var btlist = "{ btlist : [";
    	for(var cfm=1; cfm<=clickCount ; cfm++){
    		
	    	var event = $('#calendar').fullCalendar( 'clientEvents' , "No."+cfm )[0];
	    	var date0 = event.start._i.split("T");
            var date1 = date0[0].split("-");
            var sqlDate = date1[0] + '-' + date1[1] + '-' + date1[2];
            var dur = date0[1].substring(0,date0[1].lastIndexOf(":")) + "-" + event.end._i.split("T")[1].substring(0,date0[1].lastIndexOf(":"));
            if(cfm==clickCount){
            	btlist+= "{ rtr_no: ${realtorVO.rtr_no},brk_date :"+sqlDate+",brk_period:'"+dur+"'}]}";
            }else{
            	btlist+= "{ rtr_no: ${realtorVO.rtr_no},brk_date :"+sqlDate+",brk_period:'"+dur+"'},";
            }
            $("#calendar").fullCalendar('removeEvents', "No."+cfm);
    	}
    	
    	clickCount=0;
    	
    	$.ajax({
            url: "<%=request.getContextPath()%>/breaktime/bt.do",
            data: {action:'btAdd',btlist:btlist},
            type: "POST",
			dataType:'json',
			
            success: function(event) {
            	for (var i = 0; i < event.length; i++) {
            		
            		//console.log(event[i]);
            	$('#calendar').fullCalendar( 'renderEvent', event[i], true);
            	}
            	
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


	
	<input type="button" class="comfirmbt btn btn-warning" value='確認更改'>
	


							<div id='calendar'></div>
							<div ondragover="myFunction(event)">
							   <img id="calendarTrash" src="<%=request.getContextPath()%>/images/trashcan.png">
							</div>
</div>


</div>
</div>
<!-- footer -->
<footer class="container-fluid" id="cooper_footer">
	<div class="container text-center">
		<div class="col-xs-12 col-sm-2">
			<a href="#">HOME</a>
		</div>
		<div class="col-xs-12 col-sm-2">
			<a href="#">OUR TEAM</a>
		</div>
		<div class="col-xs-12 col-sm-2">
			<a href="#">COMMUNITIES</a>
		</div>
		<div class="col-xs-12 col-sm-2">
			<a href="#">COMMUNITIES</a>
		</div>
		<div class="col-xs-12 col-sm-2">
			<a href="#">COMMUNITIES</a>
		</div>
		<div class="col-xs-12 col-sm-2">
			<a href="#">CONTACT US</a>
		</div>
	</div>
	<div class="copyri text-center">
		<p>
			<small>Copyright © 2017 For House</small>
		</p>
	</div>
</footer>
<!-- footer -->
