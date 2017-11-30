<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>hosue_select_page</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>House Information Data Control</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for house data control</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='<%=request.getContextPath() %>/house/back/ListALLHouse.jsp'>List</a> all HouseInfo.  <br><br></li>
    
  <li>
    <FORM METHOD="post" ACTION="<%= request.getContextPath()%>/house/houseServlet.do" >
        <b>請輸入房屋編號 (如HSE00000001~HSE00000003):</b>
        <input type="text" name="house_no">
        <input type="hidden" name="action" value="getOneHouseInfo">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="houseSvc" scope="page" class="com.house.model.HouseService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%= request.getContextPath()%>/house/houseServlet.do" >
       <b>選擇房屋編號:</b>
       <select size="1" name="house_no">
         <c:forEach var="houseVO" items="${houseSvc.all}" > 
          <option value="${houseVO.house_no}">${houseVO.house_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOneHouseInfo">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%= request.getContextPath()%>/house/houseServlet.do" >
       <b>選擇房屋標題:</b>
       <select size="1" name="house_no">
         <c:forEach var="houseVO" items="${houseSvc.all}" > 
          <option value="${houseVO.house_no}">${houseVO.title}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOneHouseInfo">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>房屋管理</h3>

<ul>
  <li><a href='<%=request.getContextPath() %>/house/back/AddHouse.jsp'>Add</a> a new House.</li>
</ul>

</body>
</html>