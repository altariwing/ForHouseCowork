<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.realtor.model.*"%>
<%
  MemVO memVO = (MemVO) session.getAttribute("memVO");
  RealtorVO realtorVO = (RealtorVO) session.getAttribute("realtorVO");
%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/images/Houselogo1.png" />
    <title>For House</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
    <script src="<%=request.getContextPath()%>/js/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/main.js"></script>
    <style type="text/css">
    .headerBar{
    	width: 95em;
    }
    
    .imgbox {
        position: relative;
        z-index: -1;
        height: 20em;
        width: 20em;
    }

    .ina:hover {
        opacity: 0.85;
    }

    .imgbox img {
        position: relative;
        z-index: -10;
        max-height: 100%;
        box-shadow: 1px 1px 1px #bdbdbd;
    }

    .textbox {
        position: absolute;
        top: 0;
        z-index: 11;
        padding-top: 10em;
        width: 90%;
        height: 100%;
        text-align: center;
    }

    .textbackground {
        background-color: #ffd600;
        height: 3em;
        box-shadow: 1px 1px 1px grey;
        opacity: 0.8;
    }

    .textinline {
        /* height: 3em;*/
        position: relative;
        z-index: 5;
        opacity: 1;
        margin-top: -0.5em;
        line-height: 2;
    }

    .searchSRS {
        margin-top: 8em;
    }

    .furnitureAd {
        margin-top: 8em;
    }

    .furnitureAd>img {
        max-height: 100%;
        max-width: 100%;
    }

    .news {
        margin-top: 8em;
    }

    .redNote {
        position: relative;
        z-index: 3;
        height: 2em;
        width: 6em;
        margin-left: 9.7em;
        margin-right: 10em;
        background-color: red;
    }

    .newss {
        position: relative;
        z-index: 1;
        margin-top: -2.5em;
        background-color: #fff9c4;
    }

    .newss h3 {
        padding-top: 1.5em;
    }

    ul {
        list-style-type: none;
    }

    .textfix {
        margin-left: -15px;
    }
    .textfix ul{
        padding-bottom: 2em;
    }
    </style>
<style type="text/css">
#chat {
    position: fixed;
    right: 12em;
    bottom: 10px;
    z-index: 2;
}


/*#chaticon:hover{
display: none;
background-image: url("chat1-128x128.png");
}*/

.chatroom {
    display: none;
}

#chatshow {
    /*resize: none; */
    background: #fff;
    border-radius: 0px;
    width: 415px;
    height: 506px;
    padding: 5px;
    overflow-y: auto;
    
}

.btnsend {
    margin-top: 15px;
    margin-right: 15px;
}

.chatentire {
    padding-right: 0px;
    padding-left: 0px;
    padding-top: 0px;
}

.textinput {
    margin-top: -6px;
    border: none;
    text
}

.userline {
    border: 1px solid #efefef; 
    padding: 5px;
     border-radius: 10px; 
     box-shadow: 2px 2px 2px gray; 
    color: green;
    margin: 2px 10px;
    /*display:block; */
    max-width: 300px;
    word-wrap: break-word;
    float: left;
    /*text-overflow: ellipsis; */
<%--     background-image: url("<%= request.getContextPath()%>/images/speech-bubble.svg"); --%>
/*     background-size:100%;                  */
/*     background-repeat:no-repeat; */
}

.anotherline {
    border: 1px solid #f0f0f0;
    padding: 5px;
    border-radius: 10px;
    box-shadow: 2px 2px 2px gray;
    color: blue;
    margin: 2px 10px;
    /*display: block; */
    max-width: 300px;
    word-wrap: break-word;
    float: right;
}


/*.pos{ */
/*padding-top: 10px; */
/*display: inline-block; */
/*} */

.mydate {
    padding-top: 5px;
    padding-left: 264px;
}

.yourdate {
    padding-top: 5px;
    padding-right: 264px;
}


/*.twoman{  */
/*width: 400px; */
/*}  */

.pic {
    font-size: 40px;
    color: #8bb9e2;
    padding-left: 16px;
    padding-top: 11px;
}

.pic:hover {
    color: #55a6ee;
}

.showimg {
    max-width: 300px;
}

