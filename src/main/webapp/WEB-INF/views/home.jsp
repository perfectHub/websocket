<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title>Home</title>
</head>
<body>
<div>用户：${user},在线人数：${count}</div>
    Hello world!  This is a WebSocket demo!
        <textarea name="msg" cols="50" id="msg" rows="8"></textarea>
        <input type="button" value="send" onclick="send();">
    <div id="message">

    </div>
<script type="text/javascript" src="js/jquery-1.12.2.min.js"></script>
<script type="text/javascript" src="js/sockjs.min.js"></script>


<script type="text/javascript">
    function send(){
        var msg = $("#msg").val();
        $.ajax({
            url:'http://localhost:8182/socket/message?user=${user}',
            data:{msg:msg},
            dataType:'json',
            success:function(data){
                if(data){

                }
            }
        })
    }
    $(function(){
        //建立socket连接
        var sock;
        sock = new SockJS("http://localhost:8182/socket/sockjs/socketServer");
        sock.onopen = function (e) {
            console.log(e);
        };
        sock.onmessage = function (e) {
            console.log(e)
            $("#message").append("<span style='color:red'>"+e.data+"</span><br>")
        };
        sock.onerror = function (e) {
            console.log(e);
        };
        sock.onclose = function (e) {
            console.log(e);
        }
        function send1(){
            sock.send("aaaaaaaaaa");
        }
    });

</script>

</body>
</html>