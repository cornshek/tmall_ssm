<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>
<%@ page import="org.shek.tmall.pojo.Category" %>

<%@include file="../include/admin/adminHeader.jsp" %>
<%@include file="../include/admin/adminNavigator.jsp" %>

<html>
<head>
    <title>编辑分类</title>

    <script>
        $(function () {

            $("#editForm").submit(function () {
                if (!checkEmpty("name", "分类名称"))
                    return false;
                return true;
            });
        });
    </script>
</head>

<body>
<div class="workingArea">

    <ol class="breadcrumb">
        <li><a href="admin_category_list">所有分类</a></li>
        <li class="active">编辑分类</li>
    </ol>

    <div class="panel panel-warning editDiv">
        <div class="panel-heading">编辑分类</div>
        <div class="panel-body">
            <form method="post" id="editform" action="admin_category_update"
                  enctype="multipart/form-data">
                <table class="editTable">
                    <%Category category = (Category) request.getAttribute("category");%>
                    <tr>
                        <td>分类名称</td>
                        <td>
                            <input id="name" name="name" class="form-control"
                                   value="<%=category.getName()%>" type="text">
                        </td>
                    </tr>
                    <tr>
                        <td>分类图片</td>
                        <td>
                            <input id="categoryPic" accept="image/*"
                                   type="file" name="image">
                        </td>
                    </tr>
                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <input type="hidden" name="id" value="<%=category.getId()%>">
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