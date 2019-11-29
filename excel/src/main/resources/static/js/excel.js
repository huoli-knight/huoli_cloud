$(function () {

    var positionAttribute = 0;

    var positionCell = 0;

    var headName = "headName";

    $(".menu-button0").click(function () {
        $(".fileConfig").css("visibility", "visible");
        positionAttribute = 0;
    });

    $(".cancel").click(function () {
        $(".fileConfig").css("visibility", "hidden");
    });

    $(".addAttribute").click(function () {
        if (judgeNull("attributeName") == "0") {
            return;
        }
        $(".headName").append("<th class='" + headName + positionAttribute + "'>" +
            $(".attributeName").val() + "(" + positionAttribute + ")" +
            "</th>");
        addRowData();
        positionAttribute++;
    });

    $(".addData").click(function () {
        addData();
    });

    function addRowData() {
        $(".body").find("tr").each(function () {
            $(this).append("<th>" +
                "<input type='text'>" +
                "</th>");
        });
    }

    function addData() {
        $(".body").append("<tr class='body"+ positionCell + "'></tr>");
        $(".headName").find("th").each(function () {
            if ($(this).attr("class") == "select-all-th") {
                $(".body" + positionCell).append("<th class='data'>(" + positionCell +")" +
                    "<input type='checkbox'>" +
                    "</th>");
            } else {
                $(".body" + positionCell).append("<th>" +
                    "<input type='text'>" +
                    "</th>");
            }
        });
        positionCell++;
    }
    
    function judgeNull(name) {
        if ($("." + name).val() == "") {
            $(".tip").html("请输入" + name + "！");
            return "0";
        }
        return "1";
    }

    //todo 为空判断
    $(".submit").click(function () {
        var sheetName = JSON.stringify($(".sheetName").val());
        var fileName = JSON.stringify($(".fileName").val());
        var attributeState = [];
        $(".header").find("tr").each(function () {
            var context = [];
            $(this).find("th").each(function () {
                if ($(this).attr("class") != "select-all-th") {
                    context.push($(this).val());
                }
            });
            attributeState.push({"context": context});
        });
        $(".body").find("tr").each(function () {
            var context = [];
            $(this).find("th").each(function () {
                if ($(this).attr("class") != "data") {
                    context.push($(this).find("input").val());
                }
            });
            attributeState.push({"context": context});
        });
        var attribute = JSON.stringify(attributeState);
        $.ajax({
            dataType: "json",
            type: "post",
            async: false,
            url: "http://172.16.41.126:8080/excel/add",
            contentType: "application/json",
            data: {
                "sheetName": sheetName,
                "fileName": fileName,
                "attribute": attribute
            },
            traditional: true,
            success:function (data) {
                $(".tip").html(data.message);
            }
        });
    });
});