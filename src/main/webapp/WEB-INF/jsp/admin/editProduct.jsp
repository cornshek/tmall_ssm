<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/4/3
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="../include/admin/adminHeader.jsp" %>
<%@include file="../include/admin/adminNavigator.jsp" %>
<html>
<head>
    <title>编辑产品</title>
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
            <li><a href="/admin_product_list?categoryId=${product.category.id}">${product.category.name}</a></li>
            <li class="active">${product.name}</li>
            <li class="active">编辑产品</li>
        </ol>

        <div class="panel panel-warning editDiv">
            <div class="panel-heading">编辑产品</div>
            <div class="panel-body">
                <form method="post" id="addForm" action="/admin_product_update">
                    <table class="addTable">
                        <tr>
                            <td>产品名称</td>
                            <td>
                                <input id="name" name="name" type="text"
                                       value="${product.name}" class="form-control"/>
                            </td>
                        </tr>
                        <tr>
                            <td>产品小标题</td>
                            <td>
                                <input id="subtitle" name="subtitle" type="text"
                                       value="${product.subtitle}" class="form-control"/>
                            </td>
                        </tr>
                        <tr>
                            <td>原价格</td>
                            <td>
                                <input id="originalPrice" name="originalPrice"
                                       value="${product.originalPrice}" type="text"
                                       class="form-control"/>
                            </td>
                        </tr>
                        <tr>
                            <td>优惠价格</td>
                            <td>
                                <input id="promotePrice" name="promotePrice"
                                       value="${product.promotePrice}" type="text"
                                       class="form-control"/>
                            </td>
                        </tr>
                        <tr>
                            <td>库存</td>
                            <td>
                                <input id="stock" name="stock"
                                       value="${product.stock}" type="text"
                                       class="form-control"/>
                            </td>
                        </tr>
                        <tr class="submitTR">
                            <td colspan="2" align="center">
                                <input type="hidden" name="id" value="${product.id}"/>
                                <input type="hidden" name="categoryId" value="${product.category.id}"/>
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
