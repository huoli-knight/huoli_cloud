$(function(){
    $(".menu-button0").click(function () {
        $.ajax({
            type:"get",
            url:"http://172.16.41.126:8080/test/user",
            async:false,
            dataType:"json",
            success:function (data) {
                setTbody(data);
            }
        });
    });

    $(".menu-button1").click(function () {
        var path = prompt("请输入文件名：","");
        $.ajax({
            type:"get",
            url:"http://172.16.41.126:8080/test/read",
            async:false,
            dataType:"json",
            data: {
                path: path
            },
            success:function (data) {
                setTbody(data);
            }
        });
    });

    function setTbody(data) {
        $(".tbody").html("");
        for (var i = 0; i < data.length; i++) {
            $(".tbody").append("<tr>" +
                "<th><input type='checkbox' class='select" + (i + 1) + "'></th>" +
                "<th>" + (i + 1) + "</th>" +
                "<th>" + data[i].userName + "</th>" +
                "<th>" + data[i].userSex + "</th>" +
                "<th>" + data[i].userEmail + "</th>" +
                "<th>" + data[i].userPhone + "</th>" +
                "<th>" + data[i].userMessage + "</th>" +
                "</tr>");
        }
    }

});
