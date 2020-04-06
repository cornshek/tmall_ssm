<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/4/3
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="../include/admin/adminHeader.jsp" %>
<%@include file="../include/admin/adminNavigator.jsp" %>

<html>
<head>
    <title>产品图片管理</title>
    <script>
        $(function () {
            $(".addFormSingle").submit(function () {
                if (checkEmpty("filepathSingle", "图片文件")) {
                    $("#filepathSingle").value("");
                    return true;
                }
                return false;
            });
            $(".addFormDetail").submit(function () {
                if (checkEmpty("filepathDetail", "图片文件")) {
                    $("#filepathDetail").value("");
                    return true;
                }
                return false;
            });
        });
    </script>
</head>
<body>
<div class="workingArea">

    <ol class="breadcrumb">
        <li><a href="/admin_category_list">所有分类</a></li>
        <li><a href="/admin_product_list?categoryId=${product.category.id}">${product.category.name}</a></li>
        <li class="active">${product.name}</li>
        <li class="active">产品图片管理</li>
    </ol>

    <table class="addPictureTable" align="center">
        <tr>
            <td class="addPictureTableTD">
                <%-- 此处使用了div，下面没有，观察其不同--%>
                <div>
                    <table class="table table-striped table-bordered table-hover table-condensed">
                        <thead>
                        <tr class="success">
                            <th>ID</th>
                            <th>产品单个图片缩略图</th>
                            <th>删除</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${singleProductImages}" var="productImage">
                            <tr>
                                <td>${productImage.id}</td>
                                <td>
                                    <a title="点击查看原图" href="img/productSingle/${productImage.id}.jpg">
                                        <img height="50px" src="img/productSingle/${productImage.id}.jpg">
                                    </a>
                                </td>
                                <td>
                                    <a deleteLink="true" href="admin_productImage_delete?id=${productImage.id}">
                                        <span class="glyphicon glyphicon-trash"></span>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                    <div class="panel panel-warning addPictureDiv">
                        <div class="panel-heading">新增产品<b class="text-primary">单个</b>图片</div>
                        <div class="panel-body">
                            <form method="post" class="addFormSingle"
                                  action="admin_productImage_add" enctype="multipart/form-data">
                                <table class="addTable">
                                    <tr>
                                        <td>请选择本地图片 尺寸400X400 为佳</td>
                                    </tr>
                                    <tr>
                                        <td><input id="filepathSingle" type="file" name="image"/></td>
                                    </tr>
                                    <tr class="submitTR">
                                        <td align="center">
                                            <input type="hidden" name="type" value="type_single"/>
                                            <input type="hidden" name="productId" value="${product.id}"/>
                                            <button type="submit" class="btn btn-success">提 交</button>
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                    </div>
                </div>
            </td>

            <td class="addPictureTableTD">
                <table class="table table-striped table-bordered table-hover table-condensed">
                    <thead>
                    <tr class="success">
                        <th>ID</th>
                        <th>产品详情图片缩略图</th>
                        <th>删除</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${detailProductImages}" var="productImage">
                        <tr>
                            <td>${productImage.id}</td>
                            <td>
                                <a title="点击查看原图" href="img/productDetail/${productImage.id}.jpg">
                                    <img height="50px" src="img/productDetail/${productImage.id}.jpg">
                                </a>
                            </td>
                            <td>
                                <a deleteLink="true" href="admin_productImage_delete?id=${productImage.id}">
                                    <span class="glyphicon glyphicon-trash"></span>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="panel panel-warning addPictureDiv">
                    <div class="panel-heading">新增产品<b class="text-primary">详情</b>图片</div>
                    <div class="panel-body">
                        <form method="post" class="addFormDetail"
                              action="admin_productImage_add" enctype="multipart/form-data">
                            <table class="addTable">
                                <tr>
                                    <td>请选择本地图片 尺寸790 为佳</td>
                                </tr>
                                <tr>
                                    <td><input id="filepathDetail" type="file" name="image"/></td>
                                </tr>
                                <tr class="submitTR">
                                    <td align="center">
                                        <input type="hidden" name="type" value="type_detail"/>
                                        <input type="hidden" name="productId" value="${product.id}"/>
                                        <button type="submit" class="btn btn-success">提 交</button>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
