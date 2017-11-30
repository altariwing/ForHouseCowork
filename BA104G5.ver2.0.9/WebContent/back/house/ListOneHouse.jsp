<%@ page contentType="text/html; charset=Big5"%>
<%@ page import="com.house.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	HouseVO houseVO = (HouseVO) request.getAttribute("houseVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>員工資料 - listOneEmp.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
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

<style>
table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}

.img_of_td {
	height: 200px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>房屋資料 ~ 單筆</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/house/back/house_select_page.jsp"><img
						src="<%=request.getContextPath()%>/house/imgs/Houselogo1.png"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>房屋編號</th>
			<th>物件所屬</th>
			<th>原屬序號</th>
			<th>標題</th>
			<th>位置</th>
			<th>房屋類型</th>
			<th>價格</th>
			<th>總坪數</th>
			<th>主建物坪數</th>
			<th>共有建物坪數</th>
			<th>附屬建物坪數</th>
			<th>樓層</th>
			<th>屋齡</th>
			<th>格局</th>
			<th>朝向</th>
			<th>建材</th>
			<th>停車</th>
			<th>土地所屬分區</th>
			<th>土地坪數</th>
			<th>其餘細項</th>
			<th>房屋主圖片</th>
			<th>經度</th>
			<th>緯度</th>
			<th>上架狀態</th>
			<th>新增時間</th>
			<th>最後修改時間</th>
			<th colspan=2>編輯</th>

		</tr>
		<tr>
			<td>${houseVO.house_no}</td>
			<td>${houseVO.re_no}</td>
			<td>${houseVO.house_serial_number}</td>
			<td>${houseVO.title}</td>
			<td>${houseVO.location}</td>
			<td>${houseVO.house_type}"</td>
			<td>${houseVO.price}</td>
			<td>${houseVO.total_pings}</td>
			<td>${houseVO.main_pings}</td>
			<td>${houseVO.amenity_pings}</td>
			<td>${houseVO.accessory_pings}</td>
			<td>${houseVO.floor}</td>
			<td>${houseVO.age}</td>
			<td>${houseVO.pattern}</td>
			<td>${houseVO.orientation}</td>
			<td>${houseVO.building_materials}</td>
			<td>${houseVO.parking_space}</td>
			<td>${houseVO.classification_of_land}</td>
			<td>${houseVO.land_pings}</td>
			<td>${houseVO.data_info}</td>
			<td><img class="img_of_td"
				src="<%=request.getContextPath()%>/house/ImageReader/${houseVO.house_no}"></td>
			<td>${houseVO.lat}</td>
			<td>${houseVO.lng}</td>
			<td>${houseVO.house_states}</td>
			<td>${houseVO.insert_time}</td>
			<td>${houseVO.final_update_time}</td>
			<td>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/house/houseServlet.do"
					style="margin-bottom: 0px;">
					<input type="submit" value="修改"> <input type="hidden"
						name="house_no" value="${houseVO.house_no}"> <input
						type="hidden" name="action" value="GetOndHouseInfoForUpdate">
				</FORM>
			</td>
			<td>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/house/houseServlet.do"
					style="margin-bottom: 0px;">
					<input type="submit" value="刪除"> <input type="hidden"
						name="house_no" value="${houseVO.house_no}"> <input
						type="hidden" name="action" value="delete">
				</FORM>
			</td>
		</tr>
	</table>

</body>
</html>