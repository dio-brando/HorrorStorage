<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>

<h2>괴담 저장소</h2>
</head>
<body>
	<table border="1">
		<tbody>
			<tr>
				<td width="100" align="center">종류</td>
				<td width="400" align="center">제목</td>
				<td width="100" align="center">작성자</td>
			</tr>
		</tbody>
		<tbody>

			<%
				for( int i = 0; i < 3; i++) {
			%>
			<tr>
				<td width="100" align="center">종류</td>
				<td width="400" align="center">제목</td>
				<td width="100" align="center">작성자</td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>


</body>
</html>
