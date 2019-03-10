<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="javax.naming.*,redis.clients.jedis.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Jedis test</title>
</head>
<body>
<%
Context initCtx = new InitialContext();
Context envCtx = (Context) initCtx.lookup("java:comp/env");

JedisPoolAbstract thePool = (JedisPoolAbstract) envCtx.lookup("redis/jedis");
String back;

try (Jedis jedis = thePool.getResource()) {
	back = jedis.ping("Kaboom");
}
%>
<h1><%= back %></h1>
</body>
</html>