<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/stompjs@2.3.3/lib/stomp.min.js"></script>

<input id="me" placeholder="내 아이디">
<input id="to" placeholder="상대 아이디">
<input id="msg">
<button onclick="send()">보내기</button>

<ul id="chat"></ul>

<script>
let stompClient = null;

function connect() {
  const socket = new SockJS('/ws-chat');
  stompClient = Stomp.over(socket);

  const me = document.getElementById("me").value;

  stompClient.connect({}, function () {
    stompClient.subscribe('/queue/chat/' + me, function (msg) {
      const body = JSON.parse(msg.body);
      const li = document.createElement("li");
      li.innerText = body.sender + " : " + body.message;
      document.getElementById("chat").appendChild(li);
    });
  });
}

function send() {
  stompClient.send("/app/chat.send", {}, JSON.stringify({
    sender: document.getElementById("me").value,
    receiver: document.getElementById("to").value,
    message: document.getElementById("msg").value
  }));
}
</script>

<button onclick="connect()">연결</button>

</body>
</html>