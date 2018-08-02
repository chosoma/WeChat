<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>工程管理</title>
    <link href="../../static/css/mui.min.css" rel="stylesheet" />
    <link href="../../static/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="../../static/css/bindhome.css" rel="stylesheet" media="screen">
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="../../static/js/mui.min.js"></script>
    <script src="../../static/js/bootstrap.min.js"></script>
    <script type="text/javascript" charset="utf-8">
        mui.init();
        $(function() {
            $(".del").click(function() {
                var pro = this;
                mui.confirm("确定删除" + $(pro).attr("name") + "?", "删除工程", ["取消", "确定"], function(e) {
                    if(e.index) {
                        mui.ajax("delbind.action", {
                            data: {
                                "key": $(pro).attr("name") //此处要加一个userinfo
                            },
                            dataType: 'json', //服务器返回json格式数据
                            type: 'post', //HTTP请求类型
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            success: function(data) {
                                mui.alert("", "删除成功", "确定", null);
                            },
                            error: function(xhr, type, errorThrown) {
                                mui.alert("请稍后再试或联系服务人员", "删除失败", "确定", null);
                            }
                        });
                    }
                });
            });

            $(".add").click(function() {
                mui.prompt("", "请输入工程秘钥", "添加工程", ["取消", "确定"], function(e) {
                    if(e.index) {
                        mui.ajax("addbind.action", {
                            data: {
                                "key": e.value //此处要加一个userinfo
                            },
                            dataType: 'json', //服务器返回json格式数据
                            type: 'post', //HTTP请求类型
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            success: function(data) {
                                mui.alert("", "添加成功", "确定", null);
                            },
                            error: function(xhr, type, errorThrown) {
                                mui.alert("请稍后再试或联系服务人员", "添加失败", "确定", null);
                            }
                        });
                    }

                });
            });

            $(".edit").click(function() {
                var pro = this;
                mui.confirm("确定设置主工程为" + $(pro).attr("name") + "?", "设置主工程", ["取消", "确定"], function(e) {
                    console.log($(pro).attr("name"));
                    if(e.index) {
                        mui.ajax("editbind.action", {
                            data: {
                                "key": $(pro).attr("name") //此处要加一个userinfo
                            },
                            dataType: 'json', //服务器返回json格式数据
                            type: 'post', //HTTP请求类型
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            success: function(data) {
                                mui.alert("", "设置成功", "确定", null);
                            },
                            error: function(xhr, type, errorThrown) {
                                mui.alert("请稍后再试或联系服务人员", "设置失败", "确定", null);
                            }
                        });
                    }
                });

            });
        });
    </script>
</head>

<body>
<header>
    <h4>尊敬的用户,您好!</h4>
</header>
<div>
    <div class="pro">
        您的工程 <input class="add" type="button" value="添加工程">
    </div>
    <div class="tablediv">
        <table class="table table-striped table-hover">
            <tr>
                <td><span>主工程</span></td>
                <td>gil1</td>
                <td><input name="gil1" class="del" type="button" value="删除工程" /></td>
            </tr>
            <tr>
                <td><input name="gil2" class="edit" type="button" value="设置主工程" /></td>
                <td>gil2</td>
                <td><input name="gil2" class="del" type="button" value="删除工程" /></td>
            </tr>
        </table>
    </div>
</div>
</body>

</html>