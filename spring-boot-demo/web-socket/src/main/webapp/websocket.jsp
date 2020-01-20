<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="EN">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>Document</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="../../../resources/static/js/websocket.js" type="text/javascript"></script>
</head>

<body>
    测试一下WebSocket站点吧
    <br/>
    <label for="message"></label>
    <input id="message" type="text"/>
    <button onclick="sendMessage()">发送消息</button>
    <button onclick="closeWebsocket()">关闭WebSocket连接</button>
    <div id="context"></div>
</body>

</html>