#imgfile {
    display: none;
}
.msghead{
	height:36px;
}
</style>
    <script type="text/javascript">
    $(document).ready(function() {
    	
    	$(".chatbtn").click(function(){
    		console.log($(this).closest(".rtrbar").find(".info_estate>li").text());		    		    		  			
   		
    	});
    	
    	
    	
    	
    	
    	if($(".logina").find("a").text().indexOf("登出")>0){
    		$("#chat").hide();
    	}
//     	else{
<%--     		var usn = '<%= (memVO==null)? null:memVO.getMem_id()%>'; --%>
//             var MyPoint = "/Msg/"+usn+"/MB00001";
//             var host = window.location.host;
//             var path = window.location.pathname;
//             var webCtx = path.substring(0, path.indexOf('/', 1));
//             endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
//     		   connect();
//     	}
    	$("#logout").click(function(){
    		sendMessage();
            disconnect();
    	});
    	
        $("#chat").hover(function() {
            $("#chaticon").attr("src", "<%= request.getContextPath()%>/images/chat1-128x128.png");
        }, function() {
            $("#chaticon").attr("src", "<%= request.getContextPath()%>/images/chat-64x64.png");
        });

        $("#chaticon").click(function() {
            $(this).closest("#chat").find(".chatroom").show();
            $(".textinput").focus();
            $(this).css("display", "none");
           
        });

        $(".minicon").click(function() {
            $(this).closest("#chat").find("#chaticon").show();
            $(this).closest(".chatroom").css("display", "none");
        });
        
        $(".btnsend").click(function() {
            sendMessage();
        });
        
        $("#chatshow").on("dragover", function(event) {
            event.preventDefault();  
            event.stopPropagation();
        });

        $("#chatshow").on("dragleave", function(event) {
            event.preventDefault();  
            event.stopPropagation();
        });
        
        $("#chatshow").on("drop", function(event) {
        		event.preventDefault();
            	var files = event.originalEvent.dataTransfer.files;
            	console.log(files[0].size);
            	console.log(event.originalEvent.dataTransfer.getData('text/html'));
            	
            	if(files.length==0){
            		return false;
            	}
            	for(i=0;i<files.length;i++){
            		if(files[i].size>4823450){
            			alert("檔案太大囉!!");
            		
            		}else if(files[i].size < 4823450 && files[i].type.match(/image.*/)){
                		handleFiles(files[i]);
                	}else if(files[i].size < 4823450 &&files[i].type.match(/text.*/)){
                		handleTextFiles(files[i]);
                	}else{
                		alert("Not txt or jpg file!!");
                		return false;
                	}
                }
            });
        
        
        document.getElementById("imgfile").onchange=function(e){
    		for(var i = 0 ; i <e.target.files.length;i++){
    		var file =e.target.files[i];
    		handleFiles(file);
    		}
		};
        
       
        
        function handleFiles(file){
        	var img = document.createElement("img");
        	img.file = file;    	
        	var reader = new FileReader();
        	reader.onload=function(e){
        			loadimg(e.target.result);
        		};
        	reader.readAsDataURL(file);
        };
        
        
//         function handleFiles(file){
//         	 var reader = new FileReader();
//         reader.onload = function (readerEvent) {
//             var image = new Image();
//             image.onload = function (imageEvent) {

//                 // Resize the image
//                 var canvas = document.createElement('canvas'),
//                  MAX_WIDTH = 300;
//                  MAX_HEIGHT = 400;
//                 var width = img.width;
//                 var height = img.height;
//                 if (width > height) {
//                     if (width > MAX_WIDTH) {
//                         height *= MAX_WIDTH / width;
//                         width = MAX_WIDTH;
//                     }
//                 } else {
//                     if (height > MAX_HEIGHT) {
//                         width *= MAX_HEIGHT / height;
//                         height = MAX_HEIGHT;
//                     }
//                 }
//                 canvas.width = width;
//                 canvas.height = height;
//                 canvas.getContext('2d').drawImage(image, 0, 0, width, height);
//                 resizedImage = canvas.toDataURL('image/jpeg');
//             }
//             loadimg(readerEvent.target.result)
//         }
//         reader.readAsDataURL(file);
//       };  
        
        
        function loadimg(datauri){
        	var imge = datauri;//.replace(/^data:image\/(png|jpeg);base64,/, "");
        	var userName = $("#username").text().trim();
        	var d = new Date();
   	    	var mydate = ((d.getHours()<10)? "0"+d.getHours() : d.getHours())+":"+((d.getMinutes()<10)? "0"+ d.getMinutes() : d.getMinutes());
   	 
   	  			var JSONimg = {
   					"userName": userName,
   					'type' : 'img',
   	   				 'data' : imge,
   	 				"mydate": mydate
   	  			}
    			webSocket.send(JSON.stringify(JSONimg));
        }
        	
//         	var canvas = document.createElement("canvas");
//             canvas.width = img.width;
//             canvas.height = img.height;

//             // Copy the image contents to the canvas
//             var ctx = canvas.getContext("2d");
//             ctx.drawImage(img, 0, 0);

//             // Get the data-URL formatted image
//             // Firefox supports PNG and JPEG. You could check img.src to guess the
//             // original format, but be aware the using "image/jpg" will re-encode the image.
//             var dataURL = canvas.toDataURL("image/png");

//             var imge = dataURL.replace(/^data:image\/(png|jpeg);base64,/, "");
        	       
        
        function handleTextFiles(file){    	
        	var textArea = document.createElement("textarea");
        	textArea.setAttribute("cols","50");
        	textArea.setAttribute("rows","20");    	
        	var reader = new FileReader();
        		reader.onload=function(){
        		textArea.innerHTML = reader.result;
     			};
        	reader.readAsText(file);
        	document.body.appendChild(textArea);
        };

    });
    </script>
    <script>
    
    var webSocket;
    
    //webSocket.binaryType = "arraybuffer"; //"arraybuffer"  2種type
    
    function connect() {
        // 建立 websocket 物件
        webSocket = new WebSocket(endPointURL);
        //console.log(endPointURL);
//         webSocket.on('connection', function connection(ws, req) {
//         	  var ip = req.headers['x-forwarded-for'];
//         	  console.log(req.connection.remoteAddress);
//         	});

        webSocket.onopen = function (event , req) {
        	
            //$("#chatshow").text("WebSocket 成功連線");
            // document.getElementById('sendMessage').disabled = false;
            // document.getElementById('connect').disabled = true;
            // document.getElementById('disconnect').disabled = false;
        };

        webSocket.onmessage = function(event) {
            var messagesArea = $("#chatshow");            
            var jsonObj = JSON.parse(event.data);
            //console.log("onmessage:"+$(".chatroom").is(":visible")==false);
            
            if(jsonObj.type =='img' && $("#username").text()==jsonObj.userName){
            	var message ="<div class='pull-right twoman'><img class='showimg' src='"+ jsonObj.data
				+ "' ><span class='pos'>" + jsonObj.userName+"</span></div><br><div class='mydate text-center'>"
				+ jsonObj.mydate+"</div>";
                
            }else if(jsonObj.type =='img' && $("#username").text()!=jsonObj.userName){
            	var message = "<div class='pull-left twoman'><span class='pos'>"+jsonObj.userName 
				+ "</span><img class='showimg' src='"+ jsonObj.data
				+ "' ></div><br><div class='yourdate text-center'>"+jsonObj.mydate+"</div>";
            }else  if($(".chatroom").is(":visible")==false){
            	var message = "<div class=''>"+jsonObj.userName 
							+ "" + jsonObj.message
							+"  "+jsonObj.mydate+"</div>";
            }else if($("#username").text()==jsonObj.userName){
            	var message = "<div class='pull-right twoman'><span class='userline'><b>"+jsonObj.message 
            				+ "</b></span><span class='pos'>" + jsonObj.userName+"</span></div><br><div class='mydate text-center'>"
            				+ jsonObj.mydate+"</div>";
            }else{
            	var message = "<div class='pull-left twoman'><span class='pos'>"+jsonObj.userName 
            				+ "</span><span class='anotherline'><b>" + jsonObj.message
            				+"</b></span></div><br><div class='yourdate text-center'>"+jsonObj.mydate+"</div>";
            }
            messagesArea.html( messagesArea.html()+"<br>"+message);
            
            var elem = document.getElementById('chatshow');
            elem.scrollTop = elem.scrollHeight;
        	   // $("#chatshow").scrollTop( $("#chatshow")[0].scrollHeight);
        	
        };

        webSocket.onclose = function(event) {
        	
        };   
    }


    function sendMessage() {
        var userName = $("#username").text().trim();
        
        if (userName === ""){
	        alert ("使用者名稱請勿空白!");
	        $("#username").focus();	
			return;
	    }
        
        console.log("sendMessage:"+$(".chatroom").is(":visible")==false);
       		 
        	 var message = $(".textinput").val().trim();
        
        	 var d = new Date();
        	 var mydate = ((d.getHours()<10)? "0"+d.getHours() : d.getHours())+":"+((d.getMinutes()<10)? "0"+ d.getMinutes() : d.getMinutes());
        	 
        	 if($(".chatroom").is(":visible")==false){
        		 
        		 var jsonObj = { "userName": userName, "message": "已離線", "mydate": mydate };
        		 webSocket.send(JSON.stringify(jsonObj));
                 $(".textinput").val("");
                 
                }else if (message === "") {
             	
              	 $(".textinput").focus();
              	 
          	    } else { 
            	 var jsonObj = { "userName": userName, "message": message, "mydate": mydate };
                 webSocket.send(JSON.stringify(jsonObj));
                 $(".textinput").val("");
                 $(".textinput").focus(); 
            	 
             }
        	        
    }


    function disconnect() {
        webSocket.close();
//         document.getElementById('sendMessage').disabled = true;
//         document.getElementById('connect').disabled = false;
//         document.getElementById('disconnect').disabled = true;
    }

    
    
