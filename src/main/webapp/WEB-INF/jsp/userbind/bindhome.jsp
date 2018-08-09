<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<%@ page errorPage="../error.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title>工程管理</title>
    <link href="${basePath}/static/css/mui.min.css" rel="stylesheet"/>
    <link href="${basePath}/static/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="${basePath}/static/css/bindhome.css" rel="stylesheet" media="screen">
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="${basePath}/static/js/mui.min.js"></script>
    <script src="${basePath}/static/js/bootstrap.min.js"></script>
    <script type="text/javascript" charset="utf-8">
        mui.init();
        var del = function () {
            var pro = this;
            mui.confirm("确定删除" + $(pro).attr("name") + "?", "删除工程", ["取消", "确定"], function (e) {
                if (e.index) {
                    mui.ajax("delbind.action", {
                        data: {
                            <%--"userInfo":${userInfo},--%>
                            "pro_name": $(pro).attr("name")//此处要加一个userinfo
                        },
                        dataType: 'json', //服务器返回json格式数据
                        type: 'post', //HTTP请求类型
                        success: function (data) {
                            if (data.flag) {
                                $(pro).parent().parent().remove();
                            }
                            mui.alert(data.message, data.title, data.btnval, null);
                        },
                        error: function (xhr, type, errorThrown) {
                            mui.alert("请稍后再试或联系服务人员", "删除失败", "确定", null);
                        }
                    });
                }
            });
        };
        var add = function () {
            mui.prompt("", "请输入工程秘钥", "添加工程", ["取消", "确定"], function (e) {
                if (e.index) {
                    mui.ajax("addbind.action", {
                        data: {
                            <%--"userInfo":${userInfo},--%>
                            "cd_key": e.value//此处要加一个userinfo
                        },
                        dataType: 'json', //服务器返回json格式数据
                        type: 'post', //HTTP请求类型

                        success: function (data) {
                            /*
                            btnval: "确定"
                            flag: false
                            info: ""
                            message: "设置失败,CD_KEY有误!"
                            title: "失败"
                             */
                            if (data.flag) {
                                $(".table").append("<tr><td>" +
                                    "<input name='" +
                                    data.info +
                                    "' class='edit' type='button' value='设置主工程'/>" +
                                    "</td><td>" +
                                    data.info +
                                    "</td><td>" +
                                    "<input name='" +
                                    data.info +
                                    "' class='del' type='button' value='删除工程'/>" +
                                    "</td></tr>");
                                $(".edit").click(edit);
                                $(".del").click(del);
                            }
                            mui.alert(data.message, data.title, data.btnval, null);
                        },
                        error: function (xhr, type, errorThrown) {
                            mui.alert("请稍后再试或联系服务人员", "添加失败", "确定", null);
                        }
                    });
                }
            });
        };
        var edit = function () {
            var pro = this;
            mui.confirm("确定设置主工程为" + $(pro).attr("name") + "?", "设置主工程", ["取消", "确定"], function (e) {
                console.log($(pro).attr("name"));
                if (e.index) {
                    mui.ajax("editbind.action", {
                        data: {
                            <%--"userInfo":${userInfo},--%>
                            "pro_name": $(pro).attr("name")//此处要加一个userinfo
                        },
                        dataType: 'json', //服务器返回json格式数据
                        type: 'post', //HTTP请求类型
                        success: function (data) {
                            if (data.flag) {
                                $(".subjective").removeClass("subjective").addClass("edit").val("设置主工程").click(edit);
                                $(pro).removeClass("edit").addClass("subjective").val("主工程").unbind();
                            }
                            mui.alert(data.message, data.title, data.btnval, null);
                        },
                        error: function (xhr, type, errorThrown) {
                            mui.alert("请稍后再试或联系服务人员", "设置失败", "确定", null);
                        }
                    });
                }
            });
        };
        $(function () {
            $(".del").click(del);
            $(".add").click(add);
            $(".edit").click(edit);
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
            <c:forEach items="${userList}" var="user">
                <tr>
                    <c:choose>
                        <c:when test="${user.subjective}">
                            <td><input name="gil1" class="subjective" type="button" value="主工程"/></td>
                        </c:when>
                        <c:otherwise>
                            <td><input name="${user.pro_name}" class="edit" type="button" value="设置主工程"/></td>
                        </c:otherwise>
                    </c:choose>
                    <td>${user.pro_name}</td>
                    <td><input name="${user.pro_name}" class="del" type="button" value="删除工程"/></td>
                </tr>
            </c:forEach>
            <%--<tr>--%>
            <%--<td><span>主工程</span></td>--%>
            <%--<td>gil1</td>--%>
            <%--<td><input name="gil1" class="del" type="button" value="删除工程"/></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
            <%--<td><input name="gil2" class="edit" type="button" value="设置主工程"/></td>--%>
            <%--<td>gil2</td>--%>
            <%--<td><input name="gil2" class="del" type="button" value="删除工程"/></td>--%>
            <%--</tr>--%>
        </table>
    </div>
</div>
</body>

</html>