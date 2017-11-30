<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.realtor.model.*"%>

<jsp:useBean id="realtorSvc" scope="page" class="com.realtor.model.RealtorService" />
<jsp:useBean id="realestateSvc" scope="page" class="com.realestate.model.RealEstateService" />

<%
response.setHeader("Cache-Control", "no-store"); 
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>

	<%-- 萬用複合查詢-以下欄位-可隨意增減 --%>
	<ul>
		<li>
			<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/front/realtor/realtor.do"	name="form1">
				<b><font color=blue>萬用複合查詢:</font></b> 
				<br> <b>輸入房仲編號:</b> 
				<input type="text" name="RTR_NO" value="RT00000001"><br> 
				<b>輸入房仲姓名:</b>
				<input type="text" name="RTR_NAME" value="葦小寶"><br> 
				<b>輸入房仲IDemail:</b>
				<input type="text" name="RTR_ID" value="realtor001@gmail.com"><br>

				<b>選擇地區:</b> 
				<select size="1" name="RTR_AREA">
				<option value="">
				<c:forEach var="realtorVO" items="${realtorSvc.all}">
					<option value="${realtorVO.rtr_area }">${realtorVO.rtr_area }</option>
				</c:forEach>
				</select><br> 
				<b>選擇服務公司:</b> 
				<select name="RE_NO">
				<option value="">
				<c:forEach var="realestateVO" items="${realestateSvc.all}">
					<option value="${realestateVO.re_no}">${realestateVO.re_name}</option>
				</c:forEach>
				</select>
				 <input type="hidden" name="action" value="listRealtor_ByCompositeQuery">
				<input type="submit" value="送出"> 
			</FORM>
		</li>
	</ul>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>




</body>
</html>