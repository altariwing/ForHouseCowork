<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.employee.model.*"%>
<%
EmployeeVO empVO = (EmployeeVO) session.getAttribute("empVO");
if (empVO == null) {                                             // 如為 null, 代表此user未登入過 , 才做以下工作
    session.setAttribute("location", request.getRequestURI());       //*工作1 : 同時記下目前位置 , 以便於login.jsp登入成功後 , 能夠直接導至此網頁(須配合LoginHandler.java)
    response.sendRedirect("bklogin.jsp");   //*工作2 : 請該user去登入網頁(bklogin.jsp) , 進行登入
    return;
  }
  
%>

<body>
<!-- 後端include -->
<jsp:include page="/back/backend/backend_page.jsp" flush="true" />
<!-- 後端include -->

<style >
 table, th, td {
    border: 0px solid #CCCCFF;
  }
</style>


<!-- 一定要留住的<div> ================================================================================== -->
<div class="col-xs-12 col-sm-10 maincontext">
	<!-- 一定要留住的<div> ================================================================================== -->

	<!-- 以下是你可以放的內容 ================================================================================ -->
	..............後端首頁的內容 ${empVO.emp_name} ${empVO.emp_state} 
	<!-- 以上是你可以放的內容 ================================================================================= -->

	<!-- 一定要留住的</div> ================================================================================== -->
</div>
<!-- 一定要留住的</div> ================================================================================== -->
</body>
</html>