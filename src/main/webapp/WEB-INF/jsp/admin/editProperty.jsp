<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/4/3
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>

<html>
<head>
    <title>编辑属性</title>
</head>
<body>
    <div class="workingArea">
        <ol class="breadcrumb">
            <li><a href="/admin_category_list">所有分类</a></li>
            <li><a href="/admin_property_list?categoryId=${property.categoryId}">${category.name}</a></li>
            <li class="active">编辑属性</li>
        </ol>

        <div class="panel panel-warning editDiv">
            <div class="panel-heading">编辑属性</div>
            <div class="panel-body">
                <form method="post" id="editForm"  action="admin_property_update">
                    <table class="editTable">
                        <tr>
                            <td>属性名称</td>
                            <td>
                                <input id="name" name="name" value="${property.name}"
                                       type="text" class="form-control">
                            </td>
                        </tr>
                        <tr class="submitTR">
                            <td colspan="2" align="center">
                                <input type="hidden" name="id" value="${property.id}">
                                <input type="hidden" name="categoryId" value="${category.id}">
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
