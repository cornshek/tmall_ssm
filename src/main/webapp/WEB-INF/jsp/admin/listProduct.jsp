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
    <title>产品管理</title>
    <script>
        $(function () {
            $("#addForm").submit(function () {
                if (!checkEmpty("name", "产品名称")) {
                    return false;
                }
                if (!checkEmpty("subtitle", "小标题")) {
                    return false;
                }
                if (!checkEmpty("originalPrice", "原价格")) {
                    return false;
                }
                if (!checkEmpty("promotePrice", "优惠价格")) {
                    return false;
                }
                if (!checkEmpty("stock", "库存")) {
                    return false;
                }
                return true;
            });
        });
    </script>
</head>
<body>
<div class="workingArea">

    <ol class="breadcrumb">
        <li><a href="/admin_category_list">所有分类</a></li>
        <li><a href="/admin_product_list?categoryId=${category.id}">${category.name}</a></li>
        <li class="active">产品管理</li>
    </ol>

    <div class="listDataTableDiv">
        <table class="table table-striped table-bordered table-hover table-condensed">
            <thead>
            <tr class="success">
                <th>ID</th>
                <th>图片</th>
                <th>产品名称</th>
                <th>产品小标题</th>
                <th width="53px">原价格</th>
                <th width="80px">优惠价格</th>
                <th width="80px">库存数量</th>
                <th width="80px">图片管理</th>
                <th width="80px">设置属性</th>
                <th width="42px">编辑</th>
                <th width="42px">删除</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${products}" var="product">
                <tr>
                    <td>${product.id}</td>
                    <td>
                            <%--产品图片管理--%>
                        <c:if test="${!empty product.productImage}">
                            <img width="40px" src="img/productSingle/${product.productImage.id}.jpg"/>
                        </c:if>

                    </td>
                    <td>${product.name}</td>
                    <td>${product.subtitle}</td>
                    <td>${product.originalPrice}</td>
                    <td>${product.promotePrice}</td>
                    <td>${product.stock}</td>
                    <td>
                        <a href="admin_productImage_list?productId=${product.id}">
                            <span class="glyphicon glyphicon-picture"/>
                        </a>
                    </td>
                    <td>
                        <a href="admin_propertyValue_edit?productId=${product.id}">
                            <span class="glyphicon glyphicon-list"/>
                        </a>
                    </td>
                    <td>
                        <a href="admin_product_edit?id=${product.id}">
                            <span class="glyphicon glyphicon-edit"/>
                        </a>
                    </td>
                    <td>
                        <a deleteLink="true"
                           href="admin_product_delete?id=${product.id}">
                            <span class="glyphicon glyphicon-trash"/>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="pageDiv">
        <%--        数据库未插入数据，先不使用分页--%>
        <%--        <%@include file="../include/admin/adminPage.jsp" %>--%>
    </div>

    <div class="panel panel-warning addDiv">
        <div class="panel-heading">新增产品</div>
        <div class="panel-body">
            <form method="post" id="addForm" action="/admin_product_add">
                <table class="addTable">
                    <tr>
                        <td>产品名称</td>
                        <td>
                            <input id="name" name="name" type="text"
                                   class="form-control"/>
                        </td>
                    </tr>
                    <tr>
                        <td>产品小标题</td>
                        <td>
                            <input id="subtitle" name="subtitle" type="text"
                                   class="form-control"/>
                        </td>
                    </tr>
                    <tr>
                        <td>原价格</td>
                        <td>
                            <input id="originalPrice" name="originalPrice"
                                   value="99.98" type="text"
                                   class="form-control"/>
                        </td>
                    </tr>
                    <tr>
                        <td>优惠价格</td>
                        <td>
                            <input id="promotePrice" name="promotePrice"
                                   value="19.98" type="text"
                                   class="form-control"/>
                        </td>
                    </tr>
                    <tr>
                        <td>库存</td>
                        <td>
                            <input id="stock" name="stock"
                                   value="999" type="text"
                                   class="form-control"/>
                        </td>
                    </tr>
                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <input type="hidden" name="categoryId" value="${category.id}"/>
                            <button type="submit" class="btn btn-success">提交</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
</body>
</html>
