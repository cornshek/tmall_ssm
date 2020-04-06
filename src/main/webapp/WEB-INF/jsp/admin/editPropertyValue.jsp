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
    <script>
        $(function () {
            $("input.pvValue").keyup(function () {
                var value = $(this).val();
                var page = "admin_propertyValue_update";
                var propertyValueId = $(this).attr("propertyValueId");
                var parentSpan = $(this).parent("span");
                parentSpan.css("border", "3px solid yellow");
                $.post(
                    page,
                    {"value":value, "id": propertyValueId},
                    function (result) {
                        if ("success" == result) {
                            parentSpan.css("border", "2px solid green");
                        }else{
                            parentSpan.css("border", "2px solid red");
                        }
                    }
                );
            });
        });
    </script>
</head>
<body>
    <div class="workingArea">
        <ol class="breadcrumb">
            <li><a href="/admin_category_list">所有分类</a></li>
            <li><a href="/admin_property_list?categoryId=${product.categoryId}">${product.category.name}</a></li>
            <li class="active">${product.name}</li>
            <li class="active">编辑产品属性</li>
        </ol>

        <div class="editPVDiv">
            <c:forEach items="${propertyValues}" var="propertyValue">
                <div class="eachPV">
                    <span class="pvName">${propertyValue.property.name}</span>
                    <span class="pvValue">
                        <input style="width: 100%" class="pvValue" propertyValueId="${propertyValue.id}"
                               type="text" value="${propertyValue.value}">
                    </span>
                </div>
            </c:forEach>
            <div style="clear:both"></div>
        </div>
    </div>
</body>
</html>
