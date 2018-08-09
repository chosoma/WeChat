<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<%@ page errorPage="../error.jsp" %>
<html>

<head>
    <title>项目工程历史数据</title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="${basePath}/static/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="${basePath}/static/css/mescroll.css" rel="stylesheet" media="screen">
    <link href="${basePath}/static/css/mui.picker.min.css" rel="stylesheet"/>
    <link href="${basePath}/static/css/mui.min.css" rel="stylesheet"/>
    <link href="${basePath}/static/css/datapage.css" rel="stylesheet" media="screen">
    <%--<link rel="stylesheet" href="css/mui.poppicker.css" />--%>
</head>

<body style="font-size: 8px">
<header class="header">
    <div id="back" style="visibility:hidden" class="nvbt iback"></div>
    <div class="nvtt">${company.pro_name}<a href="#dateStart">项目工程历史数据</a></div>
    <div class="nvbt iabout"></div>

</header>

<div id="mescroll" class="mescroll">
    <table class="dateSelect table table-striped table-hover">
        <tr>
            <td>
                <button id="dateStart" class="mui-btn mui-btn-block" type="button">起始日期</button>
            </td>
            <td>
                <button id="dateEnd" class="mui-btn mui-btn-block" type="button">结束日期</button>
            </td>
            <td>
                <button id="form" class="mui-btn mui-btn-block" type="button">查询</button>
            </td>
        </tr>
    </table>

    <table class="table table-striped table-hover">
        <thead id="thead" class="thead">
        <tr>
            <td>表头1</td>
            <td>表头2</td>
            <td>表头3</td>
            <td>表头4</td>
            <td>表头5</td>
            <td>表头6</td>
        </tr>
        </thead>
        <tbody id="dataList" class="data-list">
        <%--<c:out value="data:${datalist}"/>--%>
        <%--<c:forEach items="${datalist}" var="data">--%>
        <%--<tr>--%>
        <%--<td>${data.place}</td>--%>
        <%--<td>${data.xw}</td>--%>
        <%--<c:choose>--%>
        <%--<c:when test="${data.unitType==1}">--%>
        <%--<td>${data.den}兆帕</td>--%>
        <%--<td>${data.pres}兆帕</td>--%>
        <%--</c:when>--%>
        <%--<c:when test="${data.unitType==2}">--%>
        <%--<td>${data.vari}</td>--%>
        <%--</c:when>--%>
        <%--<c:when test="${data.unitType==3}">--%>
        <%--<td>${data.temp}</td>--%>
        <%--</c:when>--%>
        <%--<c:when test="${data.unitType==4}">--%>
        <%--<td>${data.hitchval}</td>--%>
        <%--</c:when> </c:choose>--%>
        <%--<td>${data.batlv}</td>--%>
        <%--<td>${data.date}</td>--%>
        <%--</tr>--%>
        <%--</c:forEach>--%>
        </tbody>
    </table>
</div>

<footer class="footer ">
    <div class="navbar">
        <!--<div class="navbar-inner">-->
        <ul class="nav">
            <li class="active p0">
                <p i="1">六氟化硫</p>
            </li>
            <li class="p1">
                <p i="2">伸缩节</p>
            </li>
            <li class="p2">
                <p i="3">温升</p>
            </li>
            <li class="p3">
                <p i="4">故障</p>
            </li>
            <li class="p4">
                <p i="5">报警</p>
            </li>
        </ul>


        <!--</div>-->
    </div>
