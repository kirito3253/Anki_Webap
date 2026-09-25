<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="design.scoreBean" %>
<%@ page import="design.userBean" %>
<%
scoreBean score = (scoreBean) session.getAttribute("score");
userBean user = (userBean) session.getAttribute("user");
%>
<%
String message = "";
if(score.getScore() <= 1){
	message = "頑張ろう…";
}
else if(score.getScore() <= 3){
	message = "いい感じ。";
}
else{
	message = "すごい！";
}
%>
<%
String percentage= (score.getScore()*20)+"%";
%>
<%
//変更部分
String notification = user.getName()+"さんが"+score.getScore()+"点を獲得しました";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>結果発表ページ</title>
<link rel="stylesheet" href="qdesign.css?<?php echo date('YmdHis'); ?>">
</head>
<body>
<div class="evaluation"><p><%= message %></p></div>
<div class="score" style="--percentage: <%= percentage %>;"><p><%= score.getScore() %>/5点</p></div>

<!-- 変更部分。動くかわからない -->
<form method="GET" action="http://10.22.240.27:3000/voice">
<input type="hidden" name="msg" value="<%= notification %>"/>
<div class="notification"><input class="note_btn" type="submit" value="友達に知らせる"/></div>
</form>

<div class="back"><a class="back_btn" href="index.html">トップページ</a></div>
</body>
</html>