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
</head>
<body>

	<nav class="navbar navbar-fixed-top">
		<jsp:include page="/front/navbar.jsp" />
	</nav>
<br><br><br><br><br><br><br><br><br><br>
做做樣子的HTML 
<br>
<%=orderCreated %> ${orderCreated}
<br><br>
<form class = "form" method="post" action="<%=request.getContextPath()%>/front/order/PrdOrd.do">
    <input type="text" name="number" placeholder="card number" /><br>
    <input type="text" name="first-name" placeholder="first name" /><br>
    <input type="text" name="last-name" placeholder="last name" /><br>
    <input type="text" name="expiry" placeholder="expiry" /><br>
    <input type="text" name="cvc" placeholder="cvce" /><br>
    <input type="hidden" name="action" value="pay" /><br>
    <input type="hidden" name="requestURL" value="<%=request.getServletPath() %>" /><br>
    
    <button type="submit" class="btn btn-default">Submit</button>
</form>

$.form({
  type: "POST",
  url: "/front/order/PrdOrd.do",
  data: formData,
  success: function(){
  	if(<%=request.getAttribute("updateSuccess")!=null%>){
				swal('<%=request.getAttribute("updateSuccess")%>', '','success')
				}},
  dataType: "json",
  contentType : "application/json"
});
</body>
</html>