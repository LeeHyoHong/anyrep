<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <% request.setCharacterEncoding("UTF-8"); %>
	<% response.setContentType("text/html; charset=UTF-8"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>글 수정</h1>
		<form action="controller.do" method="post">
			<input type="hidden" name="command" value="update"/>
			<input type="hidden" name="seq" value="${dto.seq }"/>	
			
			<table border="1">
				<tr>
					<th>번	호</th>
					<td>${dto.seq }</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${dto.writer }</td>
				</tr>
				<tr>
					<th>작성일</th>
					<td>${dto.regdate }</td>
				</tr>
				<tr>
					<th>제	목</th>
					<td><input type="text" name="title" value="${dto.title }" /></td>
				</tr>
				<tr>
					<th>내	용</th>
					<td><textarea rows="10" cols="60" name="content" >${dto.content}</textarea>
				</td>
				<tr>
					<td colspan="2">
					<input type="submit" value="등록">
					<input type="button" value="삭제" onclick="location.href='controller.do?command=delete&seq=${dto.seq}'">
					<input type="button" value="취소">			
					</td>
				</tr>
			</table>	
		</form>

</body>
</html>