</script>
</head>

<body>
    <!-- <nav class="navbar navbar-fixed-top"> -->
        <div class="container headerBar" id="headerBar">
            <div class="navbar-header col-xs-11 col-sm-2">
                <a class="navbar-brand" href="<%= request.getContextPath()%>/front/index.jsp"><img class="navshadow" src="<%=request.getContextPath()%>/images/For House logo.png" width="120px"></a>
            </div>
            <div class="col-xs-12 col-sm-8">
                <ul class="nav navbar-nav activebar">
                    <li><a href="#" data-toggle="tooltipNews" data-placement="bottom" >最新消息</a></li>
                    <li><a href="#">常見問題</a></li>
                    <li><a href="<%=request.getContextPath()%>/house/houseServlet.do?action=getAll">看房去</a></li>
                    <c:if test="${realtorVO == null}">
                    	<li><a href="<%=request.getContextPath()%>/front/realtor/realtor.do?action=listQueryB">找房仲</a></li>
                    </c:if>
                    <li><a href="<%=request.getContextPath()%>/front/shopping/listAllPrd.jsp">安家商城</a></li>
                    <c:if test="${memVO==null}"> <!-- 判斷如果會員登入了就不顯示 "加入我們" -->
                    	<li><a href="<%=request.getContextPath()%>/front/index.jsp#joinUs" data-toggle="modal">加入我們</a></li>
                    </c:if>
                </ul>
            </div>
            <div class="col-xs-12 col-sm-2">
                <ul class="nav navbar-nav logina">
                <c:if test="${memVO==null && realtorVO == null}">
				<li><a href='<%=request.getContextPath()%>/front/register.jsp'><span
						class='glyphicon glyphicon-edit'></span> 註冊</a></li>
				<li><a href='<%=request.getContextPath()%>/front/login.jsp'><span
						class='glyphicon glyphicon-log-in'></span> 登入</a></li>
			</c:if>
			<c:if test="${memVO!=null}">
				<li><a href='<%=request.getContextPath()%>/front/member/memdata.jsp'><span
						class='glyphicon glyphicon-user'><%=memVO.getMem_name()%></span></a></li>
				<li><a href='<%=request.getContextPath()%>/front/logout.do'><span
						class='glyphicon glyphicon-log-out'></span>登出</a></li>
			</c:if>
			<c:if test="${realtorVO!=null}">
				<li><a
					href='<%=request.getContextPath()%>/front/realtor/realtor_center.jsp'><span
						class='glyphicon glyphicon-user'><%=realtorVO.getRtr_name()%></span></a></li>
				<li><a href='<%=request.getContextPath()%>/front/realtor/realtorlogout.do'><span
						class='glyphicon glyphicon-log-out'></span>登出</a></li>
			</c:if>
                </ul>
            </div>
        </div>
    <!-- </nav>  -->
    
    <!-- 聊天室   -->
    <div id="chat"><img id="chaticon" src="<%= request.getContextPath()%>/images/chat-64x64.png" style="cursor:pointer">
        <div class="panel panel-primary chatroom">
            <div class="panel-heading msghead">
                <h3 class="panel-title text-center"><span id="user"></span><span class="glyphicon glyphicon-minus pull-right minicon" style="cursor:pointer"></span></h3>
            </div>
            <div class="panel-body chatentire">
                <div>
                    <div id="chatshow" class="fixedhw"></div>
                    <input type="text" class="form-control textinput" id="aa" placeholder="請輸入文字" onkeydown="if (event.keyCode == 13) sendMessage();">
                    <label for="imgfile" class="custom-file-upload">
                    	<i class="glyphicon glyphicon-picture pic" style="cursor:pointer"></i>
                    </label>
                    <input type="file" id="imgfile" name="profile_pic" accept=".jpg, .jpeg, .png" multiple>
                    <button class="btn btn-success pull-right btnsend" type="button">送出</button>
                </div>
            </div>
        </div>
    </div>
    <!-- /聊天室   -->
</body>
</html>