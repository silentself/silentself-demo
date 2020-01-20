var websocket = null;

if ('WebSocket' in window){
    websocket = new WebSocket("ws://localhost:8080/ws")
} else {
    alert('NO SUPPORT WEBSOCKET')
}

websocket.onerror = function () {
    appendMessage('error');
};

websocket.onopen = function (event) {
    appendMessage("open");
};

websocket.onmessage = function(event){
    appendMessage(event.data);
}

websocket.onclose = function(){
    appendMessage("close");
};

window.onbeforeunload = function (ev) {
    websocket.close();
};

function appendMessage(message) {
    var context = $('#context').html() +"<br/>" +message;
    $("#context").html(context);
}

function closeWebSocket() {
    websocket.close();
}

function sendMessage() {
    var message = $("#message").val();
    websocket.send(message);
}