<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="design.scoreBean" %>
<%@ page import="design.questionBean" %>
<%
scoreBean score = (scoreBean) session.getAttribute("score");
questionBean questions = (questionBean) session.getAttribute("questions");
%>
<%
String message = "";
if(score.getQnum() < 5){
	message = "次の問題へ";
}
else{ //10問目が終わったらリザルトページへ移動
	message = "結果を見る";
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>不正解ページ</title>
<link rel="stylesheet" href="qdesign.css?<?php echo date('YmdHis'); ?>">
</head>
<body>

<div class="tf_message"><p>不正解…</p></div>
<div class="tf_mark"><img src="./wrong.png"　width="400" height="400"/></div>
<div class="correct_choice"><p>正解：<%= questions.getCorrectChoice() %></p></div>
<div class="next"><a class="next_btn" href="/webapp-group09/MondaiServlet"><%= message %></a></div>

</body>
</html>