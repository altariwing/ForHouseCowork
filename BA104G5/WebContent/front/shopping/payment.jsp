<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String orderCreated = (String)session.getAttribute("orderCreated");
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.0.0/sweetalert2.all.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.0.0/sweetalert2.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.0.0/sweetalert2.min.css"></script>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/Houselogo1.png" />
<title>For House</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
<script src="https://code.jquery.com/jquery.js"></script>
<script src="<%=request.getContextPath()%>/tools/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

<style type="text/css">
form_main {
    width: 100%;
}
.form_main h4 {
    font-family: roboto;
    font-size: 20px;
    font-weight: 300;
    margin-bottom: 15px;
    margin-top: 20px;
    text-transform: uppercase;
}
.heading {
    border-bottom: 1px solid #fcab0e;
    padding-bottom: 9px;
    position: relative;
}
.heading span {
    background: #9e6600 none repeat scroll 0 0;
    bottom: -2px;
    height: 3px;
    left: 0;
    position: absolute;
    width: 75px;
}   
.form {
    border-radius: 7px;
    padding: 6px;
}
.txt[type="text"] {
    border: 1px solid #ccc;
    margin: 10px 0;
    padding: 10px 0 10px 5px;
    width: 95%;
}
.txt_3[type="text"] {
    margin: 10px 0 0;
    padding: 10px 0 10px 5px;
    width: 100%;
}
.txt2[type="submit"] {
    background: #242424 none repeat scroll 0 0;
    border: 1px solid #4f5c04;
    border-radius: 25px;
    color: #fff;
    font-size: 16px;
    font-style: normal;
    line-height: 35px;
    margin: 10px 0;
    padding: 0;
    text-transform: uppercase;
    width: 30%;
}
.txt2:hover {
    background: rgba(0, 0, 0, 0) none repeat scroll 0 0;
    color: #5793ef;
    transition: all 0.5s ease 0s;
}
.newDivCenter{
margin-top:15px;
	left:17%;
	text-align:center;
}

.green{

 color: #05b70d;
 font-size: 2em;
}
</style>

</head>
<body>

	<nav class="navbar navbar-fixed-top">
		<jsp:include page="/front/navbar.jsp" />
	</nav>
<br><br><br><br><br><br>

<br>
<p class="green text-center">${orderCreated}</p>

<%-- 錯誤表列 --%>




<c:if test="${not empty errorMsgs}">

<div class="panel panel-danger">
      <div class="panel-heading">請修正以下錯誤</div>
      <div class="panel-body">
      <ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	  </ul>
      
      
      </div>



	
	
	
</div>
</c:if>


<br>


<div class='card-wrapper'></div>
<!-- CSS is included via this JavaScript file -->
<script src="<%=request.getContextPath()%>/front/shopping/js/card.js"></script>







  
<div class="container">
	<div class="row">
    <div class="col-md-8 newDivCenter">
		<div class="form_main">
                <h4 class="heading"><strong>結帳金額: ${subtotal} 請填寫付款資訊   </strong> <span></span></h4>
                <div class="form">
                <form class = "form" method="post" action="<%=request.getContextPath()%>/front/order/PrdOrd.do">
                	
                	<%-- 對的值設回去 --%>
					<c:if test="${not empty formVelues}">
					
						
						
						<script>
						$(document).ready(function() {
							
							$('.txt').next().after("<img src='<%=request.getContextPath()%>/front/shopping/images/Wrong.png'>");	
							<c:forEach var="message" items="${formVelues}">
							document.getElementById('${message.key}').value = '${message.value}';	
							document.getElementById('${message.key}').style.border='2px solid #12b715';
							$('#${message.key}').next().next().remove();
							$('#${message.key}').next().after("<img src='<%=request.getContextPath()%>/front/shopping/images/Check.png'>");
							</c:forEach>						
						});
						</script>
							
					</c:if>	
					
                
                    <input type="text" name="number" id="number" class="txt" placeholder="card number" /><span></span><br>
				    <input type="text" name="first_name" id="first_name" class="txt" placeholder="first name" /><span></span><br>
				    <input type="text" name="last_name" id="last_name" class="txt"  placeholder="last name" /><span></span><br>
				    <input type="text" name="expiry" id="expiry" class="txt" placeholder="expiry" /><span></span><br>
				    <input type="text" name="cvc" id="cvc" class="txt" placeholder="cvce" /><span></span><br>
				    
				    
				    
				    <input type="hidden" name="action" value="pay" /><br>
				    <input type="hidden" name="requestURL" value="<%=request.getServletPath() %>" /><br>
				    <button type="submit" class="btn btn-primary btn-block">送出</button>
                </form>
				    <button class="btn btn-info magic">Magic</button>
            </div>
            </div>
            </div>
	</div>
</div>



<script>
var card = new Card({
    form: 'form',
    container: '.card-wrapper',

    formSelectors: {
        nameInput: 'input[name="first_name"], input[name="last_name"]'
    }
});

$(".magic").click(function(){
	$("#number").val("5535 3881 8362 0246");
	$("#first_name").val("EMMA");
	$("#last_name").val("QUINCY");
	$("#expiry").val("07/19");
});

</script>






</body>
</html>