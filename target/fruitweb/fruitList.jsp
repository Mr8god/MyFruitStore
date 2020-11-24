<%@ page import="cn.mr8god.fruitweb.model.Fruit" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 硬汉J
  Date: 2020/11/20
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-cn">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="node_modules/bootstrap/dist/css/bootstrap.min.css">
    <title>好家伙水果专卖店</title>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">

                <button class="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <a class="navbar-brand" href="#">
                    <img src="imgs/logo/四大皆空.jpg" height="106.4" width="106.4"/></a>
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="navbar-nav">
                        <li class="nav-item active">
                            <a class="nav-link" href="#">显示所有水果信息 <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="fruitAdd.jsp">添加水果信息</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">查看特定水果信息</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">下架特定水果</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/" onclick="javascrip:window.close(); return false;">退出系统</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-striped table-sm">
                <thead>
                <tr>
                    <th>
                        id
                    </th>
                    <th>
                        水果名称
                    </th>
                    <th>
                        水果单价
                    </th>
                    <th>
                        水果库存数量
                    </th>
                    <th>
                        水果备注信息
                    </th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<Fruit> fruits = (List<Fruit>) request.getAttribute("fruits");
                    for (Fruit fruit :
                            fruits) {
                        out.println("<tr>");
                        out.println("<td>" + fruit.getId() + "</td>");
                        out.println("<td>" + fruit.getName() + "</td>");
                        out.println("<td>" + fruit.getPrice() + "</td>");
                        out.println("<td>" + fruit.getNum() + "</td>");
                        out.println("<td>" + fruit.getRemark() + "</td>");
                        out.println("<td>" +
                                "<a href='fruitDel?id=" + fruit.getId() + "'>删除</a>" +
                                "&nbsp;&nbsp;" +
                                "<a href='fruitEdit?id=" + fruit.getId() + "'>编辑</a>" +
                                "</td>");
                        out.println("</tr>");
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <h2 class="text-center">
                江某人专卖店，我们只是大自然的搬运工，不生产水果，只负责搬运@农夫果园
            </h2>
            <p class="text-center">
                <a>别嫌贵，贵是有贵的道理的。当你买不起江某人的水果的时候，你应该思考一下自己的原因！<br></a>
                <a>好家伙，那家伙又来蹭吃蹭喝了。</a>
            </p>

        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
        </div>
    </div>
</div>
<script src="node_modules/jquery/dist/jquery.slim.js"></script>
<script src="node_modules/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
