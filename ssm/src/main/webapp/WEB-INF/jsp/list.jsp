<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<style type="text/css">
span {
	padding: 3px;
}

.top {
	width: 800px;
	margin: 0 auto;
	text-align: center;
}
.tab{
	width: 800px;
	border: 1px solid black;
	text-align: center;
	border-collapse: collapse;
	margin-top: 10px;
}
.tab th,.tab td{
	border: 1px solid black;
}
.upview{
	width: 100%;
	height: 100%;
	background: black;
	position: absolute;
	top: 0;
	left: 0;
	opacity: 0.2;
}

.show{
	display: block;
}

.hidden{
	display: none;
}


.view{
	width:360px;
	background: #eee;
	padding: 10px;
	margin: 0 auto;
	margin-top: 60px;
	text-align: center;
	z-index: 1000;
	position: absolute;
	left: 36%;
	top: 4%;
	border: 1px solid black;
	box-shadow: 0 0 10px #000;
}
</style>
</head>
<body>
	<div class="top">
		<form action="/ssm/customer/list" method="post">
			<span>
				客戶名稱 <input type="text" name="name" value="${param.name }">
			</span>
			<span>
				客戶來源 <select name="source">
							<option value="">請選擇</option>
							<c:forEach items="${source }" var="dict">
								<option value="${dict.dict_id }"><c:out value="${dict.dict_item_name }"></c:out></option>
							</c:forEach>
						</select>
			</span>
			<span>
				所屬行業 <select name="industry">
							<option value="">請選擇</option>
							<c:forEach items="${industry }" var="dict">
								<option value="${dict.dict_id }"><c:out value="${dict.dict_item_name }"></c:out></option>
							</c:forEach>
						</select>
			</span>
			<span>
				客戶級別 <select name="level">
							<option value="">請選擇</option>
							<c:forEach items="${level }" var="dict">
								<option value="${dict.dict_id }"><c:out value="${dict.dict_item_name }"></c:out></option>
							</c:forEach>
						</select>
			</span>
			<span>
				<input type="submit" value="查詢">
			</span>
		</form>
	</div>
	<div class="top">
		<table class="tab">
			<thead>
				<tr>
					<th>客戶編號</th>
					<th>客戶名稱</th>
					<th>客戶來源</th>
					<th>所屬行業</th>
					<th>客戶級別</th>
					<th>固定電弧</th>
					<th>手機號碼</th>
					<th>客戶操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list.list }" var="customer">
					<tr>
						<td><c:out value="${customer.cust_id }"></c:out></td>
						<td><c:out value="${customer.cust_name }"></c:out></td>
						<td><c:out value="${customer.source.dict_item_name }"></c:out></td>
						<td><c:out value="${customer.industry.dict_item_name }"></c:out></td>
						<td><c:out value="${customer.level.dict_item_name }"></c:out></td>
						<td><c:out value="${customer.cust_phone }"></c:out></td>
						<td><c:out value="${customer.cust_mobile }"></c:out></td>
						<td><a href="javascript:;" onclick="updatecustomer('${customer.cust_id }')">修改</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="/ssm/customer/list?pageNow=${list.pageNow-1 }&name=<%= URLEncoder.encode(request.getParameter("name")==null?"":request.getParameter("name"), "utf-8") %>&source=${param.source}&industry=${param.industry}&level=${param.level}">上一頁</a>
		<a href="/ssm/customer/list?pageNow=${list.pageNow+1 }&name=<%= URLEncoder.encode(request.getParameter("name")==null?"":request.getParameter("name"), "utf-8") %>&source=${param.source}&industry=${param.industry}&level=${param.level}">下一頁</a>
		第${list.pageNow }頁/共${list.pageCount }頁
	</div>
	
	
	<div id="upview" class="upview hidden">
		
	</div>
	<div id="view" class="view hidden">
			<form id="myForm" action="xxx" method="post"><p>
				客戶名稱 <input id="cust_name" type="text" name="cust_name"><p>
				固定電話 <input id="cust_phone" type="text" name="cust_phone"><p>
				移動電話 <input id="cust_mobile" type="text" name="cust_mobile"><p>
				郵政編碼 <input id="cust_zipcode" type="text" name="cust_zipcode"><p>
				客戶地址 <input id="cust_address" type="text" name="cust_address"><p>
				<input id="cust_id" type="hidden" name="cust_id">
				<input type="button" id="btn1" value="關閉">
				<input type="button" id="btn2" value="保存">
			</form>
		</div>
<script type="text/javascript">

		$("#btn2").click(function() {
			$.ajax({
				url : "/ssm/customer/updatecustomer",
				type : 'POST',
				data : $("#myForm").serialize(),
				dataType : 'json',
				success : function(data) {
					alert(data.bool);
				}
			});
		});

		$("#view").mousedown(function(e) {
			ox = e.offsetX;
			oy = e.offsetY;
			$("body").mousemove(function(event) {
				$("#view").offset({ top:event.clientY-oy, left: event.clientX-ox });
			});
			
			$("body").mouseup(function() {
				$("body").off();
			});
		});
	
		$("#btn1").click(function() {
			$("#upview").addClass("hidden");
			$("#view").addClass("hidden");
		});
		
		function updatecustomer(id) {
			$("#upview").removeClass("hidden");
			$("#view").removeClass("hidden");
			
			$.ajax({
				url : "/ssm/customer/toedit",
				type : 'POST',
				data : 'cust_id='+id,
				dataType : 'json',
				success : function(data) {
					$("#cust_name").val(data.cust_name);
					$("#cust_phone").val(data.cust_phone);
					$("#cust_mobile").val(data.cust_mobile);
					$("#cust_zipcode").val(data.cust_zipcode);
					$("#cust_address").val(data.cust_address);
					$("#cust_id").val(data.cust_id);
				}
			});
		}
		
	
		(function () {
			$("select[name='source'] option").each(function(){
				if(this.value == '${param.source}'){
					this.selected = 'selected';
				}
			});
			$("select[name='industry'] option").each(function(){
				if(this.value == '${param.industry}'){
					this.selected = 'selected';
				}
			});
			$("select[name='level'] option").each(function(){
				if(this.value == '${param.level}'){
					this.selected = 'selected';
				}
			});
		})();
	
</script>
</body>
</html>