</footer>
<script src="http://code.jquery.com/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<%--<script src="demo/res/pdlist1.js" type="text/javascript" charset="utf-8"></script>--%>
<script src="js/mescroll.js" type="text/javascript" charset="utf-8"></script>
<script src="js/mui.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/mui.picker.min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
    $(function () {

        //创建MeScroll对象,内部已默认开启下拉刷新,自动执行up.callback,刷新列表数据;
        var mescroll = new MeScroll("mescroll", {
            //上拉加载的配置项
            up: {
                callback: getListData, //上拉回调,此处可简写; 相当于 callback: function (page) { getListData(page); }
                noMoreSize: 4, //如果列表已无数据,可设置列表的总数量要大于半页才显示无更多数据;避免列表数据过少(比如只有一条数据),显示无更多数据会不好看; 默认5
                empty: {
                    tip: "暂无相关数据~", //提示
                    btnClick: function () { //点击按钮的回调,默认null

                    }
                },
                clearEmptyId: "dataList" //相当于同时设置了clearId和empty.warpId; 简化写法;默认null
            }
        });

        /*初始化菜单*/
        var pdType = 1; //全部
        refreshMenu();
        $(".nav p").click(function () {
//            console.logException("点击下方按钮,类别:" + pdType);
            var i = $(this).attr("i");

            if (pdType != i) {
                //更改列表条件
                pdType = i;
                refreshTime();
                refreshMenu();

                $(".nav .active").removeClass("active");
                $(this).parent().addClass("active");
                //重置列表数据
                mescroll.resetUpScroll();
            }
        });

        function refreshMenu() {
            var str = '';
            if (pdType == 1) {
                str = "<tr><td>监测点</td><td>相位</td><td>压力</td><td>密度</td><td>电量</td><td>日期</td></tr>";
            }
            else if (pdType == 2) {
                str = "<tr><td>监测点</td><td>相位</td><td>偏移量</td><td>电量</td><td>日期</td></tr>";
            }
            else if (pdType == 3) {
                str = "<tr><td>监测点</td><td>相位</td><td>温升</td><td>电量</td><td>日期</td></tr>";
            }
            else if (pdType == 4) {
                str = "<tr><td>监测点</td><td>相位</td><td>故障值</td><td>电量</td><td>日期</td></tr>";
            }
            else if (pdType == 5) {
                str = "<tr><td>监测点</td><td>相位</td><td>警报信息</td><td>处理状态</td><td>日期</td></tr>";
            }
            $("#thead").empty().html(str);
        }

        /*联网加载列表数据  page = {num:1, size:20}; num:当前页 从1开始, size:每页数据条数 */
        function getListData(page) {
            //联网加载数据
//            console.logException("pdType=" + pdType + ", page.num=" + page.num);
            getListDataFromNet(pdType, page.num, page.size, function (data) {
                //联网成功的回调,隐藏下拉刷新和上拉加载的状态;
//                console.logException("data.length=" + data.length);
                mescroll.endSuccess(data.length); //传参:数据的总数; mescroll会自动判断列表如果无任何数据,则提示空;列表无下一页数据,则提示无更多数据;
                //设置列表数据
                setListData(data);
            }, function () {
                //联网失败的回调,隐藏下拉刷新和上拉加载的状态;
                mescroll.endErr();
            });
        }

        /*设置列表数据*/
        function setListData(data) {
            var listDom = $("#dataList");
            for (var i = 0; i < data.length; i++) {
                var pd = {};
                pd = data[i];
                var str = '<tr ';
                if (2 == pd.warn) {
                    str += ' class = "error"';
                } else if (1 == pd.warn) {
                    str += ' class = "warning"';
                } else if (0 == pd.warn) {
                    str += ' class = "success"';
                } else {
                    str += ' class = "info"';
                }
                str += '><td>' + pd.place + '</td>' +
                    '<td>' + pd.xw + '</td>';
                if (pdType == 1) {
                    str += '<td>' + pd.pres + '兆帕</td>' +
                        '<td>' + pd.den + '兆帕</td>' +
                        '<td>' + pd.batlv + '伏</td>';
                }
                else if (pdType == 2) {
                    str += '<td>' + pd.vari + '毫米</td>' +
                        '<td>' + pd.batlv + '伏</td>';
                }
                else if (pdType == 3) {
                    str +=
                        '<td>' + pd.temp + '摄氏度</td>' +
                        '<td>' + pd.batlv + '伏</td>';
                }
                else if (pdType == 4) {
                    str += '<td>' + pd.hitchvol + '</td>' +
                        '<td>' + pd.batlv + '伏</td>';
                }
                else if (pdType == 5) {
                    str += '<td>' + pd.info + '</td>' +
                        '<td>' + pd.handle + '</td>';
                }

                str += '<td>' + pd.date + '</td></tr>';

                listDom.append(str);
            }
        }

        /*联网加载列表数据*/
        function getListDataFromNet(pdType, pageNum, pageSize, successCallback, errorCallback) {

//            console.logException(pdType + ":" + pageNum + ":" + pageSize);

            //延时一秒,模拟联网
            setTimeout(function () {

                $.ajax({
                    type: 'POST',
                    url: 'data.action',
                    data: {
                        "company": "${company.company}",
                        "pro_name": "${company.pro_name}",
                        "unitType": pdType,
                        "pageNum": pageNum,
                        "pageSize": pageSize,
                        "pageStart": (pageNum - 1) * pageSize,
                        "dateStart": dateStratStr,
                        "dateEnd": dateEndStr
                    },
                    dataType: 'json',
                    success: function (data) {
                        var listData = [];
                        for (var i = 0; i < data.length; i++) {
                            listData.push(data[i]);
                        }
                        successCallback(listData);
                    },
                    error: errorCallback
                });
            }, 1000)
        }

        var dateStratStr = "";
        var dateEndStr = "";
// 禁止PC浏览器拖拽图片,避免与下拉刷新冲突;如果仅在移动端使用,可删除此代码
        document.ondragstart = function () {
            return false;
        };

        mui.init();

        var startDatePicker = new mui.DtPicker({
            "type": "datetime",
            beginYear: 1970 //设置开始日期
        });
        var endDatePicker = new mui.DtPicker({
            "type": "datetime",
            beginYear: 1970//设置开始日期
        });

        function refreshTime() {
            dateStratStr = "";
            dateEndStr = "";
        }


        $("#dateStart").click('tap', function (event) {
            startDatePicker.show(function (items) {
                dateStratStr = items.text;
                console.log(items.text);
                if (dateEndStr != "") {
                    $("#form").click();
                }
            });
        }, false);
        $("#dateEnd").click('tap', function (event) {
            endDatePicker.show(function (items) {
                dateEndStr = items.text;
                console.log(items.text);
                if (dateStratStr != "") {
                    $("#form").click();
                }
            });
        }, false);
        $("#form").click(function () {
            if (dateStratStr == "") {
                mui.alert("请选择起始时间", "尚未选择起始时间", "确定", function () {
                    $("#dateStart").click();
                }, "div");
            } else if (dateEndStr == "") {
                mui.alert("请选择结束时间", "尚未选择结束时间", "确定", function () {
                    $("#dateEnd").click();
                }, "div");
            } else {
                mui.confirm("起始时间:" + dateStratStr + "\n结束时间:" + dateEndStr, "确定提交?", ["取消", "确定"], function (e) {
                    if (e.index == 0) {
                        return;
                    } else {
                        mescroll.resetUpScroll();
                        getListData;
                    }
                }, "div");
            }
        });
        //----------------------

    })
    ;
</script>
</body>

</html>