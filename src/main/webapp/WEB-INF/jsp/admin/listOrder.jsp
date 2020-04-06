<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/4/6
  Time: 13:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="../include/admin/adminHeader.jsp" %>
<%@include file="../include/admin/adminNavigator.jsp" %>

<html>
<head>
    <title>订单管理</title>
    <script>
        $(function () {
            $("button.orderPageCheckOrderItems").click(function () {
                var orderId = $(this).attr("orderId");
                $("tr.orderPageOrderItemTR[orderId=" + orderId + "]").toggle();
            });
        });
    </script>
</head>
<body>
<div class="workingArea">
    <h1 class="label label-info">订单管理</h1>
    <br>
    <br>

    <div class="listDataTableDiv">
        <table class="table table-striped table-bordered table-hover table-condensed">
            <thead>
            <tr class="success">
                <th>ID</th>
                <th>状态</th>
                <th>金额</th>
                <th width="100px">商品数量</th>
                <th width="100px">买家名称</th>
                <th>创建时间</th>
                <th>支付时间</th>
                <th>发货时间</th>
                <th>确认收货时间</th>
                <th width="120px">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${orders}" var="order">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.statusCN}</td>
                    <td>￥<fmt:formatNumber type="number" value="${order.totalSum}" minFractionDigits="2"/></td>
                    <td align="center">${order.totalNumber}</td>
                    <td align="center">${order.user.name}</td>

                    <td><fmt:formatDate value="${order.createdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td><fmt:formatDate value="${order.paydate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td><fmt:formatDate value="${order.deliverydate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td><fmt:formatDate value="${order.confirmdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>

                    <td>
                        <button class="orderPageCheckOrderItems btn btn-primary btn-xs" orderId=${order.id}>查看详情</button>

                        <c:if test="${order.status=='waitDelivery'}">
                            <a href="admin_order_delivery?id=${order.id}">
                                <button class="btn btn-primary btn-xs">发货</button>
                            </a>
                        </c:if>
                    </td>
                </tr>
                <tr class="orderPageOrderItemTR" orderId=${order.id}>
                    <td colspan="10" align="center">
                        <div class="orderPageOrderItem">
                            <table width="800px" align="center" class="orderPageOrderItemTable">
                                <c:forEach items="${order.orderItems}" var="orderItem">
                                    <tr>
                                        <td align="left">
                                            <img width="40px" height="40px"
                                                 src="img/productSingle/${orderItem.product.productImage.id}.jpg">
                                        </td>

                                        <td>
                                                <%--超链接foreProduct作用未明--%>
                                                <%--                                            <a href="foreProduct?productId=${orderItem.product.id}">--%>
                                            <span>${orderItem.product.name}</span>
                                                <%--                                            </a>--%>
                                        </td>
                                        <td align="right">

                                            <span class="text-muted">${orderItem.number}个</span>
                                        </td>
                                        <td align="right">

                                            <span class="text-muted">单价：￥${orderItem.product.promotePrice}</span>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
