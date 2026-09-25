<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="design.AllScore" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Scores</title>
</head>
<body>
    <h1>Scores List</h1>
    <table border="1">
        <thead>
            <tr>
                <th>Name</th>
                <th>Score</th>
            </tr>
        </thead>
        <tbody>
            <%-- リクエストスコープからスコアリストを取得 --%>
            <%
                List<AllScore> scores = (List<AllScore>) request.getAttribute("scores");
                if (scores != null) {
                    for (AllScore score : scores) {
            %>
            <tr>
                <td><%= score.getName() %></td>
                <td><%= score.getScore() %></td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="2">No scores available.</td>
            </tr>
            <% } %>
        </tbody>
    </table>
    <a href="index.html">トップページに戻る</a>
</body>
</html>
