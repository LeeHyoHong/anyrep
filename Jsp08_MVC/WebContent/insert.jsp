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

<%
	boolean bool = (boolean)request.getAttribute("bool");

	if(bool){
%>
	<script type="text/javascript">
		alert("글쓰기 성공");
		location.href="controller.do?command=list";
	</script>
<%
	} else{
%>
	<script type="text/javascript">
		alert("글쓰기 실패")
		location.href="controller.do?command=insertform";
	</script>
<%	
	}
%>

</body>
</html>