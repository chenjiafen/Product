<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1px" align="center">
		<tr>
			<th width="8%">商品图片</th>
			<th width="8%">商品id</th>
			<th width="8%">商品名称</th>
			<th width="8%">市场价格</th>
			<th width="8%">商城价格</th>
			<th width="8%">上架时间</th>
			<th>商品描述</th>
		</tr>
		<c:if test="${empty list}">
			<tr>
				<td colspan="7" align="center">暂无商品</td>
			
			</tr>
		
		</c:if>
		
		<c:if test="${not empty list}">
			<c:forEach items="${list}" var="pro">
					<tr>
						<td><img src="${pageContext.request.contextPath}/${pro.pimage}" height="75px"></td>
						<td>${pro.pid}</td>
						<td>${pro.pname}</td>
						<td>${pro.market_price}</td>
						<td>${pro.shop_price}</td>
						<td>${pro.pdate}</td>
						<td>${pro.pdesc}</td>
					
					</tr>
			</c:forEach>
		
		</c:if>
	
	</table>
</body>
</html>