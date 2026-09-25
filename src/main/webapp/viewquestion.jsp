<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="design.questionBean" %>
<%@ page import="design.scoreBean" %>
<%
questionBean questions = (questionBean) session.getAttribute("questions");
scoreBean score = (scoreBean) session.getAttribute("score");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>問題ページ</title>
<link rel="stylesheet" href="qdesign.css?<?php echo date('YmdHis'); ?>">
</head>
<body>

<div class="q_num"><p>【第<%= score.getQnum() %>問】</p></div>

<div class="question"><%= questions.getQuestion() %></div>

<form action="/webapp-group09/MondaiServlet" method="post">

<div class="choices">

<div>
<button id="choice_a" type="submit" name="choice" value="A">
<%= questions.getChoice_a() %></button>
</div>

<div>
<button id="choice_b" type="submit" name="choice" value="B">
<%= questions.getChoice_b() %></button>
</div>

<div>
<button id="choice_c" type="submit" name="choice" value="C">
<%= questions.getChoice_c() %></button>
</div>

<div>
<button id="choice_d" type="submit" name="choice" value="D">
<%= questions.getChoice_d() %></button>
</div>

</div>

</form>

</body>
</html>