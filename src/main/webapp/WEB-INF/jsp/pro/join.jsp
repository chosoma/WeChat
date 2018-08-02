<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="UTF-8">
    <title>项目工程秘钥</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="css/joinpage.css" rel="stylesheet" media="screen">
    <link href="css/mescroll.css" rel="stylesheet" media="screen">
    <link href="css/mui.min.css" rel="stylesheet" media="screen">

</head>

<body>

<center>
    <div class="form_div">
        <form id="checkform" method="post" action="check.action">
            <input id="key" type="text" name="key" class="key" placeholder="请输入工程秘钥"/>
            <button id="submit" class="submit btn btn-large btn-block btn-primary" type="submit">提交</button>
        </form>
    </div>
</center>
<script src="js/mui.min.js"></script>
<script src="http://code.jquery.com/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<%--<script src="js/joinpage.js"></script>--%>
<script>
    $(function () {
        $(document).keydown(function(event){
            switch(event.keyCode){
                case 13:return false;
            }
        });
        mui.init();
        var flag = ${message.flag};
        if (flag) {
            $("#menu_key").val("${message.info}");
            mui.alert("${message.message}", "${message.title}", "${message.btnval}", null, 'div');// 警告提示
        }
        $("#submit").click(function () {
            $("#check").submit();
        });
    });

</script>
<!--<script src="js/mescroll.js" type="text/javascript" charset="utf-8"></script>-->
</body>

</html>